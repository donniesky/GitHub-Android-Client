package me.donnie.github.ui.trending;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.donnie.github.R;
import me.donnie.github.common.transform.GlideRoundTransform;
import me.donnie.github.common.utils.SpannableBuilder;
import me.donnie.github.data.entity.Trending;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class TrendingAdapter extends BaseQuickAdapter<Trending, BaseViewHolder> {

    public TrendingAdapter() {
        super(R.layout.list_trending_item, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, Trending trending) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();
        spannableBuilder.append(trending.getOwner())
                .append(" / ")
                .append(trending.getName());
        TextView view = (TextView) holder.getView(R.id.repo_owner);
        view.setText(spannableBuilder);

        holder.setText(R.id.desc, trending.getDescription());
        holder.setText(R.id.stars, trending.getStars()+"");
        holder.setText(R.id.forks, trending.getForks()+"");

        Glide.with(mContext)
                .load(trending.getAvatarUrl())
                .transform(new GlideRoundTransform(mContext))
                .crossFade()
                .placeholder(R.color.gray)
                .into((ImageView) holder.getView(R.id.img_owner));
    }
}
