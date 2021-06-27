package com.ptit.testmad.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentDangkyBinding;
import com.ptit.testmad.databinding.FragmentLopBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.FileManager;
import com.ptit.testmad.model.DangKi;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class DangKiFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentDangkyBinding binding;
    private FileManager databaseHelper;
    private MainActivity mainActivity;
    public DangKiFragment(FileManager databaseHelper, MainActivity mainActivity) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDangkyBinding.inflate(inflater,container,false);
        initView();
        mainActivity.setTitleActivity("Đăng kí");
        return binding.getRoot();
    }

    private void initView() {

        String[] list= databaseHelper.getAllSinhVien();
        String[] lops= databaseHelper.getAllLop();
        if (list ==null || lops== null){
            return;
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,list);

        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        binding.spinnerSv.setAdapter(adapter1);
        //Lop

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,lops);

        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        binding.spinnerLop.setAdapter(adapter2);

        binding.spinnerLop.setOnItemSelectedListener(this);
        binding.spinnerSv.setOnItemSelectedListener(this);

        binding.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kihoc = binding.edKihoc.getText().toString().trim();
                String stc = binding.edStc.getText().toString().trim();

                if (kihoc.isEmpty() || stc.isEmpty()){
                    mainActivity.showToast("Not null");
                    return;
                }
                //getIdSV
                String s=binding.spinnerSv.getSelectedItem().toString() ;

                String[] sinhvien=s.split("_");
                int idSV=Integer.parseInt(sinhvien[0]);
                String tenSv= sinhvien[1];

                //getIDlop
                String l=binding.spinnerLop.getSelectedItem().toString() ;
                String[] lop=l.split("_");
                int idLop=Integer.parseInt(lop[0]);
                String tenLop= lop[1];
                //                return idSV+"_"+idLop+"_"+tenSv+"_"+tenLop+"_"+stc+"_"+kihoc+";";
                if(databaseHelper.checkDangKy(idLop,idSV)){
                    mainActivity.showToast("DA DANG KY");
                    return;
                };

                DangKi dangKi= new DangKi(idLop,  idSV, Integer.parseInt(kihoc) , Integer.parseInt(stc), tenSv,tenLop) ;
//                Log.e("Sang", "id: "+l+ ";name: " +id);
                databaseHelper.addDangKy(dangKi.toString());
                mainActivity.showToast("DANG KI THANH CONG");
            }
        });

//        binding.btnDsLop.setOnClickListener(view->{
//            mainActivity.addDSLopFragment();
//        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}