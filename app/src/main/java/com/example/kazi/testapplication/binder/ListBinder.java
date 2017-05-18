package com.example.kazi.testapplication.binder;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.kazi.testapplication.model.AdapterRefresher;
import com.example.kazi.testapplication.model.TabView;

/**
 * Created by Kazi on 5/12/2017.
 */

public interface ListBinder<VH extends RecyclerView.ViewHolder> {

    VH onCreateHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(VH holder, int position);

    int getItemCount();

    void execute(TabView tabView);

    void setAdapterRefresher(AdapterRefresher refresher);
}
