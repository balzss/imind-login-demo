package io.github.balzss.imind.view;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.balzss.imind.R;
import io.github.balzss.imind.presenter.RegisterPresenter;

public class RegisterFragment extends MvpFragment<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.username_edittext) EditText usernameEditText;
    @BindView(R.id.password_edittext) EditText passwordEditText;
    @BindView(R.id.register_button) Button registerButton;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnTextChanged({R.id.username_edittext, R.id.password_edittext})
    protected void handleTextChange(Editable editable) {
        if(usernameEditText.getText().length() == 0 || passwordEditText.getText().length() == 0){
            registerButton.setEnabled(false);
        } else if(!registerButton.isEnabled()){
            registerButton.setEnabled(true);
        }
    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked(){
        presenter.registerUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.change_to_login_button)
    public void onChangeToLoginButtonClicked(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
    }

    @Override
    public void showSignedInFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SignedInFragment()).addToBackStack(null).commit();
    }
}
