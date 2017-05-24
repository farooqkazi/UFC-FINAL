package com.example.kazi.testapplication.api;

import com.example.kazi.testapplication.model.Events;
import com.example.kazi.testapplication.model.Fighters;
import com.example.kazi.testapplication.model.News;
import com.example.kazi.testapplication.model.Titles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

import static com.example.kazi.testapplication.model.AppConstants.EVENTS_API;
import static com.example.kazi.testapplication.model.AppConstants.FIGHTER_API;
import static com.example.kazi.testapplication.model.AppConstants.NEWS_API;
import static com.example.kazi.testapplication.model.AppConstants.TITLE_API;

/**
 * Created by Kazi on 5/May/17.
 */


public interface ApiService {

    @GET(TITLE_API)
    Call<List<Titles>> getTitles();

    @GET(EVENTS_API)
    Call<List<Events>> getEvents();

    @GET(NEWS_API)
    Call<List<News>> getNews();

    @GET(FIGHTER_API)
    Call<List<Fighters>> getFighters();

    @GET(EVENTS_API)
    Observable<List<Events>> getEventsObservable();

}
