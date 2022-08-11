package com.example.appchamcong.Adapter;

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

public class QuanLyTaiKhoanAdapter extends RecyclerView.Adapter<QuanLyTaiKhoanAdapter.Viewholder>{
    ArrayList<TaiKhoan> listTaiKhoan;
    Fragment context;
    public static int position;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public QuanLyTaiKhoanAdapter(Fragment context,ArrayList<TaiKhoan> listTaiKhoan) {
        this.listTaiKhoan = listTaiKhoan;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manage_user,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        TaiKhoan taiKhoan = listTaiKhoan.get(position);

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            holder.imgV_Hinh_qlTaikhoan.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            // chuyen byte[] -> ve bitmap
            byte[] hinhAnh = taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            holder.imgV_Hinh_qlTaikhoan.setImageBitmap(bitmap);
        }
        holder.txtV_Tennhanvien_qltaikhoan.setText(taiKhoan.getTENNGUOIDUNG());

        if (taiKhoan.getTINHTRANG() == 0) {
            holder.txtV_Tinhtrang_qlTaikhoan.setText("Chờ duyệt");
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
        ImageView imgV_Hinh_qlTaikhoan;
        TextView txtV_Tennhanvien_qltaikhoan, txtV_Tinhtrang_qlTaikhoan;
        CardView viewholder_qltaikhoan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Tennhanvien_qltaikhoan = itemView.findViewById(R.id.txtV_Tennhanvien_qltaikhoan);
            txtV_Tinhtrang_qlTaikhoan = itemView.findViewById(R.id.txtV_Tinhtrang_qlTaikhoan);
            imgV_Hinh_qlTaikhoan = itemView.findViewById(R.id.imgV_Hinh_qltaikhoan);
            viewholder_qltaikhoan = itemView.findViewById(R.id.viewholder_qltaikhoan);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_qltaikhoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_qltaikhoan.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(menu.NONE, R.id.iSua,
                    menu.NONE, "Xem tài khoản");
        }
    }
}
