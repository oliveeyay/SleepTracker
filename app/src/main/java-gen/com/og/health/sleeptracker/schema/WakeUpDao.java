package com.og.health.sleeptracker.schema;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.og.health.sleeptracker.schema.WakeUp;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WAKE_UP".
*/
public class WakeUpDao extends AbstractDao<WakeUp, Long> {

    public static final String TABLENAME = "WAKE_UP";

    /**
     * Properties of entity WakeUp.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property WakeUpTime = new Property(1, java.util.Date.class, "wakeUpTime", false, "WAKE_UP_TIME");
    };


    public WakeUpDao(DaoConfig config) {
        super(config);
    }
    
    public WakeUpDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WAKE_UP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WAKE_UP_TIME\" INTEGER UNIQUE );"); // 1: wakeUpTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WAKE_UP\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, WakeUp entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date wakeUpTime = entity.getWakeUpTime();
        if (wakeUpTime != null) {
            stmt.bindLong(2, wakeUpTime.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public WakeUp readEntity(Cursor cursor, int offset) {
        WakeUp entity = new WakeUp( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)) // wakeUpTime
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, WakeUp entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWakeUpTime(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(WakeUp entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(WakeUp entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}