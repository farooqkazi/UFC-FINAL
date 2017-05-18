package com.example.kazi.testapplication.factory;


import com.example.kazi.testapplication.binder.ListBinder;

/**
 * Created by Kazi on 5/12/2017.
 */

public interface BinderFactory {

    ListBinder getListBinder(int tabType);
}
