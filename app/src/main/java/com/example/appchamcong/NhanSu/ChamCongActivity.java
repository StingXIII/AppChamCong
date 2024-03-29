package com.example.appchamcong.NhanSu;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.Adapter.ChamCongAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.QuanLy.ThongTinChamCong;
import com.example.appchamcong.R;
import com.example.appchamcong.helpers.DBHelper;
import com.example.appchamcong.helpers.ExcelHelper;
import com.google.android.material.snackbar.Snackbar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChamCongActivity extends AppCompatActivity {

    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }
    DatePickerDialog.OnDateSetListener setListener;
    ArrayList<ChamCong> chamCongArrayList;
    TextView tb;
    DBHelper controller = new DBHelper(this);
    Button btn_import;
    GridView lv;
    ChamCongAdapter adapter;
    TextView thangnam;
    Button chonngay,btn_Xoa;


    private static final int PERMISSION_REQUEST_MEMORY_ACCESS = 0;
    private static final int requestcode = 1;
    private static String fileType = "";
    private View mLayout;
    private static String extensionXLS = "XLS";
    private static String extensionXLXS = "XLXS";
    ActivityResultLauncher<Intent> filePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_cong);
        AnhXa();
        chamCongArrayList = new ArrayList<>();
        lv = findViewById(R.id.lstView);
        mLayout = findViewById(R.id.main_layout);
        btn_import = findViewById(R.id.btn_import);

        Calendar today = Calendar.getInstance();

        final int year = today.get(Calendar.YEAR);
        final int month = today.get(Calendar.MONTH);
        final int day = today.get(Calendar.DAY_OF_MONTH);
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 += 1;
                String date = i1 + "/" + i;
                thangnam.setText(date);
                chamCongArrayList.clear();
                Log.e("ThNAGNAM", "0" + thangnam.getText().toString());
                chamCongArrayList = BatDauActivity.database.LOADCHAMCONG("0" + thangnam.getText().toString());
                adapter = new ChamCongAdapter(ChamCongActivity.this, R.layout.lst_template, chamCongArrayList);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent iCapnhat = new Intent(ChamCongActivity.this, ThongTinChamCong.class);
                        iCapnhat.putExtra("IDCHAMCONG", chamCongArrayList.get(i).getID());
                        Toast.makeText(ChamCongActivity.this, "Mã NV là: " + chamCongArrayList.get(i).getMANV(), Toast.LENGTH_SHORT).show();
                        startActivity(iCapnhat);
                    }
                });

            }
        };


        chonngay.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, setListener, year, month, day);

            datePickerDialog.getDatePicker().setMaxDate(today.getTime().getTime());
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });




        btn_import.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("gagt/sdf");
            try {
                OpenFilePicker();
            } catch (ActivityNotFoundException e) {

            }
        });
        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BatDauActivity.database.KIEMTRACHAMCONG("0" + thangnam.getText().toString()))
                {
                    BatDauActivity.database.DELETE_CHAMCONGREAL("0" + thangnam.getText().toString());
                    Toast.makeText(ChamCongActivity.this, "Xoá thành công !", Toast.LENGTH_SHORT).show();
                    chamCongArrayList.clear();
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(ChamCongActivity.this, "Không có dữ liệu!", Toast.LENGTH_SHORT).show();
                }

            }
        });



        filePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent intent1 = result.getData();

                        Uri uri = intent1.getData();
                        ReadExcelFile(ChamCongActivity.this
                                , uri);

                    }
                });


        FillList();
    }

    private void AnhXa() {
        lv = findViewById(R.id.lstView);
        chonngay = findViewById(R.id.chonngay);
        thangnam = findViewById(R.id.thangnam);
        btn_Xoa = findViewById(R.id.btn_Xoa);
    }

    private boolean CheckPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            Snackbar.make(mLayout, R.string.storage_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestStoragePermission();
                }
            }).show();


            return false;
        }
    }

    public void FillList() {

        try {
            if (controller == null) {
                DBHelper controller = new DBHelper(ChamCongActivity.this);
            }
            ArrayList<HashMap<String, String>> myList = controller.getProducts();
            if (myList.size() != 0) {
//                ListAdapter adapter = new SimpleAdapter(ChamCongActivity.this, myList,
//                        R.layout.lst_template, new String[]{DBHelper.MANHANVIEN, DBHelper.TENNHANVIEN,
//                        DBHelper.PHONGBAN,DBHelper.NGAYCONG , DBHelper.GIOCONG},
//                        new int[]{R.id.txtManhanvien,R.id.txtTennhanvien, R.id.txtPhongban,R.id.txtNgayCong,
//                                R.id.txtGiocong});

                btn_import.setText("LƯU");
                btn_import.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Toast.makeText(ChamCongActivity.this, "Đang lưu......", Toast.LENGTH_LONG).show();
                            Cursor cursor = BatDauActivity.database.Getdata("SELECT * FROM CHAMCONG");
                            while (cursor.moveToNext()) {

                                Log.e("TTTTT",cursor.getString(1) + " "
                                        + " " + cursor.getString(2)
                                        + " " + cursor.getString(3)
                                        + " " +  cursor.getString(4)
                                        + " " + cursor.getString(5)
                                        + " " + cursor.getString(6)
                                        + " " + cursor.getInt(7) );
                                BatDauActivity.database.INSERT_CHAMCONG(
                                        cursor.getString(1),
                                        cursor.getString(2),
                                        cursor.getString(3),
                                        cursor.getString(4),
                                        cursor.getString(5),
                                        cursor.getString(6),
                                        cursor.getInt(7)
                                );
                            }

                        } catch (Exception e)
                        {

                        }

                        BatDauActivity.database.DELETE_CHAMCONG();
                        Toast.makeText(ChamCongActivity.this, "Lưu thành công !", Toast.LENGTH_SHORT).show();
                        btn_import.setText("TẢI FILE");

                    }
                });
                lv.deferNotifyDataSetChanged();
            }
        } catch (Exception ex) {
            Toast("FillList error: " + ex.getMessage(), ex);
        }
    }

    public void ReadExcelFile(Context context, Uri uri) {
        try {
            Toast.makeText(context, "Đang tải.....", Toast.LENGTH_LONG).show();
            InputStream inStream;
            Workbook wb = null;

            try {
                inStream = context.getContentResolver().openInputStream(uri);

                if (fileType == extensionXLS)
                    wb = new HSSFWorkbook(inStream);
                else
                    wb = new XSSFWorkbook(inStream);

                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            DBHelper dbAdapter = new DBHelper(this);
            Sheet sheet1 = wb.getSheetAt(0);

            dbAdapter.open();
            dbAdapter.delete();
            dbAdapter.close();
            dbAdapter.open();
            ExcelHelper.insertExcelToSqlite(dbAdapter, sheet1);

            dbAdapter.close();

            FillList();
        } catch (Exception ex) {
            Toast("ReadExcelFile Error:" + ex.getMessage().toString(), ex);
        }
    }


    public void ChooseFile() {
        try {
            Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            fileIntent.addCategory(Intent.CATEGORY_OPENABLE);

            if (fileType == extensionXLS)
                fileIntent.setType("application/vnd.ms-excel");
            else
                fileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            filePicker.launch(fileIntent);
        } catch (Exception ex) {
            Toast("ChooseFile error: " + ex.getMessage().toString(), ex);

        }
    }

    void Toast(String message, Exception ex) {
        if (ex != null)
            Log.e("Error", ex.getMessage().toString());
        Toast.makeText(ChamCongActivity.this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_MEMORY_ACCESS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                OpenFilePicker();
            } else {
                Snackbar.make(mLayout, R.string.storage_access_denied,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void requestStoragePermission() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(ChamCongActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_MEMORY_ACCESS);

        } else {
            Snackbar.make(mLayout, R.string.storage_unavailable, Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_MEMORY_ACCESS);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_import_xls) {
            fileType = extensionXLS;
            OpenFilePicker();
        } else if (id == R.id.action_import_xlxs) {
            fileType = extensionXLXS;
            OpenFilePicker();
        }

        return super.onOptionsItemSelected(item);
    }

    public void OpenFilePicker() {
        try {
            if (CheckPermission()) {
                ChooseFile();
            }
        } catch (ActivityNotFoundException e) {

        }

    }
    public String ChuyenDate(String ngay)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse("1899-12-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, Integer.parseInt(ngay));
        sdf = new SimpleDateFormat("MM/yyyy");
        Date resultdate = new Date(c.getTimeInMillis());
        String dateInString = sdf.format(resultdate);
        return dateInString;
    }
}