/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vuelosfis;

import com.mycompany.vuelosfis.Controlador.BusquedaVuelosController;
import com.mycompany.vuelosfis.Controlador.ReservaController;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.csv.IReservaRepository;
import com.mycompany.vuelosfis.csv.IVueloRepository;
import com.mycompany.vuelosfis.csv.ReservaRepositoryArchivo;
import com.mycompany.vuelosfis.csv.VueloRepositoryArchivo;
import com.mycompany.vuelosfis.Servicio.CodigoService;
import com.mycompany.vuelosfis.Servicio.ReservaService;
import com.mycompany.vuelosfis.Servicio.VueloService;
import com.mycompany.vuelosfis.Util.Mensajes;
import com.mycompany.vuelosfis.vista.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VuelosFIS {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> iniciarApp());
    }

    private static void iniciarApp() {
       
        IVueloRepository vueloRepo = new VueloRepositoryArchivo();
        IReservaRepository reservaRepo = new ReservaRepositoryArchivo();

        // Servicios
        VueloService vueloService = new VueloService(vueloRepo);
        CodigoService codigoService = new CodigoService();
        ReservaService reservaService = new ReservaService(reservaRepo, vueloService, codigoService);

        // Controladores
        BusquedaVuelosController busquedaController = new BusquedaVuelosController(vueloService);
        ReservaController reservaController = new ReservaController(reservaService);

        // Vista principal
        MainFrame frame = new MainFrame();

        BusquedaVuelosPanel panelBusqueda = new BusquedaVuelosPanel();
        ReservasPanel panelReservas = new ReservasPanel();

        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Bienvenido a VuelosFIS", SwingConstants.CENTER), BorderLayout.CENTER);

        // Eventos navegación
        frame.getBtnBuscar().addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelBusqueda, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        frame.getBtnReservas().addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelReservas, BorderLayout.CENTER);
            panelReservas.mostrarReservas(reservaService.listarReservas());
            frame.revalidate();
            frame.repaint();
        });

        // Evento buscar
        panelBusqueda.getBtnBuscar().addActionListener(e -> {
            List<Vuelo> vuelos = busquedaController.buscar(
                    panelBusqueda.getOrigen(),
                    panelBusqueda.getDestino(),
                    panelBusqueda.getFecha()
            );
            panelBusqueda.mostrarResultados(vuelos);
        });

        // Evento reservar seleccionado
        panelBusqueda.getBtnReservar().addActionListener(e -> {
            String codigo = panelBusqueda.getCodigoVueloSeleccionado();
            if (codigo == null) {
                Mensajes.error(frame, "Selecciona un vuelo en la tabla.");
                return;
            }

            Vuelo vuelo = vueloService.buscarPorCodigo(codigo);
            if (vuelo == null) {
                Mensajes.error(frame, "No se encontró el vuelo seleccionado.");
                return;
            }

            ReservaDialog dialog = new ReservaDialog(frame);
            dialog.setVisible(true);

            if (!dialog.isConfirmado()) return;

            try {
                reservaController.reservar(vuelo, dialog.getCedula(), dialog.getNombre(), dialog.isComprar());
                Mensajes.info(frame, "Reserva guardada correctamente.");
            } catch (Exception ex) {
                Mensajes.error(frame, ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
