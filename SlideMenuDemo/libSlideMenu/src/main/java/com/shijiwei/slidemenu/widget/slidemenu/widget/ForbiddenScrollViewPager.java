package com.shijiwei.slidemenu.widget.slidemenu.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by shijiwei on 2017/3/29.
 */
public class ForbiddenScrollViewPager extends ViewPager {

    /**
     *  forbidden scroll
     */
    private boolean  mForbiddenScrollable = true;

    public ForbiddenScrollViewPager(Context context) {
       this(context,null);
    }

    public ForbiddenScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setForbiddenScrollable(boolean forbiddenScrollable){
        this.mForbiddenScrollable = forbiddenScrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mForbiddenScrollable)
            return false;
        else
            return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mForbiddenScrollable)
            return false;
        else
            return super.onInterceptTouchEvent(event);
    }


    @Override
    public void setCurrentItem(int item) {
        /**
         * True to smoothly scroll to the new item, false to transition immediately
         */
        super.setCurrentItem(item, false);
    }

}
