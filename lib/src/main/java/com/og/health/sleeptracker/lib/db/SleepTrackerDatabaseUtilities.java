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
package com.og.health.sleeptracker.lib.db;

import android.content.Context;
import android.util.Log;

import com.og.health.sleeptracker.lib.utilities.SharedPreferencesUtilities;

/**
 * Created by olivier.goutay on 2/17/16.
 * Allows to set and get the {@link AbstractSleepTrackerDatabase} implemented by the main app.
 */
public class SleepTrackerDatabaseUtilities {

    private static final String TAG = "AbstractSleepTrackerSer";

    /**
     * Allows to set the database class that is gonna be use in {@link com.og.health.sleeptracker.lib.services.SleepTrackerService}
     * and {@link com.og.health.sleeptracker.lib.services.ScreenOnOffService} to store the data persistently.
     *
     * @param dbClass The class that implements {@link AbstractSleepTrackerDatabase}.
     */
    public static void setDatabaseClass(Context context, Class<? extends AbstractSleepTrackerDatabase> dbClass) {
        SharedPreferencesUtilities.storeStringForKey(context, SharedPreferencesUtilities.SLEEP_TRACKER_DATABASE_IMPL_CLASS, dbClass.getCanonicalName());
    }

    /**
     * Returns the implementation of {@link AbstractSleepTrackerDatabase} in order to store the data.
     *
     * @return The main app class extending {@link AbstractSleepTrackerDatabase}
     */
    public static AbstractSleepTrackerDatabase getDatabaseClass(Context context) {
        try {
            String className = SharedPreferencesUtilities.getStringForKey(context, SharedPreferencesUtilities.SLEEP_TRACKER_DATABASE_IMPL_CLASS);

            if (className != null) {
                Class<?> clazz = Class.forName(className);
                return (AbstractSleepTrackerDatabase) clazz.newInstance();
            }
        } catch (Exception e) {
            Log.e(TAG, "An Exception happened during AbstractSleepTrackerService#getDatabaseClass()");
        }
        Log.e(TAG, SharedPreferencesUtilities.SLEEP_TRACKER_DATABASE_IMPL_CLASS + " is not defined");

        return null;
    }
}
