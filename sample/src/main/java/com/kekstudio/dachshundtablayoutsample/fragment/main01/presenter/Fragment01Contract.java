package com.kekstudio.dachshundtablayoutsample.fragment.main01.presenter;

import android.content.Context;

import com.kekstudio.dachshundtablayoutsample.Constructor.Store;

/**
 * Created by gimseong-geun on 2018. 6. 6..
 */

public interface Fragment01Contract {

    interface View{

        void showToast(String message);
    }

    interface Presenter{

        void attachView(View view);

        void detachView();

        Store getListString(int position);

        void httpTask(double lat, double lng, Context context);
    }
}
