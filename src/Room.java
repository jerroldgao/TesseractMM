import java.awt.*;

/**
 * Created by Yirugao on 11/27/16.
 */
public class Room {
    private String roomNumber;
    private Rectangle boundingBox;

    public Room(String roomNumber,Rectangle boundingBox){
        this.roomNumber = roomNumber;
        this.boundingBox = boundingBox;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
    public boolean equals(Room room){
        if(room.roomNumber.equals(roomNumber)){
            return true;
        }
        return false;
    }
    public int hashCode(Room room){
        int code = 19;
        code += 31*room.roomNumber.hashCode();
        return code;
    }
}
