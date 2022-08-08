package com.example.appchamcong.QuanLy.BOPHAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.appchamcong.QuanLy.PhongBan;
import com.example.appchamcong.R;

public class BP_Production extends AppCompatActivity {

    ImageView img_Production1, img_Production2, img_Production3, img_Production4;
    ImageButton ibtn_Exit_bophan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_production);

        AnhXa();
        SuKien();
    }

    private void SuKien() {
        img_Production1.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_Production.this, PhongBan.class);
            phongban.putExtra("phongban", 1);
            phongban.putExtra("bophan", 1);
            startActivity(phongban);
        });

        img_Production2.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_Production.this, PhongBan.class);
            phongban.putExtra("phongban", 2);
            phongban.putExtra("bophan", 1);
            startActivity(phongban);
        });

        img_Production3.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_Production.this, PhongBan.class);
            phongban.putExtra("phongban", 3);
            phongban.putExtra("bophan", 1);
            startActivity(phongban);
        });

        img_Production4.setOnClickListener(view -> {
            Intent phongban = new Intent(BP_Production.this, PhongBan.class);
            phongban.putExtra("phongban", 4);
            phongban.putExtra("bophan", 1);
            startActivity(phongban);
        });

        ibtn_Exit_bophan.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void AnhXa() {
        img_Production1 = findViewById(R.id.img_Production1);
        img_Production2 = findViewById(R.id.img_Production2);
        img_Production3 = findViewById(R.id.img_Production3);
        img_Production4 = findViewById(R.id.img_Production4);
        ibtn_Exit_bophan = findViewById(R.id.ibtn_Exit_bophan);
    }
}