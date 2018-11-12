package com.example.miche.ctm2;


import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class Database {
    private String DbName = "CTMData";

    public Database(){
    }

    public static void Check(SQLiteDatabase Db){
        Db.execSQL("CREATE TABLE IF NOT EXISTS Fermate(Fermata VARCHAR,IdFermata VARCHAR);");
        Db.execSQL("INSERT INTO Fermate VALUES('Pascoli (ang. via Petrarca)','0205');");
        Db.execSQL("INSERT INTO Fermate VALUES('Sonnino (ang. via Abba)','0020');");

        Db.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");

    }



}
