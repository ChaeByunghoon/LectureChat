package kr.co.hoonki.lecturechat.Chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;


import kr.co.hoonki.lecturechat.R;


/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

public class ChatRoomAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ChatRoomItem> items;

    public ChatRoomAdapter(List<ChatRoomItem> items, Context mContext){
        this.items = items;
        this. context =mContext;
    }

    public ChatRoomAdapter(Context mContext){
        this.items = new ArrayList<>();
        this. context =mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        itemHolder.roomTitle.setText(items.get(position).getRoomTitle());
        itemHolder.currentChat.setText(items.get(position).getCurrentChat());
        itemHolder.chatTime.setText(items.get(position).getCurrentChatDate());
        String roomImgUrl = items.get(position).getRoomImgUrl();
        if (!roomImgUrl.equals("")) {
            Picasso.with(context).load(items.get(position).getRoomImgUrl()).into(itemHolder.roomImg);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
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
            items.add(item);
        }
    }

}
