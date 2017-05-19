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
import com.example.kazi.testapplication.model.Events;
import com.example.kazi.testapplication.model.TabView;
import com.example.kazi.testapplication.presenter.ApiPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;


/**
 * Created by Kazi on 5/11/2017.
 */

public class EventsBinder implements ListBinder<EventsBinder.EventsHolder>, Callback<List<Events>> {


    public final ApiPresenter mApiPresenter;
    private final List<Events> mEventsList = new ArrayList<>();
    private AdapterRefresher mRefresher;
    private TabView mTabView;

    public EventsBinder(ApiPresenter presenter) {
        mApiPresenter = presenter;
    }

    @Override
    public EventsHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ufc_event_layout, parent, false);
        return new EventsHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Events events = mEventsList.get(position);

        holder.mEventName.setText(String.format(events.getBaseTitle()));
        holder.mEventLocation.setText(String.format(events.getLocation()));
        holder.mEventArena.setText(String.format(events.getLocation()));
        Picasso.with(context).load(events.getFeatureImage()).into(holder.mEventImage);
        holder.mEventDate.setText(String.format(events.getEventDate()));

    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    @Override
    public void setAdapterRefresher(AdapterRefresher refresher) {
        mRefresher = refresher;
    }

    @Override
    public void execute(TabView tabView) {
        mTabView = tabView;
        tabView.onShowDialog("Loading Events....");
        mApiPresenter.getEvents(new Observer<List<Events>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Events> events) {
                handleEventResponse(events);
            }
        });
        //mApiPresenter.getEvents(this);
    }

    @Override
    public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
        if (response.isSuccessful()) {
            handleEventResponse(response.body());
        }

    }

    private void handleEventResponse(List<Events> mEventsList) {
        mEventsList.clear();
        mEventsList.addAll(mEventsList);
        if (mRefresher != null) {
            mRefresher.refresh();
        }

        for (int i = 0; i < mEventsList.size(); i++) {
            Events events = mEventsList.get(i);
            Log.d("TAG", events.toString());
        }
        mTabView.onDismissDialog();
        mTabView.onShowToastMessage("Success");
    }



    @Override
    public void onFailure(Call<List<Events>> call, Throwable t) {

        mTabView.onDismissDialog();
        mTabView.onShowToastMessage(t.getMessage());

    }

    public class EventsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mEventImage;
        private TextView mEventName, mEventLocation, mEventArena,mEventDate;

        public EventsHolder(View itemView) {
            super(itemView);

            mEventImage = (ImageView) itemView.findViewById(R.id.imageViewEvent);
            mEventDate = (TextView) itemView.findViewById(R.id.textViewInfo);
            mEventName = (TextView) itemView.findViewById(R.id.textViewEventName);
            mEventArena = (TextView) itemView.findViewById(R.id.textViewArena);
            mEventLocation = (TextView) itemView.findViewById(R.id.textViewLocation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mTabView.onShowToastMessage(String.format("You clicked on an item at position %d", getAdapterPosition()));
        }
    }

}
