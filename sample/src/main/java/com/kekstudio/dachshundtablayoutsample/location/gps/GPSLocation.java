package com.kekstudio.dachshundtablayoutsample.location.gps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kekstudio.dachshundtablayoutsample.R;
import com.kekstudio.dachshundtablayoutsample.location.gps.presenter.GPSLocationContract;
import com.kekstudio.dachshundtablayoutsample.location.gps.presenter.GPSLocationPresenter;

/**
 * Created by 성근 on 2017-06-29.
 */

public class GPSLocation extends Fragment implements GPSLocationContract.View{

    private GPSLocationPresenter presenter;

    private LinearLayout address;
    private TextView myLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gps_lcoation, container, false);

        myLocation = (TextView)view.findViewById(R.id.myLocation);
        address = (LinearLayout)view.findViewById(R.id.address);

        presenter = new GPSLocationPresenter();
        presenter.attachView(this);
        presenter.setContext(getContext(), getActivity());
        presenter.GPSServieceSetting();
        presenter.getMyLocation();

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.putLocationExtra();

                getActivity().finish();
            }
        });

        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        return view;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMyLocation(String message) {
        Log.d("abc", message);
        myLocation.setText(message);
    }
}
