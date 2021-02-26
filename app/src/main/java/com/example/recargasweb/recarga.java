package com.example.recargasweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class recarga extends AppCompatActivity {
    private Button btnRecarga,btnSalir;
    private TextView tvUser;
    private Spinner spOperador;
    private EditText etPhone, etPhoneCon, etVal, etValCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);

        btnRecarga = (Button) findViewById(R.id.btnRecargar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPhoneCon = (EditText) findViewById(R.id.etPhoneCon);
        etVal = (EditText) findViewById(R.id.etVal);
        etValCon = (EditText) findViewById(R.id.etValCon);
        spOperador = (Spinner) findViewById(R.id.spOperador);
        tvUser = (TextView) findViewById(R.id.tvUser);

        String[] operador = {"Ninguno", "Claro", "Tigo", "Movistar", "ETB"};
        //objeto para pasar las opciones al spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operador);
        spOperador.setAdapter(adapter);

        //En esta clase definimos una variable de tipo Bundle y la inicializamos llamando al método getExtras()
        // de la clase Intent (esto lo hacemos para recuperar el o los parámetros que envió la otra actividad (Activity)):
        Bundle bundle = getIntent().getExtras();
        tvUser.setText("Señor(a) " + bundle.getString("user"));
        SQLitedb admin = new SQLitedb(getApplicationContext());
        SQLiteDatabase db = admin.getWritableDatabase();
        //SQLitedb db = new SQLitedb(getApplicationContext());
        btnRecarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String op = spOperador.getSelectedItem().toString();
                if (etPhone.getText().toString().equals("")  || op.equals("Ninguno") || etPhoneCon.getText().toString().equals("") || etVal.getText().toString().equals("") || etValCon.getText().toString().equals("")) {
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout1), "Todos los datos son requeridos", Snackbar.LENGTH_LONG);
                    mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) {
                        mySnackbar.dismiss();
                    } });
                    mySnackbar.show();
                }else{
                    String ph1=etPhone.getText().toString();
                    String ph2=etPhoneCon.getText().toString();
                    if(ph1.equals(ph2) && (ph1.length()==10 && ph2.length()==10)){
                        float val1=Float.parseFloat(etVal.getText().toString());
                        float val2=Float.parseFloat(etValCon.getText().toString());
                        if(val1==val2 && (val1>0 && val2>0) ){
                                admin.registrarRecarga(etPhone.getText().toString(),val1,op,bundle.getString("usuario"));
                            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout1), "La recarga fue realizada con exito...", Snackbar.LENGTH_LONG);
                            mySnackbar.show();
                            etValCon.setText("");
                            etVal.setText("");
                            etPhoneCon.setText("");
                            etPhone.setText("");
                            spOperador.setSelection(0);
                            mySnackbar = Snackbar.make(findViewById(R.id.lnLayout1), "Ver historial de recargas", Snackbar.LENGTH_LONG);
                            mySnackbar.setAction("Ver", new View.OnClickListener() { @Override public void onClick(View v) {
                                Intent i=new Intent(getApplicationContext(),historial.class);
                                //inicar la nueva ventana
                                i.putExtra("usuario",bundle.getString("usuario"));
                                startActivity(i);
                            } });
                            mySnackbar.show();

                        }else{
                            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout1), "Los valor a recargar no coinciden o son iguales a cero(0)", Snackbar.LENGTH_LONG);
                            mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) {
                                mySnackbar.dismiss();
                            } });
                            mySnackbar.show();
                        }
                    }else{
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.lnLayout1), "Los números de télefono no coinciden o no tiene la longitud valida", Snackbar.LENGTH_LONG);
                        mySnackbar.setAction("Ocultar", new View.OnClickListener() { @Override public void onClick(View v) {
                            mySnackbar.dismiss();
                        } });
                        mySnackbar.show();
                    }
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(l);
                finish();
            }
        });
    }
}