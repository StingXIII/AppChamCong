package com.example.appchamcong.Adapter;

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

import com.example.appchamcong.DTO.GopY;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QuanLyGopYAdapter extends RecyclerView.Adapter<QuanLyGopYAdapter.Viewholder>{
    ArrayList<GopY> listGopy;
    Fragment context;
    public static int position;

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public QuanLyGopYAdapter( Fragment context,ArrayList<GopY> listGopy) {
        this.listGopy = listGopy;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manage_gopy,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        GopY gopYDTO = listGopy.get(position);

        holder.imgV_Hinh_qlgopy.setImageResource(R.drawable.baseline_account_circle_24);
        holder.txtV_Tennguoidung_qlgopy.setText(gopYDTO.getTENNGUOIDUNG());
        holder.txtV_Noidung_qlgopy.setText(gopYDTO.getNOIDUNG());

        holder.itemView .setOnClickListener(new View.OnClickListener() {
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
        if(listGopy != null){
            return listGopy.size();
        }
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView txtV_Tennguoidung_qlgopy, txtV_Noidung_qlgopy;
        ImageView imgV_Hinh_qlgopy;
        CardView viewholder_qlgopy;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Tennguoidung_qlgopy = itemView.findViewById(R.id.txtV_Tennguoidung_qlgopy);
            txtV_Noidung_qlgopy = itemView.findViewById(R.id.txtV_Noidung_qlgopy);
            imgV_Hinh_qlgopy = itemView.findViewById(R.id.imgV_Hinh_qlgopy);
            viewholder_qlgopy = itemView.findViewById(R.id.viewholder_qlgopy);

            itemView.setOnCreateContextMenuListener(this);

            viewholder_qlgopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewholder_qlgopy.showContextMenu();
                }
            });
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(menu.NONE, R.id.iSua,
                    menu.NONE, "Xem bài góp ý");
            menu.add(menu.NONE, R.id.iXoa,
                    menu.NONE, "Xóa bài góp ý");
        }
    }
}
