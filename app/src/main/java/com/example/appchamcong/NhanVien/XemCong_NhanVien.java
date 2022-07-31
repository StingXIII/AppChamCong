package com.example.appchamcong.NhanVien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyGioCongAdapter;
import com.example.appchamcong.Adapter.QuanLyTaiKhoanAdapter;
import com.example.appchamcong.Adapter.QuanLyXemCongAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.ThongTinChamCong;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class XemCong_NhanVien extends AppCompatActivity {

    RecyclerView recV_DanhSachGioCong;
    ChamCong chamCong;
    ArrayList<ChamCong> listChamCong;
    QuanLyXemCongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_cong_nhan_vien);

        BatDauActivity.database = new Database(this);
        AnhXa();

        listChamCong = new ArrayList<>();
        adapter = new QuanLyXemCongAdapter(this, listChamCong);

        Load();

        recV_DanhSachGioCong.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recV_DanhSachGioCong.setAdapter(adapter);
    }

    private void AnhXa() {
        recV_DanhSachGioCong = findViewById(R.id.recV_DanhSachGioCong);
        registerForContextMenu(recV_DanhSachGioCong);
    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    public void Load(){
        listChamCong.clear();
        listChamCong.addAll(BatDauActivity.database.XemCong(BatDauActivity.taiKhoanDTO.getTENTK()));
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QuanLyXemCongAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iXemcong:
                Intent iCapnhat = new Intent(this, ThongTinCong_NhanVien.class);
                iCapnhat.putExtra("IDCHAMCONG", listChamCong.get(position).getID());
                Toast.makeText(this, "Mã NV là: " + listChamCong.get(position).getMANV(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}