/**
 * Created by Chipmunk on 2017. 11. 6..
 */

package kr.co.hoonki.lecturechat.Chat;
import android.widget.ImageView;

public class ChatSearchRoomItem {

    private String roomImgUrl;
    private String roomTitle;
    private String roomDate;

    public ChatSearchRoomItem(String roomTitle, String roomImgUrl, String roomDate) {
        setRoomTitle(roomTitle);
        setRoomImgUrl(roomImgUrl);
        setRoomDate(roomDate);
    }

    public String getRoomImgUrl(){
        return roomImgUrl;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getRoomDate() { return roomDate; }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public void setRoomDate(String roomDate) { this.roomDate = roomDate; }
}

