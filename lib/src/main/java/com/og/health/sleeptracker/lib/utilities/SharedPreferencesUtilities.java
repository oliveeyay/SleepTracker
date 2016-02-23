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
package com.og.health.sleeptracker.lib.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by oliviergoutay on 11/21/14.
 */
public class SharedPreferencesUtilities {

    /**
     * The key for SharedPreferences (private mode, internal system storage)
     */
    public static final String SHARED_PREFERENCES = "SLEEP_TRACKER_SEED_SHARED_PREFERENCES";

    /**
     * Allows to store the class that is gonna handle the database storage of the sleep data
     */
    public static final String SLEEP_TRACKER_DATABASE_IMPL_CLASS = "SLEEP_TRACKER_DATABASE_IMPL_CLASS";

    /**
     * Allows to store the last time the screen was {@link android.content.Intent#ACTION_SCREEN_OFF}
     */
    public static final String SLEEP_TRACKER_SCREEN_OFF_TIME = "SLEEP_TRACKER_SCREEN_OFF_TIME";

    /**
     * Store a String into the private {@link SharedPreferences} of the app.
     *
     * @param context The current context of the app
     * @param key     The key we want to be used to store the string
     * @param value   The String we want to be stored
     */
    public static void storeStringForKey(Context context, String key, String value) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Get a String from the private {@link SharedPreferences} of the app
     *
     * @param context The current context of the app
     * @param key     The key we want to request
     * @return The string retrieved from the given key
     */
    public static String getStringForKey(Context context, String key) {
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    /**
     * Store a boolean into the private {@link SharedPreferences} of the app.
     *
     * @param context The current context of the app
     * @param key     The key we want to be used to store the string
     * @param value   The boolean we want to be stored
     */
    public static void storeBooleanForKey(Context context, String key, boolean value) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a boolean from the private {@link SharedPreferences} of the app
     *
     * @param context The current context of the app
     * @param key     The key we want to request
     * @return The boolean retrieved from the given key
     */
    public static boolean getBooleanForKey(Context context, String key) {
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    /**
     * Store a boolean into the private {@link SharedPreferences} of the app.
     *
     * @param context The current context of the app
     * @param key     The key we want to be used to store the string
     * @param value   The boolean we want to be stored
     */
    public static void storeLongForKey(Context context, String key, long value) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Get a boolean from the private {@link SharedPreferences} of the app
     *
     * @param context The current context of the app
     * @param key     The key we want to request
     * @return The boolean retrieved from the given key
     */
    public static long getLongForKey(Context context, String key) {
        if (context == null) {
            return 0l;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, 0);
    }

    /**
     * Delete a key from the private {@link SharedPreferences} of the app
     *
     * @param context The current context of the app
     * @param key     The key we want to delete
     */
    public static void deleteKey(Context context, String key) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

}
