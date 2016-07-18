package com.stephanson.beerin.app.Presenters;

import com.stephanson.beerin.app.Presenters.Interface.IMainWindowMaterialViewPagerPresenter;
import com.stephanson.beerin.app.Views.Interface.IMaterialView;

/**
 * Created by Electric Brains on 17.04.2016.
 */
public class MainWindowPresenter implements IMainWindowMaterialViewPagerPresenter {
    private IMaterialView _view;

    public MainWindowPresenter(IMaterialView view) {
        _view = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onAttach() {
    }

    @Override
    public void onDetach() {
    }

    @Override
    public void refreshRequested() {

    }
}
