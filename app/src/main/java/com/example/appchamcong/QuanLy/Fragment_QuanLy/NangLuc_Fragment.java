package com.example.appchamcong.QuanLy.Fragment_QuanLy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.appchamcong.Adapter.NangLucAdapter;
import com.example.appchamcong.Adapter.QuanLyNhanSuAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.Nangluc;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.PhongBan;
import com.example.appchamcong.QuanLy.ThongTinNhanSu;
import com.example.appchamcong.QuanLy.ThongtinNangLucActivity;
import com.example.appchamcong.R;

import java.util.ArrayList;


public class NangLuc_Fragment extends Fragment {

    View view;
    ArrayList<Nangluc> listNangLuc;
    NangLucAdapter adapter;
    RecyclerView recV_DanhSachXepLoai_nl;

    public NangLuc_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nang_luc_, container, false);

        BatDauActivity.database = new Database(getActivity());

        AnhXa();
        listNangLuc = new ArrayList<>();
        adapter = new NangLucAdapter(this, listNangLuc);

        Load();

        recV_DanhSachXepLoai_nl.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recV_DanhSachXepLoai_nl.setAdapter(adapter);

        return view;

    }

    @Override
    public void onStart() {
        Load();
        super.onStart();
    }

    private void Load() {
        listNangLuc.clear();
        listNangLuc.addAll(BatDauActivity.database.XepLoaiNangLuc());
        adapter.notifyDataSetChanged();
    }

    private void AnhXa() {
        recV_DanhSachXepLoai_nl = view.findViewById(R.id.recV_DanhSachXepLoai_nl);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = NangLucAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iXem:
                Intent iCapnhat = new Intent(getActivity(), ThongtinNangLucActivity.class);
                iCapnhat.putExtra("position", listNangLuc.get(position).getMANHANVIEN());
                startActivity(iCapnhat);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}