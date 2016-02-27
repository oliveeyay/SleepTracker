package com.og.health.sleeptracker.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.og.health.sleeptracker.BuildConfig;
import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.lib.application.SleepTrackerApplication;

import io.fabric.sdk.android.Fabric;

/**
 * Created by olivier.goutay on 2/26/16.
 */
public class ExampleApplication extends SleepTrackerApplication {

    /**
     * The google analytics object
     */
    private static Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    public static synchronized Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(mContext);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
