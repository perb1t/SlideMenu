package com.shijiwei.slidemenu.widget.slidemenu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiwei.slidemenu.R;


/**
 * Created by shijiwei on 2017/3/28.
 */
public class ToolbarComponent extends FrameLayout implements View.OnClickListener {

    private TextView mSmallTitleTextView;
    private TextView mTitleTextView;
    public ImageView mLeftButton;

    private OnToolbarCallBack onToolbarCallBack;

    public ToolbarComponent(Context context) {
       this(context,null);
    }

    public ToolbarComponent(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public ToolbarComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initalize();
    }

    private void initalize() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_slide_menu_toolbar,this);
        mLeftButton = (ImageView) findViewById(R.id.button_left);
        mSmallTitleTextView = (TextView) findViewById(R.id.tv_small_title);
        mTitleTextView = (TextView) findViewById(R.id.tv_title);

        mLeftButton.setOnClickListener(this);
    }

    public void setmSmallTitle(String smallTitle){
        mSmallTitleTextView.setText(smallTitle);
    }

    public void setTitle(String title){
        mTitleTextView.setText(title);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_left:
                if (onToolbarCallBack != null)
                 onToolbarCallBack.onLeftButtonClick(v);
                break;
        }
    }

    public interface OnToolbarCallBack{
        void onLeftButtonClick(View view);
    }

    public void setOnToolbarCallBack(OnToolbarCallBack callBack){
        this.onToolbarCallBack = callBack;
    }

}
