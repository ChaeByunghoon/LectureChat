package kr.co.hoonki.lecturechat.Chat;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.OnClick;
import kr.co.hoonki.lecturechat.ChatBoardActivity;
import kr.co.hoonki.lecturechat.LoginActivity;
import kr.co.hoonki.lecturechat.R;

public class ChatListActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private RecyclerView recyclerView;
    private ChatRoomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton btnChatRoomAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        checkLogin();

        recyclerView = findViewById(R.id.rv_chatList_chatList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       /* adapter = new ChatRoomAdapter()
        recyclerView.setAdapter(adapter);*/

       btnChatRoomAdd = findViewById(R.id.btn_chatList_add);
       btnChatRoomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChatListActivity.this, ChatRoomSearchActivity.class);
//                startActivity(intent);
                mFirebaseAuth.signOut();
                Log.d("ChatListActivity", "SignOut");
                checkLogin();
            }
       });

        Button testButton = findViewById(R.id.btn_chatList_search);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatListActivity.this, ChatBoardActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkLogin(){
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser == null){

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else{
            Log.d("ChatListActivity", mFirebaseUser.getUid());
        }
    }



}
