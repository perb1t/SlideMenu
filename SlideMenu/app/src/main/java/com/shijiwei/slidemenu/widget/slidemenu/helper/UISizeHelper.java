package com.shijiwei.slidemenu.widget.slidemenu.helper;

import android.content.Context;

/**
 * Created by shijiwei on 2017/3/29.
 */
public class UISizeHelper {

    /**
     *  
     *  depend on android device's density convert dp to px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *  
     *  depend on android device's density convert px to dp
     */

    public static int px2dp(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue / scale + 0.5f);
    }

}
