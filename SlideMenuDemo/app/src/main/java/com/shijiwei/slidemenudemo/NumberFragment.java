package com.shijiwei.slidemenudemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shijiwei.slidemenudemo.R;
import com.shijiwei.slidemenudemo.base.BaseFragment;

/**
 * Created by shijiwei on 2017/3/28.
 */
public class NumberFragment extends BaseFragment {

    private int[] color = {
            Color.CYAN,
            Color.RED,
            Color.BLUE,
            Color.DKGRAY,
            Color.GREEN,
            Color.GRAY,
    };

    //    private OverlayView targetView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_number, container, false);
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) parent.removeView(mRootView);
        initalizeView();
        return mRootView;
    }

    private void initalizeView() {
        TextView tvNumber = (TextView) mRootView.findViewById(R.id.tv_number);
        tvNumber.append(getArguments().getString("number"));
        tvNumber.setBackgroundColor(color[Integer.parseInt(getArguments().getString("number")) % 6]);
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("NumberFragment", getArguments().getString("number"));
            }
        });


    }

    public static NumberFragment newInstance(String mumber) {

        Bundle args = new Bundle();
        args.putString("number", mumber);
        NumberFragment fragment = new NumberFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
