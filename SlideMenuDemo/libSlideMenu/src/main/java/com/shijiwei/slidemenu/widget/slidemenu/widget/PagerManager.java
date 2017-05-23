package com.shijiwei.slidemenu.widget.slidemenu.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.shijiwei.slidemenu.R;
import com.shijiwei.slidemenu.widget.slidemenu.interfaces.Source;


/**
 * Created by shijiwei on 2017/3/28.
 */
public class PagerManager extends LinearLayout implements SlideMenuComponent.OnSlideMenuItemClickListener, ToolbarComponent.OnToolbarCallBack {

    private static final String TAG = PagerManager.class.getSimpleName();

    private float MAX_X_TOUCH_LEFT_EDGE = 100;

    private ToolbarComponent mToolbar;
    private OverlayView mOverlayView;
    private ViewPager mViewPager;
    private PagerManagerAdapter mPagerManagerAdapter;

    private FragmentManager mFragmentManager;
    private SlideMenuComponent mSlideMenuComponent;
    /**
     * fragment pager index of pagerSet
     */
    private int mPagerIndex = 0;

    public PagerManager(Context context) {
        this(context, null);
    }

    public PagerManager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initalize();
    }

    private void initalize() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_pager_manager, this);
        mOverlayView = (OverlayView) findViewById(R.id.overlay_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mToolbar = (ToolbarComponent) findViewById(R.id.tool_bar);
        mToolbar.setOnToolbarCallBack(this);
        MAX_X_TOUCH_LEFT_EDGE = getContext().getResources().getDisplayMetrics().widthPixels / 30;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() - getLeft() < MAX_X_TOUCH_LEFT_EDGE && event.getY() > mToolbar.getHeight())
                    return true;
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() - getLeft() < MAX_X_TOUCH_LEFT_EDGE && event.getY() > mToolbar.getHeight())
                    return true;
                break;
            case MotionEvent.ACTION_MOVE:
                onSlide(event.getX() / getWidth());
                break;
        }
        return super.onTouchEvent(event);
    }

    public void bind(FragmentManager fragmentManager, SlideMenuComponent slideMenuComponent) {
        this.mFragmentManager = fragmentManager;
        this.mSlideMenuComponent = slideMenuComponent;
        this.mSlideMenuComponent.setOnSlideMenuItemClickListener(this);
        mPagerManagerAdapter = new PagerManagerAdapter(mFragmentManager);
        mViewPager.setAdapter(mPagerManagerAdapter);
        mToolbar.setmSmallTitle(mSlideMenuComponent.getSourceSet().get(0).getName());
    }

    /**
     * @param slideOffset 0.0f - 1.0f
     */
    public void onSlide(float slideOffset) {
        if (slideOffset > 0.25)
            mSlideMenuComponent.showSlideMenu();
    }

    @Override
    public void SlideMenuItemClick(View view, int position, Source source) {
        Log.e(TAG, "target position = " + position + " , current position = " + mPagerIndex);
        if (position == mPagerIndex) return;
        mOverlayView.setImageBitmap(mSlideMenuComponent.getSourceSet().get(mPagerIndex).getBindPager().captureScreen());
        mViewPager.setCurrentItem(position);
        mToolbar.setmSmallTitle(source.getName());
        mPagerIndex = position;
        mOverlayView.setCircleProperty(mOverlayView.getWidth() / 2, mOverlayView.getHeight() / 2);
        mOverlayView.startClip();
    }

    @Override
    public void onLeftButtonClick(View view) {
        mSlideMenuComponent.showSlideMenu();
    }

    /**
     * Fragemt pager manage adapter
     */
    public class PagerManagerAdapter extends FragmentStatePagerAdapter {

        public PagerManagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) mSlideMenuComponent.getSourceSet().get(position).getBindPager();
        }

        @Override
        public int getCount() {
            return mSlideMenuComponent.getSourceSet().size();
        }
    }
}
