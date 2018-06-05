package com.kmdev.flix.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.kmdev.flix.data.FlixTables;
import com.kmdev.flix.models.DataBaseMovieDetails;
import com.kmdev.flix.models.ResponseCastMovies;
import com.kmdev.flix.models.ResponseCastTVShows;
import com.kmdev.flix.models.ResponseMovieDetails;
import com.kmdev.flix.models.ResponseMovieReview;
import com.kmdev.flix.models.ResponsePeopleDetails;
import com.kmdev.flix.models.ResponsePersonMovie;
import com.kmdev.flix.models.ResponsePopularMovie;
import com.kmdev.flix.models.ResponseTvDetails;
import com.kmdev.flix.models.ResponseTvPopular;
import com.kmdev.flix.models.ResponseVideo;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 7;
    //database name
    private static final String DATABASE_NAME = "favouriteManager";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MOVIE = "CREATE TABLE " + FlixTables.Favourites.TABLE_NAME + "("
                + FlixTables.Favourites.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + FlixTables.Favourites.COLUMN_FAVOURITE + " TEXT,"
                + FlixTables.Favourites.COLUMN_TYPE + " INTEGER,"
                + FlixTables.Favourites.COLUMN_SIMILAR_MOVIES + " TEXT,"
                + FlixTables.Favourites.COLUMN_REVIEW + " TEXT,"
                + FlixTables.Favourites.COLUMN_VIDEO + " TEXT, "
                + FlixTables.Favourites.COLUMN_CREDITS + " TEXT"
                + ")";

        String CREATE_TABLE_PEOPLE = "CREATE TABLE " + FlixTables.People.TABLE_NAME + "("
                + FlixTables.People.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + FlixTables.People.COLUMN_FAVOURITE + " TEXT,"
                + FlixTables.People.COLUMN_PEOPLE_CREDITS + " TEXT"
                + ")";


        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_PEOPLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + FlixTables.Favourites.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FlixTables.People.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // code to add the new messaage
    public void addMovies(DataBaseMovieDetails dataBaseMovieDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FlixTables.Favourites.COLUMN_ID, dataBaseMovieDetails.getId());
        if (dataBaseMovieDetails.getType() == 0) {
            values.put(FlixTables.Favourites.COLUMN_FAVOURITE, new Gson().toJson(dataBaseMovieDetails.getResponseMovieDetails())); // Contact Name
            values.put(FlixTables.Favourites.COLUMN_TYPE, new Gson().toJson(dataBaseMovieDetails.getType()));
            values.put(FlixTables.Favourites.COLUMN_SIMILAR_MOVIES, new Gson().toJson(dataBaseMovieDetails.getResponseSimilarMovies()));
            values.put(FlixTables.Favourites.COLUMN_CREDITS, new Gson().toJson(dataBaseMovieDetails.getResponseCastMovies()));

        } else if (dataBaseMovieDetails.getType() == 1) {
            values.put(FlixTables.Favourites.COLUMN_FAVOURITE, new Gson().toJson(dataBaseMovieDetails.getResponseTvDetails())); // Contact Name
            values.put(FlixTables.Favourites.COLUMN_TYPE, new Gson().toJson(dataBaseMovieDetails.getType()));
            values.put(FlixTables.Favourites.COLUMN_SIMILAR_MOVIES, new Gson().toJson(dataBaseMovieDetails.getResponseTvSimilarShows()));
            values.put(FlixTables.Favourites.COLUMN_CREDITS, new Gson().toJson(dataBaseMovieDetails.getResponseCastShows()));

        }

        values.put(FlixTables.Favourites.COLUMN_REVIEW, new Gson().toJson(dataBaseMovieDetails.getResponseMovieReview()));
        values.put(FlixTables.Favourites.COLUMN_VIDEO, new Gson().toJson(dataBaseMovieDetails.getResponseMovieVideo()));
        // Inserting Row
        db.insert(FlixTables.Favourites.TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void addPeople(DataBaseMovieDetails dataBaseMovieDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FlixTables.People.COLUMN_ID, dataBaseMovieDetails.getId());
        values.put(FlixTables.People.COLUMN_FAVOURITE, new Gson().toJson(dataBaseMovieDetails.getResponsePeopleDetails())); // Contact Name
        values.put(FlixTables.People.COLUMN_PEOPLE_CREDITS, new Gson().toJson(dataBaseMovieDetails.getResponsePersonMovie()));
        db.insert(FlixTables.People.TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get all contacts in a list view
    public List<ResponseMovieDetails> getAllMovies() {
        List<ResponseMovieDetails> movieList = new ArrayList<ResponseMovieDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FlixTables.Favourites.TABLE_NAME + " WHERE " +
                FlixTables.Favourites.COLUMN_TYPE + "=0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //   contact.setID(Integer.parseInt(cursor.getString(0)));
                ResponseMovieDetails movieDetail = new Gson().fromJson(cursor.getString(1), ResponseMovieDetails.class);
                // Adding contact to list
                movieList.add(movieDetail);
            } while (cursor.moveToNext());
        }

        // return contact list
        return movieList;
    }

    // code to get all contacts in a list view
    public List<ResponseTvDetails> getAllTvShows() {
        List<ResponseTvDetails> tvDetailsList = new ArrayList<ResponseTvDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FlixTables.Favourites.TABLE_NAME + " WHERE "
                + FlixTables.Favourites.COLUMN_TYPE + "=1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //   contact.setID(Integer.parseInt(cursor.getString(0)));
                ResponseTvDetails tvDetails = new Gson().fromJson(cursor.getString(1), ResponseTvDetails.class);
                // Adding contact to list
                tvDetailsList.add(tvDetails);
            } while (cursor.moveToNext());
        }

        // return contact list
        return tvDetailsList;
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FlixTables.Favourites.TABLE_NAME, null, null);
    }

    // Deleting single contact
    public void deleteMovie(String movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
   /*     db.delete(TABLE_PEOPLE, KEY_ID + " = ?",
                new String[]{String.valueOf(movieDetails.getMov())});
        db.delete("tablename","id=?",new String[]{"1","jack"});*/
        db.execSQL("DELETE FROM " + FlixTables.Favourites.TABLE_NAME + " WHERE " +
                FlixTables.Favourites.COLUMN_ID + "= '" + movieId + "'");
        /*
        this is like useing this command:
    delete from tablename where id='1' and name ='jack'
*/
        db.close();
    }

    public boolean checkISDataExist(String movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql = "SELECT * FROM " + FlixTables.Favourites.TABLE_NAME + " WHERE " +
                FlixTables.Favourites.COLUMN_ID + "=" + movieId;
        cursor = db.rawQuery(sql, null);
        return cursor.getCount() > 0;
    }

    public boolean checkIsPeopleExist(String personId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String sql = "SELECT * FROM " + FlixTables.People.TABLE_NAME + " WHERE " +
                FlixTables.People.COLUMN_ID + "=" + personId;
        cursor = db.rawQuery(sql, null);
        return cursor.getCount() > 0;
    }

    public DataBaseMovieDetails getMovieDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FlixTables.Favourites.TABLE_NAME,
                new String[]{FlixTables.Favourites.COLUMN_ID,
                        FlixTables.Favourites.COLUMN_FAVOURITE,
                        FlixTables.Favourites.COLUMN_SIMILAR_MOVIES,
                        FlixTables.Favourites.COLUMN_REVIEW,
                        FlixTables.Favourites.COLUMN_VIDEO,
                        FlixTables.Favourites.COLUMN_CREDITS
                },
                FlixTables.Favourites.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DataBaseMovieDetails dataBaseMovieDetails = new DataBaseMovieDetails();
        dataBaseMovieDetails.setId(id);
        dataBaseMovieDetails.setResponseSimilarMovies(new Gson().fromJson(cursor.getString(2), ResponsePopularMovie.class));
        dataBaseMovieDetails.setResponseMovieReview(new Gson().fromJson(cursor.getString(3), ResponseMovieReview.class));
        dataBaseMovieDetails.setResponseMovieVideo(new Gson().fromJson(cursor.getString(4), ResponseVideo.class));
        dataBaseMovieDetails.setResponseCastMovies(new Gson().fromJson(cursor.getString(5), ResponseCastMovies.class));
        dataBaseMovieDetails.setResponseMovieDetails(new Gson().fromJson(cursor.getString(1), ResponseMovieDetails.class));

        // return contact
        return dataBaseMovieDetails;
    }

    public DataBaseMovieDetails getShowDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FlixTables.Favourites.TABLE_NAME, new String[]{
                        FlixTables.Favourites.COLUMN_ID,
                        FlixTables.Favourites.COLUMN_FAVOURITE,
                        FlixTables.Favourites.COLUMN_SIMILAR_MOVIES,
                        FlixTables.Favourites.COLUMN_REVIEW,
                        FlixTables.Favourites.COLUMN_VIDEO,
                        FlixTables.Favourites.COLUMN_CREDITS},
                FlixTables.Favourites.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DataBaseMovieDetails dataBaseMovieDetails = new DataBaseMovieDetails();
        dataBaseMovieDetails.setId(id);
        dataBaseMovieDetails.setResponseTvSimilarShows(new Gson().fromJson(cursor.getString(2), ResponseTvPopular.class));
        dataBaseMovieDetails.setResponseMovieReview(new Gson().fromJson(cursor.getString(3), ResponseMovieReview.class));
        dataBaseMovieDetails.setResponseMovieVideo(new Gson().fromJson(cursor.getString(4), ResponseVideo.class));
        dataBaseMovieDetails.setResponseCastShows(new Gson().fromJson(cursor.getString(5), ResponseCastTVShows.class));
        dataBaseMovieDetails.setResponseTvDetails(new Gson().fromJson(cursor.getString(1), ResponseTvDetails.class));

        // return contact
        return dataBaseMovieDetails;
    }


    public List<ResponsePeopleDetails> getAllPeoples() {
        List<ResponsePeopleDetails> responsePeopleDetails = new ArrayList<ResponsePeopleDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FlixTables.People.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //   contact.setID(Integer.parseInt(cursor.getString(0)));
                ResponsePeopleDetails peopleDetails = new Gson().fromJson(cursor.getString(1), ResponsePeopleDetails.class);
                // Adding contact to list
                responsePeopleDetails.add(peopleDetails);
            } while (cursor.moveToNext());

        }

        // return contact list
        return responsePeopleDetails;

    }

    public void deletePerson(String peopleId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + FlixTables.People.TABLE_NAME + " WHERE " +
                FlixTables.People.COLUMN_ID + "= '" + peopleId + "'");
        /*
        this is like useing this command:
    delete from tablename where id='1' and name ='jack'
*/
        db.close();

    }

    public DataBaseMovieDetails getPeopleDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FlixTables.People.TABLE_NAME, new String[]{FlixTables.People.COLUMN_ID,
                        FlixTables.People.COLUMN_FAVOURITE,
                        FlixTables.People.COLUMN_PEOPLE_CREDITS},
                FlixTables.People.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DataBaseMovieDetails dataBaseMovieDetails = new DataBaseMovieDetails();
        dataBaseMovieDetails.setId(id);
        dataBaseMovieDetails.setResponsePersonMovie(new Gson().fromJson(cursor.getString(2), ResponsePersonMovie.class));
        dataBaseMovieDetails.setResponsePeopleDetails(new Gson().fromJson(cursor.getString(1), ResponsePeopleDetails.class));

        // return contact
        return dataBaseMovieDetails;

    }

}
