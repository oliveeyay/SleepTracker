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
package com.og.health.sleeptracker.lib.db;

import java.security.SecureRandom;

/**
 * Created by olivier.goutay on 2/17/16.
 * Allows to set and get the {@link AbstractSleepTrackerDatabase} implemented by the main app.
 */
public class SleepTrackerDatabaseUtilities extends com.og.health.sleeptracker.lib.utilities.SharedPreferencesUtilities {

    private static final String TAG = "AbstractSleepTrackerSer";

    /**
     * The length of the pseudo random constructed string
     */
    private static final int SEED_LENGTH = 64;

    /**
     * The space used by {@link java.util.Random#nextInt(int)}
     */
    private static final int SEED_SPACE = 96;

    /**
     * The key to save the seed in SharedPreferences (private mode, internal system storage)
     */
    public static final String SEED_KEY = "SLEEP_TRACKER_SEED_KEY";

    /**
     * Generate a random string (used for database encryption)
     *
     * @return a random string of 64 characters
     */
    public static String generateRandomString() {
        SecureRandom generator = new SecureRandom();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        int numbChar = 0;
        while (numbChar < SEED_LENGTH) {
            tempChar = (char) (generator.nextInt(SEED_SPACE) + 32);
            boolean num = tempChar >= 48 && tempChar < 58;//0-9
            boolean cap = tempChar >= 65 && tempChar < 91;//A-Z
            boolean lower = tempChar >= 65 && tempChar < 91;//a-z
            if (num || cap || lower) {
                randomStringBuilder.append(tempChar);
                numbChar++;
            }
        }
        return randomStringBuilder.toString();
    }
}
