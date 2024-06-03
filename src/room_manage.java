import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class room_manage {
    //添加房间
    public void addRoom(Room room) {
        String query = "INSERT INTO rooms(room_id,room_number,room_type,price_per_night,discount_info,STATUS)VALUES(?,?,?,?,?,?)";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, room.getRoom_id());
            statement.setString(2, room.getRoom_number());
            statement.setString(3, room.getRoom_type());
            statement.setDouble(4, room.getPrice_per_night());
            statement.setDouble(5, room.getDiscount());
            statement.setString(6, room.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询所有房间
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<Room>();
        String query = "SELECT * FROM rooms";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {

                int room_id = resultSet.getInt("room_id");
                String room_number = resultSet.getString("room_number");
                String room_type = resultSet.getString("room_type");
                double price_per_night = resultSet.getDouble("price_per_night");
                double discount = resultSet.getDouble("discount_info");
                String status = resultSet.getString("STATUS");


                System.out.println("房间编号：" + room_id);
                System.out.println("房间号：" + room_number);
                System.out.println("房间类型：" + room_type);
                System.out.println("价格：" + price_per_night);
                System.out.println("折扣：" + discount);
                System.out.println("状态：" + status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    //展示房间细节
    public void getRoomDetail(int room_id) {
        String query = "SELECT* FROM rooms WHERE room_id=? ";

        try (    //获取数据库连接
                 Connection connection = DataBaseConnection.getConnection();
                 //预编译语句
                 PreparedStatement statement = connection.prepareStatement(query)

        ) {
            statement.setInt(1, room_id);
            //将结果返回
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String room_number = resultSet.getString("room_number");
                String room_type = resultSet.getString("room_type");
                double price_per_night = resultSet.getDouble("price_per_night");
                double discount = resultSet.getDouble("discount_info");
                String status = resultSet.getString("STATUS");

                System.out.println("房间号：" + room_number);
                System.out.println("房间类型：" + room_type);
                System.out.println("价格：" + price_per_night);
                System.out.println("折扣：" + discount);
                System.out.println("状态：" + status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新信息
    public void updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_number=?,room_type=?,price_per_night=?,discount_info=?,STATUS=? WHERE room_id=?";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, room.getRoom_number());
            statement.setString(2, room.getRoom_type());
            statement.setDouble(3, room.getPrice_per_night());
            statement.setDouble(4, room.getDiscount());
            statement.setString(5, room.getStatus());
            statement.setInt(6, room.getRoom_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //删除房间
    public void deleteRoom(int room_id) {
        String sql = "DELETE FROM rooms WHERE room_id=?";
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

//    public static void main(String[] args) {
//        room_manage manager = new room_manage();
//        Room room = new Room(2236104, "104", "标准间", 130, 0.95, "空闲");

        // 添加房间
        //manager.addRoom(room);


        // 更新房间信息
        //room = new Room(2236104,"105","标准间",130,0.95,"已预定");
        //manager.updateRoom(room);

        //查询所有房间
        //manager.getAllRooms();

        //查询房间细节
         //manager.getRoomDetail(2236104);

        // 删除房间
        //manager.deleteRoom(2236103);
//
//
    //}
//}
//}

