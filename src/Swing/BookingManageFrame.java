package Swing;

import DAO.Booking_manage;
import Data.Booking;

import javax.swing.*;
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

        JPanel panel=new JPanel(new FlowLayout());
        JButton addButton = new JButton("添加预定信息");
        JButton showAllMessage=new JButton("查询所有信息");
        panel.add(addButton);
        panel.add(showAllMessage);
        add(panel,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog((Frame) null,"添加预定信息");
                dialog.setModal(true);
                dialog.setSize(300,200);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


                JPanel panel1 = new JPanel(new GridLayout(5,2));
//                panel1.add(new JLabel("预定编号"));
//                JTextField Booking_idField = new JTextField();
//                panel1.add(Booking_idField);

                panel1.add(new JLabel("客户姓名"));
                JTextField guest_nameField = new JTextField(10);
                panel1.add(guest_nameField);

                panel1.add(new JLabel("房间号"));
                JTextField room_numberField = new JTextField(10);
                panel1.add(room_numberField);

                panel1.add(new JLabel("入住时间"));
                JTextField check_in_DateField = new JTextField(10);
                panel1.add(check_in_DateField);

                panel1.add(new JLabel("离开时间"));
                JTextField check_out_DateField = new JTextField(10);
                panel1.add(check_out_DateField);

                JButton saveButton=new JButton("保存");
                JButton cancelButton=new JButton("取消");
                panel1.add(saveButton);
                panel1.add(cancelButton);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        int booking_id=Integer.parseInt(Booking_idField.getText());
                        String guest_name=guest_nameField.getText();
                        int room_number=Integer.parseInt(room_numberField.getText());
                        LocalDate check_in_Date=LocalDate.parse(check_in_DateField.getText());
                        LocalDate check_out_Date=LocalDate.parse(check_out_DateField.getText());
                        Booking booking=new Booking(guest_name,room_number,check_in_Date,check_out_Date);

                        if(booking_manage.addbookings(booking)){
                            JOptionPane.showMessageDialog(null,guest_name+"添加成功！");
                        }else{
                            JOptionPane.showMessageDialog(null,"添加失败，请重试！");
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

                Map<String,String> customColumnNames=new HashMap<>();
                customColumnNames.put("booking_id","预定编号");
                customColumnNames.put("guest_name","客人姓名");
                customColumnNames.put("room_number","房间号");
                customColumnNames.put("check_in_date","入住时间");
                customColumnNames.put("check_out_date","离开时间");

                JTable table=Booking_manage.getBookingsTable(customColumnNames);
                if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    dialog.getContentPane().add(scrollPane);
                    dialog.setVisible(true);
                }

            }
        });

    }
}
