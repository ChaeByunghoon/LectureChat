package kr.co.hoonki.lecturechat.Chat;
import lombok.Data;

/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

@Data
public class ChatRoomItem {
    private String roomImgUrl;
    private String roomTitle;
    private String currentChat;
    private String currentChatDate;
}
