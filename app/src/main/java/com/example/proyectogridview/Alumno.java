package com.example.proyectogridview;

public class Alumno {
    private int rut;
    private String nombre;
    private String direccion;
    private String comuna;
    private String imagePath;

    public Alumno(int rut, String nombre, String direccion, String comuna, String imagePath) {
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.comuna = comuna;
        this.imagePath = imagePath;
    }

    public int getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public String getImagePath() {
        return imagePath;
    }
}
