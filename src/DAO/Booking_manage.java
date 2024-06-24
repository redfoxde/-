package DAO;
import tool.DataBaseConnection;
import Data.Booking;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import static tool.ConvertTable.getJTable;
public class Booking_manage {
    //增
    public boolean addbookings(Booking booking){
        String sql = "insert into booking(guest_name,room_number,check_in_date,check_out_date) values(?,?,?,?)";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
//            statement.setInt(1, booking.getBooking_id());
            statement.setString(1,booking.getGuest_name());
            statement.setInt(2,booking.getRoom_number());
            statement.setDate(3, java.sql.Date.valueOf(booking.getCheck_in_date()));
            statement.setDate(4, java.sql.Date.valueOf(booking.getCheck_out_date()));

            statement.executeUpdate();
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
    public void updatebooking(Booking booking){
        String sql="UPDATE booking SET guest_name=?,room_number=?,check_in_date=?,check_out_date=? WHERE booking_id=?";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(5, booking.getBooking_id());
            statement.setString(1,booking.getGuest_name());
            statement.setInt(2,booking.getRoom_number());
            statement.setDate(3, java.sql.Date.valueOf(booking.getCheck_in_date()));
            statement.setDate(4, java.sql.Date.valueOf(booking.getCheck_out_date()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //删除
    public void deletebooking(int booking_id){
        String sql = "delete from booking where booking_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, booking_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    }

