package com.example.appchamcong.QuanLy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appchamcong.Adapter.CategoryAdapter;
import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.Category;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaiKhoan_Duyet extends AppCompatActivity {
    Spinner spinner_Chucvu,spinner_Bophan,spinner_Phongban,spinner_Tinhtrang,spinner_Quyenhan;

    ArrayList<Category> listCategoryChucvu,listCategoryPhongban,listCategoryTinhtrang,
            listCategoryBophan,listCategoryQuyenhan;
    ArrayList<Category> listchucvu,listPhongban,listTinhtrang,listBophan,listQuyenhan;
    CategoryAdapter categoryAdapterChucvu,categoryAdapterPhongban,categoryAdapterTinhtrang,categoryAdapterBophan,categoryAdapterQuyenhan;
    EditText edt_Manhanvien_cd, edt_Tennhanvien_cd, edt_Bophan_cd, edt_Phongban_cd,
            edt_Chucvu_cd, edt_Tinhtrang_cd;
    Button btnDuyet_cd, btnHuy_cd;
    ImageButton ibtnExit_cd;
    int IDTK;

    private boolean isEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_duyet);

        AnhXa();
        Intent intent = getIntent();
        IDTK = intent.getIntExtra("IDTK", 1);

        if (String.valueOf(IDTK) == null){
            return;
        }
        spinners();
        GetData();
    }
    private void spinners() {
        spinnerBophan(listCategoryBophan,spinner_Bophan,edt_Bophan_cd);
        spinnerChucvu(spinner_Chucvu,edt_Chucvu_cd);
        spinnerPhongban(listCategoryPhongban,spinner_Phongban,edt_Phongban_cd);
        spinnerTinhtrang(listCategoryTinhtrang,spinner_Tinhtrang,edt_Tinhtrang_cd);
    }
    private void spinnerTinhtrang(ArrayList<Category> categoryArrayList,Spinner spinner,EditText editText ) {
        categoryArrayList = getListCategoryTinhtrang();
        categoryAdapterTinhtrang = new CategoryAdapter(TaiKhoan_Duyet.this, R.layout.item_select, categoryArrayList);
        spinner.setAdapter(categoryAdapterTinhtrang);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editText.setText(String.valueOf(categoryAdapterTinhtrang.getItem(position).getIDcategory()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Category> getListCategoryTinhtrang() {
        listTinhtrang = new ArrayList<>();
        listTinhtrang.clear();
        listTinhtrang.add(new Category(
                        " Chờ xét duyệt ",
                        0
                )
        );
        listTinhtrang.add(new Category(
                        " Đang làm ",
                        1
                )
        );
        listTinhtrang.add(new Category(
                        " Đã nghỉ ",
                        2
                )
        );

        return listTinhtrang;
    }



    private void spinnerPhongban(ArrayList<Category> categoryArrayList,Spinner spinner,EditText editText ) {
        categoryArrayList = getListCategoryPhongban();
        categoryAdapterPhongban = new CategoryAdapter(TaiKhoan_Duyet.this, R.layout.item_select, categoryArrayList);
        spinner.setAdapter(categoryAdapterPhongban);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editText.setText(String.valueOf(categoryAdapterPhongban.getItem(position).getIDcategory()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Category> getListCategoryPhongban() {
        listPhongban = new ArrayList<>();
        listPhongban.clear();
        listPhongban.add(new Category(
                        " 1 ",
                        1
                )
        );
        listPhongban.add(new Category(
                        " 2 ",
                        2
                )
        );
        listPhongban.add(new Category(
                        " 3 ",
                        3
                )
        );
        listPhongban.add(new Category(
                        " 4 ",
                        4
                )
        );
        return listPhongban;
    }

    private void spinnerBophan(ArrayList<Category> categoryArrayList,Spinner spinner,EditText editText ) {
        categoryArrayList = getListCategoryBoPhan();
        categoryAdapterBophan = new CategoryAdapter(TaiKhoan_Duyet.this, R.layout.item_select, categoryArrayList);
        spinner.setAdapter(categoryAdapterBophan);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editText.setText(String.valueOf(categoryAdapterBophan.getItem(position).getIDcategory()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void spinnerChucvu(Spinner spinner,EditText editText ) {
        categoryAdapterChucvu = new CategoryAdapter(TaiKhoan_Duyet.this, R.layout.item_select, getListCategoryChucvu());
        spinner.setAdapter(categoryAdapterChucvu);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(String.valueOf(categoryAdapterChucvu.getItem(position).getIDcategory()));
//                Toast.makeText(ThongTinNhanSu.this, ""+ categoryAdapterChucvu.getItem(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Category> getListCategoryChucvu() {
        listchucvu = new ArrayList<>();
        listchucvu.clear();
        listchucvu.add(new Category(
                        " Staff ",
                        1
                )
        );
        listchucvu.add(new Category(
                        " Supervisor ",
                        2
                )
        );
        listchucvu.add(new Category(
                        " Manager ",
                        3
                )
        );
        listchucvu.add(new Category(
                        " BOD ",
                        4
                )
        );
        listchucvu.add(new Category(
                        " Leader ",
                        5
                )
        );
        listchucvu.add(new Category(
                        " Senior Leader ",
                        6
                )
        );
        listchucvu.add(new Category(
                        " Operator ",
                        7
                )
        );
        return listchucvu;
    }

    private ArrayList<Category> getListCategoryBoPhan() {
        listBophan = new ArrayList<>();
        listBophan.clear();
        listBophan.add(new Category(
                        " Production ",
                        1
                )
        );
        listBophan.add(new Category(
                        " QA ",
                        2
                )
        );
        listBophan.add(new Category(
                        " QC ",
                        3
                )
        );
        listBophan.add(new Category(
                        " HR ",
                        4
                )
        );
        listBophan.add(new Category(
                        " Accounting ",
                        5
                )
        );
        listBophan.add(new Category(
                        " Pur ",
                        6
                )
        );




        return listBophan;
    }
    @Override
    protected void onStart() {
        GetData();
        super.onStart();
    }

    private void GetData() {
        //get data
        TaiKhoan taiKhoanDTO = BatDauActivity.database.Load(IDTK);
        int idtaikhoan = taiKhoanDTO.getMATK();
        String manhanvien = taiKhoanDTO.getTENTK();
        String tennhanvien = taiKhoanDTO.getTENNGUOIDUNG();
        int bophan = taiKhoanDTO.getBOPHAN();
        int phongban = taiKhoanDTO.getPHONGBAN();
        int chucvu = taiKhoanDTO.getCHUCVU();
        int tinhtrang = taiKhoanDTO.getTINHTRANG();

        enableControl();

        edt_Manhanvien_cd.setText(manhanvien);
        edt_Tennhanvien_cd.setText(tennhanvien);
        edt_Bophan_cd.setText(String.valueOf(bophan));
        edt_Phongban_cd.setText(String.valueOf(phongban));
        edt_Chucvu_cd.setText(String.valueOf(chucvu));
        edt_Tinhtrang_cd.setText(String.valueOf(tinhtrang));

        edt_Manhanvien_cd.setEnabled(false);
        edt_Tennhanvien_cd.setEnabled(false);
        edt_Bophan_cd.setEnabled(false);
        edt_Phongban_cd.setEnabled(false);
        edt_Chucvu_cd.setEnabled(false);
        edt_Tinhtrang_cd.setEnabled(false);
    }


    private void AnhXa() {
        //------------------------------------Spinner
        spinner_Chucvu = findViewById(R.id.spinner_Chucvu);
        spinner_Bophan = findViewById(R.id.spinner_Bophan);
        spinner_Phongban = findViewById(R.id.spinner_Phongban);
        spinner_Tinhtrang = findViewById(R.id.spinner_Tinhtrang);
        spinner_Quyenhan = findViewById(R.id.spinner_Quyenhan);

        //------------------------------


        edt_Manhanvien_cd = findViewById(R.id.edt_Manhanvien_cd);
        edt_Tennhanvien_cd = findViewById(R.id.edt_Tennhanvien_cd);
        edt_Bophan_cd = findViewById(R.id.edt_Bophan_cd);
        edt_Phongban_cd = findViewById(R.id.edt_Phongban_cd);
        edt_Chucvu_cd = findViewById(R.id.edt_Chucvu_cd);
        edt_Tinhtrang_cd = findViewById(R.id.edt_Tinhtrang_cd);

        btnDuyet_cd = findViewById(R.id.btnDuyet_cd);
        btnHuy_cd = findViewById(R.id.btnHuy_cd);
        ibtnExit_cd = findViewById(R.id.ibtnExit_cd);

        ibtnExit_cd.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuy_cd.setOnClickListener(view -> {
            onBackPressed();
        });

        btnDuyet_cd.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String MANV = edt_Manhanvien_cd.getText().toString().trim();
            String TENNV = edt_Tennhanvien_cd.getText().toString().trim();
            int BOPHAN =  Integer.parseInt(edt_Bophan_cd.getText().toString().trim());
            int PHONGBAN = Integer.parseInt(edt_Phongban_cd.getText().toString().trim());
            int CHUCVU = Integer.parseInt(edt_Chucvu_cd.getText().toString().trim());
            int TINHTRANG = Integer.parseInt(edt_Tinhtrang_cd.getText().toString().trim());

            if (isEnabled){
                btnDuyet_cd.setText("Lưu");
            }
            else{
                btnDuyet_cd.setText("Duyệt");

                BatDauActivity.database.CapNhatTaiKhoan_XetDuyet(IDTK, MANV, TENNV, BOPHAN, PHONGBAN, CHUCVU, TINHTRANG);
                Toast.makeText(TaiKhoan_Duyet.this, "Duyệt thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Manhanvien_cd.setEnabled(isEnabled);
        edt_Tennhanvien_cd.setEnabled(isEnabled);
        edt_Bophan_cd.setEnabled(false);
        edt_Phongban_cd.setEnabled(false);
        edt_Chucvu_cd.setEnabled(false);
        edt_Tinhtrang_cd.setEnabled(false);
    }
}