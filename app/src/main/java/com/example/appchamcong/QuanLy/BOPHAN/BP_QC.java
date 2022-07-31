package com.example.appchamcong.QuanLy.BOPHAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.appchamcong.NhanSu.NhanSuActivity;
import com.example.appchamcong.QuanLy.PhongBan;
import com.example.appchamcong.R;

public class BP_QC extends AppCompatActivity {

    ImageView img_QC1, img_QC2, img_FQC, img_OQC, img_IQC, img_PQC;
    ImageButton ibtn_Exit_bophan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_qc);

        AnhXa();
        SuKien();
    }

    private void SuKien() {
        img_QC1.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QC.this, PhongBan.class);
            phongban.putExtra("phongban", 1);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_QC2.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QC.this, PhongBan.class);
            phongban.putExtra("phongban", 2);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_FQC.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QC.this, PhongBan.class);
            phongban.putExtra("phongban", 3);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_OQC.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QC.this, PhongBan.class);
            phongban.putExtra("phongban", 4);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_IQC.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QC.this, PhongBan.class);
            phongban.putExtra("phongban", 5);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        img_PQC.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_QC.this, PhongBan.class);
            phongban.putExtra("phongban", 6);
            phongban.putExtra("bophan", 2);
            startActivity(phongban);
        });

        ibtn_Exit_bophan.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void AnhXa() {
        img_QC1 = findViewById(R.id.img_QC1);
        img_QC2 = findViewById(R.id.img_QC2);
        img_FQC = findViewById(R.id.img_FQC);
        img_OQC = findViewById(R.id.img_OQC);
        img_IQC = findViewById(R.id.img_IQC);
        img_PQC = findViewById(R.id.img_PQC);
        ibtn_Exit_bophan = findViewById(R.id.ibtn_Exit_bophan);
    }
}