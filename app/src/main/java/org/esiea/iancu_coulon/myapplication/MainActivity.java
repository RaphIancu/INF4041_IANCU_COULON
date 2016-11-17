package org.esiea.iancu_coulon.myapplication;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    public static final String BIERS_UPDATE="com.octip.cours.inf4042_11.BIERS_UPDATE";

    public class BierUpdate extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent){
            Log.d("Log", getIntent().getAction());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.btBienvenu);
        // Show a toast and open an new activity when we click the button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.toastReady), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ListBook.class);
                startActivity(intent);
            }
        });
        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentFilter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()) {
             case R.id.book:
                 Intent intent = new Intent(MainActivity.this, ListBook.class);
                 startActivity(intent);
                return true;

             case R.id.propos:
                 /*NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                 mBuilder.setSmallIcon(R.drawable.books);
                 mBuilder.setContentTitle(getString(R.string.titleNotif));
                 mBuilder.setContentText(getString(R.string.textNotif));

                 Intent resultIntent = new Intent(this, MainActivity.class);
                 TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                 stackBuilder.addParentStack(MainActivity.class);

                 stackBuilder.addNextIntent(resultIntent);
                 PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                 mBuilder.setContentIntent(resultPendingIntent);
                 NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                 mNotificationManager.notify(1, mBuilder.build());
                 */
                 AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                 dlgAlert.setTitle(getString(R.string.titleDialog));
                 dlgAlert.setMessage(getString(R.string.textDialog));
                 dlgAlert.setCancelable(true);
                 dlgAlert.create().show();

             default:
                 return super.onOptionsItemSelected(item);
         }
    }
}

