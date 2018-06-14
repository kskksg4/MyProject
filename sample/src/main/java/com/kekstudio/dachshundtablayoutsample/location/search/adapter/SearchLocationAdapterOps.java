package com.kekstudio.dachshundtablayoutsample.location.search.adapter;

import android.content.Context;

import com.kekstudio.dachshundtablayoutsample.Constructor.SearchAddress;

import java.util.ArrayList;

/**
 * Created by gimseong-geun on 2018. 6. 15..
 */

public interface SearchLocationAdapterOps {

    void setData(ArrayList<SearchAddress[]> addrData, Context context, Boolean flag);
}
