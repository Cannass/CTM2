package com.example.miche.ctm2;


import android.database.sqlite.SQLiteDatabase;


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

    public static void CloseConnection(SQLiteDatabase Db){
        if(Db != null ){
            Db.close();

        }

    }



}
