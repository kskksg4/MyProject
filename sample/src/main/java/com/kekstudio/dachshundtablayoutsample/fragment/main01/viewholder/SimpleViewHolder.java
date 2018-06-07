package com.kekstudio.dachshundtablayoutsample.fragment.main01.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kekstudio.dachshundtablayoutsample.R;

/**
 * Created by gimseong-geun on 2018. 6. 5..
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    public TextView storeName;

    public SimpleViewHolder(View itemView) {
        super(itemView);

        storeName = (TextView)itemView.findViewById(R.id.store_recyclerview_store_name);

    }
}
