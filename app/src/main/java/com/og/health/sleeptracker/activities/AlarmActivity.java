package com.og.health.sleeptracker.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.og.health.sleeptracker.R;
import com.og.health.sleeptracker.databinding.ActivityAlarmBinding;

/**
 * Created by olivier.goutay on 2/27/16.
 */
public class AlarmActivity extends AppCompatActivity {

    /**
     * The databinding of {@link AlarmActivity}
     */
    private ActivityAlarmBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_alarm);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay_in_place, R.anim.slide_out_bottom);
    }
}
