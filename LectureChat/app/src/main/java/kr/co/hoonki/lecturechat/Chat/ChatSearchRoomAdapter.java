/**
 * Created by Chipmunk on 2017. 11. 6..
 */

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

import kr.co.hoonki.lecturechat.R;


public class ChatSearchRoomAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ChatSearchRoomItem> chatSearchRoomItems;

    public ChatSearchRoomAdapter(Context context) { this.context = context; this.chatSearchRoomItems = new ArrayList<>(); }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_search_room_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;

        itemHolder.roomTitle.setText(chatSearchRoomItems.get(position).getRoomTitle());
        itemHolder.roomDate.setText(chatSearchRoomItems.get(position).getRoomDate());

        String roomImgUrl = chatSearchRoomItems.get(position).getRoomImgUrl();
        if (!roomImgUrl.equals("")) {
            Picasso.with(context).load(chatSearchRoomItems.get(position).getRoomImgUrl()).into(itemHolder.roomImg);
        }
    }

    @Override
    public int getItemCount() {
        return chatSearchRoomItems.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView roomImg;
        private TextView roomTitle, roomDate;
        private ViewHolder(View view) {
            super(view);
            roomImg = view.findViewById(R.id.img_chatSearchRoomItem_roomImg);
            roomTitle = view.findViewById(R.id.tv_chatSearchRoomItem_roomName);
            roomDate = view.findViewById(R.id.tv_chatSearchRoomItem_Time);
        }
    }

    public void addItem(ChatSearchRoomItem item) {
        if (item != null) {
            chatSearchRoomItems.add(item);
        }
    }

}
