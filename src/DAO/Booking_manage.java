package DAO;
import com.mysql.cj.jdbc.CallableStatementWrapper;
import tool.DataBaseConnection;
import Data.Booking;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static javax.management.remote.JMXConnectorFactory.connect;
import static tool.ConvertTable.getJTable;
public class Booking_manage {
    //增
    public boolean addBookingForGuest(int room_number, String guest_name) {
        // SQL 查询：获取特定客户的信息
        String sql = "SELECT g.guest_name, g.check_in_date, " +
                "DATE_ADD(g.check_in_date, INTERVAL g.expected_stay DAY) AS check_out_date " +
                "FROM guests g " +
                "WHERE g.guest_name = ?";

        // SQL 插入：插入预订记录到预订表
        String addSql = "INSERT INTO booking (guest_name, room_number, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // 设置查询参数
            statement.setString(1, guest_name);

            try (PreparedStatement preparedStatement = connection.prepareStatement(addSql)) {
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    LocalDate check_in_date = resultSet.getDate("check_in_date").toLocalDate();
                    LocalDate check_out_date = resultSet.getDate("check_out_date").toLocalDate();

                    // 设置预订记录的参数
                    preparedStatement.setString(1, guest_name);
                    preparedStatement.setInt(2, room_number);
                    preparedStatement.setDate(3, java.sql.Date.valueOf(check_in_date));
                    preparedStatement.setDate(4, java.sql.Date.valueOf(check_out_date));
                    preparedStatement.executeUpdate();

                    return true; // 插入成功，返回 true
                } else {
                    return false; // 没有找到指定客户，返回 false
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false; // 插入过程中发生异常，返回 false
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // 查询过程中发生异常，返回 false
        }
    }


    //查
    public static JTable getBookingsTable(Map<String,String> customColumnNames){
        String sql = "select * from booking";

        return getJTable(customColumnNames,sql);
    }

        //查单个
    public void getbookingDetail(int booking_id){
        String sql = "select * from booking where booking_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            statement.setInt(1, booking_id);
            while(resultSet.next()){
                String guest_name =resultSet.getString("guest_name");
                int room_number =resultSet.getInt("room_number");
                Date check_in_date = resultSet.getDate("check_in_date");
                Date check_out_date= resultSet.getDate("check_out_date");

                System.out.println("客户姓名："+guest_name);
                System.out.println("房间号："+room_number);
                System.out.println("入住时间："+check_in_date);
                System.out.println("退房时间："+check_out_date);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //更新
    public void updatebooking(Object booking_id,String columnNames,Object data){
        Map<String, Object> originalNames=new HashMap<>();
        originalNames.put("预定编号","booking_id");
        originalNames.put("客人姓名","guest_name");
        originalNames.put("房间号","room_number");
        originalNames.put("入住时间","check_in_date");
        originalNames.put("离开时间","check_out_date");

        String originNames=originalNames.get(columnNames).toString();
        String sql="UPDATE booking SET "+ originNames +" =? WHERE booking_id=?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)) {

            statement.setObject(1,data);
            statement.setObject(2,booking_id);
            statement.executeUpdate();
            }catch (SQLException e){
            e.printStackTrace();
        }
    }



    //删除
    public boolean deletebooking(int booking_id){
        String sql = "delete from booking where booking_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, booking_id);
            int rowAffected=statement.executeUpdate();
            return rowAffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    //报表生成
    public static JTable getBookingTable(Map<String, String> customColumnNames, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT booking_id, guest_name, room_number, check_in_date, check_out_date " +
                "FROM booking " +
                "WHERE check_in_date BETWEEN ? AND ?";

        DefaultTableModel model = new DefaultTableModel();
        customColumnNames.forEach((key, value) -> model.addColumn(value));

        try (Connection conn =DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, startDate.toString());
            pstmt.setString(2, endDate.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[customColumnNames.size()];
                int i = 0;
                for (String column : customColumnNames.keySet()) {
                    row[i++] = rs.getObject(column);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }

    public static JTable getBookingTable(Map<String, String> customColumnNames, LocalDate date) {
        return getBookingTable(customColumnNames, date, date);
    }
    
}

