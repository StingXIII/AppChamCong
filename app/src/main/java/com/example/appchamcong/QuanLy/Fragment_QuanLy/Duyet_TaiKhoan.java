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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyGioCongAdapter;
import com.example.appchamcong.Adapter.QuanLyTaiKhoanAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.CapNhat_TaiKhoan;
import com.example.appchamcong.QuanLy.TaiKhoan_Duyet;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class Duyet_TaiKhoan extends Fragment {

    View view;
    RecyclerView recV_DanhSachTaiKhoanChoDuyet;
    ArrayList<TaiKhoan> listTaiKhoan;
    QuanLyTaiKhoanAdapter adapter;

    public Duyet_TaiKhoan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_duyet__tai_khoan, container, false);

        BatDauActivity.database = new Database(getActivity());
        AnhXa();

        listTaiKhoan = new ArrayList<>();
        adapter = new QuanLyTaiKhoanAdapter(this, listTaiKhoan);

        Load();

        recV_DanhSachTaiKhoanChoDuyet.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recV_DanhSachTaiKhoanChoDuyet.setAdapter(adapter);

        return view;
    }

    private void AnhXa() {
        recV_DanhSachTaiKhoanChoDuyet = view.findViewById(R.id.recV_DanhSachTaiKhoanChoDuyet);
        registerForContextMenu(recV_DanhSachTaiKhoanChoDuyet);
    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    public void Load(){
        listTaiKhoan.clear();
        listTaiKhoan.addAll(BatDauActivity.database.ChoDuyet(0));
        adapter.notifyDataSetChanged();
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
                Intent iCapnhat = new Intent(getActivity(), TaiKhoan_Duyet.class);
                iCapnhat.putExtra("IDTK", listTaiKhoan.get(position).getMATK());
                Toast.makeText(getActivity(), "ID tài khoản là: " + listTaiKhoan.get(position).getMATK(), Toast.LENGTH_SHORT).show();
                startActivity(iCapnhat);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}