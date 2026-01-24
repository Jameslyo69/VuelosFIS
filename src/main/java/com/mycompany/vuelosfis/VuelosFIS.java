/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vuelosfis;

import com.mycompany.vuelosfis.vista.LoginFrame;

import javax.swing.*;

public class VuelosFIS {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

