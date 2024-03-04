package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Calendario extends AppCompatActivity {

    Usuario usuario;
    ListView listViewEvento;
    ArrayList <Evento> eventos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        listViewEvento = findViewById(R.id.listViewEventos);


        //Recogemos el objeto de usuario
        Bundle bundle  = getIntent().getExtras();
        //"usuario" es la clave que hemos utilizado para guardar nuestro objeto. Esta lnea convierte el objeto serializable en una instancia de la clase usuario
        usuario = (Usuario) bundle.getSerializable("usuario");

        eventos.addAll(usuario.getEventos());

        EventoAdapter adapter = new EventoAdapter(this,eventos);

        listViewEvento.setAdapter(adapter);


    }


    public void accederBienvenida(View view){

        Intent i = new Intent(this,Bienvenida.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }

    public void accederTask(View view){

        Intent i = new Intent(this,Task.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }

    public void accederEjercicios(View view){

        Intent i = new Intent(this,Ejercicios.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }

    public void accederPaginaUsuario(View view){

        Intent i = new Intent(this,PaginaUsuario.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }

    public void evento(View view) {
        Intent i = new Intent(this, nuevoEvento.class);
        startActivity(i);
    }

}