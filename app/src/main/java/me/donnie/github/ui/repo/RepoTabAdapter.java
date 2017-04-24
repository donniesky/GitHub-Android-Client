package me.donnie.github.ui.repo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.donnie.github.R;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.ui.repo.commit.CommitFragment;
import me.donnie.github.ui.repo.contributor.ContributorFragment;
import me.donnie.github.ui.repo.issue.IssueFragment;
import me.donnie.github.ui.repo.pullrequest.PullRequestFragment;
import me.donnie.github.ui.repo.readme.ReadmeFragment;
import me.donnie.github.ui.repo.test.TestFragment;

/**
 * @author donnieSky
 * @created_at 2017/4/13.
 * @description
 */

public class RepoTabAdapter extends FragmentPagerAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private String[] titles;

    private TypedArray icons;

    private Repo mRepo;

    public RepoTabAdapter(Context context, FragmentManager fm, Repo repo) {
        super(fm);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mRepo = repo;
        titles = mContext.getResources().getStringArray(R.array.repo);
        icons = mContext.getResources().obtainTypedArray(R.array.icons);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ReadmeFragment.newInstance(mRepo);
                break;
            case 2:
                fragment = IssueFragment.newInstance(mRepo);
                break;
            case 3:
                fragment = PullRequestFragment.newInstance(mRepo);
                break;
            case 4:
                fragment = CommitFragment.newInstance(mRepo);
                break;
            case 6:
                fragment = ContributorFragment.newInstance(mRepo);
                break;
            default:
                fragment = TestFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public View getTabView(int position) {
        View view = mInflater.inflate(R.layout.view_tab_icon, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
        img.setImageDrawable(icons.getDrawable(position));
        textView.setText(titles[position]);
        return view;
    }
}
