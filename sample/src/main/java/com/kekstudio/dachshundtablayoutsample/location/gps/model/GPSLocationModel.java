package com.kekstudio.dachshundtablayoutsample.location.gps.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.kekstudio.dachshundtablayoutsample.GPS.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by gimseong-geun on 2018. 6. 12..
 */

public class GPSLocationModel implements GPSLocationModelOps{

    private final int RENEWS_GPS = 1;
//    private final int SEND_PRINT = 2;

    private Context context;
    private Activity activity;

    private GPSTracker gps = null;
    private Handler mHandler;
    private Geocoder geocoder;

    private String locationThoroughfareAddress;
    private String locationFeatureNameAddress;
    private String locationThFeAddress;

    private double lat;
    private double lng;

    public GPSLocationModel(){}

    public GPSLocationModel(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void constructorSet() {
        geocoder = new Geocoder(context, Locale.KOREA);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == RENEWS_GPS){
                    makeNewGpsService();
                }

//                if(msg.what == SEND_PRINT){
//
//                }
            }
        };
        makeNewGpsService();

    }

    @Override
    public String getLocation() {
        if(gps.canGetLocation()){
            lat = gps.getLatitude();
            lng = gps.getLongitude();
        }else{
            gps.showSettingsAlert();
        }
        return returnAddress(lat, lng);
    }

    @Override
    public void putIntent() {
        Intent intent = activity.getIntent();
        intent.putExtra("locationThoroughfareAddress", locationThoroughfareAddress);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        activity.setResult(Activity.RESULT_OK, intent);
    }

    private void makeNewGpsService(){
        if(gps == null){
            gps = new GPSTracker(context, mHandler);
        }else{
            gps.update();
        }
    }

    private String returnAddress(double lat, double lng) {
        List<Address> addresses = null;

        try{
            addresses = geocoder.getFromLocation(lat, lng, 1);

            if(addresses != null && addresses.size() > 0){
//                currentLocationAddress = addresses.get(0).getAddressLine(0); // 주소 전체(대한민국 서울특별시 00구 00동 00-00)
                Address currentLocationAddress = addresses.get(0);
                locationThoroughfareAddress = currentLocationAddress.getThoroughfare();
                locationFeatureNameAddress = currentLocationAddress.getFeatureName();
                locationThFeAddress = locationThoroughfareAddress+" "+locationFeatureNameAddress;

            }else{
                locationThFeAddress = "해당하는 주소 정보가 없어요";

                return locationThFeAddress;
            }

        }catch(IOException e){
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }

        return locationThFeAddress;
    }
}
