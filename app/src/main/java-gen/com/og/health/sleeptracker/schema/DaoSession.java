package com.og.health.sleeptracker.schema;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.og.health.sleeptracker.schema.SleepMovement;
import com.og.health.sleeptracker.schema.WakeUp;

import com.og.health.sleeptracker.schema.SleepMovementDao;
import com.og.health.sleeptracker.schema.WakeUpDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig sleepMovementDaoConfig;
    private final DaoConfig wakeUpDaoConfig;

    private final SleepMovementDao sleepMovementDao;
    private final WakeUpDao wakeUpDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        sleepMovementDaoConfig = daoConfigMap.get(SleepMovementDao.class).clone();
        sleepMovementDaoConfig.initIdentityScope(type);

        wakeUpDaoConfig = daoConfigMap.get(WakeUpDao.class).clone();
        wakeUpDaoConfig.initIdentityScope(type);

        sleepMovementDao = new SleepMovementDao(sleepMovementDaoConfig, this);
        wakeUpDao = new WakeUpDao(wakeUpDaoConfig, this);

        registerDao(SleepMovement.class, sleepMovementDao);
        registerDao(WakeUp.class, wakeUpDao);
    }
    
    public void clear() {
        sleepMovementDaoConfig.getIdentityScope().clear();
        wakeUpDaoConfig.getIdentityScope().clear();
    }

    public SleepMovementDao getSleepMovementDao() {
        return sleepMovementDao;
    }

    public WakeUpDao getWakeUpDao() {
        return wakeUpDao;
    }

}
