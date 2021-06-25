package com.ptit.testmad.view;

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

public class FragmentDKSV extends Fragment implements SinhVienAdapter.AdapterCallBack {

    private DssvFrgamentBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    private Lop lop;
    public FragmentDKSV(DatabaseHelper databaseHelper, MainActivity mainActivity, Lop lop) {
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
        List<SinhVien> list=databaseHelper.getSVByLop(lop.getIdLop());
        if (list.isEmpty()){
            return;
        }
        binding.rcvSv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvSv.setAdapter(new SinhVienAdapter(list,getContext(), this));
    }

    @Override
    public void setOnClickItem(View view, SinhVien sinhVien) {
    }
}