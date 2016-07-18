package com.stephanson.beerin.app.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Presenters.Interface.ISpecificPubMaterialViewPagerPresenter;
import com.stephanson.beerin.app.Presenters.SpecificPubPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Adapters.SpecificPubMaterialViewPagerAdapter;

/**
 * Created by Electric Brains on 18.04.2016.
 */

public class SpecificPubMaterialViewPagerFragment extends MaterialViewPagerBase {

    private ISpecificPubMaterialViewPagerPresenter _presenter;
    private Pub _pub;
    private SpecificPubMaterialViewPagerAdapter _pageAdapter;

    public static SpecificPubMaterialViewPagerFragment newInstance(Pub pub) {
        SpecificPubMaterialViewPagerFragment f = new SpecificPubMaterialViewPagerFragment();
        f.setRetainInstance(true);
        f._pub = pub;
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new SpecificPubPresenter(this);
        _pageAdapter = new SpecificPubMaterialViewPagerAdapter( getChildFragmentManager(),_pub);
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
        _presenter.onPause();
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
        menu.findItem(R.id.home).setVisible(true);

    }

    @Override
    public void setHeaderContent() {
        _materialViewPager.getViewPager().setAdapter(_pageAdapter);
        _materialViewPager.setMaterialViewPagerListener(SpecificPubMaterialViewPagerAdapter::getHeaderDesign);
        _materialViewPager.getViewPager().setOffscreenPageLimit(_materialViewPager.getViewPager().getAdapter().getCount());
        _materialViewPager.getPagerTitleStrip().setViewPager(_materialViewPager.getViewPager());
        _logoImage.setVisibility(View.GONE);
        _logoText.setVisibility(View.VISIBLE);
        _logoText.setText(_pub.getName());


    }

}