package com.kmdev.flix.retrofilt;

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
import com.kmdev.flix.models.ResponseTvShow;
import com.kmdev.flix.models.ResponseVideo;
import com.kmdev.flix.utils.BaseArguments;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vishvendra.Singh@Brsoftech on 20/6/16.
 */
public interface Rest {
    @GET("movie/popular/")
    Call<ResponsePopularMovie> popularMovies(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                             @Query(BaseArguments.ARG_LANGUAGE) String language,
                                             @Query(BaseArguments.ARG_PAGE) int page);


    @GET("movie/top_rated/")
    Call<ResponsePopularMovie> topRatedMovies(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                              @Query(BaseArguments.ARG_LANGUAGE) String s,
                                              @Query(BaseArguments.ARG_PAGE) int i);

    @GET("movie/" + "{movie_id}")
    Call<ResponseMovieDetails> movieDetails(@Path(BaseArguments.ARG_MOVIE_ID) String movieId,
                                            @Query(BaseArguments.ARG_API_KEY) String apiKey,
                                            @Query(BaseArguments.ARG_LANGUAGE) String language,
                                            @Query(BaseArguments.ARG_APPEND_RESPONSE) String appendToResponse);


    @GET("movie/" + "{movie_id}" + "/reviews")
    Call<ResponseMovieReview> movieReviews(@Path(BaseArguments.ARG_MOVIE_ID) String movieId,
                                           @Query(BaseArguments.ARG_API_KEY) String apiKey
    );

    @GET("movie/" + "{movie_id}" + "/videos")
    Call<ResponseVideo> movieVideos(@Path(BaseArguments.ARG_MOVIE_ID) String movieId,
                                    @Query(BaseArguments.ARG_API_KEY) String apiKey);

    @GET("search/movie")
    Call<ResponseSearchMovie> searchMovie(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                          @Query(BaseArguments.ARG_LANGUAGE) String language,
                                          @Query(BaseArguments.ARG_QUERY) String query);


    @GET("movie/now_playing/")
    Call<ResponsePopularMovie> nowPlayingMovies(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                                @Query(BaseArguments.ARG_LANGUAGE) String language,
                                                @Query(BaseArguments.ARG_PAGE) int page);

    @GET("tv/latest")
    Call<ResponseTvShow> latestTvShows(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                       @Query(BaseArguments.ARG_LANGUAGE) String language);

    @GET("tv/popular/")
    Call<ResponseTvPopular> tvPopularShows(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                           @Query(BaseArguments.ARG_LANGUAGE) String language,
                                           @Query(BaseArguments.ARG_PAGE) int page);

    @GET("tv/top_rated/")
    Call<ResponseTvPopular> tvTopRated(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                       @Query(BaseArguments.ARG_LANGUAGE) String language,
                                       @Query(BaseArguments.ARG_PAGE) int page);

    @GET("tv/" + "{tv_id}")
    Call<ResponseTvDetails> tvDetails(@Path(BaseArguments.ARG_TV_ID) String tvId,
                                      @Query(BaseArguments.ARG_API_KEY) String apiKey,
                                      @Query(BaseArguments.ARG_LANGUAGE) String s);


    @GET("tv/" + "{tv_id}" + "/recommendations")
    Call<ResponseRecommendations> tvReviews(
            @Path(BaseArguments.ARG_TV_ID) String tvId,
            @Query(BaseArguments.ARG_API_KEY) String apiKey,
            @Query(BaseArguments.ARG_LANGUAGE) String language,
            @Query(BaseArguments.ARG_PAGE) int page

    );

    @GET("tv/" + "{tv_id}" + "/videos")
    Call<ResponseVideo> tvVideos(@Path(BaseArguments.ARG_TV_ID) String tvId,
                                 @Query(BaseArguments.ARG_API_KEY) String apiKey);


    @GET("search/tv")
    Call<ResponseSearchTv> searchTvShows(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                         @Query(BaseArguments.ARG_LANGUAGE) String language,
                                         @Query(BaseArguments.ARG_QUERY) String query);

    @GET("tv/airing_today")
    Call<ResponseTvPopular> tvAirMovieToday(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                            @Query(BaseArguments.ARG_LANGUAGE) String language,
                                            @Query(BaseArguments.ARG_PAGE) int mCurrentPage);

    @GET("tv/on_the_air")
    Call<ResponseTvPopular> onTvMovies(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                       @Query(BaseArguments.ARG_LANGUAGE) String language,
                                       @Query(BaseArguments.ARG_PAGE) int mCurrentPage);

    @GET("search/person")
    Call<ResponsePeople> searchPeoples(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                       @Query(BaseArguments.ARG_LANGUAGE) String language,
                                       @Query(BaseArguments.ARG_QUERY) String searchQuery,
                                       @Query(BaseArguments.ARG_PAGE) int page,
                                       @Query(BaseArguments.ARG_REGION) String in);

    @GET("person/popular")
    Call<ResponsePeople> popularPeople(@Query(BaseArguments.ARG_API_KEY) String apiKey,
                                       @Query(BaseArguments.ARG_LANGUAGE) String s,
                                       @Query(BaseArguments.ARG_PAGE) int page);

    @GET("person/latest")
    Call<ResponsePeople> latestPeople(String apiKey, String s, int mCurrentPage);

    @GET("person/" + "{person_id}")
    Call<ResponsePeopleDetails> peopleDetails(@Path(BaseArguments.ARG_PERSON_ID) String peopleId,
                                              @Query(BaseArguments.ARG_API_KEY) String apiKey,
                                              @Query(BaseArguments.ARG_LANGUAGE) String s);

    @GET("movie/" + "{movie_id}" + "/similar")
    Call<ResponsePopularMovie> similarMovies(@Path(BaseArguments.ARG_MOVIE_ID) String mId,
                                             @Query(BaseArguments.ARG_API_KEY) String apiKey);

    @GET("tv/" + "{tv_id}" + "/similar")
    Call<ResponseTvPopular> similarTvShows(@Path(BaseArguments.ARG_TV_ID) String mId,
                                           @Query(BaseArguments.ARG_API_KEY) String apiKey);


    @GET("person/" + "{person_id}" + "/movie_credits")
    Call<ResponsePersonMovie> personMovieCredits(@Path(BaseArguments.ARG_PERSON_ID) int id,
                                                 @Query(BaseArguments.ARG_API_KEY) String apiKey,
                                                 @Query(BaseArguments.ARG_LANGUAGE) String en);

    @GET("movie/" + "{movie_id}" + "/credits")
    Call<ResponseCastMovies> getMovieCasting(@Path(BaseArguments.ARG_MOVIE_ID) String mId,
                                             @Query(BaseArguments.ARG_API_KEY) String apiKey);

    @GET("tv/" + "{tv_id}" + "/credits")
    Call<ResponseCastTVShows> getShowsCasting(@Path(BaseArguments.ARG_TV_ID) String mId,
                                              @Query(BaseArguments.ARG_API_KEY) String apiKey);

















  /*  @GET("/users/{user}/repos")
    Call<List<ResponseCategoryList>> repositories(@Path("user") String username);

    @FormUrlEncoded
    @POST("techwelt/api/web.php/")
    Call<ResponseCategoryList> allCategories(@Field("type") String type);
*/

}
