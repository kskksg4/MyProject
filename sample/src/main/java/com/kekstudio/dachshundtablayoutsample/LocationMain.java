package com.kekstudio.dachshundtablayoutsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("내 위치");

        location_main_viewpager = (ViewPager)findViewById(R.id.location_main_viewpager);
        PagerAdapter adapter2 = new PagerAdapter(getSupportFragmentManager());
        adapter2.addFragment(new BottomBarLocation(), "현재 위치");
        adapter2.addFragment(new SearchLocation(), "주소 검색");
        location_main_viewpager.setAdapter(adapter2);

        location_main_tabLayout = (DachshundTabLayout)findViewById(R.id.location_main_tabLayout);
        location_main_tabLayout.setupWithViewPager(location_main_viewpager);

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

}