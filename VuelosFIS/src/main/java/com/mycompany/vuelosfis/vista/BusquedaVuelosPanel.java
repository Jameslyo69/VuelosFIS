/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.vista;

import com.mycompany.vuelosfis.Modelo.Vuelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BusquedaVuelosPanel extends JPanel {

    private final JTextField txtOrigen = new JTextField(10);
    private final JTextField txtDestino = new JTextField(10);
    private final JTextField txtFecha = new JTextField(10); // YYYY-MM-DD
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnReservar = new JButton("Reservar seleccionado");

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Código", "Origen", "Destino", "Fecha", "Hora", "Precio", "Cupos"}, 0
    );
    private final JTable tabla = new JTable(model);

    public BusquedaVuelosPanel() {
        setLayout(new BorderLayout());

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtros.add(new JLabel("Origen:"));
        filtros.add(txtOrigen);
        filtros.add(new JLabel("Destino:"));
        filtros.add(txtDestino);
        filtros.add(new JLabel("Fecha (YYYY-MM-DD):"));
        filtros.add(txtFecha);
        filtros.add(btnBuscar);

        add(filtros, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnReservar);
        add(abajo, BorderLayout.SOUTH);
    }

    public String getOrigen() { return txtOrigen.getText(); }
    public String getDestino() { return txtDestino.getText(); }
    public String getFecha() { return txtFecha.getText(); }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnReservar() { return btnReservar; }

    public void mostrarResultados(List<Vuelo> vuelos) {
        model.setRowCount(0);
        for (Vuelo v : vuelos) {
            model.addRow(new Object[]{
                    v.getCodigo(),
                    v.getRuta().getOrigen(),
                    v.getRuta().getDestino(),
                    v.getFecha(),
                    v.getHora(),
                    v.getPrecio(),
                    v.getCuposDisponibles()
            });
        }
    }

    public String getCodigoVueloSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return null;
        return model.getValueAt(fila, 0).toString();
    }
}
