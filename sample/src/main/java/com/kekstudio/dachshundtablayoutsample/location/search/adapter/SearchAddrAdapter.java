package com.kekstudio.dachshundtablayoutsample.location.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kekstudio.dachshundtablayoutsample.Constructor.SearchAddress;
import com.kekstudio.dachshundtablayoutsample.Listener.ItemClickListener;
import com.kekstudio.dachshundtablayoutsample.R;
import com.kekstudio.dachshundtablayoutsample.location.search.holder.SearchLocationViewholder;


import java.util.ArrayList;

/**
 * Created by gimseong-geun on 2018. 3. 25..
 */

public class SearchAddrAdapter extends RecyclerView.Adapter implements SearchLocationAdapterOps {

    private Context context;

    private SearchLocationViewholder searchLocationViewholder;

    private ArrayList<SearchAddress[]> addrData;
    private SearchAddress[] searchAddresses;

    private Boolean nullCheckFlag;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_location_recyclerview, parent, false);

        return new SearchLocationViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        searchLocationViewholder = (SearchLocationViewholder)holder;

        divisionAddr(position);
        searchLocationViewholder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(!nullCheckFlag){
                    return;
                }else{
                    String addrType = searchAddresses[position].getAddress_type();

                    checkRegionRoad(addrType, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return addrData != null ? addrData.size() : 0;
    }

    @Override
    public void setData(ArrayList<SearchAddress[]> addrData, Context context, Boolean flag) {
        this.addrData = addrData;
        this.context = context;
        this.nullCheckFlag = flag;
        this.notifyDataSetChanged();
    }

    private void divisionAddr(int position){
        for(int i=0; i<addrData.size(); i++) {

            searchAddresses = addrData.get(i);

            if(searchAddresses == null){
                searchLocationViewholder.searchLocationRegion.setText("정확한 주소를 입력해주세요");
                searchLocationViewholder.searchLocationRoad.setText("예: 디투유동 12-3, 디투유로 123");
            }else{
                if(searchAddresses[position].getAddress() != null && searchAddresses[position].getRoad_address() != null){
                    searchLocationViewholder.searchLocationRegion.setText(searchAddresses[position].getAddress().getAddress_name());
                    searchLocationViewholder.searchLocationRoad.setText("[도로명주소] " + searchAddresses[position].getRoad_address().getAddress_name());
                }else if(searchAddresses[position].getAddress() != null && searchAddresses[position].getRoad_address() == null ){
                    searchLocationViewholder.searchLocationRegion.setText(searchAddresses[position].getAddress().getAddress_name());
                    searchLocationViewholder.searchLocationRoad.setText("");
                }else if(searchAddresses[position].getAddress() == null && searchAddresses[position].getRoad_address() != null){
                    searchLocationViewholder.searchLocationRegion.setText(searchAddresses[position].getRoad_address().getAddress_name());
                    searchLocationViewholder.searchLocationRoad.setText("");
                }
            }

        }
    }

    private void checkRegionRoad(String addrType, int position){
        String locationThoroughfareAddress;
        Double lat, lng;

        lat = Double.parseDouble(searchAddresses[position].getY());
        lng = Double.parseDouble(searchAddresses[position].getX());

        if((addrType.equals("ROAD_ADDR")) || (addrType.equals("ROAD"))){
            locationThoroughfareAddress = searchAddresses[position].getRoad_address().getRoad_name();
            putExtraLatLng(locationThoroughfareAddress, lat, lng);

            ((Activity)context).finish();
        }else if((addrType.equals("REGION_ADDR")) || (addrType.equals("REGION"))){
            locationThoroughfareAddress = searchAddresses[position].getAddress().getRegion_3depth_name();
            putExtraLatLng(locationThoroughfareAddress, lat, lng);

            ((Activity)context).finish();
        }
    }

    private void putExtraLatLng(String location, Double lat, Double lng){
        Intent intent = ((Activity)context).getIntent();

        intent.putExtra("locationThoroughfareAddress", location);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        ((Activity)context).setResult(Activity.RESULT_OK, intent);
    }

}
