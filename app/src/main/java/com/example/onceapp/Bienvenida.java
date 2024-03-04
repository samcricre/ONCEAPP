package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Bienvenida extends AppCompatActivity {

    Usuario usuario;

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        //Recogemos el objeto de usuario
        Bundle bundle  = getIntent().getExtras();
        //"usuario" es la clave que hemos utilizado para guardar nuestro objeto. Esta lnea convierte el objeto serializable en una instancia de la clase usuario
        usuario = (Usuario) bundle.getSerializable("usuario");


        barChart = findViewById(R.id.barChart);
        // Crear una lista de entradas de datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, Math.max(usuario.getPuntuacionCapitales(), 0)));
        entries.add(new BarEntry(2, Math.max(usuario.getPuntuacionCiudades(), 0)));
        entries.add(new BarEntry(3, Math.max(usuario.getPuntuacionCulturalGeneral(), 0)));

        // Lista de etiquetas para las barras
        List<String> labels = new ArrayList<>();
        labels.add("Capitales");
        labels.add("Ciudades");
        labels.add("Cultura General");


        // Crear un conjunto de datos y agregar las entradas
        BarDataSet dataSet = new BarDataSet(entries, "Categorias");

        // Definir colores para las barras
        int[] colors = new int[] {Color.rgb(255, 102, 0), Color.rgb(0, 128, 255), Color.rgb(255, 0, 128)};
        dataSet.setColors(colors);


        // Crear un objeto BarData y asignar el conjunto de datos a él
        BarData barData = new BarData(dataSet);

        // Configurar el BarChart con los datos y otras opciones
        barChart.setData(barData);
        barChart.setFitBars(true); // Ajustar el ancho de las barras para que se ajusten al contenedor

        // Configurar las etiquetas en el eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels)); // Establecer las etiquetas personalizadas
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Colocar el eje X en la parte inferior
        xAxis.setGranularity(1f); // Espaciar las etiquetas por unidad
        xAxis.setTextColor(Color.WHITE);

        // Configurar el eje y del gráfico
        YAxis yAxis = barChart.getAxisLeft(); // Obtener el eje y izquierdo
        yAxis.setAxisMinimum(0f); // Establecer el mínimo en 0
        yAxis.setAxisMaximum(100f); // Establecer el máximo en 100
        yAxis.setTextColor(Color.WHITE);

        barChart.invalidate(); // Actualizar el gráfico



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

    public void accederPaginaUsuario(View view){

        Intent i = new Intent(this,PaginaUsuario.class);
        i.putExtra("usuario", usuario);
        startActivity(i);

    }
}