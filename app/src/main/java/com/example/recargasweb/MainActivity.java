package com.example.recargasweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //declarar las variables a utilizar
    private Button btnLogin;
    private EditText etPass,etUser;
    private TextView etNewAccount;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciar los elementos del main con las variables declaradas antes
        etPass=(EditText) findViewById(R.id.etPass);
        etUser=(EditText)findViewById(R.id.etUser);

        btnLogin=(Button)findViewById(R.id.btnLogin);
        etNewAccount=(TextView)findViewById(R.id.etNewAcount);


        //incovar a la funcion OnClick del boton login

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLitedb admin = new SQLitedb(getApplicationContext());
                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();
                SQLiteDatabase bd = admin.getWritableDatabase();

                //declarar el metodo cursor, metodo que almacena el resultado de la consulta sql
                Cursor c=bd.rawQuery("SELECT nom FROM users WHERE mail='"+user+"' and pass='"+pass+"'",null);

                if(c.moveToFirst()){
                    String nom=c.getString(0);
                    //invocar a una nueva ventana llamada recarga
                    Intent l= new Intent(getApplicationContext(),recarga.class);
                    l.putExtra("user",String.valueOf(nom));//pasar a la nueva venta la variable nombre,la cual almacena elnombre del usuario
                    startActivity(l);//llamar a la nueva ventana
                    finish();
                }else{
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout2), "No exite algún usuario con estas credenciales!", Snackbar.LENGTH_LONG);
                    mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) {
                        mySnackbar.dismiss();
                    } });
                    mySnackbar.show();
                }
                //bd.close();
            }

        });

        //incovar a la funcion OnClick del boton NewAccount
        etNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //invocar a la nueva ventana
            Intent i=new Intent(getApplicationContext(),register.class);
            //inicar la nueva ventana
            startActivity(i);
           // finish();
            }
        });
    }
}