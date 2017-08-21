package com.kekstudio.dachshundtablayoutsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by 성근 on 2017-06-29.
 */

public class BottomBarLocation extends AppCompatActivity {

    GPSTracker gps = null;

    public Handler mHandler;

    final Geocoder geocoder = new Geocoder(this, Locale.KOREA);

    public static int RENEWS_GPS = 1;
    public static int SEND_PRINT = 2;

    double latitude, longitude;

    LinearLayout address;
    TextView myLocation;
    String locationThoroughfareAddress, locationFeatureNameAddress, locationThFeAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottombar_location);

        address = (LinearLayout)findViewById(R.id.address);
        myLocation = (TextView)findViewById(R.id.myLocation);

        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == RENEWS_GPS){
                    makeNewGpsService();
                }

                if(msg.what == SEND_PRINT){
                    Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_SHORT).show();
                }
            }
        };

        if(gps == null){
            gps = new GPSTracker(BottomBarLocation.this, mHandler);
        }else{
            gps.update();
        }

        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            myLocation.setText(returnAddress(latitude, longitude));
        }else{
            gps.showSettingsAlert();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // remove location, intent로 값 넘겨주기
    }

    public void makeNewGpsService(){
        if(gps == null){
            gps = new GPSTracker(BottomBarLocation.this, mHandler);
        }else{
            gps.update();
        }
    }

    public String returnAddress(double lat, double lon){
        List<Address> addresses = null;

        try{
            addresses = geocoder.getFromLocation(lat, lon, 1);

            if(addresses != null && addresses.size() > 0){
//                currentLocationAddress = addresses.get(0).getAddressLine(0); // 주소 전체(대한민국 서울특별시 00구 00동 00-00)
                Address currentLocationAddress = addresses.get(0);
                locationThoroughfareAddress = currentLocationAddress.getThoroughfare();
                locationFeatureNameAddress = currentLocationAddress.getFeatureName();
                locationThFeAddress = locationThoroughfareAddress+" "+locationFeatureNameAddress;

            }

        }catch(IOException e){
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }

        if(addresses == null){
            myLocation.setText("해당하는 주소 정보가 없어요");
        }

        return locationThFeAddress;
    }
}
