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
package com.og.health.sleeptracker.db;

import com.og.health.sleeptracker.application.SleepTrackerApplication;
import com.og.health.sleeptracker.lib.db.AbstractSleepTrackerDatabase;
import com.og.health.sleeptracker.schema.SleepMovement;
import com.og.health.sleeptracker.schema.SleepMovementDao;
import com.og.health.sleeptracker.schema.WakeUp;
import com.og.health.sleeptracker.schema.WakeUpDao;

import java.util.Date;

/**
 * Created by olivier.goutay on 2/17/16.
 * Used to
 */
public class SleepTrackerDatabaseImpl extends AbstractSleepTrackerDatabase {

    @Override
    public void storeSleepMovementData(float[] values) {
        if (values.length >= 3) {
            SleepTrackerApplication.setupDatabase(SleepTrackerApplication.getContext());

            if (SleepTrackerApplication.getDaoSession() != null) {
                SleepMovementDao sleepMovementDao = SleepTrackerApplication.getDaoSession().getSleepMovementDao();
                sleepMovementDao.insertOrReplace(new SleepMovement(null, new Date(), values[0], values[1], values[2]));
            }
        }
    }

    @Override
    public void storeDeviceWakeUpTime() {
        SleepTrackerApplication.setupDatabase(SleepTrackerApplication.getContext());

        if (SleepTrackerApplication.getDaoSession() != null) {
            WakeUpDao wakeUpDao = SleepTrackerApplication.getDaoSession().getWakeUpDao();
            wakeUpDao.insertOrReplace(new WakeUp(null, new Date()));
        }
    }
}
