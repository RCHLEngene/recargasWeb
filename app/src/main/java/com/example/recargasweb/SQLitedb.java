package com.example.recargasweb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLitedb extends SQLiteOpenHelper {
    private static final String nom_db="recargasdb.db";
    private static final int version_db=1;
    private static final String createTbl="CREATE TABLE USERS(NOM TEXT NOT NULL,TP_DOC TEXT NOT NULL,NUM_DOC TEXT NOT NULL UNIQUE,MAIL TEXT  NOT NULL,PASS TEXT NOT NULL)";
    private static final String createTbl2="CREATE TABLE recarga(PHONE TEXT NOT NULL,VALOR REAL NOT NULL,OPERADOR TEXT NOT NULL,USER TEXT NOT NULL,FECHA TEXT NOT NULL)";
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
            String user=remplazartildes(String.valueOf(nom1[0]));//nom1[0].toLowerCase();
            db.execSQL("INSERT INTO USERS VALUES ('"+nom+"','"+tp_doc+"','"+num_doc+"','"+user+"@wposs.com','"+pass+"')");
            db.close();
        }
    }

    public void registrarRecarga(String phone, Float val,String operador,String doc){
        SQLiteDatabase db=getWritableDatabase();
        if(db !=null){
            //obtenerhora y fecha y salida por pantalla con formato:
            Date date=new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            String fecha=String.valueOf(hourdateFormat.format(date));
            db.execSQL("INSERT INTO RECARGA VALUES('"+phone+"','"+val+"','"+operador+"','"+doc+"','"+fecha+"')");
            db.close();
        }
    }

    public String remplazartildes(String cadena){
        cadena=cadena.toLowerCase();
                         cadena=cadena.replace('á','a');
                         cadena=cadena.replace('é','e');
                         cadena=cadena.replace('í','i');
                         cadena=cadena.replace('ó','o');
                         cadena=cadena.replace('ú','u');
        return cadena;
    }


}
