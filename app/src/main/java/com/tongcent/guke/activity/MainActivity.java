package com.tongcent.guke.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongcent.guke.R;
import com.tongcent.guke.bean.Person;
import com.tongcent.guke.bean.SumClicks;
import com.tongcent.guke.utils.TimeUtils;
import com.tongcent.guke.view.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends Activity implements View.OnClickListener {
    private Person mPerson;
    private Dialog mDialog;

    private TextView tv_name;

    private LinearLayout ll_feedback;
    private LinearLayout ll_update;
    private LinearLayout ll_donate;

    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_luna;
    private TextView tv_suit;
    private TextView tv_avoid;

    private Button btn_ranking;
    private Button btn_circle;
    private Button btn_logout;

    private Handler mSumHandler;
    private Handler mRankHandler;
    private boolean mContinue = true;
    private volatile long mSumClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        updateSumClicks();
        updateRank();
    }

    private void initComponent() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        mPerson = BmobUser.getCurrentUser(this, Person.class);
        if (mPerson != null) {
            tv_name.setText(mPerson.getUsername());
        }

        btn_ranking = (Button) findViewById(R.id.btn_ranking);
        btn_ranking.setOnClickListener(this);
        btn_circle = (Button) findViewById(R.id.btn_circle);
        btn_circle.setOnClickListener(this);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);

        ll_feedback = (LinearLayout) findViewById(R.id.ll_feedback);
        ll_feedback.setOnClickListener(this);
        ll_update = (LinearLayout) findViewById(R.id.ll_update);
        ll_update.setOnClickListener(this);
        ll_donate = (LinearLayout) findViewById(R.id.ll_donate);
        ll_donate.setOnClickListener(this);



        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_week = (TextView) findViewById(R.id.tv_week);
        tv_luna = (TextView) findViewById(R.id.tv_luna);
        tv_suit = (TextView) findViewById(R.id.tv_suit);
        tv_avoid = (TextView) findViewById(R.id.tv_avoid);

        new GetNetworkDateTask().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.ll_donate:
                break;
            case R.id.btn_ranking:
                showLoadingDialog(this);
                // 当前用户的点击数+1，当前总数+1，提交给服务器
                clickPlus();
                break;

            case R.id.btn_circle:
                showLoadingDialog(this);
                // 当前用户的点击数+1，当前总数+1，提交给服务器
                clickPlus();
                break;

            case R.id.btn_logout:
                BmobUser.logOut(MainActivity.this);
                startActivity(new Intent(MainActivity.this, LoginOrRegisterActivity.class));
                MainActivity.this.finish();
                break;
            default:
                break;
        }
    }

    /*****************************************更新点击总数*************************************/
    /**
     * 不停去更新点击的总次数
     */
    private void updateSumClicks() {
        // 一定要调用start方法，否则looper为null
        mSumThread.start();
        mSumHandler = new Handler(mSumThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                //在这里更新UI
            }
        };
        mSumHandler.post(mSumRunnable);
    }

    private Runnable mSumRunnable = new Runnable() {

        @Override
        public void run() {
            // 去查询数据，查到之后发消息更新UI
            BmobQuery<SumClicks> bmobQuery = new BmobQuery<SumClicks>();
            bmobQuery.getObject(MainActivity.this, "wH1B777G", new GetListener<SumClicks>() {
                @Override
                public void onSuccess(SumClicks object) {
                    // TODO Auto-generated method stub
                    mSumClicks = object.getClicks();
                }

                @Override
                public void onFailure(int code, String msg) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    mSumHandler.sendEmptyMessage(1);
                    try {
                        // 休眠1秒，如果还没销毁则继续查询
                        Thread.sleep(1000);
                        if (mContinue) {
                            mSumHandler.post(mSumRunnable);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private SumThread mSumThread = new SumThread("connect-thread", Thread.MAX_PRIORITY);
    private class SumThread extends HandlerThread implements Callback {

        public SumThread(String name, int priority) {
            super(name, priority);
        }
    }
    /*****************************************更新点击总数*************************************/

    /****************************************更新排名start****************************************/
    /**
     * 不停去更新排名
     */
    private void updateRank() {
        // 一定要调用start方法，否则looper为null
        mRankThread.start();
        mRankHandler = new Handler(mRankThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                //在这里更新UI
            }
        };
        mRankHandler.post(mRankRunnable);
    }

    private Runnable mRankRunnable = new Runnable() {

        @Override
        public void run() {
            // 去查询数据，查到之后发消息更新UI
            BmobQuery<SumClicks> bmobQuery = new BmobQuery<SumClicks>();
            bmobQuery.getObject(MainActivity.this, "wH1B777G", new GetListener<SumClicks>() {
                @Override
                public void onSuccess(SumClicks object) {
                    // TODO Auto-generated method stub
                    mSumClicks = object.getClicks();
                }

                @Override
                public void onFailure(int code, String msg) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    mSumHandler.sendEmptyMessage(1);
                    try {
                        // 休眠1秒，如果还没销毁则继续查询
                        Thread.sleep(1000);
                        if (mContinue) {
                            mSumHandler.post(mRankRunnable);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private RankThread mRankThread = new RankThread("connect-thread", Thread.MAX_PRIORITY);
    private class RankThread extends HandlerThread implements Callback {

        public RankThread(String name, int priority) {
            super(name, priority);
        }
    }
    /****************************************更新排名end******************************************/

    /**
     * 用户点击了一下，点击次数要自加
     */
    private void clickPlus() {
        showLoadingDialog(this);
        mPerson.setClickNum(mPerson.getClickNum() + 1);
        mPerson.save(MainActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {

                Toast.makeText(MainActivity.this, "成功点击了一次", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this, "本次点击未生效", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                super.onFinish();

                // 去查询数据，查到之后发消息更新UI
                BmobQuery<SumClicks> bmobQuery = new BmobQuery<SumClicks>();
                bmobQuery.getObject(MainActivity.this, "wH1B777G", new GetListener<SumClicks>() {
                    @Override
                    public void onSuccess(SumClicks object) {
                        // TODO Auto-generated method stub
                        mSumClicks = object.getClicks();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        // 更新总次数到服务器
                        SumClicks sumClicks = new SumClicks();
                        sumClicks.setClicks(mSumClicks+1);
                        sumClicks.update(MainActivity.this, "wH1B777G", new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                mSumHandler.sendEmptyMessage(1);
                            }
                            @Override
                            public void onFailure(int code, String msg) {
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                hideLoadingDialog();
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * 显示加载框
     * @param
     */
    private void showLoadingDialog(Activity activity) {
        mDialog = new AlertDialog.Builder(activity).create();
        mDialog.show();
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.getWindow().setBackgroundDrawableResource(R.color.color_transparent);
        mDialog.setCanceledOnTouchOutside(false);

        LoadingView lv_loading = (LoadingView) mDialog.findViewById(R.id.lv_loading);
        /*Animation animation = AnimationUtils.loadAnimation(activity, R.anim.anim_loading);
        lv_loading.startAnimation(animation);*/
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        rotateAnimation.setDuration(3600);
        //rotateAnimation.setRepeatMode(Animation.INFINITE);
        rotateAnimation.setRepeatCount(-1);
        // 匀速旋转
        rotateAnimation.setInterpolator(new LinearInterpolator());
        lv_loading.startAnimation(rotateAnimation);
    }

    /**
     * 隐藏加载框
     */
    private void hideLoadingDialog() {
        if (mDialog != null) {
            mDialog.hide();
        }
    }

    private class GetNetworkDateTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //showLoadingDialog(MainActivity.this);
        }

        @Override
        protected String doInBackground(Void... params) {
            return TimeUtils.getNetworkDate();
        }

        @Override
        protected void onPostExecute(String networkDate) {
            super.onPostExecute(networkDate);

            String showDate = networkDate;
            if (TextUtils.isEmpty(showDate)) {
                showDate = TimeUtils.getLocalDate();
            }

            tv_date.setText(showDate);
            RequestParams params = new RequestParams("http://japi.juhe.cn/calendar/day");
            params.addQueryStringParameter("key", "bd64129c61d5f50da8f0e5a62eb202e3");
            params.addQueryStringParameter("date", showDate);
            x.http().get(params, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {

                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode dataNode = mapper.readTree(result).get("result").get("data");
                        String week = dataNode.get("weekday").textValue();
                        String suit = dataNode.get("suit").textValue();
                        String avoid = dataNode.get("avoid").textValue();
                        String lunar = dataNode.get("lunar").textValue();

                        tv_week.setText(week);
                        tv_suit.setText(suit);
                        tv_avoid.setText(avoid);
                        tv_luna.setText(lunar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFinished() {
                    //hideLoadingDialog();
                }
            });
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContinue = false;
        mSumHandler.removeCallbacks(mSumRunnable);
        mRankHandler.removeCallbacks(mRankRunnable);
    }
}
