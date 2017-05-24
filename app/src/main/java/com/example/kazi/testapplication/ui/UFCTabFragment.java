package com.example.kazi.testapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kazi.testapplication.R;
import com.example.kazi.testapplication.base.BaseFragment;
import com.example.kazi.testapplication.base.BaseListAdapter;
import com.example.kazi.testapplication.binder.ListBinder;
import com.example.kazi.testapplication.model.TabView;
import com.example.kazi.testapplication.utility.ItemsMarginDecorator;

import butterknife.BindView;

/**
 * Created by Kazi on 5/12/2017.
 */

public class UFCTabFragment extends BaseFragment implements TabView {

    @BindView(R.id.ufcItemsList) protected RecyclerView mItemsList;
    @BindView(R.id.swipeRefreshLayout) protected SwipeRefreshLayout mSwipeToRefresh;

    private ListBinder mListBinder;

    public static UFCTabFragment newInstance() {
        return new UFCTabFragment();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_ufc_tab;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRetainInstance(true); // Screen Orientation
        mSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListBinder.execute(UFCTabFragment.this);
            }
        });
        mItemsList.setHasFixedSize(true);
        mItemsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mItemsList.addItemDecoration(new ItemsMarginDecorator(getActivity().getResources().getDimensionPixelSize(R.dimen.item_margin)));
        mItemsList.setAdapter(new BaseListAdapter(mListBinder));
        mListBinder.execute(this);
    }

    public void setListBinder(ListBinder listBinder) {
        mListBinder = listBinder;
    }

    @Override
    public void onShowToastMessage(String message) {
        mSwipeToRefresh.setRefreshing(false);
        showToast(message);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onDismissDialog() {
        hideDialog();
        mSwipeToRefresh.setRefreshing(false);
    }
}
