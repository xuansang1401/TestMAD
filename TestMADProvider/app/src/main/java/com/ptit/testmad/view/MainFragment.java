package com.ptit.testmad.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentMainBinding;
import com.ptit.testmad.db.DatabaseHelper;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    public MainFragment(DatabaseHelper databaseHelper, MainActivity mainActivity) {
        this.databaseHelper=databaseHelper;
        this.mainActivity=mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMainBinding.inflate(inflater,container,false);
        initButton();
        return binding.getRoot();
    }

    private void initButton() {
        binding.addSv.setOnClickListener(view->{
            mainActivity.addSinhVienFragment();
        });
        binding.addLop.setOnClickListener(view->{
            mainActivity.addLopFragment();
        });
        binding.btnDangky.setOnClickListener(view->{
            mainActivity.addDangKyFragment();
        });
    }
}