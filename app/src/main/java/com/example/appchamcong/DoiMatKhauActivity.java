package com.example.appchamcong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DoiMatKhauActivity extends AppCompatActivity {

    ImageButton ibtn_Exit;
    EditText edt_Matkhauhientai, edt_Matkhaumoi, edt_Nhaplaimatkhaumoi;
    Button btn_Doimatkhau, btn_Huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        Anhxa();
        Sukien();
    }

    private void Sukien() {
        btn_Doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_Matkhauhientai.getText().length() !=0 && edt_Matkhaumoi.getText().length() != 0 && edt_Nhaplaimatkhaumoi.getText().length() != 0){
                    if(BatDauActivity.database.isMatKhau(BatDauActivity.taiKhoanDTO.getMATK(), edt_Matkhauhientai.getText().toString())){
                        if(edt_Matkhaumoi.getText().toString().equals(edt_Nhaplaimatkhaumoi.getText().toString())){
                            BatDauActivity.database.CapNhatMatKhau(BatDauActivity.taiKhoanDTO.getMATK(), edt_Matkhaumoi.getText().toString());
                            messenge("Đổi mật khẩu thành công !");
                            onBackPressed();
                        } else {
                            messenge("Mật khẩu mới không trùng khớp !");
                        }
                    } else {
                        messenge("Nhập mật khẩu cũ không đúng !");
                    }
                } else {
                    messenge("Nhập dữ liệu chưa đủ !");
                }

            }
        });

        ibtn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void Anhxa() {
        edt_Matkhauhientai = findViewById(R.id.edtMatkhauhientai);
        edt_Matkhaumoi = findViewById(R.id.edtMatkhaumoi);
        edt_Nhaplaimatkhaumoi = findViewById(R.id.edtNhaplaimatkhaumoi);
        btn_Huy = findViewById(R.id.btnHuy);
        btn_Doimatkhau = findViewById(R.id.btnDoimatkhau);
        ibtn_Exit = findViewById(R.id.ibtnExit);
    }

    public void messenge (String messenge){
        Toast.makeText(DoiMatKhauActivity.this, messenge, Toast.LENGTH_SHORT).show();
    }

}