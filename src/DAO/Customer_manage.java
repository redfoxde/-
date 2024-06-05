package DAO;

import Data.Customer;

import tool.DataBaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer_manage{

    //添加客人
    public void addcustomer(Customer customer){
        String sql= "INSERT INTO guests(guest_id,guest_name,guest_gender,guest_contact,id_number,check_in_date,expected_stay)VALUES(?,?,?,?,?,?,?) ";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,customer.getGuest_id());
            statement.setString(2,customer.getGuest_name());
            statement.setString(3,customer.getGuest_gender());
            statement.setString(4,customer.getGuest_contact());
            statement.setString(5,customer.getId_number());
            statement.setDate(6, Date.valueOf(customer.getCheck_in_date()));
            statement.setInt(7,customer.getExpected_stay());
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
                String guest_name=resultSet.getString("guest_name");
                String guest_gender=resultSet.getString("guest_gender");
                String guest_contact=resultSet.getString("guest_contact");
                String id_number= resultSet.getString("id_number");
                Date check_in_date= resultSet.getDate("check_in_date");
                int expected_stay=resultSet.getInt("expected_stay");

                System.out.println("客户编号："+guest_id);
                System.out.println("客户姓名："+guest_name);
                System.out.println("客户性别："+guest_gender);
                System.out.println("联系电话:"+guest_contact);
                System.out.println("身份证号："+id_number);
                System.out.println("入住日期："+check_in_date);
                System.out.println("停留日期:"+expected_stay);

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
                String guest_name=resultSet.getString("guest_name");
                String guest_gender=resultSet.getString("guest_gender");
                String guest_contact=resultSet.getString("guest_contact");
                String id_number= resultSet.getString("id_number");
                Date check_in_date= resultSet.getDate("check_in_date");
                int expected_stay=resultSet.getInt("expected_stay");

                System.out.println("客户姓名："+guest_name);
                System.out.println("客户性别："+guest_gender);
                System.out.println("联系电话:"+guest_contact);
                System.out.println("身份证号："+id_number);
                System.out.println("入住日期："+check_in_date);
                System.out.println("停留日期:"+expected_stay);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //更新客户信息
    public void updateCustomer(Customer customer){
        String sql="UPDATE  guests SET guest_name=?,guest_gender=?,guest_contact=?,id_number=?,check_in_date=?,expected_stay=? WHERE guest_id=?";
        try (
                //获取数据库链接
                Connection connection = DataBaseConnection.getConnection();
                //预编译语句
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(7,customer.getGuest_id());
            statement.setString(1,customer.getGuest_name());
            statement.setString(2,customer.getGuest_gender());
            statement.setString(3,customer.getGuest_contact());
            statement.setString(4,customer.getId_number());
            statement.setDate(5, Date.valueOf(customer.getCheck_in_date()));
            statement.setInt(6,customer.getExpected_stay());
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