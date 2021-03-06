package kr.co.hoonki.lecturechat.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by chaebyeonghun on 2017. 11. 3..
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class BoardData implements Serializable{

    private String roomId;
    private String createUserId;
    private String createDate;
    private String title;
    private String content;
    private Map<String,CommentData> commentList;



    public BoardData(){

    }
    public BoardData(String roomId, String createUserId, String createDate, String title, String content){
        this.roomId = roomId;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.title = title;
        this.content = content;
    }
}
