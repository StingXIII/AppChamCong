package com.example.appchamcong.NhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.R;

public class GopYActivity extends AppCompatActivity {

    ImageButton ibtn_Exit;
    EditText edt_Tennhanvien, edt_Sdt, edt_NoiDunggopy;
    Button btn_Gopy, btn_Thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);

        Anhxa();

        GetData();
    }

    private void GetData() {
        String tennhanvien = BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG();
        int sdt = BatDauActivity.taiKhoanDTO.getSDT();

        edt_Tennhanvien.setText(tennhanvien);
        edt_Sdt.setText("0" + sdt);
    }

    private void Anhxa() {
        edt_Tennhanvien = findViewById(R.id.edtTennhanviengopy);
        edt_Sdt = findViewById(R.id.edtSdtgopy);
        edt_NoiDunggopy = findViewById(R.id.edtNoidunggopy);
        btn_Gopy = findViewById(R.id.btnGopy);
        btn_Gopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatDauActivity.database.INSERT_GOPY(
                        edt_Tennhanvien.getText().toString().trim(),
                        BatDauActivity.taiKhoanDTO.getHINHANH(),
                        Integer.parseInt(edt_Sdt.getText().toString().trim()),
                        edt_NoiDunggopy.getText().toString().trim()
                );
                Toast.makeText(GopYActivity.this, "Gửi góp ý thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        btn_Thoat = findViewById(R.id.btnThoatgopy);
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ibtn_Exit = findViewById(R.id.ibtnExit);
        ibtn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}