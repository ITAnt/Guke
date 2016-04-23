package com.tongcent.guke.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.tongcent.guke.R;
import com.tongcent.guke.fragment.LoginFragment;
import com.tongcent.guke.fragment.RegisterFragment;
import com.tongcent.guke.view.JazzyViewPager;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2016/4/22.
 */
public class LoginOrRegisterActivity extends FragmentActivity {
    private JazzyViewPager jvp_test;
    private List<Fragment> mFragments;

    private LinePageIndicator lpi_login_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);



        jvp_test = (JazzyViewPager) findViewById(R.id.jvp_test);
        jvp_test.setTransitionEffect(JazzyViewPager.TransitionEffect.FlipHorizontal);
        jvp_test.setPageMargin(30);

        mFragments = new ArrayList<>();
        mFragments.add(new LoginFragment());
        mFragments.add(new RegisterFragment());
        CustomedAdapter adapter = new CustomedAdapter(getSupportFragmentManager(), mFragments);
        jvp_test.setAdapter(adapter);

        lpi_login_register = (LinePageIndicator) findViewById(R.id.lpi_login_register);
        lpi_login_register.setViewPager(jvp_test);

    }

    private class CustomedAdapter extends FragmentPagerAdapter {

        public CustomedAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            // TODO Auto-generated constructor stub
            this.fragments = fragments;
        }

        private List<Fragment> fragments;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            jvp_test.setObjectForPosition(obj, position);
            return obj;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            if (object != null) {
                return ((Fragment) object).getView() == view;
            } else {
                return false;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(jvp_test.findViewFromObject(position));
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return fragments.get(arg0);
        }
    }
}
