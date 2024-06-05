package Swing;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("酒店管理系统-主界面");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("房间管理",new RoomManagementPanel() );
        add(tabbedPane);

        tabbedPane.addTab("客户管理",new GuestMangePanel());
        add(tabbedPane);

        tabbedPane.addTab("预定管理",new BookingManagePanel());
        add(tabbedPane);

        tabbedPane.addTab("报表生成",new ReportGenereationPanel());

        setVisible(true);


    }
}
