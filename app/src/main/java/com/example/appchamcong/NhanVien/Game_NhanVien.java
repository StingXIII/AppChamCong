package com.example.appchamcong.NhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.Vong;
import com.example.appchamcong.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Game_NhanVien extends AppCompatActivity {

    Button thuchanh,lythuyetchung,ngoaiquan,doluong;
    TextView txt_thuchanh,txt_lythuyetchung,txt_ngoaiquan,txt_doluong,tentk_choingay,diem_tk_choingay;
    ImageButton quaylai_choingay;
    CircleImageView img_tk_choingay;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_nhan_vien);
        AnhXa();
        BtnEnable();

        Intent intent = getIntent();
        id = intent.getIntExtra("id",123);
        if (id==123){
            CheckThongTin(BatDauActivity.taiKhoanDTO.getMATK());
            quaylai_choingay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Game_NhanVien.this, TrangChu_NhanVien.class));
                }
            });
        }
        Event();
    }

    private void BtnEnable() {
        lythuyetchung.setEnabled(false);
        ngoaiquan.setEnabled(false);
        doluong.setEnabled(false);
        thuchanh.setEnabled(false);
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    private void CheckThongTin(int id) {

        if (BatDauActivity.taiKhoanDTO.getHINHANH()!=null){
            byte[] hinhAnh = BatDauActivity.taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_tk_choingay.setImageBitmap(bitmap);
        }else {
            img_tk_choingay.setImageResource(R.drawable.person);
        }
        tentk_choingay.setText(BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG());
        checkvong(id);
    }



    private void Event() {

        lythuyetchung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game_NhanVien.this, TracNghiem_NhanVien.class);
                intent.putExtra("vong",1);
                startActivity(intent);
            }
        });
        ngoaiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game_NhanVien.this,TracNghiem_NhanVien.class);
                intent.putExtra("vong",2);
                startActivity(intent);

            }
        });
        doluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game_NhanVien.this, TracNghiem_NhanVien.class);
                intent.putExtra("vong",3);
                startActivity(intent);
            }
        });
        thuchanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game_NhanVien.this, TracNghiem_NhanVien.class);
                intent.putExtra("vong",4);
                startActivity(intent);
            }
        });

    }

    private void checkvong(int id) {
        List<Vong> a = new ArrayList();
        a.clear();
        Cursor cursor = BatDauActivity.database.Getdata("SELECT VONG,SOCAU FROM VONGCHINH WHERE IDTK = " + id);
        while (cursor.moveToNext()){
            a.add(new Vong(
                    Float.valueOf(cursor.getString(0)),
                    cursor.getInt(1)
            ));

        }

        //---------------------------
        try {

            txt_lythuyetchung.setText(a.get(0).getSOCAU()+"/15");
            lythuyetchung.setEnabled(false);
            lythuyetchung.setBackgroundResource(R.color.xam);
        }catch (Exception e){
            txt_lythuyetchung.setText("0/15");
            lythuyetchung.setEnabled(true);
            lythuyetchung.setBackgroundResource(R.color.teal_700);


            return;
        }
        //---------------------------
        try {
            txt_ngoaiquan.setText(a.get(1).getSOCAU()+"/5");
            ngoaiquan.setEnabled(false);
            ngoaiquan.setBackgroundResource(R.color.xam);
        }catch (Exception e){
            txt_ngoaiquan.setText("0/5");
            ngoaiquan.setEnabled(true);
            ngoaiquan.setBackgroundResource(R.color.teal_700);
            return;
        }
        //---------------------------
        try {
            txt_doluong.setText(a.get(2).getSOCAU()+"/5");
            doluong.setEnabled(false);
            doluong.setBackgroundResource(R.color.xam);
        }catch (Exception e){
            txt_doluong.setText("0/5");
            doluong.setEnabled(true);
            doluong.setBackgroundResource(R.color.teal_700);
            return;
        };
        //---------------------------
        try {
            txt_thuchanh.setText(a.get(3).getSOCAU()+"/10");
            thuchanh.setEnabled(false);
            thuchanh.setBackgroundResource(R.color.xam);

            try {
                if (a.get(0).getSOCAU()>=12
                        && a.get(1).getSOCAU()>=4
                        && a.get(2).getSOCAU()>=4
                        && a.get(3).getSOCAU()>=8)
                {
                    diem_tk_choingay.setText("Đạt");
                    if (BatDauActivity.database.KIEMTRANANGLUC(BatDauActivity.taiKhoanDTO.getTENTK()))
                    {
                        Log.e("DAT",BatDauActivity.taiKhoanDTO.getMATK() + " , " +
                                BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG() );
                        BatDauActivity.database.INSERT_NANGLUC(BatDauActivity.taiKhoanDTO.getTENTK(),
                                BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG(),1);
                    }

                } else {
                    diem_tk_choingay.setText("Chưa đạt");
                    if (BatDauActivity.database.KIEMTRANANGLUC(BatDauActivity.taiKhoanDTO.getTENTK()))
                    {
                        Log.e("CHUADAT",BatDauActivity.taiKhoanDTO.getMATK() + " , " +
                                BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG() );
                        BatDauActivity.database.INSERT_NANGLUC(BatDauActivity.taiKhoanDTO.getTENTK(),
                                BatDauActivity.taiKhoanDTO.getTENNGUOIDUNG(),0);
                    }
                }

            }catch (Exception e){
                diem_tk_choingay.setText("Không đủ điều kiện");

            };
        }catch (Exception e){
            txt_thuchanh.setText("0/10");
            thuchanh.setEnabled(true);
            thuchanh.setBackgroundResource(R.color.teal_700);
            return;
        };


    }

    private void AnhXa() {
        quaylai_choingay = findViewById(R.id.quaylai_choingay);
        diem_tk_choingay = findViewById(R.id.diem_tk_choingay);
        tentk_choingay = findViewById(R.id.tentk_choingay);
        img_tk_choingay = findViewById(R.id.img_tk_choingay);
        lythuyetchung = findViewById(R.id.lythuyetchung);
        ngoaiquan = findViewById(R.id.ngoaiquan);
        doluong = findViewById(R.id.doluong);
        thuchanh = findViewById(R.id.thuchanh);
        txt_thuchanh = findViewById(R.id.txt_thuchanh);
        txt_lythuyetchung = findViewById(R.id.txt_lythuyetchung);
        txt_ngoaiquan = findViewById(R.id.txt_ngoaiquan);
        txt_doluong = findViewById(R.id.txt_doluong);
    }

}