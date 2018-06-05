package com.kmdev.flix.RestClient;

import android.content.Context;

import com.kmdev.flix.models.ResponseCastMovies;
import com.kmdev.flix.models.ResponseCastTVShows;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponseMovieReview;
import com.kmdev.flix.models.ResponsePeople;
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.kmdev.flix.models.ResponsePersonMovie;
import com.kmdev.flix.models.ResponsePopularMovie;
import com.kmdev.flix.models.ResponseRecommendations;
import com.kmdev.flix.models.ResponseSearchMovie;
import com.kmdev.flix.models.ResponseSearchTv;
import com.kmdev.flix.models.ResponseTvDetails;
import com.kmdev.flix.models.ResponseTvPopular;
import com.kmdev.flix.models.ResponseVideo;
import com.kmdev.flix.retrofilt.Rest;
import com.kmdev.flix.retrofilt.RestService;
import com.kmdev.flix.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ububtu on 13/7/16.
 */
public class RestClient extends BaseRestClient {
    ApiHitListener apiHitListener;
    private Rest api;
    private Object object;

    public RestClient(Context _context) {
        super(_context);
    }

    public RestClient callback(ApiHitListener apiHitListener) {
        this.apiHitListener = apiHitListener;
        return this;
    }

    private Rest getApi() {
        if (api == null) {
            api = RestService.getService();
        }

        return api;
    }

