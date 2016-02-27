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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.adapters.SleepChartAdapter;
import com.og.health.sleeptracker.databinding.ActivityChartSleepBinding;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public class SleepChartActivity extends AbstractChartActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * The databinding of {@link SleepChartActivity}
     */
    ActivityChartSleepBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chart_sleep);

        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Init the {@link RecyclerView} with {@link SleepChartAdapter}
     */
    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_chart_sleep_recycler_view);

        //Better performances with fixed size of cells
        mRecyclerView.setHasFixedSize(true);

        //Adapter
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SleepChartAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

}
