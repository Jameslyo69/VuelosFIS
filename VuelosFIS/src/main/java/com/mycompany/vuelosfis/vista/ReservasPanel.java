package com.mycompany.vuelosfis.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ReservasPanel extends JPanel {

    private static final Color COLOR_CREMA = new Color(245, 245, 220);

    public ReservasPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(COLOR_CREMA);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ===== CONTENEDOR CON BORDE =====
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(COLOR_CREMA);
        contenedor.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Listado de Reservas",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12),
                new Color(0, 0, 150)
        ));

        // ===== PANEL BLANCO INTERNO =====
        JPanel panelMensaje = new JPanel();
        panelMensaje.setBackground(Color.WHITE);
        panelMensaje.setLayout(new BoxLayout(panelMensaje, BoxLayout.Y_AXIS));
        panelMensaje.setBorder(new EmptyBorder(40, 10, 40, 10));

        JLabel lblTitulo = new JLabel("No hay reservas registradas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Las reservas realizadas aparecerán aquí");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelMensaje.add(lblTitulo);
        panelMensaje.add(Box.createVerticalStrut(10));
        panelMensaje.add(lblSub);

        contenedor.add(panelMensaje, BorderLayout.CENTER);

        add(contenedor, BorderLayout.CENTER);
    }
}
