package com.example.manthilan_qly_vattu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class manlan_VTdatabase extends SQLiteOpenHelper {
    //1. khai báo CSDL
    private static final String DATABASE_NAME="qlvt";
    // khai báo tên bảng
    private static final String TABLE_NAME="maytinh";
    // khai báo tên trường
    private static final String MA="mamt";
    private static final String TEN="tenmt";
    private static final String LOAI="loaimt";
    private static final String NAM="namsx";
    private static final String HANG="hangsx";
    private static final String DONGGIA="dongia";
    private static final String SOLUONG="soluong";

    // khai version
    private static int VERSION = 1;
    private Context context;
    // constructor
    public manlan_VTdatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1="CREATE TABLE "+TABLE_NAME+" ( "+ MA+" Text primary key, "+ TEN+" Text, "+ LOAI+ " Text, "+ NAM+" Int, "+ HANG+" Text, "+ DONGGIA+" Int , "+ SOLUONG+" Int)";
        // thực thi tạo bảng
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  "+TABLE_NAME);
        onCreate(db);
    }
    //3. Thêm mới sinh viên vào CSDL
    public void AddVT(manlan_VatTu vt){
        // gọi CSDL
        SQLiteDatabase db=this.getWritableDatabase();
        // tạo đối tượng values chứa các nội dung của sv
        ContentValues values=new ContentValues();
        values.put(MA,vt.getMamt());
        values.put(TEN,vt.getTenmt());
        values.put(LOAI,vt.getLoaimt());
        values.put(NAM,vt.getNamsx());
        values.put(HANG,vt.getHangsx());
        values.put(DONGGIA,vt.getDongia());
        values.put(SOLUONG,vt.getSoluong());
        // chèn values vào CSDL
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //4. cập nhật CSDL
    public  int UpdateVT(manlan_VatTu vt){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MA,vt.getMamt());
        values.put(TEN,vt.getTenmt());
        values.put(LOAI,vt.getLoaimt());
        values.put(NAM,vt.getNamsx());
        values.put(HANG,vt.getHangsx());
        values.put(DONGGIA,vt.getDongia());
        values.put(SOLUONG,vt.getSoluong());
        int a=db.update(TABLE_NAME,values,MA+"=?",new String[]{vt.getMamt()});
        db.close();
        return a;
    }
    //5. xóa sv thông qua ID
    public int DeleteVT(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        int b=db.delete(TABLE_NAME,MA+"=?",new String[]{id});
        db.close();
        return b;
    }
    //6. lấy toàn bộ thông tin sv trong CSDL
    public List<manlan_VatTu> InformationVT(){
        List<manlan_VatTu> listHP=new ArrayList<manlan_VatTu>();
        SQLiteDatabase db=this.getWritableDatabase();
        String sql3="SELECT * FROM "+TABLE_NAME;
        // thực thi
        Cursor cursor=db.rawQuery(sql3,null);
        if(cursor.moveToFirst()){
            do {
                manlan_VatTu vt=new manlan_VatTu();
                vt.setMamt(cursor.getString(0));
                vt.setTenmt(cursor.getString(1));
                vt.setLoaimt(cursor.getString(2));
                vt.setNamsx(Integer.parseInt(cursor.getString(3)));
                vt.setHangsx(cursor.getString(4));
                vt.setDongia(Integer.parseInt(cursor.getString(5)));
                vt.setSoluong(Integer.parseInt(cursor.getString(6)));
                listHP.add(vt);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listHP;
    }
    public manlan_VatTu timkiemVT(String tim){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{MA,TEN,LOAI,NAM,HANG,DONGGIA,SOLUONG}, MA+"=?",new String[]{String.valueOf(tim)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                manlan_VatTu vt=new manlan_VatTu();
                vt.setMamt(cursor.getString(0));
                vt.setTenmt(cursor.getString(1));
                vt.setLoaimt(cursor.getString(2));
                vt.setNamsx(Integer.parseInt(cursor.getString(3)));
                vt.setHangsx(cursor.getString(4));
                vt.setDongia(Integer.parseInt(cursor.getString(5)));
                vt.setSoluong(Integer.parseInt(cursor.getString(6)));
                cursor.close();
                db.close();
                return vt;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
