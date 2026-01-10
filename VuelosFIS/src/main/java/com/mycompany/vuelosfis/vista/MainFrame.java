/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.vista;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JButton btnBuscar = new JButton("Buscar vuelos");
    private final JButton btnReservas = new JButton("Ver reservas");

    private final JPanel contentPanel = new JPanel(new BorderLayout());

    public MainFrame() {
        setTitle("VuelosFIS - Sistema de Pasajes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(btnBuscar);
        top.add(btnReservas);

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // pantalla inicial
        setContent(new JLabel("Bienvenido a VuelosFIS", SwingConstants.CENTER));
    }

    public void setContent(Component c) {
        contentPanel.removeAll();
        contentPanel.add(c, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnReservas() { return btnReservas; }
}
