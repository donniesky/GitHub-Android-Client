package me.donnie.github.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import me.donnie.github.R
import me.donnie.github.common.base.BaseActivity
import me.donnie.github.common.injection.component.AppComponent
import me.donnie.github.common.transform.GlideCircleTransform
import me.donnie.github.data.entity.User
import javax.inject.Inject

/**
 * @author donnieSky
 * @created_at 2017/4/26.
 * @description 个人详情页
 * @version
 */
class ProfileActivity : BaseActivity(), ProfileContract.View {

    @Inject
    lateinit var mNavigator: ProfileContract.Navigator

    @Inject
    lateinit var mPresenter: ProfileContract.Presenter

    var mProfileComponent: ProfileComponent? = null

    companion object {
        @JvmStatic fun getCallingIntent(context: Context): Intent {
            val callingIntent = Intent(context, ProfileActivity::class.java)
            return callingIntent
        }
    }

    override fun setupComponent(component: AppComponent?) {
        mProfileComponent = DaggerProfileComponent.builder()
                .appComponent(component)
                .profileModule(ProfileModule(this))
                .build()
        mProfileComponent?.inject(this)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_profile
    }

    override fun initView(savedInstanceState: Bundle?) {
        mPresenter.attachView(this)
        setSupportActionBar(toolbar)
        supportActionBar.let {
            it?.setDisplayHomeAsUpEnabled(true)
            it?.setDisplayShowTitleEnabled(false)
        }
        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
        viewpager.adapter = ProfileTabAdapter(this, supportFragmentManager)
        tab.setupWithViewPager(viewpager)
    }

    override fun initData() {
        mPresenter.loadUser()
    }

    override fun loadUserSuccess(user: User) {
        if (user != null) {
            toolbar.title = user.login
            Glide.with(this).load(user.avatar_url)
                    .transform(GlideCircleTransform(this))
                    .crossFade().into(avatar)
            login.text = user.login
            name.text = user.name
            bio.text = user.bio
            create_time.text = user.created_at.toString()
            location.text = user.location
            blog.text = user.blog
            repos.text = user.public_repos.toString()
            following.text = user.following.toString()
            follower.text = user.followers.toString()
        }
    }

    override fun onError() {

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }
}