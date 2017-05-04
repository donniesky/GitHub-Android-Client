package me.donnie.github.ui.profile.repos

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
class ReposAdapter : BaseQuickAdapter<Repo, BaseViewHolder> {

    constructor() : super(R.layout.list_user_repos_item, null)

    override fun convert(holder: BaseViewHolder, repo: Repo) {
        holder.setText(R.id.title, repo.name)
        holder.setText(R.id.desc, repo.description)
        var view: View = holder.getView(R.id.tags)
        (view as TagGroup).setTags("test1","test2","test3")
        holder.setText(R.id.language, repo.language)
        holder.setText(R.id.update_time, Util.friendly_time(repo.pushed_at))
    }

}