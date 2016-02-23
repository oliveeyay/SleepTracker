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
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.charts.ChartValue;
import com.og.health.sleeptracker.charts.GetSleepChartValuesTask;
import com.og.health.sleeptracker.charts.GetWakeUpChartValuesTask;
import com.og.health.sleeptracker.databinding.ActivityChartWakeupBinding;
import com.og.health.sleeptracker.interfaces.ChartInterface;
import com.og.health.sleeptracker.lib.receivers.ScreenOnOffReceiver;

import java.util.ArrayList;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public class ChartActivity extends AppCompatActivity implements ChartInterface {

    private final static float CHART_BOTTOM_TEXT_SIZE = 12f;
    private final static float CHART_VALUE_LINE_WIDTH = 2f;
    private final static float CHART_CIRCLE_SIZE = 5f;
    private final static int CHART_ANIM_TIME = 2500;

    /**
     * Save the previous range of the {@link LineChart} to reset it between the different type of charts.
     */
    private Integer mPreviousXRange;

    /**
     * The databinding of {@link ChartActivity}
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

        initCharts();

        new GetWakeUpChartValuesTask(this).execute();
        new GetSleepChartValuesTask(this).execute();
    }

    @Override
    public void onWakeUpChartValuesRetrieved(ChartValue chartValue) {
        if (chartValue == null || chartValue.getEntries() == null || chartValue.getTexts() == null) {
            return;
        }

        handleLineChartValues(mBinding.wakeupChartActivityLinechart, chartValue);
    }

    @Override
    public void onSleepChartValuesRetrieved(ChartValue chartValue) {
        if (chartValue == null || chartValue.getEntries() == null || chartValue.getTexts() == null) {
            return;
        }

        handleLineChartValues(mBinding.sleepChartActivityLinechart, chartValue);
    }

    private void handleLineChartValues(LineChart lineChart, ChartValue chartValue) {
        //Set of values (blue line)
        LineDataSet setValues = new LineDataSet(chartValue.getEntries(), "DataSet 1");
        setValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setValues.setColor(getResources().getColor(R.color.light_blue_300));
        setValues.setCircleColor(getResources().getColor(R.color.light_blue_300));
        setValues.setLineWidth(CHART_VALUE_LINE_WIDTH);
        setValues.setDrawCircleHole(false);
        setValues.setDrawValues(false);
        setValues.setFillAlpha(100);
        setValues.setFillColor(getResources().getColor(R.color.light_blue_300));
        setValues.setHighlightEnabled(false);
        setValues.setCircleSize(CHART_CIRCLE_SIZE);
        setValues.setValueTextSize(CHART_BOTTOM_TEXT_SIZE);

        //Data custom
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        //Reset the previous chart
        if (lineChart.getData() != null) {
            lineChart.moveViewToX(lineChart.getData().getXValCount());
        }
        lineChart.zoom(0.0f, 0.0f, 0.0f, 0.0f);
        if (mPreviousXRange != null) {
            lineChart.setVisibleXRange(0.0f, mPreviousXRange);
        }
        lineChart.clear();

        // set data
        lineChart.animateY(CHART_ANIM_TIME, Easing.EasingOption.EaseInOutQuart);

        // get the legend (only possible after setting data)
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setValues); // add the datasets
        LineData data = new LineData(chartValue.getTexts(), dataSets);
        lineChart.setData(data);

        //Set xaxis top values
//        lineChart.getXAxisTop().setValues(weightChartApi.getLabelsTop());
        mPreviousXRange = chartValue.getEntries().size();

        setChartZoom(lineChart);
    }

    /**
     * Zoom the {@link LineChart} on the 7 last values. (+ 1.5 for the virtual hidden value/padding)
     */
    private void setChartZoom(LineChart lineChart) {
        if (lineChart.getData() != null) {
            lineChart.setVisibleXRange(0.0f, 8.0f);
            lineChart.moveViewToX(lineChart.getData().getXValCount());
        }
    }

    /**
     * Init the charts colors, axis etc...
     */
    private void initCharts() {
        LineChart[] lineCharts = {mBinding.wakeupChartActivityLinechart, mBinding.sleepChartActivityLinechart};
        for (LineChart lineChart : lineCharts) {
            if (lineChart != null) {
                lineChart.setDrawGridBackground(false);

                // enable touch gestures
                lineChart.setTouchEnabled(true);

                // enable dragging
                lineChart.setDragEnabled(true);

                // disable zooming
                lineChart.setPinchZoom(false);
                lineChart.setScaleEnabled(false);
                lineChart.setDoubleTapToZoomEnabled(false);

                // set an alternative background color
                lineChart.setBackgroundResource(R.color.transparent);

                // disable highlight indicators (the lines that indicate the highlighted Entry)
                lineChart.setHighlightPerTapEnabled(false);
                lineChart.setHighlightPerDragEnabled(false);

                // XAxis custom
                XAxis xAxisBottom = lineChart.getXAxis();
                xAxisBottom.setLabelsToSkip(0);
                xAxisBottom.setTextColor(getResources().getColor(R.color.blue_grey_300));
                xAxisBottom.setTextSize(CHART_BOTTOM_TEXT_SIZE);
                xAxisBottom.setPosition(XAxis.XAxisPosition.BOTTOM);
//                xAxisBottom.setYOffset(getResources().getDimensionPixelSize(R.dimen.fragment_progress_weight_padding_small));
                xAxisBottom.setAxisLineWidth(0);

                // Yaxis custom
                YAxis leftAxis = lineChart.getAxisLeft();
                lineChart.getAxisRight().setEnabled(false);
                leftAxis.setEnabled(true);
                leftAxis.setDrawAxisLine(false);
                leftAxis.setDrawGridLines(false);

                // Set left Axis size
                leftAxis.setAxisMinValue(ScreenOnOffReceiver.MIN_WAKE_UP_TIME);
                leftAxis.setAxisMaxValue(ScreenOnOffReceiver.MAX_WAKE_UP_TIME);
            }
        }
    }
}
