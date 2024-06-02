public class Room {
    private int room_id;
    private String room_number;
    private String room_type;
    private double price_per_night;
    private double discount;
    private String status;

    //constructor
    Room(){}
    Room(int Room_id, String Room_number, String Room_type, double Price_per_night, double discount, String status){
        this.room_id = Room_id;
        this.room_number = Room_number;
        this.room_type = Room_type;
        this.price_per_night = Price_per_night;
        this.discount = discount;
        this.status = status;
    }

    public  int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public String getRoom_number() {
        return room_number;
    }
    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }
    public String getRoom_type() {
        return room_type;
    }
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
    public double getPrice_per_night() {
        return price_per_night;
    }
    public void setPrice_per_night(double price_per_night) {
        this.price_per_night = price_per_night;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
