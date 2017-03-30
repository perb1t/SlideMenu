package com.shijiwei.slidemenu.widget.slidemenu.helper;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.shijiwei.slidemenu.widget.slidemenu.animation.Rotate3dAnimation;

/**
 * Created by shijiwei on 2017/3/28.
 */
public class AnimotionManager {

    private long mAnimationDuration = 500;
    private long mPerIntervalTime = 30;

    private final int ANIMATION_TYPE_3D_ROTATE = 0;
    private final int ANIMATION_TYPE_MIDDLE_ROTATE = 1;

    private Handler mAnimationHander;
    private int mAnimationType = 0;
    private int mFromDegree = 90;
    private int mToDegree = 0;

    public AnimotionManager(long perIntervalTime, long animationDuration) {
        this.mPerIntervalTime = perIntervalTime;
        this.mAnimationDuration = animationDuration;
        mAnimationHander = new Handler();
    }


    public void show(ViewGroup parent) {
        if (parent == null) return;
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            mAnimationHander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Rotate3dAnimation mShowRotate3dAnimation = new Rotate3dAnimation(mFromDegree, mToDegree, 0, child.getHeight() / 2, 0, true);
                    mShowRotate3dAnimation.setDuration(mAnimationDuration);
                    mShowRotate3dAnimation.setInterpolator(new AccelerateInterpolator());
                    mShowRotate3dAnimation.setFillAfter(true);
                    child.startAnimation(mShowRotate3dAnimation);
                }
            }, mPerIntervalTime * i);
        }
    }

    public void hide(ViewGroup parent) {
        if (parent == null) return;
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            mAnimationHander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Rotate3dAnimation mShowRotate3dAnimation = new Rotate3dAnimation(mToDegree, mFromDegree, 0, child.getHeight() / 2, 0, true);
                    mShowRotate3dAnimation.setDuration(mAnimationDuration);
                    mShowRotate3dAnimation.setInterpolator(new AccelerateInterpolator());
                    mShowRotate3dAnimation.setFillAfter(true);
                    child.startAnimation(mShowRotate3dAnimation);
                }
            }, (parent.getChildCount() - (i + 1)) * mPerIntervalTime);
        }
    }
}
