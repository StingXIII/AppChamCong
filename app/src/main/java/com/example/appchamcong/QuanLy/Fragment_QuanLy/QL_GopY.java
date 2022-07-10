package com.example.appchamcong.QuanLy.Fragment_QuanLy;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.appchamcong.Adapter.QuanLyGopYAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.GopY;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.QuanLy.Xem_GopY;
import com.example.appchamcong.R;

import java.util.ArrayList;

public class QL_GopY extends Fragment {

    View view;
    RecyclerView recV_DanhSachGopY;
    GopY gopYDTO;
    ArrayList<GopY> listGopy;
    QuanLyGopYAdapter adapter;

    public QL_GopY() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_q_l__gop_y, container, false);

        BatDauActivity.database = new Database(getActivity());
        recV_DanhSachGopY = view.findViewById(R.id.recV_DanhSachGopY);
        registerForContextMenu(recV_DanhSachGopY);

        listGopy = new ArrayList<>();
        adapter = new QuanLyGopYAdapter(this, listGopy);
        Load();

        recV_DanhSachGopY.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recV_DanhSachGopY.setAdapter(adapter);

        return view;
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
                Intent iCapnhat = new Intent(getActivity(), Xem_GopY.class);
                iCapnhat.putExtra("IDGY", listGopy.get(position).getIDGY());
                Toast.makeText(getActivity(), "ID là: " + listGopy.get(position).getIDGY(), Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa nó hay không ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BatDauActivity.database.XoaGY(gopYDTO.getIDGY());
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