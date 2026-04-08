package com.mycompany.vuelosfis.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private BusquedaVuelosPanel busquedaPanel;
    private ReservasPanel reservasPanel;

    public MainFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Vuelos");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        setContentPane(panelPrincipal);

        // ================= HEADER =================
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 120, 215));
        panelHeader.setPreferredSize(new Dimension(100, 36));

        JLabel lblTitulo = new JLabel("Sistema de Gestión de Vuelos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(new EmptyBorder(8, 12, 8, 0));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

        panelHeader.add(lblTitulo, BorderLayout.WEST);
        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ================= TABS =================
        JTabbedPane tabbedPane = new JTabbedPane();

        busquedaPanel = new BusquedaVuelosPanel();
        reservasPanel = new ReservasPanel();

        // --- TAB 1: Buscar ---
        tabbedPane.addTab("Buscar Vuelos", busquedaPanel);

        // --- TAB 2: Comprar (UI INFORMATIVA) ---
        tabbedPane.addTab("Comprar Pasaje", crearPanelComprar(tabbedPane));


        // --- TAB 3: Reservas ---
        tabbedPane.addTab("Ver Reservas", reservasPanel);

        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);
    }

    // ================= PANEL COMPRAR =================
    private JPanel crearPanelComprar(JTabbedPane tabbedPane) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220));

        JButton btnVerPantalla = new JButton("Ver pantalla de compra");
        btnVerPantalla.setFont(new Font("Arial", Font.BOLD, 14));

        btnVerPantalla.addActionListener(e -> {
            ReservaDialog dialog = new ReservaDialog(this);
            dialog.setVisible(true);
//            tabbedPane.setSelectedIndex(0); // vuelve a Buscar
        });

        panel.add(btnVerPantalla, BorderLayout.CENTER);
        return panel;
    }

}
