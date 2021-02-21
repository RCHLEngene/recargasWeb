package com.example.recargasweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class register extends AppCompatActivity {
    private Spinner spDoc;
    private Button btnRegister,btnRegresar;
    private EditText etNom,etDoc,et1Pass,etPassCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNom=(EditText)findViewById(R.id.etNom);
        spDoc=(Spinner) findViewById(R.id.spDoc);
        etDoc=(EditText) findViewById(R.id.etDoc);
        et1Pass=(EditText)findViewById(R.id.et1Pass);
        etPassCon=(EditText)findViewById(R.id.etPassCon);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnRegresar=(Button)findViewById(R.id.btnRegresar);

        String [] tipo_documento={"Ninguno","Cedula de ciudadania","Tarjeta de identidad","Cedula extranjera"};
        //objeto para pasar las opciones al spinner
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tipo_documento);
        spDoc.setAdapter(adapter);

        SQLitedb db=new SQLitedb(getApplicationContext());
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tDoc=spDoc.getSelectedItem().toString();
                if(etNom.getText().toString()=="" || tDoc.equals("Ninguno") || etDoc.getText().toString()=="" || et1Pass.getText().toString()=="" || etPassCon.getText().toString()==""){
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout), "Todos los datos son requeridos", Snackbar.LENGTH_LONG);
                    mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) {
                        mySnackbar.dismiss();
                    } });
                    mySnackbar.show();
                }else{
                    String p1=et1Pass.getText().toString();
                    String p2=etPassCon.getText().toString();
                    if((p1.equals(p2)) && et1Pass.getText().toString().length()>7 || etPassCon.getText().toString().length()>7) {

                        db.registrarCuenta(etNom.getText().toString(), spDoc.getSelectedItem().toString(), etDoc.getText().toString(), et1Pass.getText().toString());
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout), "La cuenta ha sido creada con éxito!", Snackbar.LENGTH_LONG);
                        mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) { mySnackbar.dismiss(); } });
                        mySnackbar.show();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {

                                Intent k= new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(k);
                                finish();
                            }
                        }, 3000);
                    }else{
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout), "Las contraseñas no coinciden o la longitud de la(s) contraseñas es invalido!", Snackbar.LENGTH_LONG);
                        mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) { mySnackbar.dismiss(); } });
                        mySnackbar.show();
                    }
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(l);
                finish();
            }
        });
    }
}