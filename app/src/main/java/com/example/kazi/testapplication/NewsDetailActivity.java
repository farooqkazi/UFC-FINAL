package com.example.kazi.testapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kazi.testapplication.base.BaseActivity;
import com.example.kazi.testapplication.model.AppConstants;
import com.example.kazi.testapplication.ui.UFCTabDetailFragment;


/**
 * Created by Kazi on 5/23/2017.
 */

public class NewsDetailActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState, Intent intent) {
        super.onActivityCreated(savedInstanceState, intent);
        showHome();
        String newsId = intent.getStringExtra(AppConstants.KEY_NEWS_ID);
        replaceFragment(R.id.fragmentContainer, UFCTabDetailFragment.newInstance(newsId), "Details");
        if(isNetworkAvailable()){

        }else{
            Toast.makeText(this, "No Available Network. Please check Internet Connection", Toast.LENGTH_LONG).show();
            return;
        }

    }

        public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
