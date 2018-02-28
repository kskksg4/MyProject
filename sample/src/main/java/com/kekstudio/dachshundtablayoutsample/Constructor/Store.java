package com.kekstudio.dachshundtablayoutsample.Constructor;

/**
 * Created by gimseong-geun on 2018. 2. 13..
 */

public class Store {
    private String storeName;
    private String address;
    private String lat;
    private String lng;

    public Store(){}

    public Store(String storeName) { // 생성자 임시로 만든거다 반드시 수정 후 지울것
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
