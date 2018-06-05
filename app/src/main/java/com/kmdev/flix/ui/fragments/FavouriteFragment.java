package com.kmdev.flix.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brsoftech.core_utils.base.BaseSupportFragment;
import com.brsoftech.core_utils.utils.ItemClickSupport;
import com.google.gson.Gson;
import com.kmdev.flix.R;
import com.kmdev.flix.models.DataBaseEventUpdateModel;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.kmdev.flix.models.ResponseTvDetails;
import com.kmdev.flix.ui.activities.MovieDetailsActivity;
import com.kmdev.flix.ui.activities.PeopleDetailsActivity;
import com.kmdev.flix.ui.adapters.FavouriteMovieAdapter;
import com.kmdev.flix.ui.adapters.FavouritePeopleAdapter;
import com.kmdev.flix.ui.adapters.FavouriteTvAdapter;
import com.kmdev.flix.utils.Constants;
import com.kmdev.flix.utils.DataBaseHelper;
import com.kmdev.flix.utils.ItemOffsetDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kajal on 1/2/2017.
 */
public class FavouriteFragment extends BaseSupportFragment {
    public static final String ARG_MOVIES = "movies";
    public static final String ARG_TV_SHOWS = "tv_shows";
    public static final String ARG_PEOPLES = "people";
    private static final String ARG_TYPE = "type";
    private static String mType;
    private RecyclerView mRecyclerViewFav;
    private List<ResponseMovieDetails> mMovieDetailsList;
    private List<ResponseTvDetails> mResponseTvDetailsList;
    private List<ResponsePeopleDetails> mResponsePeopleDetailsList;
    private FavouriteMovieAdapter mFavouriteMovieAdapter;
    private FavouriteTvAdapter mFavouriteTvAdapter;
    private TextView mTvNoFavAvail, mTvNoInternet;
    private DataBaseHelper mDataBase;
    private FavouritePeopleAdapter mFavouritePeopleAdapter;

