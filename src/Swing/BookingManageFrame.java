package Swing;

import DAO.Booking_manage;
import Data.Booking;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BookingManageFrame extends JFrame {
    private Booking_manage booking_manage=new Booking_manage();
    public BookingManageFrame() {
        setTitle("酒店管理系统-预定管理");
        setLayout(new FlowLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        Map<String,String> customColumnNames=new HashMap<>();
        customColumnNames.put("booking_id","预定编号");
        customColumnNames.put("guest_name","客人姓名");
        customColumnNames.put("room_number","房间号");
        customColumnNames.put("check_in_date","入住时间");
        customColumnNames.put("check_out_date","离开时间");

        JPanel panel=new JPanel(new FlowLayout());
        JButton addButton = new JButton("添加预定信息");
        JButton showAllMessage=new JButton("查询所有信息");
        JButton deleteButton=new JButton("删除预定信息");
        JButton updateButton=new JButton("修改预定信息");
        panel.add(addButton);
        panel.add(showAllMessage);
        panel.add(deleteButton);
        panel.add(updateButton);
        add(panel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog((Frame) null,"添加预定信息");
                dialog.setModal(true);
                dialog.setSize(300,200);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


                JPanel panel1 = new JPanel(new GridLayout(5,2));

                panel1.add(new JLabel("客户姓名"));
                JTextField guest_nameField = new JTextField(10);
                panel1.add(guest_nameField);

                panel1.add(new JLabel("房间号"));
                JTextField room_numberField = new JTextField(10);
                panel1.add(room_numberField);


                JButton saveButton=new JButton("保存");
                JButton cancelButton=new JButton("取消");
                panel1.add(saveButton);
                panel1.add(cancelButton);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String guest_name=guest_nameField.getText();
                        int room_number=Integer.parseInt(room_numberField.getText());

                        if(booking_manage.addBookingForGuest(room_number,guest_name)){
                            JOptionPane.showMessageDialog(null,"添加成功");
                        }else{
                            JOptionPane.showMessageDialog(null,"添加失败,请重试");
                        }
                    }
                });
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.getContentPane().add(panel1);
                dialog.setVisible(true);
            }
        });

        showAllMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog((Frame) null,"添加预定信息");
                dialog.setModal(true);
                dialog.setSize(300,200);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JTable table=Booking_manage.getBookingsTable(customColumnNames);
                if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    dialog.getContentPane().add(scrollPane);
                    dialog.setVisible(true);
                }

            }
        });

        //删除预定信息
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog((Frame) null,"删除预定信息");
                dialog.setModal(true);
                dialog.setSize(300,200);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JPanel panel1 = new JPanel(new GridLayout(5,2));
                panel1.add(new JLabel("预定编号"));
                JTextField guest_nameField = new JTextField(10);
                panel1.add(guest_nameField);

                JButton confirmButton=new JButton("确认");
                panel1.add(confirmButton);
                JButton cancelButton=new JButton("取消");
                panel1.add(cancelButton);

                confirmButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int booking_id=Integer.parseInt(guest_nameField.getText());

                        if(booking_manage.deletebooking(booking_id)){
                            JOptionPane.showMessageDialog(null,booking_id+"已删除！");
                        }else {
                            JOptionPane.showMessageDialog(null,"删除失败，请重试!");
                        }
                    }
                });
                cancelButton.addActionListener(e1 -> {dialog.dispose();});
                dialog.getContentPane().add(panel1);
                dialog.setVisible(true);
            }
        });
        //修改
        updateButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"修改预定信息");
            dialog.setModal(true);
            dialog.setSize(300,200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JTable table= Booking_manage.getBookingsTable(customColumnNames);
            table.setEnabled(true);
            table.getModel().addTableModelListener(e1 -> {
                if(e1.getType()== TableModelEvent.UPDATE){
                    int row = e1.getFirstRow();
                    int column = e1.getColumn();
                    TableModel model = (TableModel) e1.getSource();
                    Object data = model.getValueAt(row, column);
                    String columnName = model.getColumnName(column);

                    // 获取预定ID
                    Object booking_id = model.getValueAt(row, 0);

                    // 更新数据库
                    booking_manage.updatebooking(booking_id,columnName,data);
                }
            });
            JScrollPane scrollPane=new JScrollPane(table);
            dialog.getContentPane().add(scrollPane,BorderLayout.CENTER);
            dialog.setVisible(true);
        });

    }
}
