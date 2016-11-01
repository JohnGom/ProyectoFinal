package com.herprogramacion.proyectofinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.herprogramacion.proyectofinal.Data.Lugar;
import com.herprogramacion.proyectofinal.R;

import java.util.ArrayList;

/**
 * Created by lds on 30/10/2016.
 */
public class lugaresAdapter extends ArrayAdapter<Lugar> {
    private final Context contexto;
    private ArrayList<Lugar> datosLugar;

        public lugaresAdapter(Context context, ArrayList<Lugar> datosLugar) {
        super(context, -1, datosLugar);
        this.contexto = context;
        this.datosLugar = datosLugar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View LayoutPro = inflater.inflate(R.layout.item_lugares, parent, false);

        TextView namel = (TextView) LayoutPro.findViewById(R.id.title);
        TextView tipl = (TextView) LayoutPro.findViewById(R.id.descrip);

        Lugar datos = datosLugar.get(position);

        namel.setText(datos.getNombre());
        tipl.setText(datos.getTipo());

        return LayoutPro;

    }

}
