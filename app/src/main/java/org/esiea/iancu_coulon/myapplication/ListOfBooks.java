package org.esiea.iancu_coulon.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.esiea.iancu_coulon.myapplication.model.AndroidVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListOfBooks extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {
    private ListView mListView;

    public static final String URL = "https://api.learn2crack.com/android/jsonandroid/";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_VER = "ver";
    private static final String KEY_NAME = "name";
    private static final String KEY_API = "api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_books);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
        Log.d("Log", "JSON download");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.books);
        mBuilder.setContentTitle(getString(R.string.titleNotif));
        mBuilder.setContentText(getString(R.string.textNotif));

        Intent resultIntent = new Intent(this, ListOfBooks.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void onLoaded(List<AndroidVersion> androidVersionList) {
        for (AndroidVersion androidVersion : androidVersionList) {
            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_VER, androidVersion.getVer());
            map.put(KEY_NAME, androidVersion.getName());
            map.put(KEY_API, androidVersion.getApi());

            mAndroidMapList.add(map);
        }
        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_NAME),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {
        ListAdapter adapter = new SimpleAdapter(ListOfBooks.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_VER, KEY_NAME, KEY_API },
                new int[] { R.id.version,R.id.name, R.id.api });

        mListView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.propos:
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setTitle(getString(R.string.titleDialog));
                dlgAlert.setMessage(getString(R.string.textDialog));
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
