import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nicknameField;
    private JTextField IDField;

    public RegisterFrame() {
        setTitle("酒店管理系统-注册");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 3));

        panel.add(new JLabel("用户名:"));
        usernameField = new JTextField(10);
        panel.add(usernameField);

        panel.add(new JLabel("密码:"));
        passwordField = new JPasswordField(10);
        panel.add(passwordField);

        panel.add(new JLabel("昵称:"));
        nicknameField = new JTextField(10);
        panel.add(nicknameField);

        panel.add(new JLabel("ID:"));
        IDField = new JTextField(10);
        panel.add(IDField);

        JButton register = new JButton("注册");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String nickname = nicknameField.getText();
                String id = IDField.getText();
                try (Connection connection = DataBaseConnection.getConnection()) {
                    String sql = "insert into admin(id,username,nickname,password) values(?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, username);
                    statement.setString(3, nickname);
                    statement.setString(4, password);
                    int resultSet = statement.executeUpdate();

                    if (resultSet>0) {
                        JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功！");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(RegisterFrame.this, "注册失败，请重试！");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
            }
        });
        panel.add(register);
        add(panel);
        setVisible(true);
    }


}
