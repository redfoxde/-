package Swing;

import Data.Room;
import DAO.room_manage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InquireManagePanel extends JPanel {
    room_manage room_manages=new room_manage();
    public InquireManagePanel() {
        setLayout(new BorderLayout());
        JDesktopPane desktopPane = new JDesktopPane();
        //以房号查询
        JInternalFrame internalFrame = new JInternalFrame("房间查询",true,false,true,true);
        internalFrame.setSize(400,200);
        internalFrame.setVisible(true);

        JPanel panel=new JPanel(new GridLayout(2,1));

        panel.add(new JLabel("输入房间id"));
        JTextField room_idField=new JTextField(10);
        panel.add(room_idField);


        JTextArea reportArea = new JTextArea();
        JScrollPane reportAreaScrollPane = new JScrollPane(reportArea);
        internalFrame.add(reportAreaScrollPane, BorderLayout.CENTER);


        JButton inquireAllRoom=new JButton("查询所有房间");
        panel.add(inquireAllRoom);
        inquireAllRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GenerateALLRoom(reportArea);
            }

            private void GenerateALLRoom(JTextArea reportArea) {
                List<Room> rooms= room_manages.getAllRooms();
                StringBuilder report=new StringBuilder("所有房间:\n");

                for(Room room:rooms){
                    report.append(room).append("\n");
                }
                reportArea.setText(report.toString());
            }
        });


        //查询单个房间
        JButton inquireRoom=new JButton("查询单个房间");
        panel.add(inquireRoom);
        inquireRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int room_id=Integer.parseInt(room_idField.getText());
                GenerateRoom(room_id,reportArea);
            }

            private void GenerateRoom(int roomId, JTextArea reportArea) {

                List<Room> rooms= room_manages.getRoomDetail(roomId);
                StringBuilder report=new StringBuilder("房间编号"+roomId+"\n");
                for(Room room:rooms){
                    report.append(room).append("\n");
                }
                reportArea.setText(report.toString());
            }
        });















        internalFrame.add(panel, BorderLayout.SOUTH);
        desktopPane.add(internalFrame, BorderLayout.CENTER);
        desktopPane.add(Box.createRigidArea(new Dimension(100,50)));
        add(desktopPane, BorderLayout.CENTER);




    }

}
