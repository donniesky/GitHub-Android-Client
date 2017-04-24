package me.donnie.github.ui.repo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import me.donnie.github.R;
import me.donnie.github.common.base.BaseActivity;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.transform.GlideRoundTransform;
import me.donnie.github.common.utils.Util;
import me.donnie.github.data.entity.Repo;

/**
 * @author donnieSky
 * @created_at 2017/4/12.
 * @description
 */

public class RepoActivity extends BaseActivity implements RepoContract.View {

    private static final String TAG = RepoActivity.class.getSimpleName();

    public static final String PARAM_LOGIN = "param_login";
    public static final String PARAM_REPO_ID = "param_repo_id";
    public static final String PARAM_REPO_URL = "param_repo_url";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.owner)
    ImageView owner;

    @BindView(R.id.repo)
    TextView tv_repo;

    /*@BindView(R.id.repo_desc)
    TextView repo_desc;*/

    @BindView(R.id.time)
    TextView time;

    @BindView(R.id.language)
    TextView language;

    @BindView(R.id.tab)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Inject
    RepoContract.Presenter mPresenter;

    @Inject
    RepoContract.Navigator mNavigator;

    private RepoTabAdapter mAdapter;

    private RepoComponent mRepoComponent;

    private String login,repoId,repoUrl;

    private Repo mRepo;

    public static Intent getCallingIntent(Context context){
        Intent callingIntent = new Intent(context,RepoActivity.class);
        return callingIntent;
    }

    @Override
    protected void setupComponent(AppComponent component) {
        mRepoComponent = DaggerRepoComponent.builder()
                .appComponent(component)
                .repoModule(new RepoModule(this)).build();

        mRepoComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_repo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mPresenter.attachView(this);
        login = getIntent().getStringExtra(PARAM_LOGIN);
        repoId = getIntent().getStringExtra(PARAM_REPO_ID);
        repoUrl = getIntent().getStringExtra(PARAM_REPO_URL);
    }

    private void initTab() {
        mAdapter = new RepoTabAdapter(this, getSupportFragmentManager(), mRepo);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(7);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i<mAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
    }

    @Override
    protected void initData() {
        //mPresenter.getRepo(login, repoId);
        mPresenter.getRepoByUrl(repoUrl);
    }

    @Override
    public void getRepoSuccess(Repo repo) {
        this.mRepo = repo;
        initTab();
        Glide.with(this).load(repo.getOwner().getAvatar_url())
                .transform(new GlideRoundTransform(this))
                .crossFade()
                .placeholder(R.color.gray)
                .into(owner);

        tv_repo.setText(repo.getFullName());

        time.setText(Util.friendly_time(repo.getUpdated_at()));

        language.setText(repo.getLanguage());

        //repo_desc.setText(repo.getDescription());
    }

    public RepoComponent getRepoComponent() {
        return mRepoComponent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_star:

                break;
            case R.id.action_watch:

                break;
            case R.id.action_pin:

                break;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