    public static FavouriteFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        FavouriteFragment fragment = new FavouriteFragment();
        fragment.setArguments(args);
        mType = type;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmnet_favourite, container, false);
        bindViewsById(view);
        init();

        return view;
    }

    private void bindViewsById(View view) {
        mRecyclerViewFav = (RecyclerView) view.findViewById(R.id.reccycler_fav);
        mTvNoFavAvail = (TextView) view.findViewById(R.id.tv_no_fav_available);
        mTvNoInternet = (TextView) view.findViewById(R.id.tv_no_internet_available);
    }

    private void init() {
        mMovieDetailsList = new ArrayList<>();
        mResponseTvDetailsList = new ArrayList<>();
        mResponsePeopleDetailsList = new ArrayList<>();

        mDataBase = new DataBaseHelper(getActivity());


        //set favourite adapter to recycler
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        mRecyclerViewFav.addItemDecoration(itemDecoration);
        if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_MOVIES)) {
            callToGetFavouriteMovies();
        } else if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_TV_SHOWS)) {
            callToGetFavouriteTvShows();
        } else if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_PEOPLES)) {
            callToGetFavouritePeoples();
        }


    }

    private void callToGetFavouritePeoples() {
        //initialize database & get movies
        List<ResponsePeopleDetails> responsePeopleDetailsList = mDataBase.getAllPeoples();
        mFavouritePeopleAdapter = new FavouritePeopleAdapter(responsePeopleDetailsList);
        mRecyclerViewFav.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerViewFav.setAdapter(mFavouritePeopleAdapter);

        if (responsePeopleDetailsList.size() > 0) {
            mResponsePeopleDetailsList.clear();
            mTvNoFavAvail.setVisibility(View.GONE);
            mResponsePeopleDetailsList.addAll(responsePeopleDetailsList);
            if (mFavouritePeopleAdapter != null) {
                mFavouritePeopleAdapter.notifyDataSetChanged();
            }
        } else {
            mResponsePeopleDetailsList.clear();
            if (mFavouritePeopleAdapter != null) {
                mFavouritePeopleAdapter.notifyDataSetChanged();
            }
            mTvNoFavAvail.setVisibility(View.VISIBLE);

        }
        ItemClickSupport.addTo(mRecyclerViewFav)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        mType = ItemListFragment.ARG_PEOPLE;
                        callMovieDetails(position);
                    }
                });
    }


    private void callToGetFavouriteTvShows() {
        //initialize database & get movies
        List<ResponseTvDetails> responseMovieDetailsList = mDataBase.getAllTvShows();
        mFavouriteTvAdapter = new FavouriteTvAdapter(mResponseTvDetailsList);
        mRecyclerViewFav.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerViewFav.setAdapter(mFavouriteTvAdapter);

        if (responseMovieDetailsList.size() > 0) {
            mResponseTvDetailsList.clear();
            mTvNoFavAvail.setVisibility(View.GONE);
            mResponseTvDetailsList.addAll(responseMovieDetailsList);
            if (mFavouriteTvAdapter != null) {
                mFavouriteTvAdapter.notifyDataSetChanged();
            }
        } else {
            mResponseTvDetailsList.clear();
            if (mFavouriteTvAdapter != null) {
                mFavouriteTvAdapter.notifyDataSetChanged();
            }
            mTvNoFavAvail.setVisibility(View.VISIBLE);

        }
        ItemClickSupport.addTo(mRecyclerViewFav)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        mType = ItemListFragment.ARG_TV_SHOWS;
                        callMovieDetails(position);
                    }
                });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDatabaseUpdateEvent(DataBaseEventUpdateModel dataBaseEventUpdateModel) {
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            callToGetFavouriteMovies();
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            callToGetFavouriteTvShows();
        } else if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_PEOPLES)) {
            callToGetFavouritePeoples();
        }
    }

    public void callToGetFavouriteMovies() {

        //initialize database & get movies
        List<ResponseMovieDetails> responseMovieDetailsList = mDataBase.getAllMovies();
        mFavouriteMovieAdapter = new FavouriteMovieAdapter(mMovieDetailsList);
        mRecyclerViewFav.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerViewFav.setAdapter(mFavouriteMovieAdapter);

        if (responseMovieDetailsList.size() > 0) {
            mMovieDetailsList.clear();
            mTvNoFavAvail.setVisibility(View.GONE);
            mMovieDetailsList.addAll(responseMovieDetailsList);
            if (mFavouriteMovieAdapter != null) {
                mFavouriteMovieAdapter.notifyDataSetChanged();
            }
        } else {
            mMovieDetailsList.clear();
            if (mFavouriteMovieAdapter != null) {
                mFavouriteMovieAdapter.notifyDataSetChanged();
            }
            mTvNoFavAvail.setVisibility(View.VISIBLE);

        }
        ItemClickSupport.addTo(mRecyclerViewFav)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        mType = ItemListFragment.ARG_MOVIES;
                        callMovieDetails(position);
                    }
                });
    }

    private void callMovieDetails(int position) {
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            ResponseMovieDetails responseMovieDetails = mMovieDetailsList.get(position);
            displayLoadingDialog(false);
            String res = new Gson().toJson(responseMovieDetails);
            if (res != null) {
                dismissLoadingDialog();
                Intent movieDetailIntent = new Intent(getActivity(), MovieDetailsActivity.class);
                movieDetailIntent.putExtra(Constants.TYPE_MOVIE_DETAILS, res);
                movieDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_MOVIES);

                movieDetailIntent.putExtra(Constants.TYPE_IS_FAVOURITE, true);
                startActivity(movieDetailIntent);
            }
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            ResponseTvDetails responseTvDetails = mResponseTvDetailsList.get(position);
            displayLoadingDialog(false);
            String res = new Gson().toJson(responseTvDetails);
            if (res != null) {
                dismissLoadingDialog();
                Intent movieDetailIntent = new Intent(getActivity(), MovieDetailsActivity.class);
                movieDetailIntent.putExtra(Constants.TYPE_TV_SHOW_DETAILS, res);
                movieDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_TV_SHOWS);
                movieDetailIntent.putExtra(Constants.TYPE_IS_FAVOURITE, true);
                startActivity(movieDetailIntent);
            }

        } else if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
            ResponsePeopleDetails responsePeopleDetails = mResponsePeopleDetailsList.get(position);
            displayLoadingDialog(false);
            String res = new Gson().toJson(responsePeopleDetails);
            if (res != null) {
                dismissLoadingDialog();
                Intent movieDetailIntent = new Intent(getActivity(), PeopleDetailsActivity.class);
                movieDetailIntent.putExtra(Constants.TYPE_PEOPLE_DETAILS, res);
                movieDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_PEOPLE);
                movieDetailIntent.putExtra(Constants.TYPE_IS_FAVOURITE, true);
                startActivity(movieDetailIntent);

            }
        }
    }

}
