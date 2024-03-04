package com.example.onceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText usuarioEditText;
    EditText contrasenaEditText;

    String usuario;
    String contrasena;

    //Variable database
    private DatabaseReference mDatabase;

    // Agrega una referencia a Firebase Authentication
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usuarioEditText = findViewById(R.id.usuarioEditText);
        contrasenaEditText = findViewById(R.id.contrasenaEditTExt);


        user = FirebaseAuth.getInstance().getCurrentUser();

        Log.d("Login", "Error al agregar usuario a la base de datos");
    }

    public void accederLogin(View view) {
        usuario = usuarioEditText.getText().toString();
        contrasena = contrasenaEditText.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");


        Log.d("Login", "fuera del listener");
        // Consulta a la base de datos para obtener el usuario correspondiente
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Login", "Entra en el snapshot");
                boolean encontrado = false;
                if (dataSnapshot.exists()) {
                    Log.d("Login", "supera el if");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.d("Login","supera el for");
                        Usuario usuarioDB = snapshot.getValue(Usuario.class);
                        assert usuarioDB != null;
                        if (usuarioDB.getAlias().equals(usuario) && usuarioDB.getPassword().equals(contrasena)) {
                            Usuario usuarioLogin = usuarioDB;
                            // Usuario y contraseña coinciden, iniciar sesión exitosa
                            // Aquí puedes abrir la actividad de bienvenida o hacer lo que sea necesario
                            Intent intent = new Intent(Login.this, Bienvenida.class);
                            intent.putExtra("usuario",usuarioLogin);
                            startActivity(intent);
                            Log.d("Login", "deberia pasar a bienvenida");
                            encontrado = true;
                            break;
                        }
                    }
                    // Si no se encuentra el usuario o la contraseña no coincide
                    if (!encontrado) {
                        Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // No hay usuarios registrados en la base de datos
                    Toast.makeText(Login.this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error al acceder a la base de datos
                Toast.makeText(Login.this, "Error al acceder a la base de datos", Toast.LENGTH_SHORT).show();
            }
        });

    }





}
