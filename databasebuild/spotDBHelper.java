package com.xiaoke.helloandroid.spotdataproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpotDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Spot.db";

    public static final String Create_SpotInfo = "create table SpotInfo("
            + "ID integer primary key autoincrement,"
            + "Name text,"
            + "Categories integer,"                  //景点分类
            + "Grade real,"
            + "Longitude real,"                      //经度
            + "Latitude real,"                       //维度
            + "TimeLength real,"
            + "OpenTime real,"
            + "CloseTime real)";

    private Context mContext;
    public SpotDbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_SpotInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newversion) {
        db.execSQL("drop table if exists SpotInfo");
        onCreate(db);
    }

    public long insertSpot(Spot spot) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Spot.COLUMN_ID, spot.getID());
        values.put(Spot.COLUMN_NAME, spot.getName());
        values.put(Spot.COLUMN_CATEGORIES, spot.getCategories());
        values.put(Spot.COLUMN_GRADE, spot.getGrade());
        values.put(Spot.COLUMN_LONGITUDE, spot.getLongitude());
        values.put(Spot.COLUMN_LATITUDE, spot.getLatitude());
        values.put(Spot.COLUMN_TIMELENGHT, spot.getTimeLength());
        values.put(Spot.COLUMN_OPENTIME, spot.getOpenTime());
        values.put(Spot.COLUMN_CLOSETIME, spot.getCloseTime());

        long id = db.insert(Spot.TABLIE_NAME, null, values);

        db.close();
        return id;
    }

    public Spot getSpot(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Spot.TABLIE_NAME, new String[]{Spot.COLUMN_ID, Spot.COLUMN_NAME, Spot.COLUMN_CATEGORIES, Spot.COLUMN_GRADE, Spot.COLUMN_LONGITUDE,
                      Spot.COLUMN_LATITUDE, Spot.COLUMN_TIMELENGHT, Spot.COLUMN_OPENTIME, Spot.COLUMN_CLOSETIME},
                      Spot.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Spot spot = new Spot(
                cursor.getInt(cursor.getColumnIndex(Spot.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Spot.COLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(Spot.COLUMN_CATEGORIES)),
                cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_GRADE)),
                cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_LONGITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_TIMELENGHT)),
                cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_OPENTIME)),
                cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_CLOSETIME)));
        cursor.close();
        db.close();
        return spot;
    }

    public List<Spot> getAllSpot() {
        List<Spot> spots= new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Spot.TABLIE_NAME + " ORDER BY " + Spot.COLUMN_ID
                + " DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Spot spot = new Spot();
                spot.setID(cursor.getInt(cursor.getColumnIndex(Spot.COLUMN_ID)));
                spot.setName((cursor.getString(cursor.getColumnIndex(Spot.COLUMN_NAME))));
                spot.setCategories(cursor.getInt(cursor.getColumnIndex(Spot.COLUMN_CATEGORIES)));
                spot.setGrade(cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_GRADE)));
                spot.setLongitude(cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_LONGITUDE)));
                spot.setLatitude(cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_LATITUDE)));
                spot.setTimeLength(cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_TIMELENGHT)));
                spot.setOpenTime(cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_OPENTIME)));
                spot.setCloseTime(cursor.getDouble(cursor.getColumnIndex(Spot.COLUMN_CLOSETIME)));

                spots.add(spot);
            }while(cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return spots;
    }
}
