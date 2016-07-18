package com.stephanson.beerin.app.Infrastructure.Persistance;

import android.content.Context;
import android.os.AsyncTask;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.stephanson.beerin.app.Model.Broadcast;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Model.PubPicture;
import com.stephanson.beerin.app.Model.PubPushNotificationSubscription;
import com.stephanson.beerin.app.Model.Pubs;
import com.stephanson.beerin.app.Model.Stock;

import java.util.List;

/**
 * Created by Stephan on 20.04.2016.
 */
public class SQLPersistance implements IPersistance {

    private static SQLPersistance _instance;

    public static SQLPersistance getInstance(Context context)
    {
        if(_instance==null)
            _instance = new SQLPersistance();
        return _instance;
    }

    @Override
    public void putPushSubscriptionForPub(Pub pub) {
        PubPushNotificationSubscription sub = new PubPushNotificationSubscription(pub);
        sub.save();
    }

    @Override
    public void removePushsubscription(PubPushNotificationSubscription sub) {
        sub.delete();
    }


    @Override
    public PubPushNotificationSubscription getSubscriptionForPub(Pub pub) {
        PubPushNotificationSubscription sub = new Select().from(PubPushNotificationSubscription.class)
                .where("pub_id="+String.valueOf(pub.getPubId())).executeSingle();
        return sub;
    }

    @Override
    public void persistPubs(List<Pub> pubs) {
        PersistPubsTask task = new PersistPubsTask();
        task.execute(pubs);
    }

    @Override
    public List<Pub> getPubs() {
        List<Pub> pubs = new Select().from(Pub.class).execute();

        for (Pub pub : pubs)
        {
            List<PubPicture> test = new Select()
                    .from(PubPicture.class).as("pic")
                    .join(Pub.class).as("pub")
                    .on("pic.pub = pub.id")
                    .where("pub.pub_id = "+pub.getPubId() )
                    .execute();

            pub.setPictures(test);

        }

        return pubs;
    }

    @Override
    public void persistStock(List<Stock> stock) {
        PersistStockTask task = new PersistStockTask();
        task.execute(stock);
    }

    @Override
    public List<Stock> getStock() {
        List<Stock> ret = new Select().from(Stock.class).execute();
        return ret;
    }

    @Override
    public void persistBroadcasts(List<Broadcast> broadcasts) {
        PersistBroadcastsTask task = new PersistBroadcastsTask();
        task.execute(broadcasts);
    }

    @Override
    public List<Broadcast> getBroadcasts() {
        List<Broadcast> ret = new Select().from(Broadcast.class).execute();
        return ret;
    }


    class PersistBroadcastsTask extends AsyncTask<List<Broadcast>, Void, Void>
    {
        @Override
        protected Void doInBackground(List<Broadcast>... params) {
            List<Broadcast> broadcasts = params[0];
            new Delete().from(Broadcast.class).execute();
            if(broadcasts != null)
            {
                for(Broadcast brod : broadcasts)
                {
                    brod.save();
                }
            }
            return null;
        }
    }

    class PersistStockTask extends AsyncTask<List<Stock>, Void, Void>
    {
        @Override
        protected Void doInBackground(List<Stock>... params) {
            List<Stock> stocks = params[0];
            new Delete().from(Stock.class).execute();
            if(stocks != null)
            {
                for(Stock stock : stocks)
                {
                    stock.save();
                }
            }
            return null;
        }
    }

    class PersistPubsTask extends AsyncTask<List<Pub>, Void, Void> {
        @Override
        protected Void doInBackground(List<Pub>... params) {
            List<Pub> pubs = params[0];

            new Delete().from(Pub.class).execute();
            if(pubs != null)
            {
                for (Pub pub : pubs)
                {
                    pub.save();
                    for(PubPicture pic : pub.getPictures())
                    {
                        pic.pub = pub;
                        pic.save();
                    }
                }
            }
            return null;
        }
    }
}
