package com.example.pethome.ui.dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pethome.MainActivity;
import com.example.pethome.R;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    private Bundle get_bundle;
    private Button info_edit,info_back;
    private ImageView in_img;
    private TextView in_name,in_date,in_place,in_phone,in_feature,in_info;
    DbAdapter dbAdapter;
    Cursor cursor;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //初始化ActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("流浪通報");

        in_img = findViewById(R.id.in_img);
        in_name = findViewById(R.id.in_name);
        in_date = findViewById(R.id.in_date);
        in_place = findViewById(R.id.in_place);
        in_phone = findViewById(R.id.in_phone);
        in_feature = findViewById(R.id.in_feature);
        in_info = findViewById(R.id.in_info);
        info_back = findViewById(R.id.info_back);

        info_back.setOnClickListener(this);

        dbAdapter = new DbAdapter(this);
        //SQLite傳詳細資料
        get_bundle = this.getIntent().getExtras();
            int index = get_bundle.getInt("item_id");
            cursor = dbAdapter.queryByID(index);
            in_img.setImageBitmap(convertStringToIcon(cursor.getString(cursor.getColumnIndexOrThrow("img"))));
            in_name.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            in_date.setText(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            in_place.setText(cursor.getString(cursor.getColumnIndexOrThrow("place")));
            in_phone.setText(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
            in_feature.setText(cursor.getString(cursor.getColumnIndexOrThrow("feature")));
            in_info.setText(cursor.getString(cursor.getColumnIndexOrThrow("info")));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.info_back: //返回按鍵
                Intent i  = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }//onClick結束
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
            Intent i  = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }return true;
    }


    //Actionbar設置 返回上一頁
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i  = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
        }
        return true;
    }
}
