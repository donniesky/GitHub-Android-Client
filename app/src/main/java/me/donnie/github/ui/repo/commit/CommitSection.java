package me.donnie.github.ui.repo.commit;

import com.chad.library.adapter.base.entity.SectionEntity;

import me.donnie.github.data.entity.Commit;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitSection extends SectionEntity<Commit> {

    private boolean isMore;

    public CommitSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public CommitSection(Commit commit) {
        super(commit);
    }
}
