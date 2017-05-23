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
import com.example.kazi.testapplication.model.Fighters;
import com.example.kazi.testapplication.model.TabView;
import com.example.kazi.testapplication.presenter.ApiPresenter;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Kazi on 5/14/2017.
 */

public class FighterBinder implements ListBinder<FighterBinder.FighterHolder>, Callback<List<Fighters>> {

    public final ApiPresenter mApiPresenter;
    private final List<Fighters> mFightersList = new ArrayList<>();
    private AdapterRefresher mRefresher;
    private TabView mTabView;

    public FighterBinder(ApiPresenter presenter) {
        mApiPresenter = presenter;
    }

    @Override
    public FighterHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ufc_fighter_layout, parent, false);
        return new FighterHolder(view);
    }

    @Override
    public void onBindViewHolder(FighterHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Fighters fighters = mFightersList.get(position);

        holder.mFighterFullName.setText(String.format(Locale.UK,fighters.getFirst_name() +" "+ fighters.getLast_name()));
        holder.mFighterWeightClass.setText(String.format(fighters.getWeight_class()));
        holder.mFighterFighterInfo.setText(String.format("Rank: "+ fighters.getRank() + ", Nickname: " + fighters.getNickname()));
        Picasso.with(context).load(fighters.getProfile_image()).into(holder.mFighterImage);
    }

    @Override
    public int getItemCount() {
        return mFightersList.size();
    }

    @Override
    public void execute(TabView tabView) {
        mTabView = tabView;
        tabView.onShowDialog("Loading Fighters....");
        mApiPresenter.getFighters(this);
    }

    @Override
    public void setAdapterRefresher(AdapterRefresher refresher) {
        mRefresher = refresher;
    }

    @Override
    public void onResponse(Call<List<Fighters>> call, Response<List<Fighters>> response) {
        if (response.isSuccessful()) {
            mFightersList.clear();
            mFightersList.addAll(response.body());
            if (mRefresher != null) {
                mRefresher.refresh();
            }

            for (int i = 0; i < mFightersList.size(); i++) {
                Fighters fighters = mFightersList.get(i);
                Log.d("TAG", fighters.toString());
            }
            mTabView.onDismissDialog();
            mTabView.onShowToastMessage("Success");
        }
    }


    @Override
    public void onFailure(Call<List<Fighters>> call, Throwable t) {
        mTabView.onDismissDialog();
        mTabView.onShowToastMessage(t.getMessage());
    }

    public class FighterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mFighterImage;
        private TextView mFighterFullName, mFighterWeightClass, mFighterFighterInfo;

        public FighterHolder(View itemView) {
            super(itemView);
            mFighterImage = (ImageView) itemView.findViewById(R.id.imageViewUFC_Fighter);
            mFighterFullName = (TextView) itemView.findViewById(R.id.textViewFullName);
            mFighterWeightClass = (TextView) itemView.findViewById(R.id.textViewWeightClass);
            mFighterFighterInfo = (TextView) itemView.findViewById(R.id.textViewFighterInfo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTabView.onShowToastMessage(String.format("You clicked on an item at position %d", getAdapterPosition()));
        }
    }
}
