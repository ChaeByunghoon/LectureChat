package kr.co.hoonki.lecturechat.Chat;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class ChatRoomItem implements Serializable {
    private String chatUid;
    private String roomImgUrl;
    private String roomTitle;
    private String currentChat;
    private String currentChatDate;
}
