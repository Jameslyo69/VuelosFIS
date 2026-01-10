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

        // Por ahora solo una vista inicial vacía
        add(new JLabel("Bienvenido a VuelosFIS", SwingConstants.CENTER), BorderLayout.CENTER);
    }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnReservas() { return btnReservas; }
}
