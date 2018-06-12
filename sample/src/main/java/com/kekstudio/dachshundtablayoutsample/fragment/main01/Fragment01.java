package com.kekstudio.dachshundtablayoutsample.fragment.main01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kekstudio.dachshundtablayoutsample.Constructor.Store;
import com.kekstudio.dachshundtablayoutsample.R;
import com.kekstudio.dachshundtablayoutsample.fragment.main01.presenter.Fragment01Contract;
import com.kekstudio.dachshundtablayoutsample.fragment.main01.presenter.Fragment01Presenter;
import com.kekstudio.dachshundtablayoutsample.fragment.main01.viewholder.SimpleViewHolder;
import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;
import java.util.ArrayList;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.FamiliarRefreshRecyclerView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 성근 on 2017-05-18.
 */

public class Fragment01 extends Fragment implements Fragment01Contract.View{

    private static final int LOCATION_INTENT = 100;

    @SuppressLint("StaticFieldLeak")
    public static ParallaxRecyclerAdapter<Store> adapter;
    public static ArrayList<Store> content;

    /*
    * 스크롤 처리 관련 변수
    **/
    float startYPosition = 0; //기본적으로 스크롤은 Y축을 기준으로 계산.//
    float endYPosition = 0;
    boolean firstDragFlag = true;
    boolean motionFlag = true;
    boolean dragFlag = false; //현재 터치가 드래그인지 먼저 확인//

    private FamiliarRecyclerView recyclerView;

    private Fragment01Presenter presenter;

    public Fragment01(){}

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment01, null);

        presenter = new Fragment01Presenter();
        presenter.attachView(this);

        final FamiliarRefreshRecyclerView refresh = (FamiliarRefreshRecyclerView) view.findViewById(R.id.myRecycler);
        final FloatingActionButton topupButton = (FloatingActionButton)view.findViewById(R.id.fragment01_topbutton);

        topupButton.setVisibility(View.GONE);

        recyclerView = refresh.getFamiliarRecyclerView();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2); // ListView를 쓰기위해 LinearLayoutManager를 사용
        recyclerView.setLayoutManager(manager); // RecyclerView에 붙이는 것이다 ListView로 사용한다는 의미
        recyclerView.setHasFixedSize(true); // RecyclerView의 사이즈를 고정시키는것 같다 http://itpangpang.xyz/31

        content = new ArrayList<Store>(); // content라는 변수에 리스트형 배열 객체를 생성하고
        for (int i = 0; i < 30; i++)
            content.add(presenter.getListString(i));

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

        refresh.setOnPullRefreshListener(new FamiliarRefreshRecyclerView.OnPullRefreshListener() {
            @Override
            public void onPullRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "리프레쉬, 초기화부분을 프리젠터에 구현", Toast.LENGTH_SHORT).show();

                        refresh.pullRefreshComplete();
                    }
                }, 1000);
            }
        });

        topupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);

                topupButton.setVisibility(View.GONE);
            }
        });

        adapter = new ParallaxRecyclerAdapter<>(content);
        adapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter.RecyclerAdapterMethods() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

                final SimpleViewHolder simpleViewHolder = (SimpleViewHolder) viewHolder;

                simpleViewHolder.storeName.setText(content.get(i).getStoreName());

            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new SimpleViewHolder(getActivity().getLayoutInflater().inflate(R.layout.store_recyclerview, viewGroup, false));
            }

            @Override
            public int getItemCount() {
                return content.size();
            }
        });

        adapter.setParallaxHeader(inflater.inflate(R.layout.my_header, refresh, false), recyclerView);
        adapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                //TODO: implement toolbar alpha. See README for details
            }
        });
        adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                presenter.onClick(view, i);
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        dragFlag = true;

                        if (firstDragFlag) //첫번째 움직임을 가지고 판단하기 위해서
                        {
                            startYPosition = event.getY();
                            firstDragFlag = false;
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        endYPosition = event.getY();
                        firstDragFlag = true;

                        if(dragFlag){
                            // 시작Y가 끝 Y보다 크다면 터치가 아래서 위로 이루어졌다는 것이고, 스크롤은 아래로내려갔다는 뜻이다.
                            if ((startYPosition > endYPosition) && (startYPosition - endYPosition) > 10) {
                                topupButton.setVisibility(View.VISIBLE);
                            }else if((startYPosition < endYPosition) && (endYPosition - startYPosition) > 10){ //시작 Y가 끝 보다 작다면 터치가 위에서 아래로 이루어졌다는 것이고, 스크롤이 올라갔다는 뜻이다.
                                topupButton.setVisibility(View.GONE);
                            }
                        }

                        //다시 Y축에 대한 위치를 초기화
                        startYPosition = 0.0f;
                        endYPosition = 0.0f;
                        motionFlag = false;

                        break;
                }

                return false;
            }
        });

        refresh.setAdapter(adapter);

        return view;
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
                    presenter.httpTask(lat, lng, getContext());
                }
            }
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
