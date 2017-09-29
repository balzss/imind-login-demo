package io.github.balzss.imind.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.balzss.imind.AccountManager;
import io.github.balzss.imind.MainActivity;
import io.github.balzss.imind.PreferencesHelper;
import io.github.balzss.imind.R;
import io.github.balzss.imind.Register.RegisterActivity;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.username_text) EditText usernameView;
    @BindView(R.id.password_text) EditText passwordView;
    @BindView(R.id.hint_text) TextView hintText;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter.setHintText();
    }

    @OnTextChanged({R.id.username_text, R.id.password_text})
    protected void handleTextChange(Editable editable) {
        if(usernameView.getText().length() == 0 || passwordView.getText().length() == 0){
            loginButton.setEnabled(false);
        } else if(!loginButton.isEnabled()){
            loginButton.setEnabled(true);
        }
    }

    @Override // Called internally by Mosby
    public LoginPresenter createPresenter(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return new LoginPresenter(new PreferencesHelper(prefs), new AccountManager(prefs));
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(){
        presenter.validateUser(usernameView.getText().toString(), passwordView.getText().toString());
    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(){
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void goToMainActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void showLoginError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHintText(String hint){
        hintText.setText(hint);
    }
}
