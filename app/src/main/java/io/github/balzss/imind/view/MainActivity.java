package io.github.balzss.imind.view;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.ButterKnife;
import io.github.balzss.imind.R;
import io.github.balzss.imind.presenter.MainPresenter;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        presenter.login();
    }

    @Override // Called internally by Mosby
    public MainPresenter createPresenter(){
        return new MainPresenter(this);
    }

    @Override
    public void showLoginFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
    }

    @Override
    public void showMainFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SignedInFragment()).commit();
    }
}