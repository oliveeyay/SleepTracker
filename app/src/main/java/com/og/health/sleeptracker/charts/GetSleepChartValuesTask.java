/**
 * Copyright 2013 Olivier Goutay (olivierg13)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.og.health.sleeptracker.charts;

import android.os.AsyncTask;

import com.github.mikephil.charting.data.Entry;
import com.og.health.sleeptracker.adapters.SleepChartAdapter;
import com.og.health.sleeptracker.interfaces.ChartInterface;
import com.og.health.sleeptracker.lib.application.SleepTrackerApplication;
import com.og.health.sleeptracker.schema.Record;
import com.og.health.sleeptracker.schema.SleepMovement;
import com.og.health.sleeptracker.schema.SleepMovementDao;
import com.og.health.sleeptracker.utilities.DateUtilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public class GetSleepChartValuesTask extends AsyncTask<Void, Void, ChartValue> {

    private static final float DEEP_VALUE = 0.1f;
    private static final float NORMAL_VALUE = 0.9f;


    /**
     * The {@link ChartInterface} callback
     */
    private ChartInterface mChartInterface;

    /**
     * The current {@link com.og.health.sleeptracker.schema.Record} being evaluated
     */
    private Record mRecord;

    /**
     * The current {@link com.og.health.sleeptracker.adapters.SleepChartAdapter.ViewHolder} we are working for
     */
    private SleepChartAdapter.ViewHolder mViewHolder;

    public GetSleepChartValuesTask(Record record, SleepChartAdapter.ViewHolder viewHolder, ChartInterface chartInterface) {
        this.mChartInterface = chartInterface;
        this.mRecord = record;
        this.mViewHolder = viewHolder;
    }

    @Override
    protected ChartValue doInBackground(Void... params) {
        SleepMovementDao sleepMovementDao = SleepTrackerApplication.getDaoSession().getSleepMovementDao();

        List<Entry> entries = new ArrayList<>();
        List<String> xValues = new ArrayList<>();

        int i = 0;
        Date previousDate = mRecord.getBeginning();
        Date nextDate = DateUtilities.getFiveMinutesAfter(previousDate);
        while (nextDate.getTime() < mRecord.getEnding().getTime()) {
            List<SleepMovement> sleepMovements = sleepMovementDao.queryBuilder()
                    .where(SleepMovementDao.Properties.MovementTime.between(previousDate, nextDate))
                    .orderAsc(SleepMovementDao.Properties.MovementTime).list();

            if (sleepMovements.size() > 0) {
                entries.add(new Entry(NORMAL_VALUE, i));
            } else {
                entries.add(new Entry(DEEP_VALUE, i));
            }

            xValues.add(DateUtilities.dateToString(nextDate, DateUtilities.DATE_SHORT_HOUR_STRING_FORMAT));

            previousDate = nextDate;
            nextDate = DateUtilities.getFiveMinutesAfter(previousDate);
            i++;
        }

        return new ChartValue(entries, xValues);
    }

    protected void onPostExecute(ChartValue chartValue) {
        super.onPostExecute(chartValue);

        mChartInterface.onSleepChartValuesRetrieved(mRecord, mViewHolder, chartValue);
    }
}
