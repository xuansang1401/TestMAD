package com.ptit.testmad.db;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    private SharedPreferences preferences;
    public AppConfig(Context context){
        preferences= context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
    }
    public static AppConfig getInstance(Context context){

        return new AppConfig(context);
    }


    public void setName(String name){
        preferences.edit().putString("NAME", name).apply();
    }

    public void getName(String name){
        preferences.getString("NAME", "sang");
    }

}
