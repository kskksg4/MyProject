package com.kekstudio.dachshundtablayoutsample.Constructor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gimseong-geun on 2018. 3. 21..
 */

public class MetaAddr {

    @SerializedName("is_end")
    private Boolean is_end;
    @SerializedName("total_count")
    private int total_count;
    @SerializedName("pageable_count")
    private int pageable_count;

    public Boolean getIs_end() {
        return is_end;
    }

    public int getTotal_count() {
        return total_count;
    }

    public int getPageable_count() {
        return pageable_count;
    }

}

