package io.github.balzss.imind.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.github.balzss.imind.helper.PreferencesHelper;
import io.github.balzss.imind.service.ProgressbarService;
import io.github.balzss.imind.view.SignedInView;

public class SignedInPresenter extends MvpBasePresenter<SignedInView> {

    PreferencesHelper prefHelper;
    Context mContext;
    BroadcastReceiver broadcastReceiver;

    public SignedInPresenter(Context context){
        mContext = context;
        prefHelper = new PreferencesHelper(mContext);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent.getBooleanExtra("running", false)) {
                    if (isViewAttached()) {
                        getView().showRunningWarn();
                    }
                }

                int progress = intent.getIntExtra("p", 0);
                if (isViewAttached()) {
                    getView().setProgress(progress);
                    if(progress == 100){
                        getView().finishProgress();
                    }
                }
            }
        };

        context.registerReceiver(broadcastReceiver, new IntentFilter("io.github.balzss.imind.progress"));
    }

    public void signOut() {
        prefHelper.setLoggedIn(false);
        if (isViewAttached()) {
            getView().showLoginScreen();
        }
    }

    public void setHello(){
        if(isViewAttached()){
            getView().showWelcomeText(prefHelper.getUsername());
        }
    }

    public void startProgressbarService(){
        mContext.startService(new Intent(mContext, ProgressbarService.class));
    }

    // Called when Activity gets destroyed, so cancel running background task
    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
        Log.d("DETACH", "VIEW");
        if (!retainPresenterInstance){
            mContext.unregisterReceiver(broadcastReceiver);
        }
    }
}
