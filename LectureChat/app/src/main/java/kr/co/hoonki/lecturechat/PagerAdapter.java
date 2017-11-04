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

    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChatFragment();
            case 1:
                return new BoardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
