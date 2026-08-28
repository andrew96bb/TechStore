package com.techstore.controller;

import com.techstore.dao.ClienteDAO;
import com.techstore.model.Cliente;

import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }

    public boolean registrarCliente(
            String nombre,
            String correo,
            String telefono) {

        Cliente cliente =
                new Cliente(nombre, correo, telefono);

        return clienteDAO.registrar(cliente);
    }

    public List<Cliente> listarClientes() {

        return clienteDAO.listar();
    }

    public boolean actualizarCliente(
            int id,
            String nombre,
            String correo,
            String telefono) {

        Cliente cliente =
                new Cliente(nombre, correo, telefono);

        cliente.setId(id);

        return clienteDAO.actualizar(cliente);
    }

    public boolean eliminarCliente(int id) {

        return clienteDAO.eliminar(id);
    }
}