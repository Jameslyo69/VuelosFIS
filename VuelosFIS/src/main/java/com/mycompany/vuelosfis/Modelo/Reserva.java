/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

public class Reserva {
    private String codigoReserva;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private EstadoReserva estado; 
    private String fechaRegistro; 

    public Reserva() {}

    public Reserva(String codigoReserva, Vuelo vuelo, Pasajero pasajero, EstadoReserva estado, String fechaRegistro) {
        this.codigoReserva = codigoReserva;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }

    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }

    public Pasajero getPasajero() { return pasajero; }
    public void setPasajero(Pasajero pasajero) { this.pasajero = pasajero; }

    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return codigoReserva + " | " + vuelo.getCodigo() + " | " + pasajero + " | " + estado + " | " + fechaRegistro;
    }
}
