package com.kekstudio.dachshundtablayoutsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayout.HelperUtils;
import com.kekstudio.dachshundtablayout.indicators.DachshundIndicator;
import com.kekstudio.dachshundtablayout.indicators.LineFadeIndicator;
import com.kekstudio.dachshundtablayout.indicators.LineMoveIndicator;
import com.kekstudio.dachshundtablayout.indicators.PointFadeIndicator;
import com.kekstudio.dachshundtablayout.indicators.PointMoveIndicator;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;


public class SampleActivity extends AppCompatActivity {

    private static final int LOCATION_INTENT = 1;

    private ViewPager viewPager;
    private DachshundTabLayout tabLayout;
    private PagerAdapter adapter;

    int oncreateBottomSelect = 0;

    Fragment f01 = new Fragment01();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        BottomBar bottomBar = (BottomBar)findViewById(R.id.bottomBar);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("애니몰리");

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(f01, "Category01");
        adapter.addFragment(new Fragment_page(), "Category02");
        adapter.addFragment(new Fragment02(), "Category03");
        viewPager.setAdapter(adapter);
        // 이게 없으면 2개 이상의 탭으로 넘어갔다올 때 메인 이미지가 로드되지않는다
        viewPager.setOffscreenPageLimit(2);

        tabLayout = (DachshundTabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // bottomBar 클릭 이벤트
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch(tabId){
                    case R.id.tab_nearby:
                        if(oncreateBottomSelect == 0){
                            oncreateBottomSelect = 1;
                            break;
                        }
                        bottombarLocationClick();
                        break;
                    case R.id.tab_account:
                        Toast.makeText(getApplicationContext(), "b", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
            }
        });

        // bottomBar reselect 클릭 이벤트
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                switch(tabId){
                    case R.id.tab_nearby:
                        bottombarLocationClick();
                        break;
                    case R.id.tab_account:
                        Toast.makeText(getApplicationContext(), "b", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
            }
        });

    }

    // 액션바 Search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.actionbar_custom, null);

        actionBar.setCustomView(actionBarView);

        Toolbar parent = (Toolbar)actionBarView.getParent();
        parent.setContentInsetsAbsolute(50, 50);
//        inflater.inflate(R.menu.actionbar_menu, menu);

        return true;
    }

    // 액션바 Search 클릭
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.actionbar_search:
//                actionBarSearchIntent();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    public void onClickDachshund(View view){
        tabLayout.setAnimatedIndicator(new DachshundIndicator(tabLayout));
    }

    public void onClickPointMove(View view){
        tabLayout.setAnimatedIndicator(new PointMoveIndicator(tabLayout));
    }

    public void onClickPointMoveAccelerate(View view){
        PointMoveIndicator pointMoveIndicator = new PointMoveIndicator(tabLayout);
        pointMoveIndicator.setInterpolator(new AccelerateInterpolator());
        tabLayout.setAnimatedIndicator(pointMoveIndicator);
    }

    public void onClickLineMove(View view){
        tabLayout.setAnimatedIndicator(new LineMoveIndicator(tabLayout));
    }

    public void onClickPointFade(View view){
        tabLayout.setAnimatedIndicator(new PointFadeIndicator(tabLayout));
    }

    public void onClickLineFade(View view){
        LineFadeIndicator lineFadeIndicator = new LineFadeIndicator(tabLayout);
        tabLayout.setAnimatedIndicator(lineFadeIndicator);

        lineFadeIndicator.setSelectedTabIndicatorHeight(HelperUtils.dpToPx(2));
        lineFadeIndicator.setEdgeRadius(0);
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
//        Context mContext;

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    public void actionBarSearchIntent(View v){
        Intent intent = new Intent(this, actionbarSearchIntent.class);
        startActivity(intent);
    }

    public void bottombarLocationClick(){
        Intent intent = new Intent(SampleActivity.this, LocationMain.class);
        startActivityForResult(intent, LOCATION_INTENT);
    }

    public void actionbar_bottombarLocationClick(View v){
        Intent intent = new Intent(SampleActivity.this, LocationMain.class);
        startActivityForResult(intent, LOCATION_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LOCATION_INTENT){
            if(resultCode == RESULT_OK){
                if(data.hasExtra("arg2")){
                    String arg2 = data.getStringExtra("arg2");
                    ((TextView)findViewById(R.id.main_address_text)).setText(arg2);
                }else if(data.hasExtra("locationThoroughfareAddress")){
                    String locationThoroughfareAddress = data.getStringExtra("locationThoroughfareAddress");
                    ((TextView)findViewById(R.id.main_address_text)).setText(locationThoroughfareAddress);
                    if(data.hasExtra("lat") && data.hasExtra("lng")){
                        callFragmentResult(requestCode, resultCode, data);
                    }else{
                        Toast.makeText(getApplicationContext(), "정확한 위치 정보를 찾을 수 없습니다", Toast.LENGTH_SHORT).show(); // Toast를 Alert으로 바꾸기(환경설정 확인 등)
                    }

                }
            }
        }
    }

    private void callFragmentResult(int requestCode, int resultCode, Intent data){
        Double lat = data.getDoubleExtra("lat", 0);
        Double lng = data.getDoubleExtra("lng", 0);
        Log.d("latlng", lat+", "+lng);

        Toast.makeText(getApplicationContext(), lat+", "+lng, Toast.LENGTH_SHORT).show();
        int request = 100;

        adapter.getItem(0).onActivityResult(request, resultCode, data);

    }

}
