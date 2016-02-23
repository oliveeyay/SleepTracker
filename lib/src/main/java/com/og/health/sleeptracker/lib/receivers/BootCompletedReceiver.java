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

import com.og.health.sleeptracker.lib.services.ScreenOnOffService;

/**
 * Created by olivier.goutay on 2/17/16.
 * Allows to listen to Boot completion in order to register the {@link ScreenOnOffReceiver}
 */
public class BootCompletedReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompletedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d(TAG, "Boot completed received");

            startScreenOnOffService(context);
        }
    }

    /**
     * Starts the {@link ScreenOnOffService}
     */
    public static void startScreenOnOffService(Context context) {
        Intent intent = new Intent(context, ScreenOnOffService.class);
        context.startService(intent);
    }

}
