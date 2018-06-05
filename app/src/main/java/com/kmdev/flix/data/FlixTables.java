package com.kmdev.flix.data;

/**
 * Created by Kajal on 3/5/2017.
 */
public class FlixTables {

    public static class Favourites {
        //table name
        public static final String TABLE_NAME = "myFavourites";
        //table columns
        public static final String COLUMN_FAVOURITE = "favourite";
        //0 for movie ,1 for TV
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ID = "movieId";
        public static final String COLUMN_REVIEW = "review";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_SIMILAR_MOVIES = "similar_movies";


        public static final String COLUMN_CREDITS = "credits";
    }

    public static class People {
        //table name
        public static final String TABLE_NAME = "people";
        //column names
        public static final String COLUMN_FAVOURITE = "favourite";
        public static final String COLUMN_ID = "movieId";
        public static final String COLUMN_PEOPLE_CREDITS = "people_credits";


    }


}
