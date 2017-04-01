package com.shijiwei.slidemenu.widget.slidemenu.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;


import com.shijiwei.slidemenu.R;
import com.shijiwei.slidemenu.widget.slidemenu.helper.AnimotionManager;
import com.shijiwei.slidemenu.widget.slidemenu.helper.UISizeHelper;
import com.shijiwei.slidemenu.widget.slidemenu.interfaces.Source;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by shijiwei on 2017/3/8.
 */
public class SlideMenuComponent extends LinearLayout implements SquareImageView.OnInterceptSingleTapClickListener {

    private static final String TAG = SlideMenuComponent.class.getSimpleName();

    private long mAnimationDuration = 500;
    private long mPerIntervalTime = 30;

    private LayoutParams mSlideMenuItemLayoutParams;
    private Handler mAnimationHander = new Handler();
    private AnimotionManager mAnimotionManager;

    private OverScroller mScroller;
    private PointF mStartPoint;
    private int mTouchSlop;
    private boolean mOutsideTouchable = true;
    private boolean mScrollState = false;
    private boolean mAnimationState = false;
    private boolean isShowing = true;

    private ImageView.ScaleType mScaleType = ImageView.ScaleType.CENTER_INSIDE;
    private List<Source> mSourceSet;


    private OnSlideMenuItemClickListener mOnSlideMenuItemClickListener;
    /**
     * the actually offset of y-axis
     */
    private int yAxisOffset;

    public SlideMenuComponent(Context context) {
        this(context, null);
    }

