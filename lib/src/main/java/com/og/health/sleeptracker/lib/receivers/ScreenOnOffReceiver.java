/**
 * Copyright 2013 Olivier Goutay (olivierg13)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.og.health.sleeptracker.lib.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.og.health.sleeptracker.lib.db.AbstractSleepTrackerDatabase;
import com.og.health.sleeptracker.lib.db.SleepTrackerDatabaseImpl;
import com.og.health.sleeptracker.lib.utilities.SharedPreferencesUtilities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by olivier.goutay on 2/17/16.
 * Allows to detect {@link Intent#ACTION_SCREEN_ON} and {@link Intent#ACTION_SCREEN_OFF}
 * to compute at what time the user wakes up approximately.
 */
public class ScreenOnOffReceiver extends BroadcastReceiver {

    private static final String TAG = "ScreenOnOffReceiver";

    /**
     * 5 hours in milliseconds, time required to detect a sleep time
     */
    private static final long TIME_TO_SLEEP = 5 * 60 * 60 * 1000;

    /**
     * The min wake up time to take in consideration, to avoid fake data is 4am
     */
    public static final long MIN_WAKE_UP_TIME = 4;

    /**
     * The max wake up time to take in consideration, to avoid fake data is 1pm (13)
     */
    public static final long MAX_WAKE_UP_TIME = 13;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            Log.d(TAG, "Screen off received");

            //Storing last time
            SharedPreferencesUtilities.storeLongForKey(context, SharedPreferencesUtilities.SLEEP_TRACKER_SCREEN_OFF_TIME, new Date().getTime());
        } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Log.d(TAG, "Screen on received");

            handleScreenWakeUp(context);
        }
    }

    /**
     * Is gonna call {@link #hasPersonSlept(Context)} to determine if we need to store the data in db.
     * If yes, then is gonna call {@link AbstractSleepTrackerDatabase#storeDeviceWakeUpTime()}
     *
     * @param context The current context of the app
     */
    public static void handleScreenWakeUp(Context context) {
        if (hasPersonSlept(context)) {
            AbstractSleepTrackerDatabase abstractSleepTrackerDatabase = new SleepTrackerDatabaseImpl();
            abstractSleepTrackerDatabase.storeDeviceWakeUpTime();
        }
    }

    /**
     * Determines if the person has slept depending on the {@link #TIME_TO_SLEEP} threshold.
     */
    public static boolean hasPersonSlept(Context context) {
        long currentScreenOn = new Date().getTime();
        long lastScreenOff = SharedPreferencesUtilities.getLongForKey(context, SharedPreferencesUtilities.SLEEP_TRACKER_SCREEN_OFF_TIME);

        return lastScreenOff != 0 && currentScreenOn - lastScreenOff > TIME_TO_SLEEP && isWithinWakeUpTimeRange();
    }

    /**
     * Determines if the current time is between {@link #MIN_WAKE_UP_TIME} and {@link #MAX_WAKE_UP_TIME}
     */
    public static boolean isWithinWakeUpTimeRange() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        return hour > MIN_WAKE_UP_TIME && hour < MAX_WAKE_UP_TIME;
    }

}
