package kr.co.hoonki.lecturechat.Board;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hoonki.lecturechat.CommentAdapter;
import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.Model.BoardDataViewDTO;
import kr.co.hoonki.lecturechat.Model.CommentData;
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
    @BindView(R.id.rv_boardContent_commentList)
    RecyclerView recyclerView;
    private String roomId;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;
    private FirebaseDatabase database;
    private CommentAdapter adapter;
    private String boardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content);
        ButterKnife.bind(this);

        setData();
        adapter = new CommentAdapter(new ArrayList<CommentData>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getComment();
    }
    private void setData(){
        Intent intent = getIntent();
        roomId = intent.getSerializableExtra("roomId").toString();
        boardId = intent.getSerializableExtra("boardId").toString();
        databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRef.child("boardData").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardData boardData = dataSnapshot.getValue(BoardData.class);
                if (boardData.getRoomId().equals(roomId)){
                    BoardDataViewDTO boardDataViewDTO = new BoardDataViewDTO(boardData,dataSnapshot.getKey());
                    if(boardDataViewDTO.getKey().equals(boardId)){
                        tv_title.setText(boardDataViewDTO.getTitle());
                        tv_writer.setText(boardDataViewDTO.getCreateUserId());
                        tv_content.setText(boardDataViewDTO.getContent());
                    }

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

    private void getComment(){

        databaseRef = FirebaseDatabase.getInstance().getReference();


        databaseRef.child("boardData").child(boardId).child("commentList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CommentData commentData = dataSnapshot.getValue(CommentData.class);
                adapter.addItem(commentData);
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
        if (edt_comment.getText().toString().length() > 0) {
            firebaseAuth = FirebaseAuth.getInstance();
            database = FirebaseDatabase.getInstance();
            databaseRef = database.getReference("boardData/" + boardId + "/commentList");
            String content = edt_comment.getText().toString();

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-h", Locale.KOREA);
            String currentDate = dateFormat.format(date);

            CommentData data = new CommentData(content,firebaseAuth.getCurrentUser().getDisplayName(), currentDate);
            databaseRef.push().setValue(data);

            hideKeyBoard(edt_comment);
            edt_comment.setText("");



        }else{
            Toast.makeText(this, "댓글을 입력해주세요",Toast.LENGTH_SHORT).show();
        }
    }
    public void hideKeyBoard(EditText editText){
        InputMethodManager imManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    @OnClick(R.id.btn_boardContent_back)
    public void backClick(){
        super.onBackPressed();
    }
}
