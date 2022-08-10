package com.example.appchamcong.NhanSu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyGopYAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.GopY;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QL_PhanAnhActivity extends AppCompatActivity {

    RecyclerView recV_DanhSachPhanAnh;
    GopY gopYDTO;
    ArrayList<GopY> listGopy;
    QuanLyGopYAdapter adapter;
    ImageButton quaylai_phananh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_phan_anh);

        BatDauActivity.database = new Database(QL_PhanAnhActivity.this);
        recV_DanhSachPhanAnh = findViewById(R.id.recV_DanhSachPhanAnh);
        registerForContextMenu(recV_DanhSachPhanAnh);

        quaylai_phananh = findViewById(R.id.quaylai_phananh);
        quaylai_phananh.setOnClickListener(view -> {
            onBackPressed();
        });

        listGopy = new ArrayList<>();
        adapter = new QuanLyGopYAdapter(QL_PhanAnhActivity.this, listGopy);
        Load();

        recV_DanhSachPhanAnh.setLayoutManager( new LinearLayoutManager(QL_PhanAnhActivity.this, LinearLayoutManager.VERTICAL,false));
        recV_DanhSachPhanAnh.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    public void Load(){
        listGopy.clear();
        listGopy.addAll(BatDauActivity.database.QuanLyGopY());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QuanLyGopYAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent iCapnhat = new Intent(QL_PhanAnhActivity.this, Xem_PhanAnhActivity.class);
                iCapnhat.putExtra("IDGY", listGopy.get(position).getIDGY());
                Toast.makeText(QL_PhanAnhActivity.this, "ID là: " + listGopy.get(position).getIDGY(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            case R.id.iXoa:
                gopYDTO = listGopy.get(position);
                ShowDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QL_PhanAnhActivity.this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa nó hay không ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BatDauActivity.database.XoaGY(gopYDTO.getIDGY());
                Toast.makeText(QL_PhanAnhActivity.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
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