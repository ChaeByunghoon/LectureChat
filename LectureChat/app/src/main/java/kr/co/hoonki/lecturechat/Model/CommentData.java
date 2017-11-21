package kr.co.hoonki.lecturechat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by chaebyeonghun on 2017. 11. 3..
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class CommentData {

    private String content;
    private String createUserId;
    private String createdTime;

    public CommentData(){

    }
}
