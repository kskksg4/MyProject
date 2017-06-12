package com.kekstudio.dachshundtablayoutsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayout.HelperUtils;
import com.kekstudio.dachshundtablayout.indicators.DachshundIndicator;
import com.kekstudio.dachshundtablayout.indicators.LineFadeIndicator;
import com.kekstudio.dachshundtablayout.indicators.LineMoveIndicator;
import com.kekstudio.dachshundtablayout.indicators.PointFadeIndicator;
import com.kekstudio.dachshundtablayout.indicators.PointMoveIndicator;

import java.util.ArrayList;
import java.util.List;


public class SampleActivity extends AppCompatActivity {



    private ViewPager viewPager;
    private DachshundTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment01(), "Category01");
        adapter.addFragment(new Fragment_page(), "Category02");
        adapter.addFragment(new Fragment02(), "Category03");
        viewPager.setAdapter(adapter);

        tabLayout = (DachshundTabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

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
        Context mContext;


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


}
