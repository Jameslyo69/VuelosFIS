/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.vista;

import javax.swing.*;
import java.awt.*;

public class ReservaDialog extends JDialog {

    private final JTextField txtCedula = new JTextField(12);
    private final JTextField txtNombre = new JTextField(20);
    private final JCheckBox chkComprar = new JCheckBox("Comprar (si no, solo reservar)");
    private final JButton btnConfirmar = new JButton("Confirmar");
    private boolean confirmado = false;

    public ReservaDialog(Window owner) {
        super(owner, "Datos del pasajero", ModalityType.APPLICATION_MODAL);
        setSize(420, 220);
        setLocationRelativeTo(owner);

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        form.add(new JLabel("Cédula:"));
        form.add(txtCedula);
        form.add(new JLabel("Nombre completo:"));
        form.add(txtNombre);
        form.add(new JLabel(""));
        form.add(chkComprar);
        form.add(new JLabel(""));
        form.add(btnConfirmar);

        add(form);

        btnConfirmar.addActionListener(e -> {
            confirmado = true;
            setVisible(false);
        });
    }

    public boolean isConfirmado() { return confirmado; }
    public String getCedula() { return txtCedula.getText(); }
    public String getNombre() { return txtNombre.getText(); }
    public boolean isComprar() { return chkComprar.isSelected(); }
}
