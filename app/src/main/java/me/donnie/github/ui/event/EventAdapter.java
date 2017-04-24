package me.donnie.github.ui.event;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.donnie.github.R;
import me.donnie.github.common.transform.GlideRoundTransform;
import me.donnie.github.common.utils.SpannableBuilder;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.entity.CommitModel;
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
            holder.setImageResource(R.id.type, R.drawable.ic_repo);
        } else if (event.getType() == EventType.IssueCommentEvent) {
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName())
                    .append("#").append(event.getPayload().getIssue().getNumber()+"");
            holder.setImageResource(R.id.type, R.drawable.ic_issue_comment);
            holder.setVisible(R.id.tv_desc, true);
            holder.setText(R.id.tv_desc, event.getPayload().getComment().getBody());
        } else if (event.getType() == EventType.PublicEvent) {
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName())
                    .bold(" public ");
            holder.setVisible(R.id.tv_desc, false);
            holder.setImageResource(R.id.type, R.drawable.ic_repo);
        } else if (event.getType() == EventType.IssuesEvent) {
            spannableBuilder.bold(event.getPayload().getAction().toLowerCase()+"  "+
                    mContext.getResources().getString(event.getType().getType()).toLowerCase()+"  ")
                    .append(event.getRepo().getName()+"#"+event.getPayload().getIssue().getNumber());
            holder.setVisible(R.id.tv_desc, true);
            holder.setText(R.id.tv_desc, event.getPayload().getIssue().getTitle());
            holder.setImageResource(R.id.type, R.drawable.ic_info_outline);
        } else if (event.getType() == EventType.PushEvent) {
            String[] strings = event.getPayload().getRef().split("/");
            spannableBuilder.bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(strings[strings.length-1]).append("  ").bold("at").append("  ")
                    .append(event.getRepo().getName());
            holder.setVisible(R.id.tv_desc, true);
            holder.setImageResource(R.id.type, R.drawable.ic_push);
            SpannableBuilder descBuilder = SpannableBuilder.builder();
            List<CommitModel> commits = event.getPayload().getCommits();
            for (int i = 0; i < commits.size(); i++) {
                descBuilder.foreground(commits.get(i).getSha().substring(0,7),
                        mContext.getResources().getColor(R.color.blue))
                .append("  ").append(commits.get(i).getMessage()).append("\n");
            }
            TextView desc = (TextView) holder.getView(R.id.tv_desc);
            desc.setText(descBuilder);
        } else if (event.getType() == EventType.PullRequestEvent) {
            spannableBuilder.bold(event.getPayload().getAction()).append(" ")
                    .bold(mContext.getResources().getString(event.getType().getType()).toLowerCase())
                    .append("  ").append(event.getRepo().getName())
                    .append("#").append(event.getPayload().getPull_request().getNumber()+"");
            holder.setVisible(R.id.tv_desc, true);
            holder.setImageResource(R.id.type, R.drawable.ic_pull_request);
            holder.setText(R.id.tv_desc, event.getPayload().getPull_request().getTitle() + "\n");
        } else if (event.getType() == EventType.GollumEvent) {
            spannableBuilder.bold(event.getPayload().getPages().get(0).getAction())
                    .append(" ").bold(" the  ")
                    .append(event.getRepo().getName()).append("  ")
                    .bold("wiki");

            holder.setVisible(R.id.tv_desc, true);
            holder.setText(R.id.tv_desc, event.getPayload().getPages().get(0).getTitle());
            holder.setImageResource(R.id.type, R.drawable.ic_wiki);
        } else if (event.getType() == EventType.PullRequestReviewCommentEvent) {
            spannableBuilder.bold("commented on pull request")
                    .append("  ").append(event.getRepo().getName())
                    .append("#").append(event.getPayload().getPull_request().getNumber()+"");
            holder.setImageResource(R.id.type, R.drawable.ic_issue_comment);
            holder.setVisible(R.id.tv_desc, true);
            holder.setText(R.id.tv_desc, event.getPayload().getComment().getBody());
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
