package com.kekstudio.dachshundtablayoutsample.Constructor;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gimseong-geun on 2018. 3. 24..
 */

public class RoadAddr {

    @SerializedName("undergroun_yn")
    private String undergroun_yn;
    @SerializedName("road_name")
    private String road_name;
    @SerializedName("underground_yn")
    private String underground_yn;
    @SerializedName("region_2depth_name")
    private String region_2depth_name;
    @SerializedName("zone_no")
    private String zone_no;
    @SerializedName("sub_building_no")
    private String sub_building_no;
    @SerializedName("region_3depth_name")
    private String region_3depth_name;
    @SerializedName("main_building_no")
    private String main_building_no;
    @SerializedName("address_name")
    private String address_name;
    @SerializedName("y")
    private String y;
    @SerializedName("x")
    private String x;
    @SerializedName("region_1depth_name")
    private String region_1depth_name;
    @SerializedName("building_name")
    private String building_name;

    public String getUndergroun_yn() {
        return undergroun_yn;
    }

    public String getRoad_name() {
        return road_name;
    }

    public String getUnderground_yn() {
        return underground_yn;
    }

    public String getRegion_2depth_name() {
        return region_2depth_name;
    }

    public String getZone_no() {
        return zone_no;
    }

    public String getSub_building_no() {
        return sub_building_no;
    }

    public String getRegion_3depth_name() {
        return region_3depth_name;
    }

    public String getMain_building_no() {
        return main_building_no;
    }

    public String getAddress_name() {
        return address_name;
    }

    public String getY() {
        return y;
    }

    public String getX() {
        return x;
    }

    public String getRegion_1depth_name() {
        return region_1depth_name;
    }

    public String getBuilding_name() {
        return building_name;
    }
}
