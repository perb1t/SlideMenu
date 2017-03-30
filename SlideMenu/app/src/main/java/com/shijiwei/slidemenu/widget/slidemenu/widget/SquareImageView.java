package com.shijiwei.slidemenu.widget.slidemenu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by shijiwei on 2017/3/16.
 * 高度与宽度相同的ImageView
 */
public class SquareImageView extends ImageView  {

    private static final String TAG = SquareImageView.class.getSimpleName();
    private OnInterceptSingleTapClickListener listener;

    public SquareImageView(Context context) {
       this(context,null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
      this(context,attrs,0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      setMeasuredDimension(getDefaultSize(0,widthMeasureSpec),getDefaultSize(0,heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onTouchEvent(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                return false;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
                setEnabled(true);
                Log.e(TAG,"ACTION_CANCEL  " + event.getX());
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"ACTION_UP   " + event.getX());
                if (listener != null) listener.onInterceptSingleTap(this);
                setEnabled(true);
                return true;
            case MotionEvent.ACTION_DOWN:
                setEnabled(false);

        }
        return super.onTouchEvent(event);
    }

    /**
     * intercept the singletap touch event
     */
    public interface OnInterceptSingleTapClickListener{
        void onInterceptSingleTap(View view);
    }

    public void setOnInterceptSingleTopClickListener(OnInterceptSingleTapClickListener listener){
        this.listener = listener;
    }
}
