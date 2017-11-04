package kr.co.hoonki.lecturechat.Board;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.R;

/**
 * Created by chaebyeonghun on 2017. 11. 4..
 */

public class BoardAdapter extends RecyclerView.Adapter {

    private List<BoardData> boardDatas;
    private Context context;

    public BoardAdapter(Context context, List<BoardData> boardDatas){
        this.context = context;
        this.boardDatas = boardDatas;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item,parent);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;

        BoardData boardItem = boardDatas.get(position);

        viewHolder.writer.setText(boardItem.getCreateUserId());
        viewHolder.title.setText(boardItem.getTitle());
        viewHolder.date.setText(boardItem.getCreateDate());

    }

    @Override
    public int getItemCount() {
        return boardDatas.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private TextView writer, title, comment, date;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            writer = itemView.findViewById(R.id.tv_boardItem_writer);
            title = itemView.findViewById(R.id.tv_boardItem_title);
            comment = itemView.findViewById(R.id.tv_boardItem_comment);
            date = itemView.findViewById(R.id.tv_boardItem_date);
            imageView = itemView.findViewById(R.id.img_boardItem_image);

        }
    }
}
