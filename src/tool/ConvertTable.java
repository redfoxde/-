package tool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Map;

public class ConvertTable {

    public static JTable getJTable(Map<String, String> customColumnNames, String sql) {
        try(Connection connection= DataBaseConnection.getConnection();
            PreparedStatement statement= connection.prepareStatement(sql)){
            return getjTable(customColumnNames, statement);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JTable getJTableOnly(Map<String, String> customColumnNames, String sql, Object... params) {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // 设置SQL参数
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            return getjTable(customColumnNames, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JTable getjTable(Map<String, String> customColumnNames, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        DefaultTableModel tableModel = new DefaultTableModel();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String customName = customColumnNames.getOrDefault(columnName, columnName);
            tableModel.addColumn(customName);
        }

        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = resultSet.getObject(i);
            }
            tableModel.addRow(row);
        }
        return new JTable(tableModel);
    }

}
