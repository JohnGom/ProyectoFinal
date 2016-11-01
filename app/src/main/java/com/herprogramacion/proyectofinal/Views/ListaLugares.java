package com.herprogramacion.proyectofinal.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.herprogramacion.proyectofinal.Adapter.lugaresAdapter;
import com.herprogramacion.proyectofinal.BD.Conexion;
import com.herprogramacion.proyectofinal.Data.Config;
import com.herprogramacion.proyectofinal.Data.Lugar;
import com.herprogramacion.proyectofinal.Data.Lugar2;
import com.herprogramacion.proyectofinal.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lds on 27/10/2016.
 */
public class ListaLugares extends Fragment {

    ArrayList<Lugar> pro = new ArrayList<>();
    ArrayList<Lugar2> prop = new ArrayList<>();
    String namep;
    String lati;
    String longi;
    String nombre;

    ListView listaLug;

    private String JSON_STRING;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lista_lug, container, false);

        listaLug = (ListView) view.findViewById(R.id.lugList);

        getJSON();
        listaLug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView tv = (TextView) vg.findViewById(R.id.title);
                Intent ven = new Intent(getActivity(),MapsActivity.class);
                namep = tv.getText().toString();
                for(int i = 0; i< prop.size(); i++) {
                    if(prop.get(i).getNombre()==namep){
                        lati=prop.get(i).getLatitud();
                        longi=prop.get(i).getLongitud();
                        nombre=prop.get(i).getNombre();
                    }
                }
                ven.putExtra("latitud", lati);
                ven.putExtra("longitud", longi);
                ven.putExtra("nombre", nombre);
                startActivity(ven);
            }
        });

        return view;
    }


    private void showEmployee(){
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.LUG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.LUG_ID);
                String name = jo.getString(Config.LUG_NAME);
                String tipo = jo.getString(Config.LUG_TIPO);
                String lat = jo.getString(Config.LUG_LAT);
                String lon = jo.getString(Config.LUG_LONG);

                pro.add(new Lugar(name,tipo));
                prop.add(new Lugar2(id,name,tipo,lat,lon));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        lugaresAdapter adapter = new lugaresAdapter(getActivity(), pro);
        listaLug.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                Conexion rh = new Conexion();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
