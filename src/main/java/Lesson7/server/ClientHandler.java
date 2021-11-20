package Lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Lesson7.constants.Constants;

/**
 * Обработчик для конкретного клиента.
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public String getName() {
        return name;
    }

    private String name;



    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authTimeChecker();
                    authentification();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    // /auth login pass

    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");    //3
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick != null) {
                    //Дописать проверку что такого ника нет в чате(*)
                    //Авторизовались
                    name = nick;
                    sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " вошел в чят");
                    server.subscribe(this);
                    return;
                } else {
                    sendMessage("Неверные логин/пароль");
                }
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            //hint: можем получать команду

            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            } else if (messageFromClient.equals(Constants.DIRECT_MESSAGE_COMMAND)) {
                String[] tokens = messageFromClient.split("\\s");
                String nick = tokens[1];
                String message  = messageFromClient.substring(4 + nick.length());
                MyServer.sendMessageToClient(this, nick, message);
            } else {
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            server.broadcastMessage(name + ": " + messageFromClient);
            }
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чята");
        try {
            in.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            out.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            socket.close();
        } catch (IOException ex) {
            //ignore
        }
    }

    private void authTimeChecker() {
            new Thread(() -> {
                try {
                    Thread threadAuthChecker = new Thread();
                    threadAuthChecker.start();
                    threadAuthChecker.join();
                    threadAuthChecker.sleep(2000);
                    if (name != null) {
                        threadAuthChecker.stop();
                        /**
                         * Нужно через .interrupt(), но я не придумал как связать команду из
                         * authentification() и этот checker (из того метода не видно этот
                         * поток, поэтому не получилось послать сюда команду),
                         * поэтому через плохой вариант с .stop()
                         */
                    } else {
                        sendMessage("Вы не подключились, соединение разорвано");
                        closeConnection();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

    }
}