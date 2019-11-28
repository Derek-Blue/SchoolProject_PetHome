package com.example.pethome.ui.dashboard;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pethome.MainActivity;
import com.example.pethome.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    private Bundle bundle;
    int index,new_img;
    private Button e_btn,ok_btn,back_btn;
    private ImageView e_img;
    private TextView txt_title;
    private EditText e_name,e_date,e_place,e_phone,e_feature,e_info;
    private DbAdapter dbAdapter;
    String new_name,new_date,new_place,new_phone,new_feature,new_info,picturePath;
    private static final int PICK_IMAGE = 1;
    private Uri selectedImage;
    Bitmap bitmap;
    String dbUsePic,dbUseUp;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //初始化ActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("流浪通報");

        initView();
        dbAdapter = new DbAdapter(this); //初始化DbAdapter
        bundle = this.getIntent().getExtras();
        findViewById(R.id.e_btn).setVisibility(View.VISIBLE);

        //判斷是否為編輯
        if (bundle.getString("type").equals("Edit")){
            txt_title.setText("編輯通報");
            index = bundle.getInt("item_id");
            Cursor cursor = dbAdapter.queryByID(index);

            findViewById(R.id.e_btn).setVisibility(View.GONE);
            e_img.setImageBitmap(convertStringToIcon(cursor.getString(cursor.getColumnIndexOrThrow("img"))));
            e_name.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            e_date.setText(cursor.getString(cursor.getColumnIndexOrThrow("date")));
            e_place.setText(cursor.getString(cursor.getColumnIndexOrThrow("place")));
            e_phone.setText(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
            e_feature.setText(cursor.getString(cursor.getColumnIndexOrThrow("feature")));
            e_info.setText(cursor.getString(cursor.getColumnIndexOrThrow("info")));
        }
    }

    public void initView(){
        e_btn = findViewById(R.id.e_btn);
        ok_btn = findViewById(R.id.ok_btn);
        back_btn = findViewById(R.id.back_btn);
        txt_title = findViewById(R.id.txt_title);
        e_img = findViewById(R.id.e_img);
        e_name = findViewById(R.id.e_name);
        e_date = findViewById(R.id.e_date);
        e_place = findViewById(R.id.e_place);
        e_phone = findViewById(R.id.e_phone);
        e_feature = findViewById(R.id.e_feature);
        e_info = findViewById(R.id.e_info);

        //啟用監聽
        back_btn.setOnClickListener(this);
        ok_btn.setOnClickListener(this);
        e_btn.setOnClickListener(this);

    }

    //取得相片後返回的監聽式
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            //抽象資料的接口
            ContentResolver cr = this.getContentResolver();
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(R.id.e_img);
                imageView.setImageBitmap(bitmap);
                dbUsePic = convertIconToString(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //Bitmap轉換String
    public static String convertIconToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] appicon = baos.toByteArray();
        return Base64.encodeToString(appicon, Base64.DEFAULT);
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

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.e_btn:
                if (Build.VERSION.SDK_INT < 19) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "請選擇圖片"), PICK_IMAGE);
                } else {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE);
                }
                break;

            case R.id.back_btn:
                Intent i  = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.ok_btn:

                new_name = e_name.getText().toString();
                new_date = e_date.getText().toString();
                new_place = e_place.getText().toString();
                new_phone = e_phone.getText().toString();
                new_feature = e_feature.getText().toString();
                new_info = e_info.getText().toString();

                //斷判是為編輯還是新增
                if (bundle.getString("type").equals("Edit")){

                    //編輯
                    try {
                        dbAdapter.UpdataPet(index,new_name,new_date,new_place,new_phone,new_feature,new_info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        Intent intent  = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else {

                    //新增
                    try {
                        dbAdapter.CreatePet(dbUsePic,new_name,new_date,new_place,new_phone,new_feature,new_info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        Intent intent  = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
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
