package kr.co.hoonki.lecturechat.Board;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.R;

public class QuestionActivity extends AppCompatActivity {

    private ImageButton backButton;
    private Button submitButton;
    private EditText titleEditText, contentEditText;
    private DatabaseReference mDatabase;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private String roomId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        roomId = intent.getStringExtra("roomId");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        backButton = findViewById(R.id.btn_question_back);
        submitButton = findViewById(R.id.btn_question_upload);
        titleEditText = findViewById(R.id.edt_question_title);
        contentEditText = findViewById(R.id.edt_question_content);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionActivity.super.onBackPressed();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //질문 올리는 그거 들어가야함
                if(titleEditText.getText().toString().length() > 0 && contentEditText.getText().toString().length() > 0){
                   //질문 올리는거 ㄱㄱ
                    submitQuestion(titleEditText.getText().toString(),contentEditText.getText().toString());

                }else{
                    Toast.makeText(QuestionActivity.this,"제목과 내용을 모두 입력해주세요",Toast.LENGTH_LONG).show();
                }
           }
        });

    }
    public void submitQuestion(String title, String content){

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-h", Locale.KOREA);
        String currentDate = dateFormat.format(date);
        BoardData boardData = new BoardData(roomId,firebaseUser.getDisplayName(),currentDate,title,content);

        mDatabase.child("boardData").push().setValue(boardData);

        finish();

    }
}
