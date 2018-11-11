package com.example.miche.ctm2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Orari {

    public  static  GetOrariProgrammati(String url,int timeout){
        try {
            Document doc = Jsoup.connect(url).timeout(timeout).get();





        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
