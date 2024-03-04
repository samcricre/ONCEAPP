package com.example.onceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {


    //Variables de la pantalla
    EditText nombreEditText;
    EditText aliasEditText;
    EditText apellidosEditText;
    EditText emailEditText;
    EditText telefonoEditText;
    EditText passwordEditText;

    Spinner spinRol;
    Spinner spinSexo;

    //Variable database
    private DatabaseReference mDatabase;

    //Variables para el objeto usuario
    String alias;
    String nombre;
    String apellidos;
    String email;
    String rol;
    String sexo;
    int telefono;
    String password;
    ArrayList<Evento> eventos;

    Usuario usuario;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            Log.d("REGISTRO","Usuario  autenticado");
        }else{
            Log.d("REGISTRO", "Usuario no autenticado");
        }



        //Vinculamos las varaibles de los elementos a los elementos de la pantalla
        aliasEditText = findViewById(R.id.aliasEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        apellidosEditText = findViewById(R.id.apellidosEditText);
        emailEditText = findViewById(R.id.emailEditText);
        telefonoEditText = findViewById(R.id.telefonoEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        spinRol = findViewById(R.id.spinRol);
        spinSexo = findViewById(R.id.spinSexo);

        //Creamos las opciones del spin de sexo
        String [] sexos = {"Hombre", "Mujer"};
        //Array de roles
        String [] roles = {"Alumno", "Profesor"};

        //Vinculamos el array roles al spiner a traves de un adapater
        ArrayAdapter<String> adapterRol = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapterRol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //vinculamos el array sexos
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexos);
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinRol.setAdapter(adapterRol);
        spinSexo.setAdapter(adapterSexo);



    }

    public void accederRegistro(View view){

        //Guardamos en las variables lo extraido en los elementos
        alias = aliasEditText.getText().toString();
        nombre = nombreEditText.getText().toString();
        apellidos = apellidosEditText.getText().toString();
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        String telefonoStr = telefonoEditText.getText().toString();
        if(!telefonoStr.isEmpty()){
            telefono = Integer.parseInt(telefonoEditText.getText().toString());
        }else{
            telefono = 000000000;
        }
        rol = spinRol.getSelectedItem().toString();
        sexo = spinSexo.getSelectedItem().toString();
        eventos = new ArrayList<>();


        //Creamos el objeto usuario para añadirlo en la base de datos
        usuario = new Usuario(alias, nombre, apellidos,email,telefono,sexo, rol, password, eventos);

        try{

            mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");


            //Escribimos usuario nuevo en la base de datos
            String key = mDatabase.child("usuarios").push().getKey();
            Log.d("Registro", key);
            mDatabase.child(key).setValue(usuario);
            //mDatabase.child(String.valueOf(numUser)).setValue(usuario);



            Log.d("Registro", "Usuario agregado correctamente");
        }catch (Exception e){

            // Maneja la excepción
            Log.e("Registro", "Error al agregar usuario a la base de datos", e);
        }

        Intent i = new Intent(this,Bienvenida.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }

}