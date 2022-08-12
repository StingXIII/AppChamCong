package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import retrofit2.http.PUT;

public class ThongKeNhanSu extends AppCompatActivity {

    BarChart mBarChart;
    int Production_1, QC_1, QA_1, HR_1, Accounting_1, Pur_1;
    int Production_2, QC_2, QA_2, HR_2, Accounting_2, Pur_2;
    ImageButton ibtnExit_Thongkenhansu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_nhan_su);

        mBarChart = findViewById(R.id.mBarChart);

        BarDataSet barDataSet_ConLam = new BarDataSet(barEntries1(), "Còn làm");
        barDataSet_ConLam.setColors(Color.GREEN);

        BarDataSet barDataSet_DaNghi = new BarDataSet(barEntries2(), "Đã nghỉ");
        barDataSet_DaNghi.setColors(Color.RED);

        BarData barData = new BarData(barDataSet_ConLam, barDataSet_DaNghi);
        mBarChart.setData(barData);

        String[] Bophan = new String[] {"Production", "QC", "QA", "HR", "Accounting", "Pur"};
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Bophan));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        mBarChart.setDragEnabled(true);
        mBarChart.setVisibleXRangeMaximum(3);

        float barSpace = 0.18f;
        float groupSpace = 0.44f;
        barData.setBarWidth(0.10f);

        mBarChart.getXAxis().setAxisMaximum(0);
        mBarChart.getXAxis().setAxisMaximum(0 + mBarChart.getBarData().getGroupWidth(groupSpace,barSpace)*7);
        mBarChart.getAxisLeft().setAxisMinimum(0);

        mBarChart.groupBars(0, groupSpace, barSpace);

        mBarChart.invalidate();

        ibtnExit_Thongkenhansu = findViewById(R.id.ibtnExit_Thongkenhansu);
        ibtnExit_Thongkenhansu.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private ArrayList<BarEntry> barEntries1() {
        ThongKe_ConLam_Production();
        ThongKe_ConLam_QC();
        ThongKe_ConLam_QA();
        ThongKe_ConLam_HR();
        ThongKe_ConLam_Account();
        ThongKe_ConLam_Pur();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, Production_1));
        barEntries.add(new BarEntry(1, QC_1));
        barEntries.add(new BarEntry(2, QA_1));
        barEntries.add(new BarEntry(3, HR_1));
        barEntries.add(new BarEntry(4, Accounting_1));
        barEntries.add(new BarEntry(5, Pur_1));

        return barEntries;
    }

    private ArrayList<BarEntry> barEntries2() {
        ThongKe_NghiLam_Production();
        ThongKe_NghiLam_QC();
        ThongKe_NghiLam_QA();
        ThongKe_NghiLam_HR();
        ThongKe_NghiLam_Account();
        ThongKe_NghiLam_Pur();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, Production_2));
        barEntries.add(new BarEntry(1, QC_2));
        barEntries.add(new BarEntry(2, QA_2));
        barEntries.add(new BarEntry(3, HR_2));
        barEntries.add(new BarEntry(4, Accounting_2));
        barEntries.add(new BarEntry(5, Pur_2));

        return barEntries;
    }

    private void ThongKe_ConLam_Production() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 1 AND BOPHAN = 1");
        cursor.moveToNext();
        Production_1 = cursor.getInt(0);
    }

    private void ThongKe_ConLam_QC() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 1 AND BOPHAN = 2");
        cursor.moveToNext();
        QC_1 = cursor.getInt(0);
    }

    private void ThongKe_ConLam_QA() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 1 AND BOPHAN = 3");
        cursor.moveToNext();
        QA_1 = cursor.getInt(0);
    }

    private void ThongKe_ConLam_HR() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 1 AND BOPHAN = 4");
        cursor.moveToNext();
        HR_1 = cursor.getInt(0);
    }

    private void ThongKe_ConLam_Account() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 1 AND BOPHAN = 5");
        cursor.moveToNext();
        Accounting_1 = cursor.getInt(0);
    }

    private void ThongKe_ConLam_Pur() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 1 AND BOPHAN = 6");
        cursor.moveToNext();
        Pur_1 = cursor.getInt(0);
    }

    private void ThongKe_NghiLam_Production() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 2 AND BOPHAN = 1");
        cursor.moveToNext();
        Production_2 = cursor.getInt(0);
    }

    private void ThongKe_NghiLam_QC() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 2 AND BOPHAN = 2");
        cursor.moveToNext();
        QC_2 = cursor.getInt(0);
    }

    private void ThongKe_NghiLam_QA() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 2 AND BOPHAN = 3");
        cursor.moveToNext();
        QA_2 = cursor.getInt(0);
    }

    private void ThongKe_NghiLam_HR() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 2 AND BOPHAN = 4");
        cursor.moveToNext();
        HR_2 = cursor.getInt(0);
    }

    private void ThongKe_NghiLam_Account() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 2 AND BOPHAN = 5");
        cursor.moveToNext();
        Accounting_2 = cursor.getInt(0);
    }

    private void ThongKe_NghiLam_Pur() {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT COUNT (*) FROM TAIKHOAN WHERE TINHTRANG = 2 AND BOPHAN = 6");
        cursor.moveToNext();
        Pur_2 = cursor.getInt(0);
    }

}