package com.techstore.dao;

import com.techstore.database.ConexionBD;
import com.techstore.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean registrar(Cliente cliente) {

        String sql = """
                INSERT INTO clientes
                (nombre, correo, telefono)
                VALUES (?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getCorreo());
            sentencia.setString(3, cliente.getTelefono());

            return sentencia.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Error al registrar cliente:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes ORDER BY id";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(resultado.getInt("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setTelefono(resultado.getString("telefono"));

                clientes.add(cliente);
            }

        } catch (Exception e) {

            System.out.println("Error al consultar clientes:");
            System.out.println(e.getMessage());
        }

        return clientes;
    }

    public boolean actualizar(Cliente cliente) {

        String sql = """
                UPDATE clientes
                SET nombre = ?,
                    correo = ?,
                    telefono = ?
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getCorreo());
            sentencia.setString(3, cliente.getTelefono());
            sentencia.setInt(4, cliente.getId());

            return sentencia.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Error al actualizar cliente:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            return sentencia.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Error al eliminar cliente:");
            System.out.println(e.getMessage());

            return false;
        }
    }
}