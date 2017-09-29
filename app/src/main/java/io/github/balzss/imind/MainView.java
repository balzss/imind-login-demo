package io.github.balzss.imind;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface MainView extends MvpView {

    void goToLoginActivity();

    void goToRegisterActivity();

    void showNotLoggedIn(String loginText);

    void showUsername(String username);
}
