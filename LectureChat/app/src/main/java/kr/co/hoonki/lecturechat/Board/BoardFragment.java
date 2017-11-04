package kr.co.hoonki.lecturechat.Board;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.hoonki.lecturechat.R;

public class BoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private BoardAdapter boardAdapter;

    public BoardFragment() {
        // Required empty public constructor
    }
    public static BoardFragment newInstance(){

        Bundle args = new Bundle();

        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        recyclerView = view.findViewById(R.id.rv_boardFragment_recyclerView);

        


        return view;
    }

}
