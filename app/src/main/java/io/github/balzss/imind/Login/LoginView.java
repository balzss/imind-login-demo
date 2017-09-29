package io.github.balzss.imind.Login;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by balzss on 2017. 09. 27..
 */

public interface LoginView extends MvpView {

    void goToMainActivity();

    void showLoginError(String errorMsg);

    void setHintText(String hintText);
}
