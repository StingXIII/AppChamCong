package com.example.appchamcong.QuanLy;

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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyNhanSuAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class PhongBan extends AppCompatActivity {

    RecyclerView recV_DanhSachTaiKhoan_qlns;
    TaiKhoan taiKhoanDTO;
    ArrayList<TaiKhoan> listTaiKhoan;
    QuanLyNhanSuAdapter adapter;
    EditText edt_timkiemtaikhoan_qlns;
    ImageButton ibtn_Exit_phongnhansu;
    int phongban, bophan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);

        BatDauActivity.database = new Database(PhongBan.this);
        Intent intent = getIntent();
        phongban = intent.getIntExtra("phongban",1);
        bophan = intent.getIntExtra("bophan",1);
        AnhXa();

        listTaiKhoan = new ArrayList<>();
        adapter = new QuanLyNhanSuAdapter(PhongBan.this, listTaiKhoan);

        Load();

        recV_DanhSachTaiKhoan_qlns.setLayoutManager( new LinearLayoutManager(PhongBan.this, LinearLayoutManager.VERTICAL,false));
        recV_DanhSachTaiKhoan_qlns.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    private void Load() {
        listTaiKhoan.clear();
        listTaiKhoan.addAll(BatDauActivity.database.QuanLyNhanSu(bophan, phongban));
        adapter.notifyDataSetChanged();
    }

    private void Load_TimKiem() {
        listTaiKhoan.clear();
        listTaiKhoan.addAll(BatDauActivity.database.QuanLyNhanSu_TimKiem(edt_timkiemtaikhoan_qlns.getText().toString(), bophan, phongban));
        adapter.notifyDataSetChanged();
    }

    private void AnhXa() {
        ibtn_Exit_phongnhansu = findViewById(R.id.ibtn_Exit_phongnhansu);
        recV_DanhSachTaiKhoan_qlns = findViewById(R.id.recV_DanhSachTaiKhoan_qlns);
        registerForContextMenu(recV_DanhSachTaiKhoan_qlns);
        edt_timkiemtaikhoan_qlns = findViewById(R.id.edt_timkiemtaikhoan_qlns);

        edt_timkiemtaikhoan_qlns.addTextChangedListener(new TextWatcher() {
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

        ibtn_Exit_phongnhansu.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QuanLyNhanSuAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iXem:
                Intent iCapnhat = new Intent(PhongBan.this, ThongTinNhanSu.class);
                iCapnhat.putExtra("idtaikhoan", listTaiKhoan.get(position).getMATK());
                Toast.makeText(PhongBan.this, "ID tài khoản là: " + listTaiKhoan.get(position).getMATK(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            case R.id.iNghi:
                taiKhoanDTO = listTaiKhoan.get(position);
                ShowDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PhongBan.this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn cho nghỉ việc không ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BatDauActivity.database.NhanSuNghiViec(taiKhoanDTO.getMATK());
                Toast.makeText(PhongBan.this, "Thành công !", Toast.LENGTH_SHORT).show();
                Load();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}