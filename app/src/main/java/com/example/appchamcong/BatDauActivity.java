package com.example.appchamcong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.Database.Database;

public class BatDauActivity extends AppCompatActivity {

    public static TaiKhoan taiKhoanDTO = new TaiKhoan();
    public static Database database;
    Button btn_Dangnhap, btn_Dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_dau);

        database = new Database(BatDauActivity.this);

        AnhXa();
        SuKien();
    }

    private void SuKien() {
        btn_Dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BatDauActivity.this, DangNhapActivity.class));
            }
        });

        btn_Dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BatDauActivity.this, DangKyActivity.class));
            }
        });
    }

    private void AnhXa() {
        btn_Dangnhap = findViewById(R.id.btn_Dangnhap);
        btn_Dangky = findViewById(R.id.btn_Dangky);
    }
}