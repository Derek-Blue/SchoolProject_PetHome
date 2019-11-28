package com.example.pethome.ui.Members;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbFavorite extends SQLiteOpenHelper {

    public static final String KEY_ID = "_id";

    public static final String KEY_PIC = "pic";

    public static final String KEY_VARIETY = "variety";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_ORIGIN = "origin";
    public static final String KEY_COLOR = "color";
    public static final String KEY_OPEN = "open";
    public static final String KEY_MECHANISM = "mechanism";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_DESCRIPTION = "description";

    public static final String DATABASE_NAME = "Memos";
    public static final String TABLE_NAME = "memo";
    public static final int DB_VERSION = 9;

    public DbFavorite(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                KEY_ID + " integer PRIMARY KEY autoincrement," +
                KEY_PIC + "," +
                KEY_VARIETY + "," + KEY_CATEGORY + "," +
                KEY_GENDER + "," + KEY_ORIGIN + "," +
                KEY_COLOR + "," + KEY_OPEN + "," +
                KEY_MECHANISM + "," + KEY_PHONE + "," +
                KEY_ADDRESS + "," + KEY_DESCRIPTION + ");";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
