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
package com.og.health.sleeptracker.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.databinding.ActivityMainBinding;
import com.og.health.sleeptracker.lib.receivers.BootCompletedReceiver;
import com.og.health.sleeptracker.lib.services.SleepTrackerService;
import com.og.health.sleeptracker.utilities.AnimationUtilities;

public class MainActivity extends AppCompatActivity {

    /**
     * The databinding of {@link MainActivity}
     */
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        BootCompletedReceiver.startScreenOnOffService(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.mainActivityCircularLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * Start the {@link SleepTrackerService} on click {@link com.og.health.sleeptracker.databinding.ActivityMainBinding#mainActivityStartButton}
     */
    public void startClicked(View view) {
        Intent intentDim = new Intent(this, DimActivity.class);
        startActivity(intentDim);
    }

    /**
     * Launches {@link WakeUpChartActivity} on click {@link com.og.health.sleeptracker.databinding.ActivityMainBinding#mainActivitySleepChartButton}
     */
    public void sleepChartClicked(View view) {
        Intent intent = new Intent(this, SleepChartActivity.class);
        startActivity(intent);
    }

    /**
     * Launches {@link WakeUpChartActivity} on click {@link com.og.health.sleeptracker.databinding.ActivityMainBinding#mainActivityWakeupChartButton}
     */
    public void wakeUpChartClicked(View view) {
        Intent intent = new Intent(this, WakeUpChartActivity.class);
        startActivity(intent);
    }

    /**
     * Launches {@link AlarmActivity} on click {@link com.og.health.sleeptracker.databinding.ActivityMainBinding#mainActivityAlarmButton}
     */
    public void alarmButtonClicked(View view) {
        Intent intent = new Intent(this, AlarmActivity.class);
        AnimationUtilities.animateTransitionCircularView(this, mBinding.mainActivityCircularLayout, mBinding.mainActivityAlarmButton, 0, intent);
    }

}
