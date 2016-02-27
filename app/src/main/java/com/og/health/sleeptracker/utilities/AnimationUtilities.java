package com.og.health.sleeptracker.utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import com.og.health.sleeptracker.R;

/**
 * Created by olivier.goutay on 2/27/16.
 */
public class AnimationUtilities {

    /**
     * Animates with new material design effect named CircularReveal
     * using {@link android.view.ViewAnimationUtils#createCircularReveal(android.view.View, int, int, float, float)}.
     * If not available (only lollipop and above), it uses the slide_in_bottom and stay_in_place animations.
     *
     * @param firstViewToAnimate The {@link android.view.View} to animate (blue background for instance)
     * @param viewToAnimateFrom  The {@link android.view.View} to animate from (where the user clicked)
     * @param marginFirstView    The {@link java.lang.Integer} representing the margin applied to firstViewToAnimate
     * @param intent             The {@link android.content.Intent} we want to launch at the end of the animation
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateTransitionCircularView(final Activity activity, final View firstViewToAnimate, View viewToAnimateFrom, int marginFirstView, final Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = (viewToAnimateFrom.getLeft() + viewToAnimateFrom.getRight()) / 2;
            int cy = (viewToAnimateFrom.getTop() + viewToAnimateFrom.getBottom()) / 2;
//            viewToAnimateFrom.setVisibility(View.INVISIBLE);
            int finalRadius = firstViewToAnimate.getWidth();

            Animator anim = ViewAnimationUtils.createCircularReveal(firstViewToAnimate, cx, cy - marginFirstView, 0, finalRadius);

            if (intent != null) {
                AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        activity.startActivity(intent);
                        activity.overridePendingTransition(0, 0);
                    }
                };

                anim.addListener(animatorListenerAdapter);
            }

            anim.setInterpolator(new AccelerateInterpolator());
            firstViewToAnimate.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.stay_in_place);
        }
    }

}
