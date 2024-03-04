package com.example.onceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class nuevoEvento extends AppCompatActivity {


    Usuario usuario;

    //Variables de las pagina
    CardView btFechaInicio;
    CardView btFechaFin;
    CardView btHoraInicio;
    CardView btHoraFin;

    EditText titulo, descripcion;

    EditText textoFechaInicio, textoFechaFin, textoHoraInicio, textoHoraFin;

    CheckBox duracionTodoDia, vincularGoolge;

    //Variables para guardar las fechas

    int diaInicio, mesInicio,anioInicio;
    int diaFin,mesFin,anioFin;

    int horaInicio,minutoInicio;
    int horaFin,minFin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_evento);


        Log.d("NuevoEvento", "Entra evento");

        btFechaInicio = findViewById(R.id.btFechaInicio);
        btFechaFin = findViewById(R.id.btFechaFin);
        btHoraInicio = findViewById(R.id.btHoraInicio);
        btHoraFin = findViewById(R.id.btHoraFin);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);
        textoFechaInicio = findViewById(R.id.fechaInicio);
        textoFechaFin = findViewById(R.id.fechaFin);
        textoHoraInicio = findViewById(R.id.horaInicio);
        textoHoraFin  = findViewById(R.id.horaFin);
        duracionTodoDia = findViewById(R.id.checkBoxTodoDia);
        vincularGoolge = findViewById(R.id.checkBoxGoogle);



        //Recogemos el objeto de usuario
        Bundle bundle  = getIntent().getExtras();
        //"usuario" es la clave que hemos utilizado para guardar nuestro objeto. Esta lnea convierte el objeto serializable en una instancia de la clase usuario
        if (bundle != null) {
            usuario = (Usuario) bundle.getSerializable("usuario");
            // Resto del código
        } else {
            // Manejar el caso en que el Bundle es null
        }



        duracionTodoDia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Aqui agregamos el codigo que deseas ejecutar cuando el estado de la checkbox cambia
                if(isChecked){

                    //Checkbox está marcado
                    textoHoraInicio.setText("00:00");
                    textoHoraFin.setText("23:59");

                    // Deshabilita las vistas de hora
                    textoHoraInicio.setEnabled(false);
                    textoHoraFin.setEnabled(false);
                    btHoraInicio.setEnabled(false);
                    btHoraFin.setEnabled(false);

                }else{

                    //CheckBox desmarcado
                    // Si el CheckBox no está marcado, habilita las vistas de hora
                    textoHoraInicio.setEnabled(true);
                    textoHoraFin.setEnabled(true);
                    btHoraInicio.setEnabled(true);
                    btHoraFin.setEnabled(true);

                }

            }
        });



        vincularGoolge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    //Iniciamos actividad de inserccion de eventos


                }

            }
        });




    }

    public void cancelarCreacionTask (View view){

        Intent i = new Intent(this,Task.class);
        i.putExtra("usuario", usuario);
        startActivity(i);
    }


    public void fechaInicio(View view){

        final Calendar calendario = Calendar.getInstance();
        diaInicio = calendario.get(Calendar.DAY_OF_MONTH);
        mesInicio = calendario.get(Calendar.MONTH);
        anioInicio = calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int diaDeMes) {
                anioInicio = year;
                mesInicio = month;
                diaInicio = diaDeMes;

                textoFechaInicio.setText(diaInicio+"/"+mesInicio+"/"+anioInicio);
            }
        },diaInicio,mesInicio,anioInicio);
        datePickerDialog.show();

    }


    public void fechaFin(View view){

        final Calendar calendario = Calendar.getInstance();
        diaFin = calendario.get(Calendar.DAY_OF_MONTH);
        mesFin = calendario.get(Calendar.MONTH);
        anioFin = calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int diaDeMes) {
                anioFin = year;
                mesFin = month;
                diaFin = diaDeMes;

                textoFechaFin.setText(diaFin+"/"+mesFin+"/"+anioFin);
            }
        },diaFin,mesFin,anioFin);
        datePickerDialog.show();

    }

    public void horaInicio(View view){

        final Calendar calendario = Calendar.getInstance();
        horaInicio = calendario.get(Calendar.HOUR_OF_DAY);
        minutoInicio = calendario.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

                horaInicio = hora;
                minutoInicio = minuto;
                textoHoraInicio.setText(horaInicio+" : "+minutoInicio);
            }
        }, horaInicio, minutoInicio,false);
        timePickerDialog.show();
    }

    public void horaFin(View view) {

        final Calendar calendario = Calendar.getInstance();
        horaFin = calendario.get(Calendar.HOUR_OF_DAY);
        minFin = calendario.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {

                horaFin = hora;
                minFin = minuto;
                textoHoraFin.setText(horaFin + " : " + minFin);
            }
        }, horaFin, minFin, false);
        timePickerDialog.show();
    }

    public void btGuardar(View view){

        String fechaInicioTxt = textoFechaInicio.getText().toString();
        String fechaFinTxt = textoFechaFin.getText().toString();
        String horaInicioTxt = textoHoraInicio.getText().toString();
        String horaFinTxt = textoHoraFin.getText().toString();



        Evento nuevoEvento = new Evento(titulo.getText().toString(),descripcion.getText().toString(),fechaInicioTxt,fechaFinTxt,horaInicioTxt,horaFinTxt);

        Usuario.getEventos().add(nuevoEvento);

        Intent i = new Intent(this,Calendario.class);
        i.putExtra("usuario", usuario);
        startActivity(i);




    }




}