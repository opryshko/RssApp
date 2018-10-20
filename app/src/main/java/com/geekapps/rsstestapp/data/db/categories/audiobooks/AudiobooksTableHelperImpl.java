package com.geekapps.rsstestapp.data.db.categories.audiobooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.geekapps.rsstestapp.data.db.categories.BaseCategoryTableHelper;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.List;

public class AudiobooksTableHelperImpl extends BaseCategoryTableHelper implements AudiobooksTableHelper {

    public AudiobooksTableHelperImpl(Context context) {
        super(context);
    }

    @Override
    public void addAudiobookItem(MediaItem media) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_AUDIOBOOKS, null, getContentValues(media));
        db.close();
    }

    @Override
    public void addAudiobooktems(List<MediaItem> medias) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < medias.size(); i++)
            db.insert(TABLE_AUDIOBOOKS, null, getContentValues(medias.get(i)));
        db.close();
    }

    @Override
    public MediaItem getAudiobook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AUDIOBOOKS, new String[]{FIELD_ID,
                        FIELD_NAME, FIELD_ARTIST, FIELD_LOGO, FIELD_IS_FAVOURITE, FIELD_POSITION}, FIELD_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new MediaItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) != 0, cursor.getInt(5));
    }

    @Override
    public List<MediaItem> getAllAudiobooks() {
        return getSomeMedias(TABLE_AUDIOBOOKS, null);
    }


    @Override
    public List<MediaItem> getFavouriteAudiobooks() {
        return getSomeMedias(TABLE_AUDIOBOOKS, FIELD_IS_FAVOURITE + "=1");
    }

    @Override
    public long getAudiobooksCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_AUDIOBOOKS);
        db.close();
        return count;
    }

    @Override
    public int updateAudiobook(MediaItem media) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.update(TABLE_AUDIOBOOKS, getContentValues(media), FIELD_ID + " = ?",
                new String[]{String.valueOf(media.getId())});
    }

    @Override
    public void updateAllAudiobooks(List<MediaItem> medias) {
        deleteAllAudiobook();
        addAudiobooktems(medias);
    }

    @Override
    public void deleteAudiobook(MediaItem contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUDIOBOOKS, FIELD_ID + " = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    @Override
    public void deleteAllAudiobook() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AUDIOBOOKS, null, null);
        db.close();
    }

    protected ContentValues getContentValues(MediaItem media) {
        ContentValues values = new ContentValues();
        values.put(FIELD_ID, media.getId());
        values.put(FIELD_NAME, media.getName());
        values.put(FIELD_ARTIST, media.getArtistName());
        values.put(FIELD_LOGO, media.getArtworkUrl100());
        values.put(FIELD_IS_FAVOURITE, media.isFavourite() ? 1 : 0);
        values.put(FIELD_POSITION, media.getPosition());

        return values;
    }
}
