package com.mycompany.vuelosfis.Controlador;

import com.mycompany.vuelosfis.csv.UsuarioRepositoryArchivo;

public class AuthController {
    private final UsuarioRepositoryArchivo usuarioRepo;

    public AuthController() {
        this.usuarioRepo = new UsuarioRepositoryArchivo();
    }

    // =========================
    // LOGIN
    // =========================
    public boolean login(String usuario, String password) {

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        boolean valido = usuarioRepo.validar(usuario, password);

        if (!valido) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        return true;
    }

    // =========================
    // REGISTRO
    // =========================
    public void registrar(String usuario,
                          String password,
                          String confirmarPassword,
                          String nombre,
                          String correo) {

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (usuarioRepo.existeUsuario(usuario)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres");
        }

        if (!password.equals(confirmarPassword)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        usuarioRepo.guardar(usuario, password, nombre, correo);
    }
}
