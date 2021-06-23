package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.adapter.LopAdapter;
import com.ptit.testmad.adapter.SinhVienAdapter;
import com.ptit.testmad.databinding.DsLopFrgamentBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class DSLopFragment extends Fragment implements LopAdapter.AdapterLopCallBack {

    private DsLopFrgamentBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    private int status;
    public DSLopFragment(DatabaseHelper databaseHelper, MainActivity mainActivity, int status) {
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
        List<Lop> list=new ArrayList<>();
        if (status==1){
            list= databaseHelper.getAllLop();
        }else {
            list=databaseHelper.getAllLop();
        }

        if (list.isEmpty()){
            return;
        }
        binding.rcvLop.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvLop.setAdapter(new LopAdapter(list,getContext(), this));
    }

    @Override
    public void setOnClickItem(View view, Lop lop) {
        mainActivity.addDKSVFragment(lop);
    }
}