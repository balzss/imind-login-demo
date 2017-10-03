package io.github.balzss.imind.view;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.balzss.imind.R;
import io.github.balzss.imind.presenter.LoginPresenter;

public class LoginFragment extends MvpFragment<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.hint_textview) TextView hintTextView;
    @BindView(R.id.username_edittext) EditText usernameEditText;
    @BindView(R.id.password_edittext) EditText passwordEditText;
    @BindView(R.id.login_button) Button loginButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setHintText();
    }

    @OnTextChanged({R.id.username_edittext, R.id.password_edittext})
    protected void handleTextChange(Editable editable) {
        if(usernameEditText.getText().length() == 0 || passwordEditText.getText().length() == 0){
            loginButton.setEnabled(false);
        } else if(!loginButton.isEnabled()){
            loginButton.setEnabled(true);
        }
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked(){
        presenter.authUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.change_to_register_button)
    public void onChangeToRegisterButtonClicked(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegisterFragment()).addToBackStack(null).commit();
    }


    @Override
    public void showLoginError(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHint(String hintText) {
        hintTextView.setText(hintText);
    }

    @Override
    public void showSignedInFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SignedInFragment()).addToBackStack(null).commit();
    }
}
