/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.csv;

import com.mycompany.vuelosfis.Modelo.EstadoReserva;
import com.mycompany.vuelosfis.Modelo.Pasajero;
import com.mycompany.vuelosfis.Modelo.Reserva;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.Util.CSVUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryArchivo implements IReservaRepository {

    private final Path rutaArchivo;

    public ReservaRepositoryArchivo() {
        this.rutaArchivo = Paths.get("data", "reservas.csv");
    }

    @Override
    public List<Reserva> cargarReservas() {
        List<Reserva> reservas = new ArrayList<>();

        if (!Files.exists(rutaArchivo)) {
            System.out.println("No existe: " + rutaArchivo.toAbsolutePath());
            return reservas;
        }

        try (BufferedReader br = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {
                if (primera) { primera = false; continue; }
                if (linea.trim().isEmpty()) continue;

                String[] p = CSVUtil.splitCSV(linea);
                if (p.length < 6) continue;

                String codigoReserva = p[0];
                String codigoVuelo = p[1];
                String cedula = p[2];
                String nombre = p[3];

                EstadoReserva estado;
                try {
                    estado = EstadoReserva.valueOf(p[4].trim());
                } catch (Exception ex) {
                    continue;
                }
                String fechaRegistro = p[5];

                Vuelo vuelo = new Vuelo();
                vuelo.setCodigo(codigoVuelo);

                Pasajero pasajero = new Pasajero(cedula, nombre);

                Reserva r = new Reserva(codigoReserva, vuelo, pasajero, estado, fechaRegistro);
                reservas.add(r);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Error leyendo reservas.csv: " + e.getMessage());
        }

        return reservas;
    }

    @Override
    public void guardarReserva(Reserva r) {
        try {
            if (!Files.exists(rutaArchivo)) {
                Files.createDirectories(rutaArchivo.getParent());
                try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.CREATE)) {
                    bw.write("codigoReserva,codigoVuelo,cedula,nombreCompleto,estado,fechaRegistro");
                    bw.newLine();
                }
            }

            try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.APPEND)) {
                String linea = String.join(",",
                        CSVUtil.safe(r.getCodigoReserva()),
                        CSVUtil.safe(r.getVuelo().getCodigo()),
                        CSVUtil.safe(r.getPasajero().getCedula()),
                        CSVUtil.safe(r.getPasajero().getNombreCompleto()),
                        CSVUtil.safe(r.getEstado().name()),
                        CSVUtil.safe(r.getFechaRegistro())
                );
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando reserva: " + e.getMessage());
        }
    }
}

