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
import com.example.appchamcong.NhanSu.ThongTinNhanSu;
import com.example.appchamcong.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CapNhat_NhanSu extends AppCompatActivity {

    EditText edtTennguoidung_ttns, edtSdt_ttns, edtDiachi_ttns, edt_Chucvu_ttns, edt_Phongban_ttns,
            edt_Tinhtrang_ttns, edt_Quyen_ttns;
    Button btnCapnhat_ttns, btnHuyCN_ttns;
    ImageButton ibtnExit_ttns;
    CircleImageView imgHinhDaiDien_ttns;
    boolean isEnabled;
    int IDTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_nhan_su);

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
        String tennguoidung = taiKhoanDTO.getTENNGUOIDUNG();
        int sdt = taiKhoanDTO.getSDT();
        String diachi = taiKhoanDTO.getDIACHI();
        int chucvu = taiKhoanDTO.getCHUCVU();
        int phongban = taiKhoanDTO.getPHONGBAN();
        int tinhtrang = taiKhoanDTO.getTINHTRANG();
        int quyen = taiKhoanDTO.getQUYEN();
        enableControl();

        edtTennguoidung_ttns.setText(tennguoidung);
        edtSdt_ttns.setText(String.valueOf(sdt));
        edtDiachi_ttns.setText(diachi);
        edt_Chucvu_ttns.setText(String.valueOf(chucvu));
        edt_Phongban_ttns.setText(String.valueOf(phongban));
        edt_Tinhtrang_ttns.setText(String.valueOf(tinhtrang));
        edt_Quyen_ttns.setText(String.valueOf(quyen));

        if (BatDauActivity.taiKhoanDTO.getHINHANH() == null){
            imgHinhDaiDien_ttns.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
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
        edt_Quyen_ttns = findViewById(R.id.edt_Quyen_ttns);
        btnCapnhat_ttns = findViewById(R.id.btnCapnhat_ttns);
        btnHuyCN_ttns = findViewById(R.id.btnHuyCN_ttns);
        ibtnExit_ttns = findViewById(R.id.ibtnExit_ttns);
        imgHinhDaiDien_ttns = findViewById(R.id.imgHinhDaiDien_ttns);

        ibtnExit_ttns.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuyCN_ttns.setOnClickListener(view -> {
            onBackPressed();
        });

        btnCapnhat_ttns.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String TENND = edtTennguoidung_ttns.getText().toString().trim();
            int SDT = Integer.parseInt(edtSdt_ttns.getText().toString().trim());
            String DC = edtDiachi_ttns.getText().toString().trim();
            int CV = Integer.parseInt(edt_Chucvu_ttns.getText().toString().trim());
            int PB = Integer.parseInt(edt_Phongban_ttns.getText().toString().trim());
            int TT = Integer.parseInt(edt_Tinhtrang_ttns.getText().toString().trim());
            int QU = Integer.parseInt(edt_Quyen_ttns.getText().toString().trim());

            if (isEnabled){
                btnCapnhat_ttns.setText("Lưu");
            }
            else{
                btnCapnhat_ttns.setText("Cập nhật");

                BatDauActivity.database.CapNhatNhanSu_QL(IDTK, TENND, SDT, DC, QU, CV, PB, TT);
                Toast.makeText(CapNhat_NhanSu.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edtTennguoidung_ttns.setEnabled(isEnabled);
        edtSdt_ttns.setEnabled(isEnabled);
        edtDiachi_ttns.setEnabled(isEnabled);
        edt_Chucvu_ttns.setEnabled(isEnabled);
        edt_Phongban_ttns.setEnabled(isEnabled);
        edt_Tinhtrang_ttns.setEnabled(isEnabled);
        edt_Quyen_ttns.setEnabled(isEnabled);
    }
}