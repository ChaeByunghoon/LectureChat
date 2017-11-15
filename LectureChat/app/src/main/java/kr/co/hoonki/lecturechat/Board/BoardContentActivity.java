package kr.co.hoonki.lecturechat.Board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.R;

public class BoardContentActivity extends AppCompatActivity {

    @BindView(R.id.tv_boardContent_writer)
    TextView tv_writer;
    @BindView(R.id.tv_boardContent_title)
    TextView tv_title;
    @BindView(R.id.tv_boardContent_content)
    TextView tv_content;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String boardKey = intent.getSerializableExtra("boardKey").toString();

    }

    public void getData(String boardKey){

        databaseRef = FirebaseDatabase.getInstance().getReference();

        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardData boardData = dataSnapshot.getValue(BoardData.class);
                tv_writer.setText(boardData.getCreateUserId());
                tv_title.setText(boardData.getTitle());
                tv_content.setText(boardData.getContent());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
