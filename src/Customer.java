public class Customer {
    private int guest_id;
    private String name;
    private String gender;
    private String id_number;
    private String phone_number;

    //constructor
    Customer(){};
    Customer(int guest_id, String name, String gender, String id_number, String phone_number) {
        this.guest_id = guest_id;
        this.name = name;
        this.gender = gender;
        this.id_number = id_number;
        this.phone_number = phone_number;
    }
    public int getGuest_id() {
        return guest_id;
    }
    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getId_number() {
        return id_number;
    }
    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
