package com.example.appchamcong.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class ThongKeNhanSuAdapter extends RecyclerView.Adapter<ThongKeNhanSuAdapter.Viewholder>{
    ArrayList<ChamCong> listChamCong;
    Context context;
    public static int position;
    int Tonggiocong;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ThongKeNhanSuAdapter(Context context,ArrayList<ChamCong> listChamCong) {
        this.listChamCong = listChamCong;
        this.context = context;
    }

    @NonNull
    @Override
    public ThongKeNhanSuAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manage_thongkenhansu,parent,false);

        return new ThongKeNhanSuAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeNhanSuAdapter.Viewholder holder, int position) {
        ChamCong chamCong = listChamCong.get(position);


        holder.txtV_Tennhanvien_tkns.setText(chamCong.getTENNV());
        holder.txtV_Thangcong_tkns.setText(chamCong.getTHANGCONG());

        holder.txtV_Tonggiocong_tkns.setText(String.valueOf(chamCong.getGIOCONG()));

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
        TextView txtV_Tennhanvien_tkns, txtV_Thangcong_tkns, txtV_Tonggiocong_tkns;
        CardView viewholder_tkns;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Tennhanvien_tkns = itemView.findViewById(R.id.txtV_Tennhanvien_tkns);
            txtV_Thangcong_tkns = itemView.findViewById(R.id.txtV_Thangcong_tkns);
            txtV_Tonggiocong_tkns = itemView.findViewById(R.id.txtV_Tonggiocong_tkns);
            viewholder_tkns = itemView.findViewById(R.id.viewholder_tkns);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_tkns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_tkns.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
