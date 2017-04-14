package me.donnie.github.ui.event;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.donnie.github.R;
import me.donnie.github.common.transform.GlideRoundTransform;
import me.donnie.github.common.utils.SpannableBuilder;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.entity.Event;
import me.donnie.github.data.entity.EventType;

/**
 * @author donnieSky
 * @created_at 2017/4/13.
 * @description
 */

public class EventAdapter extends BaseQuickAdapter<Event, BaseViewHolder> {

    public EventAdapter() {
        super(R.layout.list_event_item, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, Event event) {
        SpannableBuilder spannableBuilder = SpannableBuilder.builder();
        spannableBuilder.append(event.getActor().getLogin())
                .append("  ");

        if (event.getType() == EventType.WatchEvent) {
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName());
            holder.setVisible(R.id.tv_desc, false);
            holder.setImageResource(R.id.type, R.drawable.ic_star_filled);
        } else if (event.getType() == EventType.ForkEvent){
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName())
                    .bold(" to ").append(event.getPayload().getForkee().getFull_name());
            holder.setVisible(R.id.tv_desc, false);
            holder.setImageResource(R.id.type, R.drawable.ic_fork);
        } else if (event.getType() == EventType.CreateEvent) {
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName());
            holder.setVisible(R.id.tv_desc, false);
            holder.setImageResource(R.id.type, R.drawable.ic_add);
        } else if (event.getType() == EventType.IssueCommentEvent) {
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName())
                    .append("#").append(event.getPayload().getIssue().getNumber()+"");
            holder.setImageResource(R.id.type, R.drawable.ic_question_answer);
            holder.setVisible(R.id.tv_desc, true);
            holder.setText(R.id.tv_desc, event.getPayload().getComment().getBody());
        } else if (event.getType() == EventType.PublicEvent) {
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName())
                    .bold(" public ");
            holder.setVisible(R.id.tv_desc, false);
            holder.setImageResource(R.id.type, R.drawable.ic_add);
        }

        TextView t = (TextView) holder.getView(R.id.tv_description);
        t.setText(spannableBuilder);
        holder.setText(R.id.tv_time, Util.friendly_time(event.getCreated_at()));

        Glide.with(mContext)
                .load(event.getActor().getAvatar_url())
                .transform(new GlideRoundTransform(mContext))
                .crossFade()
                .placeholder(R.color.gray)
                .into((ImageView) holder.getView(R.id.iv_user));
    }
}
