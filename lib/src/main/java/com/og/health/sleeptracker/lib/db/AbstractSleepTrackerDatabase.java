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

import android.hardware.SensorEvent;

/**
 * Created by olivier.goutay on 2/17/16.
 */
public abstract class AbstractSleepTrackerDatabase {

    /**
     * Interface to store the movements detected by {@link com.og.health.sleeptracker.lib.services.SleepTrackerService#onSensorChanged(SensorEvent)}
     */
    public abstract void storeSleepMovementData(float[] values);

    /**
     * Interface to store the device wake up event detected by {@link com.og.health.sleeptracker.lib.receivers.ScreenOnOffReceiver}
     */
    public abstract void storeDeviceWakeUpTime();
}
