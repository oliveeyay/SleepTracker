package com.og.health.sleeptracker.receivers;

import android.content.Context;
import android.content.Intent;

import com.og.health.sleeptracker.AbstractAndroidTestCase;
import com.og.health.sleeptracker.lib.receivers.ScreenOnOffReceiver;
import com.og.health.sleeptracker.lib.utilities.SharedPreferencesUtilities;

import java.util.Calendar;

/**
 * Created by olivier.goutay on 2/17/16.
 * Test the implementation of {@link ScreenOnOffReceiver}
 */
public class ScreenOnOffReceiverTest extends AbstractAndroidTestCase {

    /**
     * Test {@link ScreenOnOffReceiver#onReceive(Context, Intent)} with {@link Intent#ACTION_SCREEN_OFF}
     */
    public void testScreenOffService() {
        ScreenOnOffReceiver screenOnOffReceiver = new ScreenOnOffReceiver();

        //No value by default
        assertEquals(0, SharedPreferencesUtilities.getLongForKey(getContext(), SharedPreferencesUtilities.SLEEP_TRACKER_SCREEN_OFF_TIME));

        //Should update the value
        Intent off = new Intent(Intent.ACTION_SCREEN_OFF);
        screenOnOffReceiver.onReceive(getContext(), off);
        assertNotSame(0, SharedPreferencesUtilities.getLongForKey(getContext(), SharedPreferencesUtilities.SLEEP_TRACKER_SCREEN_OFF_TIME));
    }

    /**
     * Test {@link ScreenOnOffReceiver#onReceive(Context, Intent)} with {@link Intent#ACTION_SCREEN_ON}
     */
    public void testScreenOnService() {
        ScreenOnOffReceiver screenOnOffReceiver = new ScreenOnOffReceiver();

        //Test with less than 5 hours ==> no entry
        Intent off = new Intent(Intent.ACTION_SCREEN_OFF);
        screenOnOffReceiver.onReceive(getContext(), off);
        Intent on = new Intent(Intent.ACTION_SCREEN_ON);
        screenOnOffReceiver.onReceive(getContext(), on);
        assertEquals(0, dao.getWakeUpDao().count());

        //Test more than 5 hours ==> should return an entry
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -6);
        SharedPreferencesUtilities.storeLongForKey(getContext(), SharedPreferencesUtilities.SLEEP_TRACKER_SCREEN_OFF_TIME, calendar.getTime().getTime());
        screenOnOffReceiver.onReceive(getContext(), on);
        assertEquals(1, dao.getWakeUpDao().count());
    }
}
