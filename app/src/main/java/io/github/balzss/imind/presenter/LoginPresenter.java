package io.github.balzss.imind.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.helper.PreferencesHelper;
import io.github.balzss.imind.view.LoginView;

public class LoginPresenter extends MvpBasePresenter<LoginView> {

    PreferencesHelper prefHelper;

    public LoginPresenter(Context context){
        prefHelper = new PreferencesHelper(context);
    }

    public void authUser(String username, String password){
        if (isViewAttached()) {
            if (prefHelper.authUser(username, password)) {
                prefHelper.setLoggedIn(true);
                getView().showSignedInFragment();
            } else {
                getView().showLoginError("wrong username or password");
            }
        }
    }

    public void setHintText(){
        getView().showHint(prefHelper.getHint());
    }
}
