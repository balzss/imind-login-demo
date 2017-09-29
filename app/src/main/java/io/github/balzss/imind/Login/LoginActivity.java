package io.github.balzss.imind.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.OnClick;
import io.github.balzss.imind.AccountManager;
import io.github.balzss.imind.MainActivity;
import io.github.balzss.imind.PreferencesHelper;
import io.github.balzss.imind.R;
import io.github.balzss.imind.Register.RegisterActivity;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    EditText usernameView;
    EditText passwordView;
    TextView hintText;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_login);

        usernameView = (EditText)findViewById(R.id.username_text);
        passwordView = (EditText)findViewById(R.id.password_text);
        hintText = (TextView)findViewById(R.id.hint_text);

        presenter.setHintText();
    }

    @Override // Called internally by Mosby
    public LoginPresenter createPresenter(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return new LoginPresenter(new PreferencesHelper(prefs), new AccountManager(prefs));
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(View v){
        presenter.validateUser(usernameView.getText().toString(), passwordView.getText().toString());
    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(View v){
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
