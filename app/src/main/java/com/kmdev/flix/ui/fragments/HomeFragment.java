package com.kmdev.flix.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brsoftech.core_utils.base.BaseSupportFragment;
import com.kmdev.flix.R;
import com.kmdev.flix.ui.adapters.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kajal on 1/15/2017.
 */
public class HomeFragment extends BaseSupportFragment implements TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mListFragments;
    private Toolbar mToolBar;
    private List<String> mTitleList;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        tabSelection();
        return view;
    }

    private void init(View view) {
        mTitleList = new ArrayList<>();
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mListFragments = new ArrayList<Fragment>();

    }

    private void tabSelection() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.movies));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tv_shows));

        mTitleList.add(getResources().getString(R.string.movies));
        mTitleList.add(getResources().getString(R.string.tv_shows));

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ItemListFragment moviesFragment = ItemListFragment.newInstance(ItemListFragment.ARG_MOVIES);
        ItemListFragment tvShowsFragment = ItemListFragment.newInstance(ItemListFragment.ARG_TV_SHOWS);
        // FavouriteMainFragment favouriteMovieFragment = FavouriteMainFragment.newInstance();

        mListFragments.add(moviesFragment);
        mListFragments.add(tvShowsFragment);
        // mListFragments.add(favouriteMovieFragment);
        final HomeAdapter adapter = new HomeAdapter
                (getChildFragmentManager(), mListFragments, mTitleList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Bold.ttf");
        ViewGroup vg = (ViewGroup) mTabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mViewPager.setOffscreenPageLimit(2);

            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), true);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
