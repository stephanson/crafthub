package com.stephanson.beerin.app.BackgroundNotifier;

/**
 * Created by Stephan on 22.05.2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.stephanson.beerin.app.MainActivity;
import com.stephanson.beerin.app.Model.PushNotification;
import com.stephanson.beerin.app.R;

import java.util.Map;

public class PushService extends FirebaseMessagingService {

    private static int id = 0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        PushNotification push = new PushNotification();
        Map<String,String> data = remoteMessage.getData();
        push.setTitle(data.get("title"));
        push.setText(data.get("body"));
        ShowNotification(push, id++);
    }

    private void ShowNotification(PushNotification push, int notify_id) {

        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), MainActivity.class), 0);

        android.support.v7.app.NotificationCompat.Builder mBuilder = new android.support.v7.app.NotificationCompat.Builder(getApplicationContext());
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(push.getTitle())
                .setContentText(push.getText());

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notify_id, mBuilder.build());
    }
}