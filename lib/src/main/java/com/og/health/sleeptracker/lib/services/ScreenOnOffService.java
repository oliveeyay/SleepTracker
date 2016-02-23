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
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.og.health.sleeptracker.lib.receivers.ScreenOnOffReceiver;

/**
 * Created by olivier.goutay on 2/17/16.
 */
public class ScreenOnOffService extends Service {

    private static final String TAG = "ScreenOnOffService";

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

        registerScreenOnOffReceiver(getApplicationContext());
    }

    /**
     * Registers manually the {@link Intent#ACTION_SCREEN_ON} and {@link Intent#ACTION_SCREEN_OFF}
     * as it does not work in the manifest
     */
    public static void registerScreenOnOffReceiver(Context context) {
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        context.registerReceiver(new ScreenOnOffReceiver(), screenStateFilter);
    }

}
