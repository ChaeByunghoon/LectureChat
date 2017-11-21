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
public class BoardDataViewDTO implements Serializable{

    private String key;
    private String roomId;
    private String createUserId;
    private String createDate;
    private String title;
    private String content;
    private Map<String,CommentData> commentList;

    public BoardDataViewDTO (BoardData boardData, String key){
        this.roomId = boardData.getRoomId();
        this.createUserId = boardData.getCreateUserId();
        this.createDate = boardData.getCreateDate();
        this.title = boardData.getTitle();
        this.content = boardData.getContent();
        this.commentList = boardData.getCommentList();
        this.key = key;
    }
}
