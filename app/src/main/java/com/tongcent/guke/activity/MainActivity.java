package com.tongcent.guke.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tongcent.guke.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_feedback;
    private LinearLayout ll_update;
    private LinearLayout ll_donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
    }

    private void initComponent() {
        ll_feedback = (LinearLayout) findViewById(R.id.ll_feedback);
        ll_feedback.setOnClickListener(this);
        ll_update = (LinearLayout) findViewById(R.id.ll_update);
        ll_update.setOnClickListener(this);
        ll_donate = (LinearLayout) findViewById(R.id.ll_donate);
        ll_donate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_feedback:
                break;
            case R.id.ll_update:
                break;
            case R.id.ll_donate:
                break;
            default:
                break;
        }
    }
}
