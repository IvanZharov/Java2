package Lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowApp extends JFrame {

    public WindowApp() throws HeadlessException {
        setTitle("Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 400, 800, 1200);
        setLayout(null);
        JTextField field = new JTextField();
        field.setBounds(50,600,500,100);
        add(field);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(50,50,500,500);
        add(textArea);
        JButton button = new JButton("Ввод");
        button.setBounds(200, 750, 120,40);
        add(button);
        setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typedText = e.getActionCommand();
                textArea.setText(textArea.getText() + "   " + field.getText());
            }
        });
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea.setText(textArea.getText() + "   " + field.getText());
            }
        });

    }

    public static void main(String[] args) {
        new WindowApp();
    }
}
