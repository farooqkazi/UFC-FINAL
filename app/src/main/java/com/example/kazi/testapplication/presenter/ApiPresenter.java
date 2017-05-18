package com.example.kazi.testapplication.presenter;



import retrofit2.Callback;
import rx.Observer;

/**
 * Created by Kazi on 5/13/2017.
 */

public interface ApiPresenter {

    void getTitles(Callback callback);

    void getEvents(Callback callback);

    void getEvents(Observer observer);

    void getNews(Callback callback);

    void getFighters(Callback callback);

    //void getFighters(Observer observer);
}
