import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking_manage {
    //增
    public void addbookings(Booking booking){
        String sql = "insert into bookings(booking_id,room_id,guest_id,check_in_date,expected_check_out_date,actual_check_out_date) values(?,?,?,?,?,?)";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, booking.getBooking_id());
            statement.setInt(2,booking.getRoom_id());
            statement.setInt(3,booking.getGuest_id());
            statement.setDate(4,booking.getCheck_in_date());
            statement.setDate(5,booking.getExpected_check_out_date());
            statement.setDate(6,booking.getActual_check_out_date());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查
    public List<Booking> getbooking(){
        List<Booking> bookings = new ArrayList<>();
        String sql = "select * from bookings";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while(resultSet.next()){
                int booking_id =resultSet.getInt("booking_id");
                int room_id =resultSet.getInt("room_id");
                int guest_id =resultSet.getInt("guest_id");
                Date check_in_date = resultSet.getDate("check_in_date");
                Date expected_check_out_date= resultSet.getDate("expected_check_out_date");
                Date actual_check_out_date= resultSet.getDate("actual_check_out_date");

                System.out.println("预定编号："+booking_id);
                System.out.println("房间编号："+room_id);
                System.out.println("客户编号："+guest_id);
                System.out.println("入住时间："+check_in_date);
                System.out.println("预计退房时间："+expected_check_out_date);
                System.out.println("实际退房时间："+actual_check_out_date);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return bookings;
    }

    //查单个
    public void getbookingDetail(int booking_id){
        String sql = "select * from bookings where booking_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while(resultSet.next()){
                int room_id =resultSet.getInt("room_id");
                int guest_id =resultSet.getInt("guest_id");
                Date check_in_date = resultSet.getDate("check_in_date");
                Date expected_check_out_date= resultSet.getDate("expected_check_out_date");
                Date actual_check_out_date= resultSet.getDate("actual_check_out_date");

                System.out.println("房间编号："+room_id);
                System.out.println("客户编号："+guest_id);
                System.out.println("入住时间："+check_in_date);
                System.out.println("预计退房时间："+expected_check_out_date);
                System.out.println("实际退房时间："+actual_check_out_date);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //更新
    public void updatebooking(Booking booking){
        String sql="UPDATE bookings SET booking_id=?,room_id=?,guest_id=?,check_in_date=?,expected_check_out_date=? WHERE booking_id=?";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, booking.getBooking_id());
            statement.setInt(2,booking.getRoom_id());
            statement.setInt(3,booking.getGuest_id());
            statement.setDate(4,booking.getCheck_in_date());
            statement.setDate(5,booking.getExpected_check_out_date());
            statement.setDate(6,booking.getActual_check_out_date());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //删除
    public void deletebooking(int booking_id){
        String sql = "delete from bookings where booking_id=?";
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
