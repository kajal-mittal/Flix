package com.kmdev.flix.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brsoftech.core_utils.base.BaseAppCompatActivity;
import com.brsoftech.core_utils.utils.ItemClickSupport;
import com.google.gson.Gson;
import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiHitListener;
import com.kmdev.flix.RestClient.ApiIds;
import com.kmdev.flix.RestClient.ApiUrls;
import com.kmdev.flix.RestClient.ConnectionDetector;
import com.kmdev.flix.RestClient.RestClient;
import com.kmdev.flix.models.DataBaseEventUpdateModel;
import com.kmdev.flix.models.DataBaseMovieDetails;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponsePeople;
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.kmdev.flix.models.ResponsePersonMovie;
import com.kmdev.flix.ui.adapters.PersonMovieCreditAdapter;
import com.kmdev.flix.ui.fragments.ItemListFragment;
import com.kmdev.flix.utils.Constants;
import com.kmdev.flix.utils.DataBaseHelper;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class PeopleDetailsActivity extends BaseAppCompatActivity implements View.OnClickListener, ApiHitListener {
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private TextView mTvTitleToolbar, mTvBiography, mTvLoadingKnown, mTvTitle, mTvPopularity, mTvKnown, mTvnoBiography;
    private ResponsePeopleDetails mResponsePeopleDetails;
    private ResponsePeople mResponsePeople;
    private ProgressBar mProgressBarKnown;
    private RecyclerView mRecyclerKnownFor;
    // private PeopleAdapter mPeopleAdapter;
    private ImageView mImageBackPic;
    private String mImageUrl, mTitle;
    private RestClient mRestClient;
    private List<ResponsePersonMovie.CastBean> mCastBeanList;
    private PersonMovieCreditAdapter mPersonMovieCreditAdapter;
    private FloatingActionButton mFabFavourite;
    private boolean mIsNotFav = true;
    private String mType;
    private DataBaseHelper mDatabase;
    private String mId;
    private boolean mIsLoadingPeopleCredits;
    private ResponsePersonMovie mResponsePersonMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        bindViewsByID();
        init();
    }

    private void bindViewsByID() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        mTvBiography = (TextView) findViewById(R.id.tv_biography);
        mTvLoadingKnown = (TextView) findViewById(R.id.tv_loading_known);
        mRecyclerKnownFor = (RecyclerView) findViewById(R.id.recycler_known);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvPopularity = (TextView) findViewById(R.id.tv_popularity);
        mImageBackPic = (ImageView) findViewById(R.id.imageMovieBack);
        mProgressBarKnown = (ProgressBar) findViewById(R.id.progress_bar_known);
        mTvLoadingKnown = (TextView) findViewById(R.id.tv_loading_known);
        mTvKnown = (TextView) findViewById(R.id.tv_known);
        mTvnoBiography = (TextView) findViewById(R.id.tv_no_biography);
        mFabFavourite = (FloatingActionButton) findViewById(R.id.fab_favorite);
        mImageBackPic.setOnClickListener(this);
        mFabFavourite.setOnClickListener(this);
        mDatabase = new DataBaseHelper(this);


    }

    private void init() {
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //hide  title from toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String peopleDetails = getIntent().getStringExtra(Constants.TYPE_PEOPLE_DETAILS);
        mType = getIntent().getStringExtra(ItemListFragment.ARG_TYPE);
        boolean isComeFromFavourites = getIntent().getBooleanExtra(Constants.TYPE_IS_FAVOURITE, false);

        mRestClient = new RestClient(this);
        mCastBeanList = new ArrayList<>();
        //get parcelable data
        if (!TextUtils.isEmpty(peopleDetails)) {
            mResponsePeopleDetails = new Gson().fromJson(peopleDetails, ResponsePeopleDetails.class);
            if (!TextUtils.isEmpty(mResponsePeopleDetails.getBiography())) {
                mTvnoBiography.setVisibility(View.GONE);
                mTvBiography.setText(mResponsePeopleDetails.getBiography());
            } else {
                mTvBiography.setVisibility(View.GONE);
                mTvnoBiography.setVisibility(View.VISIBLE);
                mTvnoBiography.setText(getString(R.string.no_biography_available));
            }
            mId = String.valueOf(mResponsePeopleDetails.getId());
            mTvTitle.setText(mResponsePeopleDetails.getName());
            mTvPopularity.setText(mResponsePeopleDetails.getPopularityInt() + "%");
            Picasso.with(getApplicationContext())
                    .load(ApiUrls.IMAGE_PATH_VERY_HIGH + mResponsePeopleDetails.getProfile_path())
                    .into(mImageBackPic);
            mImageUrl = mResponsePeopleDetails.getProfile_path();
            mTitle = mResponsePeopleDetails.getName();
            if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
                mProgressBarKnown.setVisibility(View.VISIBLE);
                mTvLoadingKnown.setVisibility(View.VISIBLE);
                mTvKnown.setVisibility(View.GONE);
                mIsLoadingPeopleCredits = true;
                mRestClient.callback(this)
                        .getPeopleMovieCredits(mResponsePeopleDetails.getId());

            } else {
                mProgressBarKnown.setVisibility(View.GONE);
                mTvLoadingKnown.setVisibility(View.GONE);
                mRecyclerKnownFor.setVisibility(View.GONE);
                mTvKnown.setVisibility(View.VISIBLE);
                mTvKnown.setText(getString(R.string.internet_connection));
            }
        }
        mPersonMovieCreditAdapter = new PersonMovieCreditAdapter(mCastBeanList);
        mRecyclerKnownFor.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerKnownFor.setAdapter(mPersonMovieCreditAdapter);
        mRecyclerKnownFor.setNestedScrollingEnabled(false);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    showToolbarContents();
                    isShow = true;
                } else if (isShow) {
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    isShow = false;
                    hideToolbarContents();
                }
            }
        });
        ItemClickSupport.addTo(mRecyclerKnownFor)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        callMovieDetails(position);

                    }
                });

        if (isComeFromFavourites) {
            checkPeopleExistIntofavourites();
            mIsLoadingPeopleCredits = false;
            mIsNotFav = false;
            if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
                DataBaseMovieDetails databasePeopleDetails = mDatabase.getPeopleDetails(Integer.parseInt(mId));
                ResponsePersonMovie responsePersonMovie = databasePeopleDetails.getResponsePersonMovie();
                if (responsePersonMovie != null) {
                    List<ResponsePersonMovie.CastBean> castBeanList = responsePersonMovie.getCast();
                    if (castBeanList.size() > 0) {
                        mCastBeanList.clear();
                        mCastBeanList.addAll(castBeanList);
                        if (mPersonMovieCreditAdapter != null) {
                            mPersonMovieCreditAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvKnown.setVisibility(View.VISIBLE);
                        mRecyclerKnownFor.setVisibility(View.GONE);
                    }
                }

            }
        }
    }

    private void callMovieDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getMovieDetails(String.valueOf(mCastBeanList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }

    }

    private void showToolbarContents() {
        mTvTitleToolbar.setVisibility(View.VISIBLE);
        mTvTitleToolbar.setText(mResponsePeopleDetails.getName());


    }

    private void hideToolbarContents() {
        mTvTitleToolbar.setText(mResponsePeopleDetails.getName());
        mTvTitleToolbar.setVisibility(View.GONE);


    }

    private void callImageFullScreen() {
        Intent intent = new Intent(PeopleDetailsActivity.this, MovieImageFullScreenActivity.class);
        intent.putExtra(Constants.FULL_IMAGE_URL, mImageUrl);
        intent.putExtra(Constants.MOVIE_TITLE, mTitle);

        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void checkPeopleExistIntofavourites() {
        boolean checkFav = mDatabase.checkIsPeopleExist(mId);
        if (checkFav) {
            mFabFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
            mIsNotFav = false;
        } else {
            mFabFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            mIsNotFav = true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_favorite:
                if (!mIsLoadingPeopleCredits) {
                    if (mIsNotFav) {
                        addToFavourites();
                        mFabFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                        // mDatabase.getPeopleCount();
                        mIsNotFav = false;
                        EventBus.getDefault().post(new DataBaseEventUpdateModel());

                    } else {
                        removeFromFavourites();
                        mFabFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        EventBus.getDefault().post(new DataBaseEventUpdateModel());
                        mIsNotFav = true;
                    }
                } else {
                    String msg = null;
                    msg = getString(R.string.wait_data_loaded, "peoples");
                    displayShortToast(msg);
                }
                break;
            case R.id.imageMovieBack:
                callImageFullScreen();
                break;

        }
    }

    private void removeFromFavourites() {
        if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
            displayShortToast(R.string.remove_to_people_favourites);
        }
        mDatabase.deletePerson(mId);
    }

    private void addToFavourites() {
        DataBaseMovieDetails dataBaseMovieDetails = new DataBaseMovieDetails();
        if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
            displayShortToast(R.string.add_to_people_favourites);
            dataBaseMovieDetails.setResponsePeopleDetails(mResponsePeopleDetails);
            dataBaseMovieDetails.setId(mResponsePeopleDetails.getId());
            dataBaseMovieDetails.setResponsePersonMovie(mResponsePersonMovies);
            mDatabase.addPeople(dataBaseMovieDetails);

        }

    }

    @Override
    public void onSuccessResponse(int apiId, Object response) {
        dismissLoadingDialog();
        mProgressBarKnown.setVisibility(View.GONE);
        mTvLoadingKnown.setVisibility(View.GONE);
        if (apiId == ApiIds.ID_PEOPLE_MOVIE_CREDITS) {
            mResponsePersonMovies = (ResponsePersonMovie) response;
            mRecyclerKnownFor.setVisibility(View.VISIBLE);
            if (mResponsePersonMovies != null) {
                List<ResponsePersonMovie.CastBean> castBeanList = mResponsePersonMovies.getCast();
                mIsLoadingPeopleCredits = false;
                if (castBeanList.size() > 0) {
                    mTvKnown.setVisibility(View.GONE);
                    mRecyclerKnownFor.setVisibility(View.VISIBLE);
                    mCastBeanList.clear();
                    mCastBeanList.addAll(castBeanList);
                    if (mPersonMovieCreditAdapter != null) {
                        mPersonMovieCreditAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTvKnown.setVisibility(View.VISIBLE);
                    mRecyclerKnownFor.setVisibility(View.GONE);
                    mTvKnown.setText(getString(R.string.no_known_available));

                }
             /*   mPersonMovieCreditAdapter = new PersonMovieCreditAdapter(mCastBeanList);
                mRecyclerKnownFor.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true));
                mRecyclerKnownFor.setAdapter(mPersonMovieCreditAdapter);
*/
            }
        } else if (apiId == ApiIds.ID_MOVIE_DETAILS) {
            ResponseMovieDetails responseMovieDetails = (ResponseMovieDetails) response;
            String res = new Gson().toJson(responseMovieDetails);
            if (responseMovieDetails != null) {

                Intent movieDetailIntent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                movieDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_MOVIES);
                movieDetailIntent.putExtra(Constants.TYPE_MOVIE_DETAILS, res);
                startActivity(movieDetailIntent);
            }
        }
    }

    @Override
    public void onFailResponse(int apiId, String error) {
        mProgressBarKnown.setVisibility(View.GONE);
        mTvLoadingKnown.setVisibility(View.GONE);

    }

    @Override
    public void networkNotAvailable() {
        mTvLoadingKnown.setText(R.string.internet_connection);

    }
}
