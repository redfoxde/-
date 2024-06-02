public class room_manager_relation {
    private int room_id;
    private int manage_id;

    room_manager_relation(int room_id, int manage_id) {
        this.room_id = room_id;
        this.manage_id = manage_id;
    }
    public int getRoom_id() {
        return room_id;
    }
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
    public int getManage_id() {
        return manage_id;
    }
    public void setManage_id(int manage_id) {
        this.manage_id = manage_id;
    }
}
