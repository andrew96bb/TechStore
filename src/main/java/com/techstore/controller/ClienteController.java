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
            String documento,
            String nombre,
            String correo,
            String telefono,
            String fechaNacimiento,
            String sexo,
            String departamento,
            String ciudad,
            String direccion
    ) {

        Cliente cliente =
                new Cliente(
                        documento,
                        nombre,
                        correo,
                        telefono,
                        fechaNacimiento,
                        sexo,
                        departamento,
                        ciudad,
                        direccion
                );

        return clienteDAO.registrar(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public boolean actualizarCliente(
            int id,
            String documento,
            String nombre,
            String correo,
            String telefono,
            String fechaNacimiento,
            String sexo,
            String departamento,
            String ciudad,
            String direccion
    ) {

        Cliente cliente =
                new Cliente(
                        documento,
                        nombre,
                        correo,
                        telefono,
                        fechaNacimiento,
                        sexo,
                        departamento,
                        ciudad,
                        direccion
                );

        cliente.setId(id);

        return clienteDAO.actualizar(cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminar(id);
    }
}
