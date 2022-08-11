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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyGioCongAdapter;
import com.example.appchamcong.Adapter.QuanLyTaiKhoanAdapter;
import com.example.appchamcong.Adapter.QuanLyXemCongAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.ThongTinChamCong;
import com.example.appchamcong.R;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class XemCong_NhanVien extends AppCompatActivity {

    RecyclerView recV_DanhSachGioCong;
    ArrayList<ChamCong> listChamCong;
    QuanLyXemCongAdapter adapter;
    EditText edt_timkiemtaikhoan_xemcong;
    Button btn_Chonthang_xemcong;
    ImageButton ibtnExit_xemcong;

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

        edt_timkiemtaikhoan_xemcong = findViewById(R.id.edt_timkiemtaikhoan_xemcong);
        edt_timkiemtaikhoan_xemcong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Load();
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Load_TimKiem();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_Chonthang_xemcong = findViewById(R.id.btn_Chonthang_xemcong);
        btn_Chonthang_xemcong.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(XemCong_NhanVien.this, new MonthPickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(int selectedMonth, int selectedYear) {
                    edt_timkiemtaikhoan_xemcong.setText("0" + (selectedMonth + 1) + "/" + selectedYear);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

            builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(1990)
                    .setActivatedYear(calendar.get(Calendar.YEAR))
                    .setMaxYear(2030)
                    .setTitle("Chọn tháng")
                    .build().show();
        });

        ibtnExit_xemcong = findViewById(R.id.ibtnExit_xemcong);
        ibtnExit_xemcong.setOnClickListener(view -> {
            onBackPressed();
        });
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

    private void Load_TimKiem() {
        listChamCong.clear();
        listChamCong.addAll(BatDauActivity.database.XemCong_TimKiem(BatDauActivity.taiKhoanDTO.getTENTK(), edt_timkiemtaikhoan_xemcong.getText().toString()));
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