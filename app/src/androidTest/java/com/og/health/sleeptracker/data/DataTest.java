package com.og.health.sleeptracker.data;

import com.og.health.sleeptracker.AbstractAndroidTestCase;
import com.og.health.sleeptracker.schema.Record;
import com.og.health.sleeptracker.schema.SleepMovement;
import com.og.health.sleeptracker.schema.WakeUp;
import com.og.health.sleeptracker.utilities.DateUtilities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by olivier.goutay on 2/26/16.
 */
public class DataTest extends AbstractAndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mResetDbOnTearDown = false;
    }

    /**
     * Call {@link #resetDatabaseAndPrefs()} to delete all
     */
    public void testDeleteAllData() {
        resetDatabaseAndPrefs();
    }

    /**
     * Create some data on the database and keep them in the app for visualization.
     */
    public void testInsertFakeData() {
        resetDatabaseAndPrefs();

        //Insert sleep
        for (int i = 0; i < 10; i++) {
            Calendar calendarSleep = Calendar.getInstance();
            calendarSleep.add(Calendar.DAY_OF_YEAR, -1);
            calendarSleep.set(Calendar.HOUR_OF_DAY, 22);

            Date beginning = calendarSleep.getTime();

            calendarSleep.add(Calendar.HOUR, 10);
            Date ending = calendarSleep.getTime();
            calendarSleep.add(Calendar.HOUR, -10);

            long recordId = dao.getRecordDao().insertOrReplace(new Record(null, beginning, ending));

            //Create movements for 3 hours
            for (int j = 0; j < 3 * 60; j += 2) {
                calendarSleep.add(Calendar.MINUTE, 2);
                dao.getSleepMovementDao().insertOrReplace(new SleepMovement(null, calendarSleep.getTime(), 0.0f, 0.0f, 0.0f, recordId));
            }

            //Nothing for one hour
            calendarSleep.add(Calendar.HOUR, 1);

            //Movement for 2 hours
            for (int j = 0; j < 2 * 60; j += 2) {
                calendarSleep.add(Calendar.MINUTE, 2);
                dao.getSleepMovementDao().insertOrReplace(new SleepMovement(null, calendarSleep.getTime(), 0.0f, 0.0f, 0.0f, recordId));
            }

            //Nothing for 30min
            calendarSleep.add(Calendar.MINUTE, 30);

            //Movements until the end
            for (int j = 0; j < 3 * 60 + 30; j += 2) {
                calendarSleep.add(Calendar.MINUTE, 2);
                dao.getSleepMovementDao().insertOrReplace(new SleepMovement(null, calendarSleep.getTime(), 0.0f, 0.0f, 0.0f, recordId));
            }

            //Go back 1 day
            calendarSleep.add(Calendar.DAY_OF_YEAR, -1);
        }

        //Insert wake up
        Calendar calendarWake = Calendar.getInstance();
        calendarWake.set(Calendar.HOUR_OF_DAY, 13);
        Date date = calendarWake.getTime();
        for (int i = 0; i < 10; i++) {
            dao.getWakeUpDao().insertOrReplace(new WakeUp(null, date));
            date = DateUtilities.getYesterday(date);
            date = DateUtilities.getMinusOneHour(date);
        }
    }
}
