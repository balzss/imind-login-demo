package io.github.balzss.imind.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.helper.PreferencesHelper;
import io.github.balzss.imind.view.SignedInView;

public class SignedInPresenter extends MvpBasePresenter<SignedInView> {

    PreferencesHelper prefHelper;

    public SignedInPresenter(Context context){
        prefHelper = new PreferencesHelper(context);
    }

    public void signOut() {
        prefHelper.setLoggedIn(false);
        if (isViewAttached()) {
            getView().showLoginScreen();
        }
    }

    public void setHello(){
        if(isViewAttached()){
            getView().showWelcomeText(prefHelper.getUsername());
        }
    }
}
