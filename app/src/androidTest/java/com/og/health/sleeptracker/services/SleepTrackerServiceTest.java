package com.og.health.sleeptracker.services;

import android.hardware.SensorEvent;

import com.og.health.sleeptracker.AbstractAndroidTestCase;
import com.og.health.sleeptracker.lib.services.SleepTrackerService;

/**
 * Created by olivier.goutay on 2/17/16.
 */
public class SleepTrackerServiceTest extends AbstractAndroidTestCase {

    /**
     * Test that {@link com.og.health.sleeptracker.lib.services.SleepTrackerService#onSensorChanged(SensorEvent)} stores data
     */
    public void testSleepTrackerServiceStoreDataOnSensorChanged() {
        SleepTrackerService sleepTrackerService = new SleepTrackerService();

        //Test moves first time (register one event)
        float[] values = new float[] {300, 300, 300};
        sleepTrackerService.handleOnSensorChanged(getContext(), values);
        assertEquals(1, dao.getSleepMovementDao().count());

        //Test moves second time (less than the 0.20 threshold)
        values = new float[] {300.1f, 300.1f, 300.1f};
        sleepTrackerService.handleOnSensorChanged(getContext(), values);
        assertEquals(1, dao.getSleepMovementDao().count());

        //Test moves third time (more than the 0.20 threshold)
        values = new float[] {300.3f, 300.3f, 300.3f};
        sleepTrackerService.handleOnSensorChanged(getContext(), values);
        assertEquals(2, dao.getSleepMovementDao().count());
    }
}
