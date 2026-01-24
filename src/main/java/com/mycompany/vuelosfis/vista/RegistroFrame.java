package com.mycompany.vuelosfis.vista;

import com.mycompany.vuelosfis.Controlador.AuthController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistroFrame extends JFrame {

    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmar;
    private final AuthController authController;

    public RegistroFrame() {
        authController = new AuthController();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Registro de Nuevo Usuario");
        setSize(600, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(245, 242, 230));
        setContentPane(panelPrincipal);

        // ================= HEADER =================
        JPanel panelHeader = new JPanel(new GridLayout(2, 1));
        panelHeader.setBackground(new Color(0, 0, 128));
        panelHeader.setPreferredSize(new Dimension(100, 90));

        JLabel lblTitulo = new JLabel("CREAR CUENTA NUEVA", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblSub = new JLabel("Complete todos los campos para registrarse", SwingConstants.CENTER);
        lblSub.setForeground(Color.WHITE);

        panelHeader.add(lblTitulo);
        panelHeader.add(lblSub);

        panelPrincipal.add(panelHeader, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(6, 1, 10, 0));
        panelForm.setBorder(new EmptyBorder(15, 10, 15, 10));
        panelForm.setBackground(panelPrincipal.getBackground());

        panelForm.add(crearCampo("Nombre Completo:", txtNombre = new JTextField()));
        panelForm.add(crearCampo("Correo Electrónico:", txtCorreo = new JTextField()));
        panelForm.add(crearCampo("Usuario:", txtUsuario = new JTextField()));

        JPanel panelPass = new JPanel(new GridLayout(1, 2, 10, 0));
        panelPass.setBackground(panelPrincipal.getBackground());
        panelPass.add(crearCampo("Contraseña:", txtPassword = new JPasswordField()));
        panelPass.add(crearCampo("Confirmar Contraseña:", txtConfirmar = new JPasswordField()));

        panelForm.add(panelPass);

        panelPrincipal.add(panelForm, BorderLayout.CENTER);

        // ================= REQUISITOS =================
        JPanel panelReq = new JPanel(new BorderLayout());
        panelReq.setBackground(new Color(255, 255, 200));
        panelReq.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextArea txtReq = new JTextArea(
                "• Nombre: mínimo 3 caracteres\n" +
                        "• Usuario: mínimo 4 caracteres\n" +
                        "• Contraseña: mínimo 4 caracteres\n" +
                        "• Las contraseñas deben coincidir"
        );
        txtReq.setEditable(false);
        txtReq.setBackground(panelReq.getBackground());
        panelReq.add(txtReq);

        panelPrincipal.add(panelReq, BorderLayout.SOUTH);

        // ================= BOTONES =================
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
        panelBotones.setBackground(panelPrincipal.getBackground());

        JButton btnRegistrar = new JButton("Registrarse");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        panelForm.add(panelBotones);

        // ================= EVENTOS =================
        btnRegistrar.addActionListener(e -> registrar());
        btnCancelar.addActionListener(e -> volverLogin());
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

    private void registrar() {
        try {
            authController.registrar(
                    txtUsuario.getText(),
                    new String(txtPassword.getPassword()),
                    new String(txtConfirmar.getPassword()),
                    txtNombre.getText(),
                    txtCorreo.getText()
            );

            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
            volverLogin();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverLogin() {
        dispose();
        new LoginFrame().setVisible(true);

    }
}
