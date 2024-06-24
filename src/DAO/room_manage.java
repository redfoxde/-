package DAO;
import Data.Room;
import tool.DataBaseConnection;
import tool.ConvertTable;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class room_manage {
    //添加房间
    public boolean addRoom(Room room) {
        String query = "INSERT INTO rooms(room_number,room_type,room_price,room_discount,STATUS,room_manager,room_contact)VALUES(?,?,?,?,?,?,?)";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
//            statement.setInt(1, room.getRoom_id());
            statement.setInt(1, room.getRoom_number());
            statement.setString(2, room.getRoom_type());
            statement.setDouble(3, room.getRoom_price());
            statement.setString(4, room.getRoom_discount());
            statement.setString(5, room.getStatus());
            statement.setString(6, room.getRoom_manager());
            statement.setString(7, room.getRoom_contact());

            statement.executeUpdate();
            int AffectedRows = statement.executeUpdate();
            return AffectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查询所有房间
    public static JTable getAllRooms(Map<String,String> customColumnNames) {
        String sql = "SELECT * FROM rooms";
        return ConvertTable.getJTable(customColumnNames,sql);
    }

    //展示房间细节
    public List<Room> getRoomDetail(int room_id) {
        List<Room> rooms = new ArrayList<>();
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
                Room room=new Room();
                room.setRoom_id(resultSet.getInt("room_id"));
                room.setRoom_number(resultSet.getInt("room_number"));
                room.setRoom_type(resultSet.getString("room_type"));
                room.setRoom_price(resultSet.getDouble("room_price"));
                room.setRoom_discount(resultSet.getString("room_discount"));
                room.setStatus(resultSet.getString("status"));
                room.setRoom_manager(resultSet.getString("room_manager"));
                room.setRoom_contact(resultSet.getString("room_contact"));
                rooms.add(room);
//                String room_number = resultSet.getString("room_number");
//                String room_type = resultSet.getString("room_type");
//                String room_price = resultSet.getString("room_price");
//                double room_discount = resultSet.getDouble("room_discount");
//                String status = resultSet.getString("STATUS");
//                String room_manager = resultSet.getString("room_manager");
//                String room_contact = resultSet.getString("room_contact");
//
//                System.out.println("房间编号：" + room_id);
//                System.out.println("房间号：" + room_number);
//                System.out.println("房间类型：" + room_type);
//                System.out.println("价格：" + room_price);
//                System.out.println("折扣：" + room_discount);
//                System.out.println("状态：" + status);
//                System.out.println("负责人："+room_manager);
//                System.out.println("联系电话："+room_contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    //更新信息
    public void updateRoom(Object room_id, String columnName, Object data) {

        Map<String,String> originalNames=new HashMap<>();
        originalNames.put("房间编号","room_id");
        originalNames.put("房间号","room_number");
        originalNames.put("房间类型","room_type");
        originalNames.put("价格","room_price");
        originalNames.put("折扣","room_discount");
        originalNames.put("状态","STATUS");
        originalNames.put("房间负责人","room_manager");
        originalNames.put("联系电话","room_contact");

        String originalName=originalNames.get(columnName);
        String sql ="UPDATE rooms SET " + originalName + " = ? WHERE room_id = ?";

        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setObject(1,data);
            statement.setObject(2, room_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //删除房间
    public boolean deleteRoom(int room_id) {
        String sql = "DELETE FROM rooms WHERE room_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, room_id);
            statement.executeUpdate();
            int AffectedRows = statement.executeUpdate();
            return AffectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

//    public static void main(String[] args) {
//        DAO.room_manage manager = new DAO.room_manage();
//        Data.Room room = new Data.Room(2236104, "104", "标准间", 130, 0.95, "空闲");

        // 添加房间
        //manager.addRoom(room);


        // 更新房间信息
        //room = new Data.Room(2236104,"105","标准间",130,0.95,"已预定");
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

