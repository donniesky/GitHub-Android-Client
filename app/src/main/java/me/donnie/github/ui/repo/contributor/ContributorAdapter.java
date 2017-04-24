package me.donnie.github.ui.repo.contributor;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.donnie.github.R;
import me.donnie.github.data.entity.User;

/**
 * @author donnieSky
 * @created_at 2017/4/24.
 * @description
 */

public class ContributorAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public ContributorAdapter() {
        super(R.layout.list_contributor_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {

        Glide.with(mContext).load(item.getAvatar_url())
                .centerCrop().crossFade()
                .placeholder(R.color.gray)
                .into((ImageView)helper.getView(R.id.user_img));

        helper.setText(R.id.uname, item.getLogin());
    }
}
