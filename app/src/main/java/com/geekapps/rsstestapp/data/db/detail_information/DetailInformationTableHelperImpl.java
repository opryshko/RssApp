package com.geekapps.rsstestapp.data.db.detail_information;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.geekapps.rsstestapp.data.db.BaseDbHelper;
import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformationItem;

import java.util.ArrayList;
import java.util.List;

public class DetailInformationTableHelperImpl extends BaseDbHelper implements DetailInformationTableHelper {

    public DetailInformationTableHelperImpl(Context context) {
        super(context);
    }

    @Override
    public void addDetailItem(DetailInformationItem detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_MEDIA_DETAIL_INFORMATION, null, getContentValues(detail));
        db.close();
    }

    @Override
    public void addDetailItems(List<DetailInformationItem> details) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < details.size(); i++)
            db.insert(TABLE_MEDIA_DETAIL_INFORMATION, null, getContentValues(details.get(i)));
        db.close();
    }

    @Override
    public void replaceDetailItem(DetailInformationItem detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.replace(TABLE_MEDIA_DETAIL_INFORMATION, null, getContentValues(detail));
        db.close();
    }

    @Override
    public DetailInformationItem getDetail(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDIA_DETAIL_INFORMATION, new String[]{FIELD_ID,
                        FIELD_NAME, FIELD_ARTIST, FIELD_LOGO, FIELD_PRICE, FIELD_CURRENCY,
                        FIELD_COUNTRY, FIELD_RELEASE_DATE, FIELD_GENRE, FIELD_DESCRIPTION},
                FIELD_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return new DetailInformationItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getDouble(4), cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8), cursor.getString(9));
        }

        return null;
    }

    @Override
    public List<DetailInformationItem> getAllDetails() {
        List<DetailInformationItem> details = new ArrayList<DetailInformationItem>();
        String selectQuery;
        selectQuery = "SELECT  * FROM " + TABLE_MEDIA_DETAIL_INFORMATION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DetailInformationItem detail = new DetailInformationItem(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getDouble(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9));
                details.add(detail);
            } while (cursor.moveToNext());
        }
        db.close();

        return details;
    }

    @Override
    public long getDetailsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_MEDIA_DETAIL_INFORMATION);
        db.close();

        return count;
    }

    @Override
    public int updateDetail(DetailInformationItem detail) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.update(TABLE_MEDIA_DETAIL_INFORMATION, getContentValues(detail), FIELD_ID + " = ?",
                new String[]{String.valueOf(detail.getId())});
    }

    @Override
    public void updateAllDetails(List<DetailInformationItem> details) {
        deleteAllDetails();
        addDetailItems(details);
    }

    @Override
    public void deleteDetail(DetailInformationItem detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDIA_DETAIL_INFORMATION, FIELD_ID + " = ?",
                new String[]{String.valueOf(detail.getId())});
        db.close();
    }

    @Override
    public void deleteAllDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDIA_DETAIL_INFORMATION, null, null);
        db.close();
    }

    protected ContentValues getContentValues(DetailInformationItem detail) {
        ContentValues values = new ContentValues();
        values.put(FIELD_ID, detail.getId());
        values.put(FIELD_NAME, detail.getCollectionName());
        values.put(FIELD_ARTIST, detail.getArtistName());
        values.put(FIELD_LOGO, detail.getArtworkUrl100());
        values.put(FIELD_PRICE, detail.getCollectionPrice());
        values.put(FIELD_CURRENCY, detail.getCurrency());
        values.put(FIELD_COUNTRY, detail.getCountry());
        values.put(FIELD_RELEASE_DATE, detail.getReleaseDate());
        values.put(FIELD_GENRE, detail.getPrimaryGenreName());
        values.put(FIELD_DESCRIPTION, detail.getDescription());

        return values;
    }
}
