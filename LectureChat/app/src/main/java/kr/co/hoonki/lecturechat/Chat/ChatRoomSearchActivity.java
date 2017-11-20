package kr.co.hoonki.lecturechat.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.co.hoonki.lecturechat.R;

public class ChatRoomSearchActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private RecyclerView recyclerView;
    private ChatSearchRoomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText edtTitle;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_search);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.rv_chatSearchList_chatList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        edtTitle = (EditText) findViewById(R.id.et_chatSearchRoomList);
        btnSearch = (Button) findViewById(R.id.btn_chatSearchRoomList_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtTitle.getText().toString();

                if (keyword.equals("")) return;

                getSearchRoomData(keyword);
            }
        });
    }

    public void getSearchRoomData(String keyword) {
        adapter = new ChatSearchRoomAdapter(this);
        recyclerView.setAdapter(adapter);

//        adapter.addItem(new ChatSearchRoomItem("검색 채팅방1", "", "2017/11/11"));
//        adapter.addItem(new ChatSearchRoomItem("검색 채팅방2", "", "2017/11/11"));

        adapter.notifyDataSetChanged();
    }
}
