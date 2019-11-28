package com.example.pethome.ui.Members;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pethome.R;

import java.util.ArrayList;

public class FavoriteAdapter_s extends BaseAdapter {

    LayoutInflater inflater;
    private ArrayList<Favorite_s> favorite_s;

    public FavoriteAdapter_s(Context context, ArrayList<Favorite_s> favorites_s) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.favorite_s = favorites_s;
    }
    @Override
    public int getCount() {
        return favorite_s.size();
    }

    @Override
    public Object getItem(int position) {
        return favorite_s.get(position);
    }
    @Override
    public long getItemId(int position) {
        return favorite_s.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Favorite_s favorite_s = (Favorite_s) getItem(position);
        convertView = inflater.inflate(R.layout.favoritecard,null);

        ImageView m_img=convertView.findViewById(R.id.img);
        TextView m_category=convertView.findViewById(R.id.category);
        TextView m_gender=convertView.findViewById(R.id.gender);
        TextView m_origin=convertView.findViewById(R.id.origin);

        m_img.setImageResource(favorite_s.getPic());
        m_category.setText(favorite_s.getCategory());
        m_gender.setText(favorite_s.getGender());
        m_origin.setText(favorite_s.getOrigin());


        return convertView;
    }
}
