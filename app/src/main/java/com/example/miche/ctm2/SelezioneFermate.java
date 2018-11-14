package com.example.miche.ctm2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SelezioneFermate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selezione_fermate);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final Spinner spinner = (Spinner)findViewById(R.id.spinner2);
        final Button fermata_selezionata = (Button) findViewById(R.id.fermata_selezionata);
        fermata_selezionata.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Object item = spinner.getSelectedItem();
                SQLiteDatabase mydatabase = openOrCreateDatabase("CTMData",MODE_PRIVATE,null);
                String itemstring = item.toString();
                Cursor c=mydatabase.query("Preferite", new String[]{"Fermata"}, "Fermata"+"=?",
                        new String[]{itemstring}, null, null, null);
                //mydatabase.execSQL("DROP TABLE IF EXISTS Preferite");
                Cursor mCount= mydatabase.rawQuery("select count(*) from Preferite where Fermata='" + itemstring + "'" , null);
                mCount.moveToFirst();
                int count= mCount.getInt(0);
                mCount.close();
                if(count == 0) {
                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");
                    mydatabase.execSQL("INSERT INTO Preferite VALUES('"+itemstring +"','');");
                }


            }
        });
//
//
//        Cursor resultSet = mydatabase.rawQuery("Select Fermata from Fermate",null);
//        while(resultSet.moveToNext()){
//            String username = resultSet.getString(0);
//            resultSet.moveToNext();
//
//            resultSet.moveToFirst();
//        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.fermate,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//            }
//        });
    }
}
