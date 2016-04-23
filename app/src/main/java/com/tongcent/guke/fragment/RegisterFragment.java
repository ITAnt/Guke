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

public class RegisterFragment extends Fragment implements View.OnClickListener {

	private Activity mActivity;
	private Button btn_register;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register, container, false);
		initialComponent(view);
		return view;
	}

	private void initialComponent(View view) {
		btn_register = (Button) view.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_register:
				// 注册成功之后，保存数据到本地，并把用户数据传递到主界面
				startActivity(new Intent(mActivity, MainActivity.class));
				mActivity.finish();
				break;
			default:
				break;
		}
	}
}
