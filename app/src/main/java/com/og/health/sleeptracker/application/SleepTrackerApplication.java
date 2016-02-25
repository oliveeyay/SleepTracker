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
package com.og.health.sleeptracker.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.og.health.sleeptracker.db.SleepTrackerDatabaseImpl;
import com.og.health.sleeptracker.db.UpgradeHelper;
import com.og.health.sleeptracker.lib.db.SleepTrackerDatabaseUtilities;
import com.og.health.sleeptracker.schema.DaoMaster;
import com.og.health.sleeptracker.schema.DaoSession;
import com.og.health.sleeptracker.utilities.SharedPreferencesUtils;

import io.fabric.sdk.android.Fabric;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by olivier.goutay on 2/17/16.
 */
public class SleepTrackerApplication extends Application {

    private static final String TAG = "SleepTrackerApplication";

    /**
     * Name of the database
     */
    public static final String DATABASE_NAME = "sleep_tracker_encrypted.db";

    /**
     * The dao master we use to open the {@link DaoSession}
     */
    private static DaoMaster mDaoMaster;

    /**
     * The dao session we use to access the database
     */
    private static DaoSession mDaoSession;

    /**
     * Saving the app context
     */
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        mContext = getApplicationContext();
        setupDatabase(getApplicationContext());

        //Allows to give to the library the class that is gonna handle the database storage
        SleepTrackerDatabaseUtilities.setDatabaseClass(getApplicationContext(), SleepTrackerDatabaseImpl.class);
    }

    /**
     * Create/Open the database and registers a {@link DaoSession}
     * @param context the current context of the app
     */
    public static void setupDatabase(Context context) {
        if(mDaoSession != null) {
            return;
        }

        //Init the random seed
        String seed = SharedPreferencesUtils.getStringForKey(context, SharedPreferencesUtils.SEED_KEY);
        if (seed == null) {
            seed = SharedPreferencesUtils.generateRandomString();
            SharedPreferencesUtils.storeStringForKey(context, SharedPreferencesUtils.SEED_KEY, seed);
        }

        //Create the database
        Log.d(TAG, context.getFilesDir().getAbsolutePath());
        SQLiteDatabase.loadLibs(context);
        UpgradeHelper helper = new UpgradeHelper(context, DATABASE_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase(seed);
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static Context getContext() {
        return mContext;
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
