package com.example.pethome.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.pethome.MainActivity;
import com.example.pethome.R;
import com.example.pethome.ui.Members.DbAdapterFav;

public class PetDetails extends AppCompatActivity implements View.OnClickListener {

    ImageView img,image_love,image_girl,image_boy;
    TextView variety,category,gender,origin,color,open,mechanism,phone,address,description;
    Bundle getbag;
    ActionBar actionBar;
    Button favorite,ret;
    DbAdapterFav dbAdapterFav;
    private int pic;
    int index;
    private String m1 = null;
    Cursor cursor;

    String r_variety,r_category,r_gender,r_origin,r_color,r_open,r_mechanism,r_phone,r_address,r_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        dbAdapterFav = new DbAdapterFav(this);

        //初始化ActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.title_pethome);

        img = findViewById(R.id.img);
        image_love =  findViewById(R.id.image_love);
        image_boy = findViewById(R.id.image_boy);
        image_girl = findViewById(R.id.image_girl);
        variety = findViewById(R.id.variety);
        category = findViewById(R.id.category);
        gender = findViewById(R.id.gender);
        origin = findViewById(R.id.origin);
        color = findViewById(R.id.color);
        open = findViewById(R.id.open);
        mechanism = findViewById(R.id.mechanism);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        description = findViewById(R.id.description);

        favorite = findViewById(R.id.toFavorite);
        favorite.setOnClickListener(this);
        ret = findViewById(R.id.return1);
        ret.setOnClickListener(this);

        getbag = getIntent().getExtras();
        pic = getbag.getInt("PIC");

        m1 = getbag.getString("VARIETY");
        String m2 = getbag.getString("CATEGORY");
        String m3 = getbag.getString("GENDER");
        String m4 = getbag.getString("ORIGIN");
        String m5 = getbag.getString("COLOR");
        String m6 = getbag.getString("OPEN");
        String m7 = getbag.getString("MECHANISM");
        String m8 = getbag.getString("PHONE");
        String m9 = getbag.getString("ADDRESS");
        String m10 = getbag.getString("DESCRIPTION");

        //監控是否在關注中，隱藏/顯示部分元件
        findViewById(R.id.toFavorite).setVisibility(View.VISIBLE);
        findViewById(R.id.remind).setVisibility(View.GONE);
        findViewById(R.id.image_love).setVisibility(View.VISIBLE);
        findViewById(R.id.image_boy).setVisibility(View.GONE);
        findViewById(R.id.image_girl).setVisibility(View.GONE);
        checkMyFav(dbAdapterFav.variety(),m1);

            img.setImageResource(pic);
            variety.setText(m1);
            category.setText(m2);
            gender.setText(m3);
            origin.setText(m4);
            color.setText(m5);
            open.setText(m6);
            mechanism.setText(m7);
            phone.setText(m8);
            address.setText(m9);
            description.setText(m10);

        //圖片圓角
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                BitmapFactory.decodeResource(getResources(),pic));
        roundedBitmapDrawable.getPaint().setAntiAlias(true);
        roundedBitmapDrawable.setCornerRadius(35);
        img.setImageDrawable(roundedBitmapDrawable);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toFavorite: {

                r_variety = variety.getText().toString();
                r_category = category.getText().toString();
                r_gender = gender.getText().toString();
                r_origin = origin.getText().toString();
                r_color = color.getText().toString();
                r_open = open.getText().toString();
                r_mechanism = mechanism.getText().toString();
                r_phone = phone.getText().toString();
                r_address = address.getText().toString();
                r_description = description.getText().toString();
                try {
                    dbAdapterFav.creatDB(pic,r_variety,r_category,r_gender,r_origin,r_color,r_open,r_mechanism,r_phone,r_address,r_description);
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    finish();
                }

                break;
            }
            case R.id.return1:{

                finish();

            }
        }
    }

    //監控是否已經在關注中
    public void checkMyFav(Cursor cursor,String name){

        for(int i =0;i<cursor.getCount();i++){
            String num = cursor.getString(cursor.getColumnIndexOrThrow("variety"));

            if (name.equals(num)){

                findViewById(R.id.toFavorite).setVisibility(View.GONE);
                findViewById(R.id.remind).setVisibility(View.VISIBLE);
                findViewById(R.id.image_love).setVisibility(View.GONE);
                findViewById(R.id.image_boy).setVisibility(View.VISIBLE);
                findViewById(R.id.image_girl).setVisibility(View.VISIBLE);
            } cursor.moveToNext();
        }
    }
}
