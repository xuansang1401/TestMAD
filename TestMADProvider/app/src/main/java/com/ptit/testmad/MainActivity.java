package com.ptit.testmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ptit.testmad.db.DatabaseHelper;
import com.ptit.testmad.model.Lop;
import com.ptit.testmad.model.SinhVien;
import com.ptit.testmad.view.AddSVFragment;
import com.ptit.testmad.view.DKLopFragment;
import com.ptit.testmad.view.DSLopFragment;
import com.ptit.testmad.view.DSSVFragment;
import com.ptit.testmad.view.DangKiFragment;
import com.ptit.testmad.view.FragmentDKSV;
import com.ptit.testmad.view.LopFragment;
import com.ptit.testmad.view.MainFragment;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper= new DatabaseHelper(this);
        addMainFragment();
        setTitleActivity("Trang chủ");

//        ContentResolver contentResolver=getContentResolver();

    }

    //1 activity nh fragment do ac quan ly
    private void addMainFragment() {
        MainFragment mainFragment = new MainFragment(databaseHelper, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, mainFragment, mainFragment.getTag())
                .commit();
    }

    public void addSinhVienFragment() {
        AddSVFragment fragment = new AddSVFragment(getContentResolver(), this);
        replaceFragment(fragment);
    }

    public void addDSSVFragment(int status) {
        DSSVFragment fragment = new DSSVFragment(getContentResolver(), this, status);

        replaceFragment(fragment);
    }

    public void addLopFragment() {
        LopFragment fragment = new LopFragment(databaseHelper, this);
        replaceFragment(fragment);

    }

    public void addDSLopFragment(int status) {
        DSLopFragment fragment = new DSLopFragment(databaseHelper, this,status);
        replaceFragment(fragment);
    }

    public void addDangKyFragment() {
        DangKiFragment fragment = new DangKiFragment(databaseHelper, this);
        replaceFragment(fragment);
    }

    public void addDKLopFragment(SinhVien sinhVien) {
        DKLopFragment fragment = new DKLopFragment(databaseHelper, this,sinhVien);
        replaceFragment(fragment);
    }

    public void addDKSVFragment(Lop lop) {
        FragmentDKSV fragment = new FragmentDKSV(databaseHelper, this,lop);
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getTag())
                .addToBackStack(null)
                .commit();
    }

    public void setTitleActivity(String title){
        getSupportActionBar().setTitle(title);
    }
    @SuppressLint("ShowToast")
    public void showToast(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }

}