package com.example.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class spotDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SpotInfo.db";

    public static final String Create_SpotInfo = "create table SpotInfo("
            + "ID integer primary key autoincrement,"
            + "Name text,"
            + "Longitude real,"                      //经度
            + "Latitude real,"                       //维度
            + "TimeLength smallint)";

    public static final String Create_SpotExper = "create table SpotExper("
            + "ID integer primary key autoincrement,"
            + "Name text,"
            + "Nature real,"
            + "Exhibition real,"                    //展馆类
            + "Entertain real,"                     //娱乐类
            + "DelFood real,"                       //美食类
            + "Humanity real,"                      //人文类
            + "WangHong real,"                      //网红类
            + "Shopping real,"
            + "Transport real,"                     //交通便利程度
            + "SnackLevel real,"
            + "Consume integer,"                    //平均消费
            + "VisitorNum integer)";                //人流量

    private static final String Create_UserInfo = "create table UserInfo("
            + "Id integer primary key autoincrement,"
            + "Name text,"
            + "Nickname text,"
            + "Password text,"
            + "Sex bool,"                           //0是女性，1是男性
            + "PhoneNumber integer,"
            + "IDCardNum text,"
            + "Email text)";
    private Context mContext;
    public spotDBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_SpotInfo);
        db.execSQL(Create_SpotExper);
        db.execSQL(Create_UserInfo);
        Toast.makeText(mContext,"Oh fuck you", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newversion) {
        db.execSQL("drop table if exists SpotInfo");
        db.execSQL("drop table if exists SpotExper");
        db.execSQL("drop table if exists UserInfo");
        onCreate(db);
    }
}