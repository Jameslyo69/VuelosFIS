package com.mycompany.vuelosfis.csv;

import com.mycompany.vuelosfis.Util.CSVUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsuarioRepositoryArchivo {

    private final Path rutaArchivo;

    public UsuarioRepositoryArchivo() {
        this.rutaArchivo = Paths.get("data", "usuarios.csv");
    }

    // =========================
    // VALIDAR LOGIN
    // =========================
    public boolean validar(String usuario, String password) {

        if (!Files.exists(rutaArchivo)) {
            System.out.println("No existe usuarios.csv");
            return false;
        }

        try (BufferedReader br = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {

                // saltar encabezado
                if (primera) {
                    primera = false;
                    continue;
                }

                if (linea.trim().isEmpty()) continue;

                String[] p = CSVUtil.splitCSV(linea);
                if (p.length < 2) continue;

                String userCSV = p[0];
                String passCSV = p[1];

                if (userCSV.equals(usuario) && passCSV.equals(password)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo usuarios.csv: " + e.getMessage());
        }

        return false;
    }

    // =========================
    // GUARDAR USUARIO
    // =========================
    public void guardar(String usuario, String password, String nombre, String correo) {

        try {
            // Si no existe el archivo, lo crea con encabezado
            if (!Files.exists(rutaArchivo)) {
                Files.createDirectories(rutaArchivo.getParent());
                try (BufferedWriter bw = Files.newBufferedWriter(
                        rutaArchivo, StandardOpenOption.CREATE)) {

                    bw.write("usuario,password,nombre,correo");
                    bw.newLine();
                }
            }

            // Guardar usuario
            try (BufferedWriter bw = Files.newBufferedWriter(
                    rutaArchivo, StandardOpenOption.APPEND)) {

                String linea = String.join(",",
                        CSVUtil.safe(usuario),
                        CSVUtil.safe(password),
                        CSVUtil.safe(nombre),
                        CSVUtil.safe(correo)
                );

                bw.write(linea);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error guardando usuario: " + e.getMessage());
        }
    }

    // =========================
    // VALIDAR SI USUARIO EXISTE
    // =========================
    public boolean existeUsuario(String usuario) {

        if (!Files.exists(rutaArchivo)) return false;

        try (BufferedReader br = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {

                if (primera) {
                    primera = false;
                    continue;
                }

                String[] p = CSVUtil.splitCSV(linea);
                if (p.length < 1) continue;

                if (p[0].equals(usuario)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo usuarios.csv: " + e.getMessage());
        }

        return false;
    }
}
