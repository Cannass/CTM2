package com.example.miche.ctm2;


import android.database.sqlite.SQLiteDatabase;

import static android.content.Context.MODE_PRIVATE;

public class Database {
    private String DbName = "CTMData";

    public Database(){
    }

    public static void Check(SQLiteDatabase Db){
        Db.execSQL("CREATE TABLE IF NOT EXISTS TutorialsPoint(Fermata VARCHAR,IdFermata VARCHAR);");
        Db.execSQL("INSERT INTO TutorialsPoint VALUES('admin','admin');");
    }



}
