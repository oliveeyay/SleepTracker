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

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.charts.ChartValue;
import com.og.health.sleeptracker.charts.GetWakeUpChartValuesTask;
import com.og.health.sleeptracker.databinding.ActivityChartWakeupBinding;
import com.og.health.sleeptracker.enums.ChartType;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public class WakeUpChartActivity extends AbstractChartActivity {

    /**
     * The databinding of {@link WakeUpChartActivity}
     */
    ActivityChartWakeupBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chart_wakeup);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initChart(ChartType.WAKE_UP_CHART, mBinding.wakeupChartActivityLinechart);

        new GetWakeUpChartValuesTask(this).execute();
    }

    @Override
    public void onWakeUpChartValuesRetrieved(ChartValue chartValue) {
        if (chartValue == null || chartValue.getEntries() == null || chartValue.getTexts() == null) {
            return;
        }

        handleLineChartValues(ChartType.WAKE_UP_CHART, mBinding.wakeupChartActivityLinechart, chartValue);
    }

}
