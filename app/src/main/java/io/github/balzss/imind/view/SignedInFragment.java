package io.github.balzss.imind.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.balzss.imind.R;
import io.github.balzss.imind.presenter.SignedInPresenter;

public class SignedInFragment extends MvpFragment<SignedInView, SignedInPresenter> implements SignedInView {

    @BindView(R.id.hello_textview) TextView helloTextView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.start_service_button) Button startServiceButton;

    public SignedInFragment() {
        // Required empty public constructor
    }

    @Override
    public SignedInPresenter createPresenter() {
        return new SignedInPresenter(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signed_in, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setHello();
    }

    @OnClick(R.id.sign_out_button)
    public void onSignOutButtonCLicked(){
        presenter.signOut();
    }

    @OnClick(R.id.start_service_button)
    public void onStartServiceButtonCLicked(){
        startServiceButton.setEnabled(false);
        presenter.startProgressbarService();
    }

    @Override
    public void showWelcomeText(String username) {
        helloTextView.setText("Hello " + username);
    }

    @Override
    public void showLoginScreen() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
    }

    @Override
    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void finishProgress() {
        progressBar.setProgress(0);
        startServiceButton.setEnabled(true);
    }

    @Override
    public void showRunningWarn() {
        Toast.makeText(getActivity(), "service already running", Toast.LENGTH_SHORT).show();
    }
}
