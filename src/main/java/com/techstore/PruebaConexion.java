package com.techstore;

import com.techstore.database.ConexionBD;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {
            System.out.println("TechStore está conectado a MySQL.");
        } else {
            System.out.println("No se pudo conectar a MySQL.");
        }
    }
}