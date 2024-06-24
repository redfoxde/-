package tool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Map;

public class ConvertTable {

    public static JTable getJTable(Map<String, String> customColumnNames, String sql) {
        try(Connection connection= DataBaseConnection.getConnection();
            PreparedStatement statement= connection.prepareStatement(sql)){
            ResultSet resultSet=statement.executeQuery();
            //获取数据
            ResultSetMetaData metaData=resultSet.getMetaData();
            int columnCount= metaData.getColumnCount();
            //创建表模型并填充列名和数据
            DefaultTableModel tableModel=new DefaultTableModel();
            for(int i=1;i<=columnCount;i++){
                String columnName=metaData.getColumnName(i);
                //自定义名称和数据库列名映射
                String customName=customColumnNames.getOrDefault(columnName,columnName);
                tableModel.addColumn(customName);
            }

            while (resultSet.next()){
                Object[] row=new Object[columnCount];
                for(int i=1;i<=columnCount;i++){
                    row[i-1]=resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
            //
            return new JTable(tableModel);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
