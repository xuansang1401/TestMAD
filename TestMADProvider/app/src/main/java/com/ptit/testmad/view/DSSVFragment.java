package com.ptit.testmad.view;

import android.database.Cursor;
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
import com.ptit.testmad.db.SinhVienProvider;
import com.ptit.testmad.model.B17DCCN528_GiangVien;

import java.util.ArrayList;
import java.util.List;

public class DSSVFragment extends Fragment implements SinhVienAdapter.AdapterCallBack {

    private DssvFrgamentBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    private int status;
    public DSSVFragment(DatabaseHelper databaseHelper, MainActivity mainActivity, int status) {
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

        Cursor c=null;
        if (status==1){
            c=getContext().getContentResolver().query(Uri.parse(SinhVienProvider.URI1),null, databaseHelper.getAllTable1(), null,null);
        }else {
            c=getContext().getContentResolver().query(Uri.parse(SinhVienProvider.URI1),null, databaseHelper.getAllTable1(), null,null);
        }
        List<B17DCCN528_GiangVien> list=databaseHelper.getListTable1(c);
        if (list.isEmpty()){
            return;
        }
        binding.rcvSv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvSv.setAdapter(new SinhVienAdapter(list,getContext(), this));
    }


    @Override
    public void setOnClickItem(View view, B17DCCN528_GiangVien sinhVien) {
        mainActivity.addDKLopFragment(sinhVien);
    }
}