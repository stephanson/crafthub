package com.stephanson.beerin.app.Infrastructure.Persistance;

import com.stephanson.beerin.app.Model.Broadcast;
import com.stephanson.beerin.app.Model.Pub;
import com.stephanson.beerin.app.Model.PubPushNotificationSubscription;
import com.stephanson.beerin.app.Model.Pubs;
import com.stephanson.beerin.app.Model.Stock;

import java.util.List;

/**
 * Created by Stephan on 20.04.2016.
 */
public interface IPersistance {
    void putPushSubscriptionForPub(Pub pub);
    void removePushsubscription(PubPushNotificationSubscription sub);
    PubPushNotificationSubscription getSubscriptionForPub(Pub pub);

    void persistPubs(List<Pub> pubs);
    List<Pub> getPubs();

    void persistStock(List<Stock> pubs);
    List<Stock> getStock();

    void persistBroadcasts(List<Broadcast> pubs);
    List<Broadcast> getBroadcasts();
}
