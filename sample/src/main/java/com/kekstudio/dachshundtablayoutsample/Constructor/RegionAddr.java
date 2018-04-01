package com.kekstudio.dachshundtablayoutsample.Constructor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gimseong-geun on 2018. 3. 17..
 */

public class RegionAddr {

    @SerializedName("b_code")
    private String b_code;
    @SerializedName("region_3depth_h_name")
    private String region_3depth_h_name;
    @SerializedName("main_address_no")
    private String main_address_no;
    @SerializedName("h_code")
    private String h_code;
    @SerializedName("region_2depth_name")
    private String region_2depth_name;
    @SerializedName("main_adderss_no")
    private String main_adderss_no;
    @SerializedName("sub_address_no")
    private String sub_address_no;
    @SerializedName("region_3depth_name")
    private String region_3depth_name;
    @SerializedName("address_name")
    private String address_name;
    @SerializedName("y")
    private String y; // lat
    @SerializedName("x")
    private String x; // lng
    @SerializedName("mountain_yn")
    private String mountain_yn;
    @SerializedName("zip_code")
    private String zip_code;
    @SerializedName("region_1depth_name")
    private String region_1depth_name;
    @SerializedName("sub_adderss_no")
    private String sub_adderss_no;


    public String getB_code() {
        return b_code;
    }

    public void setB_code(String b_code) {
        this.b_code = b_code;
    }

    public String getRegion_3depth_h_name() {
        return region_3depth_h_name;
    }

    public void setRegion_3depth_h_name(String region_3depth_h_name) {
        this.region_3depth_h_name = region_3depth_h_name;
    }

    public String getMain_address_no() {
        return main_address_no;
    }

    public void setMain_address_no(String main_address_no) {
        this.main_address_no = main_address_no;
    }

    public String getH_code() {
        return h_code;
    }

    public void setH_code(String h_code) {
        this.h_code = h_code;
    }

    public String getRegion_2depth_name() {
        return region_2depth_name;
    }

    public void setRegion_2depth_name(String region_2depth_name) {
        this.region_2depth_name = region_2depth_name;
    }

    public String getMain_adderss_no() {
        return main_adderss_no;
    }

    public void setMain_adderss_no(String main_adderss_no) {
        this.main_adderss_no = main_adderss_no;
    }

    public String getSub_address_no() {
        return sub_address_no;
    }

    public void setSub_address_no(String sub_address_no) {
        this.sub_address_no = sub_address_no;
    }

    public String getRegion_3depth_name() {
        return region_3depth_name;
    }

    public void setRegion_3depth_name(String region_3depth_name) {
        this.region_3depth_name = region_3depth_name;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getMountain_yn() {
        return mountain_yn;
    }

    public void setMountain_yn(String mountain_yn) {
        this.mountain_yn = mountain_yn;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getRegion_1depth_name() {
        return region_1depth_name;
    }

    public void setRegion_1depth_name(String region_1depth_name) {
        this.region_1depth_name = region_1depth_name;
    }

    public String getSub_adderss_no() {
        return sub_adderss_no;
    }

    public void setSub_adderss_no(String sub_adderss_no) {
        this.sub_adderss_no = sub_adderss_no;
    }
}
