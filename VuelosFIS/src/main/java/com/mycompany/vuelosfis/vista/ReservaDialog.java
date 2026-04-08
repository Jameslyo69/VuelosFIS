package com.mycompany.vuelosfis.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ReservaDialog extends JDialog {

    private static final Color COLOR_CREMA = new Color(245, 242, 230);

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JSpinner spAsientos;
    private JLabel lblTotal;

    // ===== DATOS MOCK (solo para la vista) =====
    private final double PRECIO_MOCK = 120.0;
    private final int CUPOS_MOCK = 5;


//    private Vuelo vuelo; Usar esto cuando terminen los constructores

    public ReservaDialog(JFrame parent) {
        super(parent, "Comprar Pasaje", true);
        configurar();
        inicializar();
    }


    private void configurar() {
        setSize(850, 520);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }

    private void inicializar() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(COLOR_CREMA);
        setContentPane(panelPrincipal);

        // ================= DATOS VUELO =================
        JPanel panelVuelo = new JPanel(new GridLayout(2, 4, 10, 10));
        panelVuelo.setBackground(COLOR_CREMA);
        panelVuelo.setBorder(crearBorde("Datos del Vuelo Seleccionado"));

        panelVuelo.add(new JLabel("ID Vuelo:"));
        panelVuelo.add(new JLabel("----"));

//        panelVuelo.add(new JLabel(vuelo.getCodigo()));

        panelVuelo.add(new JLabel("Aerolínea:"));
        panelVuelo.add(new JLabel("----"));

//        panelVuelo.add(new JLabel(vuelo.getAvion().getModelo()));

        panelVuelo.add(new JLabel("Origen:"));
        panelVuelo.add(new JLabel("----"));

//        panelVuelo.add(new JLabel(vuelo.getRuta().getOrigen()));

        panelVuelo.add(new JLabel("Destino:"));
        panelVuelo.add(new JLabel("----"));

//        panelVuelo.add(new JLabel(vuelo.getRuta().getDestino()));

        panelVuelo.add(new JLabel("Fecha:"));
        panelVuelo.add(new JLabel("----"));

//        panelVuelo.add(new JLabel(vuelo.getFecha()));

        panelVuelo.add(new JLabel("Hora:"));
        panelVuelo.add(new JLabel("----"));

//        panelVuelo.add(new JLabel(vuelo.getHora()));

        panelVuelo.add(new JLabel("Precio:"));
        JLabel lblPrecio = new JLabel("€" + PRECIO_MOCK);

        lblPrecio.setForeground(new Color(0, 150, 0));
        panelVuelo.add(lblPrecio);

        panelVuelo.add(new JLabel("Disponibles:"));
        panelVuelo.add(new JLabel(CUPOS_MOCK + " asientos"));


        panelPrincipal.add(panelVuelo, BorderLayout.NORTH);

        // ================= PASAJERO =================
        JPanel panelPasajero = new JPanel(new GridLayout(2, 4, 10, 10));
        panelPasajero.setBackground(COLOR_CREMA);
        panelPasajero.setBorder(crearBorde("Datos del Pasajero"));

        txtCedula = new JTextField();
        txtNombre = new JTextField();
        txtCorreo = new JTextField();
        spAsientos = new JSpinner(
                new SpinnerNumberModel(1, 1, CUPOS_MOCK, 1)
        );

        panelPasajero.add(new JLabel("Cédula / DNI:"));
        panelPasajero.add(txtCedula);

        panelPasajero.add(new JLabel("Nombre Completo:"));
        panelPasajero.add(txtNombre);

        panelPasajero.add(new JLabel("Correo Electrónico:"));
        panelPasajero.add(txtCorreo);

        panelPasajero.add(new JLabel("Cantidad de Asientos:"));
        panelPasajero.add(spAsientos);

        panelPrincipal.add(panelPasajero, BorderLayout.CENTER);

        // ================= RESUMEN =================
        JPanel panelResumen = new JPanel(new GridLayout(3, 2, 10, 10));
        panelResumen.setBackground(Color.WHITE);
        panelResumen.setBorder(crearBorde("Resumen de Compra"));

        panelResumen.add(new JLabel("Precio por asiento:"));
        panelResumen.add(new JLabel("€" + PRECIO_MOCK));


        panelResumen.add(new JLabel("Cantidad de asientos:"));
        panelResumen.add(spAsientos);

        panelResumen.add(new JLabel("TOTAL A PAGAR:"));
        lblTotal = new JLabel();
        lblTotal.setForeground(new Color(0, 150, 0));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        panelResumen.add(lblTotal);

        actualizarTotal();

        spAsientos.addChangeListener(e -> actualizarTotal());

        panelPrincipal.add(panelResumen, BorderLayout.SOUTH);

        // ================= BOTONES =================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(COLOR_CREMA);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnComprar = new JButton("Comprar");

        panelBotones.add(btnCancelar);
        panelBotones.add(btnComprar);

        panelPrincipal.add(panelBotones, BorderLayout.PAGE_END);

        btnCancelar.addActionListener(e -> dispose());

        btnComprar.addActionListener(e -> comprar());
    }

    private void actualizarTotal() {
        int cant = (int) spAsientos.getValue();
        lblTotal.setText("€" + (PRECIO_MOCK * cant));
    }


    private void comprar() {
        JOptionPane.showMessageDialog(this,
                "Reserva realizada correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private TitledBorder crearBorde(String titulo) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12),
                new Color(0, 0, 150)
        );
    }
}
