package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.appchamcong.R;

public class ThongKeGioCongNV extends AppCompatActivity {

    ImageButton ibtnExit_Thongkenhansu;
    ImageView img_Production, img_QA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_gio_cong_nv);

        AnhXa();
    }

    private void AnhXa() {
        img_Production = findViewById(R.id.img_Production);
        img_QA = findViewById(R.id.img_QA);
        ibtnExit_Thongkenhansu = findViewById(R.id.ibtnExit_Thongkenhansu);

        ibtnExit_Thongkenhansu.setOnClickListener(view -> {
            onBackPressed();
        });

        img_Production.setOnClickListener(view -> {
            Intent thongke_pro = new Intent(ThongKeGioCongNV.this, ThongKeBoPhanGioCong.class);
            thongke_pro.putExtra("bophan", "Production");
            startActivity(thongke_pro);
        });

        img_QA.setOnClickListener(view -> {
            Intent thongke_qa = new Intent(ThongKeGioCongNV.this, ThongKeBoPhanGioCong.class);
            thongke_qa.putExtra("bophan", "QA");
            startActivity(thongke_qa);
        });
    }
}