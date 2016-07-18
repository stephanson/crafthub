package com.stephanson.beerin.app.Infrastructure.Interface;

import com.stephanson.beerin.app.Model.Broadcasts;
import com.stephanson.beerin.app.Model.Pubs;
import com.stephanson.beerin.app.Model.Stocks;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Stephan on 05.04.2016.
 */
public interface IRestApiDefenition
{
    @GET("pubs")
    Observable<Pubs> getPubs();

    @GET("broadcasts/{place_id}")
    Observable<Broadcasts> getBroadcasts(@Path("place_id") int place_id);

    @GET("stock/{place_id}")
    Observable<Stocks> getStock(@Path("place_id") int place_id);
}
