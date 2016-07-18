package com.stephanson.beerin.app.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Stephan on 20.04.2016.
 */
@Table(name = "PushSubscriptions")
public class PubPushNotificationSubscription extends Model
{
    @Column(name = "pub_id",  unique = true, onUniqueConflict = Column.ConflictAction.IGNORE)
    public Integer _pubId;

    @Column(name = "last_shown")
    public long _lastShowedPushDate;


    public PubPushNotificationSubscription() {
        super();
    }

    public PubPushNotificationSubscription(Pub pub) {
        super();
        _pubId = pub.getPubId();
        _lastShowedPushDate = 0;
    }

    public PubPushNotificationSubscription(Pub pub, long lastShown) {
        super();
        _pubId = pub.getPubId();
        _lastShowedPushDate = lastShown;
    }
}
