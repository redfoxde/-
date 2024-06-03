import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class room_manager_manage {
    //增加
    public void addroom_manager(room_manager r){
        String sql="insert into room_managers(manager_id,name,contact_info) values(?,?,?)";
        try(
                //连接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
                statement.setInt(1,r.getManager_id());
                statement.setString(2,r.getName());
                statement.setString(3,r.getContact_info());
                statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //查询所有
    public List<room_manager> getroom_manager() {
        String sql = "select * from room_managers";
        List<room_manager> list = new ArrayList<room_manager>();
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {

                int manager_id = resultSet.getInt("manager_id");
                String name = resultSet.getString("name");
                String contact_info = resultSet.getString("contact_info");


                System.out.println("管理人员编号：" + manager_id);
                System.out.println("管理人员姓名：" + name);
                System.out.println("管理人员联系方式：" + contact_info);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询单个人员
    public void getmanager_detail(int manager_id) {
        String sql = "select * from room_managers where manager_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            statement.setInt(1, manager_id);
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String contact_info = resultSet.getString("contact_info");

                System.out.println("管理人员姓名：" + name);
                System.out.println("管理人员联系方式：" + contact_info);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新、修改
    public void updateroom_manager(room_manager r) {
        String sql = "update room_managers set name=?,contact_info=? where manager_id=?";
        try(
                //连接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setString(1,r.getName());
            statement.setString(2,r.getContact_info());
            statement.setInt(3,r.getManager_id());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //删除
    public void deleteroom_manager(int manager_id) {
        String sql = "delete from room_managers where manager_id=?";
        try(
                //连接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1,manager_id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
