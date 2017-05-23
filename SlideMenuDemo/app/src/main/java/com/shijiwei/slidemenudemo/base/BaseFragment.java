package com.shijiwei.slidemenudemo.base;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.shijiwei.slidemenu.widget.slidemenu.interfaces.ScreenShotable;
import com.shijiwei.slidemenu.widget.slidemenu.interfaces.SlideMenuPager;


/**
 * Created by shijiwei on 2017/3/20.
 */
public class BaseFragment extends SlideMenuPager {

    private static final String TAG = "BaseFragment";

    public View mRootView;


    @Override
    public void onResume() {
        super.onResume();
        bindRootView(mRootView);
    }
}
