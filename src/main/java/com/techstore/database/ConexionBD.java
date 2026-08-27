package com.techstore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/techstore";

    private static final String USUARIO = "root";

    private static final String CONTRASENA = "1996bello";

    public static Connection conectar() {

        try {
            Connection conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexión exitosa a TechStore.");

            return conexion;

        } catch (SQLException e) {

            System.out.println("Error al conectar con MySQL.");
            System.out.println(e.getMessage());

            return null;
        }
    }
}