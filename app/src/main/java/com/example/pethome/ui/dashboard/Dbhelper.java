package com.example.pethome.ui.dashboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String KEY_ID = "id";
    public static final String KEY_IMG = "img";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_PLACE = "place";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_FEATURE = "feature";
    public static final String KEY_INFO = "info";
    public static final String DATABASE_NAME = "Pets";
    public static final String TABLE_NAME = "pet";
    public static final int DB_VERSION = 33;


    public Dbhelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                KEY_ID + " integer PRIMARY KEY autoincrement," + KEY_IMG + "," +
                 KEY_NAME + "," + KEY_DATE + "," + KEY_PLACE + "," +
                 KEY_PHONE + "," + KEY_FEATURE + "," + KEY_INFO + ");";
            Log.d("SQL",DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
