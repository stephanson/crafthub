package com.stephanson.beerin.app.Infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestApiDefenition;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Model.Broadcasts;
import com.stephanson.beerin.app.Model.Pubs;
import com.stephanson.beerin.app.Model.Stocks;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Stephan on 05.04.2016.
 */
public class RestClient implements IRestClient {

    private static final String BASE_URL = "https://inbeer-frozenapp.rhcloud.com/";
    private IRestApiDefenition _service;

    public RestClient() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();

        _service = retrofit.create(IRestApiDefenition.class);

    }

    @Override
    public void getPubsAsync(Action1<Pubs> onResult, Action1<Throwable> onError)
    {
        Observable<Pubs> pubs = _service.getPubs();
        pubs.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ret -> onResult.call(ret)
                        , err -> onError.call(err)
        );
    }

    @Override
    public void getBroadcastsAsync(int placeId, Action1<Broadcasts> onResult, Action1<Throwable> onError) {
        Observable<Broadcasts> broadcasts = _service.getBroadcasts(placeId);

        broadcasts.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ret -> onResult.call(ret)
                        , err -> onError.call(err)
                );
    }

    @Override
    public void getStockAsync(int placeId, Action1<Stocks> onResult, Action1<Throwable> onError) {
        Observable<Stocks> broadcasts = _service.getStock(placeId);

        broadcasts.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .subscribe(ret -> onResult.call(ret)
                        , err -> onError.call(err)
                );
    }

}
