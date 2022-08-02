package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinChamCong extends AppCompatActivity {

    EditText edt_Manhanvien_qlcong, edt_Tennhanvien_qlcong, edt_phongban_qlcong, edt_Ngaycong_qlcong,
            edt_Giovao_qlcong, edt_Giora_qlcong, edt_Giocong_qlcong;
    Button btnCapnhat_qlcong, btnHuyCN_qlcong;
    ImageButton ibtnExit_qlcong;
    int IDCHAMCONG;
    private boolean isEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_cham_cong);

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
        String manv = chamCong.getMANV();
        String tennv = chamCong.getTENNV();
        String phongban = chamCong.getPHONGBAN();
        String ngaycong = chamCong.getNGAYCONG();
        String giovao = chamCong.getGIOVAO();
        String giora = chamCong.getGIORA();
        int giocong = chamCong.getGIOCONG();

        enableControl();

        edt_Manhanvien_qlcong.setText(manv);
        edt_Tennhanvien_qlcong.setText(tennv);
        edt_phongban_qlcong.setText(phongban);
        edt_Ngaycong_qlcong.setText(ngaycong);
        edt_Giovao_qlcong.setText(giovao);
        edt_Giora_qlcong.setText(giora);
        edt_Giocong_qlcong.setText(String.valueOf(giocong));

    }


    private void AnhXa() {
        edt_Manhanvien_qlcong = findViewById(R.id.edt_Manhanvien_qlcong);
        edt_Tennhanvien_qlcong = findViewById(R.id.edt_Tennhanvien_qlcong);
        edt_phongban_qlcong = findViewById(R.id.edt_phongban_qlcong);
        edt_Ngaycong_qlcong = findViewById(R.id.edt_Ngaycong_qlcong);
        edt_Giovao_qlcong = findViewById(R.id.edt_Giovao_qlcong);
        edt_Giora_qlcong = findViewById(R.id.edt_Giora_qlcong);
        edt_Giocong_qlcong = findViewById(R.id.edt_Giocong_qlcong);

        btnCapnhat_qlcong = findViewById(R.id.btnCapnhat_qlcong);
        btnHuyCN_qlcong = findViewById(R.id.btnHuyCN_qlcong);
        ibtnExit_qlcong = findViewById(R.id.ibtnExit_qlcong);

        ibtnExit_qlcong.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuyCN_qlcong.setOnClickListener(view -> {
            onBackPressed();
        });

        btnCapnhat_qlcong.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String MANV= edt_Manhanvien_qlcong.getText().toString().trim();
            String TENNV = edt_Tennhanvien_qlcong.getText().toString().trim();
            String PBAN = edt_phongban_qlcong.getText().toString().trim();
            String NGAYCONG = edt_Ngaycong_qlcong.getText().toString().trim();
            String GIOVAO = edt_Giovao_qlcong.getText().toString().trim();
            String GIORA = edt_Giora_qlcong.getText().toString().trim();
            int GIOCONG = Integer.parseInt(edt_Giocong_qlcong.getText().toString().trim());

            if (isEnabled){
                btnCapnhat_qlcong.setText("Lưu");
            }
            else{
                btnCapnhat_qlcong.setText("Cập nhật");

                BatDauActivity.database.CapNhatCong_QL(IDCHAMCONG, MANV, TENNV, PBAN, NGAYCONG, GIOVAO, GIORA, GIOCONG);
                Toast.makeText(ThongTinChamCong.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Manhanvien_qlcong.setEnabled(isEnabled);
        edt_Tennhanvien_qlcong.setEnabled(isEnabled);
        edt_phongban_qlcong.setEnabled(isEnabled);
        edt_Ngaycong_qlcong.setEnabled(isEnabled);
        edt_Giovao_qlcong.setEnabled(isEnabled);
        edt_Giora_qlcong.setEnabled(isEnabled);
        edt_Giocong_qlcong.setEnabled(isEnabled);
    }
}