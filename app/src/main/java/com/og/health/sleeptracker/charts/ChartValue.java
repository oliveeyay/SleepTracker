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

import com.github.mikephil.charting.data.Entry;
import com.og.health.sleeptracker.activities.WakeUpChartActivity;

import java.util.List;

/**
 * Created by olivier.goutay on 2/18/16.
 * Used to store a list of {@link Entry} and a list of {@link String}
 * to display in {@link WakeUpChartActivity}
 */
public class ChartValue {

    private List<Entry> mEntries;

    private List<String> mTexts;

    public ChartValue(List<Entry> entries, List<String> texts) {
        this.mEntries = entries;
        this.mTexts = texts;
    }

    public List<String> getTexts() {
        return mTexts;
    }

    public List<Entry> getEntries() {
        return mEntries;
    }
}
