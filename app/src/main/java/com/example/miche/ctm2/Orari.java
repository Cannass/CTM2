package com.example.miche.ctm2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Orari {

    private Document doc = null;
    //Costruttore
    public Orari(String url,int timeout){
        this.doc = GetDocFromUrl(url,timeout);
    }


    public List<String> GetOrariProgrammati(){
        List<String> Stringhe = new ArrayList<String>();
        if(this.doc== null){return Stringhe;}
        try{
            Element tempo_reale = this.doc.getElementById("tempo_reale");
            Elements tbody = tempo_reale.getElementsByTag("tbody");
            Elements orari = tempo_reale.getElementsByTag("tr");
            for(int i =0;i<=orari.size()-1;i++){
                if(i != 0){
                    Element orarioprogrammato = orari.get(i);
                    Element oraprogrammata = orarioprogrammato.getElementsByClass("ore").first();
                    Elements minutiprogrammati = orarioprogrammato.getElementsByIndexEquals(1);
                    String minuti[] = minutiprogrammati.get(0).childNodes().get(0).toString().split(" ");
                    String val = "";
                    for(int j=0; j<=minuti.length -1; j++){
                        val = oraprogrammata.childNodes().get(0).toString() + ":" + minuti[j];
                        Stringhe.add(val);
                    }
                }
            }


        }catch (Exception e){

        }
        return Stringhe;
    }

    public  static  Document GetDocFromUrl(String url,int timeout){
        try {
            Document doc = Jsoup.connect(url).timeout(timeout).get();
            return doc;
        }catch (Exception e){
            return null;
        }
    }
    public  String GetPalinaName(){
        if(this.doc != null ){
            return this.doc.getElementById("nome_palina").childNode(0).toString();
        }
        return "";
    }
}
