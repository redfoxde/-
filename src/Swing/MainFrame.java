package Swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("酒店管理系统-主页面");
        setLayout(new FlowLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        //房间管理
        JMenu RoomMangeFrame=new JMenu("房间管理");
        JMenuItem openItem= new JMenuItem("打开");
        JMenuItem openItem1=new JMenuItem("关闭");
        openItem.addActionListener(e -> new RoomManagementFrame());
        openItem1.addActionListener(e -> dispose());
        RoomMangeFrame.add(openItem);
        RoomMangeFrame.add(openItem1);
        menuBar.add(RoomMangeFrame);
        setJMenuBar(menuBar);

        //预定管理
        JMenu BookingManageFrame=new JMenu("预定管理");
        JMenuItem openItem2=new JMenuItem("打开");
        JMenuItem openItem3=new JMenuItem("关闭");
        openItem2.addActionListener(e -> new BookingManageFrame());
        openItem3.addActionListener(e -> dispose());
        BookingManageFrame.add(openItem2);
        BookingManageFrame.add(openItem3);
        menuBar.add(BookingManageFrame);
        setJMenuBar(menuBar);

        //客户管理
        JMenu CustomermanageFrame=new JMenu("客户管理");
        JMenuItem openItem4=new JMenuItem("打开");
        JMenuItem oenItem5=new JMenuItem("关闭");
        openItem4.addActionListener(e->new CustomerManageFrame());
        oenItem5.addActionListener(e -> dispose());
        CustomermanageFrame.add(openItem4);
        CustomermanageFrame.add(oenItem5);
        menuBar.add(CustomermanageFrame);
        setJMenuBar(menuBar);

        //报表管理
        JMenu ReportManageFrame=new JMenu("报表管理");
        JMenuItem openItem6=new JMenuItem("打开");
        JMenuItem openItem7=new JMenuItem("关闭");
        openItem6.addActionListener(e-> new ReportGenereationFrame());
        openItem7.addActionListener(e -> dispose());
        ReportManageFrame.add(openItem6);
        ReportManageFrame.add(openItem7);
        menuBar.add(ReportManageFrame);
        setJMenuBar(menuBar);

        //背景
        BackgroundPanel backgroundPanel= new BackgroundPanel();
        setContentPane(backgroundPanel);

    }
    //自定义面板
    private static class BackgroundPanel extends JLabel{
        private BufferedImage backgroundImage;
        public BackgroundPanel() {
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("Swing/background.jpg")) {
                if (is != null) {
                    backgroundImage = ImageIO.read(is);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(backgroundImage!=null){
                //绘制背景图片
                g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
            }
        }
    }
}
