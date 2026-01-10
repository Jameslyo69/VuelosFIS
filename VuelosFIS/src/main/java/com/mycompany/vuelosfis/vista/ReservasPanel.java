/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.vista;

import com.mycompany.vuelosfis.Modelo.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReservasPanel extends JPanel {

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Código", "Vuelo", "Pasajero", "Estado", "Fecha"}, 0
    );
    private final JTable tabla = new JTable(model);
    private final JButton btnRefrescar = new JButton("Refrescar");

    public ReservasPanel() {
        setLayout(new BorderLayout());
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnRefrescar);
        add(abajo, BorderLayout.SOUTH);
    }

    public JButton getBtnRefrescar() { return btnRefrescar; }

    public void mostrarReservas(List<Reserva> reservas) {
        model.setRowCount(0);
        for (Reserva r : reservas) {
            String vueloCod = (r.getVuelo() != null) ? r.getVuelo().getCodigo() : "";
            model.addRow(new Object[]{
                    r.getCodigoReserva(),
                    vueloCod,
                    r.getPasajero(),
                    r.getEstado(),
                    r.getFechaRegistro()
            });
        }
    }
}
