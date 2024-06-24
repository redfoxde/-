package Swing;

import DAO.Customer_manage;
import Data.Customer;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomerManageFrame extends JFrame {
    private Customer_manage customer_manage=new Customer_manage();
    public CustomerManageFrame() {
        setTitle("酒店管理系统-客户管理");
        setLayout(new FlowLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        Map<String,String> customColumnNames=new HashMap<>();
        customColumnNames.put("guest_id","客人编号");
        customColumnNames.put("guest_name","客人姓名");
        customColumnNames.put("guest_gender","客人性别");
        customColumnNames.put("guest_contact","联系方式");
        customColumnNames.put("id_number","身份证号码");
        customColumnNames.put("check_in_date","入住时间");
        customColumnNames.put("expected_stay","预计入住天数");

        JPanel panel=new JPanel(new GridLayout(2,1));
        JButton addButton=new JButton("添加客人");
        panel.add(addButton);
        JButton displayButton=new JButton("查询所有客人");
        panel.add(displayButton);
        JButton showButton=new JButton("查询单个客人");
        panel.add(showButton);
        JButton deleteButton=new JButton("删除客人信息");
        panel.add(deleteButton);
        JButton updateButton=new JButton("修改客人信息");
        panel.add(updateButton);
        add(panel,BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"添加预定信息");
            dialog.setModal(true);
            dialog.setSize(300,300);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel1 = new JPanel(new GridLayout(8,2));
            //创建模型
            SpinnerDateModel model=new SpinnerDateModel();
            JSpinner spinner=new JSpinner(model);

            panel1.add(new JLabel("选择日期"));
            //设置时间格式
            JSpinner.DateEditor dateEditor=new JSpinner.DateEditor(spinner,"yyyy-MM-dd HH:mm:ss");
            spinner.setEditor(dateEditor);
            panel1.add(spinner);

            panel1.add(new JLabel("客户姓名"));
            JTextField nameField=new JTextField();
            panel1.add(nameField);

            panel1.add(new JLabel("客户性别"));
            JTextField genderField=new JTextField();
            panel1.add(genderField);

            panel1.add(new JLabel("手机号"));
            JTextField phone_numberField=new JTextField();
            panel1.add(phone_numberField);

            panel1.add(new JLabel("身份证号码"));
            JTextField id_numberField=new JTextField();
            panel1.add(id_numberField);

            panel1.add(new JLabel("入住时间"));
            JTextField check_in_dateField=new JTextField();
            panel1.add(check_in_dateField);

            panel1.add(new JLabel("预计入住天数"));
            JTextField expected_stayField=new JTextField();
            panel1.add(expected_stayField);

            JButton saveButton=new JButton("保存");
            JButton cancelButton=new JButton("取消");
            panel1.add(saveButton);
            panel1.add(cancelButton);

            spinner.addChangeListener(e1 -> {
                Date date=(Date)spinner.getValue();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                check_in_dateField.setText(sdf.format(date));
            });


            saveButton.addActionListener(e12 -> {
//              int guest_id=Integer.parseInt(guest_idField.getText());

                String guest_name=nameField.getText().trim();
                String guest_gender=genderField.getText().trim();
                String guest_contact=phone_numberField.getText().trim();
                String id_number=id_numberField.getText().trim();
                LocalDate guest_check_in=LocalDate.parse(check_in_dateField.getText());
                int guest_expected_stay=Integer.parseInt(expected_stayField.getText());



                Customer customer=new Customer(guest_name,guest_gender,guest_contact,id_number,guest_check_in,guest_expected_stay);
                if(customer_manage.addcustomer(customer)){
                    JOptionPane.showMessageDialog(null,guest_name+"添加成功！");
                }else{
                    JOptionPane.showMessageDialog(null,"添加失败，请重试！");
                }

            });
            saveButton.addActionListener(e1-> dialog.dispose());
            dialog.getContentPane().add(panel1);
            dialog.setVisible(true);
        });


        displayButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"展示所有信息");
            dialog.setModal(true);
            dialog.setSize(300,200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JTable table=Customer_manage.getAllcustomer(customColumnNames);
            if(table.getRowCount()>0){
                JScrollPane scrollPane=new JScrollPane(table);
                scrollPane.setViewportView(table);
                dialog.getContentPane().add(scrollPane);
                dialog.setVisible(true);
            }

        });

        showButton.addActionListener(e -> {
            JDialog dialog = new JDialog((Frame) null,"查询单个客人");
            dialog.setModal(true);
            dialog.setSize(300,100);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel1 = new JPanel(new GridLayout(2,2));
            panel1.add(new JLabel("输入客人姓名"));
            JTextField nameField=new JTextField(10);
            panel1.add(nameField);

            JButton confirmButton=new JButton("确认");
            JButton cancelButton=new JButton("取消");
            panel1.add(confirmButton);
            panel1.add(cancelButton);
            dialog.getContentPane().add(panel1);

            confirmButton.addActionListener(e1 -> {
                JDialog dialog1 = new JDialog((Frame) null,"查询单个客人");
                dialog1.setModal(true);
                dialog1.setSize(300,200);
                dialog1.setLocationRelativeTo(null);
                dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                String customer_name=nameField.getText().trim();

                JTable table=customer_manage.getCustomerDetail(customColumnNames,customer_name);
                if(table.getRowCount()>0){
                    JScrollPane scrollPane=new JScrollPane(table);
                    scrollPane.setViewportView(table);
                    dialog1.getContentPane().add(scrollPane);
                    dialog1.setVisible(true);
                }
            });
            cancelButton.addActionListener(e1 -> {
                dialog.dispose();
            });
            dialog.setVisible(true);
        });

        //删除客人信息
        deleteButton.addActionListener(e -> {
            JDialog dialog1 = new JDialog((Frame) null,"删除客人");
            dialog1.setModal(true);
            dialog1.setSize(300,100);
            dialog1.setLocationRelativeTo(null);
            dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel1 = new JPanel(new GridLayout(2,2));
            panel1.add(new JLabel("客人姓名:"));
            JTextField nameField=new JTextField(10);
            panel1.add(nameField);

            JButton confirmButton=new JButton("确认");
            panel1.add(confirmButton);
            JButton cancelButton=new JButton("取消");
            panel1.add(cancelButton);
            dialog1.getContentPane().add(panel1);

            confirmButton.addActionListener(e1 -> {
                String name=nameField.getText().trim();

                if(customer_manage.deleteCustomer(name)){
                    JOptionPane.showMessageDialog(null,name+" 删除成功！");
                }else{
                    JOptionPane.showMessageDialog(null,"删除失败，请重试！");
                }
            });
            cancelButton.addActionListener(e1 -> {dialog1.dispose();});
            dialog1.setVisible(true);

        });
        updateButton.addActionListener(e -> {
            JDialog dialog1 = new JDialog((Frame) null,"修改客人信息");
            dialog1.setModal(true);
            dialog1.setSize(300,200);
            dialog1.setLocationRelativeTo(null);
            dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JTable table=Customer_manage.getAllcustomer(customColumnNames);
            table.setEnabled(true);
            table.getModel().addTableModelListener(e15 -> {
                if (e15.getType() == TableModelEvent.UPDATE) {
                    int row = e15.getFirstRow();
                    int column = e15.getColumn();
                    TableModel model = (TableModel) e15.getSource();
                    Object data = model.getValueAt(row, column);
                    String columnName = model.getColumnName(column);

                    // 获取客户ID
                    Object customer_id = model.getValueAt(row, 0);

                    // 更新数据库
                    customer_manage.updateCustomer(customer_id,columnName,data);
                }
            });

            JScrollPane scrollPane=new JScrollPane(table);
            dialog1.getContentPane().add(scrollPane,BorderLayout.CENTER);
            dialog1.setVisible(true);
        });
    }
}
