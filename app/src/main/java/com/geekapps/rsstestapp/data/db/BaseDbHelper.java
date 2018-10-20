package com.geekapps.rsstestapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseDbHelper extends SQLiteOpenHelper {
    protected static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "rss_content";

    protected static final String TABLE_AUDIOBOOKS = "audiobooks";
    protected static final String TABLE_MOVIES = "movies";
    protected static final String TABLE_PODCASTS = "podcasts";

    protected static final String FIELD_ID = "id";
    protected static final String FIELD_ARTIST = "artist";
    protected static final String FIELD_NAME = "name";
    protected static final String FIELD_LOGO = "logo";
    protected static final String FIELD_IS_FAVOURITE = "is_favourite";
    protected static final String FIELD_POSITION = "position";

    public BaseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createAudiobooksTable(sqLiteDatabase);
        createMoviesTable(sqLiteDatabase);
        createPodcastsTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIOBOOKS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PODCASTS);

        onCreate(sqLiteDatabase);
    }

    private void createAudiobooksTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_AUDIOBOOKS + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT," + FIELD_IS_FAVOURITE + " INTEGER," + FIELD_POSITION + " INTEGER" + ")";
        sqLiteDatabase.execSQL(request);
    }

    private void createMoviesTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_MOVIES + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT," + FIELD_IS_FAVOURITE + " INTEGER," + FIELD_POSITION + " INTEGER" + ")";
        sqLiteDatabase.execSQL(request);
    }

    private void createPodcastsTable(SQLiteDatabase sqLiteDatabase) {
        String request = "CREATE TABLE " + TABLE_PODCASTS + "("
                + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT,"
                + FIELD_ARTIST + " TEXT," + FIELD_LOGO + " TEXT," + FIELD_IS_FAVOURITE + " INTEGER," + FIELD_POSITION + " INTEGER" + ")";
        sqLiteDatabase.execSQL(request);
    }
}
