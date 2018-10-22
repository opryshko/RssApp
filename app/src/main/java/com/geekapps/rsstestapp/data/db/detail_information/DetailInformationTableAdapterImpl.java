package com.geekapps.rsstestapp.data.db.detail_information;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.geekapps.rsstestapp.data.db.DbHelper;
import com.geekapps.rsstestapp.data.network.pojo.detail_information.DetailInformationItem;

import java.util.ArrayList;
import java.util.List;

public class DetailInformationTableAdapterImpl implements DetailInformationTableAdapter {

    protected DbHelper dbHelper;

    public DetailInformationTableAdapterImpl(Context context) {
        dbHelper = new DbHelper(context);
    }

    @Override
    public void addDetailItem(DetailInformationItem detail) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION, null, getContentValues(detail));
        db.close();
    }

    @Override
    public void addDetailItems(List<DetailInformationItem> details) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < details.size(); i++)
            db.insert(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION, null, getContentValues(details.get(i)));
        db.close();
    }

    @Override
    public void replaceDetailItem(DetailInformationItem detail) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.replace(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION, null, getContentValues(detail));
        db.close();
    }

    @Override
    public DetailInformationItem getDetail(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION,
                new String[]{dbHelper.FIELD_ID, dbHelper.FIELD_NAME, dbHelper.FIELD_ARTIST,
                        dbHelper.FIELD_LOGO, dbHelper.FIELD_PRICE, dbHelper.FIELD_CURRENCY,
                        dbHelper.FIELD_COUNTRY, dbHelper.FIELD_RELEASE_DATE, dbHelper.FIELD_GENRE,
                        dbHelper.FIELD_DESCRIPTION},
                dbHelper.FIELD_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return new DetailInformationItem(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getDouble(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9));
        }

        return null;
    }

    @Override
    public List<DetailInformationItem> getAllDetails() {
        List<DetailInformationItem> details = new ArrayList<DetailInformationItem>();
        String selectQuery;
        selectQuery = "SELECT  * FROM " + dbHelper.TABLE_MEDIA_DETAIL_INFORMATION;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, dbHelper.TABLE_MEDIA_DETAIL_INFORMATION);
        db.close();

        return count;
    }

    @Override
    public int updateDetail(DetailInformationItem detail) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.update(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION, getContentValues(detail),
                dbHelper.FIELD_ID + " = ?",
                new String[]{String.valueOf(detail.getId())});
    }

    @Override
    public void updateAllDetails(List<DetailInformationItem> details) {
        deleteAllDetails();
        addDetailItems(details);
    }

    @Override
    public void deleteDetail(DetailInformationItem detail) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION, dbHelper.FIELD_ID + " = ?",
                new String[]{String.valueOf(detail.getId())});
        db.close();
    }

    @Override
    public void deleteAllDetails() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_MEDIA_DETAIL_INFORMATION, null, null);
        db.close();
    }

    protected ContentValues getContentValues(DetailInformationItem detail) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.FIELD_ID, detail.getId());
        values.put(dbHelper.FIELD_NAME, detail.getCollectionName());
        values.put(dbHelper.FIELD_ARTIST, detail.getArtistName());
        values.put(dbHelper.FIELD_LOGO, detail.getArtworkUrl100());
        values.put(dbHelper.FIELD_PRICE, detail.getCollectionPrice());
        values.put(dbHelper.FIELD_CURRENCY, detail.getCurrency());
        values.put(dbHelper.FIELD_COUNTRY, detail.getCountry());
        values.put(dbHelper.FIELD_RELEASE_DATE, detail.getReleaseDate());
        values.put(dbHelper.FIELD_GENRE, detail.getPrimaryGenreName());
        values.put(dbHelper.FIELD_DESCRIPTION, detail.getDescription());

        return values;
    }
}
