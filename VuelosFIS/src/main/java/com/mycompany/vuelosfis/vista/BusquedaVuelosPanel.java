package com.mycompany.vuelosfis.vista;

import com.mycompany.vuelosfis.Modelo.Vuelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BusquedaVuelosPanel extends JPanel {

    // =========================
    // CONSTANTES DE ESTILO
    // =========================
    private static final Color COLOR_CREMA = new Color(245, 245, 220);

    // =========================
    // CAMPOS
    // =========================
    private final JTextField txtOrigen = new JTextField(12);
    private final JTextField txtDestino = new JTextField(12);
    private final JTextField txtFecha = new JTextField(12);

    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnReservar = new JButton("Reservar seleccionado");

    // =========================
    // TABLA
    // =========================
    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Código", "Origen", "Destino", "Fecha", "Hora", "Precio", "Cupos"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // tabla solo lectura
        }
    };

    private final JTable tabla = new JTable(model);

    // =========================
    // CONSTRUCTOR
    // =========================
    public BusquedaVuelosPanel() {

        setLayout(new BorderLayout(10, 10));
        setBackground(COLOR_CREMA);

        // =========================
        // PANEL CRITERIOS
        // =========================
        JPanel filtros = new JPanel(new GridLayout(2, 4, 10, 10));
        filtros.setBackground(COLOR_CREMA);
        filtros.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Criterios de Búsqueda",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12),
                new Color(0, 0, 150)
        ));

        filtros.add(new JLabel("Origen:"));
        filtros.add(txtOrigen);
        filtros.add(new JLabel("Destino:"));
        filtros.add(txtDestino);

        filtros.add(new JLabel("Fecha (YYYY-MM-DD):"));
        filtros.add(txtFecha);
        filtros.add(new JLabel(""));
        filtros.add(btnBuscar);

        add(filtros, BorderLayout.NORTH);

        // =========================
        // PANEL RESULTADOS
        // =========================
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBackground(COLOR_CREMA);
        panelResultados.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Vuelos Encontrados",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12),
                new Color(0, 0, 150)
        ));

        // =========================
        // CONFIG TABLA
        // =========================
        tabla.setRowHeight(22);
        tabla.setGridColor(Color.LIGHT_GRAY);
        tabla.setSelectionBackground(new Color(200, 200, 255));
        tabla.getTableHeader().setBackground(new Color(220, 220, 220));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);

        panelResultados.add(scroll, BorderLayout.CENTER);
        add(panelResultados, BorderLayout.CENTER);

        // =========================
        // PANEL INFERIOR
        // =========================
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.setBackground(COLOR_CREMA);

        btnBuscar.setBackground(new Color(220, 220, 220));
        btnBuscar.setFocusPainted(false);

        btnReservar.setBackground(new Color(220, 220, 220));
        btnReservar.setFocusPainted(false);

        abajo.add(btnReservar);
        add(abajo, BorderLayout.SOUTH);
    }

    // =========================
    // GETTERS
    // =========================
    public String getOrigen() {
        return txtOrigen.getText().trim();
    }

    public String getDestino() {
        return txtDestino.getText().trim();
    }

    public String getFecha() {
        return txtFecha.getText().trim();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnReservar() {
        return btnReservar;
    }

    // =========================
    // MÉTODOS
    // =========================
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

    private void abrirReserva() {
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        ReservaDialog dialog = new ReservaDialog(parent);
        dialog.setVisible(true);
        btnReservar.addActionListener(e -> abrirReserva());

    }

}

