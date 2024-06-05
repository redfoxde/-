package Swing;

import DAO.Booking_manage;
import Data.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BookingManagePanel extends JPanel {
    private Booking_manage booking_manage=new Booking_manage();
    public BookingManagePanel() {
        setLayout(new BorderLayout());
        JDesktopPane desktopPane = new JDesktopPane();
        JInternalFrame internalFrame = new JInternalFrame("添加预定",true,false,true,true);
        internalFrame.setSize(300,200);
        internalFrame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(6,2));
        panel.add(new JLabel("预定编号"));
        JTextField Booking_idField = new JTextField();
        panel.add(Booking_idField);

        panel.add(new JLabel("客户姓名"));
        JTextField guest_nameField = new JTextField();
        panel.add(guest_nameField);

        panel.add(new JLabel("房间号"));
        JTextField room_numberField = new JTextField();
        panel.add(room_numberField);

        panel.add(new JLabel("入住时间"));
        JTextField check_in_DateField = new JTextField();
        panel.add(check_in_DateField);


        panel.add(new JLabel("离开时间"));
        JTextField check_out_DateField = new JTextField();
        panel.add(check_out_DateField);

        JButton addButton = new JButton("添加预定信息");
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int booking_id=Integer.parseInt(Booking_idField.getText());
                String guest_name=guest_nameField.getText();
                int room_number=Integer.parseInt(room_numberField.getText());
                LocalDate check_in_Date=LocalDate.parse(check_in_DateField.getText());
                LocalDate check_out_Date=LocalDate.parse(check_out_DateField.getText());


                Booking booking=new Booking(booking_id,guest_name,room_number,check_in_Date,check_out_Date);
                booking_manage.addbookings(booking);
                JOptionPane.showMessageDialog(BookingManagePanel.this,booking_id+"已添加");

            }
        });

        internalFrame.add(panel);
        desktopPane.add(internalFrame);
        add(desktopPane, BorderLayout.CENTER);

    }
}
