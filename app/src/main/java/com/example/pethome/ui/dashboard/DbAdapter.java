package com.example.pethome.ui.dashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DbAdapter {
    public static final String KEY_ID = "id";
    public static final String KEY_IMG = "img";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_PLACE = "place";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_FEATURE = "feature";
    public static final String KEY_INFO = "info";
    public static final String TABLE_NAME = "pet";
    private Dbhelper mdbhelper;
    private SQLiteDatabase mdb;
    private Context mCnx;
    public ContentValues values;

    public DbAdapter(Context mCnx){
        this.mCnx = mCnx;
        open();
    }

    public void open() {
        mdbhelper = new Dbhelper(mCnx);
        mdb = mdbhelper.getWritableDatabase();
    }

    public long CreatePet(String img, String name, String date, String place, String phone, String feature, String info){
        long id =0;
        try{
            values = new ContentValues();
            values.put(KEY_IMG, img);
            values.put(KEY_NAME, name);
            values.put(KEY_DATE, date);
            values.put(KEY_PLACE, place);
            values.put(KEY_PHONE, phone);
            values.put(KEY_FEATURE, feature);
            values.put(KEY_INFO, info);
            id = mdb.insert(TABLE_NAME, null, values);
            Log.d("name ", String.valueOf(img));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            mdb.close();
            Toast.makeText(mCnx,"新增成功",Toast.LENGTH_SHORT).show();
        }
        return id;
    }

    public Cursor List_pet(){
        Cursor mcursor = mdb.query(TABLE_NAME , new String[]{ KEY_ID, KEY_IMG, KEY_NAME, KEY_DATE, KEY_PLACE,
                KEY_PHONE, KEY_FEATURE, KEY_INFO},
                null,null,null,null,null);

        if (mcursor != null){
            mcursor.moveToFirst();
        }
        return mcursor;
    }

    public Cursor littlelist(){
        Cursor mcursor = mdb.query(TABLE_NAME , new String[]{ KEY_ID, KEY_IMG,  KEY_DATE, KEY_PLACE, KEY_FEATURE},
                null,null,null,null,null);

        if (mcursor != null){
            mcursor.moveToFirst();
        }
        return mcursor;
    }
    public Cursor queryByID(int item_id){

        Cursor mcursor = mdb.query(TABLE_NAME , new String[]{KEY_ID,KEY_IMG,KEY_NAME,KEY_DATE,KEY_PLACE,
                KEY_PHONE,KEY_FEATURE,KEY_INFO},
                KEY_ID + "=" + item_id,null,null,null,null);

        if (mcursor != null){
            mcursor.moveToFirst();
        }
        return mcursor;
    }
    //編輯
    public long UpdataPet(int id, String name, String date, String place, String phone, String feature, String info){
        long updata = 0;
        try{
            ContentValues values =new ContentValues();
            values.put(KEY_NAME,name);
            values.put(KEY_DATE,date);
            values.put(KEY_PLACE,place);
            values.put(KEY_PHONE,phone);
            values.put(KEY_FEATURE,feature);
            values.put(KEY_INFO,info);
            String [] ares = {Integer.toString(id)};
            updata = mdb.update(TABLE_NAME,values,"id=" + id,null);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            mdb.close();
            Toast.makeText(mCnx,"編輯成功",Toast.LENGTH_SHORT).show();
        }
        return updata;
    }
    //刪除
    public boolean DeletePet(int id){
        String [] ares ={Integer.toString(id)};
        mdb.delete(TABLE_NAME,"id= ?",ares);
        Toast.makeText(mCnx, "已刪除!", Toast.LENGTH_SHORT).show();
        return true;
    }
}
