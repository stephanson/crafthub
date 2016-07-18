package com.stephanson.beerin.app.Presenters;

import com.google.firebase.crash.FirebaseCrash;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.Infrastructure.Persistance.SQLPersistance;
import com.stephanson.beerin.app.Model.Stock;
import com.stephanson.beerin.app.Model.Stocks;
import com.stephanson.beerin.app.Presenters.Interface.IStockPresenter;
import com.stephanson.beerin.app.R;
import com.stephanson.beerin.app.Views.Interface.INotificationShower;
import com.stephanson.beerin.app.Views.Interface.IStockView;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Stephan on 14.04.2016.
 */
public class StockPresenter implements IStockPresenter {

    private final IPersistance _persistance;
    private final IStockView _view;
    private final IRestClient _restClient;
    private final INotificationShower _notificationShower;
    private Stocks _stockList;

    private int _placeId;

    public StockPresenter(int placeId,
                          IStockView view,
                          IRestClient restClient,
                          IPersistance peristance,
                          INotificationShower shower) {
        _view = view;
        _restClient = restClient;
        _placeId = placeId;
        _persistance = peristance;
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
        if(_stockList==null)
        {
            _restClient.getStockAsync( _placeId, this::onStockReceived, this::onGetStockError );
            _view.setLoadingInProgress(true);
        }
        else
            _view.setStockList(_stockList.getStocks());
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
        _restClient.getStockAsync( _placeId, this::onStockReceived, this::onGetStockError );
    }

    public void onGetStockError(Throwable err) {
        //todo чтото сделать по ошибке
        try
        {
            Log.d("BEER_ERROR", err.toString());
            _view.setRefreshingInProgress(false);
            _view.setLoadingInProgress(false);
            _notificationShower.showLongSnack(R.string.nonetwork_error);
            _view.setStockList(_persistance.getStock());
        }
        catch (Exception ex)
        {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            FirebaseCrash.log(this.getClass().toString()+ ":" + errors.toString());
        }

    }

    public void onStockReceived(Stocks stocks) {

        _stockList = stocks;
        _view.setStockList(_stockList.getStocks());
        _view.setRefreshingInProgress(false);
        _view.setLoadingInProgress(false);
        _persistance.persistStock(_stockList.getStocks());
    }
}
