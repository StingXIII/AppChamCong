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
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaiKhoan_Duyet extends AppCompatActivity {

    EditText edt_Manhanvien_cd, edt_Tennhanvien_cd, edt_Bophan_cd, edt_Phongban_cd,
            edt_Chucvu_cd, edt_Tinhtrang_cd;
    Button btnDuyet_cd, btnHuy_cd;
    ImageButton ibtnExit_cd;
    int IDTK;

    private boolean isEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_duyet);

        AnhXa();
        Intent intent = getIntent();
        IDTK = intent.getIntExtra("IDTK", 1);

        if (String.valueOf(IDTK) == null){
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
        TaiKhoan taiKhoanDTO = BatDauActivity.database.Load(IDTK);
        int idtaikhoan = taiKhoanDTO.getMATK();
        String manhanvien = taiKhoanDTO.getTENTK();
        String tennhanvien = taiKhoanDTO.getTENNGUOIDUNG();
        int bophan = taiKhoanDTO.getBOPHAN();
        int phongban = taiKhoanDTO.getPHONGBAN();
        int chucvu = taiKhoanDTO.getCHUCVU();
        int tinhtrang = taiKhoanDTO.getTINHTRANG();

        enableControl();

        edt_Manhanvien_cd.setText(manhanvien);
        edt_Tennhanvien_cd.setText(tennhanvien);
        edt_Bophan_cd.setText(String.valueOf(bophan));
        edt_Phongban_cd.setText(String.valueOf(phongban));
        edt_Chucvu_cd.setText(String.valueOf(chucvu));
        edt_Tinhtrang_cd.setText(String.valueOf(tinhtrang));

        edt_Manhanvien_cd.setEnabled(false);
        edt_Tennhanvien_cd.setEnabled(false);
        edt_Bophan_cd.setEnabled(false);
        edt_Phongban_cd.setEnabled(false);
        edt_Chucvu_cd.setEnabled(false);
        edt_Tinhtrang_cd.setEnabled(false);
    }


    private void AnhXa() {
        edt_Manhanvien_cd = findViewById(R.id.edt_Manhanvien_cd);
        edt_Tennhanvien_cd = findViewById(R.id.edt_Tennhanvien_cd);
        edt_Bophan_cd = findViewById(R.id.edt_Bophan_cd);
        edt_Phongban_cd = findViewById(R.id.edt_Phongban_cd);
        edt_Chucvu_cd = findViewById(R.id.edt_Chucvu_cd);
        edt_Tinhtrang_cd = findViewById(R.id.edt_Tinhtrang_cd);

        btnDuyet_cd = findViewById(R.id.btnDuyet_cd);
        btnHuy_cd = findViewById(R.id.btnHuy_cd);
        ibtnExit_cd = findViewById(R.id.ibtnExit_cd);

        ibtnExit_cd.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuy_cd.setOnClickListener(view -> {
            onBackPressed();
        });

        btnDuyet_cd.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String MANV = edt_Manhanvien_cd.getText().toString().trim();
            String TENNV = edt_Tennhanvien_cd.getText().toString().trim();
            int BOPHAN =  Integer.parseInt(edt_Bophan_cd.getText().toString().trim());
            int PHONGBAN = Integer.parseInt(edt_Phongban_cd.getText().toString().trim());
            int CHUCVU = Integer.parseInt(edt_Chucvu_cd.getText().toString().trim());
            int TINHTRANG = Integer.parseInt(edt_Tinhtrang_cd.getText().toString().trim());

            if (isEnabled){
                btnDuyet_cd.setText("Lưu");
            }
            else{
                btnDuyet_cd.setText("Duyệt");

                BatDauActivity.database.CapNhatTaiKhoan_XetDuyet(IDTK, MANV, TENNV, BOPHAN, PHONGBAN, CHUCVU, TINHTRANG);
                Toast.makeText(TaiKhoan_Duyet.this, "Duyệt thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Manhanvien_cd.setEnabled(isEnabled);
        edt_Tennhanvien_cd.setEnabled(isEnabled);
        edt_Bophan_cd.setEnabled(isEnabled);
        edt_Phongban_cd.setEnabled(isEnabled);
        edt_Chucvu_cd.setEnabled(isEnabled);
        edt_Tinhtrang_cd.setEnabled(isEnabled);
    }
}