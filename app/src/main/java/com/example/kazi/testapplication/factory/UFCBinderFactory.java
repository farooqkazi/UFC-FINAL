package com.example.kazi.testapplication.factory;



import com.example.kazi.testapplication.binder.ChampionsBinder;
import com.example.kazi.testapplication.binder.EventsBinder;
import com.example.kazi.testapplication.binder.FighterBinder;
import com.example.kazi.testapplication.binder.ListBinder;
import com.example.kazi.testapplication.binder.NewsBinder;
import com.example.kazi.testapplication.factory.BinderFactory;
import com.example.kazi.testapplication.model.AppConstants;
import com.example.kazi.testapplication.presenter.ApiPresenter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Kazi on 5/12/2017.
 */

public class UFCBinderFactory implements BinderFactory {

    private final Map<Integer, ListBinder> mBinderCache = new HashMap<>();
    private final ApiPresenter mPresenter;

    public UFCBinderFactory(ApiPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public ListBinder getListBinder(int tabType) {
        ListBinder listBinder = mBinderCache.get(tabType);
        if (listBinder == null) {
            switch (tabType) {
                case AppConstants.TAB_INDEX_ONE:
                    listBinder = new FighterBinder(mPresenter);
                    mBinderCache.put(tabType, listBinder);
                    break;
                case AppConstants.TAB_INDEX_TWO:
                    listBinder = new NewsBinder(mPresenter);
                    mBinderCache.put(tabType, listBinder);
                    break;
                 case AppConstants.TAB_INDEX_THREE:
                    listBinder = new EventsBinder(mPresenter);
                    mBinderCache.put(tabType, listBinder);
                    break;
                 case AppConstants.TAB_INDEX_FOUR:
                    listBinder = new ChampionsBinder(mPresenter);
                    mBinderCache.put(tabType, listBinder);
                    break;
            }
        }
        return listBinder;
    }
}
