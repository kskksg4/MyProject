package com.kekstudio.dachshundtablayoutsample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.kekstudio.dachshundtablayoutsample.HttpTask.SearchAddressHttpTask;
import com.kekstudio.dachshundtablayoutsample.Listener.ItemClickListener;

public class SearchLocation extends Fragment {

    View view;

    LinearLayoutManager searchLocationRecyclerLayoutManager;
    public static RecyclerView searchLocationRecyclerView;

    EditText searchLocationEdittext;

    SearchAddressHttpTask searchAddressHttpTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_location, null);

        final InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        searchLocationRecyclerLayoutManager = new LinearLayoutManager(this.getContext());
        searchLocationRecyclerView = (RecyclerView) view.findViewById(R.id.search_location_recyclerView);
        searchLocationRecyclerView.setLayoutManager(searchLocationRecyclerLayoutManager);

        searchLocationEdittext = (EditText)view.findViewById(R.id.search_location_edittext);
        searchLocationEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    imm.hideSoftInputFromWindow(searchLocationEdittext.getWindowToken(), 0); // 키보드 돋보기 클릭시 키보드 "hide"

                    searchAddressHttpTask = (SearchAddressHttpTask) new SearchAddressHttpTask(v.getText().toString(), getContext()).execute();
                }

                return false;
            }
        });

        return view;
    }

    public static class SearchLocationViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

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

    @Override
    public void onDestroy() {
        super.onDestroy();

//        searchAddressHttpTask.cancel(true);
    }
}
