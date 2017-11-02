package kr.co.hoonki.lecturechat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

public class ChatRoomAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ChatRoomItem> chatRoomItems;

    public ChatRoomAdapter(ArrayList items, Context mContext){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room_item,parent);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;

        itemHolder.roomTitle.setText(chatRoomItems.get(position).getRoomTitle());
        itemHolder.currentChat.setText(chatRoomItems.get(position).getCurrentChat());
    }

    @Override
    public int getItemCount() {
        return chatRoomItems.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView roomImg;
        private TextView roomTitle,currentChat;
        private ViewHolder(View view) {
            super(view);
            roomImg = view.findViewById(R.id.img_chatRoomItem_roomImg);
            roomTitle = view.findViewById(R.id.tv_chatRoomItem_roomName);
            currentChat = view.findViewById(R.id.tv_chatRoomItem_currentChat);
        }
    }

}
