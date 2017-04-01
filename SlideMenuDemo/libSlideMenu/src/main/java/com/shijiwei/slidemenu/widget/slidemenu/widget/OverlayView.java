package com.shijiwei.slidemenu.widget.slidemenu.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * Created by shijiwei on 2017/3/29.
 */
public class OverlayView extends FrameLayout {

    private boolean mStateCliping = false;
    private Path mClipPath;
    private PointF mCircleCenterPoint;
    private long mAnimationDuration = 500;
    private Timer mTimer;
    private float mCircleRadius;
    private ImageView mContentView;

    public OverlayView(Context context) {
        this(context,null);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public OverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initalize();
    }

    private void initalize() {
        mClipPath = new Path();
        mCircleCenterPoint = new PointF();
        mTimer = new Timer();
        mTimer.setDuration(mAnimationDuration);
        mContentView = new ImageView(getContext());
        mContentView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mContentView);
    }

    public void setCircleProperty(float centerX,float centerY){
        this.mCircleCenterPoint.set(centerX,centerY);
    }

    public void setAnimationDuration(long duration){
        this.mAnimationDuration = duration;
        mTimer.setDuration(mAnimationDuration);
    }

    public void startClip(){
        setVisibility(VISIBLE);
        mStateCliping = true;
        startAnimation(mTimer);
    }

    public void setImageBitmap(Bitmap bm){
        mContentView.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mStateCliping){
            mClipPath.reset();
            mClipPath.addCircle(mCircleCenterPoint.x,mCircleCenterPoint.y,mCircleRadius, Path.Direction.CCW);
            canvas.clipPath(mClipPath);
        }
    }

    private class Timer extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mCircleRadius = (1 - interpolatedTime) * (Math.max(mCircleCenterPoint.x,mCircleCenterPoint.y));
            if (interpolatedTime == 1) setVisibility(GONE);
            setWillNotDraw(false);
            invalidate();
        }
    }

}
