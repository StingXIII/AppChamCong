package com.example.appchamcong.QuanLy.Fragment_QuanLy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.appchamcong.QuanLy.BOPHAN.BP_Production;
import com.example.appchamcong.QuanLy.BOPHAN.BP_QC;
import com.example.appchamcong.QuanLy.ThongKeNhanSu;
import com.example.appchamcong.R;

public class QL_ThongKe extends Fragment {

    View view;
    ImageView img_Thongkenhansu, img_Thongkecong;

    public QL_ThongKe() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_q_l__thong_ke, container, false);

        AnhXa();
        SuKien();

        return view;
    }

    private void AnhXa() {
        img_Thongkenhansu = view.findViewById(R.id.img_Thongkenhansu);
        img_Thongkecong = view.findViewById(R.id.img_Thongkecong);
    }

    private void SuKien() {
        img_Thongkenhansu.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ThongKeNhanSu.class));
        });

    }
}