/**
 * Created by Chipmunk on 2017. 11. 6..
 */

package kr.co.hoonki.lecturechat.Chat;
import android.widget.ImageView;

public class ChatSearchRoomItem {

    private String roomImgUrl;
    private String roomTitle;
    private String roomDate;
    private String chatUid;

    public ChatSearchRoomItem(String roomTitle, String roomImgUrl, String roomDate, String chatUid) {
        setRoomTitle(roomTitle);
        setRoomImgUrl(roomImgUrl);
        setRoomDate(roomDate);
        setChatUid(chatUid);
    }

    public String getRoomImgUrl(){
        return roomImgUrl;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getRoomDate() { return roomDate; }

    public String getChatUid() { return chatUid; }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public void setRoomDate(String roomDate) { this.roomDate = roomDate; }

    public void setChatUid(String chatUid) { this.chatUid = chatUid; }
}

