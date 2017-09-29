package io.github.balzss.imind.Register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.OnClick;
import io.github.balzss.imind.AccountManager;
import io.github.balzss.imind.Login.LoginActivity;
import io.github.balzss.imind.MainActivity;
import io.github.balzss.imind.PreferencesHelper;
import io.github.balzss.imind.R;

public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    EditText usernameView;
    EditText passwordView;
    TextView hintText;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_register);

        usernameView = (EditText)findViewById(R.id.username_text);
        passwordView = (EditText)findViewById(R.id.password_text);
    }

    @Override // Called internally by Mosby
    public RegisterPresenter createPresenter(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return new RegisterPresenter(new PreferencesHelper(prefs), new AccountManager(prefs));
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(View v){
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(View v){
        presenter.registerUser(usernameView.getText().toString(), passwordView.getText().toString());
    }

    @Override
    public void goToMainActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
