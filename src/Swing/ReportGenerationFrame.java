package Swing;

import DAO.Booking_manage;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerationFrame extends JFrame {
    Booking_manage booking_manage = new Booking_manage();

    public ReportGenerationFrame() {
        setTitle("酒店管理系统-报表生成");
        setLayout(new BorderLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("选择报表类型:"));
        String[] report_type = {"日报表", "月报表"};
        JComboBox<String> report_type_comboBox = new JComboBox<>(report_type);
        panel.add(report_type_comboBox);


        panel.add(new JLabel("选择日期 (YYYY-MM-DD):"));
        JTextField report_date_txt = new JTextField();
        panel.add(report_date_txt);

        //创建模型
        SpinnerDateModel model=new SpinnerDateModel();
        JSpinner spinner=new JSpinner(model);

        JSpinner.DateEditor dateEditor=new JSpinner.DateEditor(spinner,"yyyy-MM-dd");
        spinner.setEditor(dateEditor);
        panel.add(spinner);

        JButton GenerateButton = new JButton("生成报表");
        panel.add(GenerateButton);
        add(panel, BorderLayout.NORTH);

        JPanel reportPanel = new JPanel(new BorderLayout());
        add(reportPanel, BorderLayout.CENTER);
        //监听器
        spinner.addChangeListener(e -> {
            Date date = (Date) spinner.getValue();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            report_date_txt.setText(formatter.format(date));
        });

        GenerateButton.addActionListener(e -> {
            String reportType = String.valueOf(report_type_comboBox.getSelectedItem());
            try {
                LocalDate date = LocalDate.parse(report_date_txt.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (reportType.equals("日报表")) {
                    generateDailyReport(reportPanel, date);
                } else if (reportType.equals("月报表")) {
                    generateMonthlyReport(reportPanel, date);
                }
            } catch (DateTimeParseException ex) {
                ex.printStackTrace();
                //JOptionPane.showMessageDialog(this, "日期格式不正确，请输入 YYYY-MM-DD 格式的日期", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void generateDailyReport(JPanel reportPanel, LocalDate date) {
        Map<String, String> customColumnNames = new HashMap<>();
        customColumnNames.put("booking_id", "预定编号");
        customColumnNames.put("guest_name", "客人姓名");
        customColumnNames.put("room_number", "房间号");
        customColumnNames.put("check_in_date", "入住时间");
        customColumnNames.put("check_out_date", "离开时间");

        JTable table = Booking_manage.getBookingTable(customColumnNames, date);
        updateReportPanel(reportPanel, table, "客房入住日报表 (" + date + ")");
    }

    private void generateMonthlyReport(JPanel reportPanel, LocalDate date) {
        Map<String, String> customColumnNames = new HashMap<>();
        customColumnNames.put("booking_id", "预定编号");
        customColumnNames.put("guest_name", "客人姓名");
        customColumnNames.put("room_number", "房间号");
        customColumnNames.put("check_in_date", "入住时间");
        customColumnNames.put("check_out_date", "离开时间");

        JTable table = Booking_manage.getBookingTable(customColumnNames, date.withDayOfMonth(1), date.withDayOfMonth(date.lengthOfMonth()));
        updateReportPanel(reportPanel, table, "客房入住月报表 (" + date.getMonth() + ")");
    }

    private void updateReportPanel(JPanel reportPanel, JTable table, String title) {
        reportPanel.removeAll();
        if (table.getRowCount() > 0) {
            JScrollPane scrollPane = new JScrollPane(table);
            reportPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            reportPanel.add(new JLabel("没有数据"), BorderLayout.CENTER);
        }
        reportPanel.revalidate();
        reportPanel.repaint();
        setTitle(title);
        setSize(800, 600);
    }

}
