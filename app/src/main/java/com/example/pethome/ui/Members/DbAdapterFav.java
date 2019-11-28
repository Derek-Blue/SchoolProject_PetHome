package com.example.pethome.ui.Members;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DbAdapterFav {
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

    private DbFavorite mDbFavorite;
    private SQLiteDatabase sqLiteDatabase;
    private final Context context;
    private ContentValues values;

    public DbAdapterFav(Context context) {
        this.context = context;
        open();
    }
    public void  open(){
        mDbFavorite = new DbFavorite(context);
        sqLiteDatabase = mDbFavorite.getWritableDatabase();
    }
    public long creatDB(int pic,String variety,String category,String gender,String origin,String color,
                        String open,String mechanism,String phone,String address, String description){
        long id = 0;
        try {
            values = new ContentValues();
            values.put(KEY_PIC,pic);
            values.put(KEY_VARIETY,variety);
            values.put(KEY_CATEGORY,category);
            values.put(KEY_GENDER,gender);
            values.put(KEY_ORIGIN,origin);
            values.put(KEY_COLOR,color);
            values.put(KEY_OPEN,open);
            values.put(KEY_MECHANISM,mechanism);
            values.put(KEY_PHONE,phone);
            values.put(KEY_ADDRESS,address);
            values.put(KEY_DESCRIPTION,description);
            id = sqLiteDatabase.insert(TABLE_NAME,null,values);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.close();
            Toast.makeText(context,"成功加入 我的關注",Toast.LENGTH_SHORT).show();
        }return id;
    }
    public Cursor listMemos(){
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{KEY_ID,KEY_PIC,KEY_VARIETY,KEY_CATEGORY,
                KEY_GENDER,KEY_ORIGIN,KEY_COLOR,KEY_OPEN,KEY_MECHANISM,KEY_PHONE,KEY_ADDRESS,KEY_DESCRIPTION},
                null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }return  cursor;
    }
    public Cursor variety(){
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{KEY_ID,KEY_VARIETY},
                null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }return  cursor;
    }
    public Cursor queryById(int item_id){
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {KEY_ID,KEY_PIC,KEY_VARIETY,KEY_CATEGORY,
                        KEY_GENDER,KEY_ORIGIN,KEY_COLOR,KEY_OPEN,KEY_MECHANISM,KEY_PHONE,KEY_ADDRESS,KEY_DESCRIPTION},
                KEY_ID + "=" + item_id, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public boolean deleteMemo(int id){
        String[] args = {Integer.toString(id)};
        sqLiteDatabase.delete(TABLE_NAME, "_id= ?",args);
        return true;
    }



}
