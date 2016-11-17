package org.esiea.iancu_coulon.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static org.esiea.iancu_coulon.myapplication.MainActivity.BIERS_UPDATE;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GetBiersServices extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_BIERS = "org.esiea.iancu_coulon.myapplication.action.BIERS";

    public GetBiersServices() {
        super("GetBiersServices");
    }

    /**
     * Starts this service to perform action Biers with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBiers(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        intent.setAction(ACTION_BIERS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        handleActionBiers();
    }

    /**
     * Handle action Biers in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBiers() {
        Log.i("Log", "Thread service name:"+Thread.currentThread().getName());
        URL url = null;
        try{
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "bieres.json"));
                Log.d("Log", "Bieres json download");
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setSmallIcon(R.drawable.books);
                mBuilder.setContentTitle(getString(R.string.titleDialog));
                mBuilder.setContentText(getString(R.string.textDialog));

                Intent resultIntent = new Intent(this, MainActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(MainActivity.class);

                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, mBuilder.build());
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(BIERS_UPDATE));
            }
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyInputStreamToFile(InputStream in, File file){
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
