package com.kekstudio.dachshundtablayoutsample.location.search.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kekstudio.dachshundtablayoutsample.Listener.ItemClickListener;
import com.kekstudio.dachshundtablayoutsample.R;

/**
 * Created by gimseong-geun on 2018. 6. 14..
 */

public class SearchLocationViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView searchLocationRegion;
    public TextView searchLocationRoad;

    private ItemClickListener itemClickListener;

    public SearchLocationViewholder(View itemView) {
        super(itemView);

        searchLocationRegion = (TextView)itemView.findViewById(R.id.search_location_recyclerView_region);
        searchLocationRoad = (TextView)itemView.findViewById(R.id.search_location_recyclerView_road);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
