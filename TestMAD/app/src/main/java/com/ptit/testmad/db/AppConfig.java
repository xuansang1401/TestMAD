package com.ptit.testmad.db;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    private Context context;
    public AppConfig(Context context){
        this.context=context;
    }
    private SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

    public void setName(String name){
        preferences.edit().putString("NAME", name).apply();
    }

    public void getName(String name){
        preferences.getString("NAME", "sang");
    }

}
