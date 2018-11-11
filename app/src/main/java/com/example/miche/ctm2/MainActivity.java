package com.example.miche.ctm2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase mydatabase = openOrCreateDatabase("CTMData",MODE_PRIVATE,null);

        Database.Check(mydatabase);

        final TextView scritta_statica_orari = (TextView) findViewById(R.id.scritta_statica_orari);
        final TextView nome_palina = (TextView) findViewById(R.id.nome_palina);
        final ListView item = (ListView) findViewById(R.id.list_item);
        final Button settings= (Button) findViewById(R.id.settings);
        settings.setVisibility(View.VISIBLE);
        settings.setOnClickListener( new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SelezioneFermate.class);
                MainActivity.this.startActivity(myIntent);

            }
        });

            if (Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            String url = "http://www.ctmcagliari.it/linee_orari.php?linea=01&verso=Di&palina=PA0205";
            Orari or = new Orari(url,10000);
            List<String> Stringhe = or.GetOrariProgrammati();
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, Stringhe);
            nome_palina.setText(or.GetPalinaName());
            item.setAdapter(arrayAdapter);
    }
}
