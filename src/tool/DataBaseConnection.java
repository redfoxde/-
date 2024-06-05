package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    //数据库连接
    private static final String url="jdbc:mysql://localhost:3306/hotelmanagementsystem";
    private static final String user="root";
    private static final String password="fushiyuchen";

    public static Connection getConnection(){
        Connection connection;
        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
