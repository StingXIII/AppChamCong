package com.example.appchamcong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appchamcong.DTO.TaiKhoan;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinNguoiDungActivity extends AppCompatActivity {

    EditText edt_Tentaikhoan, edt_Sdtnguoidung, edt_Tennguoidung, edt_Diachinguoidung, edt_Ngaysinh, edt_Email;
    Button btn_Capnhatthongtin, btn_Huycapnhatthongtin;
    ImageButton ibtn_Exit;
    CircleImageView cimg_HinhDaiDien;
    boolean isEnabled;
    int id;

    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nguoi_dung);

        Anhxa();
        GetData();

    }

    @Override
    protected void onStart() {
        GetData();
        super.onStart();
    }

    private void GetData() {
        if (BatDauActivity.taiKhoanDTO.getMATK() == -1)
        {
            Toast.makeText(this, "Bạn chưa đăng nhập !", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, DangNhapActivity.class));
        } else {
            //get data
            id = BatDauActivity.taiKhoanDTO.getMATK();
            TaiKhoan taiKhoanDTO = BatDauActivity.database.Load(id);
            String tentaikhoan = taiKhoanDTO.getTENTK();
            int sdt = taiKhoanDTO.getSDT();
            String tennguoidung = taiKhoanDTO.getTENNGUOIDUNG();
            String diachi = taiKhoanDTO.getDIACHI();
            String ngaysinh = taiKhoanDTO.getNGAYSINH();
            String email = taiKhoanDTO.getEMAIL();
            enableControl();

            edt_Tentaikhoan.setText(tentaikhoan);
            edt_Sdtnguoidung.setText("0" + sdt);
            edt_Tennguoidung.setText(tennguoidung);
            edt_Diachinguoidung.setText(diachi);
            edt_Ngaysinh.setText(ngaysinh);
            edt_Email.setText(email);
            edt_Tentaikhoan.setEnabled(false);
            if (taiKhoanDTO.getHINHANH() == null){
                cimg_HinhDaiDien.setImageResource(R.drawable.baseline_account_circle_white_24);
            } else {
                byte[] hinhAnh = taiKhoanDTO.getHINHANH();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
                cimg_HinhDaiDien.setImageBitmap(bitmap);
            }
        }
    }

    private void Anhxa() {
        edt_Tentaikhoan = findViewById(R.id.edt_Tentaikhoan);
        edt_Sdtnguoidung = findViewById(R.id.edt_Sdtnguoidung);
        edt_Tennguoidung = findViewById(R.id.edt_Tennguoidung);
        edt_Diachinguoidung = findViewById(R.id.edt_Diachinguoidung);
        edt_Ngaysinh = findViewById(R.id.edt_Ngaysinh);
        edt_Email = findViewById(R.id.edt_Email);
        cimg_HinhDaiDien = findViewById(R.id.cimg_HinhDaiDien);

        ibtn_Exit = findViewById(R.id.ibtn_Exit);
        btn_Huycapnhatthongtin = findViewById(R.id.btn_Huycapnhatthongtin);
        btn_Capnhatthongtin = findViewById(R.id.btn_Capnhatthongtin);

        registerForContextMenu(cimg_HinhDaiDien);

        edt_Ngaysinh.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            final int yearNow = calendar.get(Calendar.YEAR);
            final int monthNow = calendar.get(Calendar.MONTH);
            final int dayNow = calendar.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DatePickerDialog datePickerDialog = new DatePickerDialog(ThongTinNguoiDungActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year, month, dayOfMonth);
                    edt_Ngaysinh.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }, yearNow, monthNow, dayNow);
            datePickerDialog.setTitle("Chọn ngày");
            datePickerDialog.show();
        });

        cimg_HinhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cimg_HinhDaiDien.showContextMenu();
            }
        });

        ibtn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_Huycapnhatthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_Capnhatthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnabled = !isEnabled;
                enableControl();
                int Sdt = Integer.parseInt(edt_Sdtnguoidung.getText().toString().trim());
                String TenNguoiDung = edt_Tennguoidung.getText().toString();
                String DiaChi = edt_Diachinguoidung.getText().toString();
                String NgaySinh = edt_Ngaysinh.getText().toString();
                String Email = edt_Email.getText().toString();

                if (isEnabled){
                    btn_Capnhatthongtin.setText("Lưu");
                }
                else{
                    btn_Capnhatthongtin.setText("Cập nhật");
                    // chuyen data image view -> mang byte[]
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) cimg_HinhDaiDien.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArray);
                    byte[] hinhAnh = byteArray.toByteArray();

                    BatDauActivity.database.CapNhatTaiKhoan(BatDauActivity.taiKhoanDTO.getMATK(), hinhAnh, Sdt, TenNguoiDung, DiaChi, NgaySinh, Email);
                    Toast.makeText(ThongTinNguoiDungActivity.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThongTinNguoiDungActivity.this, DangNhapActivity.class);
                    BatDauActivity.taiKhoanDTO = new TaiKhoan();
                    startActivity(intent);
                    Toast.makeText(ThongTinNguoiDungActivity.this, "Hãy đăng nhập lại !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void enableControl() {
        cimg_HinhDaiDien.setEnabled(isEnabled);
        edt_Sdtnguoidung.setEnabled(isEnabled);
        edt_Tennguoidung.setEnabled(isEnabled);
        edt_Diachinguoidung.setEnabled(isEnabled);
        edt_Ngaysinh.setEnabled(isEnabled);
        edt_Email.setEnabled(isEnabled);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nguoidung, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iFolder:
                Intent iFolder = new Intent(Intent.ACTION_PICK);
                iFolder.setType("image/*");
                startActivityForResult(iFolder,REQUEST_CODE_FOLDER);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            cimg_HinhDaiDien.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                cimg_HinhDaiDien.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_FOLDER:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }else
                {
                    Toast.makeText(ThongTinNguoiDungActivity.this," Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}