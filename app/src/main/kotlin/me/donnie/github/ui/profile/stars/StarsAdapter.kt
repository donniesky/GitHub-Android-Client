package me.donnie.github.ui.profile.stars

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.donnie.github.R
import me.donnie.github.common.utils.Util
import me.donnie.github.data.entity.Repo
import me.gujun.android.taggroup.TagGroup

/**
 * @author donnieSky
 * @created_at 2017/5/3.
 * @description
 * @version
 */
class StarsAdapter : BaseQuickAdapter<Repo, BaseViewHolder> {

    constructor() : super(R.layout.list_user_stars_item, null)

    override fun convert(holder: BaseViewHolder, repo: Repo) {
        holder.setText(R.id.title, repo.fullName)
        holder.setText(R.id.desc, repo.description)
        val view: View = holder.getView(R.id.tags)
        (view as TagGroup).setTags("test1","test2","test3")
        holder.setText(R.id.language, repo.language)
        holder.setText(R.id.stars, repo.stargazers_count.toString())
        holder.setText(R.id.forks, repo.forks.toString())
        holder.setText(R.id.update_time, Util.friendly_time(repo.pushed_at))
    }

}