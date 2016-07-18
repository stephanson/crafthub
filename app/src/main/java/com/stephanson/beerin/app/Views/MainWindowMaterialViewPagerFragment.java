package com.stephanson.beerin.app.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import android.support.v7.widget.Toolbar;
import android.view.*;
import com.stephanson.beerin.app.MainActivity;
import com.stephanson.beerin.app.Presenters.Interface.IMainWindowMaterialViewPagerPresenter;
import com.stephanson.beerin.app.Presenters.MainWindowPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Adapters.MainWindowMaterialViewPagerAdapter;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

/**
 * Created by Electric Brains on 18.04.2016.
 */


public class MainWindowMaterialViewPagerFragment extends MaterialViewPagerBase {

    private IMainWindowMaterialViewPagerPresenter _presenter;
    private MainWindowMaterialViewPagerAdapter _pageAdapter;

    public static MainWindowMaterialViewPagerFragment newInstance() {
        MainWindowMaterialViewPagerFragment f = new MainWindowMaterialViewPagerFragment();
        f.setRetainInstance(true);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new MainWindowPresenter(this);
        _pageAdapter = new MainWindowMaterialViewPagerAdapter(getChildFragmentManager());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        _presenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        _presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        _presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _presenter.onDestroy();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void setHeaderContent() {
        _materialViewPager.getViewPager().setAdapter(_pageAdapter);
        _materialViewPager.setMaterialViewPagerListener(MainWindowMaterialViewPagerAdapter::getHeaderDesign);
        _materialViewPager.getViewPager().setOffscreenPageLimit(_materialViewPager.getViewPager().getAdapter().getCount());
        _materialViewPager.getPagerTitleStrip().setViewPager(_materialViewPager.getViewPager());
        _logoText.setVisibility(View.GONE);
        _logoImage.setVisibility(View.VISIBLE);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}