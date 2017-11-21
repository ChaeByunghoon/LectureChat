package kr.co.hoonki.lecturechat;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatBoardActivity extends AppCompatActivity {

    PagerAdapter pagerAdapter;
    PagerSlidingTabStrip tabs;
    ViewPager viewPager;
    String roomKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_board);
        ButterKnife.bind(this);
        viewPager = findViewById(R.id.pager_chatBoard_viewPager);
        //roomKey = getIntent().getStringExtra("roomUid");
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),roomKey);
        viewPager.setAdapter(pagerAdapter);

        tabs = findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
    }

//    @OnClick(R.id.btn_chatBoard_chat)
//    public void chatClick(){
//        viewPager.setCurrentItem(0);
//    }
//    @OnClick(R.id.btn_chatBoard_question)
//    public void questionClick(){
//        viewPager.setCurrentItem(1);
//    }
}
