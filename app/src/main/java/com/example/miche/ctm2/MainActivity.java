package com.example.miche.ctm2;

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

import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView scritta_statica_orari = (TextView) findViewById(R.id.scritta_statica_orari);
        final TextView nome_palina = (TextView) findViewById(R.id.nome_palina);
        final ListView item = (ListView) findViewById(R.id.list_item);

        try {
            // get URL content



            if (Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Document doc = Jsoup.connect("http://www.ctmcagliari.it/linee_orari.php?linea=01&verso=Di&palina=PA0205").get();
//            Elements newsHeadlines = doc.select("#mp-itn b a");
//            String a="http://www.ctmcagliari.it/linee_orari.php?linea=01&verso=Di&palina=PA0205";
//            Connection conn = new  Connection();

            //Element masthead = doc.select("div[id*=tempo_reale").first();
            //StringBuilder builder= conn.doInBackground(a);
            try {
                Element mast = doc.getElementById("tempo_reale");
                Elements tbody = mast.getElementsByTag("tbody");
                Elements ora1 = mast.getElementsByTag("tr");
                List<String> Stringhe = new ArrayList<String>();
                for(int i =0;i<=ora1.size()-1;i++){
                    if(i != 0){
                        Element orarioprogrammato = ora1.get(i);
                        Element oraprogrammata = orarioprogrammato.getElementsByClass("ore").first();
                        Elements minutiprogrammati = orarioprogrammato.getElementsByIndexEquals(1);
                        String minuti[] = minutiprogrammati.get(0).childNodes().get(0).toString().split(" ");
                        String val = "";
                        for(int j=0; j<=minuti.length -1; j++){
                            if(val == ""){
                                val = oraprogrammata.childNodes().get(0).toString() + ":" + minuti[j];
                                Stringhe.add(val);
                            }
                            else{
                                val =oraprogrammata.childNodes().get(0).toString() + ":" + minuti[j];
                                Stringhe.add(val);
                            }
                        }
                    }
                }

                nome_palina.setText(doc.getElementById("nome_palina").childNode(0).toString());
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_list_item_1, Stringhe);
                item.setAdapter(arrayAdapter);

            } catch (Exception e) {
                Element orari = doc.getElementsByClass("tabella_orari testo").first();
                Elements ora = orari.getElementsByTag("tr");
                Element firstorario = ora.get(1);
                String val = "";
                String minuti[] =firstorario.childNode(3).childNode(0).toString().split(" ");
                for(int i = 0; i<=minuti.length -1;i++){
                    if(val == ""){
                        val = firstorario.getElementsByClass("ore").first().text() + ":" + minuti[i];
                    }
                    else{
                        val = val + "\n" +firstorario.getElementsByClass("ore").first().text() + ":" + minuti[i];
                    }
                }
//                text2.setText( val);

            }



//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//            Notification n  = new Notification.Builder(this)
//                    .setContentTitle("New mail from " + "test@gmail.com")
//                    .setContentText(ore.text())
//                    .setSmallIcon(R.drawable.ic_launcher_background)
//                    .setAutoCancel(true).build();
//
//            NotificationManager notificationManager =
//                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.notify(0, n);


//            synchronized(mBuilder){
//                mBuilder.notify();
//            }




            String prova = "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
