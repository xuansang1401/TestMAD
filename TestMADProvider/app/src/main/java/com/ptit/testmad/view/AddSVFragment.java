package com.ptit.testmad.view;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentAddSvBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.SinhVienProvider;
import com.ptit.testmad.model.B17DCCN528_GiangVien;


public class AddSVFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentAddSvBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    public AddSVFragment(DatabaseHelper databaseHelper,MainActivity mainActivity) {
        this.databaseHelper = databaseHelper;
        this.mainActivity=mainActivity;
    }

    ContentResolver resolver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSvBinding.inflate(inflater, container, false);
        mainActivity.setTitleActivity("Giang vien");
        resolver=getContext().getContentResolver();
        initView();
        return binding.getRoot();
    }

    private void initView() {
        String trinhdo[] = {"Thac si","Tien si","Pho GS", "Giao su"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,trinhdo);

        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        binding.spTrinhDo.setAdapter(adapter1);
        binding.spTrinhDo.setOnItemSelectedListener(this);
        binding.btnAddSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = binding.edTen.getText().toString().trim();
                String kn = binding.edKn.getText().toString().trim();
//
                if (ten.isEmpty() || kn.isEmpty() ) {
                    mainActivity.showToast(
                            "Not null"
                    );
                    return;
                }
                int k= Integer.parseInt(kn);
                String td = binding.spTrinhDo.getSelectedItem().toString();
                B17DCCN528_GiangVien sinhVien = new B17DCCN528_GiangVien(ten, td, k);
                resolver.insert(Uri.parse(SinhVienProvider.URI1),databaseHelper.addTable1(sinhVien));
                mainActivity.showToast("THEM THANH CONG");
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

//    private boolean isAdd(String ten, String kn) {
//
//        int ns= Integer.parseInt(namsinh);
//        if (ns< 1900 || ns>2020){
//            mainActivity.showToast("Nam sinh sai");
//            return false;
//        }
//        int nh= Integer.parseInt(namhoc);
//        if (nh< 1 || nh>4){
//            mainActivity.showToast("Nam hoc tu 1 den 4");
//            return false;
//        }
//
//        return true;
//    }

    private void removeText(){
         binding.edTen.setText("");
         binding.edKn.setText("");
//         binding.edQueQuan.setText("");
//         binding.edNamHoc.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}