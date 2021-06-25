package com.ptit.testmad.view;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.adapter.SinhVienAdapter;
import com.ptit.testmad.databinding.DssvFrgamentBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class DSSVFragment extends Fragment implements SinhVienAdapter.AdapterCallBack {

    private DssvFrgamentBinding binding;
    private ContentResolver databaseHelper;
    private MainActivity mainActivity;
    private int status;
    public DSSVFragment(ContentResolver databaseHelper, MainActivity mainActivity, int status) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
        this.status=status;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DssvFrgamentBinding.inflate(inflater,container,false);
        initView();

        return binding.getRoot();
    }

    private void initView() {
        mainActivity.setTitleActivity("Danh Sach Sinh Vien");

//        if (status==1){
//        Cursor c =  managedQuery(students, null, null, null, "name");
        String URI = "content://com.example.provider.College/students";
        Uri students = Uri.parse(URI);
        Cursor c=databaseHelper.query(students,null, "SELECT  * FROM SinhVien", null,null);
        List<SinhVien> list= getListSV(c);
//        }else {
//            list=databaseHelper.getAllStudentByName();
//        }
        if (list.isEmpty()){
            return;
        }
        //lươt dọc xuống
        binding.rcvSv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvSv.setAdapter(new SinhVienAdapter(list,getContext(), this));
    }


    @Override
    public void setOnClickItem(View view, SinhVien sinhVien) {
        mainActivity.addDKLopFragment(sinhVien);
    }

    private List<SinhVien> getListSV(Cursor cursor) {
        if (cursor == null){
            return null;
        }
        List<SinhVien> listStudent = new ArrayList<SinhVien>();

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

        return listStudent;
    }

}