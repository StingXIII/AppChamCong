package com.example.appchamcong.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {

    public static String tbl_TAIKHOAN = "TAIKHOAN";
    public static String tbl_GOPY = "GOPY";

    public static String tbl_TAIKHOAN_IDTK = "IDTAIKHOAN";
    public static String tbl_TAIKHOAN_TENTAIKHOAN = "TENTAIKHOAN";
    public static String tbl_TAIKHOAN_TENNGUOIDUNG = "TENNGUOIDUNG";
    public static String tbl_TAIKHOAN_MATKHAU = "MATKHAU";
    public static String tbl_TAIKHOAN_HINHANH = "HINHANH";
    public static String tbl_TAIKHOAN_SDT = "SDT";
    public static String tbl_TAIKHOAN_DIACHI = "DIACHI";
    public static String tbl_TAIKHOAN_QUYEN = "QUYEN";
    public static String tbl_TAIKHOAN_CHUCVU = "CHUCVU";
    public static String tbl_TAIKHOAN_BOPHAN = "BOPHAN";
    public static String tbl_TAIKHOAN_PHONGBAN = "PHONGBAN";
    public static String tbl_TAIKHOAN_TINHTRANG = "TINHTRANG";

    public static String tbl_GOPY_IDGOPY = "IDGOPY";
    public static String tbl_GOPY_TENNHANVIEN = "TENNHANVIEN";
    public static String tbl_GOPY_SDT = "SDT";
    public static String tbl_GOPY_NOIDUNG = "NOIDUNG";



    public CreateDatabase(@Nullable Context context) {
        super(context, "DatabaseAppChamCong", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TAIKHOAN = "CREATE TABLE " + tbl_TAIKHOAN + " (" + tbl_TAIKHOAN_IDTK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbl_TAIKHOAN_TENTAIKHOAN + " TEXT ," + tbl_TAIKHOAN_TENNGUOIDUNG + " TEXT , " + tbl_TAIKHOAN_MATKHAU + " TEXT, "
                + tbl_TAIKHOAN_HINHANH + " BLOB, " + tbl_TAIKHOAN_SDT + " INTEGER, " + tbl_TAIKHOAN_DIACHI + " TEXT, "
                + tbl_TAIKHOAN_QUYEN + " INTEGER, " + tbl_TAIKHOAN_CHUCVU + " INTEGER," + tbl_TAIKHOAN_BOPHAN + " INTEGER, "
                + tbl_TAIKHOAN_PHONGBAN + " INTEGER, " + tbl_TAIKHOAN_TINHTRANG + " INTEGER )";

        String GOPY = "CREATE TABLE " + tbl_GOPY + "(" + tbl_GOPY_IDGOPY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbl_GOPY_TENNHANVIEN + " TEXT, " + tbl_GOPY_SDT + " INTEGER, " + tbl_GOPY_NOIDUNG + " TEXT )";

        db.execSQL(TAIKHOAN);
        db.execSQL(GOPY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open (){
        return this.getWritableDatabase();
    }
}
