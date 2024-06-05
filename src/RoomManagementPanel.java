import javax.swing.*;
import java.awt.*;

public class RoomManagementPanel extends JPanel {
    private room_manage roomManage=new room_manage();
    public RoomManagementPanel() {
        setLayout(new BorderLayout());

        // Room Form
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        formPanel.add(new JLabel("房间编号:"));
        JTextField roomIDField = new JTextField(10);
        formPanel.add(roomIDField);

        formPanel.add(new JLabel("房间号:"));
        JTextField roomnumberField = new JTextField();
        formPanel.add(roomnumberField);

        formPanel.add(new JLabel("房间类型:"));
        JTextField roomtypeField = new JTextField();
        formPanel.add(roomtypeField );

        formPanel.add(new JLabel("价格:"));
        JTextField  priceField= new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("折扣:"));
        JTextField discountField = new JTextField();
        formPanel.add(discountField);

        formPanel.add(new JLabel("状态:"));
        JTextField STAUTSField = new JTextField();
        formPanel.add(STAUTSField);

        JButton addButton= new JButton("添加房间");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);


    }
}
