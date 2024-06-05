import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class user_manage {
    //增加用户信息（实际是实现注册的功能之一）
    public void adduser(user u){
        String sql="insert into admin(id,username,nickname,password) values(?,?,?,?)";
        try(
                //连接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1,u.getId());
            statement.setString(2,u.getUsername());
            statement.setString(3,u.getNickname());
            statement.setString(4,u.getPassword());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //查看用户信息
    public List<user> getuser(){
        List<user> list=new ArrayList<user>();
        String sql="select * from admin";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");


                System.out.println("用户id：" + id);
                System.out.println("用户姓名：" + username);
                System.out.println("用户昵称：" + nickname);
                System.out.println("用户密码：" + password);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查看单个用户信息
    public void getuser(int id){
        String sql="select * from admin where id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            statement.setInt(1,id);
            while (resultSet.next()) {

                String username = resultSet.getString("username");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");

                System.out.println("用户姓名：" + username);
                System.out.println("用户昵称：" + nickname);
                System.out.println("用户密码：" + password);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新
    public void updateuser(user u){
        String sql="update admin set username=?,nickname=?,password=? where id=?";
        try(
                //连接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(4,u.getId());
            statement.setString(1,u.getUsername());
            statement.setString(2,u.getNickname());
            statement.setString(3,u.getPassword());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //删除
    public void deleteuser(int id){
        String sql="delete from admin where id=?";
        try(
                //连接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1,id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
