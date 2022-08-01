package com.example.appchamcong.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChamCongAdapter extends BaseAdapter {
    SQLiteDatabase database;

    private Context context;
    private int layout;
    public static List<ChamCong> chamCongList;
    int id;


    public ChamCongAdapter(Context context, int layout, List<ChamCong> chamCongList) {
        this.context = context;
        this.layout = layout;
        this.chamCongList = chamCongList;
    }

    @Override
    public int getCount() {
        return chamCongList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        TextView txtManhanvien,txtTennhanvien,txtPhongban,txtNgayCong,txtGiocong;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtManhanvien = (TextView) view.findViewById(R.id.txtManhanvien);
            holder.txtTennhanvien = (TextView) view.findViewById(R.id.txtTennhanvien);
            holder.txtPhongban = (TextView) view.findViewById(R.id.txtPhongban);
            holder.txtNgayCong = (TextView) view.findViewById(R.id.txtNgayCong);
            holder.txtGiocong = (TextView) view.findViewById(R.id.txtGiocong);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ChamCong ChamCong = chamCongList.get(i);
        holder.txtManhanvien.setText(ChamCong.getMANV());
        holder.txtTennhanvien.setText(ChamCong.getTENNV());
        holder.txtPhongban.setText(ChamCong.getPHONGBAN());
        holder.txtNgayCong.setText(ChamCong.getNGAYCONG());
        holder.txtGiocong.setText(ChamCong.getGIOCONG()+"");

        return view;
    }
}
