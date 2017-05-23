package com.shijiwei.slidemenu.widget.slidemenu.interfaces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * Created by shijiwei on 2017/5/16.
 */
public class SlideMenuPager extends Fragment implements ScreenShotable {

    private Bitmap mScreenBitmap;
    private View mRootView;


    /**
     * set the root view of this page
     *
     * @param rootView
     */
    public void bindRootView(View rootView) {
        this.mRootView = rootView;
    }

    @Override
    public Bitmap captureScreen() {
        if (mRootView != null) {
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
            if (bgDrawable != null) {
                bgDrawable.draw(canvas);
            } else {
                canvas.drawColor(Color.WHITE);
            }
            mRootView.draw(canvas);
            mScreenBitmap = screenBitmap;
        } else {
            throw new NullPointerException("root view is null,please invoke bindRootView() before");
        }
        return mScreenBitmap;
    }



}
