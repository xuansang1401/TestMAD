package com.ptit.testmad.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentLopBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.SinhVienProvider;
import com.ptit.testmad.model.B17DCCN528_ChuyenMon;


public class LopFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentLopBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;

    public LopFragment(DatabaseHelper databaseHelper, MainActivity mainActivity) {
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
        String cm[] = {"Phan men","Lap trinh mang","Phan tich du lieu", "An toan thong tin"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,cm);

        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        binding.spinnerCm.setAdapter(adapter1);
        binding.spinnerCm.setOnItemSelectedListener(this);
        binding.btnAddLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mota = binding.edMoTa.getText().toString().trim();

                if ( mota.isEmpty()){
                    mainActivity.showToast("Not null");
                    return;
                }
                String ten = binding.spinnerCm.getSelectedItem().toString();

                B17DCCN528_ChuyenMon lop=  new B17DCCN528_ChuyenMon(ten, mota);
//                if (!databaseHelper.addTable2(lop)){
//                    mainActivity.showToast("THEM THAT BAI");
//                    return;
//                };
                getContext().getContentResolver().insert(Uri.parse(SinhVienProvider.URI2),databaseHelper.addTable2(lop));

                mainActivity.showToast("THEM THANH CONG");
            }
        });

        binding.btnDsLop.setOnClickListener(view->{
            mainActivity.addDSLopFragment(1);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}