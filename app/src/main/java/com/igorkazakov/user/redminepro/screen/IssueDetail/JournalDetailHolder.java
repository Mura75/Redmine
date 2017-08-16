package com.igorkazakov.user.redminepro.screen.IssueDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.igorkazakov.user.redminepro.R;
import com.igorkazakov.user.redminepro.database.entity.DetailEntity;
import com.igorkazakov.user.redminepro.database.entity.PriorityEntity;
import com.igorkazakov.user.redminepro.database.entity.StatusEntity;
import com.igorkazakov.user.redminepro.database.entity.TrackerEntity;
import com.igorkazakov.user.redminepro.database.entity.UserEntity;
import com.igorkazakov.user.redminepro.database.entity.VersionEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 03.08.17.
 */

public class JournalDetailHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.detailTextView)
    TextView mDetailTextView;

    private Context mContext;
    private DetailEntity mDetailEntity;

    public JournalDetailHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void bind(DetailEntity entity, IssueDetailPresenter issueDetailPresenter) {

        mDetailEntity = entity;
        String string = "";
        String prefixStr = "";
        String newValue = "";
        String oldValue = "";

        if (mDetailEntity.getProperty().equalsIgnoreCase("attr")) {
            switch (mDetailEntity.getName()) {
                case "assigned_to_id": {
                    if (mDetailEntity.getNewValue() != null) {
                        UserEntity user = issueDetailPresenter.
                                getUserById(Long.valueOf(mDetailEntity.getNewValue()));

                        if (user != null) {
                            newValue = user.getName();
                        }
                    }

                    if (mDetailEntity.getOldValue() != null) {
                        UserEntity user = issueDetailPresenter.
                                getUserById(Long.valueOf(mDetailEntity.getOldValue()));

                        if (user != null) {
                            oldValue = user.getName();
                        }
                    }
                }

                case "fixed_version_id": {
                    if (mDetailEntity.getNewValue() != null) {
                        VersionEntity version = issueDetailPresenter.
                                getVersionById(Long.valueOf(mDetailEntity.getNewValue()));

                        if (version != null) {
                            newValue = version.getName();
                        }
                    }

                    if (mDetailEntity.getOldValue() != null) {
                        VersionEntity version = issueDetailPresenter.
                                getVersionById(Long.valueOf(mDetailEntity.getOldValue()));

                        if (version != null) {
                            oldValue = version.getName();
                        }
                    }
                }

                case "status_id": {
                    if (mDetailEntity.getNewValue() != null) {
                        StatusEntity status = issueDetailPresenter.
                                getStatusById(Long.valueOf(mDetailEntity.getNewValue()));

                        if (status != null) {
                            newValue = status.getName();
                        }
                    }

                    if (mDetailEntity.getOldValue() != null) {
                        StatusEntity status = issueDetailPresenter.
                                getStatusById(Long.valueOf(mDetailEntity.getOldValue()));

                        if (status != null) {
                            oldValue = status.getName();
                        }
                    }
                }

                case "tracker_id": {
                    if (mDetailEntity.getNewValue() != null) {
                        TrackerEntity tracker = issueDetailPresenter.
                                getTrackerById(Long.valueOf(mDetailEntity.getNewValue()));

                        if (tracker != null) {
                            newValue = tracker.getName();
                        }
                    }

                    if (mDetailEntity.getOldValue() != null) {
                        TrackerEntity tracker = issueDetailPresenter.
                                getTrackerById(Long.valueOf(mDetailEntity.getOldValue()));

                        if (tracker != null) {
                            oldValue = tracker.getName();
                        }
                    }
                }

                case "priority_id": {
                    if (mDetailEntity.getNewValue() != null) {
                        PriorityEntity priority = issueDetailPresenter.
                                getPriorityById(Long.valueOf(mDetailEntity.getNewValue()));

                        if (priority != null) {
                            newValue = priority.getName();
                        }
                    }

                    if (mDetailEntity.getOldValue() != null) {
                        PriorityEntity priority = issueDetailPresenter.
                                getPriorityById(Long.valueOf(mDetailEntity.getOldValue()));

                        if (priority != null) {
                            oldValue = priority.getName();
                        }
                    }
                }
            }

        } else {
            if (mDetailEntity.getNewValue() != null) {
                newValue = mDetailEntity.getNewValue();
            }

            if (mDetailEntity.getOldValue() != null) {
                oldValue = mDetailEntity.getOldValue();
            }
        }


        if (mDetailEntity.getProperty().equalsIgnoreCase("attachment")) {
            prefixStr = "<b>File</b> ";

            string = string.concat(prefixStr);

            if (newValue != null && newValue.length() > 0) {
                string = string.concat(newValue + " <b>added</b>");

            } else {
                string = string.concat(oldValue + " <b>deleted</b>");
            }

        } else {

            switch (mDetailEntity.getName()) {
                case "parent_id":
                    prefixStr = "<b>Parent task</b> ";
                    break;

                case "tracker_id":
                    prefixStr = "<b>Tracker</b> ";
                    break;

                case "status_id":
                    prefixStr = "<b>Status</b> ";
                    break;

                case "fixed_version_id":
                    prefixStr = "<b>Target version</b> ";
                    break;

                case "subject":
                    prefixStr = "<b>Subject</b> ";
                    break;

                case "assigned_to_id":
                    prefixStr = "<b>Assignee</b> ";
                    break;

                default:
                    prefixStr = "";
            }

            string = string.concat(prefixStr);

            if (newValue != null && oldValue != null && newValue.length() > 0 && oldValue.length() > 0) {

                string = string.concat("changed from " + "<b>" + oldValue + "</b>" + " to " + "<b>" + newValue + "</b>");

            } else if (newValue != null && newValue.length() > 0) {

                string = string.concat("set to " + "<b>" + newValue + "</b>");
            }
        }

        mDetailTextView.setText(Html.fromHtml(string));
    }
}