    public SlideMenuComponent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenuComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intialize();
    }

    private void intialize() {
        setOrientation(VERTICAL);
        mSourceSet = new ArrayList<>();
        mScroller = new OverScroller(getContext());
        mStartPoint = new PointF();
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mAnimotionManager = new AnimotionManager(mPerIntervalTime, mAnimationDuration);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mSlideMenuItemLayoutParams = new LayoutParams(
                UISizeHelper.dp2px(getContext(), 60),
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * match parent
         */
        int expandWidthSpec = MeasureSpec.makeMeasureSpec(((View) getParent()).getWidth(),
                MeasureSpec.EXACTLY);

        int expandHeightSpec = MeasureSpec.makeMeasureSpec(((View) getParent()).getHeight(),
                MeasureSpec.EXACTLY);

        super.onMeasure(expandWidthSpec, expandHeightSpec);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {

            if (getChildCount() == 0) return;
            int mContentTotalHeight = getChildCount() * getChildAt(0).getWidth();
            if (mContentTotalHeight < getHeight()) return;
            if (getScrollY() == 0) {
                scrollBy(0, yAxisOffset < 0 ? 0 : yAxisOffset);
            } else if (getScrollY() > 0) {
                if (yAxisOffset < 0 && getScrollY() < Math.abs(yAxisOffset)) {
                    scrollBy(0, -getScrollY());
                } else {
                    if (mContentTotalHeight - (getHeight() + getScrollY()) < yAxisOffset) {
                        scrollBy(0, mContentTotalHeight - (getHeight() + getScrollY()));
                    } else {
                        scrollBy(0, yAxisOffset);
                    }
                }
            }
            postInvalidate();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartPoint.set(event.getX(), event.getY());
                return !mAnimationState;
            case MotionEvent.ACTION_MOVE:
                if (event.getX() > mSlideMenuItemLayoutParams.width)
                    return super.onTouchEvent(event);
                yAxisOffset = (int) (mStartPoint.y - event.getY());
                if (!mScrollState) {
                    if (Math.abs(yAxisOffset) > mTouchSlop) mScrollState = true;
                } else {
                    mStartPoint.set(event.getX(), event.getY());
                    mScroller.startScroll((int) mStartPoint.x, (int) mStartPoint.y, 0, yAxisOffset);
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                if (mOutsideTouchable && !mScrollState) {
                    if (event.getY() > getChildCount() * getChildAt(0).getWidth()) {
                        hideSlideMenu();
                    } else {
                        if (event.getX() > mSlideMenuItemLayoutParams.width)
                            hideSlideMenu();
                    }
                }

                for (int i = 0; i < getChildCount(); i++) {
                    SquareImageView child = (SquareImageView) getChildAt(i);
                    if (child != null && child.getReleaseFocus()) {
                        if (Math.abs(child.getCancelPoint().x - event.getX()) < 5
                                && Math.abs(child.getCancelPoint().y - event.getY()) < 5
                                && !mScrollState){
                            onInterceptSingleTap(child);
                        }
                            break;
                    }
                }
                mScrollState = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onInterceptSingleTap(View view) {
        if (hideSlideMenu()) {
            if (mOnSlideMenuItemClickListener != null && view != null)
                mOnSlideMenuItemClickListener.SlideMenuItemClick(view, (Integer) view.getTag(), mSourceSet.get((Integer) view.getTag()));
        }
    }


    private Runnable mTimeRunnable = new Runnable() {
        @Override
        public void run() {
            mAnimationState = false;
            isShowing = !isShowing;
            if (!isShowing) setVisibility(GONE);
        }
    };

    public interface OnSlideMenuItemClickListener {
        void SlideMenuItemClick(View view, int position, Source source);
    }

    private void addSlideMenuItem(Source source) {
        if (source != null) mSourceSet.add(source);
    }

    private void addSlideMenuItemView(List<Source> sources) {
        if (sources == null) return;
        for (int i = 0; i < sources.size(); i++) {
            Source source = sources.get(i);
            if (source == null) continue;
            SquareImageView icon = new SquareImageView(getContext());
            icon.setLayoutParams(mSlideMenuItemLayoutParams);
            icon.setBackgroundResource(R.drawable.menu_item_selector);
            icon.setScaleType(mScaleType);
            icon.setImageResource(source.getIconResourceId());
            icon.setTag(i);
            icon.setOnInterceptSingleTopClickListener(this);
            addView(icon);
        }
    }


    /**
     * add slid menu items's source
     *
     * @param items
     */
    public void addSlideMenuItem(List<Source> items) {
        if (items != null && items.size() != 0) {
            for (int i = 0; i < items.size(); i++) {
                addSlideMenuItem(items.get(i));
            }
        }
        addSlideMenuItemView(mSourceSet);
        hideSlideMenu();
    }

    /**
     * show the menu
     */
    public void showSlideMenu() {
        if (!isShowing() && !isAnimationing()) {
            setVisibility(VISIBLE);
            mAnimotionManager.show(this);
            mAnimationState = true;
            mAnimationHander.postDelayed(mTimeRunnable, getTotalPerfromAnimationDutation());
        }
    }

    /**
     * hide the menu
     */
    public boolean hideSlideMenu() {
        if (isShowing() && !isAnimationing()) {
            mAnimotionManager.hide(this);
            mAnimationState = true;
            mAnimationHander.postDelayed(mTimeRunnable, getTotalPerfromAnimationDutation());
            return true;
        }

        return false;
    }

    /**
     * set the scale type of slide menu item icon,plz call it before addSlideMenuItem()
     *
     * @param scaleType
     */
    public void setSlideMenuIconScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    /**
     * set the layoutParams of slide menu item icon, plz call it before addSlideMenuItem()
     *
     * @param lp
     */
    public void setSlideMenuItemLayoutParams(LayoutParams lp) {
        this.mSlideMenuItemLayoutParams = lp;
    }

    /**
     * set a monitor  click event of the slide menu item
     *
     * @param listener
     */
    public void setOnSlideMenuItemClickListener(OnSlideMenuItemClickListener listener) {
        this.mOnSlideMenuItemClickListener = listener;
    }

    public void setAnimationDuration(long mAnimationDuration) {
        this.mAnimationDuration = mAnimationDuration;
    }

    public void setPerIntervalTime(long mPerIntervalTime) {
        this.mPerIntervalTime = mPerIntervalTime;
    }

    public long getTotalPerfromAnimationDutation() {
        return getChildCount() * mPerIntervalTime + mAnimationDuration;
    }

    public void setOutsideTouchable(boolean outsideTouchable) {
        this.mOutsideTouchable = outsideTouchable;
    }

    public List<Source> getSourceSet() {
        return mSourceSet;
    }

    /**
     * return a state of animation is playing
     *
     * @return
     */
    public boolean isAnimationing() {
        return mAnimationState;
    }

    /**
     * return a state of menu  is visible
     *
     * @return
     */
    public boolean isShowing() {
        return isShowing;
    }

}
