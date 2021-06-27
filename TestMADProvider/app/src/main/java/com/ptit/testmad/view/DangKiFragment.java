package com.ptit.testmad.view;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;


import com.ptit.testmad.MainActivity;
import com.ptit.testmad.R;
import com.ptit.testmad.databinding.FragmentDangkyBinding;
import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.db.SinhVienProvider;
import com.ptit.testmad.model.B17DCCN527_DangKi;
import com.ptit.testmad.model.B17DCCN528_ChuyenMon;
import com.ptit.testmad.model.B17DCCN528_GiangVien;

import java.util.ArrayList;
import java.util.List;

public class DangKiFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentDangkyBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;

    public DangKiFragment(DatabaseHelper databaseHelper, MainActivity mainActivity) {
        this.databaseHelper = databaseHelper;
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDangkyBinding.inflate(inflater, container, false);
        initView();
        mainActivity.setTitleActivity("Đăng kí");
        return binding.getRoot();
    }

    private List<B17DCCN528_ChuyenMon> list2;
    private List<B17DCCN528_GiangVien> list1;

    private void initView() {
        Cursor c2 = getContext().getContentResolver().query(Uri.parse(SinhVienProvider.URI2), null, databaseHelper.getAllTable2(), null, null);
        Cursor c1 = getContext().getContentResolver().query(Uri.parse(SinhVienProvider.URI1), null, databaseHelper.getAllTable1(), null, null);

        list1 = databaseHelper.getListTable1(c1);
        list2 = databaseHelper.getListTable2(c2);
        if (list1.isEmpty() || list2.isEmpty()) {
            return;
        }
        List<String> arr1 = new ArrayList();
        for (B17DCCN528_GiangVien data : list1) {
            arr1.add(data.getId() + "_" + data.getTen());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arr1);

        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        binding.spinner1.setAdapter(adapter1);
        //Lop

        List<String> arr2 = new ArrayList();
        for (B17DCCN528_ChuyenMon data : list2) {
            arr2.add(data.getId() + "_" + data.getTen());
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arr2);

        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//
        binding.spinner2.setAdapter(adapter2);

        binding.spinner2.setOnItemSelectedListener(this);
        binding.spinner1.setOnItemSelectedListener(this);

        binding.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String kihoc = binding.edKihoc.getText().toString().trim();
                String kn = binding.edKn.getText().toString().trim();
//
//                if (kihoc.isEmpty() || kn.isEmpty()){
//                    mainActivity.showToast("Not null");
//                    return;
//                }
                //getIdSV
                String s = binding.spinner1.getSelectedItem().toString();
                int id1 = Integer.parseInt(s.replaceAll("[\\D]", ""));

                //getIDlop
                String l = binding.spinner2.getSelectedItem().toString();
                int id2 = Integer.parseInt(l.replaceAll("[\\D]", ""));
                Cursor cdk = getContext().getContentResolver().query(Uri.parse(SinhVienProvider.URI3), null, databaseHelper.checkDangKy(id1, id2), null, null);

                int i = cdk.getCount();
                if (i > 0) {
                    mainActivity.showToast("DA DANG KY");
                    return;
                }

//                if(databaseHelper.checkDangKy(id1,id2)){
//                    mainActivity.showToast("DA DANG KY");
//                    return;
//                };

                B17DCCN527_DangKi dangKi = new B17DCCN527_DangKi(id1, id2, Integer.parseInt(kn.replaceAll("[\\D]", "")));
//                Log.e("Sang", "id: "+l+ ";name: " +id);
                Uri uri = getContext().getContentResolver().insert(Uri.parse(SinhVienProvider.URI3), databaseHelper.addTable3(dangKi));

                mainActivity.showToast("DANG KI THANH CONG" + uri);
            }
        });

//        binding.btnDsLop.setOnClickListener(view->{
//            mainActivity.addDSLopFragment();
//        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (list1 == null || list1.isEmpty()) return;

        Log.e("sang", "onItemSelected" + parent);
        if (parent.getId() == R.id.spinner_1) {
            binding.edKn.setText("Số năm kinh nghiệm: " + list1.get(position).getSoNamKN());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}