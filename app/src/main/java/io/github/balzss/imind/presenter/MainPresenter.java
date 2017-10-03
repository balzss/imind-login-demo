package io.github.balzss.imind.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.helper.PreferencesHelper;
import io.github.balzss.imind.view.MainView;

public class MainPresenter extends MvpBasePresenter<MainView> {

    PreferencesHelper prefHelper;

    public MainPresenter(Context context){
        prefHelper = new PreferencesHelper(context);
    }

    public void signOut(){
        prefHelper.setLoggedIn(false);
    }

    public void login(){
        if(isViewAttached()){
            if(prefHelper.isLoggedIn()){
                getView().showMainFragment();
            } else {
                getView().showLoginFragment();
            }
        }
    }
}
