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
package com.og.health.sleeptracker.views;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;

/**
 * Created by stoyan on 7/8/15.
 */
public class ProgressLineChartView extends LineChart {
    public ProgressLineChartView(Context context) {
        super(context);
    }

    public ProgressLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressLineChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getLowestVisibleXIndex() {//TODO fix these?
//        float[] pts = new float[]{this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom()};
//        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue(pts);
//        pts[0] = pts[0] - 20f; My change, should be dynamic to the largest gap in data?
//        return pts[0] <= 0.0F?0:(int)(pts[0]);
        return 0;
    }

    @Override
    public int getHighestVisibleXIndex() {//TODO fix these?
//        float[] pts = new float[]{this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom()};
//        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue(pts);
//        pts[0] = pts[0] + 20f; My change, should be dynamic to the largest gap in data?
//        return pts[0] >= (float)(this.mData).getXValCount()?(this.mData).getXValCount() - 1:(int)pts[0];
        return (this.mData).getXValCount() - 1;
    }
}
