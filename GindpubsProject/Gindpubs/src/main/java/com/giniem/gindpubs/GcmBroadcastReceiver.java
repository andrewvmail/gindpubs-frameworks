package com.giniem.gindpubs;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONObject;

/**
 * Created by Holland on 10-24-13.
 */
public class GcmBroadcastReceiver extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /**
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             **/
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification(context, "Error notification", "Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification(context, "Deleted messages", "Deleted messages on server: " + extras.toString());
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                try {
                    JSONObject json = new JSONObject(extras.getString("notificationData"));

                    // Values can be either "standard-notification" or "background-download". This value is required.
                    String type = json.getString("type");

                    if ("standard-notification".equals(type)) {

                        // A title to show at the notification bar in android. This value is optional.
                        String title = json.has("title") ? json.getString("title") : "";

                        // The message description for the notification to show at the notifications bar in android. This value is optional.
                        String message = json.has("message") ? json.getString("message") : "";

                        this.sendNotification(context, title, message);
                    } else if ("background-download".equals(type)) {

                        if (json.has("issueName")) {

                            // Values can be "latest" or the name of the issue, for example "magazine-12". This value is required.
                            String issueName = json.getString("issueName");
                            Intent gindIntent = new Intent(context, GindActivity.class);
                            gindIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            gindIntent.putExtra("START_DOWNLOAD", issueName);
                            context.startActivity(gindIntent);
                        }
                    }


                } catch (Exception ex) {
                    // Do nothing, if it fails we simply do not process the notification.
                    Log.e(this.getClass().toString(), ex.getMessage());
                }

                // Post notification of received message.
                //sendNotification(context, extras.getString("collapse_key"), extras.getString("message"));
                Log.i(this.getClass().toString(), "Received: " + extras.toString());
            }
        }
    }

    private void sendNotification(Context context, String title, String message) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, GindActivity.class);
        intent.putExtra("START_DOWNLOAD", message);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(title)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .setContentText(message);

        mBuilder.setContentIntent(contentIntent);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
