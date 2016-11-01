package com.herprogramacion.proyectofinal.Views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.proyectofinal.BD.Conexion;
import com.herprogramacion.proyectofinal.Data.Config;
import com.herprogramacion.proyectofinal.R;

import java.util.HashMap;

/**
 * Created by lds on 31/10/2016.
 */
public class NuevoLugar extends Fragment {


    TextView nombre;
    TextView tipo;
    TextView direccion;
    TextView latitud;
    TextView longitud;


    Button aceptar;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contenido_datos,container,false);

        nombre = (TextView) view.findViewById(R.id.campo_nombre);
        tipo = (TextView) view.findViewById(R.id.campo_tipo);
        direccion = (TextView) view.findViewById(R.id.campo_direc);
        latitud = (TextView) view.findViewById(R.id.campo_latitud);
        longitud = (TextView) view.findViewById(R.id.campo_long);

        aceptar = (Button) view.findViewById(R.id.boton_aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addEmployee();
            }
        });

        return view;
    }

    private void addEmployee(){

        final String name = nombre.getText().toString().trim();
        final String tip = tipo.getText().toString().trim();
        final String direc = direccion.getText().toString().trim();
        final String lat = latitud.getText().toString().trim();
        final String lon = longitud.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(),s , Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_NAM,name);
                params.put(Config.KEY_TIP,tip);
                params.put(Config.KEY_DIR,direc);
                params.put(Config.KEY_LAT,lat);
                params.put(Config.KEY_LON,lon);

                Conexion rh = new Conexion();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }




}

