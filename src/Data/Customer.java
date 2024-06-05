package Data;

import java.time.LocalDate;

public class Customer {
    private int guest_id;
    private String guest_name;
    private String guest_gender;
    private String guest_contact;
    private String id_number;
    private LocalDate check_in_date;
    private int expected_stay;

    //constructor
    public Customer(int guest_id,String guest_name,String guest_gender,String guest_contact,String id_number,LocalDate check_in_date,int expected_stay){
        this.guest_id=guest_id;
        this.guest_name=guest_name;
        this.guest_gender=guest_gender;
        this.guest_contact=guest_contact;
        this.id_number=id_number;
        this.check_in_date=check_in_date;
        this.expected_stay=expected_stay;
    }
    public int getGuest_id() {
        return guest_id;
    }
    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }
    public String getGuest_name() {
        return guest_name;
    }
    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }
    public String getGuest_gender() {
        return guest_gender;
    }
    public void setGuest_gender(String guest_gender) {
        this.guest_gender = guest_gender;
    }
    public String getGuest_contact() {
        return guest_contact;
    }
    public void setGuest_contact(String guest_contact) {
        this.guest_contact = guest_contact;
    }
    public String getId_number() {
        return id_number;
    }
    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
    public LocalDate getCheck_in_date() {
        return check_in_date;
    }
    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }
    public int getExpected_stay() {
        return expected_stay;
    }
    public void setExpected_stay(int expected_stay) {
        this.expected_stay = expected_stay;
    }
}
