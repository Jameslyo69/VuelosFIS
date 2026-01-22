package com.mycompany.vuelosfis.vista;

import com.mycompany.vuelosfis.Controlador.AuthController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private final AuthController authController;

    public LoginFrame() {
        authController = new AuthController();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Vuelos - Iniciar Sesión");
        setSize(520, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 242, 230));
        setContentPane(panelPrincipal);

        // =========================
        // HEADER AZUL
        // =========================
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(0, 0, 128));
        panelHeader.setPreferredSize(new Dimension(100, 90));
        panelHeader.setLayout(new GridLayout(2, 1));

        JLabel lblTitulo = new JLabel("SISTEMA DE VUELOS", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblSubtitulo = new JLabel("Bienvenido al sistema", SwingConstants.CENTER);
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));

        panelHeader.add(lblTitulo);
        panelHeader.add(lblSubtitulo);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // =========================
        // FORMULARIO
        // =========================
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(4, 1, 10, 10));
        panelForm.setBorder(new EmptyBorder(20, 10, 20, 10));
        panelForm.setBackground(panelPrincipal.getBackground());

        panelForm.add(crearCampo("Usuario:", txtUsuario = new JTextField()));
        panelForm.add(crearCampo("Contraseña:", txtPassword = new JPasswordField()));

        panelPrincipal.add(panelForm, BorderLayout.CENTER);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBackground(panelPrincipal.getBackground());

        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegistro = new JButton("Registrarse");

        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistro);

        panelForm.add(panelBotones);

        // =========================
        // PANEL INFO
        // =========================
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(new Color(255, 255, 200));
        panelInfo.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblInfo = new JLabel("💡 Información: Si es tu primera vez, haz clic en \"Registrarse\".");
        panelInfo.add(lblInfo);

        panelPrincipal.add(panelInfo, BorderLayout.SOUTH);

        // =========================
        // EVENTOS
        // =========================
        btnLogin.addActionListener(e -> login());
        btnRegistro.addActionListener(e -> abrirRegistro());
    }

    private JPanel crearCampo(String texto, JComponent campo) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(getContentPane().getBackground());

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        campo.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }

    private void login() {
        try {
            String usuario = txtUsuario.getText();
            String password = new String(txtPassword.getPassword());

            authController.login(usuario, password);

            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);
            dispose();
            new MainFrame().setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        dispose();
        new RegistroFrame().setVisible(true);
    }
}
