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

import com.og.health.sleeptracker.interfaces.ChartInterface;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public class GetSleepChartValuesTask extends AsyncTask<Void, Void, ChartValue> {

    /**
     * The {@link ChartInterface} callback
     */
    private ChartInterface mChartInterface;

    public GetSleepChartValuesTask(ChartInterface chartInterface) {
        this.mChartInterface = chartInterface;
    }

    @Override
    protected ChartValue doInBackground(Void... params) {
        //TODO retrieve from database and transform

        return null;
    }

    protected void onPostExecute(ChartValue chartValue) {
        super.onPostExecute(chartValue);

        mChartInterface.onSleepChartValuesRetrieved(chartValue);
    }
}
