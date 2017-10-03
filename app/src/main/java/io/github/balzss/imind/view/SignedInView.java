package io.github.balzss.imind.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface SignedInView extends MvpView {

    void showWelcomeText(String username);

    void showLoginScreen();
}
