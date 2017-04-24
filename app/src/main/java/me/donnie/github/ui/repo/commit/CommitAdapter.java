package me.donnie.github.ui.repo.commit;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.donnie.github.R;
import me.donnie.github.common.transform.GlideRoundTransform;
import me.donnie.github.common.utils.SpannableBuilder;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.entity.Commit;
import me.donnie.github.data.entity.User;

/**
 * @author donnieSky
 * @created_at 2017/4/21.
 * @description
 */

public class CommitAdapter extends BaseQuickAdapter<Commit, BaseViewHolder> {

    public CommitAdapter() {
        super(R.layout.list_commit_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Commit item) {

        User author = item.getAuthor();

        Glide.with(mContext).load(author != null ?
        item.getAuthor().getAvatar_url() : null)
                .crossFade()
                .transform(new GlideRoundTransform(mContext))
                .placeholder(R.color.gray)
                .into((ImageView)helper.getView(R.id.user_img));

        helper.setText(R.id.desc, item.getCommit().getMessage());

        SpannableBuilder builder = SpannableBuilder.builder();
        builder.bold(author != null ? item.getAuthor().getLogin() :
        item.getCommit().getAuthor().getName()).append("  ")
                .append("committed on  ").append(Util.friendly_time(item.getCommit()
                .getAuthor().getDate()));

        TextView view = (TextView)helper.getView(R.id.time);
        view.setText(builder);

        helper.setText(R.id.count, item.getCommit().getComment_count()+"");
    }
}
