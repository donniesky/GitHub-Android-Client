package me.donnie.github.ui.profile.overview

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_overview.*
import me.donnie.github.R
import me.donnie.github.common.base.LazyFragment
import me.donnie.github.common.transform.GlideCircleTransform
import me.donnie.github.data.entity.User
import me.donnie.github.ui.profile.ProfileActivity
import me.donnie.github.ui.profile.ProfileComponent
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/4/28.
 * @description
 * @version
 */
class OverViewFragment : LazyFragment(),OverViewContract.View {

    @Inject
    lateinit var presenter: OverViewContract.Presenter

    @Inject
    lateinit var navigator: OverViewContract.Navigator

    val profileComponent: ProfileComponent? get() = (activity as? ProfileActivity)?.mProfileComponent

    var overViewComponent: OverViewComponent? = null

    companion object {
        @JvmStatic fun newInstance(): OverViewFragment {
            val args = Bundle()
            val fragment = OverViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupComponent() {
        overViewComponent = profileComponent?.plus(OverViewModule())
        overViewComponent?.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_overview
    }

    override fun initView(view: View?) {
        presenter.attachView(this)
    }

    override fun initData() {
        lazyLoad()
    }

    override fun lazyLoad() {
        if (!isVisible && !isPrepared) {
            return
        }
        presenter.loadUser()
    }

    override fun loadUserSuccess(user: User) {
        uname.text = user.login
        Glide.with(this).load(user.avatar_url)
                .transform(GlideCircleTransform(activity))
                .crossFade().into(avatar)
    }

    override fun onError() {
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}