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

public class ThongTinNhanSu extends AppCompatActivity {
    ArrayList<Category> listCategoryChucvu,listCategoryPhongban,listCategoryTinhtrang,
            listCategoryBophan,listCategoryQuyenhan;
    ArrayList<Category> listchucvu,listPhongban,listTinhtrang,listBophan,listQuyenhan;
    CategoryAdapter categoryAdapterChucvu,categoryAdapterPhongban,categoryAdapterTinhtrang,categoryAdapterBophan,categoryAdapterQuyenhan;
    EditText edtTennguoidung_ttns, edtSdt_ttns, edtDiachi_ttns, edt_Chucvu_ttns, edt_Phongban_ttns,
            edt_Tinhtrang_ttns, edtManhanvien_ttns, edt_Bophan_ttns, edt_Quyen_ttns, edtNgaysinh_ttns, edtEmail_ttns;
    Button btnCapnhat_ttns, btnHuyCN_ttns;
    ImageButton ibtnExit_ttns;
    CircleImageView imgHinhDaiDien_ttns;
    boolean isEnabled;
    int IDTK;
    Spinner spinner_Chucvu,spinner_Bophan,spinner_Phongban,spinner_Tinhtrang,spinner_Quyenhan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhan_su);

        Anhxa();
        Intent intent = getIntent();
        IDTK = intent.getIntExtra("idtaikhoan", 1);

        if (String.valueOf(IDTK) == null){
            return;
        }
        spinners();
        GetData();

    }

    private void spinners() {
        spinnerBophan(listCategoryBophan,spinner_Bophan,edt_Bophan_ttns);
        spinnerChucvu(spinner_Chucvu,edt_Chucvu_ttns);
        spinnerPhongban(listCategoryPhongban,spinner_Phongban,edt_Phongban_ttns);
        spinnerQuyenhan(listCategoryQuyenhan,spinner_Quyenhan,edt_Quyen_ttns);
        spinnerTinhtrang(listCategoryTinhtrang,spinner_Tinhtrang,edt_Tinhtrang_ttns);
    }
    private void spinnerTinhtrang(ArrayList<Category> categoryArrayList,Spinner spinner,EditText editText ) {
        categoryArrayList = getListCategoryTinhtrang();
        categoryAdapterTinhtrang = new CategoryAdapter(ThongTinNhanSu.this, R.layout.item_select, categoryArrayList);
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

    private void spinnerQuyenhan(ArrayList<Category> categoryArrayList,Spinner spinner,EditText editText ) {
        categoryArrayList = getListCategoryQuyenhan();
        categoryAdapterQuyenhan = new CategoryAdapter(ThongTinNhanSu.this, R.layout.item_select, categoryArrayList);
        spinner.setAdapter(categoryAdapterQuyenhan);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editText.setText(String.valueOf(categoryAdapterQuyenhan.getItem(position).getIDcategory()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Category> getListCategoryQuyenhan() {
        listQuyenhan = new ArrayList<>();
        listQuyenhan.clear();
        listQuyenhan.add(new Category(
                        " Quản lý ",
                        0
                )
        );
        listQuyenhan.add(new Category(
                        " Nhân viên ",
                        1
                )
        );
        listQuyenhan.add(new Category(
                        " Nhân sự ",
                        2
                )
        );

        return listQuyenhan;
    }

    private void spinnerPhongban(ArrayList<Category> categoryArrayList,Spinner spinner,EditText editText ) {
        categoryArrayList = getListCategoryPhongban();
        categoryAdapterPhongban = new CategoryAdapter(ThongTinNhanSu.this, R.layout.item_select, categoryArrayList);
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
        categoryAdapterBophan = new CategoryAdapter(ThongTinNhanSu.this, R.layout.item_select, categoryArrayList);
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
        categoryAdapterChucvu = new CategoryAdapter(ThongTinNhanSu.this, R.layout.item_select, getListCategoryChucvu());
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
        String manhanvien = taiKhoanDTO.getTENTK();
        String tennguoidung = taiKhoanDTO.getTENNGUOIDUNG();
        String ngaysinh = taiKhoanDTO.getNGAYSINH();
        String email = taiKhoanDTO.getEMAIL();
        int sdt = taiKhoanDTO.getSDT();
        String diachi = taiKhoanDTO.getDIACHI();
        int chucvu = taiKhoanDTO.getCHUCVU();
        int bophan = taiKhoanDTO.getBOPHAN();
        int phongban = taiKhoanDTO.getPHONGBAN();
        int tinhtrang = taiKhoanDTO.getTINHTRANG();
        int quyen = taiKhoanDTO.getQUYEN();
        enableControl();

        edtTennguoidung_ttns.setText(tennguoidung);
        edtSdt_ttns.setText(String.valueOf(sdt));
        edtDiachi_ttns.setText(diachi);
        edtNgaysinh_ttns.setText(ngaysinh);
        edtEmail_ttns.setText(email);
        edt_Chucvu_ttns.setText(String.valueOf(chucvu));
        edt_Phongban_ttns.setText(String.valueOf(phongban));
        edt_Tinhtrang_ttns.setText(String.valueOf(tinhtrang));
        edtManhanvien_ttns.setText(String.valueOf(manhanvien));
        edt_Bophan_ttns.setText(String.valueOf(bophan));
        edt_Quyen_ttns.setText(String.valueOf(quyen));

        imgHinhDaiDien_ttns.setEnabled(false);
        edtTennguoidung_ttns.setEnabled(false);
        edtSdt_ttns.setEnabled(false);
        edtDiachi_ttns.setEnabled(false);

        if (taiKhoanDTO.getHINHANH() == null){
            imgHinhDaiDien_ttns.setImageResource(R.drawable.ic_baseline_account_circle_24);
        } else {
            byte[] hinhAnh = taiKhoanDTO.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            imgHinhDaiDien_ttns.setImageBitmap(bitmap);
        }
    }

    private void Anhxa() {
        //------------------------------------Spinner
        spinner_Chucvu = findViewById(R.id.spinner_Chucvu);
        spinner_Bophan = findViewById(R.id.spinner_Bophan);
        spinner_Phongban = findViewById(R.id.spinner_Phongban);
        spinner_Tinhtrang = findViewById(R.id.spinner_Tinhtrang);
        spinner_Quyenhan = findViewById(R.id.spinner_Quyenhan);

        //------------------------------
        edtTennguoidung_ttns = findViewById(R.id.edtTennguoidung_ttns);
        edtSdt_ttns = findViewById(R.id.edtSdt_ttns);
        edtDiachi_ttns = findViewById(R.id.edtDiachi_ttns);
        edt_Chucvu_ttns = findViewById(R.id.edt_Chucvu_ttns);
        edt_Phongban_ttns = findViewById(R.id.edt_Phongban_ttns);
        edt_Tinhtrang_ttns = findViewById(R.id.edt_Tinhtrang_ttns);
        btnCapnhat_ttns = findViewById(R.id.btnCapnhat_ttns);
        btnHuyCN_ttns = findViewById(R.id.btnHuyCN_ttns);
        ibtnExit_ttns = findViewById(R.id.ibtnExit_ttns);
        imgHinhDaiDien_ttns = findViewById(R.id.imgHinhDaiDien_ttns);
        edt_Bophan_ttns = findViewById(R.id.edt_Bophan_ttns);
        edtManhanvien_ttns = findViewById(R.id.edtManhanvien_ttns);
        edt_Quyen_ttns = findViewById(R.id.edt_Quyen_ttns);
        edtNgaysinh_ttns = findViewById(R.id.edtNgaysinh_ttns);
        edtEmail_ttns = findViewById(R.id.edtEmail_ttns);

        ibtnExit_ttns.setOnClickListener(view -> {
            onBackPressed();
        });

        btnHuyCN_ttns.setOnClickListener(view -> {
            onBackPressed();
        });

        btnCapnhat_ttns.setOnClickListener(view -> {
            isEnabled = !isEnabled;
            enableControl();
            String MANV = edtManhanvien_ttns.getText().toString().trim();
            String TENND = edtTennguoidung_ttns.getText().toString().trim();
            int SDT = Integer.parseInt(edtSdt_ttns.getText().toString().trim());
            String DC = edtDiachi_ttns.getText().toString().trim();
            int CV = Integer.parseInt(edt_Chucvu_ttns.getText().toString().trim());
            int BP = Integer.parseInt(edt_Bophan_ttns.getText().toString().trim());
            int PB = Integer.parseInt(edt_Phongban_ttns.getText().toString().trim());
            int TT = Integer.parseInt(edt_Tinhtrang_ttns.getText().toString().trim());
            int QU = Integer.parseInt(edt_Quyen_ttns.getText().toString().trim());
            String NS = edtNgaysinh_ttns.getText().toString().trim();
            String EM = edtEmail_ttns.getText().toString().trim();

            if (isEnabled){
                btnCapnhat_ttns.setText("Lưu");
            }
            else{
                btnCapnhat_ttns.setText("Cập nhật");

                BatDauActivity.database.CapNhatNhanSu_QL(IDTK, MANV, TENND, SDT, DC, QU, CV, BP, PB, TT, NS, EM);
                Toast.makeText(ThongTinNhanSu.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void enableControl() {
        edt_Chucvu_ttns.setEnabled(false);
        edt_Phongban_ttns.setEnabled(false);
        edt_Tinhtrang_ttns.setEnabled(false);
        edt_Bophan_ttns.setEnabled(false);
        edt_Quyen_ttns.setEnabled(false);
        //--- mau
        edt_Chucvu_ttns.setBackgroundResource(R.color.xamnhat);
        edt_Phongban_ttns.setBackgroundResource(R.color.xamnhat);
        edt_Tinhtrang_ttns.setBackgroundResource(R.color.xamnhat);
        edt_Bophan_ttns.setBackgroundResource(R.color.xamnhat);
        edt_Quyen_ttns.setBackgroundResource(R.color.xamnhat);

        edtManhanvien_ttns.setEnabled(isEnabled);

        edtDiachi_ttns.setEnabled(isEnabled);
        edtSdt_ttns.setEnabled(isEnabled);
        edtTennguoidung_ttns.setEnabled(isEnabled);
        imgHinhDaiDien_ttns.setEnabled(isEnabled);
        edtNgaysinh_ttns.setEnabled(isEnabled);
        edtEmail_ttns.setEnabled(isEnabled);
    }
}