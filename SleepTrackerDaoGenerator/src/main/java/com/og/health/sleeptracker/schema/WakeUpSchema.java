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

/**
 *
 */
public class WakeUpSchema extends AbstractSchema {

    public static final String SCHEMA_KEY = "WakeUp";
    public static final String WAKE_UP_TIME = "wakeUpTime";

    @Override
    public void setSchemaProperties() {
        Entity wakeUp = mSchema.addEntity(SCHEMA_KEY);
        wakeUp.addIdProperty().autoincrement();
        wakeUp.addDateProperty(WAKE_UP_TIME).unique();
    }
}
