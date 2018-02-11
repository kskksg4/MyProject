package com.kekstudio.dachshundtablayoutsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 성근 on 2017-05-18.
 */

public class Fragment01 extends Fragment {

    private static final int LOCATION_INTENT = 100;

    Context mContext;

    public Fragment01(){}

//    public Fragment01(Context context){
//        mContext = context;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, null);

        RecyclerView myRecycler = (RecyclerView) view.findViewById(R.id.myRecycler);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2); // ListView를 쓰기위해 LinearLayoutManager를 사용
//        manager.setOrientation(LinearLayoutManager.VERTICAL); // ListView의 orirentation값인가?
        myRecycler.setLayoutManager(manager); // RecyclerView에 붙이는 것이다 ListView로 사용한다는 의미
        myRecycler.setHasFixedSize(true); // RecyclerView의 사이즈를 고정시키는것 같다 http://itpangpang.xyz/31

        final List<String> content = new ArrayList<String>(); // content라는 변수에 리스트형 배열 객체를 생성하고
        for (int i = 0; i < 30; i++)
            content.add(getListString(i));

        // 그리드뷰의 span을 합쳐주거나 2개
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0){
                    return 2;
                }
                return 1;
            }
        });

        Bundle bundle = getArguments();
        if(bundle != null){
            Double lat = bundle.getDouble("bundleLat");
            Double lng = bundle.getDouble("bundleLng");

            Log.d("fragmentresult", lat+""+lng);
        }


        ParallaxRecyclerAdapter<String> stringAdapter = new ParallaxRecyclerAdapter<String>(content);
        stringAdapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter.RecyclerAdapterMethods() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                ((TextView) viewHolder.itemView).setText(content.get(i));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new SimpleViewHolder(getActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_1, viewGroup, false));
            }

            @Override
            public int getItemCount() {
                return content.size();
            }
        });

        stringAdapter.setParallaxHeader(inflater.inflate(R.layout.my_header, myRecycler, false), myRecycler);
        stringAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                //TODO: implement toolbar alpha. See README for details
            }
        });
        myRecycler.setAdapter(stringAdapter);

        return view;
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }

    public String getListString(int position) {
        return position + " - android";
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LOCATION_INTENT){
            if(resultCode == RESULT_OK){
                if(data.hasExtra("lat") && data.hasExtra("lng")){
                    Double lat = data.getDoubleExtra("lat", 0);
                    Double lng = data.getDoubleExtra("lng", 0);
                    Log.d("result", LOCATION_INTENT+"");

                    Toast.makeText(getContext(), lat+"와 "+lng, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
