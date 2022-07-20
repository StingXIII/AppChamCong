package com.example.appchamcong;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.DAO.TaiKhoanDAO;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.NhanSu.TrangChuActivity;
import com.example.appchamcong.NhanVien.TrangChu_NhanVien;
import com.example.appchamcong.QuanLy.QuanLyActivity;

import java.util.concurrent.Executor;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 123;
    TextView txt_Chuachotaikhoan, txt_Quenmatkhau;
    ImageButton ibtn_Dangnhapvantay;
    Button btn_Dangnhaptaikhoan;
    EditText txt_Taikhoandangnhap, txt_Matkhaudangnhap;
    TaiKhoanDAO taiKhoanDAO;
    String mTaikhoan = "";

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        AnhXa();
        VanTay();
    }

    private void VanTay() {
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(DangNhapActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Thông báo: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.e("Vân tay ", result + "" );

                startActivity(new Intent(DangNhapActivity.this, TrangChuActivity.class));
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công!",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Đăng nhập vào ứng dụng chấm công !")
                .setSubtitle("Đăng nhập bằng vân tay của bạn")
                .setNegativeButtonText("Sử dụng mật khẩu")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        ibtn_Dangnhapvantay.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }

    private void AnhXa() {
        txt_Chuachotaikhoan = findViewById(R.id.txt_Chuacotaikhoan);
        txt_Quenmatkhau = findViewById(R.id.txt_Quenmatkhau);
        btn_Dangnhaptaikhoan = findViewById(R.id.btn_Dangnhaptaikhoan);
        txt_Taikhoandangnhap = findViewById(R.id.txt_Taikhoandangnhap);
        txt_Matkhaudangnhap = findViewById(R.id.txt_Matkhaudangnhap);
        ibtn_Dangnhapvantay = findViewById(R.id.ibtn_Dangnhapvantay);

        taiKhoanDAO = new TaiKhoanDAO(DangNhapActivity.this);
        btn_Dangnhaptaikhoan.setOnClickListener(this);
        txt_Quenmatkhau.setOnClickListener(this);

        txt_Chuachotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_Dangnhaptaikhoan:
                setBtn_Login();
                break;
            case R.id.ibtn_Dangnhapvantay:
                setIbtn_Login();
                break;
        }
    }

    private void setIbtn_Login() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "Ứng dụng có thể xác thực bằng sinh trắc học.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "Không có tính năng sinh trắc học nào trên thiết bị này.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Các tính năng sinh trắc học hiện không khả dụng.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }
    }

    private void setBtn_Login() {
        String sTentaikhoan = txt_Taikhoandangnhap.getText().toString();
        String sMatkhau = txt_Matkhaudangnhap.getText().toString();
        TaiKhoan kiemtra = taiKhoanDAO.KiemTraDangNhap(sTentaikhoan, sMatkhau);
        mTaikhoan = sTentaikhoan;

        if (kiemtra != null){
            BatDauActivity.taiKhoanDTO = kiemtra;
            Toast.makeText(DangNhapActivity.this, "Đăng Nhập Thành Công ! ", Toast.LENGTH_SHORT).show();
            if (BatDauActivity.taiKhoanDTO.getQUYEN() == 0){
                startActivity(new Intent(DangNhapActivity.this, QuanLyActivity.class));
            } else if (BatDauActivity.taiKhoanDTO.getQUYEN() == 1) {
                startActivity(new Intent(DangNhapActivity.this, TrangChu_NhanVien.class));
            } else if (BatDauActivity.taiKhoanDTO.getQUYEN() == 2) {
                startActivity(new Intent(DangNhapActivity.this, TrangChuActivity.class));
            }
        } else {
            Toast.makeText(DangNhapActivity.this, "Đăng Nhập Thất Bại !", Toast.LENGTH_SHORT).show();
        }
    }
}