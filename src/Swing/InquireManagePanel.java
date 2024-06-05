package Swing;

import javax.swing.*;
import java.awt.*;

public class InquireManagePanel extends JPanel {
    public InquireManagePanel() {
        setLayout(new BorderLayout());
        JDesktopPane desktopPane = new JDesktopPane();
        //以房号查询
        JInternalFrame internalFrame = new JInternalFrame("查询房间",true,false,true,true);
        internalFrame.setSize(400,200);
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        desktopPane.add(Box.createRigidArea(new Dimension(100,50)));
        add(desktopPane, BorderLayout.CENTER);


        //以最低价格查询
        //以最高价格查询
        //以房间编号查询

        //查询所有客人
        //查询单个客人

        //以客人姓名查询
        //以客人身份证号查询


    }

}
