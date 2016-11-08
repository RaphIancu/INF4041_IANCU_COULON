package org.esiea.iancu_coulon.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListBook extends AppCompatActivity {
    ListView listBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);

        //get listView object from wml
        listBook = (ListView) findViewById(R.id.list);
        //define array values
        String[] values = new String[]{
          "0","1","2","3","4","5","6","7","8","9"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, values);
        // assign adapter to listView
        listBook.setAdapter(adapter);
        //listView item click listener
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //listView clicked item index
                int itemPosition = position;
                //listView clicked item value
                String itemValue = (String) listBook.getItemAtPosition(position);
                //show alert
                Toast.makeText(getApplicationContext(), "Position"+itemPosition+"listitem"+listBook, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}

