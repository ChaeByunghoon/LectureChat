package kr.co.hoonki.lecturechat.Chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.function.Consumer;

import kr.co.hoonki.lecturechat.R;


/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

public class ChatRoomAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ChatRoomItem> chatRoomItems;

    public ChatRoomAdapter(Context context) { this.context = context; this.chatRoomItems = new ArrayList<>(); }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;

        itemHolder.roomTitle.setText(chatRoomItems.get(position).getRoomTitle());
        if (!itemHolder.currentChat.getText().equals("")) {
            itemHolder.currentChat.setText(chatRoomItems.get(position).getCurrentChat());
        }
        else {
            itemHolder.currentChat.setText("최근 대화가 없습니다.");
        }
        // TODO : 최근 메시지 날짜 가져와서 날짜 텍스트뷰 수정
        itemHolder.chatTime.setText("날짜");

        String roomImgUrl = chatRoomItems.get(position).getRoomImgUrl();
        if (!roomImgUrl.equals("")) {
            Picasso.with(context).load(chatRoomItems.get(position).getRoomImgUrl()).into(itemHolder.roomImg);
        }
    }

    @Override
    public int getItemCount() {
        return chatRoomItems.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView roomImg;
        private TextView roomTitle,currentChat,chatTime;
        private ViewHolder(View view) {
            super(view);
            roomImg = view.findViewById(R.id.img_chatRoomItem_roomImg);
            roomTitle = view.findViewById(R.id.tv_chatRoomItem_roomName);
            currentChat = view.findViewById(R.id.tv_chatRoomItem_currentChat);
            chatTime = view.findViewById(R.id.tv_chatRoomItem_Time);
        }
    }

    public void addItem(ChatRoomItem item) {
        if (item != null) {
            chatRoomItems.add(item);
        }
    }

}
