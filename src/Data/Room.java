package Data;

public class Room {
    private int room_id;
    private int room_number;
    private String room_type;
    private double room_price;
    private String room_discount;
    private String status;
    private String room_manager;
    private String room_contact;

    public Room(int room_number,String room_type,double room_price,String room_discount,String status,String room_manager,String room_contact){
//        this.room_id = room_id;
        this.room_number = room_number;
        this.room_type = room_type;
        this.room_price = room_price;
        this.room_discount = room_discount;
        this.status = status;
        this.room_manager = room_manager;
        this.room_contact = room_contact;
    }

    public int getRoom_number() {
        return room_number;
    }

    public String getRoom_type() {
        return room_type;
    }

    public double getRoom_price() {
        return room_price;
    }

    public String getRoom_discount() {
        return room_discount;
    }

    public String getStatus() {
        return status;
    }

    public String getRoom_manager() {
        return room_manager;
    }

    public String getRoom_contact() {
        return room_contact;
    }

    public String toString(){
        return "房间信息[房间编号:"+
                room_id+"\n"+
                "房间号:"+
                room_number+"\n"+
                "房间类型:"+
                room_type+"\n"+
                "房间价格:"+
                room_price+"\n"+
                "房间折扣:"+
                room_discount+"\n"+
                "房间负责人:"+
                room_manager+"\n"+
                "负责人联系电话:"+
                room_contact+"\n"+"]";


    }

}
