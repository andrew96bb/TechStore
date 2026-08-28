package com.techstore.controller;

import com.techstore.dao.ProductoDAO;
import com.techstore.model.Producto;

import java.util.List;

public class ProductoController {

    private final ProductoDAO productoDAO;

    public ProductoController() {
        productoDAO = new ProductoDAO();
    }

    public boolean registrarProducto(
            String codigo,
            String nombre,
            String categoria,
            String marca,
            double precio,
            int stock,
            boolean estado) {

        Producto producto = new Producto(
                codigo,
                nombre,
                categoria,
                marca,
                precio,
                stock,
                estado
        );

        return productoDAO.registrar(producto);
    }

    public List<Producto> listarProductos() {

        return productoDAO.listar();
    }

    public boolean actualizarProducto(
            String codigo,
            String nombre,
            String categoria,
            String marca,
            double precio,
            int stock,
            boolean estado) {

        Producto producto = new Producto(
                codigo,
                nombre,
                categoria,
                marca,
                precio,
                stock,
                estado
        );

        return productoDAO.actualizar(producto);
    }

    public boolean eliminarProducto(String codigo) {

        return productoDAO.eliminar(codigo);
    }
}