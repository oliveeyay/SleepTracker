package com.og.health.sleeptracker;

import com.og.health.sleeptracker.schema.RecordSchema;
import com.og.health.sleeptracker.schema.WakeUpSchema;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

/**
 * Database generator that creates the auto-gen sqlite files
 * Generator can only operate on one {@link de.greenrobot.daogenerator.Schema}, therefore all entities
 * must use a static {@link de.greenrobot.daogenerator.Schema} from {@link com.og.health.sleeptracker.schema.AbstractSchema}
 * that they add themselves to
 */
public class DatabaseGenerator {

    public static void main(String args[]) throws Exception {
        generateSchemas(args[0]);
    }

    private static void generateSchemas(String outputPath)  throws Exception{
        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(getSchema(), outputPath);
    }

    public static Schema getSchema(){
        RecordSchema recordSchema = new RecordSchema();
        WakeUpSchema wakeUpSchema = new WakeUpSchema();
        return wakeUpSchema.getSchema();
    }
}
