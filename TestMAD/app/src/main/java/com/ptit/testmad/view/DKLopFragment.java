package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.adapter.LopAdapter;
import com.ptit.testmad.databinding.DsLopFrgamentBinding;
import com.ptit.testmad.databinding.FrgamentDkLopBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.List;

public class DKLopFragment extends Fragment implements LopAdapter.AdapterLopCallBack {

    private FrgamentDkLopBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    private SinhVien sinhVien;
    public DKLopFragment(DatabaseHelper databaseHelper, MainActivity mainActivity, SinhVien sinhVien) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
        this.sinhVien=sinhVien;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FrgamentDkLopBinding.inflate(inflater,container,false);
        initView();

        return binding.getRoot();
    }

    private void initView() {
        mainActivity.setTitleActivity(sinhVien.getId()+"_"+sinhVien.getTen());
        List<Lop> list= databaseHelper.getAllLopByIdSV(sinhVien.getId());
        binding.rcvLopDk.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvLopDk.setAdapter(new LopAdapter(list,getContext(), this));
    }

    @Override
    public void setOnClickItem(View view, Lop lop) {

    }
}