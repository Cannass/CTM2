package com.example.miche.ctm2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView scritta_statica_orari = (TextView) findViewById(R.id.scritta_statica_orari);
        final TextView nome_palina = (TextView) findViewById(R.id.nome_palina);
        final ListView item = (ListView) findViewById(R.id.list_item);
        final Button settings= (Button) findViewById(R.id.settings);


        TxtReader quoteBank = new TxtReader(getApplicationContext());
        List<String> lines = quoteBank.readLine("Fermate.txt");
        settings.setVisibility(View.VISIBLE);
        settings.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SelezioneFermate.class);
                MainActivity.this.startActivity(myIntent);
                MainActivity.this.finish();
            }
        });
        SQLiteDatabase mydatabase = openOrCreateDatabase("CTMData",MODE_PRIVATE,null);
        Database.Check(mydatabase);
        //int count =Database.CheckPreferite(mydatabase);
//        if(count == 0){
//            Database.CloseConnection(mydatabase);
//            Intent myIntent = new Intent(MainActivity.this, SelezioneFermate.class);
//            MainActivity.this.finish();
//            MainActivity.this.startActivity(myIntent);
//
//        }else{
//            if (Build.VERSION.SDK_INT > 9)
//            {
//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//            }
//            //String fermata = Database.GetPreferita(mydatabase);
//            Database.CloseConnection(mydatabase);
//            String url = "";
//            if(fermata.equals("Pascoli (ang. via Petrarca)")){
//                url = "http://www.ctmcagliari.it/linee_orari.php?linea=01&verso=Di&palina=PA0205";
//            }else if (fermata.equals("Sonnino (ang. via Abba)")){
//                url = "http://www.ctmcagliari.it/linee_orari.php?linea=10&verso=Di&palina=SO0020";
//            }else{
//                url = "http://www.ctmcagliari.it/linee_orari.php?linea=29&verso=As&palina=DA0206";
//            }
//            url = "http://m.ctmcagliari.it/fermate.php?s=0020";
//            Orari or = new Orari(url,10000);
//            List<String> st= or.GetTestOrari();
//            List<String> Stringhe = or.GetOrariProgrammati();
//            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                    (this, android.R.layout.simple_list_item_1, Stringhe);
//            nome_palina.setText(or.GetPalinaName());
//            item.setAdapter(arrayAdapter);
//        }
    }
}
