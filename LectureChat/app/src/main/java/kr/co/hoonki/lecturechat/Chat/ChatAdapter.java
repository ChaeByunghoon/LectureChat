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

import java.util.List;

import kr.co.hoonki.lecturechat.Board.BoardContentActivity;
import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.Model.ChatData;
import kr.co.hoonki.lecturechat.Model.ChatDataViewDTO;
import kr.co.hoonki.lecturechat.R;

/**
 * Created by chaebyeonghun on 2017. 11. 4..
 */

public class ChatAdapter extends RecyclerView.Adapter{

    private List<ChatDataViewDTO> chatDatas;
    private Context context;

    public ChatAdapter(List<ChatDataViewDTO> boardDatas, Context context){

        this.chatDatas = boardDatas;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;

        final ChatDataViewDTO chat = chatDatas.get(position);
        if (!chat.isMine()){
            viewHolder.wrapper.setVisibility(View.VISIBLE);
            viewHolder.myText.setVisibility(View.GONE);
            viewHolder.text.setText(chat.getMessage());
            viewHolder.name.setText(chat.getUserName());
        }else{
            viewHolder.wrapper.setVisibility(View.GONE);
            viewHolder.myText.setVisibility(View.VISIBLE);
            viewHolder.myText.setText(chat.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatDatas.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout wrapper;
        private TextView text, myText, name;
        private ImageView profile;
        public ViewHolder(View itemView) {
            super(itemView);
            wrapper = itemView.findViewById(R.id.wrapper_chatItem_notMine);
            text = itemView.findViewById(R.id.tv_chatItem_text);
            myText = itemView.findViewById(R.id.tv_chatItem_myText);
            name = itemView.findViewById(R.id.tv_chatItem_name);
            profile = itemView.findViewById(R.id.img_chatItem_profile);
        }
    }

    public void addItem(ChatDataViewDTO chatData){
        if(chatData != null){
            chatDatas.add(chatData);
        }
        notifyDataSetChanged();
    }

}
