import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer_manage{

    //添加客人
    public void addcustomer(Customer customer){
        String sql= "INSERT INTO guests(guest_id,name,gender,id_number,phone_number)VALUES(?,?,?,?,?) ";

        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,customer.getGuest_id());
            statement.setString(2,customer.getName());
            statement.setString(3, customer.getGender());
            statement.setString(4, customer.getId_number());
            statement.setString(5, customer.getPhone_number());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //查看所有客人
    public List<Customer> getCustomer(){
        String sql="select * from guests";
        List<Customer> customerList=new ArrayList<Customer>();
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            while(resultSet.next()){
                int guest_id=resultSet.getInt("guest_id");
                String name=resultSet.getString("name");
                String gender=resultSet.getString("gender");
                String id_number= resultSet.getString("id_number");
                String phone_number= resultSet.getString("phone_number");

                System.out.println("客户编号："+guest_id);
                System.out.println("客户姓名："+name);
                System.out.println("客户性别："+gender);
                System.out.println("身份证号码："+id_number);
                System.out.println("电话号码："+phone_number);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return customerList;
    }

    //查看客人信息
    public void getCustomerDetail(int guest_id){
        String sql="select * from guests where guest_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                //执行并返回结果
                ResultSet resultSet = statement.executeQuery()
        ) {
            statement.setInt(1,guest_id);
            while(resultSet.next()){
                String name=resultSet.getString("name");
                String gender=resultSet.getString("gender");
                String id_number= resultSet.getString("id_number");
                String phone_number= resultSet.getString("phone_number");


                System.out.println("客户姓名："+name);
                System.out.println("客户性别："+gender);
                System.out.println("身份证号码："+id_number);
                System.out.println("电话号码："+phone_number);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //更新客户信息
    public void updateCustomer(Customer customer){
        String sql="UPDATE  guests SET guest_id=?,name=?,gender=?,id_number=?,phone_number=? WHERE guest_id=?";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, customer.getGuest_id());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getGender());
            statement.setString(4, customer.getId_number());
            statement.setString(5, customer.getPhone_number());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除客户信息
    public void deleteCustomer(int guest_id){
        String sql="delete from guests where guest_id=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, guest_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}