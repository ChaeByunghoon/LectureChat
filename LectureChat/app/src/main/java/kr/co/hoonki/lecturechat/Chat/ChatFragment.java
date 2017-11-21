package kr.co.hoonki.lecturechat.Chat;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.hoonki.lecturechat.LoginActivity;
import kr.co.hoonki.lecturechat.Model.ChatData;
import kr.co.hoonki.lecturechat.Model.ChatDataViewDTO;
import kr.co.hoonki.lecturechat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {


    private String roomKey;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mRef;
    private FirebaseDatabase mFirebaseDatabase;
    private ChatAdapter chatAdapter;
    @BindView(R.id.rv_chat_chats)
    RecyclerView recyclerView;
    @BindView(R.id.edt_chat_input)
    EditText edt_input;
    @BindView(R.id.btn_chat_input)
    Button btn_input;

    public ChatFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        this.roomKey = getArguments().getString("roomKey");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatAdapter = new ChatAdapter(new ArrayList<ChatDataViewDTO>(),getActivity());
        recyclerView.setAdapter(chatAdapter);
        setRoomKey(roomKey);
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_input.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                ChatData chatData = new ChatData(mFirebaseUser.getUid(),mFirebaseUser.getDisplayName(),edt_input.getText().toString(),simpleDateFormat.format(new Date()));
                mRef = mFirebaseDatabase.getReference("Room").child(roomKey).child("chats");
                mRef.push().setValue(chatData);
                edt_input.setText("");
            }
        });
        return view;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        checkLogin();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Room").child(roomKey).child("chats");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                ChatDataViewDTO chatDataViewDTO = new ChatDataViewDTO(chatData);
                if (chatData==null){
                    Log.e("채팅애드","chat data is null");
                    return;
                }
                if (chatData.getUserId().equals(mFirebaseUser.getUid())){
                    chatDataViewDTO.setMine(true);
                }
                chatAdapter.addItem(chatDataViewDTO);
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

    private void checkLogin() {
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
