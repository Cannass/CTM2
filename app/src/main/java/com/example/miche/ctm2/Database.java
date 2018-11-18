package com.example.miche.ctm2;


import android.database.sqlite.SQLiteDatabase;


public class Database {
    private String DbName = "CTMData";

    public Database(){
    }

    public static void Check(SQLiteDatabase Db){
        Db.execSQL("CREATE TABLE IF NOT EXISTS Fermate(Fermata VARCHAR not null,IdFermata VARCHAR not null primary key);");
        Db.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");
        Db.execSQL("CREATE TABLE IF NOT EXISTS Linee(Nome VARCHAR not null primary key);");

    }



    public static void CloseConnection(SQLiteDatabase Db){
        if(Db != null ){
            Db.close();
        }
    }



}
