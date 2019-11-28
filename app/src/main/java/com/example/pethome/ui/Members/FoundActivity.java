package com.example.pethome.ui.Members;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.pethome.R;
import com.example.pethome.ui.dashboard.DbAdapter;

import java.util.ArrayList;

public class FoundActivity extends AppCompatActivity {

    ListView listView;
    private DbAdapter dbAdapter;
    foundAdapter foundAdapter;
    ArrayList<found> founds;
    Cursor cursor;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found);

        //初始化ActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的發現");

        founds = new ArrayList<>();
        listView = findViewById(R.id.listview);
        dbAdapter = new DbAdapter(this);
        displayList();
    }
    private void displayList() {
        cursor = dbAdapter.littlelist();
        if(cursor.moveToFirst()){
            do{
                founds.add(new found(
                        cursor.getInt(0),
                        convertStringToIcon(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            }while(cursor.moveToNext());
        }
        cursor.moveToFirst();
        foundAdapter = new foundAdapter(this,founds);
        listView.setAdapter(foundAdapter);
    }
    public static Bitmap convertStringToIcon(String st)
    {
        // OutputStream out  String轉換Bitmap;
        Bitmap bitmap = null;
        try
        {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Log.e("bitmap", String.valueOf(bitmap));
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    //攔截設定返回鍵 回上一頁
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
        }return true;
    }
    //Actionbar設置 返回上一頁
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                finish();
        }
        return true;
    }
}
