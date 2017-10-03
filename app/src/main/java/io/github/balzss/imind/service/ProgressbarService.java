package io.github.balzss.imind.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProgressbarService extends Service {

    private boolean mRunning;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mRunning = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!mRunning) {
            mRunning = true;

            new Thread(new Runnable() {
                public void run() {

                    for (int i = 1; i <= 20; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        updateProgress(i * 5);
                    }
                    mRunning = false;
                }
            }).start();
        } else {
            alreadyRunning();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateProgress(int progress) {
        Intent intent = new Intent();
        intent.setAction("io.github.balzss.imind.progress");
        intent.putExtra("p", progress);
        sendBroadcast(intent);
    }

    private void alreadyRunning() {
        Intent intent = new Intent();
        intent.setAction("io.github.balzss.imind.progress");
        intent.putExtra("running", true);
        sendBroadcast(intent);
    }
}