package com.example.appchamcong.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appchamcong.DTO.Nangluc;
import com.example.appchamcong.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class NangLucAdapter extends BaseAdapter {

    SQLiteDatabase database;

    private Fragment context;
    private int layout;
    public static List<Nangluc> nanglucList;
    int id;

    public NangLucAdapter(Fragment context, int layout, List<Nangluc> nanglucList) {
        this.context = context;
        this.layout = layout;
        this.nanglucList = nanglucList;
    }


    @Override
    public int getCount() {
        return nanglucList.size();
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
        TextView txtManhanvien, txtTennhanvien,txtXepLoai;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtManhanvien = (TextView) view.findViewById(R.id.txtManhanvien);
            holder.txtTennhanvien = (TextView) view.findViewById(R.id.txtTennhanvien);
            holder.txtXepLoai = (TextView) view.findViewById(R.id.txtXepLoai);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Nangluc nangluc = nanglucList.get(i);
        holder.txtManhanvien.setText(nangluc.getMANHANVIEN());
        holder.txtTennhanvien.setText(nangluc.getTENNHANVIEN());
        if (nangluc.getXEPLOAI()>=80)
        {
            holder.txtXepLoai.setText("A");

        }else  if (nangluc.getXEPLOAI()>=60)
        {
            holder.txtXepLoai.setText("B");
        }else  if (nangluc.getXEPLOAI()>=40)
        {
            holder.txtXepLoai.setText("C");
        }else  if (nangluc.getXEPLOAI()>=20)
        {
            holder.txtXepLoai.setText("D");
        }
        else {
            holder.txtXepLoai.setText("Chưa đủ điều kiện");
        }

        return view;
    }

}
