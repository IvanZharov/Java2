package Lesson6;

import Lesson4.WindowApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientApp extends JFrame {

    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 8089;

    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientApp() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String messageFromServer = dataInputStream.readUTF();
                        if (messageFromServer.equals("/end")) {
                            break;
                        }
                        textArea.append(messageFromServer);
                        textArea.append("\n");
                    }
                    textArea.append("Socket closed");
                    textField.setEnabled(false);
                    closeConnection();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {

        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {

        }
        try {
            socket.close();
        } catch (Exception ex) {

        }
    }

    private void sendMessage() {
        if (textField.getText().trim().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");
            textField.grabFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void prepareUI() {
        setTitle("ClientApp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 400, 800, 1200);
        setLayout(null);

        JTextField field = new JTextField();
        field.setBounds(50,600,500,100);
        add(field);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50,50,500,500);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(textArea);

        JButton button = new JButton("Ввод");
        button.setBounds(200, 750, 120,40);
        add(button);
        setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientApp::new);
    }
}
