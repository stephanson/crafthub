package com.stephanson.beerin.app;

import android.content.ContextWrapper;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.pixplicity.easyprefs.library.Prefs;
import com.stephanson.beerin.app.Infrastructure.ImageLoaderController;
import com.stephanson.beerin.app.Infrastructure.Interface.IRestClient;
import com.stephanson.beerin.app.Infrastructure.Persistance.IPersistance;
import com.stephanson.beerin.app.Infrastructure.Persistance.SQLPersistance;
import com.stephanson.beerin.app.Infrastructure.RestClient;
import com.stephanson.beerin.app.Model.Broadcast;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Model.PubPicture;
import com.stephanson.beerin.app.Model.PubPushNotificationSubscription;
import com.stephanson.beerin.app.Model.Stock;


/**
 * Created by Stephan on 05.04.2016.
 */
public class BeerApp extends com.activeandroid.app.Application {

    IRestClient _restClient;
    IPersistance _persistance;

    @Override
    public void onCreate()
    {
        initSingletons();
        super.onCreate();
    }

    private void initPersistance() {
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClass(PubPushNotificationSubscription.class);
        configurationBuilder.addModelClass(Pub.class);
        configurationBuilder.addModelClass(PubPicture.class);
        configurationBuilder.addModelClass(Stock.class);
        configurationBuilder.addModelClass(Broadcast.class);
        configurationBuilder.setDatabaseVersion(1);
        configurationBuilder.setDatabaseName("persistance.db");
        ActiveAndroid.initialize(configurationBuilder.create());
        _persistance =  SQLPersistance.getInstance(getApplicationContext());
    }

    protected void initSingletons()
    {
        initPersistance();
        ImageLoaderController.InitializeImageLoader(getApplicationContext());
        _restClient = new RestClient();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    public IRestClient get_restClient() {
        return _restClient;
    }

    public IPersistance get_persistance()
    {
        return _persistance;
    }

}
