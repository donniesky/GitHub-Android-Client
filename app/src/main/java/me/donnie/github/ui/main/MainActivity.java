package me.donnie.github.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import butterknife.BindView;
import me.donnie.github.R;
import me.donnie.github.common.base.BaseActivity;
import me.donnie.github.common.injection.component.AppComponent;
import me.donnie.github.common.transform.GlideCircleTransform;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
MainContract.View {

    @Inject
    MainContract.Navigator mNavigator;

    @Inject
    MainContract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ImageView avarar;

    TextView username;

    private MainComponent mMainComponent;

    public MainComponent getMainComponent() {
        return mMainComponent;
    }

    public static Intent getCallingIntent(Context context){
        Intent callingIntent = new Intent(context,MainActivity.class);
        return callingIntent;
    }

    @Override
    protected void setupComponent(AppComponent component) {
        mMainComponent = DaggerMainComponent.builder()
                .appComponent(component)
                .mainModule(new MainModule(this))
                .build();
        mMainComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private boolean isLogIn() {
        return !TextUtils.isEmpty(prefser.get("token", String.class, ""));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!isLogIn()) {
            mNavigator.navigateToLogin();
        }
        mPresenter.attachView(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        avarar = (ImageView) headerView.findViewById(R.id.imageView);
        username = (TextView) headerView.findViewById(R.id.username);

        if (savedInstanceState == null) {
            mNavigator.navigateToEvent();
        }
    }

    @Override
    protected void initData() {
        String user_avatar = prefser.get("user_avatar", String.class, "");
        String uname = prefser.get("username", String.class, "");
        username.setText(uname);
        Glide.with(this).load(user_avatar)
                .transform(new GlideCircleTransform(this))
                .crossFade()
                .placeholder(R.color.gray)
                .into(avarar);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logEvent(String itemName, String contentType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            logEvent("nav_home", "navigation_item");
            mNavigator.navigateToEvent();
        } else if (id == R.id.nav_gallery) {
            logEvent("nav_trending", "navigation_item");
            mNavigator.navigateToTrending();
        } else if (id == R.id.nav_logout) {
            logEvent("nav_logout", "navigation_item");
            mNavigator.logout();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
