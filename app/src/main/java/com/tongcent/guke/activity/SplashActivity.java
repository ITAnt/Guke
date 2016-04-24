package com.tongcent.guke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tongcent.guke.R;
import com.tongcent.guke.bean.Person;
import com.tongcent.guke.utils.GukeUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bmob.initialize(this, "f9635dd269c4e57d3cec4e0835ad04c3");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Person person = BmobUser.getCurrentUser(SplashActivity.this, Person.class);
                if (person != null) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginOrRegisterActivity.class));
                }
                SplashActivity.this.finish();
            }
        }, 3200);
    }
}
