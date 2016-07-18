package com.stephanson.beerin.app.Presenters;

import com.google.firebase.crash.FirebaseCrash;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.Model.Broadcasts;
import com.stephanson.beerin.app.Presenters.Interface.IBroadcastsPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.BroadcastFragment;
import com.stephanson.beerin.app.Views.Interface.IBroadcastsView;
import com.stephanson.beerin.app.Views.Interface.INotificationShower;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Stephan on 17.04.2016.
 */
public class BroadcastsPresenter implements IBroadcastsPresenter {

    private final IPersistance _persistance;
    private final IBroadcastsView _view;
    private final IRestClient _restClient;
    private final INotificationShower _notificationShower;
    private Broadcasts _bcList;
    private int _placeId;

    public BroadcastsPresenter(int placeId,
                               IBroadcastsView view,
                               IRestClient restClient,
                               IPersistance persistance,
                               INotificationShower shower) {
        _view = view;
        _restClient = restClient;
        _placeId = placeId;
        _persistance = persistance;
        _notificationShower = shower;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {
        if(_bcList==null){
            _view.setLoadingInProgress(true);
            _restClient.getBroadcastsAsync( _placeId, this::onBcReceived, this::onGetBckError );
        }
        else
            _view.setBroadcastsList(_bcList.getBroadcasts());
    }

    private void onGetBckError(Throwable err) {
        //todo чтото сделать по ошибке
        try
        {
            Log.d("BEER_ERROR", err.toString());
            _view.setRefreshingInProgress(false);
            _view.setLoadingInProgress(false);
            _notificationShower.showLongSnack(R.string.nonetwork_error);
            _view.setBroadcastsList(_persistance.getBroadcasts());
        }
        catch (Exception ex)
        {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            FirebaseCrash.log(this.getClass().toString()+ ":" + errors.toString());
        }

    }

    private void onBcReceived(Broadcasts broadcasts) {
        _bcList = broadcasts;
        _view.setBroadcastsList(broadcasts.getBroadcasts());
        _view.setRefreshingInProgress(false);
        _view.setLoadingInProgress(false);
        _persistance.persistBroadcasts(_bcList.getBroadcasts());
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
        _view.setRefreshingInProgress(true);
        _restClient.getBroadcastsAsync( _placeId, this::onBcReceived, this::onGetBckError );
    }

}
