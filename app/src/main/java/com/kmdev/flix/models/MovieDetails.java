package com.kmdev.flix.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kajal on 10/11/2016.
 */
public class MovieDetails implements Parcelable {
    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };
    public String movieTitle;
    public String movieReleaseDate;
    public String movieImagePath;
    public String movieId;
    public boolean video;

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object
     */


    protected MovieDetails(Parcel in) {
        this.movieId = in.readString();
    }

    public MovieDetails(String movieId) {
        this.movieId = movieId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieId);


    }
}
