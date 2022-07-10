package com.example.appchamcong.NhanSu.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.DoiMatKhauActivity;
import com.example.appchamcong.NhanSu.LichSuChamCongActivity;
import com.example.appchamcong.R;
import com.example.appchamcong.ThongTinNguoiDungActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class NguoiDung extends Fragment {

    View view;
    TextView txt_Tennguoidung_us, txt_Chucvunguoidung_us, txt_Dangxuattaikhoan_us,
            txt_Thongtingnuoidung_us, txt_Doimatkhau_us, txt_Lichsuchamcong_us;
    CircleImageView img_Hinhanhnguoidung_us;

    public NguoiDung() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);

        AnhXa();
        HienThiThongTin();
        SuKien();

        return view;
    }

    @Override
    public void onStart() {
        HienThiThongTin();
        super.onStart();
    }

    private void SuKien() {
        txt_Dangxuattaikhoan_us.setOnClickListener(view -> {
            ShowDialog();
        });

        txt_Thongtingnuoidung_us.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ThongTinNguoiDungActivity.class));
        });

        txt_Doimatkhau_us.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), DoiMatKhauActivity.class));
        });

        txt_Lichsuchamcong_us.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), LichSuChamCongActivity.class));
        });
    }

    private void HienThiThongTin() {
        if (BatDauActivity.taiKhoanDTO.getQUYEN() == 1) {
            txt_Chucvunguoidung_us.setText("Chức vụ: Nhân viên");
        } else if (BatDauActivity.taiKhoanDTO.getQUYEN() == 2) {
            txt_Chucvunguoidung_us.setText("Chức vụ: Phòng Nhân Sự");
        }

        txt_Tennguoidung_us.setText("Họ tên: " + BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG());

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            img_Hinhanhnguoidung_us.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_Hinhanhnguoidung_us.setImageBitmap(bitmap);
        }
    }

    private void AnhXa() {
        txt_Tennguoidung_us = view.findViewById(R.id.txt_Tennguoidung_us);
        txt_Chucvunguoidung_us = view.findViewById(R.id.txt_Chucvunguoidung_us);
        txt_Dangxuattaikhoan_us = view.findViewById(R.id.txt_Dangxuattaikhoan_us);
        txt_Thongtingnuoidung_us = view.findViewById(R.id.txt_Thongtingnuoidung_us);
        txt_Doimatkhau_us = view.findViewById(R.id.txt_Doimatkhau_us);
        txt_Lichsuchamcong_us = view.findViewById(R.id.txt_Lichsuchamcong_us);
        img_Hinhanhnguoidung_us = view.findViewById(R.id.img_Hinhanhnguoidung_us);


    }

    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn đăng xuất hay không ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), BatDauActivity.class);
                BatDauActivity.taiKhoanDTO = new TaiKhoan();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}