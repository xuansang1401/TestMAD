package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.adapter.LopAdapter;
import com.ptit.testmad.adapter.SinhVienAdapter;
import com.ptit.testmad.databinding.DsLopFrgamentBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.FileManager;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class DSLopFragment extends Fragment {

    private DsLopFrgamentBinding binding;
    private FileManager databaseHelper;
    private MainActivity mainActivity;
    private int status;
    public DSLopFragment(FileManager databaseHelper, MainActivity mainActivity, int status) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
        this.status=status;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DsLopFrgamentBinding.inflate(inflater,container,false);
        initView();
        mainActivity.setTitleActivity("Danh Sach Lop");
        return binding.getRoot();
    }

    private void initView() {
//        List<Lop> list=new ArrayList<>();
//        if (status==1){

//        }else {
//            list=databaseHelper.getAllLopByName();
//        }
//
//        if (list.isEmpty()){
//            return;
//        }

        String[] list= databaseHelper.getAllLop();
        if (list== null) return;
        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);
        //lươt dọc xuống
//        binding.rcvSv.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.rcvSv.setAdapter(new SinhVienAdapter(list,getContext(), this));
        binding.rcvLop.setAdapter(adapter1);

        binding.rcvLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= list[position];
                Lop lop=databaseHelper.stringToLop(s);
                mainActivity.addDKSVFragment(lop);
            }
        });
    }


}