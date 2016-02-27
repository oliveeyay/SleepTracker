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
package com.og.health.sleeptracker.schema;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.ToMany;

/**
 *
 */
public class RecordSchema extends AbstractSchema {

    //Record
    public static final String SCHEMA_RECORD_KEY = "Record";
    public static final String BEGINNING = "beginning";
    public static final String ENDING = "ending";

    //Sleep movement
    public static final String SCHEMA_SLEEP_MOVEMENT_KEY = "SleepMovement";
    public static final String RECORD_ID = "recordId";
    public static final String RECORD_NAME = "sleepMovements";
    public static final String MOVEMENT_TIME = "movementTime";
    public static final String MOVEMENT_X = "movementX";
    public static final String MOVEMENT_Y = "movementY";
    public static final String MOVEMENT_Z = "movementZ";

    @Override
    public void setSchemaProperties() {
        //Record
        Entity record = mSchema.addEntity(SCHEMA_RECORD_KEY);
        record.addIdProperty().autoincrement();
        record.addDateProperty(BEGINNING).unique();
        record.addDateProperty(ENDING).unique();

        //Sleep movement
        Entity sleepMovement = mSchema.addEntity(SCHEMA_SLEEP_MOVEMENT_KEY);
        sleepMovement.addIdProperty().autoincrement();
        sleepMovement.addDateProperty(MOVEMENT_TIME).unique();
        sleepMovement.addFloatProperty(MOVEMENT_X);
        sleepMovement.addFloatProperty(MOVEMENT_Y);
        sleepMovement.addFloatProperty(MOVEMENT_Z);

        //A sleep movement is linked to a record
        Property recordId = sleepMovement.addLongProperty(RECORD_ID).notNull().getProperty();
        ToMany recordToSleepMovements = record.addToMany(sleepMovement, recordId);
        recordToSleepMovements.setName(RECORD_NAME);
    }
}
