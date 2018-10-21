package com.geekapps.rsstestapp.data.db.categories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.geekapps.rsstestapp.data.db.DbHelper;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseCategoryTableAdapter implements CategoryTableAdapter {
    protected DbHelper dbHelper;

    public BaseCategoryTableAdapter(Context context) {
        dbHelper = new DbHelper(context);
    }

    protected List<MediaItem> getSomeMedias(String tableName, String condition) {
        List<MediaItem> medias = new ArrayList<MediaItem>();
        String selectQuery;
        if (condition == null)
            selectQuery = "SELECT  * FROM " + tableName;
        else
            selectQuery = "SELECT  * FROM " + tableName + " WHERE " + condition;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MediaItem media = new MediaItem(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getInt(4) != 0, cursor.getInt(5));
                medias.add(media);
            } while (cursor.moveToNext());
        }
        Collections.sort(medias);

        return medias;
    }
}
