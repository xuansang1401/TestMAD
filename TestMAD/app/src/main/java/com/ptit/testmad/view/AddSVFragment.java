package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentAddSvBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.model.SinhVien;

public class AddSVFragment extends Fragment {

    private FragmentAddSvBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    public AddSVFragment(DatabaseHelper databaseHelper,MainActivity mainActivity) {
        this.databaseHelper = databaseHelper;
        this.mainActivity=mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSvBinding.inflate(inflater, container, false);
        mainActivity.setTitleActivity("Sinh Viên");
        initView();


        return binding.getRoot();
    }

    private void initView() {

        binding.btnAddSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = binding.edTen.getText().toString().trim();
                String namsinh = binding.edNamSinh.getText().toString().trim();
                String quequan = binding.edQueQuan.getText().toString().trim();
                String namhoc = binding.edNamHoc.getText().toString().trim();
                if (!isAdd(ten, namsinh, quequan, namhoc)){
                    return;
                }
                int ns= Integer.parseInt(namsinh);
                int nh= Integer.parseInt(namhoc);

                SinhVien sinhVien = new SinhVien(ten, ns, quequan, nh);
                databaseHelper.addSinhVien(sinhVien);
//                Toast.makeText(getContext(),"Them... ", Toast.LENGTH_LONG).show();
                mainActivity.showToast("THEM SV THANH CONG");
                removeText();
            }
        });
        binding.btnDssv.setOnClickListener(view->{
            mainActivity.addDSSVFragment(1);
        });
        binding.btnLoc.setOnClickListener(view->{
            mainActivity.addDSSVFragment(9);
        });
    }

    private boolean isAdd(String ten, String namsinh, String quequan, String namhoc) {
        if (ten.isEmpty() || namsinh.isEmpty() || quequan.isEmpty() || namhoc.isEmpty() ) {
            mainActivity.showToast(
                    "Not null"
            );
            return false;
        }
        int ns= Integer.parseInt(namsinh);
        if (ns< 1900 || ns>2020){
            mainActivity.showToast("Nam sinh sai");
            return false;
        }
        int nh= Integer.parseInt(namhoc);
        if (nh< 1 || nh>4){
            mainActivity.showToast("Nam hoc tu 1 den 4");
            return false;
        }

        return true;
    }

    private void removeText(){
         binding.edTen.setText("");
         binding.edNamSinh.setText("");
         binding.edQueQuan.setText("");
         binding.edNamHoc.setText("");
    }

}