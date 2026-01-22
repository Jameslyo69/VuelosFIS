package com.mycompany.vuelosfis.Controlador;

public class TestAuthController {
    public static void main(String[] args) {

        AuthController auth = new AuthController();

        try {
            auth.login("admin", "1234");
            System.out.println("Login correcto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            auth.registrar("maria", "abcd", "abcd", "Maria Lopez", "maria@mail.com");
            System.out.println("Registro correcto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
