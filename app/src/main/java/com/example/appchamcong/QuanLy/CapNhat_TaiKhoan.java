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
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.NhanSu.ThongTinNhanSu;
import com.example.appchamcong.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CapNhat_TaiKhoan extends AppCompatActivity {

    EditText edt_Idtaikhoan_qltk, edt_Tentaikhoan_qltk, edt_Matkhau_qltk, edt_Tennguoidung_qltk;

    Button btnCapnhat_qltk, btnHuyCN_qltk;
    CircleImageView img_HinhDaiDien_qltk;
    ImageButton ibtnExit_qltk;
    int IDTK;

    private boolean isEnabled;
    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_tai_khoan);

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
        String tennguoidung = taiKhoanDTO.getTENNGUOIDUNG();
        String tentaikhoan = taiKhoanDTO.getTENTK();
        String matkhau = taiKhoanDTO.getMATKHAU();

        enableControl();

        edt_Idtaikhoan_qltk.setText(String.valueOf(idtaikhoan));
        edt_Tentaikhoan_qltk.setText(tentaikhoan);
        edt_Matkhau_qltk.setText(matkhau);
        edt_Tennguoidung_qltk.setText(tennguoidung);

        img_HinhDaiDien_qltk.setEnabled(false);
        edt_Idtaikhoan_qltk.setEnabled(false);
        edt_Tentaikhoan_qltk.setEnabled(false);
        edt_Matkhau_qltk.setEnabled(false);
        edt_Tennguoidung_qltk.setEnabled(false);

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            img_HinhDaiDien_qltk.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_HinhDaiDien_qltk.setImageBitmap(bitmap);
        }
    }


    private void AnhXa() {
        edt_Idtaikhoan_qltk = findViewById(R.id.edt_Idtaikhoan_qltk);
        edt_Tentaikhoan_qltk = findViewById(R.id.edt_Tentaikhoan_qltk);
        edt_Matkhau_qltk = findViewById(R.id.edt_Matkhau_qltk);
        edt_Tennguoidung_qltk = findViewById(R.id.edt_Tennguoidung_qltk);

        img_HinhDaiDien_qltk = findViewById(R.id.img_HinhDaiDien_qltk);
        btnCapnhat_qltk = findViewById(R.id.btnCapnhat_qltk);
        btnHuyCN_qltk = findViewById(R.id.btnHuyCN_qltk);
        ibtnExit_qltk = findViewById(R.id.ibtnExit_qltk);

        ibtnExit_qltk.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuyCN_qltk.setOnClickListener(view -> {
            onBackPressed();
        });

        btnCapnhat_qltk.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String TENTK = edt_Tentaikhoan_qltk.getText().toString().trim();
            String MATKHAU = edt_Matkhau_qltk.getText().toString().trim();
            String TENND = edt_Tennguoidung_qltk.getText().toString().trim();

            if (isEnabled){
                btnCapnhat_qltk.setText("Lưu");
            }
            else{
                btnCapnhat_qltk.setText("Cập nhật");

                BatDauActivity.database.CapNhatTaiKhoan_QL(IDTK, TENTK, MATKHAU, TENND);
                Toast.makeText(CapNhat_TaiKhoan.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Tentaikhoan_qltk.setEnabled(isEnabled);
        edt_Matkhau_qltk.setEnabled(isEnabled);
        edt_Tennguoidung_qltk.setEnabled(isEnabled);
    }
}