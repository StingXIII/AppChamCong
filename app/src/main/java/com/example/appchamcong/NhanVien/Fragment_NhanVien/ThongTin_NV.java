package com.example.appchamcong.NhanVien.Fragment_NhanVien;

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
import com.example.appchamcong.ThongTinNguoiDungActivity;
import com.example.appchamcong.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTin_NV extends Fragment {

    View view;
    TextView txt_Tennguoidung_nv, txt_Chucvunguoidung_nv, txt_Dangxuattaikhoan_nv,
            txt_Thongtingnuoidung_nv, txt_Doimatkhau_nv;
    CircleImageView img_Hinhanhnguoidung_nv;

    public ThongTin_NV() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_thong_tin__n_v, container, false);

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
        txt_Dangxuattaikhoan_nv.setOnClickListener(view -> {
            ShowDialog();
        });

        txt_Thongtingnuoidung_nv.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ThongTinNguoiDungActivity.class));
        });

        txt_Doimatkhau_nv.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), DoiMatKhauActivity.class));
        });
    }

    private void HienThiThongTin() {
        if (BatDauActivity.taiKhoanDTO.getQUYEN() == 1) {
            if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 1) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: Staff");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 2) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: Supervisor");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 3) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: Manager");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 4) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: BOD");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 5) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: Leader");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 6) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: Senior Leader");
            } else if (BatDauActivity.taiKhoanDTO.getCHUCVU() == 7) {
                txt_Chucvunguoidung_nv.setText("Chức vụ: Operator");
            }
        }

        txt_Tennguoidung_nv.setText(BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG());

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            img_Hinhanhnguoidung_nv.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_Hinhanhnguoidung_nv.setImageBitmap(bitmap);
        }
    }

    private void AnhXa() {
        txt_Tennguoidung_nv = view.findViewById(R.id.txt_Tennguoidung_nv);
        txt_Chucvunguoidung_nv = view.findViewById(R.id.txt_Chucvunguoidung_nv);
        txt_Dangxuattaikhoan_nv = view.findViewById(R.id.txt_Dangxuattaikhoan_nv);
        txt_Thongtingnuoidung_nv = view.findViewById(R.id.txt_Thongtingnuoidung_nv);
        txt_Doimatkhau_nv = view.findViewById(R.id.txt_Doimatkhau_nv);
        img_Hinhanhnguoidung_nv = view.findViewById(R.id.img_Hinhanhnguoidung_nv);


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