package io.github.balzss.imind.Register;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.AccountManager;
import io.github.balzss.imind.PreferencesHelper;

public class RegisterPresenter extends MvpBasePresenter<RegisterView> {

    PreferencesHelper prefHelper;
    AccountManager accountManager;

    public RegisterPresenter(PreferencesHelper prefHelper, AccountManager accountManager){
        this.prefHelper = prefHelper;
        this.accountManager = accountManager;
    }

    public void registerUser(String username, String password){
        prefHelper.setLoggedIn(true);
        accountManager.registerUser(username, password);
        if (isViewAttached()) {
            getView().goToMainActivity();
        }
    }
}
