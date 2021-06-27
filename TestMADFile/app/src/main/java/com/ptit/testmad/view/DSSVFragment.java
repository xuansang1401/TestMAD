package com.ptit.testmad.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.adapter.SinhVienAdapter;
import com.ptit.testmad.databinding.DssvFrgamentBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.FileManager;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class DSSVFragment extends Fragment{

    private DssvFrgamentBinding binding;
    private FileManager databaseHelper;
    private MainActivity mainActivity;
    private int status;
    public DSSVFragment(FileManager databaseHelper, MainActivity mainActivity, int status) {
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
//        List<SinhVien> list= new ArrayList<>();
//        if (status==1){
//            list=databaseHelper.getAllStudent();
//        }else {
//            list=databaseHelper.getAllStudentByName();
//        }
//        if (list.isEmpty()){
//            return;
//        }
        String[] list= databaseHelper.getAllSinhVien();
        if (list==null){
            return;
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,list);

        binding.rcvSv.setAdapter(adapter1);

        binding.rcvSv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Sang", "text: "+parent.toString()+"; pos: "+position);
                SinhVien sinhVien= databaseHelper.stringToSV(list[position]);
                mainActivity.addDKLopFragment(sinhVien);
            }
        });
    }


}