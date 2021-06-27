package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FragmentDKSV extends Fragment{

    private DssvFrgamentBinding binding;
    private FileManager databaseHelper;
    private MainActivity mainActivity;
    private Lop lop;
    public FragmentDKSV(FileManager databaseHelper, MainActivity mainActivity, Lop lop) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
        this.lop=lop;
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
        List<String> list=databaseHelper.getListLopBySV(lop.getIdLop());
//        String[] list= databaseHelper.getAllLop();
        if (list== null) return;
        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);

        binding.rcvSv.setAdapter(adapter1);

    }

}