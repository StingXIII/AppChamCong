package com.example.appchamcong.QuanLy.Fragment_QuanLy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyGioCongAdapter;
import com.example.appchamcong.Adapter.QuanLyTaiKhoanAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.CapNhat_TaiKhoan;
import com.example.appchamcong.QuanLy.ThongTinChamCong;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QL_ChamCong extends Fragment {

    View view;
    RecyclerView recV_DanhSachChamCong_qlcong;
    ChamCong chamCong;
    ArrayList<ChamCong> listChamCong;
    QuanLyGioCongAdapter adapter;
    EditText edt_timkiemtaikhoan_qlcong;

    public QL_ChamCong() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_q_l__cham_cong, container, false);

        BatDauActivity.database = new Database(getActivity());
        AnhXa();

        listChamCong = new ArrayList<>();
        adapter = new QuanLyGioCongAdapter(this, listChamCong);

        Load();

        recV_DanhSachChamCong_qlcong.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recV_DanhSachChamCong_qlcong.setAdapter(adapter);

        return view;
    }

    private void AnhXa() {
        recV_DanhSachChamCong_qlcong = view.findViewById(R.id.recV_DanhSachChamCong_qlcong);
        registerForContextMenu(recV_DanhSachChamCong_qlcong);
        edt_timkiemtaikhoan_qlcong = view.findViewById(R.id.edt_timkiemtaikhoan_qlcong);

        edt_timkiemtaikhoan_qlcong.addTextChangedListener(new TextWatcher() {
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
    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    public void Load(){
        listChamCong.clear();
        listChamCong.addAll(BatDauActivity.database.QuanLyChamCong());
        adapter.notifyDataSetChanged();
    }

    private void Load_TimKiem() {
        listChamCong.clear();
        listChamCong.addAll(BatDauActivity.database.QuanLyChamCong_TimKiem(edt_timkiemtaikhoan_qlcong.getText().toString()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QuanLyGioCongAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iXemcong:
                Intent iCapnhat = new Intent(getActivity(), ThongTinChamCong.class);
                iCapnhat.putExtra("IDCHAMCONG", listChamCong.get(position).getID());
                Toast.makeText(getActivity(), "Mã NV là: " + listChamCong.get(position).getMANV(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            case R.id.iXoacong:
                chamCong = listChamCong.get(position);
                ShowDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa nó hay không ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BatDauActivity.database.XoaCong(chamCong.getID());
                Toast.makeText(getContext(), "Xóa thành công !", Toast.LENGTH_SHORT).show();
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