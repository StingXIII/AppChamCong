package com.example.appchamcong.QuanLy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appchamcong.BatDauActivity;
import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.DangNhapActivity;
import com.example.appchamcong.QuanLy.Fragment_QuanLy.QL_ChamCong;
import com.example.appchamcong.QuanLy.Fragment_QuanLy.QL_NhanSu;
import com.example.appchamcong.QuanLy.Fragment_QuanLy.QL_TaiKhoan;
import com.example.appchamcong.QuanLy.Fragment_QuanLy.QL_ThongKe;
import com.example.appchamcong.R;
import com.google.android.material.navigation.NavigationView;

public class QuanLyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;
    private static final int FRAGMENT_QL_NHANSU = 1;
    private static final int FRAGMENT_QL_CONG = 2;
    private static final int FRAGMENT_QL_THONGKE = 3;
    private int currentFragment = FRAGMENT_QL_NHANSU;

    // Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LinearLayout contentView;
    private Toolbar toolbar;
    // Drawer

    TextView txt_TenTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);

        AnhXa();

        HienThiTen();
    }

    @Override
    protected void onStart() {
        Menu menu = navigationView.getMenu();
        if(BatDauActivity.taiKhoanDTO.getMATK() == -1){
            menu.findItem(R.id.nav_logout_manager).setVisible(false);
        }else {
            menu.findItem(R.id.nav_logging_manager).setVisible(false);
        }
        super.onStart();
    }

    private void AnhXa() {
        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout_manager);
        navigationView = findViewById(R.id.home_nav_view_manager);
        contentView = findViewById(R.id.content_View_manager);
        toolbar = findViewById(R.id.toolbar_manager);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_quanly_nhansu);
        // Drawer

        replaceFragment(new QL_NhanSu());

        animateNavigation();
    }

    private void animateNavigation() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.mauchude));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaleOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaleOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleX(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaleOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }
        });
    }

    private void HienThiTen() {
        View view = navigationView.inflateHeaderView(R.layout.header_manager);
        txt_TenTaiKhoan = view.findViewById(R.id.txtTennguoidung);

        txt_TenTaiKhoan.setText(BatDauActivity.taiKhoanDTO.getTENTK());
        txt_TenTaiKhoan.setTextColor(Color.BLACK);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logging_manager) {
            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Remember", "Failed");
            editor.putString("token", null);
            editor.apply();

            startActivity(new Intent(this, DangNhapActivity.class));
            finish();
        }else if (id == R.id.nav_logout_manager){
            Intent intent = new Intent(this, DangNhapActivity.class);
            BatDauActivity.taiKhoanDTO = new TaiKhoan();
            startActivity(intent);
        }else if (id == R.id.nav_quanly_chamcong) {
            if (FRAGMENT_QL_CONG != currentFragment) {
                replaceFragment(new QL_ChamCong());
                currentFragment = FRAGMENT_QL_CONG;
            }
        }else if (id == R.id.nav_quanly_nhansu) {
            if (FRAGMENT_QL_NHANSU != currentFragment) {
                replaceFragment(new QL_NhanSu());
                currentFragment = FRAGMENT_QL_NHANSU;
            }
        }else if (id == R.id.nav_quanly_thongke) {
            if (FRAGMENT_QL_THONGKE != currentFragment) {
                replaceFragment(new QL_ThongKe());
                currentFragment = FRAGMENT_QL_THONGKE;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_manager, fragment);
        fragmentTransaction.commit();
    }
}