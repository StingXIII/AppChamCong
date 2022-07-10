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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyNhanSuAdapter;
import com.example.appchamcong.Adapter.QuanLyTaiKhoanAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.CapNhat_NhanSu;
import com.example.appchamcong.QuanLy.CapNhat_TaiKhoan;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QL_NhanSu extends Fragment {

    View view;
    RecyclerView recV_DanhSachNhanSu_qlns;
    TaiKhoan taiKhoanDTO;
    ArrayList<TaiKhoan> listTaiKhoan;
    QuanLyTaiKhoanAdapter adapter;
    EditText edt_timkiemnhansu_qlns;

    public QL_NhanSu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_q_l__nhan_su, container, false);

        BatDauActivity.database = new Database(getActivity());
        AnhXa();

        listTaiKhoan = new ArrayList<>();
        adapter = new QuanLyTaiKhoanAdapter(this, listTaiKhoan);

        Load();

        recV_DanhSachNhanSu_qlns.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recV_DanhSachNhanSu_qlns.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    private void Load() {
        listTaiKhoan.clear();
        listTaiKhoan.addAll(BatDauActivity.database.QuanLyTaiKhoan(1));
        adapter.notifyDataSetChanged();
    }

    private void Load_TimKiem() {
        listTaiKhoan.clear();
        listTaiKhoan.addAll(BatDauActivity.database.QuanLyTaiKhoan_TimKiem(edt_timkiemnhansu_qlns.getText().toString()));
        adapter.notifyDataSetChanged();
    }

    private void AnhXa() {
        recV_DanhSachNhanSu_qlns = view.findViewById(R.id.recV_DanhSachNhanSu_qlns);
        registerForContextMenu(recV_DanhSachNhanSu_qlns);
        edt_timkiemnhansu_qlns = view.findViewById(R.id.edt_timkiemnhansu_qlns);

        edt_timkiemnhansu_qlns.addTextChangedListener(new TextWatcher() {
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
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QuanLyTaiKhoanAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent iCapnhat = new Intent(getActivity(), CapNhat_NhanSu.class);
                iCapnhat.putExtra("idtaikhoan", listTaiKhoan.get(position).getMATK());
                Toast.makeText(getActivity(), "ID tài khoản là: " + listTaiKhoan.get(position).getMATK(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            case R.id.iXoa:
                taiKhoanDTO = listTaiKhoan.get(position);
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
                BatDauActivity.database.XoaTK(taiKhoanDTO.getMATK());
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