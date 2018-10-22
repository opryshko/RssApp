package com.geekapps.rsstestapp.data.db.categories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public class PodcastsTableAdapterImpl extends BaseCategoryTableAdapter {

    public PodcastsTableAdapterImpl(Context context) {
        super(context);
    }

    @Override
    public void addMediaItem(MediaItem media) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(dbHelper.TABLE_PODCASTS, null, getContentValues(media));
        db.close();
    }

    @Override
    public void addMediaItems(List<MediaItem> medias) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < medias.size(); i++)
            db.insert(dbHelper.TABLE_PODCASTS, null, getContentValues(medias.get(i)));
        db.close();
    }

    @Override
    public MediaItem getMedia(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(dbHelper.TABLE_PODCASTS, new String[]{dbHelper.FIELD_ID,
                        dbHelper.FIELD_NAME, dbHelper.FIELD_ARTIST, dbHelper.FIELD_LOGO,
                        dbHelper.FIELD_IS_FAVOURITE, dbHelper.FIELD_POSITION}, dbHelper.FIELD_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new MediaItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4) != 0, cursor.getInt(5));
    }

    @Override
    public List<MediaItem> getAllMedias() {
        return getSomeMedias(dbHelper.TABLE_PODCASTS, null);
    }


    @Override
    public List<MediaItem> getFavouriteMedias() {

        return getSomeMedias(dbHelper.TABLE_PODCASTS, dbHelper.FIELD_IS_FAVOURITE + "=1");
    }

    @Override
    public long getMediasCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, dbHelper.TABLE_PODCASTS);
        db.close();

        return count;
    }

    @Override
    public int updateMedia(MediaItem media) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.update(dbHelper.TABLE_PODCASTS, getContentValues(media), dbHelper.FIELD_ID + " = ?",
                new String[]{String.valueOf(media.getId())});
    }

    @Override
    public void updateAllMedias(List<MediaItem> medias) {
        deleteAllMedias();
        addMediaItems(medias);
    }

    @Override
    public void deleteMedia(MediaItem media) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_PODCASTS, dbHelper.FIELD_ID + " = ?",
                new String[]{String.valueOf(media.getId())});
        db.close();
    }

    @Override
    public void deleteAllMedias() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_PODCASTS, null, null);
        db.close();
    }

    protected ContentValues getContentValues(MediaItem media) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.FIELD_ID, media.getId());
        values.put(dbHelper.FIELD_NAME, media.getName());
        values.put(dbHelper.FIELD_ARTIST, media.getArtistName());
        values.put(dbHelper.FIELD_LOGO, media.getArtworkUrl100());
        values.put(dbHelper.FIELD_IS_FAVOURITE, media.isFavourite() ? 1 : 0);
        values.put(dbHelper.FIELD_POSITION, media.getPosition());

        return values;
    }
}
