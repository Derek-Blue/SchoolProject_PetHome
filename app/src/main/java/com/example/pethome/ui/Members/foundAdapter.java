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

public class foundAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private ArrayList<found> founds;

    public foundAdapter(Context context, ArrayList<found> founds) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.founds = founds;
    }
    @Override
    public int getCount() {
        return founds.size();
    }

    @Override
    public Object getItem(int position) {
        return founds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return founds.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        found found = (found) getItem(position);
        convertView = inflater.inflate(R.layout.foundcard,null);

        ImageView f_img=convertView.findViewById(R.id.imgimg);
        TextView f_date=convertView.findViewById(R.id.date);
        TextView f_place=convertView.findViewById(R.id.place);
        TextView f_feature=convertView.findViewById(R.id.feature);

        f_img.setImageBitmap(found.getImg());
        f_date.setText(found.getDate());
        f_place.setText(found.getPlace());
        f_feature.setText(found.getFeature());

        return convertView;
    }
}
