package com.example.kazi.testapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Kazi on 5/12/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mBinder;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        mBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        mBinder.unbind();
        super.onDestroyView();
    }

    protected void showToast(String message) {
        getBaseActivity().showToast(message);
    }

    protected void showDialog(String message) {
        (getBaseActivity()).showDialog(message);

    }

    protected void hideDialog() {
        (getBaseActivity()).hideDialog();
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity)getActivity();
    }

    protected abstract int getContentView();
}
