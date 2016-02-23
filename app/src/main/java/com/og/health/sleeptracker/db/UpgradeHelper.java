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
package com.og.health.sleeptracker.db;

import android.content.Context;
import android.util.Log;

import com.og.health.sleeptracker.schema.DaoMaster;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by oliviergoutay on 6/5/15.
 */
public class UpgradeHelper extends DaoMaster.OpenHelper {
    private static final String TAG = "UpgradeHelper";

    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * Here is where the calls to upgrade are executed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* i represent the version where the user is now and the class named with this number implies that is upgrading from i to i++ schema */
        for (int i = oldVersion; i < newVersion; i++) {
            try {
                /* New instance of the class that migrates from i version to i++ version named DBMigratorHelper{version that the db has on this moment} */
                AbstractMigrationHelper migrationHelper = getMigrationHelper(i + 1);

                if (migrationHelper != null) {
                    /* Upgrade the db */
                    migrationHelper.onUpgrade(db);
                }
            } catch (Exception e) {
                Log.e(TAG, "Could not migrate from schema from schema: " + i + " to " + i + 1);
                /* If something fail prevent the DB to be updated to future version if the previous version has not been upgraded successfully */
                DaoMaster.dropAllTables(db, true);
                onCreate(db);
                break;
            }
        }
    }

    public AbstractMigrationHelper getMigrationHelper(int version) throws Exception {
        return (AbstractMigrationHelper) Class.forName(this.getClass().getPackage().getName() + ".MigrateTo" + version + "Helper").newInstance();
    }
}