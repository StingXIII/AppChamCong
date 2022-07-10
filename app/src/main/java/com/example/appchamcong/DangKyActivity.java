package com.example.appchamcong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.DAO.TaiKhoanDAO;
import com.example.appchamcong.DTO.TaiKhoan;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_Taikhoan_dk,edt_Tennguoidung_dk, edt_Matkhau_dk, edt_Nhaplaimatkhau_dk;
    TextView txt_Quaylaidangnhap;
    Button btn_Dangkytaikhoan;
    TaiKhoanDAO taiKhoanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();
    }

    private void AnhXa() {
        edt_Taikhoan_dk = findViewById(R.id.edt_Taikhoan_dk);
        edt_Tennguoidung_dk = findViewById(R.id.edt_Tennguoidung_dk);
        edt_Matkhau_dk = findViewById(R.id.edt_Matkhau_dk);
        edt_Nhaplaimatkhau_dk = findViewById(R.id.edt_Nhaplaimatkhau_dk);
        btn_Dangkytaikhoan = findViewById(R.id.btn_Dangkytaikhoan);
        txt_Quaylaidangnhap = findViewById(R.id.txt_Quaylaidangnhap);

        btn_Dangkytaikhoan.setOnClickListener(this);
        txt_Quaylaidangnhap.setOnClickListener(this);

        taiKhoanDAO = new TaiKhoanDAO(DangKyActivity.this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txt_Quaylaidangnhap:
                startActivity(new Intent(this, DangNhapActivity.class));
                break;
            case R.id.btn_Dangkytaikhoan:
                TaiKhoan taiKhoanDTO = new TaiKhoan();
                String sTaiKhoan = edt_Taikhoan_dk.getText().toString();
                String sTennguoidung = edt_Tennguoidung_dk.getText().toString();
                String sMatKhau = edt_Matkhau_dk.getText().toString();
                String sNhapLaiMatKhau = edt_Nhaplaimatkhau_dk.getText().toString();

                if (sTaiKhoan == null || sTaiKhoan.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Vui Lòng Nhập Tài Khoản !", Toast.LENGTH_SHORT).show();
                } else if (sTennguoidung == null || sTennguoidung.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui Lòng Nhập Họ Tên !", Toast.LENGTH_SHORT).show();
                } else if (BatDauActivity.database.isTonTaiTaiKhoan(sTaiKhoan)) {
                    Toast.makeText(DangKyActivity.this, "Tên tài khoản đã tồn tại !", Toast.LENGTH_SHORT).show();
                } else if (sMatKhau == null || sMatKhau.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui Lòng Nhập Mật Khẩu !", Toast.LENGTH_SHORT).show();
                } else if (!sMatKhau.equals(sNhapLaiMatKhau)){
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không trùng khớp !", Toast.LENGTH_SHORT).show();
                } else {
                    taiKhoanDTO.setTENTK(sTaiKhoan);
                    taiKhoanDTO.setTENNGUOIDUNG(sTennguoidung);
                    taiKhoanDTO.setMATKHAU(sMatKhau);

                    long kiemtra = taiKhoanDAO.ThemTaiKhoan(taiKhoanDTO);
                    if (kiemtra != 0){
                        Toast.makeText(DangKyActivity.this, "Đăng Ký Thành Công !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DangKyActivity.this, "Đăng Ký Thất Bại !", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}