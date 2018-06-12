package com.kekstudio.dachshundtablayoutsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayoutsample.location.gps.GPSLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 성근 on 2017-08-21.
 */

public class LocationMain extends AppCompatActivity {

    private ViewPager location_main_viewpager;
    private DachshundTabLayout location_main_tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_main);

        setSupportActionBar((Toolbar)findViewById(R.id.location_main_toolbar));

        location_main_viewpager = (ViewPager)findViewById(R.id.location_main_viewpager);
        PagerAdapter adapter2 = new PagerAdapter(getSupportFragmentManager());
        adapter2.addFragment(new GPSLocation(), "현재 위치");
        adapter2.addFragment(new SearchLocation(), "주소 검색");
        location_main_viewpager.setAdapter(adapter2);

        location_main_tabLayout = (DachshundTabLayout)findViewById(R.id.location_main_tabLayout);
        location_main_tabLayout.setupWithViewPager(location_main_viewpager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.actionbar_location_main_custom, null);

        actionBar.setCustomView(actionBarView);

        return true;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

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
