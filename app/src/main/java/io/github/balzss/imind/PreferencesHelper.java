package io.github.balzss.imind;

import android.content.SharedPreferences;

public class PreferencesHelper {

    SharedPreferences preferences;

    public PreferencesHelper(SharedPreferences preferences){
        this.preferences = preferences;
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean("loggedIn", false);
    }

    public void setLoggedIn(boolean state){
        preferences.edit().putBoolean("loggedIn", state).apply();
    }
}
