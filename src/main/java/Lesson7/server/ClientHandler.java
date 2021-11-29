package Lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import Lesson7.constants.Constants;

/**
 * Обработчик для конкретного клиента.
 */
public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

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
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick.isPresent()) {
                    //Дописать проверку, что такого ника нет в чате(*)
                    //Авторизовались
//-------------------------------------------------------------------------------------------
                    if (server.getActiveClients().contains(nick.get())) {
                        sendMessage("Такой ник уже есть в активном чате");
//-------------------------------------------------------------------------------------------
                    } else {
                        name = nick.get();
                        sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                        server.broadcastMessage(nick + " вошел в чят");
                        server.broadcastMessage(server.getActiveClients());
                        server.subscribe(this);
                        return;
                    }
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
/*
* В методе readMessage() есть ошибка при вызове MyServer.sendMessageToClient:
* Non-static method 'sendMessageToClient(java.lang.String, java.lang.String, java.lang.String)' cannot be referenced from a static context
* Решить самостоятельно не получилось.
* */
    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            //hint: можем получать команду
            if (messageFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMessage(server.getActiveClients());
            } else if (messageFromClient.equals(Constants.DIRECT_MESSAGE_COMMAND)) {
                    String[] tokens = messageFromClient.split("\\s");
                    String nickToSend = tokens[1];
                    String message = messageFromClient.substring(4 + nickToSend.length());
                    MyServer.sendMessageToClient(name, nickToSend, message);
            } else {
                System.out.println("Сообщение от " + name + ": " + messageFromClient);
                if (messageFromClient.equals(Constants.END_COMMAND)) {
                    break;
                }
                server.broadcastMessage(name + ": " + messageFromClient);
            }
        }
    }

    public String getName() {
        return name;
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

//-------------------------------------------------------------------------------------------
    private void authTimeChecker() {
        new Thread(() -> {
            try {
                Thread threadAuthChecker = new Thread();
                while (!threadAuthChecker.isInterrupted()) {
                    for (int i = 0; i < 12 ; i++) {
                        threadAuthChecker.sleep(10_000);
                        if (name != null) {
                            threadAuthChecker.interrupt();
                        }
                    }
                    sendMessage("Вы не подключились, соединение разорвано");
                    closeConnection();
                }
                threadAuthChecker.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }
//-------------------------------------------------------------------------------------------
}