package com.igorkazakov.user.redminepro.database.dao;

import com.igorkazakov.user.redminepro.database.entity.IssueEntity;
import com.igorkazakov.user.redminepro.models.IssueModel;
import com.igorkazakov.user.redminepro.utils.PreferenceUtils;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 25.07.17.
 */

public class IssueEntityDAO extends BaseDaoImpl<IssueEntity, Long> {

    public IssueEntityDAO(ConnectionSource connectionSource, Class<IssueEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<IssueEntity> getAll() {
        List<IssueEntity> issueEntities = new ArrayList();
        try {
            issueEntities = this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return issueEntities;
        }
    }

    public void saveIssueEntity(IssueEntity timeEntryEntity) {

        if (timeEntryEntity == null) {
            return;
        }

        try {

            createOrUpdate(timeEntryEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveIssueEntities(List<IssueEntity> issueEntities) {

        if (issueEntities == null) {
            return;
        }

        for (IssueEntity issueEntity : issueEntities) {
            try {
                this.createOrUpdate(issueEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<IssueModel> getMyIssues() {

        List<IssueEntity> issueEntities;
        List<IssueModel> issueModels = null;
        long userId = PreferenceUtils.getInstance().getUserId();

        try {
            issueEntities = this.queryBuilder().where().eq("assigned_to_id", String.valueOf(userId)).query();

            for(IssueEntity issue: issueEntities) {

                issueModels.add(new IssueModel(issue.getProjectName(),
                        String.valueOf(issue.getId()),
                        issue.getSubject()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return issueModels;
    }
}