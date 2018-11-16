package com.example.miche.ctm2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public static void CloseConnection(SQLiteDatabase Db){
        if(Db != null ){
            Db.close();
        }
    }

    public static int CheckPreferite(SQLiteDatabase Db){
        Db.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");
        Cursor mCount= Db.rawQuery("select count(*) from Preferite " , null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return  count;
    }

    public static String GetPreferita(SQLiteDatabase Db){
        Db.execSQL("CREATE TABLE IF NOT EXISTS Preferite(Fermata VARCHAR,IdFermata VARCHAR);");
        Cursor cursor = Db.rawQuery("SELECT Fermata FROM Preferite",null);
        cursor.moveToNext();
        String fermata = cursor.getString(cursor.getColumnIndex("Fermata"));
        return fermata;
    }

}
