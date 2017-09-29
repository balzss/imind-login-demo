package io.github.balzss.imind;

import android.content.SharedPreferences;

public class AccountManager {

    SharedPreferences preferences;

    public AccountManager(SharedPreferences preferences){
        this.preferences = preferences;
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
        Ed.commit();
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
