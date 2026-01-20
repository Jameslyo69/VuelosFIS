/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

public class Ruta {

    private String origen;
    private String destino;

    public Ruta() {
    }

    public Ruta(String origen, String destino) {

        if (origen == null || origen.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar la ciudad de origen");
        }

        if (destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar la ciudad de destino");
        }

        this.origen = origen;
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        if (origen != null && !origen.isEmpty()) {
            this.origen = origen;
        }
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino != null && !destino.isEmpty()) {
            this.destino = destino;
        }
    }

    @Override
    public String toString() {
        return origen + " -> " + destino;
    }
}
