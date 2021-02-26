package com.example.recargasweb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class historial extends AppCompatActivity {
    private RecyclerView rView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        rView=(RecyclerView)findViewById(R.id.rView);

        SQLitedb admin = new SQLitedb(getApplicationContext());
        SQLiteDatabase bd = admin.getWritableDatabase();
        Bundle ble=getIntent().getExtras();
        /*
        //declarar el metodo cursor, metodo que almacena el resultado de la consulta sql
        Cursor c=bd.rawQuery("SELECT PHONE,VALOR,OPERADOR,FECHA FROM recargas AS r JOIN users AS u ON(r.user=u.num_doc) and u.user='"+ble.getString("usuario")+"'",null);

        if(c.moveToFirst()){
            //objeto para pasar las opciones al spinner
            Toast.makeText(historial.this,"Phone: "+String.valueOf(c.getString(0)),Toast.LENGTH_LONG).show();
        }

*/
    }
}