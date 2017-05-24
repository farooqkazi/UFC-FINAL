package com.example.kazi.testapplication.binder;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kazi.testapplication.R;
import com.example.kazi.testapplication.model.AdapterRefresher;
import com.example.kazi.testapplication.model.Titles;
import com.example.kazi.testapplication.model.TabView;
import com.example.kazi.testapplication.presenter.ApiPresenter;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Kazi on 5/11/2017.
 */

public class ChampionsBinder implements ListBinder<ChampionsBinder.ChampionsHolder>, Callback<List<Titles>> {

    public final ApiPresenter mApiPresenter;
    private final List<Titles> mTitlesList = new ArrayList<>();
    private AdapterRefresher mRefresher;
    private TabView mTabView;

    public ChampionsBinder(ApiPresenter presenter) {
        mApiPresenter = presenter;
    }

    @Override
    public ChampionsHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ufc_champion_layout, parent, false);
        return new ChampionsBinder.ChampionsHolder(view);
    }

    @Override
    public void onBindViewHolder(ChampionsHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Titles titles = mTitlesList.get(position);

        holder.mChampionFullName.setText(String.format(titles.getFirstName() +" "+ titles.getLastName()));
        holder.mChampionWeightClass.setText(String.format(titles.getWeightClass()));
        holder.mChampionInfo.setText(String.format("Wins: "+ titles.getWins() + ", Losses: " + titles.getLosses() + ", Draws : " + titles.getDraws()));
        holder.mChampionStatus.setText(String.format("Status: " + titles.getFighterStatus()));
        Picasso.with(context).load(titles.getBeltThumbnail()).into(holder.mChampionImage);

    }

    @Override
    public int getItemCount() {
        return mTitlesList.size();
    }

    @Override
    public void execute(TabView tabView) {
        mTabView = tabView;
        tabView.onShowDialog("Loading Champions....");
        mApiPresenter.getTitles(this);
    }

    @Override
    public void setAdapterRefresher(AdapterRefresher refresher) {
        mRefresher = refresher;
    }

    @Override
    public void onResponse(Call<List<Titles>> call, Response<List<Titles>> response) {

        if (response.isSuccessful()) {
            mTitlesList.clear();
            mTitlesList.addAll(response.body());
            if (mRefresher != null) {
                mRefresher.refresh();
            }

            for (int i = 0; i < mTitlesList.size(); i++) {
                Titles titles = mTitlesList.get(i);
                Log.d("TAG", mTitlesList.get(i).toString());
            }
            mTabView.onDismissDialog();
            mTabView.onShowToastMessage("Success");
        }

    }

    @Override
    public void onFailure(Call<List<Titles>> call, Throwable t) {
        mTabView.onDismissDialog();
        mTabView.onShowToastMessage(t.getMessage());

    }


    public class ChampionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mChampionImage;
        private TextView mChampionFullName, mChampionWeightClass, mChampionInfo, mChampionStatus;

        public ChampionsHolder(View itemView) {
            super(itemView);
            mChampionImage = (ImageView) itemView.findViewById(R.id.imageViewUFC_Fighter);

            mChampionFullName = (TextView) itemView.findViewById(R.id.textViewFullName);
            mChampionWeightClass = (TextView) itemView.findViewById(R.id.textViewWeightClass);
            mChampionInfo = (TextView) itemView.findViewById(R.id.textViewFighterInfo);
            mChampionStatus = (TextView) itemView.findViewById(R.id.textViewStatus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTabView.onShowToastMessage(String.format("You clicked on an item at position %d", getAdapterPosition()));
        }
    }


}
