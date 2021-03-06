package com.igorkazakov.user.redminepro.screen.Issue_detail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.igorkazakov.user.redminepro.R;
import com.igorkazakov.user.redminepro.database.room.entity.JournalsEntity;
import com.igorkazakov.user.redminepro.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 03.08.17.
 */

public class JournalHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.titleTextView)
    TextView mTitleTextView;

    @BindView(R.id.notesTextView)
    TextView mNotesTextView;

    @BindView(R.id.detailJournalList)
    RecyclerView mDetailJournalList;

    private Context mContext;
    private JournalsEntity mJournalEntity;

    public JournalHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }

    public void bind(JournalsEntity entity, IssueDetailPresenter issueDetailPresenter) {

        mJournalEntity = entity;

        String text = String.format(mContext.getResources().getString(R.string.title_journal_issue_detail),
                String.valueOf(mJournalEntity.getUser().getShortUserName()),
                String.valueOf(DateUtils.timeDifference(
                        DateUtils.dateFromString(mJournalEntity.getCreatedOn(),
                                DateUtils.getDateFormatterWithTime()))));
        mTitleTextView.setText(Html.fromHtml(text));

        mDetailJournalList.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        if (mJournalEntity.getNotes() != null && mJournalEntity.getNotes().length() != 0) {
            mNotesTextView.setVisibility(View.VISIBLE);
            mNotesTextView.setText(mJournalEntity.getNotes());

        } else {
            mNotesTextView.setVisibility(View.GONE);
        }

        issueDetailPresenter.fetchDetails(entity.getId(), result -> {
            if (!result.isEmpty()) {
                JournalDetailAdapter journalAdapter =
                        new JournalDetailAdapter(result, issueDetailPresenter);
                mDetailJournalList.setAdapter(journalAdapter);
            }
        });
    }
}