package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeGioCong extends AppCompatActivity {

    private LineChart mLineChart;
    TextView txt_Thangchon;
    Button btn_Thangchon;
    ImageButton ibtnExit_Thongkecong;
    int Production_1, QC_1, QA_1, HR_1, Accounting_1, Pur_1;
    String strThang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_gio_cong);

        mLineChart = findViewById(R.id.mLineChart);
        txt_Thangchon = findViewById(R.id.txt_Thangchon);
        btn_Thangchon = findViewById(R.id.btn_Thangchon);
        ibtnExit_Thongkecong = findViewById(R.id.ibtnExit_Thongkecong);

        Sukien();
    }

    private void Sukien() {
        ibtnExit_Thongkecong.setOnClickListener(view -> {
            onBackPressed();
        });

        btn_Thangchon.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(ThongKeGioCong.this, new MonthPickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(int selectedMonth, int selectedYear) {
                    txt_Thangchon.setText("0" + (selectedMonth + 1) + "/" + selectedYear);
                    strThang = txt_Thangchon.getText().toString();

                    LineDataSet lineDataSet = new LineDataSet(barEntries(), "Giờ công");
                    lineDataSet.setColors(Color.GREEN);
                    Description description = new Description();
                    description.setText("Tháng");
                    mLineChart.setDescription(description);
                    LineData lineData = new LineData(lineDataSet);
                    mLineChart.setData(lineData);

                    String[] Bophan = new String[] {"Production", "QA", "QC", "HR", "Accounting", "Pur"};
                    XAxis xAxis = mLineChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(Bophan));
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setDrawGridLines(false);
                    xAxis.setDrawAxisLine(false);
                    xAxis.setGranularity(1f);
                    xAxis.setLabelRotationAngle(270);
                    mLineChart.animateY(2000);

                    mLineChart.invalidate();
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

            builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(1990)
                    .setActivatedYear(calendar.get(Calendar.YEAR))
                    .setMaxYear(2030)
                    .setTitle("Chọn tháng")
                    .build().show();

        });
    }

    private ArrayList<Entry> barEntries() {
        ThongKe_GioCong_Production(strThang);
        ThongKe_GioCong_QC(strThang);
        ThongKe_GioCong_QA(strThang);
        ThongKe_GioCong_HR(strThang);
        ThongKe_GioCong_Accounting(strThang);
        ThongKe_GioCong_Pur(strThang);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, Production_1));
        entries.add(new Entry(1, QA_1));
        entries.add(new Entry(2, QC_1));
        entries.add(new Entry(3, HR_1));
        entries.add(new Entry(4, Accounting_1));
        entries.add(new Entry(5, Pur_1));

        return entries;
    }

    private void ThongKe_GioCong_Production(String Thang) {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT SUM ( A.GIOCONG ) FROM CHAMCONGREAL A, TAIKHOAN B WHERE A.MANHANVIEN = B.TENTAIKHOAN AND B.BOPHAN = 1 AND A.THANGCONG = '" + Thang + "'");
        cursor.moveToNext();
        Production_1 = cursor.getInt(0);
    }

    private void ThongKe_GioCong_QA(String Thang) {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT SUM ( A.GIOCONG ) FROM CHAMCONGREAL A, TAIKHOAN B WHERE A.MANHANVIEN = B.TENTAIKHOAN AND B.BOPHAN = 2 AND A.THANGCONG = '" + Thang + "'");
        cursor.moveToNext();
        QA_1 = cursor.getInt(0);
    }

    private void ThongKe_GioCong_QC(String Thang) {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT SUM ( A.GIOCONG ) FROM CHAMCONGREAL A, TAIKHOAN B WHERE A.MANHANVIEN = B.TENTAIKHOAN AND B.BOPHAN = 3 AND A.THANGCONG = '" + Thang + "'");
        cursor.moveToNext();
        QC_1 = cursor.getInt(0);
    }

    private void ThongKe_GioCong_HR(String Thang) {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT SUM ( A.GIOCONG ) FROM CHAMCONGREAL A, TAIKHOAN B WHERE A.MANHANVIEN = B.TENTAIKHOAN AND B.BOPHAN = 4 AND A.THANGCONG = '" + Thang + "'");
        cursor.moveToNext();
        HR_1 = cursor.getInt(0);
    }

    private void ThongKe_GioCong_Accounting(String Thang) {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT SUM ( A.GIOCONG ) FROM CHAMCONGREAL A, TAIKHOAN B WHERE A.MANHANVIEN = B.TENTAIKHOAN AND B.BOPHAN = 5 AND A.THANGCONG = '" + Thang + "'");
        cursor.moveToNext();
        Accounting_1 = cursor.getInt(0);
    }

    private void ThongKe_GioCong_Pur(String Thang) {
        Cursor cursor = BatDauActivity.database.Getdata("SELECT SUM ( A.GIOCONG ) FROM CHAMCONGREAL A, TAIKHOAN B WHERE A.MANHANVIEN = B.TENTAIKHOAN AND B.BOPHAN = 6 AND A.THANGCONG = '" + Thang + "'");
        cursor.moveToNext();
        Pur_1 = cursor.getInt(0);
    }
}