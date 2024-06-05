import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("酒店管理系统-登录");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));


        panel.add(new JLabel("用户名:"));
        usernameField = new JTextField(10);
        panel.add(usernameField);

        panel.add(new JLabel("密码:"));
        passwordField = new JPasswordField(10);
        panel.add(passwordField);

        JButton login = new JButton("登录");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try(Connection connection=DataBaseConnection.getConnection()){
                    String sql="SELECT *FROM admin WHERE username=? and password=?";
                    PreparedStatement statement=connection.prepareStatement(sql);
                    statement.setString(1,username);
                    statement.setString(2,password);
                    ResultSet resultSet=statement.executeQuery();

                    if(resultSet.next()) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "登录成功！");
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.setVisible(true);
                        dispose();
                        //加载主界面
                    }
                        else{
                            JOptionPane.showMessageDialog(LoginFrame.this,"用户名或密码不正确。");
                        }
                }catch(SQLException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginFrame.this,ex.getMessage());
                }


            }
        });
        panel.add(login);

        JButton register = new JButton("注册");
        register.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });
        panel.add(register);

        add(panel);
        setVisible(true);

    }

}