package DAO;

import Data.Customer;

import tool.DataBaseConnection;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static tool.ConvertTable.getJTable;
import static tool.ConvertTable.getJTableOnly;

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
    public JTable getCustomerDetail(Map<String,String> customColumnNames,String customer_name){
        String sql="select * from guests where guest_name=?";

        return getJTableOnly(customColumnNames,sql,customer_name);
    }

    //更新客户信息
    public void updateCustomer(Object customer_id, String columnName, Object data){

        Map<String,String> originalColumnNames = new HashMap<>();
        originalColumnNames.put("客人编号","guest_id");
        originalColumnNames.put("客人姓名","guest_name");
        originalColumnNames.put("客人性别","guest_gender");
        originalColumnNames.put("联系方式","guest_contact");
        originalColumnNames.put("身份证号码","id_number");
        originalColumnNames.put("入住时间","check_in_date");
        originalColumnNames.put("预计入住天数","expected_stay");

        String original=originalColumnNames.get(columnName);
        String sql="UPDATE guests SET " + original + " = ? WHERE guest_id = ?";

        try(Connection connection=DataBaseConnection.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql)){

            statement.setObject(1,data);
            statement.setObject(2,customer_id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //删除客户信息
    public boolean deleteCustomer(String guest_name){
        String sql="delete from guests where guest_name=?";
        try (
                Connection connection = DataBaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1,guest_name);
            int rowAffected=statement.executeUpdate();
            return rowAffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}