package com.example.pethome.ui.Members;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

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
import android.widget.Toast;

import com.example.pethome.R;

public class PetDetailsFav extends AppCompatActivity {

    ImageView img;
    TextView variety,category,gender,origin,color,open,mechanism,phone,address,description;
    Bundle getbag;
    ActionBar actionBar;
    Button favorite;
    DbAdapterFav dbAdapterFav;
    int index;
    Button delete,ret;
    Intent intent;
    int item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details_fav);

        dbAdapterFav = new DbAdapterFav(this);
        //初始化ActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的關注");

        img = findViewById(R.id.img);
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
        delete = findViewById(R.id.delete);
        ret = findViewById(R.id.retu);

        getbag = this.getIntent().getExtras();

        index = getbag.getInt("ITEM");
        Cursor cursor = dbAdapterFav.queryById(index);
        img.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("pic")));
        variety.setText(cursor.getString(cursor.getColumnIndexOrThrow("variety")));
        category.setText(cursor.getString(cursor.getColumnIndexOrThrow("category")));
        gender.setText(cursor.getString(cursor.getColumnIndexOrThrow("gender")));
        origin.setText(cursor.getString(cursor.getColumnIndexOrThrow("origin")));
        color.setText(cursor.getString(cursor.getColumnIndexOrThrow("color")));
        open.setText(cursor.getString(cursor.getColumnIndexOrThrow("open")));
        mechanism.setText(cursor.getString(cursor.getColumnIndexOrThrow("mechanism")));
        phone.setText(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
        address.setText(cursor.getString(cursor.getColumnIndexOrThrow("address")));
        description.setText(cursor.getString(cursor.getColumnIndexOrThrow("description")));

        item_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbAdapterFav.deleteMemo(item_id);
                Toast.makeText(v.getContext(),"已取消關注",Toast.LENGTH_SHORT).show();
                intent = new Intent(PetDetailsFav.this,FavoriteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(PetDetailsFav.this,FavoriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //圖片圓角
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                BitmapFactory.decodeResource(getResources(),cursor.getInt(cursor.getColumnIndexOrThrow("pic"))));
        roundedBitmapDrawable.getPaint().setAntiAlias(true);
        roundedBitmapDrawable.setCornerRadius(35);
        img.setImageDrawable(roundedBitmapDrawable);

    }
    //攔截設定返回鍵 回上一頁
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            intent = new Intent(PetDetailsFav.this,FavoriteActivity.class);
            startActivity(intent);
            finish();
        }return true;
    }

    //Actionbar設置 返回上一頁
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                intent = new Intent(PetDetailsFav.this,FavoriteActivity.class);
                startActivity(intent);
                finish();
        }
        return true;
    }
}
