package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Task extends AppCompatActivity {

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //Recogemos el objeto de usuario
        Bundle bundle  = getIntent().getExtras();
        //"usuario" es la clave que hemos utilizado para guardar nuestro objeto. Esta lnea convierte el objeto serializable en una instancia de la clase usuario
        usuario = (Usuario) bundle.getSerializable("usuario");

    }



    public void accederBienvenida(View view){

        Intent i = new Intent(this,Bienvenida.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }


    public void accederCalendario(View view){

        Intent i = new Intent(this,Calendario.class);
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



}