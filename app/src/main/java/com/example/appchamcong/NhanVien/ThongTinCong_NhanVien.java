package com.example.appchamcong.NhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.QuanLy.ThongTinChamCong;
import com.example.appchamcong.R;

public class ThongTinCong_NhanVien extends AppCompatActivity {

    EditText edt_Manhanvien_nv, edt_Tennhanvien_nv, edt_phongban_nv, edt_Ngaycong_nv,
            edt_Giovao_nv, edt_Giora_nv, edt_Giocong_nv;
    ImageButton ibtnExit_nv;
    int IDCHAMCONG;
    private boolean isEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_cong_nhan_vien);

        AnhXa();
        Intent intent = getIntent();
        IDCHAMCONG = intent.getIntExtra("IDCHAMCONG", 1);

        if (String.valueOf(IDCHAMCONG) == null){
            return;
        }

        GetData();
    }

    @Override
    protected void onStart() {
        GetData();
        super.onStart();
    }

    private void GetData() {
        //get data
        ChamCong chamCong = BatDauActivity.database.Load_ChamCong(IDCHAMCONG);
        int idchamcong = chamCong.getID();
        String manv = chamCong.getMANV();
        String tennv = chamCong.getTENNV();
        String phongban = chamCong.getPHONGBAN();
        String ngaycong = chamCong.getNGAYCONG();
        String giovao = chamCong.getGIOVAO();
        String giora = chamCong.getGIORA();
        int giocong = chamCong.getGIOCONG();

        enableControl();

        edt_Manhanvien_nv.setText(manv);
        edt_Tennhanvien_nv.setText(tennv);
        edt_phongban_nv.setText(phongban);
        edt_Ngaycong_nv.setText(ngaycong);
        edt_Giovao_nv.setText(giovao);
        edt_Giora_nv.setText(giora);
        edt_Giocong_nv.setText(String.valueOf(giocong));

    }


    private void AnhXa() {
        edt_Manhanvien_nv = findViewById(R.id.edt_Manhanvien_nv);
        edt_Tennhanvien_nv = findViewById(R.id.edt_Tennhanvien_nv);
        edt_phongban_nv = findViewById(R.id.edt_phongban_nv);
        edt_Ngaycong_nv = findViewById(R.id.edt_Ngaycong_nv);
        edt_Giovao_nv = findViewById(R.id.edt_Giovao_nv);
        edt_Giora_nv = findViewById(R.id.edt_Giora_nv);
        edt_Giocong_nv = findViewById(R.id.edt_Giocong_nv);
        ibtnExit_nv = findViewById(R.id.ibtnExit_nv);

        ibtnExit_nv.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void enableControl() {
        edt_Manhanvien_nv.setEnabled(isEnabled);
        edt_Tennhanvien_nv.setEnabled(isEnabled);
        edt_phongban_nv.setEnabled(isEnabled);
        edt_Ngaycong_nv.setEnabled(isEnabled);
        edt_Giovao_nv.setEnabled(isEnabled);
        edt_Giora_nv.setEnabled(isEnabled);
        edt_Giocong_nv.setEnabled(isEnabled);
    }
}