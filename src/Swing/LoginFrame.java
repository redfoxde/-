package Swing;

import DAO.UserService;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends javax.swing.JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserService us;

    public LoginFrame() {
        us = new UserService();

        setTitle("酒店管理系统-登录");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("用户名:"));
        panel.add(usernameField);
        panel.add(new JLabel("密码"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if(us.loginUser(username, password)) {
                JOptionPane.showMessageDialog(null,"登录成功！");
                new MainFrame();
                dispose();

            }else{
                JOptionPane.showMessageDialog(null,"用户名或密码不正确！");
            }

        });

        registerButton.addActionListener(e -> new RegisterFrame());

    }

}
