package Swing;

import DAO.UserService;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private UserService us = new UserService();

    public RegisterFrame() {

        setTitle("酒店管理系统-注册");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("用户名:"));
        panel.add(usernameField = new JTextField(10));
        panel.add(new JLabel("密码:"));
        panel.add(passwordField = new JPasswordField(10));
        panel.add(new JLabel("确认密码"));
        panel.add(confirmPasswordField = new JPasswordField(10));
        JButton registerButton = new JButton("注册");
        JButton cancelButton = new JButton("取消");
        panel.add(registerButton);
        panel.add(cancelButton);
        add(panel);

        registerButton.addActionListener(e -> {
            //获取密码和确认密码
            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            //比较是否一致
            if (password.equals(confirmPassword)) {
                String username = usernameField.getText();
                String passwordstr=String.valueOf(passwordField.getPassword());
                if(us.registerUser(username, passwordstr)) {
                    JOptionPane.showMessageDialog(null, username + " 注册成功!");
                }else{
                    JOptionPane.showMessageDialog(null,"注册失败!");
                }
            }else{
                JOptionPane.showMessageDialog(null,"两次输入密码不一致!");
            }
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        cancelButton.addActionListener(e -> dispose());

    }
}
