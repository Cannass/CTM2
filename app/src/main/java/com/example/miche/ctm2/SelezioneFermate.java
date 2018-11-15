package com.example.miche.ctm2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SelezioneFermate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selezione_fermate);

        final Spinner spinner = (Spinner)findViewById(R.id.spinner2);
        final Button fermata_selezionata = (Button) findViewById(R.id.fermata_selezionata);
        final Button home_button = (Button) findViewById(R.id.home_button);
        home_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase mydatabase = openOrCreateDatabase("CTMData",MODE_PRIVATE,null);


                Cursor mCount= mydatabase.rawQuery("select count(*) from Preferite" , null);
                mCount.moveToFirst();
                int count= mCount.getInt(0);
                mCount.close();
                mydatabase.close();
                if(count == 0) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Seleziona almeno una fermata!", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    Intent myIntent = new Intent(SelezioneFermate.this, MainActivity.class);
                    SelezioneFermate.this.startActivity(myIntent);
                    SelezioneFermate.this.finish();
                }


            }
        });

        fermata_selezionata.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object item = spinner.getSelectedItem();
                SQLiteDatabase mydatabase = openOrCreateDatabase("CTMData",MODE_PRIVATE,null);
                String itemstring = item.toString();
                mydatabase.execSQL("DROP TABLE IF EXISTS Preferite");
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");
                Cursor mCount= mydatabase.rawQuery("select count(*) from Preferite where Fermata='" + itemstring + "'" , null);
                mCount.moveToFirst();
                int count= mCount.getInt(0);
                mCount.close();
                if(count == 0) {
                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");
                    mydatabase.execSQL("INSERT INTO Preferite VALUES('"+itemstring +"','');");
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Fermata:" + itemstring+ "salvata!!!", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });

       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.fermate,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
