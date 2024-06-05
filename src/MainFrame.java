import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("酒店管理系统-主界面");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("房间管理", new RoomManagementPanel());
//        tabbedPane.addTab("Guest Management", new GuestManagementPanel());
//        tabbedPane.addTab("Booking Management", new BookingManagementPanel());
//        tabbedPane.addTab("Reports", new ReportGenerationPanel());

        add(tabbedPane);
    }
}
