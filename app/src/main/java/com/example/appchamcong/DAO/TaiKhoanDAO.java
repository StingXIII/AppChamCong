package com.example.appchamcong.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.appchamcong.DTO.TaiKhoan;
import com.example.appchamcong.Database.CreateDatabase;
import com.example.appchamcong.R;

import java.util.List;

public class TaiKhoanDAO extends BaseAdapter {
    SQLiteDatabase database;
    private Context context;
    private int layout;
    public static List<TaiKhoan> taiKhoanDTOList;
    int id;

    public TaiKhoanDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemTaiKhoan(TaiKhoan taiKhoanDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN,taiKhoanDTO.getTENTK());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_TENNGUOIDUNG,taiKhoanDTO.getTENNGUOIDUNG());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_MATKHAU,taiKhoanDTO.getMATKHAU());
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_QUYEN,1);
        contentValues.put(CreateDatabase.tbl_TAIKHOAN_TINHTRANG,0);

        long kiemtra = database.insert(CreateDatabase.tbl_TAIKHOAN, null, contentValues);
        return kiemtra;
    }

    public TaiKhoan VANTAY (){
        String truyvan = "SELECT * FROM TAIKHOAN WHERE QUYEN = 0";

        Cursor cursor = database.rawQuery(truyvan, null);
        while(cursor.moveToNext()){
            Log.e("Vân tay ", cursor.getString(1) + "" );
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

    public TaiKhoan KiemTraDangNhap(String tendangnhap, String matkhau){
        String truyvan = "SELECT * FROM " + CreateDatabase.tbl_TAIKHOAN + " WHERE " + CreateDatabase.tbl_TAIKHOAN_TENTAIKHOAN + " = '" + tendangnhap
                + "' AND " + CreateDatabase.tbl_TAIKHOAN_MATKHAU + " = '" + matkhau + "'";

        Cursor cursor = database.rawQuery(truyvan, null);
        while(cursor.moveToNext()){
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


    @Override
    public int getCount() {
        return taiKhoanDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        EditText edtTentaikhoan, edtMatkhau;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
//        if(view ==null )
//        {
//            holder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(layout,null);
//            holder.edtTentaikhoan = view.findViewById(R.id.edt_Taikhoan_dk);
//            holder.edtMatkhau = view.findViewById(R.id.edt_Matkhau_dk);
//            view.setTag(holder);
//
//        }
//        else
//        {
//            holder = (ViewHolder) view.getTag();
//        }
//        TaiKhoan taiKhoanDTO = taiKhoanDTOList.get(i);
//        holder.edtTentaikhoan.setText(taiKhoanDTO.getTENTK());
//        holder.edtMatkhau.setText(taiKhoanDTO.getMATKHAU());
//        holder.edtSdt.setText(taiKhoanDTO.getSDT());
//        holder.edtDiachi.setText(taiKhoanDTO.getDIACHI());
//        id = taiKhoanDTO.getMATK();
        return view;
    }
}