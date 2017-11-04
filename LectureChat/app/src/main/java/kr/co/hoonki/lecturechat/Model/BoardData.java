package kr.co.hoonki.lecturechat.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by chaebyeonghun on 2017. 11. 3..
 */
@Data
@AllArgsConstructor
public class BoardData {

    private String roomId;
    private String createUserId;
    private String createDate;
    private String boardId;
    private String title;
    private String content;
    private List<String> commentIds;
    private List<String> attachmentUrls;

    public int getCommentCount(){
        return this.commentIds.size();
    }
}