    public void getPopularMovies(int current_page) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {


            Call<ResponsePopularMovie> call = getApi().popularMovies(Constants.API_KEY, "en-US", current_page);
            call.enqueue(new Callback<ResponsePopularMovie>() {
                @Override
                public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_POPULAR_MOVIES, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_POPULAR_MOVIES, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getTopRatedMovies(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {


            Call<ResponsePopularMovie> call = getApi().topRatedMovies(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponsePopularMovie>() {
                @Override
                public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TOP_RATED_MOVIES, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TOP_RATED_MOVIES, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getMovieDetails(String movieId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseMovieDetails> call = getApi().movieDetails(movieId, Constants.API_KEY, "en-US", "videos/images");
            call.enqueue(new Callback<ResponseMovieDetails>() {
                @Override
                public void onResponse(Call<ResponseMovieDetails> call, Response<ResponseMovieDetails> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_MOVIE_DETAILS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseMovieDetails> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_MOVIE_DETAILS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getReviews(String movieId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseMovieReview> call = getApi().movieReviews(movieId, Constants.API_KEY);
            call.enqueue(new Callback<ResponseMovieReview>() {
                @Override
                public void onResponse(Call<ResponseMovieReview> call, Response<ResponseMovieReview> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_MOVIE_REVIEW, response.body());
                }

                @Override
                public void onFailure(Call<ResponseMovieReview> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_MOVIE_REVIEW, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getVideos(String movieId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseVideo> call = getApi().movieVideos(movieId, Constants.API_KEY);
            call.enqueue(new Callback<ResponseVideo>() {
                @Override
                public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_MOVIE_VIDEO, response.body());
                }

                @Override
                public void onFailure(Call<ResponseVideo> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_MOVIE_VIDEO, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void searchMovie(String query) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseSearchMovie> call = getApi().searchMovie(Constants.API_KEY, "en-US", query);
            call.enqueue(new Callback<ResponseSearchMovie>() {
                @Override
                public void onResponse(Call<ResponseSearchMovie> call, Response<ResponseSearchMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_SEARCH_MOVIE, response.body());
                }

                @Override
                public void onFailure(Call<ResponseSearchMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_SEARCH_MOVIE, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getMoviesNowPlaying(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {


            Call<ResponsePopularMovie> call = getApi().nowPlayingMovies(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponsePopularMovie>() {
                @Override
                public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_NOW_PLAYING, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_NOW_PLAYING, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }

    public void getUpcomingMovies(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {


            Call<ResponsePopularMovie> call = getApi().nowPlayingMovies(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponsePopularMovie>() {
                @Override
                public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_UPCOMING_MOVIES, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_UPCOMING_MOVIES, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }


    public void getTvPopularShows(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseTvPopular> call = getApi().tvPopularShows(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponseTvPopular>() {
                @Override
                public void onResponse(Call<ResponseTvPopular> call, Response<ResponseTvPopular> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_POPULAR, response.body());
                }

                @Override
                public void onFailure(Call<ResponseTvPopular> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_POPULAR, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getTvTopRated(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseTvPopular> call = getApi().tvTopRated(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponseTvPopular>() {
                @Override
                public void onResponse(Call<ResponseTvPopular> call, Response<ResponseTvPopular> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_TOP_RATED, response.body());
                }

                @Override
                public void onFailure(Call<ResponseTvPopular> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_TOP_RATED, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }


    }

    public void getOnTvMovies(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseTvPopular> call = getApi().onTvMovies(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponseTvPopular>() {
                @Override
                public void onResponse(Call<ResponseTvPopular> call, Response<ResponseTvPopular> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_ON_TV, response.body());
                }

                @Override
                public void onFailure(Call<ResponseTvPopular> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_ON_TV, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }


    }

    public void getAiringMoviesToday(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseTvPopular> call = getApi().tvAirMovieToday(Constants.API_KEY,
                    "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponseTvPopular>() {
                @Override
                public void onResponse(Call<ResponseTvPopular> call, Response<ResponseTvPopular> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_AIR_MOVIE_TODAY, response.body());
                }

                @Override
                public void onFailure(Call<ResponseTvPopular> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_AIR_MOVIE_TODAY, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getTvShowDetails(String tvId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseTvDetails> call = getApi().tvDetails(tvId,
                    Constants.API_KEY,
                    "en-US");
            call.enqueue(new Callback<ResponseTvDetails>() {
                @Override
                public void onResponse(Call<ResponseTvDetails> call, Response<ResponseTvDetails> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_DETAILS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseTvDetails> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_DETAILS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }

    public void getTvVideos(String mId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseVideo> call = getApi().tvVideos(mId, Constants.API_KEY);
            call.enqueue(new Callback<ResponseVideo>() {
                @Override
                public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_VIDEOS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseVideo> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_VIDEOS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }

    public void getTvReviews(String mId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseRecommendations> call = getApi().tvReviews(mId, Constants.API_KEY, "en-us", 1);
            call.enqueue(new Callback<ResponseRecommendations>() {
                @Override
                public void onResponse(Call<ResponseRecommendations> call, Response<ResponseRecommendations> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_REVIEW, response.body());
                }

                @Override
                public void onFailure(Call<ResponseRecommendations> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_REVIEW, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void searchTvShows(String searchQuery) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseSearchTv> call = getApi().searchTvShows(Constants.API_KEY, "en-US", searchQuery);
            call.enqueue(new Callback<ResponseSearchTv>() {
                @Override
                public void onResponse(Call<ResponseSearchTv> call, Response<ResponseSearchTv> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_SEARCH_TV_SHOWS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseSearchTv> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_SEARCH_TV_SHOWS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void searchPeoples(String searchQuery, int page) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponsePeople> call = getApi().searchPeoples(Constants.API_KEY, "en-US", searchQuery, page, "IN");
            call.enqueue(new Callback<ResponsePeople>() {
                @Override
                public void onResponse(Call<ResponsePeople> call, Response<ResponsePeople> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_SEARCH_PEOPLE, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePeople> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_SEARCH_PEOPLE, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getPopularPeople(int page) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponsePeople> call = getApi().popularPeople(Constants.API_KEY, "en-US", page);
            call.enqueue(new Callback<ResponsePeople>() {
                @Override
                public void onResponse(Call<ResponsePeople> call, Response<ResponsePeople> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_POPULAR_PEOPLE, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePeople> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_POPULAR_PEOPLE, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }


    public void getLatestPeople(int mCurrentPage) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponsePeople> call = getApi().latestPeople(Constants.API_KEY, "en-US", mCurrentPage);
            call.enqueue(new Callback<ResponsePeople>() {
                @Override
                public void onResponse(Call<ResponsePeople> call, Response<ResponsePeople> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_LATEST_PEOPLE, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePeople> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_LATEST_PEOPLE, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }

    public void getPeopleDetails(String peopleId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponsePeopleDetails> call = getApi().peopleDetails(peopleId,
                    Constants.API_KEY,
                    "en-US");
            call.enqueue(new Callback<ResponsePeopleDetails>() {
                @Override
                public void onResponse(Call<ResponsePeopleDetails> call, Response<ResponsePeopleDetails> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_PEOPLE_DETAILS, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePeopleDetails> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_PEOPLE_DETAILS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getSimilarMovies(String mId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponsePopularMovie> call = getApi().similarMovies(mId, Constants.API_KEY);
            call.enqueue(new Callback<ResponsePopularMovie>() {
                @Override
                public void onResponse(Call<ResponsePopularMovie> call, Response<ResponsePopularMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_SIMILAR_MOVIES, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePopularMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_SIMILAR_MOVIES, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }

    public void getSimilarTvShows(String mId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseTvPopular> call = getApi().similarTvShows(mId, Constants.API_KEY);
            call.enqueue(new Callback<ResponseTvPopular>() {
                @Override
                public void onResponse(Call<ResponseTvPopular> call, Response<ResponseTvPopular> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_SIMILAR_TV_SHOWS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseTvPopular> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_SIMILAR_TV_SHOWS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }


    }


    public void getPeopleMovieCredits(int id) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponsePersonMovie> call = getApi().personMovieCredits(id, Constants.API_KEY, "en");
            call.enqueue(new Callback<ResponsePersonMovie>() {
                @Override
                public void onResponse(Call<ResponsePersonMovie> call, Response<ResponsePersonMovie> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_PEOPLE_MOVIE_CREDITS, response.body());
                }

                @Override
                public void onFailure(Call<ResponsePersonMovie> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_PEOPLE_MOVIE_CREDITS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }

    }

    public void getCastingOfMovies(String mId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseCastMovies> call = getApi().getMovieCasting(mId, Constants.API_KEY);
            call.enqueue(new Callback<ResponseCastMovies>() {
                @Override
                public void onResponse(Call<ResponseCastMovies> call, Response<ResponseCastMovies> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_MOVIE_CREDITS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseCastMovies> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_MOVIE_CREDITS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }

    public void getCastingOfTvShows(String mId) {
        if (ConnectionDetector.isNetworkAvailable(_context)) {
            Call<ResponseCastTVShows> call = getApi().getShowsCasting(mId, Constants.API_KEY);
            call.enqueue(new Callback<ResponseCastTVShows>() {
                @Override
                public void onResponse(Call<ResponseCastTVShows> call, Response<ResponseCastTVShows> response) {
                    apiHitListener.onSuccessResponse(ApiIds.ID_TV_SHOWS_CREDITS, response.body());
                }

                @Override
                public void onFailure(Call<ResponseCastTVShows> call, Throwable t) {
                    apiHitListener.onFailResponse(ApiIds.ID_TV_SHOWS_CREDITS, t.getMessage());
                }
            });
        } else {
            apiHitListener.networkNotAvailable();
        }
    }
}