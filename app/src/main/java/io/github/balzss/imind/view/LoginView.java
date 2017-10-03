package io.github.balzss.imind.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by balzss on 2017. 09. 27..
 */

public interface LoginView extends MvpView {

    void showLoginError(String errorMsg);

    void showHint(String hintText);

    void showSignedInFragment();
}
