package com.shijiwei.slidemenu.widget.slidemenu.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/5/31.
 * <p>
 * 属性动画封装
 */
public class AnimationUtil {

    private final String[] modeNames = {
            "alpha",
            "rotation", "rotationX", "rotationY",
            "translationX", "translationY",
            "scaleX", "scaleY"
    };

    private static List<AnimationUtil> instanceList = new ArrayList<>();
    private View targetView;

    private AnimationUtil(View targetView) {
        this.targetView = targetView;
    }

    private void addAnimation(String modeName, long duration, Animator.AnimatorListener listener, float... values) {
        try {
            ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, modeName, values);
            animator.setDuration(duration);
            if (listener!= null) animator.addListener(listener);
            animator.start();
        } catch (Exception e) {
            if (targetView == null)
                throw new UnsupportedOperationException("targetView is null");
        }
    }

    /**
     * 绑定控件   可以自己选择保存对象与否
     */
    public static AnimationUtil bindView(View targetView) {
//        for (int i = 0; i < instanceList.size(); i++) {
//            if (instanceList.get(i).targetView.getId() == targetView.getId()) {
//                return instanceList.get(i);
//            }
//        }
//        instanceList.add(new AnimationUtil(targetView));
//        return instanceList.get(instanceList.size() - 1);
        return new AnimationUtil(targetView);
    }

    /**
     * 释放控件动画
     */
    public AnimationUtil clear() {
        this.targetView.clearAnimation();
        return this;
    }

    /**
     * 透明度
     */

    public AnimationUtil setAlpha(long duration, float... values) {
        return setAlpha(duration, null, values);
    }

    public AnimationUtil setAlpha(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[0], duration, listener, values);
        return this;
    }

    /**
     * 旋转
     */
    public AnimationUtil setRotation(long duration, float... values) {
        return setRotation(duration, null, values);
    }

    public AnimationUtil setRotation(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[1], duration, listener, values);
        return this;
    }

    /**
     * 围绕X轴旋转
     */
    public AnimationUtil setRotationX(long duration, float... values) {
        return setRotationX(duration, null, values);
    }

    public AnimationUtil setRotationX(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[2], duration, listener, values);
        return this;
    }

    /**
     * 围绕Y轴旋转
     */
    public AnimationUtil setRotationY(long duration, float... values) {
        return setRotationY(duration, null, values);
    }

    public AnimationUtil setRotationY(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[3], duration, listener, values);
        return this;
    }

    /**
     * X轴平移
     */
    public AnimationUtil setTranslationX(long duration, float... values) {
        return setTranslationX(duration, null, values);
    }

    public AnimationUtil setTranslationX(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[4], duration, listener, values);
        return this;
    }

    /**
     * Y轴平移
     */
    public AnimationUtil setTranslationY(long duration, float... values) {
        return setTranslationY(duration, null, values);
    }

    public AnimationUtil setTranslationY(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[5], duration, listener, values);
        return this;
    }

    /**
     * X轴缩放（按倍数缩放）
     */
    public AnimationUtil setScaleX(long duration, float... values) {
        return setScaleX(duration, null, values);
    }

    public AnimationUtil setScaleX(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[6], duration, listener, values);
        return this;
    }

    /**
     * Y轴缩放（按倍数缩放）
     */
    public AnimationUtil setScaleY(long duration, float... values) {
        return setScaleY(duration, null, values);
    }

    public AnimationUtil setScaleY(long duration, Animator.AnimatorListener listener, float... values) {
        addAnimation(modeNames[7], duration, listener, values);
        return this;
    }


}
