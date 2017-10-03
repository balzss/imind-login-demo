package io.github.balzss.imind.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.helper.PreferencesHelper;
import io.github.balzss.imind.view.RegisterView;

public class RegisterPresenter extends MvpBasePresenter<RegisterView> {

    PreferencesHelper prefHelper;

    public RegisterPresenter(Context context){
        prefHelper = new PreferencesHelper(context);
    }

    public void registerUser(String username, String password){
        prefHelper.setLoggedIn(true);
        prefHelper.registerUser(username, password);
        if (isViewAttached()) {
            getView().showSignedInFragment();
        }
    }
}
