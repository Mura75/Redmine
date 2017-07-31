package com.igorkazakov.user.redminepro.database.dao;

import com.igorkazakov.user.redminepro.database.entity.JournalEntity;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31.07.17.
 */

public class JournalEntityDAO extends BaseDaoImpl<JournalEntity, Long> {

    public JournalEntityDAO(ConnectionSource connectionSource, Class<JournalEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<JournalEntity> getAll() {
        List<JournalEntity> entityList = new ArrayList();
        try {
            entityList = this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return entityList;
        }
    }

    public void saveJournalEntity(JournalEntity entity) {

        if (entity == null) {
            return;
        }

        try {

            createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveJournalEntities(List<JournalEntity> entityList) {

        if (entityList == null) {
            return;
        }

        for (JournalEntity item : entityList) {
            try {
                this.createOrUpdate(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}