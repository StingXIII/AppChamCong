package com.example.appchamcong.QuanLy.Fragment_QuanLy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchamcong.R;

public class Duyet_TaiKhoan extends Fragment {

    View view;

    public Duyet_TaiKhoan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_duyet__tai_khoan, container, false);



        return view;
    }
}