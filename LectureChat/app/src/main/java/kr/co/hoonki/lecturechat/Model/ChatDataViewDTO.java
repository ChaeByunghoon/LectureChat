package kr.co.hoonki.lecturechat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chaebyeonghun on 2017. 11. 1..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class ChatDataViewDTO {
    public ChatDataViewDTO(ChatData chatData) {
        this.userId = chatData.getUserId();
        this.userName = chatData.getUserName();
        this.message = chatData.getMessage();
        this.sendTime = chatData.getSendTime();
    }

    public ChatDataViewDTO(ChatData chatData, boolean isMine) {
        this.userId = chatData.getUserId();
        this.userName = chatData.getUserName();
        this.message = chatData.getMessage();
        this.sendTime = chatData.getSendTime();
        this.isMine = isMine;
    }

    private String userId;
    private String userName;
    private String message;
    private String sendTime;
    private boolean isMine = false;


}
