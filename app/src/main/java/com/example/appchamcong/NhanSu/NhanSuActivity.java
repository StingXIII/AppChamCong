package com.example.appchamcong.NhanSu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appchamcong.QuanLy.PhongBan;
import com.example.appchamcong.R;

public class NhanSuActivity extends AppCompatActivity {

    TextView txt_phongban_1, txt_phongban_2, txt_phongban_3;
    ImageButton ibtn_Exit_phongnhansu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_su);

        AnhXa();
        SuKien();

    }

    private void SuKien() {
        txt_phongban_1.setOnClickListener(view -> {
            Intent phongban = new Intent(NhanSuActivity.this, PhongBan.class);
            phongban.putExtra("phongban", 1);
            startActivity(phongban);
        });

        txt_phongban_2.setOnClickListener(view -> {
            Intent phongban = new Intent(NhanSuActivity.this, PhongBan.class);
            phongban.putExtra("phongban", 2);
            startActivity(phongban);
        });

        txt_phongban_3.setOnClickListener(view -> {
            Intent phongban = new Intent(NhanSuActivity.this, PhongBan.class);
            phongban.putExtra("phongban", 3);
            startActivity(phongban);
        });

        ibtn_Exit_phongnhansu.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void AnhXa() {
        txt_phongban_1 = findViewById(R.id.txt_phongban_1);
        txt_phongban_2 = findViewById(R.id.txt_phongban_2);
        txt_phongban_3 = findViewById(R.id.txt_phongban_3);
        ibtn_Exit_phongnhansu = findViewById(R.id.ibtn_Exit_phongnhansu);
    }
}