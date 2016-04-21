package com.tongcent.guke.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.tongcent.guke.R;
import com.tongcent.guke.adapter.PagerAdapter;
import com.tongcent.guke.fragment.ClickFragment;
import com.tongcent.guke.fragment.MeFragment;
import com.tongcent.guke.fragment.SayFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager vp_main;
    private List<Fragment> mFragments;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vp_main = (ViewPager) findViewById(R.id.vp_main);
        mFragments = new ArrayList<>();
        mFragments.add(new SayFragment());
        mFragments.add(new ClickFragment());
        mFragments.add(new MeFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(), mFragments);
        vp_main.setAdapter(mAdapter);
    }


}
