package com.kmdev.flix.models;

/**
 * Created by Kajal on 11/24/2016.
 */
public class DataBaseMovieDetails {
    private ResponseMovieDetails responseMovieDetails;
    private ResponseMovieReview responseMovieReview;
    private ResponseVideo responseMovieVideo;
    private ResponseTvDetails responseTvDetails;
    private ResponsePeopleDetails responsePeopleDetails;
    private int type;
    private int id;
    private ResponsePopularMovie responseSimilarMovies;
    private ResponseTvPopular responseTvSimilarShows;
    private ResponsePersonMovie responsePersonMovie;
    private ResponseCastTVShows responseCastShows;
    private ResponseCastMovies responseCastMovies;

    public ResponseCastTVShows getResponseCastShows() {
        return responseCastShows;
    }

    public void setResponseCastShows(ResponseCastTVShows responseCastShows) {
        this.responseCastShows = responseCastShows;
    }

    public ResponseCastMovies getResponseCastMovies() {
        return responseCastMovies;
    }

    public void setResponseCastMovies(ResponseCastMovies responseCastMovies) {
        this.responseCastMovies = responseCastMovies;
    }


    public ResponsePersonMovie getResponsePersonMovie() {
        return responsePersonMovie;
    }

    public void setResponsePersonMovie(ResponsePersonMovie responsePersonMovie) {
        this.responsePersonMovie = responsePersonMovie;
    }

    public ResponsePeopleDetails getResponsePeopleDetails() {
        return responsePeopleDetails;
    }

    public void setResponsePeopleDetails(ResponsePeopleDetails responsePeopleDetails) {
        this.responsePeopleDetails = responsePeopleDetails;
    }

    public ResponseTvPopular getResponseTvSimilarShows() {
        return responseTvSimilarShows;
    }

    public void setResponseTvSimilarShows(ResponseTvPopular responseTvSimilarShows) {
        this.responseTvSimilarShows = responseTvSimilarShows;
    }

    public ResponsePopularMovie getResponseSimilarMovies() {
        return responseSimilarMovies;
    }

    public void setResponseSimilarMovies(ResponsePopularMovie responseSimilarMovies) {
        this.responseSimilarMovies = responseSimilarMovies;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ResponseTvDetails getResponseTvDetails() {
        return responseTvDetails;
    }

    public void setResponseTvDetails(ResponseTvDetails responseTvDetails) {
        this.responseTvDetails = responseTvDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResponseMovieDetails getResponseMovieDetails() {
        return responseMovieDetails;
    }

    public void setResponseMovieDetails(ResponseMovieDetails responseMovieDetails) {
        this.responseMovieDetails = responseMovieDetails;
    }

    public ResponseMovieReview getResponseMovieReview() {
        return responseMovieReview;
    }

    public void setResponseMovieReview(ResponseMovieReview responseMovieReview) {
        this.responseMovieReview = responseMovieReview;
    }

    public ResponseVideo getResponseMovieVideo() {
        return responseMovieVideo;
    }

    public void setResponseMovieVideo(ResponseVideo responseMovieVideo) {
        this.responseMovieVideo = responseMovieVideo;
    }
}
