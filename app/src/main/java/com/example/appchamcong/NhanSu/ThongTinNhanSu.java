package com.example.appchamcong.NhanSu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import org.apache.poi.ss.formula.functions.T;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinNhanSu extends AppCompatActivity {

    EditText edtTennguoidung_ttns, edtSdt_ttns, edtDiachi_ttns, edt_Chucvu_ttns, edt_Phongban_ttns, edt_Tinhtrang_ttns;
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
        String tennguoidung = taiKhoanDTO.getTENNGUOIDUNG();
        int sdt = taiKhoanDTO.getSDT();
        String diachi = taiKhoanDTO.getDIACHI();
        int chucvu = taiKhoanDTO.getCHUCVU();
        int phongban = taiKhoanDTO.getPHONGBAN();
        int tinhtrang = taiKhoanDTO.getTINHTRANG();
        enableControl();

        edtTennguoidung_ttns.setText(tennguoidung);
        edtSdt_ttns.setText(String.valueOf(sdt));
        edtDiachi_ttns.setText(diachi);
        edt_Chucvu_ttns.setText(String.valueOf(chucvu));
        edt_Phongban_ttns.setText(String.valueOf(phongban));
        edt_Tinhtrang_ttns.setText(String.valueOf(tinhtrang));

        imgHinhDaiDien_ttns.setEnabled(false);
        edtTennguoidung_ttns.setEnabled(false);
        edtSdt_ttns.setEnabled(false);
        edtDiachi_ttns.setEnabled(false);

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
            int CV = Integer.parseInt(edt_Chucvu_ttns.getText().toString().trim());
            int PB = Integer.parseInt(edt_Phongban_ttns.getText().toString().trim());
            int TT = Integer.parseInt(edt_Tinhtrang_ttns.getText().toString().trim());

            if (isEnabled){
                btnCapnhat_ttns.setText("Lưu");
            }
            else{
                btnCapnhat_ttns.setText("Cập nhật");

                BatDauActivity.database.CapNhatNhanSu(IDTK, CV, PB, TT);
                Toast.makeText(ThongTinNhanSu.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Chucvu_ttns.setEnabled(isEnabled);
        edt_Phongban_ttns.setEnabled(isEnabled);
        edt_Tinhtrang_ttns.setEnabled(isEnabled);
    }
}