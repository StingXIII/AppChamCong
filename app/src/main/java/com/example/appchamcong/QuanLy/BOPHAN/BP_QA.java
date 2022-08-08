package com.example.appchamcong.QuanLy.BOPHAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.appchamcong.QuanLy.PhongBan;
import com.example.appchamcong.R;

public class BP_QA extends AppCompatActivity {

    ImageView img_QA1, img_QA2, img_QA3, img_QA4;
    ImageButton ibtn_Exit_bophan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_qa);

        AnhXa();
        SuKien();
    }

    private void SuKien() {
        img_QA1.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QA.this, PhongBan.class);
            phongban.putExtra("phongban", 1);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_QA2.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QA.this, PhongBan.class);
            phongban.putExtra("phongban", 2);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_QA3.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QA.this, PhongBan.class);
            phongban.putExtra("phongban", 3);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_QA4.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QA.this, PhongBan.class);
            phongban.putExtra("phongban", 4);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        ibtn_Exit_bophan.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void AnhXa() {
        img_QA1 = findViewById(R.id.img_QA1);
        img_QA2 = findViewById(R.id.img_QA2);
        img_QA3 = findViewById(R.id.img_QA3);
        img_QA4 = findViewById(R.id.img_QA4);
        ibtn_Exit_bophan = findViewById(R.id.ibtn_Exit_bophan);
    }
}