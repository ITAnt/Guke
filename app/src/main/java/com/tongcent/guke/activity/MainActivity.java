package com.tongcent.guke.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tongcent.guke.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn_ranking;
    private Dialog mDialog;

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

        btn_ranking = (Button) findViewById(R.id.btn_ranking);
        btn_ranking.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.ll_update:
                break;
            case R.id.ll_donate:
                break;
            case R.id.btn_ranking:
                showLoadingDialog(this);
                // 当前用户的点击数+1，当前总数+1，提交给服务器
                break;
            default:
                break;
        }
    }

    /**
     * 显示加载框
     * @param context
     */
    private void showLoadingDialog(Context context) {
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.show();
    }

    /**
     * 隐藏加载框
     */
    private void hideLoadingDialog() {
        if (mDialog != null) {
            mDialog.hide();
        }
    }
}
