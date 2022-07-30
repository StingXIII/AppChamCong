package com.example.appchamcong.NhanSu;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.BatDauActivity;
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

    TextView lbl;
    DBHelper controller = new DBHelper(this);
    Button btn_import;
    ListView lv;

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

        lv = findViewById(R.id.lstView);
        mLayout = findViewById(R.id.main_layout);
        btn_import = findViewById(R.id.btn_import);

        btn_import.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("gagt/sdf");
            try {
                OpenFilePicker();
            } catch (ActivityNotFoundException e) {

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
//        try {
//            Cursor cursor = BatDauActivity.database.Getdata("SELECT ID,NGAYCONG FROM CHAMCONG");
//            while (cursor.moveToNext()) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar c = Calendar.getInstance();
//                try {
//                    c.setTime(sdf.parse("1899-12-30"));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                c.add(Calendar.DATE, Integer.parseInt(cursor.getString(1)));
//                sdf = new SimpleDateFormat("MM/yyyy");
//                Date resultdate = new Date(c.getTimeInMillis());
//                String dateInString = sdf.format(resultdate);
//                BatDauActivity.database.CAPNHATNGAYCONG(cursor.getInt(0), dateInString);
//            }
//        } catch (Exception e){
//            Toast.makeText(ChamCongActivity.this, "cccc", Toast.LENGTH_SHORT).show();
//        }
        try {
            if (controller == null) {
                DBHelper controller = new DBHelper(ChamCongActivity.this);
            }
            ArrayList<HashMap<String, String>> myList = controller.getProducts();
            if (myList.size() != 0) {
                lv = findViewById(R.id.lstView);
                ListAdapter adapter = new SimpleAdapter(ChamCongActivity.this, myList,
                        R.layout.lst_template, new String[]{DBHelper.MANHANVIEN, DBHelper.TENNHANVIEN,
                        DBHelper.PHONGBAN, DBHelper.NGAYCONG, DBHelper.GIOCONG},
                        new int[]{R.id.txtManhanvien,R.id.txtTennhanvien, R.id.txtPhongban,R.id.txtNgayCong,
                                R.id.txtGiocong});
                lv.setAdapter(adapter);
            }
        } catch (Exception ex) {
            Toast("FillList error: " + ex.getMessage(), ex);
        }
    }

    public void ReadExcelFile(Context context, Uri uri) {
        try {
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
}