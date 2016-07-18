package com.stephanson.beerin.app.Presenters.Interface;

/**
 * Created by Stephan on 14.04.2016.
 */
public interface IBaseFragmentPresenter {
    void onStart();
    void onStop();
    void onResume();
    void onPause();
    void onDestroy();

    void onAttach();
    void onDetach();

    void refreshRequested();
}
