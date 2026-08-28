package com.techstore.dao;

import com.techstore.database.ConexionBD;
import com.techstore.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean registrar(Producto producto) {

        String sql = """
                INSERT INTO productos
                (codigo, nombre, categoria, marca, precio, stock, estado)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, producto.getCodigo());
            sentencia.setString(2, producto.getNombre());
            sentencia.setString(3, producto.getCategoria());
            sentencia.setString(4, producto.getMarca());
            sentencia.setDouble(5, producto.getPrecio());
            sentencia.setInt(6, producto.getStock());
            sentencia.setBoolean(7, producto.isEstado());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al registrar producto:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM productos ORDER BY id";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Producto producto = new Producto();

                producto.setId(resultado.getInt("id"));
                producto.setCodigo(resultado.getString("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setCategoria(resultado.getString("categoria"));
                producto.setMarca(resultado.getString("marca"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setStock(resultado.getInt("stock"));
                producto.setEstado(resultado.getBoolean("estado"));

                productos.add(producto);
            }

        } catch (SQLException e) {

            System.out.println("Error al consultar productos:");
            System.out.println(e.getMessage());
        }

        return productos;
    }

    public boolean actualizar(Producto producto) {

        String sql = """
                UPDATE productos
                SET nombre = ?,
                    categoria = ?,
                    marca = ?,
                    precio = ?,
                    stock = ?,
                    estado = ?
                WHERE codigo = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, producto.getNombre());
            sentencia.setString(2, producto.getCategoria());
            sentencia.setString(3, producto.getMarca());
            sentencia.setDouble(4, producto.getPrecio());
            sentencia.setInt(5, producto.getStock());
            sentencia.setBoolean(6, producto.isEstado());
            sentencia.setString(7, producto.getCodigo());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar producto:");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean eliminar(String codigo) {

        String sql = "DELETE FROM productos WHERE codigo = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, codigo);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar producto:");
            System.out.println(e.getMessage());

            return false;
        }
    }
}