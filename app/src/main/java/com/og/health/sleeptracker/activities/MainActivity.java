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

    /**
     * Start the {@link SleepTrackerService} on click {@link com.og.health.sleeptracker.databinding.ActivityMainBinding#mainActivityStartButton}
     */
    public void startDimActivity(View view) {
        Intent intentDim = new Intent(this, DimActivity.class);
        startActivity(intentDim);
    }

    /**
     * Launches {@link ChartActivity} on click {@link com.og.health.sleeptracker.databinding.ActivityMainBinding#mainActivityWakeupButton}
     */
    public void wakeUpChart(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

}
