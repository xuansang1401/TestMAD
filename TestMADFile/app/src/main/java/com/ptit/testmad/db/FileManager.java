package com.ptit.testmad.db;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.ptit.testmad.model.DangKi;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Context context;

    public FileManager(Context context) {
        this.context = context;
    }

    public static FileManager getInstance(Context context) {
        return new FileManager(context);
    }

    private static final String SINHVIEN = "SINHVIEN.txt";
    private static final String LOP = "LOP.txt";
    private static final String DANGKY = "DANGKY.txt";

    private void writeFile(String nameFile, String text) {
        try {
            FileOutputStream out = context.openFileOutput(nameFile, context.MODE_APPEND);

            out.write(text.getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String nameFile) {
        String readString = "";
        try {
            FileInputStream fis = context.openFileInput(nameFile);
            byte[] data = new byte[(int) fis.available()];
            fis.read(data);
            fis.close();
            readString = new String(data, "UTF-8");
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readString;
    }

    public void addStudent(String s) {
        writeFile(SINHVIEN, s);
    }

    public String[] getAllSinhVien() {
        String file = readFile(SINHVIEN);
        if (file.isEmpty()){
            Log.e("Sang", "null");
            return null;
        }
        String[] arr = file.split(";");
        return arr;
    }

    public void addLop(String s) {
        writeFile(LOP, s);
    }

    public String[] getAllLop() {
        String file = readFile(LOP);
        if (file.isEmpty()){
            Log.e("Sang", "null");
            return null;
        }
        String[] arr = file.split(";");

        return arr;
    }
    public void addDangKy(String s) {
        writeFile(DANGKY, s);
    }

    public String[] getAllDangKy() {
        String file = readFile(DANGKY);
        if (file.isEmpty()){
            Log.e("Sang", "null");
            return null;
        }
        String[] arr = file.split(";");
        return arr;
    }

    public List<DangKi> getAllList(){
        List<DangKi> list=new ArrayList<>();
        String[] dangKis=getAllDangKy();
        if (dangKis==null) return null;
        for (String dangki: dangKis){
            String[] dk= dangki.split("_");
//            DangKi( int idLop, int idSV, int kihoc, int stc, String tenSv, String tenLop)
            list.add(new DangKi(Integer.parseInt(dk[0]),
                    Integer.parseInt(dk[1]),
                    Integer.parseInt(dk[2]),
                    Integer.parseInt(dk[0]),
                    dk[3],
                    dk[4]));
        }
        return list;
    }
    public boolean checkDangKy(int idLop, int idSV){
        String[] list=getAllDangKy();
        if (list==null) return false;
        for (String dangky: list){
            if (dangky.startsWith(idSV+"_"+idLop)){
                return true;
            }
        }
        return false;
    }

    public List<String> getListLopBySV(int id){
        List<String> list=new ArrayList<>();
        List<DangKi> listDK= getAllList();
        if (listDK==null) return null;
        for (DangKi dangKi: listDK){
            if (dangKi.getIdSV()==id){
                list.add(dangKi.toStringLop());
            }
        }

        return list;
    }

    public List<String> getListSVByLop(int id){
        List<String> list=new ArrayList<>();
        List<DangKi> listDK= getAllList();
        if (listDK==null) return null;
        for (DangKi dangKi: listDK){
            if (dangKi.getIdLop()==id){
                list.add(dangKi.toStringSinhVien());
            }
        }
        return list;
    }

    public Lop stringToLop(String s){
        String lop[]= s.split("_");
        Lop l= new Lop(Integer.parseInt(lop[0]), lop[1], lop[2]);
        return l;
    }
    public SinhVien stringToSV(String s){
        String strings[]= s.split("_");
        SinhVien l= new SinhVien(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]),strings[3],Integer.parseInt(strings[4]));
        return l;
    }
}
