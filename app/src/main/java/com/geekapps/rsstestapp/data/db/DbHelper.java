package com.geekapps.rsstestapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rss_content";

    public static final String TABLE_AUDIOBOOKS = "audiobooks";
    public static final String TABLE_MOVIES = "movies";
    public static final String TABLE_PODCASTS = "podcasts";
    public static final String TABLE_MEDIA_DETAIL_INFORMATION = "media_detail_information";

    public static final String FIELD_ID = "id";
    public static final String FIELD_ARTIST = "artist";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_LOGO = "logo";
    public static final String FIELD_IS_FAVOURITE = "is_favourite";
    public static final String FIELD_POSITION = "position";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_CURRENCY = "currency";
    public static final String FIELD_RELEASE_DATE = "release_date";
    public static final String FIELD_GENRE = "genre";
    public static final String FIELD_DESCRIPTION = "description";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createAudiobooksTable(sqLiteDatabase);
        createMoviesTable(sqLiteDatabase);
        createPodcastsTable(sqLiteDatabase);
        createMediaDetailInformationTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIOBOOKS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PODCASTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIA_DETAIL_INFORMATION);

        onCreate(sqLiteDatabase);
    }

    private void createAudiobooksTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_AUDIOBOOKS + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT,"
                + FIELD_IS_FAVOURITE + " INTEGER," + FIELD_POSITION + " INTEGER" + ")";
        sqLiteDatabase.execSQL(request);
    }

    private void createMoviesTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_MOVIES + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT,"
                + FIELD_IS_FAVOURITE + " INTEGER," + FIELD_POSITION + " INTEGER" + ")";
        sqLiteDatabase.execSQL(request);
    }

    private void createPodcastsTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_PODCASTS + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT,"
                + FIELD_IS_FAVOURITE + " INTEGER," + FIELD_POSITION + " INTEGER" + ")";
        sqLiteDatabase.execSQL(request);
    }

    private void createMediaDetailInformationTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_MEDIA_DETAIL_INFORMATION + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT,"
                + FIELD_PRICE + " REAL," + FIELD_CURRENCY + " TEXT,"
                + FIELD_COUNTRY + " TEXT," + FIELD_RELEASE_DATE + " TEXT,"
                + FIELD_GENRE + " TEXT," + FIELD_DESCRIPTION + " TEXT" + ")";
        sqLiteDatabase.execSQL(request);
    }
}
