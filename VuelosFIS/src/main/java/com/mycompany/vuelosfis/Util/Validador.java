/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Util;

public class Validador {

    public static boolean noVacio(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean fechaBasicaYYYYMMDD(String s) {
        // Validación súper simple (para principiantes)
        return noVacio(s) && s.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
