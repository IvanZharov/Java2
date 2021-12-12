package Lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            System.out.println("Сервер ожидает подключения... ");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился!");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String message = dataInputStream.readUTF();
                String consoleMessage = reader.readLine();

                    if (message.equals("/end")) {
                        dataOutputStream.writeUTF(message);
                        break;
                    }
                    System.out.println("Клиент прислал " + message);
                    dataOutputStream.writeUTF("Echo: " + message);

                    System.out.println("Ввели через консоль " + consoleMessage);
                    dataOutputStream.writeUTF("Server Echo: " + consoleMessage);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
