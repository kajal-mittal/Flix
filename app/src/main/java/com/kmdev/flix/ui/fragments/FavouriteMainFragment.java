package com.kmdev.flix.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brsoftech.core_utils.base.BaseSupportFragment;
import com.kmdev.flix.R;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.ui.adapters.FavouriteMovieAdapter;
import com.kmdev.flix.ui.adapters.HomeAdapter;
import com.kmdev.flix.utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kajal on 10/23/2016.
 */
public class FavouriteMainFragment extends BaseSupportFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    //  private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String TAG = "FavouriteMainFragment";
    private RecyclerView mRecyclerViewFav;
    private List<ResponseMovieDetails> mMovieDetailsList;
    private FavouriteMovieAdapter mFavouriteMovieAdapter;
    private TextView mTvNoFavAvail, mTvNoInternet;
    private DataBaseHelper mDataBase;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mListFragments;
    private List<String> mTitleList;

    public static FavouriteMainFragment newInstance() {

        Bundle args = new Bundle();
        FavouriteMainFragment fragment = new FavouriteMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_movie, container, false);
        bindViewsById(view);
        init();
        tabSelection();
        return view;
    }


    private void init() {
        mTitleList = new ArrayList<>();
        mListFragments = new ArrayList<Fragment>();

    }

    private void tabSelection() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.movies));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tv_shows));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.peoples));

        mTitleList.add(getResources().getString(R.string.movies));
        mTitleList.add(getResources().getString(R.string.tv_shows));
        mTitleList.add(getResources().getString(R.string.peoples));

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        FavouriteFragment moviesFragment = FavouriteFragment.newInstance(FavouriteFragment.ARG_MOVIES);
        FavouriteFragment tvShowsFragment = FavouriteFragment.newInstance(FavouriteFragment.ARG_TV_SHOWS);
        FavouriteFragment peoplesFragment = FavouriteFragment.newInstance(FavouriteFragment.ARG_PEOPLES);

        mListFragments.add(moviesFragment);
        mListFragments.add(tvShowsFragment);
        mListFragments.add(peoplesFragment);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //  super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    private void bindViewsById(View view) {
        //  mRecyclerViewFav = (RecyclerView) view.findViewById(R.id.reccycler_fav);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        //  mTvNoFavAvail = (TextView) view.findViewById(R.id.tv_no_fav_available);
        //  mTvNoInternet = (TextView) view.findViewById(R.id.tv_no_internet_available);
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

    @Override
    public void onClick(View v) {

    }
}
