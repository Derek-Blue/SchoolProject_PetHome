package com.example.pethome.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pethome.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {
    public ArrayList<Pet> pet_lis;
    Bundle bundle = new Bundle();
    private DbAdapter dbAdapter;
    public Cursor cursor;
    Context context;
    private AlertDialog dialog = null;
    AlertDialog.Builder builder =null;


    public RecyclerAdapter(Context context, ArrayList<Pet> pet_lis) {
        this.pet_lis = pet_lis;
        this.context = context;
    }

    // 刪除 list
    public void removeItem(int position){
        pet_lis.remove(position);
        notifyItemRemoved(position);
    }//結束 removeItem

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        Viewholder vh = new Viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.i_img.setImageBitmap(pet_lis.get(position).getImg());
        holder.i_name.setText(pet_lis.get(position).getName());
        holder.i_date.setText(pet_lis.get(position).getDate());
        holder.i_place.setText(pet_lis.get(position).getPlace());
        holder.i_phone.setText(pet_lis.get(position).getPhone());
        holder.i_feature.setText(pet_lis.get(position).getFeature());
        holder.i_info.setText(pet_lis.get(position).getInfo());

    } //onBindViewHolder結束

    @Override
    public int getItemCount() {
        return pet_lis.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView i_img;
        private TextView i_name,i_date,i_place,i_phone,i_feature,i_info;
        private Button i_btn_data,i_btn_edit ;
        public int gotId,gotIdtoDel;

        public Viewholder(@NonNull final View itemView) {
        super(itemView);
            i_img = itemView.findViewById(R.id.i_img);
            i_name = itemView.findViewById(R.id.i_name);
            i_date = itemView.findViewById(R.id.i_date);
            i_place = itemView.findViewById(R.id.i_place);
            i_phone = itemView.findViewById(R.id.i_phone);
            i_feature = itemView.findViewById(R.id.i_feature);
            i_info = itemView.findViewById(R.id.i_info);
            i_btn_data = itemView.findViewById(R.id.i_btn_data);
            i_btn_edit = itemView.findViewById(R.id.i_btn_edit);

            dbAdapter = new DbAdapter(context);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //取得 SQLite的 id
                    gotIdtoDel = pet_lis.get(getAdapterPosition()).getId();
                    dbAdapter.DeletePet(gotIdtoDel);
                    removeItem(getAdapterPosition());

                    return true;
                }
            });

            i_btn_data.setOnClickListener(this);
            i_btn_edit.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.i_btn_data: //設定連接詳細資料按鍵

                    gotId = pet_lis.get(getAdapterPosition()).getId();
                    Intent intent = new Intent();
                    intent.putExtra("item_id",gotId);
                    intent.setClass(v.getContext(),InfoActivity.class);
                    v.getContext().startActivity(intent);
                    ((Activity)context).finish();

                    break;

                case R.id.i_btn_edit: //設定編輯按鍵
                    gotId = pet_lis.get(getAdapterPosition()).getId();
                    intent = new Intent();
                    intent.putExtra("item_id",gotId);
                    intent.putExtra("type","Edit");

                    intent.setClass(v.getContext(),EditActivity.class);
                    v.getContext().startActivity(intent);
                    ((Activity)context).finish();

                    break;
            }
        }
    }
}
