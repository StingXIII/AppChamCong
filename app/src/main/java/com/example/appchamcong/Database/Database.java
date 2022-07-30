package com.example.appchamcong.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.appchamcong.DTO.GopY;
import com.example.appchamcong.DTO.TaiKhoan;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(Context context) {
        super(context,"DatabaseAppChamCong",null,2);
    }

    // Truy vấn không trả kết quả: INSERT, CREATE, UPDATE, DELETE, ...
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Truy vấn có trả kết quả: SELECT
    public Cursor Getdata(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public boolean isTonTaiTaiKhoan(String TENTAIKHOAN){
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN = '" + TENTAIKHOAN + "'");
        while (cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public TaiKhoan Load(int IDTK)
    {
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE IDTAIKHOAN = " + IDTK );
        while (cursor.moveToNext()) {
            return new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            );
        }
        return null;
    }

    public void CapNhatTaiKhoan(int IDTAIKHOAN, byte[] HINHANH, int SDT, String TENNGUOIDUNG, String DIACHI){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_SDT + " = '" + SDT + "', " + CreateDatabase.tbl_TAIKHOAN_TENNGUOIDUNG + " = '" + TENNGUOIDUNG +
                "' , " + CreateDatabase.tbl_TAIKHOAN_DIACHI + " = '" + DIACHI + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");

        String sql = "UPDATE TAIKHOAN SET HINHANH = ? WHERE IDTAIKHOAN= " + IDTAIKHOAN ;
        SQLiteDatabase database = getWritableDatabase();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,HINHANH);
        statement.executeInsert();
    }

    public boolean isMatKhau(int IDTAIKHOAN, String MATKHAU){
        Cursor cursor = Getdata("SELECT * FROM " + CreateDatabase.tbl_TAIKHOAN + " WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = " + IDTAIKHOAN + " AND " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + MATKHAU + "'");
        while (cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public void CapNhatMatKhau(int IDTAIKHOAN, String MATKHAU){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + MATKHAU + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = " + IDTAIKHOAN);
    }
    public void CAPNHATNGAYCONG(int ID, String NGAYCONG){
        QueryData("UPDATE CHAMCONG SET NGAYCONG = '" + NGAYCONG + "' WHERE ID = " + ID);
    }

    public ArrayList<TaiKhoan> QuanLyTaiKhoan(int QUYEN){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE QUYEN >=" + QUYEN);
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            ));
        }
        return list;
    }

    //region Video
    public ArrayList<TaiKhoan> QuanLyTaiKhoan_TimKiem(String Tennhanvien){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE TENNGUOIDUNG LIKE '%" + Tennhanvien +"%'");
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            ));
        }
        return list;
    }

    public ArrayList<TaiKhoan> QuanLyNhanSu(int PHONGBAN){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE PHONGBAN =" + PHONGBAN);
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            ));
        }
        return list;
    }

    //region Video
    public ArrayList<TaiKhoan> QuanLyNhanSu_TimKiem(String Tennhanvien, int PHONGBAN){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE PHONGBAN = " + PHONGBAN + " AND TENNGUOIDUNG LIKE '%" + Tennhanvien +"%'");
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            ));
        }
        return list;
    }

    public void NhanSuNghiViec(int IDTAIKHOAN){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_TINHTRANG + " = 0 WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = " + IDTAIKHOAN);
    }

    public void CapNhatNhanSu(int IDTAIKHOAN, int CHUCVU, int PHONGBAN, int TINHTRANG){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_CHUCVU + " = '" + CHUCVU + "', " + CreateDatabase.tbl_TAIKHOAN_PHONGBAN+ " = '" + PHONGBAN +
                "' , " + CreateDatabase.tbl_TAIKHOAN_TINHTRANG + " = '" + TINHTRANG + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public void CapNhatTaiKhoan_QL(int IDTAIKHOAN, String TENTAIKHOAN, String MATKHAU, String TENNGUOIDUNG){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN + " = '" + TENTAIKHOAN + "', " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + MATKHAU +
                "' , " + CreateDatabase.tbl_TAIKHOAN_TENNGUOIDUNG + " = '" + TENNGUOIDUNG + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public void CapNhatNhanSu_QL(int IDTAIKHOAN, String TENND, int SDT, String DIACHI, int QUYEN, int CHUCVU, int PHONGBAN, int TINHTRANG){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_TENNGUOIDUNG + " = '" + TENND + "'," + CreateDatabase.tbl_TAIKHOAN_SDT + " = '" + SDT + "',"
                + CreateDatabase.tbl_TAIKHOAN_DIACHI + " = '" + DIACHI + "', " + CreateDatabase.tbl_TAIKHOAN_QUYEN + " = '" + QUYEN + "'," + CreateDatabase.tbl_TAIKHOAN_CHUCVU + " = '" + CHUCVU + "', "
                + CreateDatabase.tbl_TAIKHOAN_PHONGBAN + " = '" + PHONGBAN + "' , " + CreateDatabase.tbl_TAIKHOAN_TINHTRANG + " = '" + TINHTRANG + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public void XoaTK(int IDTAIKHOAN){
        QueryData("DELETE FROM TAIKHOAN WHERE IDTAIKHOAN = '" + IDTAIKHOAN + "'");
    }

    public void INSERT_GOPY(String tennhanvien, int sdt, String noidung){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO GOPY VALUES(null,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tennhanvien);
        statement.bindDouble(2, sdt);
        statement.bindString(3, noidung);

        statement.executeInsert();
    }

    public ArrayList<GopY> QuanLyGopY(){
        ArrayList<GopY> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM GOPY");
        while (cursor.moveToNext()){
            list.add(new GopY(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            ));
        }
        return list;
    }
    public void INSRERTSOCAU(int IDTK,int VONG, int SOCAU){

        QueryData("INSERT INTO VONGCHINH ( IDTK, VONG, SOCAU ) VALUES ( " + IDTK + " , " + VONG + " , "
                    + SOCAU + ")");

    }

    public void XoaGY(int IDGY){
        QueryData("DELETE FROM GOPY WHERE IDGOPY = '" + IDGY + "'");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
