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
package com.og.health.sleeptracker.interfaces;

import com.github.mikephil.charting.data.Entry;
import com.og.health.sleeptracker.activities.WakeUpChartActivity;
import com.og.health.sleeptracker.adapters.SleepChartAdapter;
import com.og.health.sleeptracker.charts.ChartValue;
import com.og.health.sleeptracker.schema.Record;

/**
 * Created by olivier.goutay on 2/18/16.
 * Allows {@link WakeUpChartActivity} to get back the list of {@link Entry}
 */
public interface ChartInterface {

    /**
     * Allows to get back the WakeUp Chart values
     */
    public void onWakeUpChartValuesRetrieved(ChartValue chartValue);

    /**
     * Allows to get back the Sleep Chart values
     */
    public void onSleepChartValuesRetrieved(Record record, SleepChartAdapter.ViewHolder viewHolder, ChartValue chartValue);
}
