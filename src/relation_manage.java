import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class relation_manage {
    //增
    public void addrelation(room_manager_relation relation){
        String sql="insert into room_manager_relations(room_id,manager_id) values(?,?)";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, relation.getRoom_id());
            statement.setInt(2, relation.getManage_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查所有
    public List<room_manager_relation> getrelation(){
        List<room_manager_relation> list=new ArrayList<>();
        String sql="select * from room_manager_relations";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {

                int room_id = resultSet.getInt("room_id");
                int manger_id = resultSet.getInt("manager_id");

                System.out.println("房间编号：" + room_id);
                System.out.println("管理人员编号：" + manger_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查单个
    public void getrelation_details(int room_id){
        String sql="select * from room_manager_relations where room_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            statement.setInt(1, room_id);
            while (resultSet.next()) {

               int manager_id = resultSet.getInt("manager_id");
                System.out.println("管理人员编号：" +manager_id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新
    public void updaterelation(room_manager_relation relation){
        String sql="UPDATE room_manager_relations SET manager_id=? WHERE room_id=?";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, relation.getManage_id());
            statement.setInt(2, relation.getRoom_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //删除
    public void deleterelation(int room_id){
        String sql="DELETE FROM room_manager_relations WHERE room_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, room_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
