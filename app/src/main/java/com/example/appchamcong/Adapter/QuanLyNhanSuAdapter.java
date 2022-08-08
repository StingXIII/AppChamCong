package com.example.appchamcong.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QuanLyNhanSuAdapter extends RecyclerView.Adapter<QuanLyNhanSuAdapter.Viewholder>{
    ArrayList<TaiKhoan> listTaiKhoan;
    Context context;
    public static int position;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public QuanLyNhanSuAdapter(Context context,ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
        this.context = context;
    }

    @NonNull
    @Override
    public QuanLyNhanSuAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manage_staff,parent,false);

        return new QuanLyNhanSuAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyNhanSuAdapter.Viewholder holder, int position) {
        TaiKhoan taiKhoan = listTaiKhoan.get(position);

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            holder.imgV_Hinh_qlnhansu.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            // chuyen byte[] -> ve bitmap
            byte[] hinhAnh = taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            holder.imgV_Hinh_qlnhansu.setImageBitmap(bitmap);
        }
        holder.txtV_Tennhanvien_qlnhansu.setText(taiKhoan.getTENNGUOIDUNG());

        if (taiKhoan.getQUYEN() == 1) {
            if (taiKhoan.getCHUCVU() == 1) {
                holder.txtV_Chucvu_qlnhansu.setText("Staff");
            } else if (taiKhoan.getCHUCVU() == 2) {
                holder.txtV_Chucvu_qlnhansu.setText("Supervisor");
            } else if (taiKhoan.getCHUCVU() == 3) {
                holder.txtV_Chucvu_qlnhansu.setText("Manager");
            } else if (taiKhoan.getCHUCVU() == 4) {
                holder.txtV_Chucvu_qlnhansu.setText("BOD");
            } else if (taiKhoan.getCHUCVU() == 5) {
                holder.txtV_Chucvu_qlnhansu.setText("Leader");
            } else if (taiKhoan.getCHUCVU() == 6) {
                holder.txtV_Chucvu_qlnhansu.setText("Senior Leader");
            } else if (taiKhoan.getCHUCVU() == 7) {
                holder.txtV_Chucvu_qlnhansu.setText("Operator");
            }
        }

        if (taiKhoan.getTINHTRANG() == 1) {
            holder.txtV_Tinhtrang_qlnhansu.setText("Còn làm");
        } else {
            holder.txtV_Tinhtrang_qlnhansu.setText("Đã nghỉ");
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
        if(listTaiKhoan != null){
            return listTaiKhoan.size();
        }
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        ImageView imgV_Hinh_qlnhansu;
        TextView txtV_Tennhanvien_qlnhansu, txtV_Tinhtrang_qlnhansu, txtV_Chucvu_qlnhansu;
        CardView viewholder_qlnhansu;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Tennhanvien_qlnhansu = itemView.findViewById(R.id.txtV_Tennhanvien_qlnhansu);
            txtV_Tinhtrang_qlnhansu = itemView.findViewById(R.id.txtV_Tinhtrang_qlnhansu);
            txtV_Chucvu_qlnhansu = itemView.findViewById(R.id.txtV_Chucvu_qlnhansu);
            imgV_Hinh_qlnhansu = itemView.findViewById(R.id.imgV_Hinh_qlnhansu);
            viewholder_qlnhansu = itemView.findViewById(R.id.viewholder_qlnhansu);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_qlnhansu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_qlnhansu.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(menu.NONE, R.id.iXem,
                    menu.NONE, "Chỉnh sửa");
            menu.add(menu.NONE, R.id.iNghi,
                    menu.NONE, "Nghĩ việc");
        }
    }
}
