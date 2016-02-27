package com.og.health.sleeptracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.activities.AbstractChartActivity;
import com.og.health.sleeptracker.application.ExampleApplication;
import com.og.health.sleeptracker.charts.ChartValue;
import com.og.health.sleeptracker.charts.GetSleepChartValuesTask;
import com.og.health.sleeptracker.enums.ChartType;
import com.og.health.sleeptracker.interfaces.ChartInterface;
import com.og.health.sleeptracker.schema.Record;
import com.og.health.sleeptracker.schema.RecordDao;
import com.og.health.sleeptracker.utilities.DateUtilities;

import java.util.List;

/**
 * Created by olivier.goutay on 2/26/16.
 * {@link #onBindViewHolder(ViewHolder, int)} is gonna launch a {@link GetSleepChartValuesTask}
 * asynchronously and gonna get back to the UI {@link Thread} with {@link #onSleepChartValuesRetrieved(Record, ViewHolder, ChartValue)}.
 */
public class SleepChartAdapter extends RecyclerView.Adapter<SleepChartAdapter.ViewHolder> implements ChartInterface {

    /**
     * The current {@link AbstractChartActivity} to call {@link AbstractChartActivity#initChart(ChartType, LineChart)}
     */
    private AbstractChartActivity mChartActivity;

    /**
     * The list of all the {@link Record} init in the constructor
     */
    private List<Record> mRecords;

    public SleepChartAdapter(AbstractChartActivity abstractChartActivity) {
        this.mChartActivity = abstractChartActivity;
        this.mRecords = ExampleApplication.getDaoSession().getRecordDao().queryBuilder().orderDesc(RecordDao.Properties.Beginning).list();
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    @Override
    public SleepChartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_chart_sleep, parent, false);
        ViewHolder vh = new ViewHolder(v);

        mChartActivity.initChart(ChartType.SLEEP_CHART, vh.mLineChart);
        vh.mLineChart.setNoDataText("");

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mRecords.size() > position) {
            //Cancelling previous asynctask
            if(holder.mTask != null) {
                holder.mTask.cancel(true);
            }

            //Reset current chart
            holder.mLineChart.clear();

            //Getting the record
            Record record = mRecords.get(position);

            //Set title
            holder.mTextView.setText(DateUtilities.datesToString(record.getBeginning(), record.getEnding()));

            //Creating new asynctask
            holder.mLineChart.setTag(record.getId());
            holder.mTask = new GetSleepChartValuesTask(record, holder, this);
            holder.mTask.execute();
        }
    }

    @Override
    public void onSleepChartValuesRetrieved(Record record, ViewHolder holder, ChartValue chartValue) {
        if (record == null || holder == null || chartValue == null || chartValue.getEntries() == null || chartValue.getTexts() == null) {
            return;
        }

        //Removing finished task
        holder.mTask = null;

        //Updating the Chart
        if (holder.mLineChart.getTag() == record.getId()) {
            mChartActivity.handleLineChartValues(ChartType.SLEEP_CHART, holder.mLineChart, chartValue);
        }
    }

    /**
     * The ViewHolder of this cell
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public LineChart mLineChart;
        public GetSleepChartValuesTask mTask;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.sleep_chart_activity_textview);
            mLineChart = (LineChart) view.findViewById(R.id.sleep_chart_activity_linechart);
        }
    }

    @Override
    public void onWakeUpChartValuesRetrieved(ChartValue chartValue) {
        //Do nothing
    }
}
