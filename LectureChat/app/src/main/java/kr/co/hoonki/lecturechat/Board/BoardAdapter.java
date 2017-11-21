package kr.co.hoonki.lecturechat.Board;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.Model.BoardDataViewDTO;
import kr.co.hoonki.lecturechat.R;

/**
 * Created by chaebyeonghun on 2017. 11. 4..
 */

public class BoardAdapter extends RecyclerView.Adapter{

    private List<BoardDataViewDTO> boardDatas;
    private Context context;
    private String roomId;

    public BoardAdapter(List<BoardDataViewDTO> boardDatas,Context context, String roomId){

        this.boardDatas = boardDatas;
        this.context = context;
        this.roomId = roomId;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;

        final BoardDataViewDTO boardItem = boardDatas.get(position);

        viewHolder.writer.setText(boardItem.getCreateUserId());
        viewHolder.title.setText(boardItem.getTitle());
        viewHolder.date.setText(boardItem.getCreateDate());

        viewHolder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BoardContentActivity.class);
                intent.putExtra("boardId",boardItem.getKey());
                intent.putExtra("roomId",boardItem.getRoomId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return boardDatas.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout wrapper;
        private TextView writer, title, comment, date;
        public ViewHolder(View itemView) {
            super(itemView);
            wrapper = itemView.findViewById(R.id.wrapper_boardItem_wrapper);
            writer = itemView.findViewById(R.id.tv_boardItem_writer);
            title = itemView.findViewById(R.id.tv_boardItem_title);
            comment = itemView.findViewById(R.id.tv_boardItem_comment);
            date = itemView.findViewById(R.id.tv_boardItem_date);

        }
    }

    public void addItem(BoardDataViewDTO boardData){
        if(boardData != null){
            boardDatas.add(boardData);
        }
        notifyDataSetChanged();
    }

}
