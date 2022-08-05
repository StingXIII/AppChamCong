package com.example.appchamcong.QuanLy.Fragment_QuanLy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.appchamcong.Adapter.NangLucAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.Nangluc;
import com.example.appchamcong.QuanLy.ThongtinNangLucActivity;
import com.example.appchamcong.R;

import java.util.ArrayList;


public class NangLuc_Fragment extends Fragment {
    ArrayList<Nangluc> nanglucArrayList;
    NangLucAdapter adapter;
    GridView lstView;
    View view;

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
        AnhXa();
        nanglucArrayList = new ArrayList<>();
        adapter = new NangLucAdapter(NangLuc_Fragment.this, R.layout.nangluc, nanglucArrayList);
        lstView.setAdapter(adapter);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ThongtinNangLucActivity.class);


                intent.putExtra("position",i);
                startActivity(intent);

            }
        });
        GetData();
        return view;

    }

    private void AnhXa() {
        lstView = view.findViewById(R.id.lstView);
    }
    private void GetData() {
        //get data
        Cursor cursor = BatDauActivity.database.Getdata("SELECT * FROM NANGLUC");
        nanglucArrayList.clear();
        while (cursor.moveToNext())
        {
            nanglucArrayList.add(new Nangluc(
                 cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}