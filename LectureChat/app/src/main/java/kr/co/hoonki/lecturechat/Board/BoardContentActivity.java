package kr.co.hoonki.lecturechat.Board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.R;

public class BoardContentActivity extends AppCompatActivity {

    @BindView(R.id.tv_boardContent_writer)
    TextView tv_writer;
    @BindView(R.id.tv_boardContent_title)
    TextView tv_title;
    @BindView(R.id.tv_boardContent_content)
    TextView tv_content;
    @BindView(R.id.edt_boardContent_comment)
    EditText edt_comment;
    private String boardId;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Intent intent = getIntent();
        boardId = intent.getSerializableExtra("boardId").toString();

        Log.e("aaa", boardId);
        getData(boardId);
    }

    public void getData(String boardId) {

        databaseRef = FirebaseDatabase.getInstance().getReference();

        Query query = databaseRef.child("boardData").orderByChild("boardId").equalTo(boardId);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    BoardData boardData = dataSnapshot.getValue(BoardData.class);
                    Log.e("aaa", boardData.getTitle());
                    Log.e("aaa", boardData.getContent());

                    tv_writer.setText(boardData.getCreateUserId());
                    tv_title.setText(boardData.getTitle());
                    tv_content.setText(boardData.getContent());


                }
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
    @OnClick(R.id.btn_boardContent_write)
    public void writeClick(){
        if (edt_comment.getText().toString().length() > 0){
            databaseRef = FirebaseDatabase.getInstance().getReference();

            Query query = databaseRef.child("boardData").orderByChild("boardId").equalTo(boardId);

        }
    }
}
