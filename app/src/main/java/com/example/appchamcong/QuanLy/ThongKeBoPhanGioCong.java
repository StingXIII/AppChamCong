package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appchamcong.Adapter.ThongKeNhanSuAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.R;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeBoPhanGioCong extends AppCompatActivity {

    RecyclerView recV_DanhSachNhanSu;
    ArrayList<ChamCong> listChamCong;
    ThongKeNhanSuAdapter adapter;
    EditText edt_timkiemthang_thongkenhansu;
    TextView txtV_Tenbophan;
    Button btn_Chonthang_thongkenhansu;
    ImageButton ibtnExit_Thongkenhansu;
    String bophan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_bo_phan_gio_cong);

        Intent intent = getIntent();
        bophan = intent.getStringExtra("bophan");
        AnhXa();

        listChamCong = new ArrayList<>();
        adapter = new ThongKeNhanSuAdapter(this, listChamCong);

        Load();

        recV_DanhSachNhanSu.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recV_DanhSachNhanSu.setAdapter(adapter);

        if (bophan.equals("Production")) {
            txtV_Tenbophan.setText("Bộ phận Production");
        } else if (bophan.equals("QA")) {
            txtV_Tenbophan.setText("Bộ phận QA");
        }
    }

    private void AnhXa() {
        recV_DanhSachNhanSu = findViewById(R.id.recV_DanhSachNhanSu);
        registerForContextMenu(recV_DanhSachNhanSu);
        txtV_Tenbophan = findViewById(R.id.txtV_Tenbophan);

        edt_timkiemthang_thongkenhansu = findViewById(R.id.edt_timkiemthang_thongkenhansu);
        edt_timkiemthang_thongkenhansu.addTextChangedListener(new TextWatcher() {
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

        btn_Chonthang_thongkenhansu = findViewById(R.id.btn_Chonthang_thongkenhansu);
        btn_Chonthang_thongkenhansu.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(this, new MonthPickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(int selectedMonth, int selectedYear) {
                    edt_timkiemthang_thongkenhansu.setText("0" + (selectedMonth + 1) + "/" + selectedYear);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

            builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(1990)
                    .setActivatedYear(calendar.get(Calendar.YEAR))
                    .setMaxYear(2030)
                    .setTitle("Chọn tháng")
                    .build().show();
        });

        ibtnExit_Thongkenhansu = findViewById(R.id.ibtnExit_Thongkenhansu);
        ibtnExit_Thongkenhansu.setOnClickListener(view -> {
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
        listChamCong.addAll(BatDauActivity.database.ThongKeNhanSu(bophan));
        adapter.notifyDataSetChanged();
    }

    private void Load_TimKiem() {
        listChamCong.clear();
        listChamCong.addAll(BatDauActivity.database.ThongKeNhanSu_TimKiem(bophan, edt_timkiemthang_thongkenhansu.getText().toString()));
        adapter.notifyDataSetChanged();
    }

}