package io.github.balzss.imind;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class MainPresenter extends MvpBasePresenter<MainView> {

    PreferencesHelper prefHelper;
    AccountManager accountManager;

    public MainPresenter(PreferencesHelper prefHelper, AccountManager accountManager){
        this.prefHelper = prefHelper;
        this.accountManager = accountManager;
    }

    public void signOut(){
        prefHelper.setLoggedIn(false);
        setLoginText();
    }

    public void setLoginText(){
        if (isViewAttached()) {
            if(prefHelper.isLoggedIn()){
                getView().showUsername(accountManager.getUsername());
            } else {
                getView().showNotLoggedIn("you are not logged in");
            }
        }
    }
}
