package net.shojigate.toshokan.notifypigeon;

import android.content.Context;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;


/**
 * Created by toshokan on 3/27/17.
 */

public class PigeonListener extends NotificationListenerService{
    // Uses NotificationListenerService to use accessibility functionality to read notifications

    Context context;

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }
    @Override

    public void onNotificationPosted(StatusBarNotification sbNotification) {
        // This will run when a new notification is registered

        //String pack = sbNotification.getPackageName();

        // Store the information we will want later
        Bundle extras = sbNotification.getNotification().extras;
        // Initialize empty strings in case there is an error getting the real title and text
        String title = "";
        String text = "";
        try {
            // Some notifications (HTC audio notification, for example) do not set either title or text properly
            title = extras.getString("android.title");
            text = extras.getCharSequence("android.text").toString();
        }catch(NullPointerException e){
            // Ignore notifications that do not have title or text set
            return;
        }
        // Store the id in case it is used in the future FIXME
        int id = sbNotification.getId();


        // Put the information into the correct order
        String[] message = new String[4];
        // This method is run on new notifications, so set the command to "s", for "show"
        message[0] = "s";
        message[1] = title;
        message[2] = text;
        message[3] = "" + id;
        // Run PigeonFlight AsyncTask in another thread
        new PigeonFlight().execute(message);
    }

    @Override

    public void onNotificationRemoved(StatusBarNotification sbNotification) {
        // This will run when a notification is removed

        //String pack = sbNotification.getPackageName();

        // Store the information we will want later
        Bundle extras = sbNotification.getNotification().extras;
        // Initialize empty strings in case there is an error getting the real title and text
        String title = "";
        String text = "";
        try {
            // Some notifications (HTC audio notification, for example) do not set either title or text properly
            title = extras.getString("android.title");
            text = extras.getCharSequence("android.text").toString();
        }catch(NullPointerException e){
            // Ignore notifications that do not have title or text set
            return;
        }
        // Store the id in case it is used in the future FIXME
        int id = sbNotification.getId();


        // Put the information into the correct order
        String[] message = new String[4];
        // This method is run on notification removal, so set the command to "u", for "unshow"
        message[0] = "s";
        message[1] = title;
        message[2] = text;
        message[3] = "" + id;
        // Run PigeonFlight AsyncTask in another thread
        new PigeonFlight().execute(message);
    }
}

