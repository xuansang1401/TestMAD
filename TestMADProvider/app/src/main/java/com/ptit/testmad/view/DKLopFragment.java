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
import com.ptit.testmad.adapter.LopAdapter;
import com.ptit.testmad.databinding.FrgamentDkLopBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.SinhVienProvider;
import com.ptit.testmad.model.B17DCCN528_ChuyenMon;
import com.ptit.testmad.model.B17DCCN528_GiangVien;

import java.util.List;

public class DKLopFragment extends Fragment implements LopAdapter.AdapterLopCallBack {

    private FrgamentDkLopBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    private B17DCCN528_GiangVien sinhVien;
    public DKLopFragment(DatabaseHelper databaseHelper, MainActivity mainActivity, B17DCCN528_GiangVien sinhVien) {
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
        Cursor c2=getContext().getContentResolver().query(Uri.parse(SinhVienProvider.URI3),null, databaseHelper.getAllLopByIdSV(sinhVien.getId()), null,null);


        List<B17DCCN528_ChuyenMon> list= databaseHelper.getListTable2(c2);
        binding.rcvLopDk.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvLopDk.setAdapter(new LopAdapter(list,getContext(), this));
    }

    @Override
    public void setOnClickItem(View view, B17DCCN528_ChuyenMon lop) {

    }
}