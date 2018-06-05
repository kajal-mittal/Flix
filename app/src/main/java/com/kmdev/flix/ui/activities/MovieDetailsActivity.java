package com.kmdev.flix.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
import com.kmdev.flix.models.ResponseCastMovies;
import com.kmdev.flix.models.ResponseCastTVShows;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponseMovieReview;
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.kmdev.flix.models.ResponsePopularMovie;
import com.kmdev.flix.models.ResponseRecommendations;
import com.kmdev.flix.models.ResponseTvDetails;
import com.kmdev.flix.models.ResponseTvPopular;
import com.kmdev.flix.models.ResponseVideo;
import com.kmdev.flix.ui.adapters.CastMovieAdapter;
import com.kmdev.flix.ui.adapters.CastShowsAdapter;
import com.kmdev.flix.ui.adapters.ReviewMovieAdapter;
import com.kmdev.flix.ui.adapters.SimilarMoviesAdapter;
import com.kmdev.flix.ui.adapters.SimilarShowAdapter;
import com.kmdev.flix.ui.adapters.VideoMovieAdapter;
import com.kmdev.flix.ui.fragments.ItemListFragment;
import com.kmdev.flix.utils.Constants;
import com.kmdev.flix.utils.DataBaseHelper;
import com.kmdev.flix.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MovieDetailsActivity extends BaseAppCompatActivity implements ApiHitListener,
        View.OnClickListener {
    ResponseMovieDetails mResponseMovieDetails;
    private RestClient mRestClient;
    private Toolbar mToolbar;
    private ImageView mImageMovieBack;
    private TextView mTvDescription, mTvMovieTitle, mTvReleaseDate, mTvReviews, mTvVideos;
    private RecyclerView mRecyclerViewReview, mRecyclerViewVideos, mRecyclerSimilarMovies;
    private RecyclerView mRecyclerCast;
    private ReviewMovieAdapter mReviewAdapter;
    private VideoMovieAdapter mVideoMovieAdapter;
    private CastMovieAdapter mCastMovieAdapter;
    private List<ResponseMovieReview.ReviewBean> mReviewBeanList;
    private List<ResponseVideo.VideoBean> mVideoBeanList;
    private String mImageUrl;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private ProgressBar mProgressBar, mProgressBarVideo, mProgressBarReview, mProgressBarSimilarMovies;
    private TextView mTvLoadingVideo, mTvLoadReview, mTvLoadDetails;
    private FloatingActionButton mFabFavourite;
    private boolean mIsNotFav;
    private DataBaseHelper mDatabase;
    private String mId;
    private String mTitle, mType;
    private ResponseMovieReview mResponseMovieReview;
    private ResponseVideo mResponseMovieVideo;
    private boolean mIsLoadingReview = false;
    private boolean mIsLoadingSimilarMovies = false;
    private boolean mIsLoadingTrailers = false;
    private TextView mTvTitleToolbar, mTvReview, mTvRating, mTvLoadingSimilarMovies, mTvSimilar;
    private ResponseTvDetails mResponseTvDetails;
    private ResponseRecommendations mResponseRecommendations;
    private FrameLayout mFrameReview;
    private FrameLayout mFrameCastTVShows;
    private FrameLayout mFrameCastMovies;
    private ResponsePopularMovie mResponseSimilarMovies;
    private ResponseTvPopular mResponseSimilarShows;
    private List<ResponsePopularMovie.PopularMovie> mSimilarMoviesList;
    private SimilarMoviesAdapter mSimilarMovieAdapter;
    private SimilarShowAdapter mSimilarShowAdapter;
    private List<ResponseTvPopular.ResultsBean> mSimilarShowsList;
    private TextView mTvSimilarTxt;
    private TextView mTvNoCast;
    private TextView mTvLoadingCast;
    private ProgressBar mProgressCast;
    private boolean mIsLoadingCasting;
    private List<ResponseCastMovies.CastBean> mMovieCastList;
    private TextView mTvLoadingCastTVShows;
    private RecyclerView mRecyclerCastShows;
    private ProgressBar mProgressCastShows;
    private CastShowsAdapter mCastShowsAdapter;
    private ArrayList<ResponseCastTVShows.CastBean> mShowCastList;
    private boolean mIsLoadingCastingShows;
    private ResponseCastTVShows mResponseCastShows;
    private ResponseCastMovies mResponseCastMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        bindViewsById();
        init(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void bindViewsById() {
        mRestClient = new RestClient(MovieDetailsActivity.this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageMovieBack = (ImageView) findViewById(R.id.imageMovieBack);
        mTvDescription = (TextView) findViewById(R.id.tv_description);
        mRecyclerViewReview = (RecyclerView) findViewById(R.id.recycler_review);
        mRecyclerSimilarMovies = (RecyclerView) findViewById(R.id.recycler_similar_movies);
        mRecyclerViewVideos = (RecyclerView) findViewById(R.id.recycler_videos);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mTvMovieTitle = (TextView) findViewById(R.id.tv_title);
        mTvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mTvReviews = (TextView) findViewById(R.id.tv_reviews);
        mTvSimilar = (TextView) findViewById(R.id.tv_similar_movies);
        mTvVideos = (TextView) findViewById(R.id.tv_videos);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBarVideo = (ProgressBar) findViewById(R.id.progress_bar_video);
        mProgressBarReview = (ProgressBar) findViewById(R.id.progress_bar_review);
        mProgressBarSimilarMovies = (ProgressBar) findViewById(R.id.progress_bar_movies);
        mTvLoadReview = (TextView) findViewById(R.id.tv_loading_review);
        mTvLoadingVideo = (TextView) findViewById(R.id.tv_loading_video);
        mTvLoadingSimilarMovies = (TextView) findViewById(R.id.tv_loading_movies);
        mTvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        mFabFavourite = (FloatingActionButton) findViewById(R.id.fab_favorite);
        mTvReview = (TextView) findViewById(R.id.tv_review);
        mFrameReview = (FrameLayout) findViewById(R.id.frame_review);
        mTvRating = (TextView) findViewById(R.id.tv_rating);
        mTvSimilarTxt = (TextView) findViewById(R.id.tv_similar);
        mRecyclerCast = (RecyclerView) findViewById(R.id.recycler_cast);
        mRecyclerCastShows = (RecyclerView) findViewById(R.id.recycler_cast_shows);
        mTvNoCast = (TextView) findViewById(R.id.tv_no_cast);
        mTvLoadingCast = (TextView) findViewById(R.id.tv_loading_cast);
        mTvLoadingCastTVShows = (TextView) findViewById(R.id.tv_loading_cast_shows);
        mProgressCast = (ProgressBar) findViewById(R.id.progress_bar_cast);
        mProgressCastShows = (ProgressBar) findViewById(R.id.progress_bar_cast_shows);
        mFrameCastMovies = (FrameLayout) findViewById(R.id.frame_cast);
        mFrameCastTVShows = (FrameLayout) findViewById(R.id.frame_cast_tvshows);
    }

    private void init(Bundle savedInstanceState) {
        mReviewBeanList = new ArrayList<>();
        mSimilarShowsList = new ArrayList<>();
        mDatabase = new DataBaseHelper(this);
        mVideoBeanList = new ArrayList<>();
        mSimilarMoviesList = new ArrayList<>();
        mMovieCastList = new ArrayList<>();
        mShowCastList = new ArrayList<>();
        mImageMovieBack.setOnClickListener(this);
        mFabFavourite.setOnClickListener(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //hide  title from toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //get parcelable data
        String movieDetails = getIntent().getStringExtra(Constants.TYPE_MOVIE_DETAILS);
        mType = getIntent().getStringExtra(ItemListFragment.ARG_TYPE);
        String tvDetails = getIntent().getStringExtra(Constants.TYPE_TV_SHOW_DETAILS);
        boolean isComeFromFavourites = getIntent().getBooleanExtra(Constants.TYPE_IS_FAVOURITE, false);
        if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            mTvReview.setVisibility(View.GONE);
            mTvSimilarTxt.setText(getString(R.string.similar_tv_shows));
            mRecyclerViewReview.setVisibility(View.GONE);
            mRecyclerCast.setVisibility(View.GONE);
            //  mRecyclerSimilarMovies.setVisibility(View.GONE);
            mFrameReview.setVisibility(View.GONE);
        }

        //set review adapter
        mRecyclerViewReview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mReviewAdapter = new ReviewMovieAdapter(mReviewBeanList);
        mRecyclerViewReview.setAdapter(mReviewAdapter);
        mRecyclerViewReview.setNestedScrollingEnabled(false);

        //set movie cast adapter
        mRecyclerCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mCastMovieAdapter = new CastMovieAdapter(mMovieCastList);
        mRecyclerCast.setAdapter(mCastMovieAdapter);
        mRecyclerCast.setNestedScrollingEnabled(false);

        mRecyclerCastShows.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mCastShowsAdapter = new CastShowsAdapter(mShowCastList);
        mRecyclerCastShows.setAdapter(mCastShowsAdapter);
        mRecyclerCastShows.setNestedScrollingEnabled(false);

        //set similar movies adapter
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            mTvSimilarTxt.setText(getString(R.string.similar_movies));

            mRecyclerSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mSimilarMovieAdapter = new SimilarMoviesAdapter(mSimilarMoviesList);
            mRecyclerSimilarMovies.setAdapter(mSimilarMovieAdapter);
            mRecyclerSimilarMovies.setNestedScrollingEnabled(false);
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            mRecyclerSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mSimilarShowAdapter = new SimilarShowAdapter(mSimilarShowsList);
            mRecyclerSimilarMovies.setAdapter(mSimilarShowAdapter);
            mRecyclerSimilarMovies.setNestedScrollingEnabled(false);

        }


        //set video adapter
        mRecyclerViewVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mVideoMovieAdapter = new VideoMovieAdapter(mVideoBeanList);
        mRecyclerViewVideos.setAdapter(mVideoMovieAdapter);
        mRecyclerViewVideos.setNestedScrollingEnabled(false);
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            if (movieDetails != null && savedInstanceState == null) {
                mResponseMovieDetails = new Gson().fromJson(movieDetails, ResponseMovieDetails.class);
                if (mResponseMovieDetails != null) {
                    mId = String.valueOf(mResponseMovieDetails.getId());
                    checkMovieExistIntofavourites();
                    mTvDescription.setText(mResponseMovieDetails.getOverview());
                    mTitle = mResponseMovieDetails.getTitle();
                    mImageUrl = mResponseMovieDetails.getPoster_path();
                    mTvRating.setText(String.valueOf(mResponseMovieDetails.getVote_average()));
                    Picasso.with(this)
                            .load(ApiUrls.IMAGE_PATH_VERY_HIGH + mResponseMovieDetails.getBackdrop_path())
                            .into(mImageMovieBack);
                    mTvMovieTitle.setText(mResponseMovieDetails.getOriginal_title());
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
                    try {
                        Date date = simpleDateFormat1.parse(mResponseMovieDetails.getRelease_date());
                        String releaseDate = simpleDateFormat.format(date);
                        Date formattedDate = simpleDateFormat.parse(releaseDate);
                        mTvReleaseDate.setText(simpleDateFormat.format(formattedDate));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        } else if (tvDetails != null && savedInstanceState == null
                && TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            mResponseTvDetails = new Gson().fromJson(tvDetails, ResponseTvDetails.class);
            if (mResponseTvDetails != null) {
                mId = String.valueOf(mResponseTvDetails.getId());
                checkMovieExistIntofavourites();
                mTvDescription.setText(mResponseTvDetails.getOverview());
                mTitle = mResponseTvDetails.getName();
                mImageUrl = mResponseTvDetails.getPoster_path();
                mTvRating.setText(String.valueOf(mResponseTvDetails.getVote_average()));

                Picasso.with(this)
                        .load(ApiUrls.IMAGE_PATH_HIGH + mResponseTvDetails.getBackdrop_path())
                        .into(mImageMovieBack);
                mTvMovieTitle.setText(mResponseTvDetails.getOriginal_name());
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");
                if (!TextUtils.isEmpty(mResponseTvDetails.getFirst_air_date())) {
                    try {
                        Date date = simpleDateFormat1.parse(mResponseTvDetails.getFirst_air_date());
                        String releaseDate = simpleDateFormat.format(date);
                        Date formattedDate = simpleDateFormat.parse(releaseDate);
                        mTvReleaseDate.setText(simpleDateFormat.format(formattedDate));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        //review click listener
        ItemClickSupport.addTo(mRecyclerViewReview)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        String mAuthor = mReviewBeanList.get(position).getAuthor();
                        String mContent = mReviewBeanList.get(position).getContent();
                        Utils.displayReviewDetails(MovieDetailsActivity.this, mAuthor, mContent);
                    }
                });
        //video click listener
        ItemClickSupport.addTo(mRecyclerSimilarMovies)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
                            callTvShowDetails(position);
                        } else {
                            callMovieDetails(position);

                        }
                    }
                });
        //set cast adpter
        ItemClickSupport.addTo(mRecyclerCast)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        callPeopleDetails(position);

                    }
                });

        //set cast adpter
        ItemClickSupport.addTo(mRecyclerCastShows)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        callPeopleDetailsShows(position);

                    }
                });
        //similar movie listener
        ItemClickSupport.addTo(mRecyclerViewVideos)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        String urlVideo = ApiUrls.VIDEO_PATH_YOUTUBE + mVideoBeanList.get(position).getKey();
                        Intent ytplay = new Intent(Intent.ACTION_VIEW, Uri.parse(urlVideo));
                        startActivity(ytplay);

                    }
                });

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


        //get reviews from database
        if (isComeFromFavourites) {
            if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
                DataBaseMovieDetails dataBaseMovieDetails = mDatabase.getMovieDetails(Integer.parseInt(mId));
                ResponseMovieReview responseMovieReview = dataBaseMovieDetails.getResponseMovieReview();
                ResponseCastMovies responseCastMovies = dataBaseMovieDetails.getResponseCastMovies();
                if (responseCastMovies != null) {
                    List<ResponseCastMovies.CastBean> castMoviesList = responseCastMovies.getCast();
                    if (castMoviesList.size() > 0) {
                        mMovieCastList.clear();
                        mMovieCastList.addAll(castMoviesList);
                        if (mCastMovieAdapter != null) {
                            mCastMovieAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mRecyclerCast.setVisibility(View.GONE);
                        mTvNoCast.setVisibility(View.VISIBLE);
                    }
                }
                if (responseMovieReview != null) {
                    List<ResponseMovieReview.ReviewBean> reviewBeanList = responseMovieReview.getReviewBean();
                    if (reviewBeanList.size() > 0) {
                        mReviewBeanList.clear();
                        mReviewBeanList.addAll(reviewBeanList);
                        if (mReviewAdapter != null) {
                            mReviewAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvReviews.setVisibility(View.VISIBLE);
                        mRecyclerViewReview.setVisibility(View.GONE);
                    }
                }
                //get videos from database
                ResponseVideo responseMovieVideo = dataBaseMovieDetails.getResponseMovieVideo();
                if (responseMovieVideo != null) {
                    List<ResponseVideo.VideoBean> videoBeanList = responseMovieVideo.getVideoBean();
                    if (videoBeanList.size() > 0) {
                        mVideoBeanList.clear();
                        mVideoBeanList.addAll(videoBeanList);
                        if (mVideoMovieAdapter != null) {
                            mVideoMovieAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvVideos.setVisibility(View.VISIBLE);
                        mRecyclerViewVideos.setVisibility(View.GONE);
                    }
                }
                //get similar movie from database
                ResponsePopularMovie responseSimilarMovies = dataBaseMovieDetails.getResponseSimilarMovies();
                if (responseSimilarMovies != null) {
                    List<ResponsePopularMovie.PopularMovie> similarMovieList = responseSimilarMovies.getPopularMovie();
                    if (similarMovieList.size() > 0) {
                        mSimilarMoviesList.clear();
                        mSimilarMoviesList.addAll(similarMovieList);
                        if (mSimilarMovieAdapter != null) {
                            mSimilarMovieAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvSimilar.setVisibility(View.VISIBLE);
                        mRecyclerSimilarMovies.setVisibility(View.GONE);
                    }
                }
            } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
                DataBaseMovieDetails dataBaseMovieDetails = mDatabase.getShowDetails(Integer.parseInt(mId));
                ResponseMovieReview responseMovieReview = dataBaseMovieDetails.getResponseMovieReview();
                ResponseCastTVShows responseCastTVShows = dataBaseMovieDetails.getResponseCastShows();
                if (responseCastTVShows != null) {
                    List<ResponseCastTVShows.CastBean> castShowsList = responseCastTVShows.getCast();
                    if (castShowsList.size() > 0) {
                        mShowCastList.clear();
                        mShowCastList.addAll(castShowsList);
                        if (mCastShowsAdapter != null) {
                            mCastShowsAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvNoCast.setVisibility(View.VISIBLE);
                        mRecyclerCastShows.setVisibility(View.GONE);
                    }
                }
                if (responseMovieReview != null) {
                    List<ResponseMovieReview.ReviewBean> reviewBeanList = responseMovieReview.getReviewBean();
                    if (reviewBeanList.size() > 0) {
                        mReviewBeanList.clear();
                        mReviewBeanList.addAll(reviewBeanList);
                        if (mReviewAdapter != null) {
                            mReviewAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvReviews.setVisibility(View.VISIBLE);
                        mRecyclerViewReview.setVisibility(View.GONE);
                    }
                }
                //get videos from database
                ResponseVideo responseMovieVideo = dataBaseMovieDetails.getResponseMovieVideo();
                if (responseMovieVideo != null) {
                    List<ResponseVideo.VideoBean> videoBeanList = responseMovieVideo.getVideoBean();
                    if (videoBeanList.size() > 0) {
                        mVideoBeanList.clear();
                        mVideoBeanList.addAll(videoBeanList);
                        if (mVideoMovieAdapter != null) {
                            mVideoMovieAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvVideos.setVisibility(View.VISIBLE);
                        mRecyclerViewVideos.setVisibility(View.GONE);
                    }
                }
                //get similar tv shows from database
                ResponseTvPopular responseTvShow = dataBaseMovieDetails.getResponseTvSimilarShows();
                if (responseTvShow != null) {
                    List<ResponseTvPopular.ResultsBean> similarShowList = responseTvShow.getResults();
                    if (similarShowList.size() > 0) {
                        mSimilarShowsList.clear();
                        mSimilarShowsList.addAll(similarShowList);
                        if (mSimilarShowAdapter != null) {
                            mSimilarShowAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mTvSimilar.setVisibility(View.VISIBLE);
                        mRecyclerSimilarMovies.setVisibility(View.GONE);
                    }
                }


            }


        } else if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            if (!TextUtils.isEmpty(mId)) {
                mIsLoadingReview = true;
                mIsLoadingTrailers = true;
                mIsLoadingSimilarMovies = true;
                mIsLoadingCasting = true;
                mProgressBarReview.setVisibility(View.VISIBLE);
                mTvLoadReview.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getReviews(mId);
                mProgressBarVideo.setVisibility(View.VISIBLE);
                mTvLoadingVideo.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getVideos(mId);
                mTvLoadingSimilarMovies.setVisibility(View.VISIBLE);
                mProgressBarSimilarMovies.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getSimilarMovies(mId);

                mTvLoadingCast.setVisibility(View.VISIBLE);
                mProgressCast.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getCastingOfMovies(mId);

            }
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            if (!TextUtils.isEmpty(mId)) {
                mIsLoadingReview = true;
                mIsLoadingSimilarMovies = true;
                mIsLoadingTrailers = true;
                mIsLoadingCastingShows = true;
                mProgressBarReview.setVisibility(View.VISIBLE);
                mTvLoadReview.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getTvReviews(mId);
                mProgressBarVideo.setVisibility(View.VISIBLE);
                mTvLoadingVideo.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getTvVideos(mId);
                mTvLoadingSimilarMovies.setVisibility(View.VISIBLE);
                mProgressBarSimilarMovies.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getSimilarTvShows(mId);
                mTvLoadingCastTVShows.setVisibility(View.VISIBLE);
                mProgressCastShows.setVisibility(View.VISIBLE);
                mRestClient.callback(this).getCastingOfTvShows(mId);

            }
        }
    }

    private void callPeopleDetailsShows(int position) {
        if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
            displayLoadingDialog(false);
            mRestClient.callback(this)
                    .getPeopleDetails(String.valueOf(mShowCastList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
    }


    private void hideToolbarContents() {
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            mTvTitleToolbar.setText(mResponseMovieDetails.getOriginal_title());
            mTvTitleToolbar.setVisibility(View.GONE);
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            mTvTitleToolbar.setVisibility(View.GONE);
            mTvTitleToolbar.setText(mResponseTvDetails.getOriginal_name());

        }


    }

    private void callPeopleDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
            displayLoadingDialog(false);
            mRestClient.callback(this)
                    .getPeopleDetails(String.valueOf(mMovieCastList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
    }

    private void callTvShowDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getTvShowDetails(String.valueOf(mSimilarShowsList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
    }

    private void callMovieDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(getApplicationContext())) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getMovieDetails(String.valueOf(mSimilarMoviesList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }

    }

    private void showToolbarContents() {
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            mTvTitleToolbar.setVisibility(View.VISIBLE);
            mTvTitleToolbar.setText(mResponseMovieDetails.getOriginal_title());
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            mTvTitleToolbar.setVisibility(View.VISIBLE);
            mTvTitleToolbar.setText(mResponseTvDetails.getOriginal_name());

        }


    }

    private void checkMovieExistIntofavourites() {
        boolean checkFav = mDatabase.checkISDataExist(mId);
        if (checkFav) {
            mFabFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
            mIsNotFav = false;
        } else {
            mFabFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            mIsNotFav = true;
        }

    }

    @Override
    public void onSuccessResponse(int apiId, Object response) {
        dismissLoadingDialog();
        if (apiId == ApiIds.ID_MOVIE_REVIEW) {
            mIsLoadingReview = false;
            mProgressBarReview.setVisibility(View.GONE);
            mTvLoadReview.setVisibility(View.GONE);
            mResponseMovieReview = (ResponseMovieReview) response;
            if (mResponseMovieReview != null) {
                List<ResponseMovieReview.ReviewBean> reviewBeen = mResponseMovieReview.getReviewBean();
                if (reviewBeen.size() > 0) {
                    mReviewBeanList.clear();
                    mReviewBeanList.addAll(reviewBeen);
                    if (mReviewAdapter != null) {
                        mReviewAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTvReviews.setVisibility(View.VISIBLE);
                    mRecyclerViewReview.setVisibility(View.GONE);
                }
            }
        } else if (apiId == ApiIds.ID_MOVIE_VIDEO) {
            mIsLoadingTrailers = false;
            mProgressBarVideo.setVisibility(View.GONE);
            mTvLoadingVideo.setVisibility(View.GONE);
            mResponseMovieVideo = (ResponseVideo) response;
            if (mResponseMovieVideo != null) {
                List<ResponseVideo.VideoBean> videoBean = mResponseMovieVideo.getVideoBean();
                if (videoBean.size() > 0) {
                    mVideoBeanList.clear();
                    mVideoBeanList.addAll(videoBean);
                    if (mVideoBeanList != null) {
                        mVideoMovieAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTvVideos.setVisibility(View.VISIBLE);
                    mRecyclerViewVideos.setVisibility(View.GONE);
                }
            }
        } else if (apiId == ApiIds.ID_TV_VIDEOS) {
            mIsLoadingTrailers = false;
            mProgressBarVideo.setVisibility(View.GONE);
            mTvLoadingVideo.setVisibility(View.GONE);
            mResponseMovieVideo = (ResponseVideo) response;
            if (mResponseMovieVideo != null) {
                List<ResponseVideo.VideoBean> videoBean = mResponseMovieVideo.getVideoBean();
                if (videoBean.size() > 0) {
                    mVideoBeanList.clear();
                    mVideoBeanList.addAll(videoBean);
                    if (mVideoBeanList != null) {
                        mVideoMovieAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTvVideos.setVisibility(View.VISIBLE);
                    mRecyclerViewVideos.setVisibility(View.GONE);
                }
            }
        } else if (apiId == ApiIds.ID_TV_REVIEW) {
            mIsLoadingReview = false;
            mProgressBarReview.setVisibility(View.GONE);
            mTvLoadReview.setVisibility(View.GONE);
            mResponseRecommendations = (ResponseRecommendations) response;
            if (mResponseRecommendations != null) {
                List<ResponseRecommendations.ResultsBean> reviewBeen = mResponseRecommendations.getResults();
                if (reviewBeen.size() > 0) {
                    //    mReviewBeanList.clear();
                    //   mReviewBeanList.addAll(reviewBeen);
                    if (mReviewAdapter != null) {
                        mReviewAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTvReviews.setVisibility(View.VISIBLE);
                    mRecyclerViewReview.setVisibility(View.GONE);
                }
            }
        } else if (apiId == ApiIds.ID_SIMILAR_MOVIES) {
            mIsLoadingSimilarMovies = false;
            mProgressBarSimilarMovies.setVisibility(View.GONE);
            mTvLoadingSimilarMovies.setVisibility(View.GONE);
            mResponseSimilarMovies = (ResponsePopularMovie) response;
            if (mResponseSimilarMovies != null) {
                List<ResponsePopularMovie.PopularMovie> similarMoviesList = mResponseSimilarMovies.getPopularMovie();
                if (similarMoviesList.size() > 0) {
                    mSimilarMoviesList.clear();
                    mSimilarMoviesList.addAll(similarMoviesList);
                    if (mSimilarMovieAdapter != null) {
                        mSimilarMovieAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTvSimilar.setVisibility(View.VISIBLE);
                    mRecyclerSimilarMovies.setVisibility(View.GONE);
                }
            }

        } else if (apiId == ApiIds.ID_SIMILAR_TV_SHOWS) {
            mIsLoadingSimilarMovies = false;
            mProgressBarSimilarMovies.setVisibility(View.GONE);
            mTvLoadingSimilarMovies.setVisibility(View.GONE);
            mResponseSimilarShows = (ResponseTvPopular) response;
            if (mResponseSimilarShows != null) {
                List<ResponseTvPopular.ResultsBean> similarShowList = mResponseSimilarShows.getResults();
                if (similarShowList.size() > 0) {
                    mSimilarShowsList.clear();
                    mSimilarShowsList.addAll(similarShowList);
                    mRecyclerSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    mSimilarShowAdapter = new SimilarShowAdapter(mSimilarShowsList);
                    mRecyclerSimilarMovies.setAdapter(mSimilarShowAdapter);
                    mRecyclerSimilarMovies.setNestedScrollingEnabled(false);

                }
            } else {
                mTvSimilar.setVisibility(View.VISIBLE);
                mRecyclerSimilarMovies.setVisibility(View.GONE);
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
        } else if (apiId == ApiIds.ID_TV_DETAILS) {
            ResponseTvDetails responseTvDetails = (ResponseTvDetails) response;
            String res = new Gson().toJson(responseTvDetails);
            if (responseTvDetails != null) {
                Intent movieDetailIntent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                movieDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_TV_SHOWS);
                movieDetailIntent.putExtra(Constants.TYPE_TV_SHOW_DETAILS, res);
                startActivity(movieDetailIntent);
            }
        } else if (apiId == ApiIds.ID_MOVIE_CREDITS) {
            mTvLoadingCast.setVisibility(View.GONE);
            mProgressCast.setVisibility(View.GONE);
            mRecyclerCast.setVisibility(View.VISIBLE);
            mResponseCastMovies = (ResponseCastMovies) response;
            if (mResponseCastMovies != null) {
                mIsLoadingCasting = false;
                List<ResponseCastMovies.CastBean> castList = mResponseCastMovies.getCast();
                mMovieCastList.clear();
                mMovieCastList.addAll(castList);
                if (mCastMovieAdapter != null) {
                    mCastMovieAdapter.notifyDataSetChanged();
                }

            }
        } else if (apiId == ApiIds.ID_TV_SHOWS_CREDITS) {
            mTvLoadingCastTVShows.setVisibility(View.GONE);
            mProgressCastShows.setVisibility(View.GONE);
            mRecyclerCastShows.setVisibility(View.VISIBLE);
            mResponseCastShows = (ResponseCastTVShows) response;
            if (mResponseCastShows != null) {
                mIsLoadingCastingShows = false;
                List<ResponseCastTVShows.CastBean> castList = mResponseCastShows.getCast();
                mShowCastList.clear();
                mShowCastList.addAll(castList);
                if (mCastShowsAdapter != null) {
                    mCastShowsAdapter.notifyDataSetChanged();
                }

            }
        } else if (apiId == ApiIds.ID_PEOPLE_DETAILS) {
            ResponsePeopleDetails responsePeopleDetails = (ResponsePeopleDetails) response;
            String res = new Gson().toJson(responsePeopleDetails);
            // String resKnown = new Gson().toJson(mResponsePeople);
            if (responsePeopleDetails != null) {

                Intent peopleDetailIntent = new Intent(getApplicationContext(), PeopleDetailsActivity.class);
                peopleDetailIntent.putExtra(Constants.TYPE_PEOPLE_DETAILS, res);
                peopleDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_PEOPLE);
                startActivity(peopleDetailIntent);
            }

        }


    }


    @Override
    public void onFailResponse(int apiId, String error) {
        dismissLoadingDialog();
        if (getApplicationContext() != null) {
            displayErrorDialog(getResources().getString(R.string.error), error);
        }
    }

    @Override
    public void networkNotAvailable() {
        dismissLoadingDialog();
        if (getApplicationContext() != null) {
            displayErrorDialog(R.string.error, R.string.internet_connection);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_favorite:
                if (!mIsLoadingReview && !mIsLoadingTrailers && !mIsLoadingSimilarMovies) {
                    if (mIsNotFav) {
                        addToFavourites();
                        mFabFavourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                        mIsNotFav = false;
                        EventBus.getDefault().post(new DataBaseEventUpdateModel());

                    } else {
                        mFabFavourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        removeFromFavourites();
                        EventBus.getDefault().post(new DataBaseEventUpdateModel());
                        mIsNotFav = true;
                    }
                } else {
                    String msg = null;
                    if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
                        msg = getString(R.string.wait_data_loaded, "movies");
                    } else {
                        msg = getString(R.string.wait_data_loaded, "tv shows");

                    }
                    displayShortToast(msg);
                }
                break;
            case R.id.imageMovieBack:
                callImageFullScreen();
                break;

        }
    }


    private void shareMovieDetails() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<b><center>" + mTitle + "</center></b") + "\n\n" +
                Html.fromHtml("<b><center>" + mTvDescription.getText().toString() + "</center></b"));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.item_share:

                shareMovieDetails();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_detail_menu, menu);
        return true;
    }

    private void addToFavourites() {

        DataBaseMovieDetails dataBaseMovieDetails = new DataBaseMovieDetails();
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            displayShortToast(R.string.add_to_favourites);
            dataBaseMovieDetails.setResponseMovieDetails(mResponseMovieDetails);
            dataBaseMovieDetails.setType(0);
            dataBaseMovieDetails.setResponseMovieReview(mResponseMovieReview);
            dataBaseMovieDetails.setResponseMovieVideo(mResponseMovieVideo);
            dataBaseMovieDetails.setId(mResponseMovieDetails.getId());
            dataBaseMovieDetails.setResponseSimilarMovies(mResponseSimilarMovies);
            dataBaseMovieDetails.setResponseCastMovies(mResponseCastMovies);
            mDatabase.addMovies(dataBaseMovieDetails);

        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            displayShortToast(R.string.add_to_favourites_tv);
            dataBaseMovieDetails.setResponseTvDetails(mResponseTvDetails);
            dataBaseMovieDetails.setType(1);
            dataBaseMovieDetails.setResponseMovieReview(mResponseMovieReview);
            dataBaseMovieDetails.setResponseMovieVideo(mResponseMovieVideo);
            dataBaseMovieDetails.setResponseTvSimilarShows(mResponseSimilarShows);
            dataBaseMovieDetails.setId(mResponseTvDetails.getId());
            dataBaseMovieDetails.setResponseCastShows(mResponseCastShows);
            mDatabase.addMovies(dataBaseMovieDetails);
        }
    }

    private void removeFromFavourites() {
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            displayShortToast(R.string.remove_to_favourites);
        } else {
            displayShortToast(R.string.remove_to_fav_show);
        }
        mDatabase.deleteMovie(mId);

    }

    private void callImageFullScreen() {
        Intent intent = new Intent(MovieDetailsActivity.this, MovieImageFullScreenActivity.class);
        intent.putExtra(Constants.FULL_IMAGE_URL, mImageUrl);
        intent.putExtra(Constants.MOVIE_TITLE, mTitle);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
