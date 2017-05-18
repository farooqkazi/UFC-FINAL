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
import com.example.kazi.testapplication.model.News;
import com.example.kazi.testapplication.model.TabView;
import com.example.kazi.testapplication.presenter.ApiPresenter;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kazi on 5/15/2017.
 */

public class NewsBinder implements ListBinder<NewsBinder.NewsHolder>, Callback<List<News>> {

    public final ApiPresenter mApiPresenter;
    private final List<News> mNewsList = new ArrayList<>();
    private AdapterRefresher mRefresher;
    private TabView mTabView;

    public NewsBinder(ApiPresenter presenter) {
        mApiPresenter = presenter;
    }

    @Override
    public NewsHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ufc_news_layout, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        Context context = holder.itemView.getContext();
        News news = mNewsList.get(position);

        holder.mNewsTitle.setText(news.getTitle());
        String newsAuthor = news.getAuthor();
        holder.mNewsBy.setText(newsAuthor.isEmpty() ? "By Unknown" : newsAuthor);
        holder.mNewsDate.setText(String.format("Last modified: %s", news.getLast_modified()));
        String thumbnail = news.getThumbnail();
        if (thumbnail != null && !thumbnail.isEmpty()) {
            Picasso.with(context).load(thumbnail).into(holder.mNewsImage);
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public void execute(TabView tabView) {
        mTabView = tabView;
        tabView.onShowDialog("Loading News....");
        mApiPresenter.getNews(this);
    }

    @Override
    public void setAdapterRefresher(AdapterRefresher refresher) {
        mRefresher = refresher;
    }

    @Override
    public void onResponse(Call<List<News>> call, Response<List<News>> response) {
        if (response.isSuccessful()) {
            mNewsList.clear();
            mNewsList.addAll(response.body());
            if (mRefresher != null) {
                mRefresher.refresh();
            }

            for (int i = 0; i < mNewsList.size(); i++) {
                News news = mNewsList.get(i);
                Log.d("TAG", news.toString());
            }
            mTabView.onDismissDialog();
            mTabView.onShowToastMessage("Success");
        }
    }

    @Override
    public void onFailure(Call<List<News>> call, Throwable t) {
        mTabView.onDismissDialog();
        mTabView.onShowToastMessage(t.getMessage());
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView mNewsImage;
        private final TextView mNewsTitle;
        private final TextView mNewsBy;
        private final TextView mNewsDate;

        public NewsHolder(View itemView) {
            super(itemView);
            mNewsImage = (ImageView) itemView.findViewById(R.id.imageViewUFC_News);
            mNewsTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            mNewsBy = (TextView) itemView.findViewById(R.id.textViewBy);
            mNewsDate = (TextView) itemView.findViewById(R.id.textViewModified);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTabView.onShowToastMessage(String.format("You clicked on an item at position %d", getAdapterPosition()));
        }
    }
}
