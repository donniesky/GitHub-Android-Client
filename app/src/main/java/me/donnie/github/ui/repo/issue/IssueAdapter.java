package me.donnie.github.ui.repo.issue;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.donnie.github.R;
import me.donnie.github.common.utils.SpannableBuilder;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.entity.Issue;
import me.donnie.github.data.entity.StateType;

/**
 * @author donnieSky
 * @created_at 2017/4/19.
 * @description
 */

public class IssueAdapter extends BaseQuickAdapter<Issue, BaseViewHolder> {

    public IssueAdapter() {
        super(R.layout.list_issue_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Issue item) {
        SpannableBuilder builder = SpannableBuilder.builder();
        builder.bold("#"+item.getNumber()).append("  ");


        if (item.getState() == StateType.open) {
            builder.append(mContext.getResources().getString(item.getState().getType()))
                    .append("  ").append(Util.friendly_time(item.getCreated_at()))
                    .append("  by  ").append(item.getUser().getLogin());
        } else if (item.getState() == StateType.closed) {
            builder.append("  by  ").append(item.getUser().getLogin()).append("  ")
                    .append("  was  ")
                    .append(mContext.getResources().getString(item.getState().getType()))
                    .append("  ").append(Util.friendly_time(item.getClosed_at()));
        }



        helper.setText(R.id.title, item.getTitle());
        TextView time = (TextView) helper.getView(R.id.time);
        time.setText(builder);

        helper.setText(R.id.cmt_count, item.getComments()+"");
    }
}
