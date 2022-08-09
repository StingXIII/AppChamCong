package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.Adapter.NangLucAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.Nangluc;
import com.example.appchamcong.R;

public class ThongtinNangLucActivity extends AppCompatActivity {
    private boolean isEnabled;
    int position;
    ImageView ibtnExit_nv;
    Button btn_Capnhatthongtin,btn_Huy;
    EditText edt_Manhanvien,edt_Tennhanvien,edt_KQ,edt_DIEMQL,edt_XEPLOAI;
    TextView thongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_nang_luc);
        AnhXa();
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 2022);

        Events();
        Getdata();
        edt_Manhanvien.setEnabled(false);
        edt_Tennhanvien.setEnabled(false);
        edt_KQ.setEnabled(false);
        edt_XEPLOAI.setEnabled(false);

    }

    private void Getdata() {

        Nangluc nangluc = BatDauActivity.database.Load_Thongtin(NangLucAdapter.nanglucList.get(position).getMANHANVIEN());

        edt_Manhanvien.setText(nangluc.getMANHANVIEN());
        edt_Tennhanvien.setText(nangluc.getTENNHANVIEN());
        if (nangluc.getKQ()==1)
        {
            edt_KQ.setText("Đạt");
        }
        else if (nangluc.getKQ()==0)
        {
            edt_KQ.setText("Chưa Đạt");
        }
        else
        {
            edt_KQ.setText("Chưa làm bài kiểm tra");
        }

        if (nangluc.getDIEM()>=0)
        {
            edt_DIEMQL.setText(nangluc.getDIEM()+"");
            thongbao.setText("");
        }
        else
        {
            edt_DIEMQL.setText("");
            Toast.makeText(ThongtinNangLucActivity.this, "Vui lòng cập nhật điểm Quản lý", Toast.LENGTH_SHORT).show();

        }
        if (nangluc.getXEPLOAI()>=80)
        {
            edt_XEPLOAI.setText("A");

        }else  if (nangluc.getXEPLOAI()>=60)
        {
            edt_XEPLOAI.setText("B");
        }else  if (nangluc.getXEPLOAI()>=40)
        {
            edt_XEPLOAI.setText("C");
        }else  if (nangluc.getXEPLOAI()>=1)
        {
            edt_XEPLOAI.setText("D");
        }
        else {
            edt_XEPLOAI.setText("Chưa đủ điều kiện");
        }
        enableControl();


    }

    private void Events() {
        ibtnExit_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_Capnhatthongtin.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
//            String MANV= edt_Manhanvien_qlcong.getText().toString().trim();

            if (isEnabled){
                btn_Capnhatthongtin.setText("Lưu");
            }
            else{
                int DIEMQL = Integer.parseInt(edt_DIEMQL.getText().toString().trim());
                if (DIEMQL==0)
                {
                    Toast.makeText(ThongtinNangLucActivity.this, "Vui lòng nhập điểm lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
                else if (DIEMQL < 0)
                {
                    Toast.makeText(ThongtinNangLucActivity.this, "Điểm không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else if (DIEMQL<=50)
                {
                    btn_Capnhatthongtin.setText("Cập nhật");
                    if (edt_KQ.getText().toString().trim().equals("Đạt"))
                    {
                        BatDauActivity.database.CAPNHATDIEMQL(
                                edt_Manhanvien.getText().toString().trim(),
                                DIEMQL,
                                50+DIEMQL
                        );
                    }
                    else
                    {
                        BatDauActivity.database.CAPNHATDIEMQL(
                                edt_Manhanvien.getText().toString().trim(),
                                DIEMQL,
                                DIEMQL
                        );
                    }
                    Intent intent = new Intent(ThongtinNangLucActivity.this, QuanLyActivity.class);
                    intent.putExtra("nangluc", R.id.nav_rank_manager);
                    startActivity(intent);
                    Toast.makeText(ThongtinNangLucActivity.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                    Getdata();
                }else
                {
                    Toast.makeText(ThongtinNangLucActivity.this, "Tối đa chỉ được chấm 50", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void AnhXa() {
        thongbao = findViewById(R.id.thongbao);
        ibtnExit_nv = findViewById(R.id.ibtnExit_nv);
        btn_Capnhatthongtin = findViewById(R.id.btn_Capnhatthongtin);
        btn_Huy = findViewById(R.id.btn_Huy);
        edt_Manhanvien = findViewById(R.id.edt_Manhanvien);
        edt_Tennhanvien = findViewById(R.id.edt_Tennhanvien);
        edt_KQ = findViewById(R.id.edt_KQ);
        edt_DIEMQL = findViewById(R.id.edt_DIEMQL);
        edt_XEPLOAI = findViewById(R.id.edt_XEPLOAI);
    }
    private void enableControl() {
        edt_DIEMQL.setEnabled(isEnabled);
    }
}