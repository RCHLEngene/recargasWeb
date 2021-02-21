package com.example.recargasweb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLitedb extends SQLiteOpenHelper {
    private static final String nom_db="recargasdb.db";
    private static final int version_db=1;
    private static final String createTbl="CREATE TABLE USERS(NOM TEXT NOT NULL,TP_DOC TEXT NOT NULL,NUM_DOC TEXT NOT NULL,MAIL TEXT UNIQUE NOT NULL,PASS TEXT NOT NULL)";
    private static final String createTbl2="CREATE TABLE recarga(PHONE TEXT NOT NULL,VALOR REAL NOT NULL,OPERADOR TEXT NOT NULL)";
    public SQLitedb(@Nullable Context context) {
        super(context, nom_db, null,version_db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTbl);
        db.execSQL(createTbl2);
        db.execSQL("INSERT INTO USERS VALUES ('pedro','Cedula de ciudadania','49789678','pedro@wposs.com','12345678')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(createTbl);
        db.execSQL(createTbl2);

    }

    //metodo que permite registrar a los nuevos usuarios
    public void registrarCuenta(String nom,String tp_doc,String num_doc,String pass){
        SQLiteDatabase db=getWritableDatabase();
        if(db !=null){
            //separar en dos parte la cadena de texto quetrae la variable nom
            String[] nom1=nom.split(" ");
            String user=nom1[0];
            db.execSQL("INSERT INTO USERS VALUES ('"+nom+"','"+tp_doc+"','"+num_doc+"','"+user+"@wposs.com','"+pass+"')");
            db.close();
        }
    }

    public void registrarRecarga(String phone, Float val,String operador){
        SQLiteDatabase db=getWritableDatabase();
        if(db !=null){
            db.execSQL("INSERT INTO RECARGA VALUES('"+phone+"','"+val+"','"+operador+"')");
            db.close();
        }
    }


}
