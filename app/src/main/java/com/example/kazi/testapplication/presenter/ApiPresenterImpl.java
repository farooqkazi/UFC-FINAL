package com.example.kazi.testapplication.presenter;

import com.example.kazi.testapplication.api.ApiService;
import com.example.kazi.testapplication.model.Events;
import com.example.kazi.testapplication.model.Fighters;
import com.example.kazi.testapplication.model.News;
import com.example.kazi.testapplication.model.Titles;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kazi on 5/13/2017.
 */

public class ApiPresenterImpl implements ApiPresenter {

    private ApiService mApiService;

    public ApiPresenterImpl(ApiService service) {
        mApiService = service;
    }

    @Override
    public void getTitles(Callback callback) {
        Call<List<Titles> >titlesCall = mApiService.getTitles();
        titlesCall.enqueue(callback);
    }

    public void getEvents(Callback callback) {
        Call<List<Events>> eventsCall = mApiService.getEvents();
        eventsCall.enqueue(callback);
    }

    @Override
    public void getNews(Callback callback) {
        Call<List<News>> newsCall = mApiService.getNews();
        newsCall.enqueue(callback);
    }

    @Override
    public void getFighters(Callback callback) {
        Call<List<Fighters>> fightersCall = mApiService.getFighters();
        fightersCall.enqueue(callback);
    }

    @Override
    public void getEvents(Observer observer) {
        Observable<List<Events>> observable = mApiService.getEventsObservable();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}