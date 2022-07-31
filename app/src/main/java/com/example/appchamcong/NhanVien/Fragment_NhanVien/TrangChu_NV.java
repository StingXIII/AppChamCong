package com.example.appchamcong.NhanVien.Fragment_NhanVien;

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
import com.example.appchamcong.NhanVien.GopYActivity;
import com.example.appchamcong.NhanVien.Game_NhanVien;
import com.example.appchamcong.NhanVien.XemCong_NhanVien;
import com.example.appchamcong.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrangChu_NV extends Fragment {

    View view;
    ImageView img_Game, img_Trangchu, img_Gopy, img_Caidat, img_Xemcong;
    TextView txt_Tennguoidung_tt, txt_Chucvunguoidung_tt;
    CircleImageView img_hinhanhnguoidung_tt;

    public TrangChu_NV() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trang_chu__n_v, container, false);

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
        img_Xemcong.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), XemCong_NhanVien.class));
        });

        img_Gopy.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), GopYActivity.class));
        });

        img_Game.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), Game_NhanVien.class));
        });
    }

    private void HienThiThongTin() {
        if (BatDauActivity.taiKhoanDTO.getQUYEN() == 1) {
            if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 1) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: Staff");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 2) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: Supervisor");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 3) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: Manager");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 4) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: BOD");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 5) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: Leader");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 6) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: Senior Leader");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 7) {
                txt_Chucvunguoidung_tt.setText("Chức vụ: Operator");
            }
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

        img_Xemcong = view.findViewById(R.id.img_XemCong);
        img_Gopy = view.findViewById(R.id.img_Gopy);
        img_Game = view.findViewById(R.id.img_Game);
    }
}