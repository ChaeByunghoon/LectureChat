package kr.co.hoonki.lecturechat.Chat;
import android.widget.ImageView;

/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

public class ChatRoomItem {

    private String roomImgUrl;
    private String roomTitle;
    private String currentChat;

    public ChatRoomItem(String roomTitle, String roomImgUrl, String currentChat) {
        setRoomTitle(roomTitle);
        setRoomImgUrl(roomImgUrl);
        setCurrentChat(currentChat);
    }

    public String getRoomImgUrl(){
        return roomImgUrl;
    }

    public String getCurrentChat() {
        return currentChat;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setCurrentChat(String currentChat) {
        this.currentChat = currentChat;
    }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }
}
