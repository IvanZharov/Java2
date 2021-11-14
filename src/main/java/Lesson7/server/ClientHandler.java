package Lesson7.server;

import Lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
                    authentification();
                    readMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    private void authentification() throws IOException {
        while (true) {
            String str = in.readUTF();
            if(str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                String nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);

                if (nick != null) {
                    name = nick;

                    if (server.activeClientsChecker(name)) {
                        System.out.println("Такой пользователь уже находится в чате");
                    } else {
                        sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                        server.broadcastMessage(nick + " has entered the chat");
                        server.subscribe(this);
                    }
                    return;
                } else {
                    System.out.println("Incorrect login/password");
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
        while(true) {
            String messageFromClient = in.readUTF();

            if (messageFromClient.equals(Constants.DIRECT_MESSAGE_COMMAND)) {
                String[] messageDirect = messageFromClient.split("\\s+");
                String nameReceiver = messageDirect[1];

                /**Если есть команда письма конкретному пользователю, и этот пользователь есть в активном чате,
                 * то мы отправляем ему письмо, за исключением первых двух элементов.
                 */


            } else {
                System.out.println("Message from: " + name + ": " + messageFromClient);
                if (messageFromClient.equals(Constants.END_COMMAND)) {
                    break;
                }
                server.broadcastMessage(messageFromClient);
            }
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " has left");
        try {
            in.close();
        }catch (IOException ex) {

        }
        try {
            out.close();
        }catch (IOException ex) {

        }
        try {
            socket.close();
        }catch (IOException ex) {

        }
    }
}
