package me.donnie.github.ui.profile

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.donnie.github.R
import me.donnie.github.ui.profile.event.UserEventFragment
import me.donnie.github.ui.profile.followers.FollowerFragment
import me.donnie.github.ui.profile.following.FollowingFragment
import me.donnie.github.ui.profile.repos.ReposFragment
import me.donnie.github.ui.profile.stars.StarsFragment
import me.donnie.github.ui.repo.test.TestFragment

/**
 * @author donnieSky
 * @created_at 2017/4/28.
 * @description
 * @version
 */
class ProfileTabAdapter(context: Context,
                        fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val titles: Array<String> = context.resources.getStringArray(R.array.profile)

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> fragment = UserEventFragment.newInstance()
            1 -> fragment = ReposFragment.newInstance()
            2 -> fragment = StarsFragment.newInstance()
            3 -> fragment = FollowerFragment.newInstance()
            4 -> fragment = FollowingFragment.newInstance()
            else -> fragment = TestFragment.newInstance()
        }
        return fragment
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}