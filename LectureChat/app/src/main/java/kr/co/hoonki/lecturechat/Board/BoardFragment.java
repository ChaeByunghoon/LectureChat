package kr.co.hoonki.lecturechat.Board;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hoonki.lecturechat.Model.BoardData;
import kr.co.hoonki.lecturechat.Model.BoardDataViewDTO;
import kr.co.hoonki.lecturechat.R;

public class BoardFragment extends Fragment {

    @BindView(R.id.rv_boardFragment_recyclerView)
    RecyclerView recyclerView;
    private BoardAdapter boardAdapter;
    private DatabaseReference databaseRef;
    String roomId;

    public BoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        ButterKnife.bind(this,view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        roomId = getArguments().getString("roomKey");
        boardAdapter = new BoardAdapter(new ArrayList<BoardDataViewDTO>(),getActivity(),roomId);
        recyclerView.setAdapter(boardAdapter);
        getData(roomId);
        return view;
    }
    public void getData(final String roomkey){
        databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRef.child("boardData").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardData boardData = dataSnapshot.getValue(BoardData.class);
                if (boardData.getRoomId().equals(roomkey)){
                    BoardDataViewDTO boardDataViewDTO = new BoardDataViewDTO(boardData,dataSnapshot.getKey());
                    boardAdapter.addItem(boardDataViewDTO);
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

    @OnClick(R.id.btn_board_add)
    public void addClick(){
        Intent intent = new Intent(getActivity(),QuestionActivity.class);
        Log.e("roomId",roomId);
        intent.putExtra("roomId",roomId);
        startActivity(intent);
    }


}
