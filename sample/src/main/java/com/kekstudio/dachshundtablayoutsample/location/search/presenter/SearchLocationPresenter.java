package com.kekstudio.dachshundtablayoutsample.location.search.presenter;

import android.content.Context;

import com.kekstudio.dachshundtablayoutsample.location.search.adapter.SearchAddrAdapter;
import com.kekstudio.dachshundtablayoutsample.location.search.task.SearchAddressHttpTask;

/**
 * Created by gimseong-geun on 2018. 6. 14..
 */

public class SearchLocationPresenter implements SearchLocationContract.Presenter {

    private SearchLocationContract.View view;
    private SearchAddrAdapter adapter;

    @Override
    public void attachView(SearchLocationContract.View view) {
        this.view = view;
        adapter = new SearchAddrAdapter();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void restapiTask(String addr, Context context) {
        new SearchAddressHttpTask(addr, context, adapter).execute();
        view.settingAdapter(adapter);
    }
}
