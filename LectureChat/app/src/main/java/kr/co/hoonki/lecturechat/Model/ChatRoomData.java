package kr.co.hoonki.lecturechat.Model;

import java.util.List;

/**
 * Created by chaebyeonghun on 2017. 11. 3..
 */

public class ChatRoomData {

    private String roomId;
    private String roomName;
    private String roomImage;
    private String createDate;
    private String updateDate;
    private int userCount;
    private List<String> userIds;
    private List<ChatData> chats;
    private List<BoardData> bords;

}
