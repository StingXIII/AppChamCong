package com.example.appchamcong.helpers;

import android.content.ContentValues;
import android.util.Log;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public class ExcelHelper {

    public static final String Tablename = "CHAMCONG";
    public static final String ID = "ID";// 0 integer
    public static final String MANHANVIEN = "MANHANVIEN";// 1 text(String)
    public static final String TENNHANVIEN = "TENNHANVIEN";// 2 text(String)
    public static final String PHONGBAN = "PHONGBAN";// 3 integer
    public static final String NGAYCONG = "NGAYCONG";//4 date(String)
    public static final String GIOVAO = "GIOVAO";//4 date(String)
    public static final String GIORA = "GIORA";//4 date(String)
    public static final String GIOCONG = "GIOCONG";// 5 date(String)

    public static void insertExcelToSqlite(DBHelper dbAdapter, Sheet sheet) {

        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row = rit.next();

            ContentValues contentValues = new ContentValues();
            row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);

            contentValues.put(MANHANVIEN, row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(TENNHANVIEN, row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(PHONGBAN, row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(NGAYCONG, row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(GIOVAO, row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(GIORA, row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            contentValues.put(GIOCONG, row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());

            try {
                if (dbAdapter.insert("CHAMCONG", contentValues) < 0) {
                    return;
                }
            } catch (Exception ex) {
                Log.d("Exception in importing", ex.getMessage().toString());
            }
        }
    }
}