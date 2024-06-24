package Swing;

import DAO.room_manage;
import Data.Room;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RoomManagementFrame extends JFrame {
    private room_manage roomManage=new room_manage();

    public RoomManagementFrame() {
        setTitle("酒店管理系统-房间管理");
        setLayout(new FlowLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        Map<String,String> customColumnNames=new HashMap<>();
        customColumnNames.put("room_id","房间编号");
        customColumnNames.put("room_number","房间号");
        customColumnNames.put("room_type","房间类型");
        customColumnNames.put("room_price","价格");
        customColumnNames.put("room_discount","折扣");
        customColumnNames.put("STATUS","状态");
        customColumnNames.put("room_manager","房间负责人");
        customColumnNames.put("room_contact","联系电话");

        JPanel panel=new JPanel(new GridLayout(2,1));
        JButton addButton1=new JButton("添加房间");
        panel.add(addButton1);
        JButton deleteButton= new JButton("删除房间");
        panel.add(deleteButton);
        JButton displayButton=new JButton("展示所有房间");
        panel.add(displayButton);
        JButton updateButton=new JButton("修改房间信息");
        panel.add(updateButton);
        JButton selectRoom=new JButton("按房号查询");
        panel.add(selectRoom);
        JButton statusButton=new JButton("按状态查询");
        panel.add(statusButton);
        JButton priceButton=new JButton("按价格查询");
        panel.add(priceButton);
        add(panel,BorderLayout.CENTER);

        addButton1.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"添加房间");
            dialog.setModal(true);
            dialog.setSize(300,400);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel formPanel = new JPanel(new GridLayout(10, 2));

            formPanel.add(new JLabel("房间号:"));
            JTextField roomnumberField = new JTextField();
            formPanel.add(roomnumberField);

            formPanel.add(new JLabel("房间类型:"));
            JTextField roomtypeField = new JTextField();
            formPanel.add(roomtypeField );

            formPanel.add(new JLabel("价格:"));
            JTextField  priceField= new JTextField();
            formPanel.add(priceField);

            formPanel.add(new JLabel("折扣:"));
            JTextField room_discountField = new JTextField();
            formPanel.add(room_discountField);

            formPanel.add(new JLabel("状态:"));
            JTextField STAUTSField = new JTextField();
            formPanel.add(STAUTSField);

            formPanel.add(new JLabel("负责人:"));
            JTextField room_managerField = new JTextField();
            formPanel.add(room_managerField);

            formPanel.add(new JLabel("联系方式:"));
            JTextField room_contactField = new JTextField();
            formPanel.add(room_contactField);

            JButton saveButton=new JButton("保存");
            formPanel.add(saveButton);
            JButton cancelButton=new JButton("取消");
            formPanel.add(cancelButton);

            saveButton.addActionListener(e1 -> {
//              int room_id= Integer.parseInt(roomIDField.getText());
                int room_number= Integer.parseInt(roomnumberField.getText());
                String room_type=roomtypeField.getText();
                double room_price= Double.parseDouble(priceField.getText());
                String room_discount=room_discountField.getText();
                String STAUTS=STAUTSField.getText();
                String room_manager=room_managerField.getText();
                String room_contact=room_contactField.getText();
                Room room=new Room(room_number,room_type,room_price,room_discount,STAUTS,room_manager, room_contact);
                if(roomManage.addRoom(room)){
                    JOptionPane.showMessageDialog(null,room_number+"添加成功!");
                }else{
                    JOptionPane.showMessageDialog(null,"添加失败，请重试！");
                }

            });
            cancelButton.addActionListener(e12 -> dialog.dispose());
            dialog.getContentPane().add(formPanel);
            dialog.setVisible(true);

        });

            deleteButton.addActionListener(e -> {
                    JDialog dialog = new JDialog((Frame) null,"删除房间");
                    dialog.setModal(true);
                    dialog.setSize(300,100);
                    dialog.setLocationRelativeTo(null);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                    JPanel panel1 = new JPanel(new GridLayout(2,2));
                    panel1.add(new JLabel("房间号"));
                    JTextField IdField=new JTextField(10);
                    panel1.add(IdField);

                    JButton confirmButton=new JButton("确认");
                    panel1.add(confirmButton);
                    JButton cancelButton=new JButton("取消");
                    panel1.add(cancelButton);

                    confirmButton.addActionListener(e13 -> {
                        int room_number= Integer.parseInt(IdField.getText());
                        if(roomManage.deleteRoom(room_number)){
                            JOptionPane.showMessageDialog(null,room_number+"删除成功！");
                        }else{
                            JOptionPane.showMessageDialog(null,"删除失败，请重试!");
                        }
                    });
                    cancelButton.addActionListener(e14 -> dialog.dispose());
                    dialog.getContentPane().add(panel1);
                    dialog.setVisible(true);

                });

            displayButton.addActionListener(e -> {
                JDialog dialog = new JDialog((Frame) null,"所有房间");
                dialog.setModal(true);
                dialog.setSize(500,300);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JTable table=room_manage.getAllRooms(customColumnNames);
                if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    dialog.getContentPane().add(scrollPane);
                    dialog.setVisible(true);
                }
            });

        updateButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"所有房间");
            dialog.setModal(true);
            dialog.setSize(500,300);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JTable table=room_manage.getAllRooms(customColumnNames);
            table.setEnabled(true);
            table.getModel().addTableModelListener(e15 -> {
                if (e15.getType() == TableModelEvent.UPDATE) {
                    int row = e15.getFirstRow();
                    int column = e15.getColumn();
                    TableModel model = (TableModel) e15.getSource();
                    Object data = model.getValueAt(row, column);
                    String columnName = model.getColumnName(column);

                    // 获取房间ID
                    Object room_id = model.getValueAt(row, 0);

                    // 更新数据库
                    roomManage.updateRoom(room_id,columnName,data);
                }
            });
