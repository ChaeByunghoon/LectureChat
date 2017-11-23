package kr.co.hoonki.lecturechat.Chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;


import kr.co.hoonki.lecturechat.ChatBoardActivity;
import kr.co.hoonki.lecturechat.R;


/**
 * Created by chaebyeonghun on 2017. 11. 2..
 */

public class ChatRoomAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ChatRoomItem> chatRoomItems;
    private String roomName;

    public ChatRoomAdapter(List<ChatRoomItem> items, Context mContext,String roomName){
        this.chatRoomItems = items;
        this.context = mContext;
        this.roomName = roomName;
    }

    public ChatRoomAdapter(Context mContext){
        this.chatRoomItems = new ArrayList<>();
        this.context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder itemHolder = (ViewHolder) holder;

        itemHolder.roomTitle.setText(chatRoomItems.get(position).getRoomTitle());
        String currentChat = chatRoomItems.get(position).getCurrentChat();
        if (currentChat != null) {
            if (!currentChat.equals("")) {
                itemHolder.currentChat.setText(chatRoomItems.get(position).getCurrentChat());
            } else {
                itemHolder.currentChat.setText("최근 대화가 없습니다.");
            }
        }
        else {
            itemHolder.currentChat.setText("최근 대화가 없습니다.");
        }

        String currentChatDate = chatRoomItems.get(position).getCurrentChatDate();
        if (currentChatDate != null) {
            if (!currentChatDate.equals("")) {
                itemHolder.chatTime.setText(currentChatDate);
            } else {
                itemHolder.chatTime.setText("");
            }
        }
        else {
            itemHolder.chatTime.setText("");
        }

        String roomImgUrl = chatRoomItems.get(position).getRoomImgUrl();
        if (!roomImgUrl.equals("")) {
            Picasso.with(context).load(chatRoomItems.get(position).getRoomImgUrl()).into(itemHolder.roomImg);
        }
        itemHolder.foreground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatBoardActivity.class);
                intent.putExtra("roomUid", chatRoomItems.get(position).getChatUid());
                intent.putExtra("roomName", chatRoomItems.get(position).getRoomTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatRoomItems.size();
    }

    public ChatRoomItem getItem(int position) { return chatRoomItems.get(position); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView roomImg;
        public TextView roomTitle,currentChat,chatTime;
        public RelativeLayout foreground;
        public RelativeLayout background;
        public ViewHolder(View view) {
            super(view);
            foreground = view.findViewById(R.id.wrapper_chatRoomItem_totalWrapper);
            background = view.findViewById(R.id.view_chatRoomItem_background);
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
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public void removeItem(int position) {
        chatRoomItems.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ChatRoomItem item, int position) {
        chatRoomItems.add(position, item);
        notifyItemInserted(position);
    }

}
