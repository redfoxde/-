package DAO;

import Data.Customer;

import tool.DataBaseConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static tool.ConvertTable.getJTable;

public class Customer_manage{

    //添加客人
    public boolean addcustomer(Customer customer){
        String sql= "INSERT INTO guests(guest_name,guest_gender,guest_contact,id_number,check_in_date,expected_stay)VALUES(?,?,?,?,?,?) ";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){
//            statement.setInt(1,customer.getGuest_id());
            statement.setString(1,customer.getGuest_name());
            statement.setString(2,customer.getGuest_gender());
            statement.setString(3,customer.getGuest_contact());
            statement.setString(4,customer.getId_number());
            statement.setDate(5, Date.valueOf(customer.getCheck_in_date()));
            statement.setInt(6,customer.getExpected_stay());
            int rowAffected=statement.executeUpdate();
            return rowAffected>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //查看所有客人
    public static JTable getAllcustomer(Map<String,String> customColumnNames){
        String sql= "SELECT * FROM guests";

        return getJTable(customColumnNames,sql);
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
               Customer customer;
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