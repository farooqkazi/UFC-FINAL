package com.example.kazi.testapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.kazi.testapplication.api.ApiManager;
import com.example.kazi.testapplication.base.BaseActivity;
import com.example.kazi.testapplication.factory.BinderFactory;
import com.example.kazi.testapplication.factory.UFCBinderFactory;
import com.example.kazi.testapplication.model.AppConstants;
import com.example.kazi.testapplication.presenter.ApiPresenterImpl;
import com.example.kazi.testapplication.ui.UFCTabFragment;

import butterknife.BindView;
import io.realm.Realm;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawerLayout) protected DrawerLayout mDrawerLayout;
    @BindView(R.id.navigationView) protected NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;
    private BinderFactory mBinderFactory;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState, Intent intent) {
        super.onActivityCreated(savedInstanceState,intent);

        setTitle("UFC APP");
        showBurgerMenu();
        //Realm.init(this);

        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                UFCTabFragment tabFragment = UFCTabFragment.newInstance();
                switch (item.getItemId()){
                    case R.id.nav_fighters:
                        tabFragment.setListBinder(mBinderFactory.getListBinder(AppConstants.TAB_INDEX_ONE));
                        replaceFragment(R.id.fragmentContainer, tabFragment, AppConstants.TAB_TAG_ONE);
                        return true;
                    case R.id.nav_news:
                        tabFragment.setListBinder(mBinderFactory.getListBinder(AppConstants.TAB_INDEX_TWO));
                        replaceFragment(R.id.fragmentContainer, tabFragment, AppConstants.TAB_TAG_TWO);
                        return true;
                    case R.id.nav_events:
                        tabFragment.setListBinder(mBinderFactory.getListBinder(AppConstants.TAB_INDEX_THREE));
                        replaceFragment(R.id.fragmentContainer, tabFragment, AppConstants.TAB_TAG_THREE);
                        return true;
                    case R.id.nav_champions:
                        tabFragment.setListBinder(mBinderFactory.getListBinder(AppConstants.TAB_INDEX_FOUR));
                        replaceFragment(R.id.fragmentContainer, tabFragment, AppConstants.TAB_TAG_FOUR);
                        return true;
                }
                return false;
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                supportInvalidateOptionsMenu();
            }
        };


        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        ApiManager apiManager = new ApiManager();
        apiManager.initialize(AppConstants.BASE_URL);
        mBinderFactory = new UFCBinderFactory(new ApiPresenterImpl(apiManager.getApiService()));

        UFCTabFragment tabFragment = UFCTabFragment.newInstance();
        tabFragment.setListBinder(mBinderFactory.getListBinder(AppConstants.TAB_INDEX_ONE));
        replaceFragment(R.id.fragmentContainer, tabFragment, AppConstants.TAB_TAG_ONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
