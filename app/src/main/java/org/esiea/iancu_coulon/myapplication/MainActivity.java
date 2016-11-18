package org.esiea.iancu_coulon.myapplication;


import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.btBienvenu);
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
                 Intent intent = new Intent(MainActivity.this, ListOfBooks.class );
                 startActivity(intent);
                return true;

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

