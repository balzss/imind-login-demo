package io.github.balzss.imind.Register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.balzss.imind.AccountManager;
import io.github.balzss.imind.Login.LoginActivity;
import io.github.balzss.imind.MainActivity;
import io.github.balzss.imind.PreferencesHelper;
import io.github.balzss.imind.R;

public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.register_button) Button registerButton;
    @BindView(R.id.username_text) EditText usernameView;
    @BindView(R.id.password_text) EditText passwordView;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

    }

    @OnTextChanged({R.id.username_text, R.id.password_text})
    protected void handleTextChange(Editable editable) {
        if(usernameView.getText().length() == 0 || passwordView.getText().length() == 0){
            registerButton.setEnabled(false);
        } else if(!registerButton.isEnabled()){
            registerButton.setEnabled(true);
        }
    }

    @Override // Called internally by Mosby
    public RegisterPresenter createPresenter(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return new RegisterPresenter(new PreferencesHelper(prefs), new AccountManager(prefs));
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(){
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(){
        presenter.registerUser(usernameView.getText().toString(), passwordView.getText().toString());
    }

    @Override
    public void goToMainActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
