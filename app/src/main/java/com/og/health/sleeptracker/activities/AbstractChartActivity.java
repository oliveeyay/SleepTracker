package com.og.health.sleeptracker.activities;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
import com.og.health.sleeptracker.adapters.SleepChartAdapter;
import com.og.health.sleeptracker.charts.ChartValue;
import com.og.health.sleeptracker.enums.ChartType;
import com.og.health.sleeptracker.interfaces.ChartInterface;
import com.og.health.sleeptracker.lib.receivers.ScreenOnOffReceiver;
import com.og.health.sleeptracker.schema.Record;

import java.util.ArrayList;

/**
 * Created by olivier.goutay on 2/26/16.
 */
public abstract class AbstractChartActivity extends AppCompatActivity implements ChartInterface {

    protected final static float CHART_VALUE_LINE_WIDTH = 2f;
    protected final static float CHART_CIRCLE_SIZE = 5f;
    protected final static int CHART_ANIM_TIME = 2500;
    protected final static float CHART_BOTTOM_TEXT_SIZE = 12f;

    protected final static float MIN_SLEEP_VALUE = 0f;
    protected final static float MAX_SLEEP_VALUE = 1f;

    /**
     * Save the previous range of the {@link LineChart} to reset it between the different type of charts.
     */
    private Integer mPreviousXRange;

    @Override
    public void onWakeUpChartValuesRetrieved(ChartValue chartValue) {
        //Dont do anything, just not forcing impl
    }

    @Override
    public void onSleepChartValuesRetrieved(Record record, SleepChartAdapter.ViewHolder viewHolder, ChartValue chartValue) {
        //Dont do anything, just not forcing impl
    }

    /**
     * Handle the {@link LineChart} {@link ChartValue} to show values
     */
    public void handleLineChartValues(ChartType chartType, LineChart lineChart, ChartValue chartValue) {
        //Set of values (blue line)
        LineDataSet setValues = new LineDataSet(chartValue.getEntries(), "DataSet 1");
        setValues.setAxisDependency(YAxis.AxisDependency.LEFT);
        setValues.setColor(ContextCompat.getColor(this, R.color.light_blue_300));
        setValues.setLineWidth(CHART_VALUE_LINE_WIDTH);
        setValues.setDrawValues(false);
        setValues.setFillAlpha(100);
        setValues.setFillColor(ContextCompat.getColor(this, R.color.light_blue_300));
        setValues.setHighlightEnabled(false);
        setValues.setValueTextSize(CHART_BOTTOM_TEXT_SIZE);
        setValues.setDrawCircleHole(false);

        //Data custom
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.resetLabelsToSkip();

        //Reset the previous chart
        if (lineChart.getData() != null) {
            lineChart.moveViewToX(lineChart.getData().getXValCount());
        }

        if (chartType == ChartType.WAKE_UP_CHART) {
            //Circles on wake up time
            setValues.setCircleRadius(CHART_CIRCLE_SIZE);
            setValues.setCircleColor(ContextCompat.getColor(this, R.color.light_blue_300));

            //Zooming on wake up time
            lineChart.zoom(0.0f, 0.0f, 0.0f, 0.0f);
            if (mPreviousXRange != null) {
                lineChart.setVisibleXRange(0.0f, mPreviousXRange);
            }
            lineChart.clear();
        } else {
            setValues.setDrawCircles(false);
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.gradient_blue);
            setValues.setFillDrawable(drawable);
            setValues.setDrawFilled(true);
        }

        // set data
        lineChart.animateY(CHART_ANIM_TIME, Easing.EasingOption.EaseInOutQuart);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setValues); // add the datasets
        LineData data = new LineData(chartValue.getTexts(), dataSets);
        lineChart.setData(data);

        //Set xaxis top values
        //lineChart.getXAxisTop().setValues(weightChartApi.getLabelsTop());
        mPreviousXRange = chartValue.getEntries().size();

        // get the legend (only possible after setting data)
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        legend.setFormSize(0f);

        if (chartType == ChartType.WAKE_UP_CHART) {
            setChartZoom(lineChart);
        }
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
    public void initChart(ChartType chartType, LineChart lineChart) {
        if (lineChart != null) {
            lineChart.setDrawGridBackground(false);
            lineChart.setDescription("");

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
            xAxisBottom.setTextColor(ContextCompat.getColor(this, R.color.blue_grey_300));
            xAxisBottom.setTextSize(CHART_BOTTOM_TEXT_SIZE);
            xAxisBottom.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxisBottom.setAxisLineWidth(0f);
            xAxisBottom.setAxisLineColor(ContextCompat.getColor(this, R.color.transparent));
            xAxisBottom.setDrawAxisLine(false);
            xAxisBottom.setDrawGridLines(false);

            // Yaxis custom
            YAxis leftAxis = lineChart.getAxisLeft();
            lineChart.getAxisRight().setEnabled(false);
            leftAxis.setEnabled(false);

            // Set left Axis size
            if (chartType == ChartType.WAKE_UP_CHART) {
                leftAxis.setAxisMinValue(ScreenOnOffReceiver.MIN_WAKE_UP_TIME - 1);
                leftAxis.setAxisMaxValue(ScreenOnOffReceiver.MAX_WAKE_UP_TIME + 1);
            } else {
                leftAxis.setAxisMinValue(MIN_SLEEP_VALUE);
                leftAxis.setAxisMaxValue(MAX_SLEEP_VALUE);
            }
        }
    }
}
