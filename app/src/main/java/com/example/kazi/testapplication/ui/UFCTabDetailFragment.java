package com.example.kazi.testapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.kazi.testapplication.R;
import com.example.kazi.testapplication.base.BaseFragment;
import com.example.kazi.testapplication.model.AppConstants;

import butterknife.BindView;

/**
 * Created by Kazi on 5/23/2017.
 */

public class UFCTabDetailFragment extends BaseFragment {

    @BindView(R.id.webView) protected WebView mWebView;

    private String mNewsId;

    public static UFCTabDetailFragment newInstance(String newsId) {

        Bundle args = new Bundle();
        args.putString(AppConstants.KEY_NEWS_ID, newsId);
        UFCTabDetailFragment fragment = new UFCTabDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_ufc_news_details;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsId = getArguments().getString(AppConstants.KEY_NEWS_ID, "-1");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://ufc-data-api.ufc.com/api/v3/iphone/news/" + mNewsId);
    }
}
