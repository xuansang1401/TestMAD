package com.ptit.testmad.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ptit.testmad.model.DangKi;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MADSQL";
    // bảng sinhh vien
    private static final String TABLE_NAME = "SinhVien";
    private static final String IDSV = "id";
    private static final String NAME = "name";
    private static final String NAM = "nam";
    private static final String QUEQUAN = "quequan";
    private static final String NAMHOC = "namhoc";

    // bang lop
    private static final String TABLE_LOP = "LOP";
    private static final String IDLOP = "idLOP";
    private static final String TENLOP = "tenlop";
    private static final String MOTALOP = "mota";
    // bảng dang ki
    private static final String TABLE_DKI = "DANGKY";
    private static final String IDDK = "idDK";
    //    private static final String IDSVDK ="idSV";
//    private static final String IDLOPDK ="idLop";
    private static final String KIHOC = "kihoc";
    private static final String STC = "stc";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DB Manager", "DB Manager");

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQuery1 = "CREATE TABLE " + TABLE_NAME + " (" +
                IDSV + " integer primary key AUTOINCREMENT, " +
                NAME + " TEXT, " +
                NAM + " integer, " +
                QUEQUAN + " TEXT, " +
                NAMHOC + " integer)";
        db.execSQL(sqlQuery1);
        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();
        String sqlQuery2 = "CREATE TABLE " + TABLE_LOP + " (" +
                IDLOP + " integer primary key AUTOINCREMENT, " +
                TENLOP + " TEXT, " +
                MOTALOP + " TEXT)";

        db.execSQL(sqlQuery2);

        String sqlQuery3 = "CREATE TABLE " + TABLE_DKI + " (" +
                IDDK + " integer primary key AUTOINCREMENT, " +
                IDSV + " integer, " +
                IDLOP + " integer, " +
                KIHOC + " integer, " +
                STC + " integer)";
        db.execSQL(sqlQuery3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOP);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DKI);
        onCreate(db);
        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();

    }

    private void execSQLite(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Log.e("Sang","query: "+ sql);
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("SQLITE", e.toString());
        }
        db.close();
    }

    private List<SinhVien> getListSV(String sql) {
        List<SinhVien> listStudent = new ArrayList<SinhVien>();
        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                SinhVien student = new SinhVien();
                student.setId(cursor.getInt(0));
                student.setTen(cursor.getString(1));
                student.setNam(cursor.getInt(2));
                student.setQuequan(cursor.getString(3));
                student.setNamhoc(cursor.getInt(4));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }

    private List<Lop> getListLop(String selectQuery) {
        List<Lop> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Lop lop = new Lop();
                lop.setIdLop(cursor.getInt(0));
                lop.setTenLop(cursor.getString(1));
                lop.setMoTa(cursor.getString(2));

                list.add(lop);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addSinhVien(SinhVien sinhVien) {
        String query = "INSERT INTO SinhVien (name,nam,quequan,namhoc)\n" +
                "VALUES ('" + sinhVien.getTen() + "' , '" + sinhVien.getNam() +
                "' , '" + sinhVien.getQuequan() + "' , '" + sinhVien.getNamhoc() + "');";
        execSQLite(query);

    }

    public void deleteSV(int id) {
        String query = "DELETE FROM SinhVien WHERE id = " + id + ";";
        execSQLite(query);
    }

    public List<SinhVien> getAllStudent() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        List<SinhVien> listStudent = getListSV(selectQuery);
        return listStudent;
    }

    public List<SinhVien> getAllStudentByName() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE name ='Nam'";
        List<SinhVien> listStudent = getListSV(selectQuery);
        return listStudent;
    }

//    private static final String TABLE_NAME = "SinhVien";
//    private static final String IDSV = "id";
//    private static final String NAME = "name";
//    private static final String NAM = "nam";
//    private static final String QUEQUAN = "quequan";
//    private static final String NAMHOC = "namhoc";
    public List<SinhVien> getSVByLop(int idLop) {
        String selectQuery = "SELECT DANGKY.id, SinhVien.name, SinhVien.nam, SinhVien.quequan, SinhVien.namhoc from DANGKY " +
                "INNER JOIN SinhVien ON DANGKY.id = SinhVien.id " +
                "WHERE idLOP = " + idLop + ";";
//        String selectQuery = "SELECT  * FROM " + TABLE_LOP;n
        List<SinhVien> list = getListSV(selectQuery);
        return list;

    }
    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    public void addLop(Lop lop) {
        String query = "INSERT INTO LOP (tenlop,mota)\n" +
                "VALUES ('" + lop.getTenLop() + "', '" + lop.getMoTa() + "' );";
        execSQLite(query);
    }


    public List<Lop> getAllLop() {
        String selectQuery = "SELECT  * FROM " + TABLE_LOP;
        List<Lop> list = getListLop(selectQuery);
        return list;
    }

    public void addDangKy(DangKi dangKi) {
        String query = "INSERT INTO DANGKY (id,idLop,kihoc,stc)\n" +
                "VALUES ('" + dangKi.getIdSV() + "'" +
                ", '" + dangKi.getIdLop() + "', '" + dangKi.getKihoc() + "', '" + dangKi.getStc() + "');";

        execSQLite(query);
    }

    public List<Lop> getAllLopByIdSV(int idSV) {
        // Select All Query
        String selectQuery = "SELECT DANGKY.idLOP, LOP.tenlop, LOP.mota from DANGKY " +
                "INNER JOIN LOP ON LOP.idLOP = DANGKY.idLOP " +
                "WHERE id = " + idSV + ";";
//        String selectQuery = "SELECT  * FROM " + TABLE_LOP;
        List<Lop> list = getListLop(selectQuery);
        return list;
    }


    public boolean checkDangKy(int idLop, int idSV) {
        String selectQuery = "SELECT  * FROM " + TABLE_DKI + " WHERE idLOP= " + idLop + " AND id= " + idSV + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("Sang", "chek: " + selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i = cursor.getCount();
        if (i > 0) {
            return true;
        }
        return false;
    }



}
