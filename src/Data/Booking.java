package Data;

import java.time.LocalDate;

public class Booking {
    private int booking_id;
    private String guest_name;
    private int room_number;
    private LocalDate check_in_date;
    private LocalDate check_out_date;

    //constructor
    public Booking(int booking_id, String guest_name, int room_number, LocalDate check_in_date, LocalDate check_out_date) {

    this.booking_id = booking_id;
    this.guest_name = guest_name;
    this.room_number = room_number;
    this.check_in_date = check_in_date;
    this.check_out_date = check_out_date;
    }
    public int getBooking_id() {
        return booking_id;
    }
    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }
    public String getGuest_name() {
        return guest_name;
    }
    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }
    public int getRoom_number() {
        return room_number;
    }
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }
    public LocalDate getCheck_in_date() {
        return check_in_date;
    }
    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }
    public LocalDate getCheck_out_date() {
        return check_out_date;
    }
    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }



}
