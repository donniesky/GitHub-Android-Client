package me.donnie.github.ui.profile

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import me.donnie.github.R
import me.donnie.github.ui.profile.overview.OverViewFragment
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
            0 -> fragment = OverViewFragment.newInstance()
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