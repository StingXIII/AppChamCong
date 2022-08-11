package com.example.appchamcong.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appchamcong.DTO.ChamCong;
import com.example.appchamcong.DTO.GopY;
import com.example.appchamcong.DTO.Nangluc;
import com.example.appchamcong.DTO.TaiKhoan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
//    public ArrayList<ChamCong> LOADCHAMCONGFILE(){
//        ArrayList<ChamCong> list = new ArrayList<>();
//        Cursor cursor = Getdata("SELECT * FROM CHAMCONG ");
//        while (cursor.moveToNext()){
//            list.add(new ChamCong(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getString(5),
//                    cursor.getString(6),
//                    cursor.getInt(7)
//            ));
////            Log.e("THANGNAM",cursor.getString(1) + " "
////                    + " " + cursor.getString(2)
////                    + " " + cursor.getString(3)
////                    + " " +  cursor.getString(4)
////                    + " " + cursor.getString(5)
////                    + " " + cursor.getString(6)
////                    + " " + cursor.getString(7)
////                    + " " + cursor.getInt(8) );
//        }
//        return list;
//    }
    public ArrayList<ChamCong> LOADCHAMCONG(String thangcong){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL WHERE THANGCONG = '"+ thangcong + "'");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            ));
//            Log.e("THANGNAM",cursor.getString(1) + " "
//                    + " " + cursor.getString(2)
//                    + " " + cursor.getString(3)
//                    + " " +  cursor.getString(4)
//                    + " " + cursor.getString(5)
//                    + " " + cursor.getString(6)
//                    + " " + cursor.getString(7)
//                    + " " + cursor.getInt(8) );
        }
        return list;
    }
    public void DELETE_CHAMCONG(){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM CHAMCONG" ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
    }
    public void DELETE_CHAMCONGREAL(String thangcong){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE  FROM CHAMCONGREAL WHERE THANGCONG = '" + thangcong + "'" ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();


        statement.executeInsert();
    }
    public void INSERT_CHAMCONG(String MANV
            ,String TENNV
            , String PHONGBAN
            , String NGAYCONG
            , String GIOVAO
            , String GIORA
            , int GIOCONG){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO CHAMCONGREAL VALUES(null,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        //----------------------chuyen ngay-------------------
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdm = new SimpleDateFormat("MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse("1899-12-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DATE, Integer.parseInt(NGAYCONG));
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultdate = new Date(c.getTimeInMillis());
        String ngaycong = sdf.format(resultdate);
        String thangcong = sdm.format(resultdate);
        //----------------------chuyen ngay-------------------

        statement.bindString(1, MANV);
        statement.bindString(2, TENNV);
        statement.bindString(3, PHONGBAN);
        statement.bindString(4, ngaycong);
        statement.bindString(5, thangcong);
        statement.bindString(6, GIOVAO);
        statement.bindString(7, GIORA);
        statement.bindDouble(8, GIOCONG);

        statement.executeInsert();
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
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12),
                    cursor.getInt(13)
            );
        }
        return null;
    }

    public ChamCong Load_ChamCong(int IDCHAMCONG)
    {
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL WHERE ID = " + IDCHAMCONG );
        while (cursor.moveToNext()) {
            return new ChamCong(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            );
        }
        return null;
    }
    public Nangluc Load_Thongtin(String MANHANVIEN)
    {
        Cursor cursor = Getdata("SELECT * FROM NANGLUC WHERE MANHANVIEN = '" + MANHANVIEN + "'" );
        while (cursor.moveToNext()) {
            Log.e("ABCD",cursor.getString(0) + " , "
                    + cursor.getString(1) + " , "
                    + cursor.getString(2) + " , "
                    + cursor.getString(3) + " , "
                    + cursor.getString(4));

            return new Nangluc(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );
        }
        return null;
    }

    public void CapNhatTaiKhoan(int IDTAIKHOAN, byte[] HINHANH, int SDT, String TENNGUOIDUNG, String DIACHI, String NGAYSINH, String EMAIL){
        QueryData("UPDATE TAIKHOAN SET SDT = '" + SDT + "', TENNGUOIDUNG = '" + TENNGUOIDUNG + "' , DIACHI = '" + DIACHI + "', " +
                "NGAYSINH = '" + NGAYSINH + "', EMAIL = '" + EMAIL + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");

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
        QueryData("UPDATE CHAMCONGREAL SET NGAYCONG = '" + NGAYCONG + "' WHERE ID = " + ID);
    }
    public void CAPNHATDIEMQL(String MANHANVIEN, int DIEMQL, int XEPLOAI){
        QueryData("UPDATE NANGLUC SET DIEMQL = " + DIEMQL + " , XEPLOAI = "
                + XEPLOAI + " WHERE MANHANVIEN = '"+ MANHANVIEN +"'"  );
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
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12),
                    cursor.getInt(13)
            ));
        }
        return list;
    }

    public ArrayList<TaiKhoan> ChoDuyet(int TINHTRANG){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE TINHTRANG =" + TINHTRANG);
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12),
                    cursor.getInt(13)
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
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12),
                    cursor.getInt(13)
            ));
        }
        return list;
    }

    public ArrayList<TaiKhoan> QuanLyNhanSu(int BOPHAN, int PHONGBAN){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE BOPHAN = " + BOPHAN + " AND PHONGBAN =" + PHONGBAN);
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12),
                    cursor.getInt(13)
            ));
        }
        return list;
    }

    public ArrayList<Nangluc> XepLoaiNangLuc(){
        ArrayList<Nangluc> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM NANGLUC");
        while (cursor.moveToNext()){
            list.add(new Nangluc(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            ));
        }
        return list;
    }

    //region Video
    public ArrayList<TaiKhoan> QuanLyNhanSu_TimKiem(String Tennhanvien, int BOPHAN, int PHONGBAN){
        ArrayList<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM TAIKHOAN WHERE BOPHAN = " + BOPHAN + " AND PHONGBAN = " + PHONGBAN + " AND TENNGUOIDUNG LIKE '%" + Tennhanvien +"%'");
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    cursor.getInt(11),
                    cursor.getInt(12),
                    cursor.getInt(13)
            ));
        }
        return list;
    }

    public ArrayList<ChamCong> QuanLyChamCong(){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            ));
        }
        return list;
    }
    public boolean KIEMTRACHAMCONG(String thangcong){
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL WHERE THANGCONG = '" + thangcong+ "'" );
        while (cursor.moveToNext()){
            return true;
        }
        return false;
    }
    public boolean KIEMTRANANGLUC(String MANHANVIEN){
        Cursor cursor = Getdata("SELECT * FROM NANGLUC WHERE MANHANVIEN = '" + MANHANVIEN + "'" );
        while (cursor.moveToNext()){
            return false;
        }
        return true;
    }
    public void INSERT_NANGLUC(String MANHANVIEN, String TENNHANVIEN,int KQ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO NANGLUC VALUES(?,?,?,null,null)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, MANHANVIEN);
        statement.bindString(2, TENNHANVIEN);
        statement.bindDouble(3, KQ);

        statement.executeInsert();
    }
    public ArrayList<ChamCong> XemCong(String MANV){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL WHERE MANHANVIEN = '" + MANV + "'");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            ));
        }
        return list;
    }

    public ArrayList<ChamCong> XemCong_TimKiem(String MANHANVIEN, String THANG){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL WHERE (MANHANVIEN LIKE '%" + MANHANVIEN +"%') AND (THANGCONG LIKE '%" + THANG +"%')");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            ));
        }
        return list;
    }

    //region Video
    public ArrayList<ChamCong> QuanLyChamCong_TimKiem(String Tennhanvien, String Ngay, String Thang){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM CHAMCONGREAL WHERE (TENNHANVIEN LIKE '%" + Tennhanvien +"%') OR (NGAYCONG LIKE '%" + Ngay +"%') OR (THANGCONG LIKE '%" + Thang +"%')");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8)
            ));
        }
        return list;
    }

    public ArrayList<ChamCong> ThongKeNhanSu(String BOPHAN){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT TENNHANVIEN,THANGCONG,SUM(GIOCONG) FROM CHAMCONGREAL WHERE PHONGBAN = '" + BOPHAN + "' GROUP BY TENNHANVIEN, THANGCONG");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2)
            ));
        }
        return list;
    }

    public ArrayList<ChamCong> ThongKeNhanSu_TimKiem(String BOPHAN, String THANG){
        ArrayList<ChamCong> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT TENNHANVIEN,THANGCONG,SUM(GIOCONG) FROM CHAMCONGREAL WHERE PHONGBAN = '" + BOPHAN + "' AND (THANGCONG LIKE '%" + THANG +"%') GROUP BY TENNHANVIEN ORDER BY SUM(GIOCONG) DESC");
        while (cursor.moveToNext()){
            list.add(new ChamCong(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2)
            ));
        }
        return list;
    }

    public void XoaCong(int ID){
        QueryData("DELETE FROM CHAMCONGREAL WHERE ID = '" + ID + "'");
    }

    public void NhanSuNghiViec(int IDTAIKHOAN){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_TINHTRANG + " = 2 WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = " + IDTAIKHOAN);
    }

    public void CapNhatNhanSu(int IDTAIKHOAN, int CHUCVU, int PHONGBAN, int TINHTRANG){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_CHUCVU + " = '" + CHUCVU + "', " + CreateDatabase.tbl_TAIKHOAN_PHONGBAN+ " = '" + PHONGBAN +
                "' , " + CreateDatabase.tbl_TAIKHOAN_TINHTRANG + " = '" + TINHTRANG + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public void CapNhatTaiKhoan_QL(int IDTAIKHOAN, String TENTAIKHOAN, String MATKHAU, String TENNGUOIDUNG){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN + " = '" + TENTAIKHOAN + "', " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + MATKHAU +
                "' , " + CreateDatabase.tbl_TAIKHOAN_TENNGUOIDUNG + " = '" + TENNGUOIDUNG + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public void CapNhatNhanSu_QL(int IDTAIKHOAN, String MANV, String TENND, int SDT, String DIACHI, int QUYEN, int CHUCVU, int BOPHAN, int PHONGBAN, int TINHTRANG, String NGAYSINH, String EMAIL){
        QueryData("UPDATE " + CreateDatabase.tbl_TAIKHOAN + " SET " + CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN + " = '" + MANV + "'," + CreateDatabase.tbl_TAIKHOAN_TENNGUOIDUNG + " = '" + TENND + "'," + CreateDatabase.tbl_TAIKHOAN_SDT + " = '" + SDT + "',"
                + CreateDatabase.tbl_TAIKHOAN_DIACHI + " = '" + DIACHI + "', " + CreateDatabase.tbl_TAIKHOAN_QUYEN + " = '" + QUYEN + "'," + CreateDatabase.tbl_TAIKHOAN_CHUCVU + " = '" + CHUCVU + "', "+ CreateDatabase.tbl_TAIKHOAN_BOPHAN + " = '" + BOPHAN + "', "
                + CreateDatabase.tbl_TAIKHOAN_PHONGBAN + " = '" + PHONGBAN + "' , " + CreateDatabase.tbl_TAIKHOAN_TINHTRANG + " = '" + TINHTRANG + "', NGAYSINH = '" + NGAYSINH + "', EMAIL = '" + EMAIL + "' WHERE " + CreateDatabase.tbl_TAIKHOAN_IDTK + " = '" + IDTAIKHOAN +"'");
    }

    public void CapNhatCong_QL(int ID, String MANV, String TENNV, String PHONGBAN, String NGAYCONG, String GIOVAO, String GIORA, int GIOCONG){
        QueryData("UPDATE CHAMCONGREAL SET MANHANVIEN = '" + MANV + "', TENNHANVIEN = '" + TENNV + "', PHONGBAN = '" + PHONGBAN + "', NGAYCONG = '" + NGAYCONG +
                "' , GIOVAO = '" + GIOVAO + "', GIORA = '" + GIORA + "', GIOCONG = '" + GIOCONG + "' WHERE ID = '" + ID +"'");
    }

    public void CapNhatTaiKhoan_XetDuyet(int IDTAIKHOAN, String MANV, String TENNV, int BOPHAN, int PHONGBAN, int CHUCVU, int TINHTRANG){
        QueryData("UPDATE TAIKHOAN SET TENTAIKHOAN = '" + MANV + "', TENNGUOIDUNG = '" + TENNV + "' , BOPHAN = " + BOPHAN + ", PHONGBAN = "
                + PHONGBAN + ", CHUCVU = " + CHUCVU + ", TINHTRANG = " + TINHTRANG + " WHERE IDTAIKHOAN = " + IDTAIKHOAN);
    }

    public void XoaTK(int IDTAIKHOAN){
        QueryData("DELETE FROM TAIKHOAN WHERE IDTAIKHOAN = '" + IDTAIKHOAN + "'");
    }

    public void INSERT_GOPY(String tennhanvien, byte[] HINHANH, int sdt, String noidung){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO GOPY VALUES(null,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tennhanvien);
        statement.bindBlob(2, HINHANH);
        statement.bindDouble(3, sdt);
        statement.bindString(4, noidung);

        statement.executeInsert();
    }

    public ArrayList<GopY> QuanLyGopY(){
        ArrayList<GopY> list = new ArrayList<>();
        Cursor cursor = Getdata("SELECT * FROM GOPY");
        while (cursor.moveToNext()){
            list.add(new GopY(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getBlob(2),
                    cursor.getInt(3),
                    cursor.getString(4)
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
