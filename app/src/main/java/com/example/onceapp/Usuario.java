package com.example.onceapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    //Variables
    String alias;
    String nombre;
    String apellidos;
    String email;
    int telefono;
    String sexo;
    String rol;
    String password;

    int puntuacionCiudades = 0;
    int puntuacionCapitales = 0;
    int puntuacionCulturalGeneral = 0;

    static ArrayList<Evento> eventos = new ArrayList<>();

    public Usuario(){

    }

    public Usuario(String alias, String nombre, String apellidos, String email, int telefono, String sexo, String rol, String password, ArrayList <Evento> eventos) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.sexo = sexo;
        this.rol = rol;
        this.password = password;
        this.eventos = eventos;
    }

    //getter y setter

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuntuacionCiudades() {
        return puntuacionCiudades;
    }

    public void setPuntuacionCiudades(int puntuacionCiudades) {
        this.puntuacionCiudades = puntuacionCiudades;
    }

    public int getPuntuacionCapitales() {
        return puntuacionCapitales;
    }

    public void setPuntuacionCapitales(int puntuacionCapitales) {
        this.puntuacionCapitales = puntuacionCapitales;
    }

    public int getPuntuacionCulturalGeneral() {
        return puntuacionCulturalGeneral;
    }

    public void setPuntuacionCulturalGeneral(int puntuacionCulturalGeneral) {
        this.puntuacionCulturalGeneral = puntuacionCulturalGeneral;
    }
}
