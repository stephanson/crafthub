package com.stephanson.beerin.app.Infrastructure.Interface;

import com.stephanson.beerin.app.Model.Broadcasts;
import com.stephanson.beerin.app.Model.Pubs;
import com.stephanson.beerin.app.Model.Stocks;

import rx.functions.Action1;

/**
 * Created by Stephan on 05.04.2016.
 */
public interface IRestClient {
    void getPubsAsync(Action1<Pubs> onResult, Action1<Throwable> err);
    void getBroadcastsAsync(int placeId, Action1<Broadcasts> onResult, Action1<Throwable> err);
    void getStockAsync(int placeId, Action1<Stocks> onResult, Action1<Throwable> err);
}
