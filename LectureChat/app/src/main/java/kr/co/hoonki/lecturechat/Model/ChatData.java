package kr.co.hoonki.lecturechat.Model;

/**
 * Created by chaebyeonghun on 2017. 11. 1..
 */

public class ChatData {

    private String userName;
    private String message;
    private String sendTime;

    public ChatData(){

    }

    public ChatData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
