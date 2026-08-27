package com.techstore.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.techstore.controller.ProductoController;
import com.techstore.model.Producto;

public class VentanaPrincipal extends JFrame {

    // Campos del formulario
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtPrecio;
    private JTextField txtStock;

    // Componentes de selección
    private JComboBox<String> cmbCategoria;
    private JCheckBox chkEstado;

    // Botones
    private JButton btnRegistrar;
    private JButton btnConsultar;

    // Controlador
    private ProductoController productoController;

    // Constructor
    public VentanaPrincipal() {

        productoController = new ProductoController();

        setTitle("TechStore - Gestión de Productos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
    }

    // Crear interfaz
    private void crearInterfaz() {

        JPanel panel = new JPanel();

        panel.setLayout(
                new GridLayout(9, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        // Código
        panel.add(new JLabel("Código:"));

        txtCodigo = new JTextField();

        panel.add(txtCodigo);

        // Nombre
        panel.add(new JLabel("Nombre:"));

        txtNombre = new JTextField();

        panel.add(txtNombre);

        // Categoría
        panel.add(new JLabel("Categoría:"));

        String[] categorias = {
                "Computador",
                "Celular",
                "Tablet",
                "Accesorios"
        };

        cmbCategoria = new JComboBox<>(categorias);

        panel.add(cmbCategoria);

        // Marca
        panel.add(new JLabel("Marca:"));

        txtMarca = new JTextField();

        panel.add(txtMarca);

        // Precio
        panel.add(new JLabel("Precio:"));

        txtPrecio = new JTextField();

        panel.add(txtPrecio);

        // Stock
        panel.add(new JLabel("Stock:"));

        txtStock = new JTextField();

        panel.add(txtStock);

        // Estado
        panel.add(new JLabel("Estado:"));

        chkEstado = new JCheckBox("Disponible");

        chkEstado.setSelected(true);

        panel.add(chkEstado);

        // ========================================
        // BOTÓN REGISTRAR
        // ========================================

        btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(e -> {

            System.out.println(
                    "Botón Registrar presionado."
            );

            registrarProducto();

        });

        panel.add(btnRegistrar);

        // ========================================
        // BOTÓN CONSULTAR
        // ========================================

        btnConsultar = new JButton("Consultar");

        btnConsultar.addActionListener(e ->
                consultarProductos()
        );

        panel.add(btnConsultar);

        // Agregar panel a la ventana
        add(panel);
    }

    // ========================================
    // REGISTRAR PRODUCTO
    // ========================================

    private void registrarProducto() {

        try {

            String codigo =
                    txtCodigo.getText().trim();

            String nombre =
                    txtNombre.getText().trim();

            String categoria =
                    cmbCategoria.getSelectedItem().toString();

            String marca =
                    txtMarca.getText().trim();

            double precio =
                    Double.parseDouble(
                            txtPrecio.getText().trim()
                    );

            int stock =
                    Integer.parseInt(
                            txtStock.getText().trim()
                    );

            boolean estado =
                    chkEstado.isSelected();

            // Validar campos
            if (codigo.isEmpty()
                    || nombre.isEmpty()
                    || marca.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos."
                );

                return;
            }

            // Enviar información al controlador
            boolean registrado =
                    productoController.registrarProducto(
                            codigo,
                            nombre,
                            categoria,
                            marca,
                            precio,
                            stock,
                            estado
                    );

            if (registrado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto registrado correctamente."
                );

                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el producto."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Precio y stock deben ser números."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ocurrió un error: "
                            + e.getMessage()
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // ========================================
    // CONSULTAR PRODUCTOS
    // ========================================

    private void consultarProductos() {

        List<Producto> productos =
                productoController.listarProductos();

        if (productos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay productos registrados."
            );

            return;
        }

        String[] columnas = {
                "ID",
                "Código",
                "Nombre",
                "Categoría",
                "Marca",
                "Precio",
                "Stock",
                "Estado"
        };

        Object[][] datos =
                new Object[productos.size()][8];

        for (int i = 0; i < productos.size(); i++) {

            Producto producto = productos.get(i);

            datos[i][0] = producto.getId();
            datos[i][1] = producto.getCodigo();
            datos[i][2] = producto.getNombre();
            datos[i][3] = producto.getCategoria();
            datos[i][4] = producto.getMarca();
            datos[i][5] = producto.getPrecio();
            datos[i][6] = producto.getStock();

            datos[i][7] =
                    producto.isEstado()
                            ? "Disponible"
                            : "No disponible";
        }

        JTable tabla =
                new JTable(datos, columnas);

        JScrollPane scrollPane =
                new JScrollPane(tabla);

        JFrame ventana =
                new JFrame("Productos registrados");

        ventana.setSize(900, 400);

        ventana.setLocationRelativeTo(this);

        ventana.add(scrollPane);

        ventana.setVisible(true);
    }

    // ========================================
    // LIMPIAR FORMULARIO
    // ========================================

    private void limpiarCampos() {

        txtCodigo.setText("");

        txtNombre.setText("");

        txtMarca.setText("");

        txtPrecio.setText("");

        txtStock.setText("");

        cmbCategoria.setSelectedIndex(0);

        chkEstado.setSelected(true);
    }

    // ========================================
    // MÉTODO PRINCIPAL
    // ========================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana =
                    new VentanaPrincipal();

            ventana.setVisible(true);
        });
    }
}