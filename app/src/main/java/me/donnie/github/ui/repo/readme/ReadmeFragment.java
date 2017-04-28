package me.donnie.github.ui.repo.readme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kennyc.view.MultiStateView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import me.donnie.github.R;
import me.donnie.github.common.base.LazyFragment;
import me.donnie.github.data.entity.Repo;
import me.donnie.github.ui.repo.RepoActivity;
import me.donnie.github.ui.repo.RepoComponent;

/**
 * @author donnieSky
 * @created_at 2017/4/17.
 * @description README详情页
 */

public class ReadmeFragment extends LazyFragment
implements ReadmeContract.View {

    private static final String PARAM_REPO_ARGS = "repo_args";

    @BindView(R.id.state)
    MultiStateView mStateView;

    @BindView(R.id.webview)
    WebView mWebView;

    @Inject
    ReadmeContract.Navigator mNavigator;

    @Inject
    ReadmeContract.Presenter mPresenter;

    private ReadmeComponent mReadmeComponent;

    private Repo mRepo;

    public static ReadmeFragment newInstance(Repo repo) {

        Bundle args = new Bundle();
        args.putParcelable(PARAM_REPO_ARGS, repo);
        ReadmeFragment fragment = new ReadmeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setupComponent() {
        mReadmeComponent = getRepoComponent().plus(new ReadmeModule());
        mReadmeComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_readme;
    }

    @Override
    protected void initView(View view) {
        mPresenter.attachView(this);
        mRepo = (Repo)getArguments().getParcelable(PARAM_REPO_ARGS);
    }

    @Override
    protected void initData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        if (mRepo != null) {
            mPresenter.loadReadme(mRepo.getOwner().getLogin(), mRepo.getName());
        }
    }

    @Override
    public void loadReadmeSuccess(final String content) {
        if (!TextUtils.isEmpty(content)) {

            mWebView.getSettings().setAppCachePath(getContext().getCacheDir().getPath());
            mWebView.getSettings().setAppCacheEnabled(true);
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
            mWebView.getSettings().setLoadsImagesAutomatically(true);
            mWebView.getSettings().setBlockNetworkImage(false);

            mWebView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(request.getUrl());
                        startActivity(intent);
                    }
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    if (mStateView != null) {
                        mStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
                    }
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (mStateView != null) {
                        mStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                    }
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    if (mStateView != null) {
                        mStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                    }
                }
            });

            String template = "<html>\n" + "\n" + "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\"/>" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + "file:///android_asset/md/github.css" + "\">\n" +
                    "</head>\n" + "\n" + "<body>\n" + "%s" +
                    "\n<script src=\"file:///android_asset/md/intercept-touch.js\"></script>\n" +
                    "<script>\n" +
                    "function scrollTo(hash) {\n" +
                    "var element = document.getElementById(\"user-content-\" + hash);\n" +
                    "element.scrollIntoView();\n" +
                    "}" +
                    "</script>" + "\n" + "</body>\n" + "\n" + "</html>\n";
            String readme = String.format(Locale.CHINA, template, content);
            mWebView.loadDataWithBaseURL("https://api.github.com/repos/", readme, "text/html", "UTF-8", null);
        }
    }

    @Override
    public void onNetWorkError() {
        if (mStateView != null) {
            mStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }

    private RepoComponent getRepoComponent() {
        return ((RepoActivity)getActivity()).getRepoComponent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
