public class room_manager{
    private int manager_id;
    private String name;
    private String contact_info;

    //constructor
    room_manager(){

    }
    room_manager(int manager_id, String name, String contact_info){
        this.manager_id = manager_id;
        this.name = name;
        this.contact_info = contact_info;
    }
    public int getManager_id(){
        return manager_id;
    }
    public void setManager_id(int manager_id){
        this.manager_id = manager_id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getContact_info(){
        return contact_info;
    }
    public void setContact_info(String contact_info){
        this.contact_info = contact_info;
    }
}
