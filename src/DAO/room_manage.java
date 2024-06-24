package DAO;
import Data.Room;
import tool.DataBaseConnection;
import tool.ConvertTable;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

    //按房号查询
    public static JTable getRoomDetail(Map<String,String> customColumnNames,int room_number) {
        String sql = "SELECT * FROM rooms WHERE room_number = ?";

        return ConvertTable.getJTableOnly(customColumnNames,sql,room_number);
    }

    //按状态查询房间
    public static JTable getRooms(Map<String,String> customColumnNames,String STATUS){
        String sql = "SELECT * FROM rooms WHERE STATUS = ?";

        return ConvertTable.getJTableOnly(customColumnNames,sql,STATUS);
    }
    //按价格查询
    public static JTable getRoomInPrice(Map<String,String> customColumnNames,double room_price) {
        String sql = "SELECT * FROM rooms WHERE room_price <= ?";

        return ConvertTable.getJTableOnly(customColumnNames,sql,room_price);
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
    public boolean deleteRoom(int room_number) {
        String checkBookingsQuery = "SELECT COUNT(*) FROM booking WHERE room_number = ?";
        String deleteRoomQuery = "DELETE FROM rooms WHERE room_number = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkBookingsQuery);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteRoomQuery)) {

            // Check if there are bookings for the room
            checkStatement.setInt(1, room_number);
            try (ResultSet rs = checkStatement.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);

                // If there are bookings, return false
                if (count > 0) {
                    return false;
                }
            }

            // Delete the room
            deleteStatement.setInt(1, room_number);
            int affectedRows = deleteStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

