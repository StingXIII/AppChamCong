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
import com.example.appchamcong.R;

public class QL_NhanSu extends Fragment {

    View view;
    ImageView img_Production, img_QA, img_QC, img_HR, img_Accounting, img_Pur;

    public QL_NhanSu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_q_l__nhan_su, container, false);

        AnhXa();
        SuKien();

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private void AnhXa() {
        img_Production = view.findViewById(R.id.img_Production);
        img_QA = view.findViewById(R.id.img_QA);
        img_QC = view.findViewById(R.id.img_QC);
        img_HR = view.findViewById(R.id.img_HR);
        img_Accounting = view.findViewById(R.id.img_Accounting);
        img_Pur = view.findViewById(R.id.img_Pur);
    }

    private void SuKien() {
        img_Production.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BP_Production.class));
        });

        img_QA.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BP_QC.class));
        });

        img_QC.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BP_QC.class));
        });

        img_HR.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BP_QC.class));
        });

        img_Accounting.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BP_QC.class));
        });

        img_Pur.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BP_QC.class));
        });
    }
}