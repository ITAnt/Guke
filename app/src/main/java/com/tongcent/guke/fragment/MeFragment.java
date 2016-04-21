package com.tongcent.guke.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.tongcent.guke.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2016/4/19.
 */
public class MeFragment extends Fragment {

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        mContext = getContext();
        initComponent(view);
        return view;
    }

    private void initComponent(View view) {


    }
}
