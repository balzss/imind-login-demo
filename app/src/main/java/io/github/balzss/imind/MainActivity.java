package io.github.balzss.imind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.OnClick;
import io.github.balzss.imind.Login.LoginActivity;
import io.github.balzss.imind.Register.RegisterActivity;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    TextView notLoggedInView;
    TextView usernameView;
    TextView helloTextView;
    Button loginButton;
    Button registerButton;
    Button signOutButton;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);

        helloTextView = (TextView)findViewById(R.id.hello_text);
        notLoggedInView = (TextView)findViewById(R.id.not_logged_in_view);
        usernameView = (TextView)findViewById(R.id.username_text);
        loginButton = (Button)findViewById(R.id.login_button);
        registerButton = (Button)findViewById(R.id.register_button);
        signOutButton = (Button)findViewById(R.id.signout_button);

        presenter.setLoginText();
    }

    @Override // Called internally by Mosby
    public MainPresenter createPresenter(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return new MainPresenter(new PreferencesHelper(prefs), new AccountManager(prefs));
    }

    @OnClick(R.id.signout_button)
    public void signOut(View v) { presenter.signOut(); }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(View v){
        goToLoginActivity();
    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(View v){
        goToRegisterActivity();
    }

    @Override
    public void showNotLoggedIn(String loginText){
        helloTextView.setVisibility(View.INVISIBLE);
        usernameView.setVisibility(View.INVISIBLE);
        notLoggedInView.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        signOutButton.setVisibility(View.INVISIBLE);

        notLoggedInView.setText(loginText);
    }

    @Override
    public void showUsername(String username){
        notLoggedInView.setVisibility(View.INVISIBLE);
        registerButton.setVisibility(View.INVISIBLE);
        loginButton.setVisibility(View.INVISIBLE);

        helloTextView.setVisibility(View.VISIBLE);
        usernameView.setVisibility(View.VISIBLE);
        signOutButton.setVisibility(View.VISIBLE);

        usernameView.setText(username);
    }

    @Override
    public void goToLoginActivity(){
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void goToRegisterActivity(){
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }
}