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
import me.donnie.github.ui.test.TestFragment;

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

    public RepoTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        titles = mContext.getResources().getStringArray(R.array.repo);
        icons = mContext.getResources().obtainTypedArray(R.array.icons);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance();
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
