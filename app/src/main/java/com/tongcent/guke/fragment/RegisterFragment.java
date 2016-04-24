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

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterFragment extends Fragment {

	private Activity mActivity;
	private Button btn_register;

	private EditText et_phone;
	private EditText et_nickname;
	private EditText et_pass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mActivity = getActivity();
		View view = inflater.inflate(R.layout.fragment_register, container, false);
		initialComponent(view);
		return view;
	}

	private void initialComponent(View view) {
		et_phone = (EditText) view.findViewById(R.id.et_phone);
		et_nickname = (EditText) view.findViewById(R.id.et_nickname);
		et_pass = (EditText) view.findViewById(R.id.et_pass);

		btn_register = (Button) view.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.btn_register:

						final String phone = et_phone.getText().toString();
						final String nickName = et_nickname.getText().toString();
						final String pass = et_pass.getText().toString();

						if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(nickName) && !TextUtils.isEmpty(pass)) {
							BmobQuery<Person> query = new BmobQuery<>();
							query.addWhereEqualTo("phonenumber", phone);
							query.findObjects(mActivity, new FindListener<Person>() {
								@Override
								public void onSuccess(List<Person> persons) {
									if (persons != null && persons.size() == 1) {
										Toast.makeText(mActivity, "该手机号已经注册过了", Toast.LENGTH_SHORT).show();
									} else {
										Person person = new Person();
										person.setPhoneNumber(phone);
										person.setThePassword(pass);
										person.setPassword(pass);
										person.setUsername(nickName);

										person.signUp(mActivity, new SaveListener() {
											@Override
											public void onSuccess() {
												// 注册成功之后，保存数据到本地，并把用户数据传递到主界面
												startActivity(new Intent(mActivity, MainActivity.class));
												mActivity.finish();
											}

											@Override
											public void onFailure(int i, String s) {
												Toast.makeText(mActivity, "注册失败", Toast.LENGTH_SHORT).show();
											}
										});
									}
								}

								@Override
								public void onError(int i, String s) {
									Toast.makeText(mActivity, "注册失败", Toast.LENGTH_SHORT).show();
								}
							});
						} else {
							Toast.makeText(mActivity, "请完善信息", Toast.LENGTH_SHORT).show();
						}
						break;
					default:
						break;
				}
			}
		});
	}
}
