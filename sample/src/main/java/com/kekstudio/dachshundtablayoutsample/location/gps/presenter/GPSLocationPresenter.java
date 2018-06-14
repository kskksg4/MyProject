package com.kekstudio.dachshundtablayoutsample.location.gps.presenter;

import android.app.Activity;
import android.content.Context;

import com.kekstudio.dachshundtablayoutsample.location.gps.model.GPSLocationModel;

/**
 * Created by gimseong-geun on 2018. 6. 11..
 */

public class GPSLocationPresenter implements GPSLocationContract.Presenter {

    private GPSLocationContract.View view;
    private GPSLocationModel model;

    @Override
    public void attachView(GPSLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void setContext(Context context, Activity activity) {
        model = new GPSLocationModel(context, activity);
    }

    @Override
    public void GPSServieceSetting() {
        model.constructorSet();
    }

    @Override
    public void getMyLocation() {
        String locationThFeAddress = model.getLocation();
        view.setMyLocation(locationThFeAddress);
    }

    @Override
    public void putLocationExtra() {
        model.putIntent();
    }
}
