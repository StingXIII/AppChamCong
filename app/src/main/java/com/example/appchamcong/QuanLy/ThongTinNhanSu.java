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

public class ThongTinNhanSu extends AppCompatActivity {

    EditText edtTennguoidung_ttns, edtSdt_ttns, edtDiachi_ttns, edt_Chucvu_ttns, edt_Phongban_ttns,
            edt_Tinhtrang_ttns, edtManhanvien_ttns, edt_Bophan_ttns, edt_Quyen_ttns, edtNgaysinh_ttns, edtEmail_ttns;
    Button btnCapnhat_ttns, btnHuyCN_ttns;
    ImageButton ibtnExit_ttns;
    CircleImageView imgHinhDaiDien_ttns;
    boolean isEnabled;
    int IDTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhan_su);

        Anhxa();
        Intent intent = getIntent();
        IDTK = intent.getIntExtra("idtaikhoan", 1);

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
        String manhanvien = taiKhoanDTO.getTENTK();
        String tennguoidung = taiKhoanDTO.getTENNGUOIDUNG();
        String ngaysinh = taiKhoanDTO.getNGAYSINH();
        String email = taiKhoanDTO.getEMAIL();
        int sdt = taiKhoanDTO.getSDT();
        String diachi = taiKhoanDTO.getDIACHI();
        int chucvu = taiKhoanDTO.getCHUCVU();
        int bophan = taiKhoanDTO.getBOPHAN();
        int phongban = taiKhoanDTO.getPHONGBAN();
        int tinhtrang = taiKhoanDTO.getTINHTRANG();
        int quyen = taiKhoanDTO.getQUYEN();
        enableControl();

        edtTennguoidung_ttns.setText(tennguoidung);
        edtSdt_ttns.setText(String.valueOf(sdt));
        edtDiachi_ttns.setText(diachi);
        edtNgaysinh_ttns.setText(ngaysinh);
        edtEmail_ttns.setText(email);
        edt_Chucvu_ttns.setText(String.valueOf(chucvu));
        edt_Phongban_ttns.setText(String.valueOf(phongban));
        edt_Tinhtrang_ttns.setText(String.valueOf(tinhtrang));
        edtManhanvien_ttns.setText(String.valueOf(manhanvien));
        edt_Bophan_ttns.setText(String.valueOf(bophan));
        edt_Quyen_ttns.setText(String.valueOf(quyen));

        imgHinhDaiDien_ttns.setEnabled(false);
        edtTennguoidung_ttns.setEnabled(false);
        edtSdt_ttns.setEnabled(false);
        edtDiachi_ttns.setEnabled(false);

        if (taiKhoanDTO.getHINHANH() == null){
            imgHinhDaiDien_ttns.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            imgHinhDaiDien_ttns.setImageBitmap(bitmap);
        }
    }

    private void Anhxa() {
        edtTennguoidung_ttns = findViewById(R.id.edtTennguoidung_ttns);
        edtSdt_ttns = findViewById(R.id.edtSdt_ttns);
        edtDiachi_ttns = findViewById(R.id.edtDiachi_ttns);
        edt_Chucvu_ttns = findViewById(R.id.edt_Chucvu_ttns);
        edt_Phongban_ttns = findViewById(R.id.edt_Phongban_ttns);
        edt_Tinhtrang_ttns = findViewById(R.id.edt_Tinhtrang_ttns);
        btnCapnhat_ttns = findViewById(R.id.btnCapnhat_ttns);
        btnHuyCN_ttns = findViewById(R.id.btnHuyCN_ttns);
        ibtnExit_ttns = findViewById(R.id.ibtnExit_ttns);
        imgHinhDaiDien_ttns = findViewById(R.id.imgHinhDaiDien_ttns);
        edt_Bophan_ttns = findViewById(R.id.edt_Bophan_ttns);
        edtManhanvien_ttns = findViewById(R.id.edtManhanvien_ttns);
        edt_Quyen_ttns = findViewById(R.id.edt_Quyen_ttns);
        edtNgaysinh_ttns = findViewById(R.id.edtNgaysinh_ttns);
        edtEmail_ttns = findViewById(R.id.edtEmail_ttns);

        ibtnExit_ttns.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuyCN_ttns.setOnClickListener(view -> {
            onBackPressed();
        });

        btnCapnhat_ttns.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String MANV = edtManhanvien_ttns.getText().toString().trim();
            String TENND = edtTennguoidung_ttns.getText().toString().trim();
            int SDT = Integer.parseInt(edtSdt_ttns.getText().toString().trim());
            String DC = edtDiachi_ttns.getText().toString().trim();
            int CV = Integer.parseInt(edt_Chucvu_ttns.getText().toString().trim());
            int BP = Integer.parseInt(edt_Bophan_ttns.getText().toString().trim());
            int PB = Integer.parseInt(edt_Phongban_ttns.getText().toString().trim());
            int TT = Integer.parseInt(edt_Tinhtrang_ttns.getText().toString().trim());
            int QU = Integer.parseInt(edt_Quyen_ttns.getText().toString().trim());
            String NS = edtNgaysinh_ttns.getText().toString().trim();
            String EM = edtEmail_ttns.getText().toString().trim();

            if (isEnabled){
                btnCapnhat_ttns.setText("Lưu");
            }
            else{
                btnCapnhat_ttns.setText("Cập nhật");

                BatDauActivity.database.CapNhatNhanSu_QL(IDTK, MANV, TENND, SDT, DC, QU, CV, BP, PB, TT, NS, EM);
                Toast.makeText(ThongTinNhanSu.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Chucvu_ttns.setEnabled(isEnabled);
        edt_Phongban_ttns.setEnabled(isEnabled);
        edt_Tinhtrang_ttns.setEnabled(isEnabled);
        edtManhanvien_ttns.setEnabled(isEnabled);
        edt_Bophan_ttns.setEnabled(isEnabled);
        edtDiachi_ttns.setEnabled(isEnabled);
        edtSdt_ttns.setEnabled(isEnabled);
        edtTennguoidung_ttns.setEnabled(isEnabled);
        imgHinhDaiDien_ttns.setEnabled(isEnabled);
        edt_Quyen_ttns.setEnabled(isEnabled);
        edtNgaysinh_ttns.setEnabled(isEnabled);
        edtEmail_ttns.setEnabled(isEnabled);
    }
}