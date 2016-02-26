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
package com.og.health.sleeptracker.lib.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.og.health.sleeptracker.lib.db.AbstractSleepTrackerDatabase;
import com.og.health.sleeptracker.lib.db.SleepTrackerDatabaseUtilities;

/**
 * Created by olivier.goutay on 2/17/16.
 * Listen to the accelerometer to track your sleep.
 */
public class SleepTrackerService extends Service implements SensorEventListener {

    private static final String TAG = "SleepTrackerService";

    /**
     * The variation threshold meant to detect a movement
     * TODO test in real life
     */
    private static final float VARIATION_THRESHOLD = 0.2f;

    /**
     * Stores the previous value in these variables to compare with new ones (variation has to be 0.5)
     */
    private float mPreviousX;
    private float mPreviousY;
    private float mPreviousZ;

    /**
     * The {@link SensorManager} used to listen to {@link Sensor#TYPE_ACCELEROMETER}
     */
    private SensorManager mSensorManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service Started");

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Register this service to listen to accelerometer
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSensorManager.unregisterListener(this);
        Log.d(TAG, "Service Stopped");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event != null && event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            handleOnSensorChanged(getApplicationContext(), event.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do nothing?
    }

    /**
     * Handles the informations received by {@link #onSensorChanged(SensorEvent)}
     */
    public void handleOnSensorChanged(Context context, float[] values) {
        boolean somethingChanged = false;

        //Show text
        if (Math.abs(mPreviousX - values[0]) > VARIATION_THRESHOLD) {
            somethingChanged = true;
            mPreviousX = values[0];
        }
        if (Math.abs(mPreviousY - values[1]) > VARIATION_THRESHOLD) {
            somethingChanged = true;
            mPreviousY = values[1];
        }
        if (Math.abs(mPreviousZ - values[2]) > VARIATION_THRESHOLD) {
            somethingChanged = true;
            mPreviousZ = values[2];
        }

        //Store in db
        AbstractSleepTrackerDatabase abstractSleepTrackerDatabase = SleepTrackerDatabaseUtilities.getDatabaseClass(context);
        if (abstractSleepTrackerDatabase != null && somethingChanged) {
            abstractSleepTrackerDatabase.storeSleepMovementData(values);
        }
    }
}
