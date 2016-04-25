package com.tongcent.guke.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tongcent.guke.R;
import com.tongcent.guke.activity.MainActivity;
import com.tongcent.guke.bean.Person;
import com.umeng.analytics.MobclickAgent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginFragment extends Fragment {

	private Button btn_login;
	private Activity mActivity;

	private EditText et_phone;
	private EditText et_pass;

	private String PATTERN_PHONE = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mActivity = getActivity();
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		initialComponent(view);
		return view;
	}

	private void initialComponent(View view) {

		et_phone = (EditText) view.findViewById(R.id.et_phone);
		et_pass = (EditText) view.findViewById(R.id.et_pass);

		btn_login = (Button) view.findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.btn_login:

						String phone = et_phone.getText().toString();
						String pass = et_pass.getText().toString();

						if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pass)) {
							Pattern r = Pattern.compile(PATTERN_PHONE);
							Matcher m = r.matcher(phone);
							boolean match = m.matches();
							if (match) {
								Person user = new Person();
								user.setUsername(phone);
								user.setPassword(pass);
								user.login(mActivity, new SaveListener() {
									@Override
									public void onSuccess() {
										// 校验成功并且保存用户数据到本地之后，跳转到主界面，并且把用户数据传递过去
										Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show();
										startActivity(new Intent(mActivity, MainActivity.class));
										mActivity.finish();
									}

									@Override
									public void onFailure(int i, String s) {
										Toast.makeText(mActivity, "登录失败", Toast.LENGTH_SHORT).show();
									}
								});
							} else {
								Toast.makeText(mActivity, "请输入有效的手机号", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(mActivity, "请先填写手机号和密码", Toast.LENGTH_SHORT).show();
						}
						break;
					default:
						break;
				}
			}
		});
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("SignupSignin"); //统计页面，"MainScreen"为页面名称，可自定义
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("SignupSignin");
	}
}
