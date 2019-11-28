package com.example.pethome.ui.dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pethome.MainActivity;
import com.example.pethome.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    View view;
    Intent intent;
    FloatingActionButton f_btn;
    RecyclerView petList;
    private DbAdapter dbAdapter;
    ArrayList<Pet> arr_pet = new ArrayList<>();
    Cursor cursor;
    private RecyclerAdapter dataSimpleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return view;
    }
    public void onStart(){
        f_btn = getView().findViewById(R.id.f_btn);
        petList = getView().findViewById(R.id.pet_list);
        dbAdapter = new DbAdapter(view.getContext());

        f_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(view.getContext(),EditActivity.class);
                intent.putExtra("type","add");
                startActivity(intent);
                getActivity().finish();

            }
        });//新增通報按鍵監聽結束
        desplay();
        super.onStart();
    }
    public void desplay(){
        cursor = dbAdapter.List_pet();
        if (cursor.moveToFirst()) {
            do {
                arr_pet.add(new Pet(cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        convertStringToIcon(cursor.getString(cursor.getColumnIndexOrThrow("img"))),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("place")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("feature")),
                        cursor.getString(cursor.getColumnIndexOrThrow("info"))));
            } while(cursor.moveToNext());
        }// if結束
        cursor.moveToFirst();
        dataSimpleAdapter = new RecyclerAdapter(view.getContext(),arr_pet);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        petList.setLayoutManager(layoutManager);
        petList.setAdapter(dataSimpleAdapter);

    }//desplay結束

    public static Bitmap convertStringToIcon(String st)
    {
        // OutputStream out    String 轉換 Bitmap;
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
}