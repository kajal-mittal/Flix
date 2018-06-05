package com.kmdev.flix.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.brsoftech.core_utils.base.BaseAppCompatActivity;
import com.brsoftech.core_utils.utils.ItemClickSupport;
import com.google.gson.Gson;
import com.kmdev.flix.R;
import com.kmdev.flix.RestClient.ApiHitListener;
import com.kmdev.flix.RestClient.ApiIds;
import com.kmdev.flix.RestClient.ConnectionDetector;
import com.kmdev.flix.RestClient.RestClient;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponsePeople;
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.kmdev.flix.models.ResponseSearchMovie;
import com.kmdev.flix.models.ResponseSearchTv;
import com.kmdev.flix.models.ResponseTvDetails;
import com.kmdev.flix.ui.adapters.SearchMovieAdapter;
import com.kmdev.flix.ui.adapters.SearchPeopleAdapter;
import com.kmdev.flix.ui.adapters.SearchTvAdapter;
import com.kmdev.flix.ui.fragments.ItemListFragment;
import com.kmdev.flix.utils.Constants;
import com.kmdev.flix.utils.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchMovieActivity extends BaseAppCompatActivity implements ApiHitListener {
    private RestClient mRestClient;
    private SearchMovieAdapter mSearchMovieAdapter;
    private SearchTvAdapter mSearchTvAdapter;
    private SearchPeopleAdapter mSearchPeopleAdapter;
    private RecyclerView mRecyclerSearch;
    private String mQuery;
    private EditText mEtSearch;
    private List<ResponseSearchMovie.ResultsSearchBean> mSearchBeanList;
    private List<ResponseSearchTv.SearchBean> mSearchTvList;
    private Toolbar mToolBar;
    private TextView mTvError;
    private String mType;
    private int mCurrentPage = 1;
    private ResponsePeople mResponsePeople;
    private List<ResponsePeople.ResultsBean.KnownForBean> mKnowPeopleList;
    private List<ResponsePeople.ResultsBean> mSearchPeopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindViewsById();
        init();
    }


    private void bindViewsById() {
        mRecyclerSearch = (RecyclerView) findViewById(R.id.recycler_search);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTvError = (TextView) findViewById(R.id.tv_error_show);


    }

    private void init() {
        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //hide  title from toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(4);
        mType = getIntent().getStringExtra(ItemListFragment.ARG_TYPE);
        mKnowPeopleList = new ArrayList<>();
        mSearchPeopleList = new ArrayList<>();
        mRestClient = new RestClient(this);
        mSearchBeanList = new ArrayList<>();
        mSearchTvList = new ArrayList<>();
        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
            mRecyclerSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mSearchMovieAdapter = new SearchMovieAdapter(mSearchBeanList);
            mRecyclerSearch.setAdapter(mSearchMovieAdapter);
            mEtSearch.setHint(R.string.search_movies);
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
            mRecyclerSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mSearchTvAdapter = new SearchTvAdapter(mSearchTvList);
            mRecyclerSearch.setAdapter(mSearchTvAdapter);
            mEtSearch.setHint(R.string.search_tv_shows);
        } else if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
            mRecyclerSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mSearchPeopleAdapter = new SearchPeopleAdapter(mSearchPeopleList);
            mRecyclerSearch.setAdapter(mSearchPeopleAdapter);
            mEtSearch.setHint(R.string.search_people);
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.spacing);

        mRecyclerSearch.addItemDecoration(itemDecoration);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchQuery = mEtSearch.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
                        callSearchMovie(searchQuery);
                    } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
                        callSearchTv(searchQuery);
                    } else if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
                        callSearchPeople(searchQuery);
                    }
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
                                mSearchBeanList.clear();
                                mSearchMovieAdapter.notifyDataSetChanged();
                            } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
                                mSearchTvList.clear();
                                mSearchTvAdapter.notifyDataSetChanged();

                            } else if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
                                mSearchPeopleList.clear();
                                mSearchPeopleAdapter.notifyDataSetChanged();

                            }


                        }
                    }, 300);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
              /*  if(TextUtils.isEmpty(s))
                {
                    mSearchBeanList.clear();
                    mSearchMovieAdapter.notifyDataSetChanged();
                }*/

            }
        });
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchQuery = mEtSearch.getText().toString();
                    if (!TextUtils.isEmpty(searchQuery)) {
                        if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
                            displayLoadingDialog(false);
                            callSearchMovie(searchQuery);
                        }
                    } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
                        displayLoadingDialog(false);
                        callSearchTv(searchQuery);
                    } else if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
                        displayLoadingDialog(false);
                        callSearchPeople(searchQuery);
                    }

                    return true;
                }
                return false;
            }
        });
        ItemClickSupport.addTo(mRecyclerSearch).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (TextUtils.equals(mType, ItemListFragment.ARG_MOVIES)) {
                    callMovieDetails(position);
                } else if (TextUtils.equals(mType, ItemListFragment.ARG_TV_SHOWS)) {
                    callTvDetails(position);
                } else if (TextUtils.equals(mType, ItemListFragment.ARG_PEOPLE)) {
                    callPeopleDetails(position);
                }



            }
        });

        //mToolBar.
    }

    private void callPeopleDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(SearchMovieActivity.this)) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getPeopleDetails(String.valueOf(mSearchPeopleList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
    }

    private void callSearchPeople(String searchQuery) {
        mRestClient.callback(this).searchPeoples(searchQuery, mCurrentPage);

    }

    private void callTvDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(SearchMovieActivity.this)) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getTvShowDetails(String.valueOf(mSearchTvList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
    }

    private void callSearchTv(String searchQuery) {
        mRestClient.callback(this).searchTvShows(searchQuery);

    }

    private void callSearchMovie(String searchQuery) {
        // displayLoadingDialog(false);
        mRestClient.callback(this).searchMovie(searchQuery);

    }

    private void callMovieDetails(int position) {
        if (ConnectionDetector.isNetworkAvailable(SearchMovieActivity.this)) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getMovieDetails(String.valueOf(mSearchBeanList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }
        /*if (ConnectionDetector.isNetworkAvailable(SearchMovieActivity.this)) {
            displayLoadingDialog(true);
            mRestClient.callback(this).getMovieDetails(String.valueOf(mSearchBeanList.get(position).getId()));
        } else {
            displayShortToast(R.string.internet_connection);
        }*/

    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);//Menu Resource, Menu
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
           /* case R.id.action_movies:
                break;
            case R.id.action_tv_shows:
                break;
            case R.id.action_people:
                break;*/
        }
        return true;
    }


    @Override
    public void onSuccessResponse(int apiId, Object response) {
        dismissLoadingDialog();
        mTvError.setVisibility(View.GONE);

        if (apiId == ApiIds.ID_SEARCH_MOVIE) {
            ResponseSearchMovie resultsSearchBean = (ResponseSearchMovie) response;
            if (resultsSearchBean != null) {
                List<ResponseSearchMovie.ResultsSearchBean> searchBeanList = resultsSearchBean.getResultsSearchList();
                if (searchBeanList.size() > 0) {
                    mSearchBeanList.clear();
                    mSearchBeanList.addAll(searchBeanList);
                    if (mSearchMovieAdapter != null) {
                        mSearchMovieAdapter.notifyDataSetChanged();
                    }


                }
            }
        } else if (apiId == ApiIds.ID_MOVIE_DETAILS) {
            ResponseMovieDetails responseMovieDetails = (ResponseMovieDetails) response;
            String res = new Gson().toJson(responseMovieDetails);
            if (responseMovieDetails != null) {

                Intent movieDetailIntent = new Intent(this, MovieDetailsActivity.class);
                movieDetailIntent.putExtra(Constants.TYPE_MOVIE_DETAILS, res);
                movieDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_MOVIES);
                startActivity(movieDetailIntent);
                //finish();
            }
        } else if (apiId == ApiIds.ID_SEARCH_TV_SHOWS) {
            ResponseSearchTv resultsSearchBean = (ResponseSearchTv) response;
            if (resultsSearchBean != null) {
                List<ResponseSearchTv.SearchBean> searchBeanList = resultsSearchBean.getSearch();
                if (searchBeanList.size() > 0) {
                    mSearchTvList.clear();
                    mSearchTvList.addAll(searchBeanList);
                    if (mSearchTvAdapter != null) {
                        mSearchTvAdapter.notifyDataSetChanged();
                    }


                }
            }
        } else if (apiId == ApiIds.ID_TV_DETAILS) {
            ResponseTvDetails responseTvDetails = (ResponseTvDetails) response;
            String res = new Gson().toJson(responseTvDetails);
            if (responseTvDetails != null) {

                Intent tvDetailIntent = new Intent(this, MovieDetailsActivity.class);
                tvDetailIntent.putExtra(Constants.TYPE_TV_SHOW_DETAILS, res);
                tvDetailIntent.putExtra(ItemListFragment.ARG_TYPE, ItemListFragment.ARG_TV_SHOWS);
                startActivity(tvDetailIntent);
                // finish();
            }
        } else if (apiId == ApiIds.ID_SEARCH_PEOPLE) {
            /* ResponseSearchPeople responseSearchPeople = (ResponseSearchPeople) response;
            String res = new Gson().toJson(responseSearchPeople);
            String resKnown = new Gson().toJson(mResponsePeople);

            if (responseSearchPeople != null) {

                Intent peopleDetailIntent = new Intent(this, PeopleDetailsActivity.class);
                peopleDetailIntent.putExtra(Constants.TYPE_PEOPLE_DETAILS, res);
                peopleDetailIntent.putExtra(Constants.TYPE_KNOWN_FOR, resKnown);
                startActivity(peopleDetailIntent);
                // finish();
            }*/
            mResponsePeople = (ResponsePeople) response;
            if (mResponsePeople != null) {
                List<ResponsePeople.ResultsBean> popularPeople = mResponsePeople.getResults();
                for (int i = 0; i < popularPeople.size(); i++) {
                    mKnowPeopleList = popularPeople.get(i).getKnown_for();
                }
                if (popularPeople != null && popularPeople.size() > 0) {
                    mSearchPeopleList.clear();
                    //   mIsClearDataSet = false;

                    mSearchPeopleList.addAll(popularPeople);
                    if (mSearchPeopleAdapter != null) {
                        mSearchPeopleAdapter.notifyDataSetChanged();
                        // mIsLoadingNewItems = false;

                    }
                }
            }

        } else if (apiId == ApiIds.ID_PEOPLE_DETAILS) {
            ResponsePeopleDetails responsePeopleDetails = (ResponsePeopleDetails) response;
            String res = new Gson().toJson(responsePeopleDetails);

            if (responsePeopleDetails != null) {
                Intent peopleDetailIntent = new Intent(getApplicationContext(), PeopleDetailsActivity.class);
                peopleDetailIntent.putExtra(Constants.TYPE_PEOPLE_DETAILS, res);
                startActivity(peopleDetailIntent);


            }
        }


    }

    @Override
    public void onFailResponse(int apiId, String error) {
        if (apiId == ApiIds.ID_SEARCH_MOVIE && apiId == ApiIds.ID_MOVIE_DETAILS) {
            mTvError.setText(R.string.unable_load_movies);
            mTvError.setVisibility(View.VISIBLE);
        } else if (apiId == ApiIds.ID_SEARCH_TV_SHOWS && apiId == ApiIds.ID_TV_DETAILS) {
            mTvError.setText(R.string.unable_load_tv);
            mTvError.setVisibility(View.VISIBLE);

        } else if (apiId == ApiIds.ID_SEARCH_PEOPLE && apiId == ApiIds.ID_PEOPLE_DETAILS) {
            mTvError.setText(R.string.unable_load_people);
            mTvError.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void networkNotAvailable() {
        mTvError.setVisibility(View.VISIBLE);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
