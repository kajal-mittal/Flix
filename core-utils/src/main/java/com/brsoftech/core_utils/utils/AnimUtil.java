package com.brsoftech.core_utils.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 28/4/16</p>
 * <p>Time: 12:27 PM</p>
 * <p>Project: Grocery</p>
 * <p>Package: com.brsoftech.starterpack.utils</p>
 */
public class AnimUtil {
    private Context mContext;

    public AnimUtil(Context context) {
        this.mContext = context;
    }

    public void fadeInLeft(View view) {
        view.setVisibility(View.INVISIBLE);
        Animation fadeInAnim = AnimationUtils.makeInAnimation(mContext, true);
        view.startAnimation(fadeInAnim);
        view.setVisibility(View.VISIBLE);
        Log.d("fadeIn", "true");
    }

    public void fadeOutRight(View view) {
        Animation fadeOutAnim = AnimationUtils.makeOutAnimation(mContext, true);
        view.startAnimation(fadeOutAnim);
        view.setVisibility(View.INVISIBLE);
        Log.d("fadeOut", "true");
    }

    public void fadeInAlpha(View view, int durationInMillis) {
        view.setVisibility(View.INVISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        alphaAnimation.setDuration(durationInMillis);
        view.startAnimation(alphaAnimation);
        view.setVisibility(View.VISIBLE);
    }

    public void fadeOutAlpha(final View view, int durationInMillis) {
        view.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
        alphaAnimation.setDuration(durationInMillis);
        view.startAnimation(alphaAnimation);
        view.setVisibility(View.INVISIBLE);
    }

    public void crossFade(View initialView, View finalView) {
        fadeOutAlpha(initialView, 500);
        fadeInAlpha(finalView, 500);
    }
}
