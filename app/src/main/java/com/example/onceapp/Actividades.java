package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Actividades extends AppCompatActivity {

    //Declaramos las variables generales
    //titulo de app
    TextView titulo;
    //selector de categorias
    Spinner spinner;
    //Seleccion de categoria de spinner
    String seleccionSpinner;
    //puntuacion
    int puntuacion = 0;
    //Pregunta
    TextView textoPregunta;
    //Posibles respuestas a las preguntas
    RadioButton opcionesRespuesta1;
    RadioButton opcionesRespuesta2;
    RadioButton opcionesRespuesta3;
    RadioButton opcionesRespuesta4;
    RadioGroup grupoRespuestasCorrectas;
    //Texto resultado respuesta
    TextView textoResultadoRespuesta;
    //Boton que comprueba la respuesta
    Button btCompruebaRespuesta;
    //Variable que guarda el indice de la pregunta aleatoria
    int indicePreguntaAleatoria;
    //Texto donde se muestra la puntuacion
    TextView textoPuntuacion;
    //Botón para restablcer pregunta
    Button btRestablecerPregunta;

    CardView cardViewCorrecionVisible;

    //Variable para recoger usuario
    Usuario usuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        //Recogemos el objeto de usuario
        Bundle bundle  = getIntent().getExtras();
        //"usuario" es la clave que hemos utilizado para guardar nuestro objeto. Esta lnea convierte el objeto serializable en una instancia de la clase usuario
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
            // Resto del código
        } else {
            // Manejar el caso en que el Bundle es null
        }

        //Vinculamos la variable al id del widget
        titulo = findViewById(R.id.tituloQuiz);
        spinner = findViewById(R.id.spinner);
        textoPregunta = findViewById(R.id.textoPregunta);
        opcionesRespuesta1 = findViewById(R.id.radioButton1);
        opcionesRespuesta2 = findViewById(R.id.radioButton2);
        opcionesRespuesta3 = findViewById(R.id.radioButton3);
        opcionesRespuesta4 = findViewById(R.id.radioButton4);
        grupoRespuestasCorrectas = findViewById(R.id.grupoRespuestas);
        textoResultadoRespuesta = findViewById(R.id.textoResultadoRespuesta);
        btCompruebaRespuesta = findViewById(R.id.btComppruebaResuesta);
        textoPuntuacion = findViewById(R.id.textoPuntuacion);
        btRestablecerPregunta = findViewById(R.id.restablecerPregunta);

        cardViewCorrecionVisible = findViewById(R.id.cardViewRespuestaCorrecion);

        //Ocultanmos la cardview donde aparece el texto de respuesta a la comprobacion de la pregunta
        cardViewCorrecionVisible.setVisibility(View.INVISIBLE);
        textoResultadoRespuesta.setVisibility(View.INVISIBLE);

        //Lista donde guardamos las opciones del spinner
        ArrayList<String> opciones = new ArrayList<>();

        opciones.add("Ciudades");
        //opciones.add("Banderas");
        opciones.add("Capitales");
        opciones.add("Cultura general");

        //Vinculamos el arrayList al spinner a traves de un adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //Escucha de la seleccion del spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //guardamos la selecion del spinner en una variable
                seleccionSpinner = (String) spinner.getSelectedItem().toString();
                //Modificamos el titulo para comprobar que la seleccion se ha guardado en la variable
                titulo.setText(seleccionSpinner);

                if(seleccionSpinner.equalsIgnoreCase("Ciudades")){
                    indicePreguntaAleatoria=ciudades();
                }

                if(seleccionSpinner.equalsIgnoreCase("Capitales")){
                    indicePreguntaAleatoria = capitales();
                }


                if(seleccionSpinner.equalsIgnoreCase("Cultura general")){
                    indicePreguntaAleatoria= culturaGeneral();
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btCompruebaRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprobarRespuesta(indicePreguntaAleatoria,puntuacion,seleccionSpinner);
            }
        });

        btRestablecerPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restablecerGrupoRespuestas();

            }
        });


    }



    //MÉTODO QUE MUESTRA LAS PREGUNTAS SOBRE CIUDADES
    public int ciudades(){

        //Array donde se recogen las respuestas de todos las preguntas,
        //declarado en cada una de las categorias para que se resetee con el cambo de categoria
        ArrayList<String []> grupoRespuestas = new ArrayList<>();

        String [] preguntas = new String [5];


        preguntas[0] = "¿En qué ciudad europea se encuentra la famosa Torre de Pisa?";
        preguntas[1] = "¿Qué ciudad europea es conocida por su vida nocturna y su famosa Puerta de Brandeburgo?";
        preguntas [2] = "¿En cuál de las siguientes ciudades europeas se encuentra el Palacio de Buckingham?";
        preguntas [3] = "¿Qué ciudad europea es famosa por su pintoresco sistema de canales y puentes?";
        preguntas [4] = "¿Cuál de las siguientes ciudades es conocida por su animado mercado navideño en la Plaza del Ayuntamiento?";

        //Todas las respuestas de la 1ª pregunta
        grupoRespuestas.add(new String[] {"Venecia", "Florencia","Milán","Roma"});
        //Todas las respuestas de la 2ª pregunta
        grupoRespuestas.add(new String[] {"Berlín","Praga","Viena","Amsterdam","Budapest"});
        //Todas las respuestas de la 3ª pregunta
        grupoRespuestas.add(new String[] {"París","Viena","Londres","Barcelona"});
        //Todas las respuestas de la 4ª pregunta
        grupoRespuestas.add(new String[] {"Lisboa","Bruselas","Amsterdam","Madrid"});
        //Todas las resouestas de la 5ª pregunta
        grupoRespuestas.add(new String[] {"Helsinki","Copenhague","Salzburgo","Praga"});

        //Generamos un numero aleatorio para elegir una pregunta
        Random random = new Random();
        int indicePreguntaAleatoria = random.nextInt(preguntas.length);

        //Se escoge las respuestas en funcion de la pregunta seleccionada
        String [] respuestas = grupoRespuestas.get(indicePreguntaAleatoria);

        //Impresion de la pregunta y sus respuestas
        textoPregunta.setText(preguntas[indicePreguntaAleatoria]);
        opcionesRespuesta1.setText(respuestas[0]);
        opcionesRespuesta2.setText(respuestas[1]);
        opcionesRespuesta3.setText(respuestas[2]);
        opcionesRespuesta4.setText(respuestas[3]);


        return indicePreguntaAleatoria;


    }

    //MÉTODO QUE PREGUNTA SOBRE LAS CAPITALES
    public int capitales(){

        //Array donde se recogen las respuestas de todos las preguntas,
        //declarado en cada una de las categorias para que se resetee con el cambo de categoria
        ArrayList<String []> grupoRespuestas = new ArrayList<>();

        String [] preguntas = new String [5];


        preguntas[0] = "¿Cuál es la capital de Francia?";
        preguntas[1] = "¿Cuál es la capital de Japón?";
        preguntas [2] = "¿Cuál es la capital de Australia?";
        preguntas [3] = "¿Cuál es la capital de Brasil?";
        preguntas [4] = "¿Cuál es la capital de México?";

        //Todas las respuestas de la 1ª pregunta
        grupoRespuestas.add(new String[] {"Madrid", "París","Berlín","Roma"});
        //Todas las respuestas de la 2ª pregunta
        grupoRespuestas.add(new String[] {"Seúl","Pekín","Tokio","Bangkok"});
        //Todas las respuestas de la 3ª pregunta
        grupoRespuestas.add(new String[] {"Wellintong","Sídney","Canberra","Auckland"});
        //Todas las respuestas de la 4ª pregunta
        grupoRespuestas.add(new String[] {"Río de Janeiro","Brasilia","São Paulo","Salvador"});
        //Todas las resouestas de la 5ª pregunta
        grupoRespuestas.add(new String[] {"Ciudad de México","Guadalajara","Monterrey","Puebla"});

        //Generamos un numero aleatorio para elegir una pregunta
        Random random = new Random();
        int indicePreguntaAleatoria = random.nextInt(preguntas.length);

        //Se escoge las respuestas en funcion de la pregunta seleccionada
        String [] respuestas = grupoRespuestas.get(indicePreguntaAleatoria);

        //Impresion de la pregunta y sus respuestas
        textoPregunta.setText(preguntas[indicePreguntaAleatoria]);
        opcionesRespuesta1.setText(respuestas[0]);
        opcionesRespuesta2.setText(respuestas[1]);
        opcionesRespuesta3.setText(respuestas[2]);
        opcionesRespuesta4.setText(respuestas[3]);


        return indicePreguntaAleatoria;


    }

    //MÉTODO QUE PREGUNTA SOBRE CULTURA GENERAL
    public int culturaGeneral(){

        //Array donde se recogen las respuestas de todos las preguntas,
        //declarado en cada una de las categorias para que se resetee con el cambo de categoria
        ArrayList<String []> grupoRespuestas = new ArrayList<>();

        String [] preguntas = new String [5];


        preguntas[0] = "¿Quién pintó la Mona Lisa?";
        preguntas[1] = "¿Cuál de los siguientes planetas es conocido como el 'Planeta Rojo'?";
        preguntas [2] = "¿En qué año comenzó la Primera Guerra Mundial?";
        preguntas [3] = "¿Quién fue el famoso físico teórico conocido por desarrollar la teoría de la relatividad?";
        preguntas [4] = "¿Quién escribió la obra 'Romeo y Julieta'?";

        //Todas las respuestas de la 1ª pregunta
        grupoRespuestas.add(new String[] {"Vincent van Gogh", "Leonardo da Vinci","Pablo Picasso","Michelangelo"});
        //Todas las respuestas de la 2ª pregunta
        grupoRespuestas.add(new String[] {"Júpiter","Marte","Venus","Saturno"});
        //Todas las respuestas de la 3ª pregunta
        grupoRespuestas.add(new String[] {"1492","1914","1861","1929"});
        //Todas las respuestas de la 4ª pregunta
        grupoRespuestas.add(new String[] {"Isaac Newton","Albert Einstein","Marie Curie","Galileo Galilei"});
        //Todas las resouestas de la 5ª pregunta
        grupoRespuestas.add(new String[] {"William Shakespeare","Charles Dickens","Jane Austen","Fyodor Dostoevsky"});

        //Generamos un numero aleatorio para elegir una pregunta
        Random random = new Random();
        int indicePreguntaAleatoria = random.nextInt(preguntas.length);

        //Se escoge las respuestas en funcion de la pregunta seleccionada
        String [] respuestas = grupoRespuestas.get(indicePreguntaAleatoria);

        //Impresion de la pregunta y sus respuestas
        textoPregunta.setText(preguntas[indicePreguntaAleatoria]);
        opcionesRespuesta1.setText(respuestas[0]);
        opcionesRespuesta2.setText(respuestas[1]);
        opcionesRespuesta3.setText(respuestas[2]);
        opcionesRespuesta4.setText(respuestas[3]);


        return indicePreguntaAleatoria;


    }


    //MÉTODO QUE COMPRUEBA LA RESPUESTA
    public void comprobarRespuesta(int indicePreguntaAleatoria, int puntos, String seleccionSpinner){

        //Obtenemos el indice del boton seleecionado
        int radioButtonID = grupoRespuestasCorrectas.getCheckedRadioButtonId();
        //Obten el radiobutton segun el indice
        RadioButton radioButtonSeleccionado = findViewById(radioButtonID);
        //Obtenemos el texto de ese rdioButton
        String textoRadioButtonSeleccionado = radioButtonSeleccionado.getText().toString();

        String [] respuestasCorrectas = new String [5];

        switch (seleccionSpinner){

            case "Ciudades":

                respuestasCorrectas[0] = "Roma";
                respuestasCorrectas[1] = "Berlín";
                respuestasCorrectas[2] = "Londres";
                respuestasCorrectas[3] = "Ámsterdam";
                respuestasCorrectas[4] = "Praga";
                break;

            case "Capitales":

                respuestasCorrectas[0] = "París";
                respuestasCorrectas[1] = "Tokio";
                respuestasCorrectas[2] = "Canberra";
                respuestasCorrectas[3] = "Brasilia";
                respuestasCorrectas[4] = "Ciudad de México";
                break;


            case "Cultura general":

                respuestasCorrectas[0] = "Leonardo da Vinci";
                respuestasCorrectas[1] = "Marte";
                respuestasCorrectas[2] = "1914";
                respuestasCorrectas[3] = "Albert Einstein";
                respuestasCorrectas[4] = "William Shakespeare";
                break;

        }

        //mostramos el cardview donde va si la respuesta es correcta o incorrecta
        cardViewCorrecionVisible.setVisibility(View.VISIBLE);
        textoResultadoRespuesta.setVisibility(View.VISIBLE);

        String repuestaComprobar = respuestasCorrectas[indicePreguntaAleatoria];

        if(textoRadioButtonSeleccionado.equalsIgnoreCase(repuestaComprobar)){

            //Cambiamos el color de la card view a verde para indicar correcto
            cardViewCorrecionVisible.setCardBackgroundColor(Color.parseColor("#4CD067"));
            textoResultadoRespuesta.setText("RESPUESTA CORRECTA");
            puntuacion = puntos + 10;

            //dividmos los puntos en funcion de la categoria escogida
            switch (seleccionSpinner){

                case "Ciudades":

                    usuario.setPuntuacionCiudades(usuario.getPuntuacionCiudades() + 10);
                    break;

                case "Capitales":

                    usuario.setPuntuacionCapitales(usuario.getPuntuacionCapitales()+10);
                    break;


                case "Cultura general":

                    usuario.setPuntuacionCulturalGeneral(usuario.getPuntuacionCulturalGeneral() + 10);
                    break;

            }

        }else{

            //Cambiamos el color de la card view a rojo para indicar incorrecto
            cardViewCorrecionVisible.setCardBackgroundColor(Color.parseColor("#CA2B1F"));
            textoResultadoRespuesta.setText("RESPUESTA INCORRECTA");
            puntuacion = puntos - 5;

            switch (seleccionSpinner){

                case "Ciudades":

                    usuario.setPuntuacionCiudades(usuario.getPuntuacionCiudades() -5);
                    break;

                case "Capitales":

                    usuario.setPuntuacionCapitales(usuario.getPuntuacionCapitales()-5);
                    break;


                case "Cultura general":

                    usuario.setPuntuacionCulturalGeneral(usuario.getPuntuacionCulturalGeneral() -5);
                    break;

            }



        }

        //Condicional para establecer el color de la puntuación en funcion de si vamos en positivo o negativo
        if(puntuacion>0){

            textoPuntuacion.setTextColor(Color.parseColor("#4CD067"));//ponemos de color verde en caso de que la puntuación sea positiva

        }else if(puntuacion<0){

            textoPuntuacion.setTextColor(Color.parseColor("#FF0000"));//ponemos de color rojo en caso de que la puntuación sea negativa

        }else{

            textoPuntuacion.setTextColor(Color.parseColor("#FFFFFF"));//ponemos de color blanco en caso de que la puntuación sea 0
        }

        textoPuntuacion.setText(String.valueOf(puntuacion));



    }


    // Método para restablecer la pregunta y actualizar la interfaz
    public void restablecerGrupoRespuestas() {
        grupoRespuestasCorrectas.clearCheck();
        textoResultadoRespuesta.setText("");

        // Llamada al método para obtener una nueva pregunta aleatoria
        indicePreguntaAleatoria = obtenerNuevaPreguntaAleatoria(seleccionSpinner);
    }

    // Método para obtener una nueva pregunta aleatoria
    private int obtenerNuevaPreguntaAleatoria(String seleccionSpinner) {

        cardViewCorrecionVisible.setVisibility(View.INVISIBLE);
        textoResultadoRespuesta.setVisibility(View.INVISIBLE);

        switch (seleccionSpinner) {
            case "Ciudades":
                return ciudades();
            case "Capitales":
                return capitales();
            case "Cultura general":
                return culturaGeneral();
            default:
                return 0; // Manejo de errores, ajusta según sea necesario
        }
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

    public void accederPaginaUsuario(View view){

        Intent i = new Intent(this,PaginaUsuario.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }


}