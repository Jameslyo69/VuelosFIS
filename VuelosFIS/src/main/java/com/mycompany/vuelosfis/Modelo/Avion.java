/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

public class Avion {
    private String codigo;
    private String modelo;
    private int capacidad;

    public Avion() {}

    public Avion(String codigo, String modelo, int capacidad) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() {
        return codigo + " (" + modelo + "), cap=" + capacidad;
    }
}
