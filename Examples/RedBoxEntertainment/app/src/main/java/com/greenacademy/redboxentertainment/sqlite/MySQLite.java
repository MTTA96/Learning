package com.greenacademy.redboxentertainment.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.greenacademy.redboxentertainment.model.CD;

import java.util.ArrayList;

/**
 * Created by ADMIN on 4/29/2017.
 */

public class MySQLite extends SQLiteOpenHelper {
    // khai bao ten database
    public static String DB_NAME = "RedBox.db";
    // khai boa ten bang
    public static String CD_TABLE_NAME = "CD";

    // khai bao ten cot
    // bang Tai Khoan
    public static String TENCD = "TenCD";
    public static String TACGIA = "TacGia";
    public static String GIA = "Gia";
    public static String CODE = "Code";
    public static String SOLUONG = "SoLuong";
    public static String LOAICD = "LoaiCD";

    // Cau lenh SQLite  tao bang Tai Khoan
    private String CREATE_TABLE_CD = "CREATE TABLE " + CD_TABLE_NAME + " ( "
            + CODE +  " text primary key, "
            + TENCD + " text, "
            + TACGIA + " text, "
            + GIA + " integer, "
            + LOAICD + " integer, "
            + SOLUONG + " integer)";

    public MySQLite(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bien luu tai khoan
        ContentValues contentValues;
        // tao ban tai khoan
        db.execSQL(CREATE_TABLE_CD);
        // khoi tao gia tri ban dau
        contentValues = new ContentValues();
        // them du lieu vao bang Tai Khoan
        contentValues.put(CODE, "12345");
        contentValues.put(TENCD, "Anh Cứ Đi Đi");
        contentValues.put(TACGIA, "Hariwon");
        contentValues.put(GIA, 9000);
        contentValues.put(LOAICD, 1);
        contentValues.put(SOLUONG, 9999);
        // luu vao bang
        db.insert(CD_TABLE_NAME, null, contentValues);
    }

    // ham them tai khoan
    public void addCD (CD cd) {
        // khoi tao doi tuong SQLite
        SQLiteDatabase db = getWritableDatabase();
        // tao bien luu du lieu tam dang ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(CODE, cd.getCode());
        contentValues.put(TENCD, cd.getTenCD());
        contentValues.put(TACGIA, cd.getTacgia());
        contentValues.put(GIA, cd.getGia());
        contentValues.put(LOAICD, cd.getLoaiCD());
        contentValues.put(SOLUONG, cd.getSoluong());
        // luu du lieu vao database
        db.insert(CD_TABLE_NAME, null, contentValues);
        // dong ket noi database
        db.close();
    }

    // ham lay du lieu danh sach mon an
    public ArrayList<CD> getCD(int data){
        // khai bao bien tra ve
        ArrayList<CD> result = new ArrayList<>();
        // tao doi tuong SQLite
        SQLiteDatabase db = getReadableDatabase();
        // khai bao bien android de doc du lieu
        String sql = "select * from " + CD_TABLE_NAME  + " where " + LOAICD + " = " + data;
        //  nhan ket qua tra ve tung hang
        Cursor cursor = db.rawQuery(sql, null);
        // tao bien dang tai khoan
        CD cd;
        if (cursor.moveToFirst()){
            // thuc hien vong lap doc du lieu
            do {
                // khai bao gia tri ban dau cho taiKhoan
                cd = new CD();
                // thiet du lieu vao bien bien monAn
                cd.setTenCD(cursor.getString(cursor.getColumnIndex(TENCD)));
                cd.setTacgia(cursor.getString(cursor.getColumnIndex(TACGIA)));
                cd.setCode(cursor.getString(cursor.getColumnIndex(CODE)));
                cd.setGia(cursor.getString(cursor.getColumnIndex(GIA)));
                cd.setSoluong(cursor.getString(cursor.getColumnIndex(SOLUONG)));
                // them doi tuong
                result.add(cd);
            }while (cursor.moveToNext());
        }
        // dong ket noi
        cursor.close();
        db.close();
        // xuat du lieu
        return result;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
