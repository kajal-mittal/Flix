package com.kmdev.flix.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kajal on 10/2/2016.
 */

public class HomeAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragments = new ArrayList<>();
    List<String> fragmentTitleList = new ArrayList<>();

    public HomeAdapter(FragmentManager fm, ArrayList<Fragment> fragments, List<String> titleList) {
        super(fm);
        this.fragments = fragments;
        fragmentTitleList = titleList;


    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

    }


    @Override
    public int getCount() {
        return fragments.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}