package kr.co.hoonki.lecturechat;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.OnClick;

public class ChatBoardActivity extends AppCompatActivity {

    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_board);
        viewPager = findViewById(R.id.pager_chatBoard_viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
    }
    @OnClick(R.id.btn_chatBoard_chat)
    public void chatClick(){
        viewPager.setCurrentItem(0);
    }
    @OnClick(R.id.btn_chatBoard_question)
    public void questionClick(){
        viewPager.setCurrentItem(1);
    }
}
