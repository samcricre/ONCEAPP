package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PaginaUsuario extends AppCompatActivity {

    Usuario usuario;

    TextView alias;
    TextView nombre;
    TextView apellidos;
    TextView email;
    TextView telefono;
    TextView sexo;
    TextView rol;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_usuario);

        Log.d("Usuario", "Entra usuario");

        //Recogemos el objeto de usuario
        Bundle bundle  = getIntent().getExtras();
        //"usuario" es la clave que hemos utilizado para guardar nuestro objeto. Esta lnea convierte el objeto serializable en una instancia de la clase usuario
        usuario = (Usuario) bundle.getSerializable("usuario");

        alias = findViewById(R.id.alias);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        email = findViewById(R.id.email);
        telefono = findViewById(R.id.telefono);
        rol = findViewById(R.id.rol);
        sexo = findViewById(R.id.sexo);


        alias.setText(usuario.getAlias());
        nombre.setText(usuario.getNombre());
        apellidos.setText(usuario.getApellidos());
        email.setText(usuario.getEmail());
        telefono.setText(String.valueOf(usuario.getTelefono()));
        rol.setText(usuario.getRol());
        sexo.setText(usuario.getSexo());


        Log.d("Usuario", "fin on create");

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
}