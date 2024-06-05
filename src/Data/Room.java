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

    //constructor
    public Room(int room_id,int room_number,String room_type,double room_price,String room_discount,String status,String room_manager,String room_contact){
        this.room_id = room_id;
        this.room_number = room_number;
        this.room_type = room_type;
        this.room_price = room_price;
        this.room_discount = room_discount;
        this.status = status;
        this.room_manager = room_manager;
        this.room_contact = room_contact;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public int getRoom_number() {
        return room_number;
    }
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }
    public String getRoom_type() {
        return room_type;
    }
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
    public double getRoom_price() {
        return room_price;
    }
    public void setRoom_price(double room_price) {
        this.room_price = room_price;
    }
    public String getRoom_discount() {
        return room_discount;
    }
    public void setRoom_discount(String room_discount) {
        this.room_discount = room_discount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRoom_manager() {
        return room_manager;
    }
    public void setRoom_manager(String room_manager) {
        this.room_manager = room_manager;
    }
    public String getRoom_contact() {
        return room_contact;
    }
    public void setRoom_contact(String room_contact) {
        this.room_contact = room_contact;
    }

}
