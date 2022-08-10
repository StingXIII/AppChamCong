package com.example.appchamcong.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.DTO.Nangluc;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NangLucAdapter extends RecyclerView.Adapter<NangLucAdapter.Viewholder> {
    ArrayList<Nangluc> listNangLuc;
    Fragment context;
    public static int position;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public NangLucAdapter(Fragment context, ArrayList<Nangluc> listNangLuc) {
        this.listNangLuc = listNangLuc;
        this.context = context;
    }

    @NonNull
    @Override
    public NangLucAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manage_xeploai,parent,false);

        return new NangLucAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NangLucAdapter.Viewholder holder, int position) {
        Nangluc nangluc = listNangLuc.get(position);

        holder.txtV_Manhanvien_nl.setText(nangluc.getMANHANVIEN());
        holder.txtV_Tennhanvien_nl.setText(nangluc.getTENNHANVIEN());
        if (nangluc.getXEPLOAI()>=80)
        {
            holder.txtV_Xeploai_nl.setText("A");

        }else  if (nangluc.getXEPLOAI()>=60)
        {
            holder.txtV_Xeploai_nl.setText("B");
        }else  if (nangluc.getXEPLOAI()>=40)
        {
            holder.txtV_Xeploai_nl.setText("C");
        }else  if (nangluc.getXEPLOAI()>=20)
        {
            holder.txtV_Xeploai_nl.setText("D");
        }
        else {
            holder.txtV_Xeploai_nl.setText("Chưa đủ điều kiện");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(holder.getPosition());
                holder.itemView .performLongClick();
                //Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        if(listNangLuc != null){
            return listNangLuc.size();
        }
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtV_Manhanvien_nl, txtV_Tennhanvien_nl, txtV_Xeploai_nl;
        CardView viewholder_nl;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Manhanvien_nl = itemView.findViewById(R.id.txtV_Manhanvien_nl);
            txtV_Tennhanvien_nl = itemView.findViewById(R.id.txtV_Tennhanvien_nl);
            txtV_Xeploai_nl = itemView.findViewById(R.id.txtV_Xeploai_nl);
            viewholder_nl = itemView.findViewById(R.id.viewholder_nl);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_nl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_nl.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(menu.NONE, R.id.iXem,
                    menu.NONE, "Chỉnh sửa");
        }
    }
}
