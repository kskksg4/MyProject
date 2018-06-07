package com.kekstudio.dachshundtablayoutsample.fragment.main01.presenter;

import android.content.Context;
import android.view.View;

import com.kekstudio.dachshundtablayoutsample.Constructor.Store;
import com.kekstudio.dachshundtablayoutsample.Listener.ItemClickListener;
import com.kekstudio.dachshundtablayoutsample.fragment.main01.Fragment01;
import com.kekstudio.dachshundtablayoutsample.fragment.main01.task.HttpTask;
import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by gimseong-geun on 2018. 6. 6..
 */

public class Fragment01Presenter implements Fragment01Contract.Presenter, ParallaxRecyclerAdapter.OnClickEvent {

    private Fragment01Contract.View mView;

    @Override
    public void attachView(Fragment01Contract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public Store getListString(int position) {
        return new Store(position + " - store");
    }

    @Override
    public void httpTask(double lat, double lng, Context context) {
        new HttpTask(lat, lng, context).execute();
    }

    @Override
    public void onClick(View view, int position) {
        mView.showToast(Fragment01.content.get(position).getStoreName());
    }
}
