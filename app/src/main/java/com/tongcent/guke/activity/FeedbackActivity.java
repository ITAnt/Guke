package com.tongcent.guke.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tongcent.guke.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/4/23.
 */
public class FeedbackActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
