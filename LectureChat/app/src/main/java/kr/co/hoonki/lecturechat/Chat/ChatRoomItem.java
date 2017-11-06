package kr.co.hoonki.lecturechat.Chat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomItem {
    private String roomImgUrl;
    private String roomTitle;
    private String currentChat;
    private String currentChatDate;
}
