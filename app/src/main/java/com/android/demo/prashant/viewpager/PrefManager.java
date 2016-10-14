package com.android.demo.prashant.viewpager;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ADMIN on 5/12/2016.
 */
public class PrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME = "introPage";
    private static final String IS_FIRST_TIME_LAUNCH_KEY = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }


    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH_KEY,true);
    }

    public void setFirstLaunchFlag(boolean flag){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH_KEY, flag);
        editor.commit();
    }

}
