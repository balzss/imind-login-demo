package io.github.balzss.imind.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesHelper {

    SharedPreferences preferences;

    public PreferencesHelper(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean("loggedIn", false);
    }

    public void setLoggedIn(boolean state){
        preferences.edit().putBoolean("loggedIn", state).apply();
    }

    public boolean authUser(String username, String password){
        if(preferences.getString("username", null) == null){
            registerUser("asd", "jkl");
        }
        return username.equals(preferences.getString("username", null)) && password.equals(preferences.getString("password", null));
    }

    public void registerUser(String username, String password){
        SharedPreferences.Editor Ed = preferences.edit();
        Ed.putString("username", username);
        Ed.putString("password", password);
        Ed.apply();
    }

    public String getHint(){
        if(preferences.getString("username", null) == null){
            registerUser("asd", "jkl");
        }
        return "Hint: username is " + preferences.getString("username", null) + " and password is " + preferences.getString("password", null);
    }

    public String getUsername(){
        if(preferences.getString("username", null) == null){
            registerUser("asd", "jkl");
        }
        return preferences.getString("username", null);
    }
}
