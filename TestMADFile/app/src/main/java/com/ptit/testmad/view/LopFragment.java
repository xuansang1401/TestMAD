package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentLopBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.FileManager;
import com.ptit.testmad.model.Lop;

public class LopFragment extends Fragment {

    private FragmentLopBinding binding;
    private FileManager databaseHelper;
    private MainActivity mainActivity;

    public LopFragment(FileManager databaseHelper, MainActivity mainActivity) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLopBinding.inflate(inflater,container,false);
        initView();
        mainActivity.setTitleActivity("Lớp");
        return binding.getRoot();
    }

    private void initView() {
        binding.btnAddLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = binding.edTenLop.getText().toString().trim();
                String mota = binding.edMoTa.getText().toString().trim();

                if (ten.isEmpty() || mota.isEmpty()){
                    mainActivity.showToast("Not null");
                    return;
                }
                int id=0;
                if (databaseHelper.getAllLop()!= null){
                    id=databaseHelper.getAllLop().length;
                }
                Lop lop=  new Lop(id,ten, mota);
                databaseHelper.addLop(lop.toString());
                mainActivity.showToast("THEM LOP THANH CONG");
            }
        });

        binding.btnDsLop.setOnClickListener(view->{
            mainActivity.addDSLopFragment(1);
        });
    }
}