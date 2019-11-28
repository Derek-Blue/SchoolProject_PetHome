package com.example.pethome.ui.Members;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.pethome.R;


import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    ListView fav_memo;
    private DbAdapterFav dbAdapterFav;
    FavoriteAdapter_s favoriteAdapter_s;
    ArrayList<Favorite_s> favorites_s;
    Intent intent;
    Cursor cursor;
    ActionBar actionBar;
    int item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //初始化ActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的關注");

        favorites_s = new ArrayList<>();
        fav_memo = findViewById(R.id.list);
        dbAdapterFav = new DbAdapterFav(this);
        displayList();

        fav_memo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.move(position);
                item_id=cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                intent = new Intent();
                intent.putExtra("ITEM",item_id);
                intent.setClass(FavoriteActivity.this, PetDetailsFav.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void displayList() {
        cursor = dbAdapterFav.listMemos();
        if(cursor.moveToFirst()){
            do{
                favorites_s.add(new Favorite_s(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(cursor.getColumnIndexOrThrow("category")),
                        cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                        cursor.getString(cursor.getColumnIndexOrThrow("mechanism"))
                      ));
            }while(cursor.moveToNext());
        }
        cursor.moveToFirst();
        favoriteAdapter_s = new FavoriteAdapter_s(this,favorites_s);
        fav_memo.setAdapter(favoriteAdapter_s);
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
