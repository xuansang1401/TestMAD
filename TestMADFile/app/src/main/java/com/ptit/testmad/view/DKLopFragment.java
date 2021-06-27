package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.adapter.LopAdapter;
import com.ptit.testmad.databinding.DsLopFrgamentBinding;
import com.ptit.testmad.databinding.FrgamentDkLopBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.FileManager;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.List;

public class DKLopFragment extends Fragment  {

    private FrgamentDkLopBinding binding;
    private FileManager databaseHelper;
    private MainActivity mainActivity;
    private SinhVien sinhVien;
    public DKLopFragment(FileManager databaseHelper, MainActivity mainActivity, SinhVien sinhVien) {
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
        List<String> list=databaseHelper.getListLopBySV(sinhVien.getId());
        if (list== null) return;
        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);

        binding.rcvLopDk.setAdapter(adapter1);
    }

}