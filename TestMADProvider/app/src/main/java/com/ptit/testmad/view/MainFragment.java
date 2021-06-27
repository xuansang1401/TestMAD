package com.ptit.testmad.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ptit.testmad.MainActivity;
import com.ptit.testmad.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainActivity mainActivity;
    public MainFragment( MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentMainBinding.inflate(inflater,container,false);
        mainActivity.setTitleActivity("Trang chu");
        initButton();
        return binding.getRoot();
    }

    private void initButton() {
        binding.btn1.setOnClickListener(view->{
            mainActivity.addSinhVienFragment();
        });
        binding.btn2.setOnClickListener(view->{
            mainActivity.addLopFragment();
        });
        binding.btn3.setOnClickListener(view->{
            mainActivity.addDangKyFragment();
        });
    }
}