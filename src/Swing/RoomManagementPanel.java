package Swing;

import DAO.room_manage;
import Data.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementPanel extends JPanel{
    private room_manage roomManage=new room_manage();
    public RoomManagementPanel() {
        setLayout(new BorderLayout());
        JDesktopPane desktopPane = new JDesktopPane();
        JInternalFrame internalFrame = new JInternalFrame("添加房间",true,false,true,true);
        internalFrame.setSize(400,400);
        internalFrame.setVisible(true);

        // Data.Room Form
        JPanel formPanel = new JPanel(new GridLayout(10, 2));
        formPanel.add(new JLabel("房间编号:"));
        JTextField roomIDField = new JTextField();
        formPanel.add(roomIDField);

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

        JButton addButton1=new JButton("添加房间");
        formPanel.add(addButton1);

        JTextArea roomListArea = new JTextArea();
        add(new JScrollPane(roomListArea), BorderLayout.CENTER);

        internalFrame.add(formPanel, BorderLayout.CENTER);

        desktopPane.add(internalFrame, BorderLayout.CENTER);




        addButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int room_id= Integer.parseInt(roomIDField.getText());
                int room_number= Integer.parseInt(roomnumberField.getText());
                String room_type=roomtypeField.getText();
                double room_price= Double.parseDouble(priceField.getText());
                String room_discount=room_discountField.getText();
                String STAUTS=STAUTSField.getText();
                String room_manager=room_managerField.getText();
                String room_contact=room_contactField.getText();


                Room room=new Room(room_id,room_number,room_type,room_price,room_discount,STAUTS,room_manager, room_contact);
                roomManage.addRoom(room);
                JOptionPane.showMessageDialog(RoomManagementPanel.this,room_number+"添加成功！");

            }
        });


        JInternalFrame internalFrame2 = new JInternalFrame("删除房间",true,false,true,true);
        internalFrame2.setSize(300,400);
        internalFrame2.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(10,2));
        panel.add(new JLabel("房间编号"));
        JTextField IdField=new JTextField();
        panel.add(IdField);

        JButton deleteButton= new JButton("删除房间");
        panel.add(deleteButton);

        deleteButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int room_id=Integer.parseInt(IdField.getText());
                        roomManage.deleteRoom(room_id);
                        JOptionPane.showMessageDialog(RoomManagementPanel.this,room_id+"已删除！");
                    }
                }
        );

        internalFrame2.add(panel, BorderLayout.CENTER);
        desktopPane.add(internalFrame2, BorderLayout.CENTER);









        add(desktopPane, BorderLayout.CENTER);
    }
}
