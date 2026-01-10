/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

public class Vuelo {
    private String codigo;
    private Ruta ruta;
    private Avion avion;
    private String fecha; // YYYY-MM-DD
    private String hora;  // HH:mm
    private double precio;
    private int cuposDisponibles;

    public Vuelo() {}

    public Vuelo(String codigo, Ruta ruta, Avion avion, String fecha, String hora, double precio, int cuposDisponibles) {
        this.codigo = codigo;
        this.ruta = ruta;
        this.avion = avion;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.cuposDisponibles = cuposDisponibles;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) { this.ruta = ruta; }

    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCuposDisponibles() { return cuposDisponibles; }
    public void setCuposDisponibles(int cuposDisponibles) { this.cuposDisponibles = cuposDisponibles; }

    @Override
    public String toString() {
        return codigo + " | " + ruta + " | " + fecha + " " + hora + " | $" + precio + " | cupos=" + cuposDisponibles;
    }
}
