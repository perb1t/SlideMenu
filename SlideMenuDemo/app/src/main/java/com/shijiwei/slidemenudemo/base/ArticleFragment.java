package com.shijiwei.slidemenudemo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shijiwei.slidemenudemo.R;

/**
 * Created by shijiwei on 2017/3/28.
 */
public class ArticleFragment extends BaseFragment {

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
        tvNumber.setBackgroundColor(color[3]);
        tvNumber.append(getArguments().getString("number"));
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("NumberFragment", getArguments().getString("number"));
            }
        });


    }

    public static ArticleFragment newInstance(String mumber) {

        Bundle args = new Bundle();
        args.putString("number", mumber);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
