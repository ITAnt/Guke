package com.tongcent.guke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tongcent.guke.R;
import com.tongcent.guke.utils.GukeUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (GukeUtils.getInstance().hasLogin(SplashActivity.this)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginOrRegisterActivity.class));
                }
                SplashActivity.this.finish();
            }
        }, 3500);
    }
}
