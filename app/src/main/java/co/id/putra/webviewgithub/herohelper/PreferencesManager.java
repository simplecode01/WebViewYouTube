package co.id.putra.webviewgithub.herohelper;

import android.content.Context;
import android.content.SharedPreferences;

import co.id.putra.webviewgithub.welcomepackage1243.WelcomeActivity;

public class PreferencesManager {
    SharedPreferences sharedPreferencesSlider;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;



    private  static final String PREF_NAME = "Aplikasi Music";
    private static final String IS_FIRST_TIME_LAUNCH ="isFirstTimeLaunch";

    public  PreferencesManager(WelcomeActivity context){
        this._context = context;
        sharedPreferencesSlider=_context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor=sharedPreferencesSlider.edit();
    }
    public void SetIsFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch(){
        return sharedPreferencesSlider.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}

