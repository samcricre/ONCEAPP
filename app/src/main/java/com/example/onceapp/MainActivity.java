package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inicioSesion(View view){

        Intent i = new Intent(this, Login.class);
        startActivity(i);

    }

    public void registro(View view){

        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void evento(View view){
        Intent i = new Intent(this, nuevoEvento.class);
        startActivity(i);
    }

}