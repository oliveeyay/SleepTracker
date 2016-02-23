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
package com.og.health.sleeptracker.charts;

import android.os.AsyncTask;

import com.github.mikephil.charting.data.Entry;
import com.og.health.sleeptracker.application.SleepTrackerApplication;
import com.og.health.sleeptracker.interfaces.ChartInterface;
import com.og.health.sleeptracker.schema.WakeUp;
import com.og.health.sleeptracker.schema.WakeUpDao;
import com.og.health.sleeptracker.utilities.DateUtilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public class GetWakeUpChartValuesTask extends AsyncTask<Void, Void, ChartValue> {

    /**
     * The {@link ChartInterface} callback
     */
    private ChartInterface mChartInterface;

    public GetWakeUpChartValuesTask(ChartInterface chartInterface) {
        this.mChartInterface = chartInterface;
    }

    @Override
    protected ChartValue doInBackground(Void... params) {
        WakeUpDao wakeUpDao = SleepTrackerApplication.getDaoSession().getWakeUpDao();
        List<WakeUp> wakeUps = wakeUpDao.loadAll();

        List<Entry> entries = new ArrayList<>();
        List<String> xValues = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        //Add an empty space
        xValues.add("");

        int i = 1;
        for (WakeUp wakeUp : wakeUps) {
            Date date = wakeUp.getWakeUpTime();

            calendar.setTime(date);
            float wakeUpTimeInFloat = ((float) calendar.get(Calendar.HOUR_OF_DAY)) + ((float) calendar.get(Calendar.MINUTE)) / 60.0f;
            entries.add(new Entry(wakeUpTimeInFloat, i));

            xValues.add(DateUtilities.dateToString(date, DateUtilities.DATE_SHORT_MONTH_DAY_HOUR_MINUTE_STRING_FORMAT));

            i++;
        }

        //Add an empty space
        xValues.add("");

        return new ChartValue(entries, xValues);
    }

    protected void onPostExecute(ChartValue chartValue) {
        super.onPostExecute(chartValue);

        mChartInterface.onWakeUpChartValuesRetrieved(chartValue);
    }
}
