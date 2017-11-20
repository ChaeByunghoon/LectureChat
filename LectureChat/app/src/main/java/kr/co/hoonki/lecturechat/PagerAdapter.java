package kr.co.hoonki.lecturechat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kr.co.hoonki.lecturechat.Board.BoardFragment;
import kr.co.hoonki.lecturechat.Chat.ChatFragment;

/**
 * Created by chaebyeonghun on 2017. 11. 4..
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 2;
    private String roomKey;
    private ChatFragment chatFragment;
    private BoardFragment boardFragment;

    public PagerAdapter(FragmentManager fm,String roomKey){
        super(fm);
        this.roomKey = roomKey;
        chatFragment = new ChatFragment();
        boardFragment = new BoardFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                //chatFragment.setRoomKey(roomKey);
                return chatFragment;
            case 1:
                return boardFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
