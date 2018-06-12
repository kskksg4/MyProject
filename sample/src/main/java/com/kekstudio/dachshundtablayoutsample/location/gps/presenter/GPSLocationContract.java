package com.kekstudio.dachshundtablayoutsample.location.gps.presenter;

import android.app.Activity;
import android.content.Context;

/**
 * Created by gimseong-geun on 2018. 6. 11..
 */

public interface GPSLocationContract {

    interface View{

        void showToast(String message);

        void setMyLocation(String message);
    }

    interface Presenter{

        void attachView(View view);

        void detachView();

        void setContext(Context context, Activity activity);

        void GPSServieceSetting();

        void getMyLocation();

        void putLocationExtra();
    }
}
