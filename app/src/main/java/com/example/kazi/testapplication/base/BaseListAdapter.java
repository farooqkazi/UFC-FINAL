package com.example.kazi.testapplication.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.kazi.testapplication.binder.ListBinder;
import com.example.kazi.testapplication.model.AdapterRefresher;


/**
 * Created by Kazi on 5/12/2017.
 */

public class BaseListAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements AdapterRefresher {

    private final ListBinder mListBinder;

    public BaseListAdapter(@NonNull ListBinder listBinder) {
        mListBinder = listBinder;
        mListBinder.setAdapterRefresher(this);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return (VH) mListBinder.onCreateHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mListBinder.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mListBinder.getItemCount();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }
}
