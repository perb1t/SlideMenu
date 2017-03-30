package com.shijiwei.slidemenu.widget.slidemenu.modle;


import com.shijiwei.slidemenu.widget.slidemenu.interfaces.ScreenShotable;
import com.shijiwei.slidemenu.widget.slidemenu.interfaces.Source;


/**
 * Created by shijiwei on 2017/3/8.
 * <p/>
 * 策划菜单选择项
 */
public class SlideMenuItem<T extends ScreenShotable> implements Source<ScreenShotable> {

    private String name;
    private int iconResourceId;
    private T t;

    public SlideMenuItem() {
    }

    public SlideMenuItem(String name, int iconResourceId, T t) {
        this.name = name;
        this.iconResourceId = iconResourceId;
        this.t = t;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIconResourceId() {
        return iconResourceId;
    }

    @Override
    public T getBindPager() {
        return t;
    }
}
