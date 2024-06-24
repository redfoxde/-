package Swing;

import DAO.Booking_manage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ReportGenereationFrame extends JFrame {
    Booking_manage booking_manage=new Booking_manage();
    public ReportGenereationFrame() {

        setTitle("酒店管理系统-报表生成");
        setLayout(new BorderLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel(new GridLayout(4,2));
        panel.add(new JLabel("选择报表类型"));
        String[] report_type={"日报表","月报表"};
        JComboBox<String> report_type_comboBox = new JComboBox<>(report_type);
        panel.add(report_type_comboBox);

        panel.add(new JLabel("选择日期(YYYY-MM-DD):"));
        JTextField report_date_txt = new JTextField();
        panel.add(report_date_txt);


        JButton GenerateButton=new JButton("报表生成");
        panel.add(GenerateButton);
        add(panel, BorderLayout.SOUTH);

        


        GenerateButton.addActionListener(e -> {

            String report_type1 = String.valueOf(report_type_comboBox.getSelectedItem());
            LocalDate Date=LocalDate.parse(report_date_txt.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if(report_type1.equals("日报表")){
                GenerateDailyReport(Date);
            }else if(report_type1.equals("月报表")){
                GenerateMonthReport(Date);
            }

        });

    }

    private void GenerateDailyReport(LocalDate Date){
//        StringBuilder report = new StringBuilder("客房入住报表(" + Date + "):\n");
        Map<String,String> customColumnNames=new HashMap<>();
        customColumnNames.put("booking_id","预定编号");
        customColumnNames.put("guest_name","客人姓名");
        customColumnNames.put("room_number","房间号");
        customColumnNames.put("check_in_date","入住时间");
        customColumnNames.put("check_out_date","离开时间");
        JTable table=Booking_manage.getBookingsTable(customColumnNames);
        if(table.getRowCount()>0){
            JScrollPane scrollPane=new JScrollPane(table);
            add(scrollPane,BorderLayout.CENTER);

            setTitle("客房入住报表(" + Date + "):\n");
            setSize(800,600);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }

    }
    private void GenerateMonthReport(LocalDate Date){
//        StringBuilder report = new StringBuilder("客房入住报表(" + Date.getMonth() + "):\n");
        Map<String,String> customColumnNames=new HashMap<>();
        customColumnNames.put("booking_id","预定编号");
        customColumnNames.put("guest_name","客人姓名");
        customColumnNames.put("room_number","房间号");
        customColumnNames.put("check_in_date","入住时间");
        customColumnNames.put("check_out_date","离开时间");
        JTable table=Booking_manage.getBookingsTable(customColumnNames);
        if(table.getRowCount()>0){
            JScrollPane scrollPane=new JScrollPane(table);
            add(scrollPane,BorderLayout.CENTER);
            scrollPane.setViewportView(table);
            setVisible(true);
        }


    }
}
