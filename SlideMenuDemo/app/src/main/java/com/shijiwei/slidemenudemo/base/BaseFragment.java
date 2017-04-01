package com.shijiwei.slidemenudemo.base;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.shijiwei.slidemenu.widget.slidemenu.interfaces.ScreenShotable;


/**
 * Created by shijiwei on 2017/3/20.
 */
public class BaseFragment extends android.support.v4.app.Fragment implements ScreenShotable {

    private static final String TAG = "BaseFragment";

    public View mRootView;
    private Bitmap mFragmentScreen;

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               captureScreen();
            }
        },200);
    }

    @Override
    public void captureScreen() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap screenBitmap = Bitmap.createBitmap(
                        mRootView.getWidth(), mRootView.getHeight(),
                        Bitmap.Config.ARGB_8888
                );
                Canvas canvas = new Canvas(screenBitmap);
                mRootView.layout(
                        mRootView.getLeft(), mRootView.getTop(),
                        mRootView.getRight(), mRootView.getBottom()
                );
                Drawable bgDrawable = mRootView.getBackground();
                if (bgDrawable != null){
                    bgDrawable.draw(canvas);
                } else{
                    canvas.drawColor(Color.WHITE);
                }
                mRootView.draw(canvas);
                mFragmentScreen = screenBitmap;
            }
        };

        thread.start();
    }

    @Override
    public Bitmap getScreen() {
        return mFragmentScreen;
    }
}
