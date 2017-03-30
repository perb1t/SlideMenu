package com.shijiwei.slidemenu.widget.slidemenu.interfaces;


/**
 * Created by shijiwei on 2017/3/8.
 */
public interface Source<T extends ScreenShotable> {

    String getName();

    int getIconResourceId();

    T getBindPager();
}
