package service.presenter;

import android.content.Intent;

import service.view.View;

/**
 * Created by Administrator on 2018/6/25 0025.
 */

public interface Presenter {

    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);

}
