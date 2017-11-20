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
public class ChatData {

    private String userId;
    private String userName;
    private String message;
    private String sendTime;


}
