package io.github.balzss.imind.Login;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.AccountManager;
import io.github.balzss.imind.PreferencesHelper;

public class LoginPresenter extends MvpBasePresenter<LoginView> {

    PreferencesHelper prefHelper;
    AccountManager accountManager;

    public LoginPresenter(PreferencesHelper prefHelper, AccountManager accountManager){
        this.prefHelper = prefHelper;
        this.accountManager = accountManager;
    }

    public void validateUser(String username, String password){
        if (isViewAttached()) {
            if (accountManager.authUser(username, password)) {
                prefHelper.setLoggedIn(true);
                getView().goToMainActivity();
            } else {
                getView().showLoginError("wrong username or password");
            }
        }
    }

    public void setHintText(){
        getView().setHintText(accountManager.getHint());
    }
}
