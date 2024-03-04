package com.example.onceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter <Evento> {

    private Context mContext;
    private ArrayList<Evento> mEventos;

    public EventoAdapter(Context context, ArrayList<Evento> eventos) {
        super(context, 0, eventos);
        mContext = context;
        mEventos = eventos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        }

        Evento evento = mEventos.get(position);

        TextView textTitulo = listItem.findViewById(R.id.textTitulo);
        textTitulo.setText(evento.getTitulo());

        // Concatenamos las fechas en una sola cadena para mostrarlas juntas
        String fechas = evento.getFechaInicio() + " - " + evento.getFechaFin();
        TextView textFechas = listItem.findViewById(R.id.textFechas);
        textFechas.setText(fechas);

        return listItem;
    }

}
