package com.example.appchamcong.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



public class DBHelper {

    String TAG = "DBAdapter";

    public static final String Tablename = "CHAMCONG";
    public static final String ID = "ID";// 0 integer
    public static final String MANHANVIEN = "MANHANVIEN";// 1 text(String)
    public static final String TENNHANVIEN = "TENNHANVIEN";// 2 text(String)
    public static final String PHONGBAN = "PHONGBAN";// 3 integer
    public static final String NGAYCONG = "NGAYCONG";//4 date(String)
    public static final String GIOVAO = "GIOVAO";//4 date(String)
    public static final String GIORA = "GIORA";//4 date(String)
    public static final String GIOCONG = "GIOCONG";// 5 date(String)

    private SQLiteDatabase db;
    private Database dbHelper;


    private static final int VERSION = 2;
    private static final String DB_NAME = "DatabaseAppChamCong";


    public DBHelper(Context context) {
        dbHelper = new Database(context);
    }


    public void open() {
        if (null == db || !db.isOpen()) {
            try {
                db = dbHelper.getWritableDatabase();
            } catch (SQLiteException sqLiteException) {
            }
        }
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public int insert(String table, ContentValues values) {
        try {
            db = dbHelper.getWritableDatabase();
            int y = (int) db.insert(table, null, values);
            db.close();
            Log.e("Data Inserted", "Data Inserted");
            Log.e("y", y + "");
            return y;
        } catch (Exception ex) {
            Log.e("Error Insert", ex.getMessage().toString());
            return 0;
        }
    }

    public void delete() {
        db.execSQL("delete from " + Tablename);
    }

    public Cursor getAllRow(String table) {
        return db.query(table, null, null, null, null, null, ID);
    }


    public ArrayList<HashMap<String, String>> getProducts() {
        ArrayList<HashMap<String, String>> prolist;
        prolist = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + Tablename;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(MANHANVIEN, cursor.getString(1));
                map.put(TENNHANVIEN, cursor.getString(2));
                map.put(PHONGBAN, cursor.getString(3));

                map.put(NGAYCONG, cursor.getString(4));
                map.put(GIOVAO, cursor.getString(5));
                map.put(GIORA, cursor.getString(6));
                map.put(GIOCONG, cursor.getString(7));
                prolist.add(map);
            } while (cursor.moveToNext());
        }
        return prolist;
    }


    private class Database extends SQLiteOpenHelper {
        private static final int VERSION = 2;
        private static final String DB_NAME = "DatabaseAppChamCong";

        public Database(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create_sql = "CREATE TABLE IF NOT EXISTS " + Tablename + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MANHANVIEN + " TEXT , "+ TENNHANVIEN + " TEXT ," + PHONGBAN + " TEXT ,"
                    + NGAYCONG + " TEXT ," + GIOVAO + " TEXT " + "," + GIORA + " TEXT " + ","
                    + GIOCONG + " INTEGER " + ")";
            db.execSQL(create_sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Tablename);
        }

    }
}
