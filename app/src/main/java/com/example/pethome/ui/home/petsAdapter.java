package com.example.pethome.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pethome.MainActivity;
import com.example.pethome.R;

import java.util.ArrayList;


public class petsAdapter extends RecyclerView.Adapter<petsAdapter.ViewHolder> {

    private ArrayList<pets> pets ;
    Context context;

    final Bundle bag=new Bundle();
    Intent intent;

    public petsAdapter(Context context, ArrayList<pets> pets) {
        this.pets=pets;
        this.context=context;

    }

    @NonNull
    @Override
    public petsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull petsAdapter.ViewHolder holder, int position) {

        holder.m_pic.setImageResource(pets.get(position).getPic());
        holder.m_variety.setText(pets.get(position).getVariety());
        holder.m_category.setText(pets.get(position).getCategory());
        holder.m_gender.setText(pets.get(position).getGender());
        holder.m_origin.setText(pets.get(position).getOrigin());
        holder.m_color.setText(pets.get(position).getColor());
        holder.m_open.setText(pets.get(position).getOpen());
        holder.m_mechanism.setText(pets.get(position).getMechanism());
        holder.m_phone.setText(pets.get(position).getPhone());
        holder.m_address.setText(pets.get(position).getAddress());
        holder.m_description.setText(pets.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView m_pic;
        private TextView m_variety;
        private TextView m_category;
        private TextView m_gender;
        private TextView m_origin;
        private TextView m_color;
        private TextView m_open;
        private TextView m_mechanism;
        private TextView m_phone;
        private TextView m_address;
        private TextView m_description;
        private Button m_check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_pic=itemView.findViewById(R.id.img);
            m_variety=itemView.findViewById(R.id.variety);
            m_category=itemView.findViewById(R.id.category);
            m_gender=itemView.findViewById(R.id.gender);
            m_origin=itemView.findViewById(R.id.origin);
            m_color = itemView.findViewById(R.id.color);
            m_open = itemView.findViewById(R.id.open);
            m_mechanism = itemView.findViewById(R.id.mechanism);
            m_phone = itemView.findViewById(R.id.phone);
            m_address = itemView.findViewById(R.id.address);
            m_description = itemView.findViewById(R.id.description);

            m_check = itemView.findViewById(R.id.check);
            m_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String user  = ((MainActivity)context).memberUser;

                    if (user.equals("OK")){
                    intent= new Intent(v.getContext(),PetDetails.class);
                    int img = pets.get(getAdapterPosition()).getPic();
                    String va = m_variety.getText().toString();
                    String ca = m_category.getText().toString();
                    String ge = m_gender.getText().toString();
                    String or = m_origin.getText().toString();
                    String co = m_color.getText().toString();
                    String op = m_open.getText().toString();
                    String me = m_mechanism.getText().toString();
                    String ph = m_phone.getText().toString();
                    String ad = m_address.getText().toString();
                    String de = m_description.getText().toString();

                    bag.putString("TYPE","check");
                    bag.putInt("PIC",img);
                    bag.putString("VARIETY",va);
                    bag.putString("CATEGORY",ca);
                    bag.putString("GENDER",ge);
                    bag.putString("ORIGIN",or);
                    bag.putString("COLOR",co);
                    bag.putString("OPEN",op);
                    bag.putString("MECHANISM",me);
                    bag.putString("PHONE",ph);
                    bag.putString("ADDRESS",ad);
                    bag.putString("DESCRIPTION",de);

                    intent.putExtras(bag);
                    context.startActivity(intent);

                    }else if(user.equals("null")) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setTitle("請登入會員")

                                .setCancelable(true);
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {     }
                        });
                        dialog.show();
                    }
                }
            });
        }
    }
    }


