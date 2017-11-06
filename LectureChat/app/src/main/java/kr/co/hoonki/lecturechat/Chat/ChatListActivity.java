package kr.co.hoonki.lecturechat.Chat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import kr.co.hoonki.lecturechat.LoginActivity;
import kr.co.hoonki.lecturechat.R;

public class ChatListActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private RecyclerView recyclerView;
    private ChatRoomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionMenu materialDesignFAM;
    private FloatingActionButton btnChatRoomAdd;

<<<<<<< HEAD
=======
    private Button btnSearch;

    private final String TAG = "ChatListActivity";
    private final int CREATE_REQUEST = 101;

>>>>>>> 3ec4af85b90387f647552e9a3ae77f8bbc126d53
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        checkLogin();

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.rv_chatList_chatList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

<<<<<<< HEAD
        btnChatRoomAdd = findViewById(R.id.btn_chatList_add);
        btnChatRoomAdd.setOnClickListener(new View.OnClickListener() {
=======
       // @TODO : ChatRoomItem 정보 가져옴
        getChatRoomData();

//       adapter.addItem(new ChatRoomItem("객체지향프로그래밍 (영어A강의)", "", "오늘 수업한 내용 중 질문 있습니다."));
//       adapter.addItem(new ChatRoomItem("컴퓨터구조 (영어A강의)", "", "다음 주 까지 ..."));
       recyclerView.setAdapter(adapter);

       materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
       btnChatRoomAdd = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
       btnChatRoomAdd.setOnClickListener(new View.OnClickListener() {
>>>>>>> 3ec4af85b90387f647552e9a3ae77f8bbc126d53
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, ChatCreateActivity.class);
                startActivityForResult(intent, CREATE_REQUEST);
//                mFirebaseAuth.signOut();
//                Log.d("ChatListActivity", "SignOut");
//                checkLogin();
            }
        });

        btnSearch = (Button) findViewById(R.id.btn_chatList_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, ChatRoomSearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin() {
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

<<<<<<< HEAD
        } else {
            Log.d("ChatListActivity", mFirebaseUser.getUid());
=======
        }else{
//            Log.d("ChatListActivity", mFirebaseUser.getUid());
>>>>>>> 3ec4af85b90387f647552e9a3ae77f8bbc126d53
        }
    }

    private void getChatRoomData() {
        adapter = new ChatRoomAdapter(this);

        mRef = mFirebaseDatabase.getReference("User").child(mFirebaseUser.getUid() + "/chatRoomList/");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot chatroomlistSnapshot : dataSnapshot.getChildren()) {
                    String roomKey = chatroomlistSnapshot.getKey();

                    DatabaseReference ref = mFirebaseDatabase.getReference("Room").child(roomKey);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String roomTitle, roomImageUrl;

                            roomTitle = (String) dataSnapshot.child("roomName").getValue();
                            roomImageUrl = (String) dataSnapshot.child("roomImage").getValue();

                            if (roomTitle != null) {
                                ChatRoomItem item = new ChatRoomItem("", "", "");
                                item.setRoomTitle(roomTitle);
                                item.setRoomImgUrl(roomImageUrl);

                                Log.d(TAG, "AddItem");
                                adapter.addItem(item);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (resultCode == CREATE_REQUEST) {
                getChatRoomData();
            }
        }
    }
}
