package com.example.appchamcong.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QuanLyXemCongAdapter extends RecyclerView.Adapter<QuanLyXemCongAdapter.Viewholder>{
    ArrayList<ChamCong> listChamCong;
    Context context;
    public static int position;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public QuanLyXemCongAdapter(Context context,ArrayList<ChamCong> listChamCong) {
        this.listChamCong = listChamCong;
        this.context = context;
    }

    @NonNull
    @Override
    public QuanLyXemCongAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manage_chamcong,parent,false);

        return new QuanLyXemCongAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyXemCongAdapter.Viewholder holder, int position) {
        ChamCong chamCong = listChamCong.get(position);

        holder.txtV_Tennhanvien_qlcong.setText(chamCong.getTENNV());
        holder.txtV_Ngaycong_qlcong.setText(chamCong.getNGAYCONG());
        holder.txtV_Giocong_qlcong.setText(String.valueOf(chamCong.getGIOCONG()));

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
        if(listChamCong != null){
            return listChamCong.size();
        }
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtV_Tennhanvien_qlcong, txtV_Ngaycong_qlcong, txtV_Giocong_qlcong;
        CardView viewholder_qlcong;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Tennhanvien_qlcong = itemView.findViewById(R.id.txtV_Tennhanvien_qlcong);
            txtV_Ngaycong_qlcong = itemView.findViewById(R.id.txtV_Ngaycong_qlcong);
            txtV_Giocong_qlcong = itemView.findViewById(R.id.txtV_Giocong_qlcong);
            viewholder_qlcong = itemView.findViewById(R.id.viewholder_qlcong);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_qlcong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_qlcong.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(menu.NONE, R.id.iXemcong,
                    menu.NONE, "Xem công");
        }
    }
}
