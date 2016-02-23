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

import de.greenrobot.daogenerator.Schema;

/**
 * An abstract class that holds the defaults for {@link de.greenrobot.daogenerator.Schema}
 * generation in our application's database. All {@link de.greenrobot.daogenerator.Schema}
 * generation should inherit from this class
 * <p></p>
 */
public abstract class AbstractSchema {
    /**
     * The database version code, increment by 1 every time new schema are to be added to
     * the database.
     * <p></p>
     * Warning : GreenDao does not perform db migrations between versions itself,
     * these have to be done manually
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * The package that all the schema fall into
     */
    public static final String DATABASE_PACKAGE = "com.og.health.sleeptracker.schema";

    /**
     * The database schema to be set in child classes and created in {@link com.og.health.sleeptracker.DatabaseGeneratorator}.
     * Initializes the mSchema object with {@link #DATABASE_VERSION} and {@link #DATABASE_PACKAGE}
     */
    protected static Schema mSchema = new Schema(DATABASE_VERSION, DATABASE_PACKAGE);

    /**
     * Default constructor. Sets the schema properties.
     */
    public AbstractSchema() {
        setSchemaProperties();
    }

    /**
     * Abstract method that should set all the properties on {@link #mSchema}
     */
    public abstract void setSchemaProperties();

    /**
     * Get method for schema object
     *
     * @return {@link #mSchema}
     */
    public Schema getSchema() {
        return mSchema;
    }

}
