package kr.co.hoonki.lecturechat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import kr.co.hoonki.lecturechat.Model.CommentData;

/**
 * Created by chaebyeonghun on 2017. 11. 21..
 */

public class CommentAdapter extends RecyclerView.Adapter {

    List<CommentData> commentList;

    public CommentAdapter(ArrayList<CommentData> commentList){
        this.commentList = commentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder itemViewHolder = (ViewHolder)holder;
        CommentData commentData = commentList.get(position);

        itemViewHolder.writer.setText(commentData.getCreateUserId());
        itemViewHolder.content.setText(commentData.getContent());
        itemViewHolder.date.setText(commentData.getCreatedTime());


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout wrapper;
        private TextView writer, content, date;
        public ViewHolder(View itemView) {
            super(itemView);
            wrapper = itemView.findViewById(R.id.wrapper_commentItem_relativeLayout);
            writer = itemView.findViewById(R.id.tv_commentItem_writer);
            content = itemView.findViewById(R.id.tv_commentItem_content);
            date = itemView.findViewById(R.id.tv_commentItem_date);
        }
    }

    public void addItem(CommentData commentData){
        if(commentData != null){
            commentList.add(commentData);
        }
        notifyDataSetChanged();
    }
}
