package com.kekstudio.dachshundtablayoutsample.Constructor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gimseong-geun on 2018. 3. 23..
 */

public class SearchAddress {

    @SerializedName("road_address")
    private RoadAddr road_address;
    @SerializedName("address_name")
    private String address_name;
    @SerializedName("address")
    private RegionAddr address;
    @SerializedName("y")
    private String y;
    @SerializedName("x")
    private String x;
    @SerializedName("address_type")
    private String address_type;

    public RoadAddr getRoad_address() {
        return road_address;
    }

    public String getAddress_name() {
        return address_name;
    }

    public RegionAddr getAddress() {
        return address;
    }

    public String getY() {
        return y;
    }

    public String getX() {
        return x;
    }

    public String getAddress_type() {
        return address_type;
    }
}
