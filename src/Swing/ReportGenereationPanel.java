package Swing;

import DAO.Booking_manage;
import Data.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportGenereationPanel extends JPanel {
    Booking_manage booking_manage=new Booking_manage();
    public ReportGenereationPanel() {

        setLayout(new BorderLayout());
        JDesktopPane desktopPane = new JDesktopPane();
        JInternalFrame internalFrame = new JInternalFrame("报表生成",true,false,true,true);
        internalFrame.setSize(400,200);
        internalFrame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(4,2));
        panel.add(new JLabel("选择报表类型"));
        String[] report_type={"日报表","月报表"};
        JComboBox<String> report_type_comboBox = new JComboBox(report_type);
        panel.add(report_type_comboBox);

        panel.add(new JLabel("选择日期(YYYY-MM-DD):"));
        JTextField report_date_txt = new JTextField();
        panel.add(report_date_txt);

        JTextArea reportArea = new JTextArea();
        JScrollPane reportAreaScrollPane = new JScrollPane(reportArea);
        internalFrame.add(reportAreaScrollPane, BorderLayout.CENTER);

        //显示内部框架
        try{
            internalFrame.setMaximizable(true);
            internalFrame.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }


        JButton GenerateButton=new JButton("报表生成");
        panel.add(GenerateButton);


        GenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String report_type= String.valueOf(report_type_comboBox.getSelectedItem());
                LocalDate Date=LocalDate.parse(report_date_txt.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if(report_type.equals("日报表")){
                    GenerateDailyReport(Date,reportArea);
                }else if(report_type.equals("月报表")){
                    GenerateMonthReport(Date,reportArea);
                }

            }



        });

        internalFrame.add(panel,BorderLayout.NORTH);
        desktopPane.add(internalFrame);
        add(desktopPane,BorderLayout.CENTER);
    }

    private void GenerateDailyReport(LocalDate Date,JTextArea reportArea){
        List<Booking> bookings=booking_manage.getbooking();
        StringBuilder report = new StringBuilder("客房入住报表(" + Date + "):\n");

        for(Booking booking:bookings){
            if (booking.getCheck_in_date().equals(Date)){
                report.append(booking).append("\n");
            }
        }

        reportArea.setText(report.toString());
    }
    private void GenerateMonthReport(LocalDate Date,JTextArea reportArea){
        List<Booking> bookings=booking_manage.getbooking();
        StringBuilder report = new StringBuilder("客房入住报表(" + Date.getMonth() + "):\n");

        for(Booking booking:bookings){
            if (booking.getCheck_in_date().getMonth().equals(Date.getMonth())){
                report.append(booking).append("\n");
            }
        }
        reportArea.setText(report.toString());

    }
}