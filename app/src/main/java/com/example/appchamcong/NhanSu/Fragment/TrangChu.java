package com.example.appchamcong.NhanSu.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.CaiDatActivity;
import com.example.appchamcong.NhanSu.ChamCongActivity;
import com.example.appchamcong.GopYActivity;
import com.example.appchamcong.NhanSu.NhanSuActivity;
import com.example.appchamcong.R;
import com.example.appchamcong.NhanSu.TrangChuActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrangChu extends Fragment {

    View view;
    ImageView img_Chamcong, img_Trangchu, img_Gopy, img_Caidat, img_Nhansu;
    TextView txt_Tennguoidung_tt, txt_Chucvunguoidung_tt;
    CircleImageView img_hinhanhnguoidung_tt;

    public TrangChu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        AnhXa();
        HienThiThongTin();
        Sukien();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void Sukien() {
        img_Chamcong.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ChamCongActivity.class));
        });

        img_Trangchu.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), TrangChuActivity.class));
        });

        img_Caidat.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), CaiDatActivity.class));
        });

        img_Gopy.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), GopYActivity.class));
        });

        img_Nhansu.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), NhanSuActivity.class));
        });
    }

    private void HienThiThongTin() {
        if (BatDauActivity.taiKhoanDTO.getQUYEN() == 1) {
            txt_Chucvunguoidung_tt.setText("Chức vụ: Nhân viên");
        } else if (BatDauActivity.taiKhoanDTO.getQUYEN() == 2) {
            txt_Chucvunguoidung_tt.setText("Chức vụ: Phòng Nhân Sự");
        }

        txt_Tennguoidung_tt.setText("Họ tên: " + BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG());

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            img_hinhanhnguoidung_tt.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_hinhanhnguoidung_tt.setImageBitmap(bitmap);
        }
    }

    private void AnhXa() {
        img_hinhanhnguoidung_tt = view.findViewById(R.id.img_hinhanhnguoidung_tt);
        txt_Tennguoidung_tt = view.findViewById(R.id.txt_Tennguoidung_tt);
        txt_Chucvunguoidung_tt = view.findViewById(R.id.txt_Chucvunguoidung_tt);

        img_Chamcong = view.findViewById(R.id.img_Chamcong);
        img_Trangchu = view.findViewById(R.id.img_Trangchu);
        img_Gopy = view.findViewById(R.id.img_Gopy);
        img_Caidat = view.findViewById(R.id.img_Caidat);
        img_Nhansu = view.findViewById(R.id.img_Nhansu);
    }
}