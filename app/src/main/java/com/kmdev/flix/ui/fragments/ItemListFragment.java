package com.kmdev.flix.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brsoftech.core_utils.base.BaseSupportFragment;
import com.brsoftech.core_utils.utils.ItemClickSupport;
import com.google.gson.Gson;
import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiHitListener;
import com.kmdev.flix.RestClient.ApiIds;
import com.kmdev.flix.RestClient.ConnectionDetector;
import com.kmdev.flix.RestClient.RestClient;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponsePopularMovie;
import com.kmdev.flix.models.ResponseTvDetails;
import com.kmdev.flix.models.ResponseTvPopular;
import com.kmdev.flix.prefrences.AppPrefs;
import com.kmdev.flix.ui.activities.MovieDetailsActivity;
import com.kmdev.flix.ui.activities.SearchMovieActivity;
import com.kmdev.flix.ui.adapters.HomeMoviesAdapter;
import com.kmdev.flix.ui.adapters.HomeTvShowsAdapter;
import com.kmdev.flix.utils.Constants;
import com.kmdev.flix.utils.ItemOffsetDecoration;
import com.kmdev.flix.utils.SelectedMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kajal on 10/2/2016.
 */
public class ItemListFragment extends BaseSupportFragment implements ApiHitListener, SwipeRefreshLayout.OnRefreshListener, HomeMoviesAdapter.OnRetryListener {
    public static final String ARG_MOVIES = "movies";
    public static final String ARG_TV_SHOWS = "tv_shows";
    public static final String ARG_TYPE = "type";
    public static final String ARG_PEOPLE = "people";
    private static final String TAG = "ItemListFragment";
    private RestClient mRestClient;
    private RecyclerView mRecyclerPopularMovie;
    private HomeMoviesAdapter mHomeMoviesAdapter;
    private HomeTvShowsAdapter mHomeTvShowsAdapter;
    private List<ResponsePopularMovie.PopularMovie> mPopularMovieList = new ArrayList<>();
    private List<ResponseTvPopular.ResultsBean> mTvShowsList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;
    private TextView mTvLoading, mTvErrorShow;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private int mCurrentPage = 1;
    private int mPastVisibleItems, mVisibleItemCount, mTotalItemCount;
    private boolean mIsLoadingNewItems = false;
    private FrameLayout mFrameNetworkError;
    private SelectedMenu mCurrentState = SelectedMenu.IDLE;
    private boolean mIsClearDataSet = false;



