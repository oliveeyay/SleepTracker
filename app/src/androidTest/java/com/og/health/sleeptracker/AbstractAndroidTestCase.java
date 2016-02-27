package com.og.health.sleeptracker;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

import com.og.health.sleeptracker.lib.application.SleepTrackerApplication;
import com.og.health.sleeptracker.lib.utilities.SharedPreferencesUtilities;
import com.og.health.sleeptracker.schema.DaoSession;

/**
 * Created by oliviergoutay on 10/8/15.
 * Gives you access to {@link com.og.health.sleeptracker.schema.DaoSession} and a bunch of things like that needed in the unit tests.
 * Don't provide access to the views or anything else.
 */
public class AbstractAndroidTestCase extends AndroidTestCase {

    private static final String TAG = "AbstractAndroidTestCase";

    public boolean mResetDbOnTearDown = true;

    public DaoSession dao;

    private int numberOfTry;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        numberOfTry = 0;
        createApplication();
        dao = SleepTrackerApplication.getDaoSession();

        if (dao == null) {
            throw new RuntimeException("dao not created yet");
        }

        resetDatabaseAndPrefs();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        if (mResetDbOnTearDown) {
            resetDatabaseAndPrefs();
        }
    }

    /**
     * Reset the {@link DaoSession} and the {@link SharedPreferencesUtilities}
     */
    protected void resetDatabaseAndPrefs() {
        dao.getWakeUpDao().deleteAll();
        dao.getSleepMovementDao().deleteAll();
        dao.getRecordDao().deleteAll();
        SharedPreferencesUtilities.deleteKey(getContext(), SharedPreferencesUtilities.SLEEP_TRACKER_SCREEN_OFF_TIME);
    }

    /**
     * Create the {@link SleepTrackerApplication} by calling {@link SleepTrackerApplication#setupDatabase(Context)}.
     * If init failed (often the database not ready, then we wait for 1 sec and recall the same function.
     * Will try 5 times.
     */
    private void createApplication() {
        if (numberOfTry >= 5) {
            throw new RuntimeException("Could not create application database in five times");
        }

        try {
            SleepTrackerApplication.setupDatabase(getContext());
        } catch (Exception e) {
            Log.e(TAG, "Error creating the app, retry in 1sec");
            numberOfTry++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                Log.e(TAG, "Error waiting to create the app");
            }
            createApplication();
        }
    }
}
