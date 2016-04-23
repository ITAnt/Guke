package com.tongcent.guke.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tongcent.guke.R;
import com.tongcent.guke.activity.MainActivity;

public class LoginFragment extends Fragment implements View.OnClickListener {

	private Button btn_login;
	private Activity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		initialComponent(view);
		return view;
	}

	private void initialComponent(View view) {
		btn_login = (Button) view.findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_login:
				// 校验成功并且保存用户数据到本地之后，跳转到主界面，并且把用户数据传递过去
				startActivity(new Intent(mActivity, MainActivity.class));
				mActivity.finish();
				break;
			default:
				break;
		}
	}
}
