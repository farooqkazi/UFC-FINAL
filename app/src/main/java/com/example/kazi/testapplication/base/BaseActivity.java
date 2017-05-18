package com.example.kazi.testapplication.base;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import android.support.v4.app.Fragment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.widget.Toast;


import com.example.kazi.testapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Kazi on 5/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) protected Toolbar mToolbar;

    private Unbinder bind;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        bind = ButterKnife.bind(this);
        if (mToolbar != null) {
          setSupportActionBar(mToolbar);
         }

        onActivityCreated(savedInstanceState, getIntent());
    }

    protected abstract int getContentView();

    @CallSuper
    protected void onActivityCreated(Bundle savedInstanceState, Intent intent) {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (bind != null) {
            bind.unbind();
            bind = null;
        }
        super.onDestroy();
    }


    protected void showDialog(String message) {
        createDialog();
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    private void createDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showBurgerMenu() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void replaceFragment(int containerId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(containerId, fragment, tag)
                .commit();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
