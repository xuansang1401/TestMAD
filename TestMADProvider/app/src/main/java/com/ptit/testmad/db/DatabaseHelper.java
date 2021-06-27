package com.ptit.testmad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ptit.testmad.model.B17DCCN527_DangKi;
import com.ptit.testmad.model.B17DCCN528_ChuyenMon;
import com.ptit.testmad.model.B17DCCN528_GiangVien;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DOXUANSANG_QUANLY";
    static final String ID = "id";
    public static final String TABLE_1 = "GiangVien";
    static final String COL1_1 = "ten";
    static final String COL1_2 = "trinhDo";
    static final String COL1_3 = "soNamKN";

    static final String TABLE_2 = "ChuyenMon";
    static final String COL2_1 = "tenf";
    static final String COL2_2 = "moTa";

     static final String TABLE_3 = "DangKi";
     static final String COL3_1 = "idGV";
     static final String COL3_2 = "idCM";
     static final String COL3_3 = "soNamKn";

     static final String T1C0 = TABLE_1+"."+ID;
     static final String T1C1 = TABLE_1+"."+COL1_1;
     static final String T1C2 = TABLE_1+"."+COL1_2;
     static final String T1C3 = TABLE_1+"."+COL1_3;

     static final String T2C0 = TABLE_2+"."+ID;
     static final String T2C1 = TABLE_2+"."+COL2_1;
     static final String T2C2 = TABLE_2+"."+COL2_2;

     static final String T3C1 = TABLE_3+"."+COL3_1;
     static final String T3C2 = TABLE_3+"."+COL3_2;


    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DB Manager", "DB Manager");

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sqlQuery1 = "CREATE TABLE " + TABLE_1 + " (" +
                ID + " integer primary key AUTOINCREMENT, " +
                COL1_1 + " TEXT, " +
                COL1_2 + " TEXT, " +
                COL1_3 + " integer)";
        db.execSQL(sqlQuery1);
        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();
        String sqlQuery2 = "CREATE TABLE " + TABLE_2 + " (" +
                ID + " integer primary key AUTOINCREMENT, " +
                COL2_1 + " TEXT, " +
                COL2_2 + " TEXT)";

        db.execSQL(sqlQuery2);

        String sqlQuery3 = "CREATE TABLE " + TABLE_3 + " (" +
                ID + " integer primary key AUTOINCREMENT, " +
                COL3_1 + " integer, " +
                COL3_2 + " integer, " +
                COL3_3 + " integer)";
        db.execSQL(sqlQuery3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_2);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_3);
        onCreate(db);
        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();

    }

    private void execSQLite(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Log.e("Sang", "query: " + sql);
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("SQLITE", e.toString());
        }
        db.close();
    }

    public List<B17DCCN528_GiangVien> getListTable1(Cursor cursor) {
        List<B17DCCN528_GiangVien> listStudent = new ArrayList<B17DCCN528_GiangVien>();

        if (cursor.moveToFirst()) {
            do {
                B17DCCN528_GiangVien student = new B17DCCN528_GiangVien();
                student.setId(cursor.getInt(0));
                student.setTen(cursor.getString(1));
                student.setTrinhDo(cursor.getString(2));
                student.setSoNamKN(cursor.getInt(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listStudent;
    }

    public List<B17DCCN528_ChuyenMon> getListTable2(Cursor cursor) {
        List<B17DCCN528_ChuyenMon> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                B17DCCN528_ChuyenMon lop = new B17DCCN528_ChuyenMon();
                lop.setId(cursor.getInt(0));
                lop.setTen(cursor.getString(1));
                lop.setMoTa(cursor.getString(2));

                list.add(lop);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    public ContentValues addTable1(B17DCCN528_GiangVien data) {
            ContentValues values = new ContentValues();
//            values.put(ID, data.getId());
            values.put(COL1_1, data.getTen());
            values.put(COL1_2, data.getTrinhDo());
            values.put(COL1_3, data.getSoNamKN());

            //Neu de null thi khi value bang null thi loi

        return values;

    }

    public void deleteSV(int id) {
        String query = "DELETE FROM "+TABLE_1+" WHERE id = " + id + ";";
        execSQLite(query);
    }

    public String getAllTable1() {
        String selectQuery = "SELECT  * FROM "+ TABLE_1;
        return selectQuery;
    }

//    public List<B17DCCN528_GiangVien> getAllStudentByName() {
//        String selectQuery = "SELECT  * FROM " + TABLE_1 + " WHERE "+COL1_3+" >5 ";
//        List<B17DCCN528_GiangVien> listStudent = getListTable1(selectQuery);
//        return listStudent;
//    }

    public String getGVByCM(int id) {
        String selectQuery = "SELECT "+T3C1+", "+T1C1+", "+T1C2+", "+T1C3+"" +
                " from "+TABLE_3+
                " INNER JOIN "+TABLE_1+" ON "+T3C1+" = "+T1C0 +
                " WHERE "+T3C2+" = " + id + ";";

        return selectQuery;

    }

    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    public ContentValues addTable2(B17DCCN528_ChuyenMon data) {
            ContentValues values = new ContentValues();
//            values.put(ID, data.getId());
            values.put(COL2_1, data.getTen());
            values.put(COL2_2, data.getMoTa());
        return values;
    }


    public String getAllTable2() {
        String selectQuery = "SELECT  * FROM " + TABLE_2;

        return selectQuery;
    }

//    public List<B17DCCN528_ChuyenMon> getAllLopByName() {
//        String selectQuery = "SELECT  * FROM " + TABLE_2 + " WHERE ten= 'toan'; ";
//        List<B17DCCN528_ChuyenMon> list = getListTable2(selectQuery);
//        return list;
//    }

    public ContentValues addTable3(B17DCCN527_DangKi data) {
            ContentValues values = new ContentValues();
//            values.put(ID, data.getId());
            values.put(COL3_1, data.getIdGV());
            values.put(COL3_2, data.getIdCM());
            values.put(COL3_3, data.getSoNamKn());
        return values;

    }

    public String getAllLopByIdSV(int id) {
        // Select All Query

        String selectQuery = "SELECT "+T3C2+", "+T2C1+", "+T2C2+" from "+TABLE_3+
                " INNER JOIN "+TABLE_2+" ON "+T2C0+" = "+T3C2+
                " WHERE "+T3C1+" = " + id + ";";
//        String selectQuery = "SELECT  * FROM " + TABLE_LOP;
        return selectQuery;
    }


    public String checkDangKy(int id1, int id2) {
        String selectQuery = "SELECT  * FROM " + TABLE_3 +
                " WHERE "+COL3_1+"= " + id1 + " AND "+COL3_2+"= " + id2 + ";";
        Log.e("Sang", "chek: " + selectQuery);
        return selectQuery;
    }
}


