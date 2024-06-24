package Swing;

import DAO.Customer_manage;
import Data.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GuestMangePanel extends JPanel {
    private Customer_manage customer_manage=new Customer_manage();
    public GuestMangePanel() {
        setLayout(new BorderLayout());
        JDesktopPane desktopPane = new JDesktopPane();
        JInternalFrame internalFrame = new JInternalFrame("添加客户",true,false,true,true);
        internalFrame.setSize(300,400);
        internalFrame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(10,2));
        panel.add(new JLabel("客户编号"));
        JTextField guest_idField=new JTextField();
        panel.add(guest_idField);

        panel.add(new JLabel("客户姓名"));
        JTextField nameField=new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("客户性别"));
        JTextField genderField=new JTextField();
        panel.add(genderField);

        panel.add(new JLabel("手机号"));
        JTextField phone_numberField=new JTextField();
        panel.add(phone_numberField);

        panel.add(new JLabel("身份证号码"));
        JTextField id_numberField=new JTextField();
        panel.add(id_numberField);

       panel.add(new JLabel("入住时间"));
       JTextField check_in_dateField=new JTextField();
       panel.add(check_in_dateField);

       panel.add(new JLabel("预计入住天数"));
       JTextField expected_stayField=new JTextField();
       panel.add(expected_stayField);


        JButton addButton=new JButton("添加客人");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int guest_id=Integer.parseInt(guest_idField.getText());
                String guest_name=nameField.getText();
                String guest_gender=genderField.getText();
                String guest_contact=phone_numberField.getText();
                String id_number=id_numberField.getText();
                LocalDate guest_check_in=LocalDate.parse(check_in_dateField.getText());
                int guest_expected_stay=Integer.parseInt(expected_stayField.getText());


                Customer customer=new Customer(guest_id,guest_name,guest_gender,guest_contact,id_number,guest_check_in,guest_expected_stay);
                customer_manage.addcustomer(customer);

                JOptionPane.showMessageDialog(GuestMangePanel.this,guest_name+"已添加");
            }
        });

        panel.add(addButton);
        internalFrame.add(panel);
        desktopPane.add(internalFrame,BorderLayout.CENTER);
        add(desktopPane, BorderLayout.CENTER);


    }
}
