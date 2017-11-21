/**
 * Created by Chipmunk on 2017. 11. 6..
 */

package kr.co.hoonki.lecturechat.Chat;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kr.co.hoonki.lecturechat.R;


public class ChatSearchRoomAdapter extends RecyclerView.Adapter {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<ChatSearchRoomItem> chatSearchRoomItems;
    private View v;

    public ChatSearchRoomAdapter(Context context, RecyclerView recyclerView) { this.context = context; this.chatSearchRoomItems = new ArrayList<>(); this.recyclerView = recyclerView; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_search_room_item, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder itemHolder = (ViewHolder) holder;

        itemHolder.roomTitle.setText(chatSearchRoomItems.get(position).getRoomTitle());
        itemHolder.roomDate.setText(chatSearchRoomItems.get(position).getRoomDate());

        String roomImgUrl = chatSearchRoomItems.get(position).getRoomImgUrl();
        if (!roomImgUrl.equals("")) {
            Picasso.with(context).load(chatSearchRoomItems.get(position).getRoomImgUrl()).into(itemHolder.roomImg);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recyclerView.getChildLayoutPosition(v);
                ChatSearchRoomItem item = chatSearchRoomItems.get(itemPosition);

                FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("User/" + mFirebaseUser.getUid() + "/chatRoomList/" + chatSearchRoomItems.get(position).getChatUid());
                myRef.setValue(true);

                ChatRoomSearchActivity activity = (ChatRoomSearchActivity) context;
                activity.setResult(Activity.RESULT_OK);
                activity.finish();
            }
        });
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
