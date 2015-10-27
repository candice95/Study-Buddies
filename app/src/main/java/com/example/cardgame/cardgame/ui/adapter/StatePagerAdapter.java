package com.example.cardgame.cardgame.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.cardgame.cardgame.ui.fragment.FirstFragment;
import com.example.cardgame.cardgame.ui.fragment.SecondFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Syuman on 10/26/15.
 */
public class StatePagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGES = 2;
    private List<String> titles;

    public StatePagerAdapter(FragmentManager fm) {
        super(fm);
        titles = Arrays.asList("Home", "Appointments");
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FirstFragment();
        } else {
            return new SecondFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
