import java.util.Date;

public class Booking {
    private int booking_id;
    private int room_id;
    private int guest_id;
    private Date check_in_date;
    private Date expected_check_out_date;
    private Date actual_check_out_date;

    //constructor
    Booking(){}
    Booking(int booking_id, int room_id, int guest_id, Date check_in_date, Date expecteed_check_out_date){
        this.booking_id = booking_id;
        this.room_id = room_id;
        this.guest_id = guest_id;
        this.check_in_date = check_in_date;
        this.expected_check_out_date=expecteed_check_out_date;
        this.actual_check_out_date=check_in_date;
    }
    public int getBooking_id() {
        return booking_id;
    }
    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public int getGuest_id() {
        return guest_id;
    }
    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }
    public java.sql.Date getCheck_in_date() {
        return (java.sql.Date) check_in_date;
    }
    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }
    public java.sql.Date getExpected_check_out_date() {
        return (java.sql.Date) expected_check_out_date;
    }
    public void setExpected_check_out_date(Date expected_check_out_date) {
        this.expected_check_out_date = expected_check_out_date;
    }
    public java.sql.Date getActual_check_out_date() {
        return (java.sql.Date) actual_check_out_date;
    }
}
