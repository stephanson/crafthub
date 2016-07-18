package com.stephanson.beerin.app.Presenters;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.messaging.FirebaseMessaging;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Model.PubPushNotificationSubscription;
import com.stephanson.beerin.app.Model.Pubs;

import android.util.Log;

import com.stephanson.beerin.app.Presenters.Interface.IPubsPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Interface.INotificationShower;
import com.stephanson.beerin.app.Views.Interface.IPubsView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Stephan on 05.04.2016.
 */
public class PubsFragmentPresenter implements IPubsPresenter {
    private final IPubsView _view;
    private final IRestClient _restClient;
    private final IPersistance _persistance;
    private Pubs _pubs;
    private final INotificationShower _notificationShower;

    public PubsFragmentPresenter(IPubsView view,
                                 IRestClient restClient,
                                 IPersistance peristace,
                                 INotificationShower shower) {
        _view = view;
        _restClient = restClient;
        _persistance = peristace;
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
        if(_pubs==null){
            _view.setLoadingInProgress(true);
            _restClient.getPubsAsync( this::onPubsReceived, this::onGetPubsError );
        }
        else
            _view.setPubsList(_pubs.getPubs());
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
        _restClient.getPubsAsync( this::onPubsReceived, this::onGetPubsError );
    }

    private void onGetPubsError(Throwable err)
    {
        try {
            Log.d("BEER_ERROR", err.toString());
            _view.setRefreshingInProgress(false);
            _view.setLoadingInProgress(false);
            _notificationShower.showLongSnack(R.string.nonetwork_error);
            _view.setPubsList(_persistance.getPubs());
        }
        catch (Exception ex)
        {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            FirebaseCrash.log(this.getClass().toString()+ ":" + errors.toString());
        }
    }

    private void onPubsReceived(Pubs pubs)
    {
        for (Iterator<Pub> iter = pubs.getPubs().listIterator(); iter.hasNext(); ) {
            Pub p = iter.next();
            if (!p.getIsActive()) {
                iter.remove();
            }
        }

        _pubs = pubs;
        _persistance.persistPubs(_pubs.getPubs());
        _view.setPubsList(pubs.getPubs());
        _view.setRefreshingInProgress(false);
        _view.setLoadingInProgress(false);

    }

    @Override
    public void subscribeToPubPush(Pub pub) {
        PubPushNotificationSubscription sub = _persistance.getSubscriptionForPub(pub);
        if(sub==null)
        {
            FirebaseMessaging.getInstance().subscribeToTopic("pub"+pub.getPubId().toString());
            _persistance.putPushSubscriptionForPub(pub);
            _notificationShower.showShortSnack("%s "+pub.getName(), R.string.subscribe_to_push);
        }

        else
        {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("pub"+pub.getPubId().toString());
            _persistance.removePushsubscription(sub);
            _notificationShower.showShortSnack("%s "+pub.getName(), R.string.unsubscribe_from_push);
        }
    }

    @Override
    public boolean isSubscribeToPubPush(Pub pub) {
        return _persistance.getSubscriptionForPub(pub) != null;
    }
}