    public static ItemListFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        ItemListFragment fragment = new ItemListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        bindViewsById(view);
        init();
        return view;
    }

    private void bindViewsById(View view) {
        mRecyclerPopularMovie = (RecyclerView) view.findViewById(R.id.recyclerview_popular);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        //mProgressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        mTvLoading = (TextView) view.findViewById(R.id.tv_loading);
        mTvErrorShow = (TextView) view.findViewById(R.id.tv_error_show);
        // mFrameNetworkError= (FrameLayout) view.findViewById(R.id.frame_network_error);
    }

    private void init() {
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerPopularMovie.setLayoutManager(mStaggeredGridLayoutManager);
        if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_MOVIES)) {
            mHomeMoviesAdapter = new HomeMoviesAdapter(mPopularMovieList, this);
            mRecyclerPopularMovie.setAdapter(mHomeMoviesAdapter);
        } else {
            mHomeTvShowsAdapter = new HomeTvShowsAdapter(mTvShowsList, this);
            mRecyclerPopularMovie.setAdapter(mHomeTvShowsAdapter);

        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        mRecyclerPopularMovie.addItemDecoration(itemDecoration);

        ItemClickSupport
                .addTo(mRecyclerPopularMovie)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_MOVIES)) {
                            if (position != mHomeMoviesAdapter.getItemCount() - 1) {
                                callMovieDetails(position);
                            }
                        } else if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_TV_SHOWS)) {
                            if (position != mHomeTvShowsAdapter.getItemCount() - 1) {
                                callTvShowDetails(position);
                            }
                        }

                    }
                });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.black,
                R.color.colorPrimary,
                R.color.colorAccent);


        mRecyclerPopularMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //  mTvErrorShow.setVisibility(View.GONE);
                onScrollLoadMovies();


            }

        });


    }

    private void callTvShowDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(getActivity())) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getTvShowDetails(String.valueOf(mTvShowsList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
    }

    private void onScrollLoadMovies() {
        StaggeredGridLayoutManager linearLayoutManager = (StaggeredGridLayoutManager) mRecyclerPopularMovie.getLayoutManager();
        // check if loading view (last item on our list) is visible
        mVisibleItemCount = linearLayoutManager.getChildCount();
        mTotalItemCount = linearLayoutManager.getItemCount();
        int[] firstVisibleItems = null;
        firstVisibleItems = linearLayoutManager.findFirstVisibleItemPositions(firstVisibleItems);
        if (firstVisibleItems != null && firstVisibleItems.length > 0) {
            mPastVisibleItems = firstVisibleItems[0];
        }

        if ((mVisibleItemCount + mPastVisibleItems) >= mTotalItemCount) {
            mCurrentPage++;
            callMovies();
            Log.d("tag", "LOAD NEXT ITEM");

        }
    }

    private void callMovies() {
        if (ConnectionDetector.isNetworkAvailable(getActivity())) {
            if (mCurrentPage == 1) {
                mIsLoadingNewItems = false;
            } else {
                if (!mIsLoadingNewItems) {
                    mIsLoadingNewItems = true;
                    getMovies();
                }
            }
        }


    }


    private void callMovieDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(getActivity())) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getMovieDetails(String.valueOf(mPopularMovieList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRestClient = new RestClient(getActivity());
        // displayLoadingDialog(true);
        mProgressBar.setVisibility(View.VISIBLE);
        mTvLoading.setVisibility(View.VISIBLE);
        getMovies();
    }

    private void getMovies() {
        String movieMenuValue = AppPrefs.getStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE);
        String tvMenuValue = AppPrefs.getStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE);
        if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_MOVIES)) {
            if (TextUtils.equals(movieMenuValue, getString(R.string.top_rated))) {
                mRestClient.callback(this).getTopRatedMovies(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.top_rated));


            }
            if (TextUtils.equals(movieMenuValue, getString(R.string.popular))) {
                mRestClient.callback(this).getPopularMovies(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.popular));


            } else if (TextUtils.equals(movieMenuValue, getString(R.string.upcoming))) {
                mRestClient.callback(this).getUpcomingMovies(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.upcoming));

            } else if (TextUtils.equals(movieMenuValue, getString(R.string.now_playing))) {
                mRestClient.callback(this).getMoviesNowPlaying(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.now_playing));

            } else {
                mRestClient.callback(this).getMoviesNowPlaying(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.now_playing));
            }

        } else if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_TV_SHOWS))

        {
            if (TextUtils.equals(tvMenuValue, getString(R.string.top_rated) + getString(R.string.tv_shows))) {
                mRestClient.callback(this).getTvTopRated(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE, getString(R.string.top_rated)
                        + getString(R.string.tv_shows));

            } else if (TextUtils.equals(tvMenuValue, getString(R.string.popular) + getString(R.string.tv_shows))) {
                mRestClient.callback(this).getTvPopularShows(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE,
                        getString(R.string.popular) + getString(R.string.tv_shows));

            } else if (TextUtils.equals(tvMenuValue, getString(R.string.on_tv) + getString(R.string.tv_shows))) {
                mRestClient.callback(this).getOnTvMovies(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE,
                        getString(R.string.on_tv) + getString(R.string.tv_shows));

            } else if (TextUtils.equals(tvMenuValue, getString(R.string.airing_today) + getString(R.string.tv_shows))) {
                mRestClient.callback(this).getAiringMoviesToday(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE,
                        getString(R.string.airing_today) + getString(R.string.tv_shows));


            } else {
                mRestClient.callback(this).getOnTvMovies(mCurrentPage);
                AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE,
                        getString(R.string.on_tv) + getString(R.string.tv_shows));


            }

        }
    }

    @Override
    public void onSuccessResponse(int apiId, Object response) {
        dismissLoadingDialog();
        mProgressBar.setVisibility(View.GONE);
        mTvLoading.setVisibility(View.GONE);
        mTvErrorShow.setVisibility(View.GONE);
        if (apiId == ApiIds.ID_POPULAR_MOVIES || apiId == ApiIds.ID_TOP_RATED_MOVIES ||
                apiId == ApiIds.ID_NOW_PLAYING || apiId == ApiIds.ID_UPCOMING_MOVIES) {
            mSwipeRefreshLayout.setRefreshing(false);
            ResponsePopularMovie responsePopularMovie = (ResponsePopularMovie) response;
            if (responsePopularMovie != null) {
                List<ResponsePopularMovie.PopularMovie> popularMovie = responsePopularMovie.getPopularMovie();
                if (popularMovie != null && popularMovie.size() > 0) {
                    if (mIsClearDataSet) {
                        mPopularMovieList.clear();
                        mIsClearDataSet = false;
                    }
                    mPopularMovieList.addAll(popularMovie);
                    if (mHomeMoviesAdapter != null) {
                        mHomeMoviesAdapter.notifyDataSetChanged();
                        mIsLoadingNewItems = false;

                    }
                }
            }

        } else if (apiId == ApiIds.ID_TV_POPULAR ||
                apiId == ApiIds.ID_TV_TOP_RATED || apiId == ApiIds.ID_ON_TV ||
                apiId == ApiIds.ID_TV_AIR_MOVIE_TODAY) {
            mSwipeRefreshLayout.setRefreshing(false);
            ResponseTvPopular responseTvShow = (ResponseTvPopular) response;
            if (responseTvShow != null) {
                if (responseTvShow != null) {
                    List<ResponseTvPopular.ResultsBean> responseTvShowResults = responseTvShow.getResults();
                    if (responseTvShowResults.size() > 0) {

                        if (mIsClearDataSet) {
                            mTvShowsList.clear();
                            mIsClearDataSet = false;
                        }
                        mTvShowsList.addAll(responseTvShowResults);

                        if (mHomeTvShowsAdapter != null) {
                            mHomeTvShowsAdapter.notifyDataSetChanged();
                            mIsLoadingNewItems = false;

                        }
                    }


                }
            }
        } else if (apiId == ApiIds.ID_MOVIE_DETAILS) {
            ResponseMovieDetails responseMovieDetails = (ResponseMovieDetails) response;
            String res = new Gson().toJson(responseMovieDetails);
            if (responseMovieDetails != null) {

                Intent movieDetailIntent = new Intent(getActivity(), MovieDetailsActivity.class);
                movieDetailIntent.putExtra(ARG_TYPE, ARG_MOVIES);
                movieDetailIntent.putExtra(Constants.TYPE_MOVIE_DETAILS, res);
                startActivity(movieDetailIntent);
            }
        } else if (apiId == ApiIds.ID_TV_DETAILS) {
            ResponseTvDetails responseTvDetails = (ResponseTvDetails) response;
            String res = new Gson().toJson(responseTvDetails);
            if (responseTvDetails != null) {
                Intent movieDetailIntent = new Intent(getActivity(), MovieDetailsActivity.class);
                movieDetailIntent.putExtra(ARG_TYPE, ARG_TV_SHOWS);
                movieDetailIntent.putExtra(Constants.TYPE_TV_SHOW_DETAILS, res);
                startActivity(movieDetailIntent);
            }
        }


    }

    @Override
    public void onFailResponse(int apiId, String error) {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);

        mTvLoading.setVisibility(View.GONE);
        if (mHomeMoviesAdapter != null) {
            if (getActivity() != null && mHomeMoviesAdapter.getItemCount() == 0) {
                if (apiId == ApiIds.ID_TV_LATEST_SHOW || apiId == ApiIds.ID_TV_POPULAR ||
                        apiId == ApiIds.ID_TV_TOP_RATED || apiId == ApiIds.ID_TV_DETAILS) {
                    mTvErrorShow.setText(R.string.unable_load_tv);
                    mTvErrorShow.setVisibility(View.VISIBLE);


                } else {
                    mTvErrorShow.setText(R.string.unable_load_movies);
                    mTvErrorShow.setVisibility(View.VISIBLE);
                }
            }
        } else if (apiId == ApiIds.ID_TV_POPULAR ||
                apiId == ApiIds.ID_TV_TOP_RATED || apiId == ApiIds.ID_TV_DETAILS) {
            displayShortToast(R.string.unable_load_tv);
        } else {
            displayShortToast(R.string.unable_load_movies);
        }

    }

    @Override
    public void networkNotAvailable() {

        mProgressBar.setVisibility(View.GONE);
        mTvLoading.setVisibility(View.GONE);
        mTvErrorShow.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);


    }

    @Override
    public void onRefresh() {
        if (ConnectionDetector.isNetworkAvailable(getActivity())) {
            callMovies();
        } else {
            displayShortToast(R.string.internet_connection);
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onRetry() {
        onScrollLoadMovies();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_MOVIES)) {
            inflater.inflate(R.menu.main_menu, menu);
        } else {
            inflater.inflate(R.menu.tv_shows_menu, menu);
        }

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        String menu_value = AppPrefs.getStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE);
        String tvMenuValue = AppPrefs.getStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE);

        if (TextUtils.equals(getArguments().getString(ARG_TYPE), ARG_MOVIES)) {
            for (int i = 0; i < menu.size(); i++) {
                if (menu.getItem(i).getTitle().equals(menu_value)) {
                    menu.getItem(i).setChecked(true);
                }
            }
        } else {
            for (int i = 0; i < menu.size(); i++) {
                String title = menu.getItem(i).getTitle() + getString(R.string.tv_shows);
                if (title.equals(tvMenuValue)) {
                    menu.getItem(i).setChecked(true);
                }
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String menu_value = AppPrefs.getStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE);
        String tvMenuValue = AppPrefs.getStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE);
        if (menu_value.equals(item.getTitle())) {
            item.setChecked(true);
        }
        if (tvMenuValue.equals(item.getTitle())) {
            item.setChecked(true);
        }
        switch (item.getItemId()) {
            case R.id.action_top_rated:
                if (mCurrentState != SelectedMenu.TOP_RATED_MOVIES) {
                    item.setChecked(true);
                    displayLoadingDialog(false);
                    mIsClearDataSet = true;
                    mCurrentPage = 1;
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.top_rated));
                    mRestClient.callback(this).getTopRatedMovies(mCurrentPage);
                    mCurrentState = SelectedMenu.TOP_RATED_MOVIES;
                }
                break;
            case R.id.action_popular:
                if (mCurrentState != SelectedMenu.POPULAR_MOVIES) {
                    item.setChecked(true);
                    displayLoadingDialog(false);
                    mCurrentPage = 1;
                    mIsClearDataSet = true;
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.popular));
                    mRestClient.callback(this).getPopularMovies(mCurrentPage);
                    mCurrentState = SelectedMenu.POPULAR_MOVIES;
                }
                break;
            case R.id.action_now_playing:
                if (mCurrentState != SelectedMenu.NOW_PLAYING_MOVIES) {
                    item.setChecked(true);
                    mCurrentPage = 1;
                    mIsClearDataSet = true;
                    displayLoadingDialog(false);
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.now_playing));
                    mCurrentState = SelectedMenu.NOW_PLAYING_MOVIES;
                    mRestClient.callback(this).getMoviesNowPlaying(mCurrentPage);
                }
                break;
            case R.id.action_upcoming:
                if (mCurrentState != SelectedMenu.UPCOMING_MOVIES) {
                    item.setChecked(true);
                    mCurrentPage = 1;
                    mIsClearDataSet = true;
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_MENU_VALUE, getString(R.string.upcoming));
                    displayLoadingDialog(false);
                    mCurrentState = SelectedMenu.UPCOMING_MOVIES;
                    mRestClient.callback(this).getUpcomingMovies(mCurrentPage);
                }
                break;
            case R.id.action_tv_popular:

                if (mCurrentState != SelectedMenu.TV_POPULAR) {
                    item.setChecked(true);
                    mCurrentPage = 1;
                    mIsClearDataSet = true;
                    displayLoadingDialog(false);
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE, getString(R.string.popular)
                            + getString(R.string.tv_shows));

                    mRestClient.callback(this).getTvPopularShows(mCurrentPage);
                    mCurrentState = SelectedMenu.TV_POPULAR;
                    item.setChecked(true);
                }
                break;
            case R.id.action_tv_top_rated:
                if (mCurrentState != SelectedMenu.TV_TOP_RATED) {
                    item.setChecked(true);
                    mCurrentPage = 1;
                    mIsClearDataSet = true;
                    displayLoadingDialog(false);
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE, getString(R.string.top_rated)
                            + getString(R.string.tv_shows));
                    mRestClient.callback(this).getTvTopRated(mCurrentPage);
                    mCurrentState = SelectedMenu.TV_TOP_RATED;
                }
                break;
            case R.id.action_on_tv:
                if (mCurrentState != SelectedMenu.ON_TV) {
                    item.setChecked(true);
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE,
                            getString(R.string.on_tv) + getString(R.string.tv_shows));

                    mCurrentPage = 1;
                    mIsClearDataSet = true;
                    displayLoadingDialog(false);
                    mRestClient.callback(this).getOnTvMovies(mCurrentPage);
                    mCurrentState = SelectedMenu.ON_TV;
                }


                break;
            case R.id.action_airing_today:
                if (mCurrentState != SelectedMenu.TV_AIRING_TODAY) {
                    mCurrentPage = 1;
                    item.setChecked(true);
                    AppPrefs.setStringKeyvaluePrefs(getActivity(), AppPrefs.KEY_TV_MENU_VALUE,
                            getString(R.string.airing_today) + getString(R.string.tv_shows));
                    mIsClearDataSet = true;
                    displayLoadingDialog(false);
                    mRestClient.callback(this).getAiringMoviesToday(mCurrentPage);

                    mCurrentState = SelectedMenu.TV_AIRING_TODAY;
                }

                break;

            case R.id.action_search:
                Intent movieIntent = new Intent(getActivity(), SearchMovieActivity.class);
                movieIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_MOVIES);
                Log.e(TAG, "onOptionsItemSelected: " + getArguments().getString(ItemListFragment.ARG_TYPE));
                startActivity(movieIntent);
                break;
            case R.id.action_tv_search:
                Intent tvIntent = new Intent(getActivity(), SearchMovieActivity.class);
                tvIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_TV_SHOWS);
                Log.e(TAG, "onOptionsItemSelected: " + getArguments().getString(ItemListFragment.ARG_TYPE));

                startActivity(tvIntent);
                break;

        }
        return super.onOptionsItemSelected(item);

    }

}
