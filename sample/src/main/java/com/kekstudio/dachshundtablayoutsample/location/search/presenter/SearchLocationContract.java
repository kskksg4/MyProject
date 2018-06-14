package com.kekstudio.dachshundtablayoutsample.location.search.presenter;

import android.content.Context;

import com.kekstudio.dachshundtablayoutsample.location.search.adapter.SearchAddrAdapter;

/**
 * Created by gimseong-geun on 2018. 6. 14..
 */

public interface SearchLocationContract {

    interface View{

        void showToast(String message);

        void settingAdapter(SearchAddrAdapter adapter);
    }

    interface Presenter{

        void attachView(View view);

        void detachView();

        void restapiTask(String addr, Context context);
    }
}