//            if(table!=null&&table.getRowCount()>0){
            JScrollPane scrollPane=new JScrollPane(table);
            dialog.getContentPane().add(scrollPane,BorderLayout.CENTER);
            dialog.setVisible(true);

//            }
        });

        selectRoom.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"查询单个房间");
            dialog.setModal(true);
            dialog.setSize(300,100);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel1 = new JPanel(new GridLayout(2,2));
            panel1.add(new JLabel("房间号"));
            JTextField numberField=new JTextField(10);
            panel1.add(numberField);

            JButton confirmButton=new JButton("确认");
            panel1.add(confirmButton);
            JButton cancelButton=new JButton("取消");
            panel1.add(cancelButton);
            dialog.getContentPane().add(panel1);

            confirmButton.addActionListener(e12 -> {
                JDialog dialog1 = new JDialog((Frame) null,"所有房间");
                dialog1.setModal(true);
                dialog1.setSize(500,300);
                dialog1.setLocationRelativeTo(null);
                dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                int room_number= Integer.parseInt(numberField.getText());

                JTable table=room_manage.getRoomDetail(customColumnNames,room_number);
                if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    dialog1.getContentPane().add(scrollPane);
                    dialog1.setVisible(true);
                }
            });

            cancelButton.addActionListener(e13 -> dialog.dispose());
            dialog.setVisible(true);
        });
        //按照状态查询
        statusButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"查询单个房间");
            dialog.setModal(true);
            dialog.setSize(300,100);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            //创建一个ComboBOX模型
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("已入住");
            model.addElement("已预定");
            model.addElement("空闲");

            JPanel panel1 = new JPanel(new GridLayout(2,2));
            //创建一个ComboBox并设置模型
            JComboBox<String> comboBox=new JComboBox<>(model);
            panel1.add(comboBox);

            JTextField STATUSField=new JTextField(10);
            panel1.add(STATUSField);

            JButton confirmButton=new JButton("确认");
            panel1.add(confirmButton);
            JButton cancelButton=new JButton("取消");
            panel1.add(cancelButton);

            comboBox.addActionListener(e12 -> {
                String selected= Objects.requireNonNull(comboBox.getSelectedItem()).toString();
                STATUSField.setText(selected);
            });
            dialog.getContentPane().add(panel1);

            confirmButton.addActionListener(e1 -> {
                JDialog dialog1 = new JDialog((Frame) null,"查询房间状态");
                dialog1.setModal(true);
                dialog1.setSize(300,200);
                dialog1.setLocationRelativeTo(null);
                dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                String status=STATUSField.getText();

                JTable table=room_manage.getRooms(customColumnNames,status);

                if(table.getRowCount() == 0){

                    JOptionPane.showMessageDialog(null,"当前状态无房间！");

                } else if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    dialog1.getContentPane().add(scrollPane);
                    dialog1.setVisible(true);
                }
            });
            cancelButton.addActionListener(e13 -> dialog.dispose());
            dialog.setVisible(true);

        });

        priceButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"按价格房间");
            dialog.setModal(true);
            dialog.setSize(300,100);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel1 = new JPanel(new GridLayout(2,2));
            panel1.add(new JLabel("价格"));
            JTextField priceField=new JTextField(10);
            panel1.add(priceField);

            JButton confirmButton=new JButton("确定");
            panel1.add(confirmButton);
            JButton cancelButton=new JButton("取消");
            panel1.add(cancelButton);

            dialog.getContentPane().add(panel1);

            confirmButton.addActionListener(e12 -> {
                JDialog dialog1 = new JDialog((Frame) null,"房间信息");
                dialog1.setModal(true);
                dialog1.setSize(300,200);
                dialog1.setLocationRelativeTo(null);
                dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                double price= Double.parseDouble(priceField.getText());
                JTable table=room_manage.getRoomInPrice(customColumnNames,price);
                if(table.getRowCount() == 0){
                    JOptionPane.showMessageDialog(null,"当前价格没有主子想要的房间哟！");
                }else if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    dialog1.getContentPane().add(scrollPane);
                    dialog1.setVisible(true);
                }
            });
            cancelButton.addActionListener(e13 -> dialog.dispose());
            dialog.setVisible(true);
        });
    }
}
