package org.esiea.iancu_coulon.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonBienvenuRecycler = (Button)findViewById(R.id.btBienvenuRecycler);
        buttonBienvenuRecycler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfAndroidRecycler.class );
                startActivity(intent);
            }
        });

        final Button buttonBienvenu = (Button)findViewById(R.id.btBienvenu);
        buttonBienvenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfAndroid.class );
                startActivity(intent);
            }
        });
        final Button buttonJoin = (Button)findViewById(R.id.btJoin);
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"iancu@et.esiea.fr"});
                email.putExtra(Intent.EXTRA_CC, new String[]{"coulon@et.esiea.fr"});
                email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
                email.putExtra(Intent.EXTRA_TEXT, getString(R.string.message));
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, getString(R.string.emailClient)));
            }
        });
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

