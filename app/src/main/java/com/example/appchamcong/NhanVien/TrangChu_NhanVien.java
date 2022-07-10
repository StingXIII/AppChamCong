package com.example.appchamcong.NhanVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.appchamcong.Database.Database;
import com.example.appchamcong.NhanSu.Fragment.NguoiDung;
import com.example.appchamcong.NhanSu.Fragment.ThongBao;
import com.example.appchamcong.NhanSu.Fragment.TrangChu;
import com.example.appchamcong.NhanVien.Fragment_NhanVien.ThongBao_NV;
import com.example.appchamcong.NhanVien.Fragment_NhanVien.ThongTin_NV;
import com.example.appchamcong.NhanVien.Fragment_NhanVien.TrangChu_NV;
import com.example.appchamcong.R;

public class TrangChu_NhanVien extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_nv);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_account_circle_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new ThongBao_NV();
                        break;

                    case 2:
                        fragment = new TrangChu_NV();
                        break;

                    case 3:
                        fragment = new ThongTin_NV();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.setCount(1, "2");
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}