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
package com.og.health.sleeptracker.utilities;

import com.google.android.gms.analytics.HitBuilders;
import com.og.health.sleeptracker.BuildConfig;
import com.og.health.sleeptracker.application.ExampleApplication;

/**
 * Created by olivier.goutay on 2/25/16.
 */
public final class AnalyticsUtilities {

    /**
     * Tracks an event towards Google analytics {@link com.google.android.gms.analytics.Tracker}
     *
     * @param category The category we want that event to be put into
     * @param action   The action produced by the user
     */
    public static void trackEvent(String category, String action) {
        if (!BuildConfig.DEBUG) {
            ExampleApplication.getDefaultTracker().send(new HitBuilders.EventBuilder()
                    .setCategory(category)
                    .setAction(action)
                    .build());
        }
    }
}
