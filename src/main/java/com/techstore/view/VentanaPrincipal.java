package com.techstore.view;

import com.techstore.controller.ProductoController;
import com.techstore.model.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class VentanaPrincipal extends JFrame {

    // =========================================================
    // COLORES
    // =========================================================

    private static final Color FONDO =
            new Color(7, 9, 30);

    private static final Color PANEL =
            new Color(18, 21, 52);

    private static final Color AZUL =
            new Color(0, 170, 255);

    private static final Color CYAN =
            new Color(0, 220, 255);

    private static final Color MORADO =
            new Color(145, 45, 255);

    private static final Color MORADO_OSCURO =
            new Color(55, 25, 100);

    private static final Color VERDE =
            new Color(0, 220, 120);

    private static final Color NARANJA =
            new Color(255, 140, 0);

    private static final Color ROJO =
            new Color(230, 55, 75);

    private static final Color BLANCO =
            new Color(245, 245, 255);

    private static final Color GRIS =
            new Color(175, 181, 210);

    // =========================================================
    // CONTROLADOR
    // =========================================================

    private final ProductoController productoController;

    // =========================================================
    // DATOS
    // =========================================================

    private List<Producto> productos;

    // =========================================================
    // COMPONENTES
    // =========================================================

    private JPanel contenido;

    private JTable tablaAdmin;

    private DefaultTableModel modeloAdmin;

    private JLabel lblCarrito;

    private int cantidadCarrito = 0;

    private final NumberFormat moneda =
            NumberFormat.getCurrencyInstance(
                    new Locale("es", "CO")
            );

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public VentanaPrincipal() {

        productoController =
                new ProductoController();

        productos =
                productoController.listarProductos();

        configurarVentana();

        construirInterfaz();
    }

    // =========================================================
    // CONFIGURAR VENTANA
    // =========================================================

    private void configurarVentana() {

        setTitle(
                "TechStore - Tienda Tecnológica"
        );

        setSize(
                1450,
                850
        );

        setMinimumSize(
                new Dimension(
                        1100,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }

    // =========================================================
    // CONSTRUIR INTERFAZ
    // =========================================================

    private void construirInterfaz() {

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setBackground(FONDO);

        principal.add(
                crearMenuSuperior(),
                BorderLayout.NORTH
        );

        contenido =
                new JPanel(
                        new BorderLayout()
                );

        contenido.setBackground(FONDO);

        principal.add(
                contenido,
                BorderLayout.CENTER
        );

        principal.add(
                crearPie(),
                BorderLayout.SOUTH
        );

        setContentPane(principal);

        mostrarInicio();
    }

    // =========================================================
    // MENU SUPERIOR
    // =========================================================

    private JPanel crearMenuSuperior() {

        JPanel menu =
                new JPanel(
                        new BorderLayout()
                );

        menu.setBackground(
                new Color(10, 12, 42)
        );

        menu.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        JPanel izquierda =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                0
                        )
                );

        izquierda.setOpaque(false);

        JButton inicio =
                crearBotonMenu(
                        "⌂  INICIO"
                );

        JButton productos =
                crearBotonMenu(
                        "▣  PRODUCTOS"
                );

        JButton ofertas =
                crearBotonMenu(
                        "★  OFERTAS"
                );

        JButton cliente =
                crearBotonMenu(
                        "♙  CLIENTE"
                );

        JButton administracion =
                crearBotonMenu(
                        "⚙  ADMINISTRACIÓN"
                );

        inicio.addActionListener(
                e -> mostrarInicio()
        );

        productos.addActionListener(
                e -> mostrarProductos()
        );

        ofertas.addActionListener(
                e -> mostrarOfertas()
        );

        cliente.addActionListener(
                e -> mostrarCliente()
        );

        administracion.addActionListener(
                e -> mostrarAdministracion()
        );

        izquierda.add(inicio);
        izquierda.add(productos);
        izquierda.add(ofertas);
        izquierda.add(cliente);
        izquierda.add(administracion);

        lblCarrito =
                new JLabel(
                        "🛒 CARRITO (0)"
                );

        lblCarrito.setForeground(BLANCO);

        lblCarrito.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        lblCarrito.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                MORADO,
                                2
                        ),
                        new EmptyBorder(
                                9,
                                15,
                                9,
                                15
                        )
                )
        );

        lblCarrito.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        lblCarrito.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        mostrarCarrito();
                    }
                }
        );

        JPanel derecha =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        derecha.setOpaque(false);

        derecha.add(lblCarrito);

        menu.add(
                izquierda,
                BorderLayout.WEST
        );

        menu.add(
                derecha,
                BorderLayout.EAST
        );

        return menu;
    }

    // =========================================================
    // BOTON MENU
    // =========================================================

    private JButton crearBotonMenu(
            String texto
    ) {

        JButton boton =
                new JButton(texto);

        boton.setForeground(BLANCO);

        boton.setBackground(
                new Color(
                        18,
                        20,
                        52
                )
        );

        boton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        boton.setFocusPainted(false);

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        boton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        70,
                                        75,
                                        130
                                ),
                                1
                        ),
                        new EmptyBorder(
                                9,
                                14,
                                9,
                                14
                        )
                )
        );

        boton.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        boton.setBackground(
                                MORADO_OSCURO
                        );

                        boton.setBorder(
                                BorderFactory.createLineBorder(
                                        CYAN,
                                        2
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        boton.setBackground(
                                new Color(
                                        18,
                                        20,
                                        52
                                )
                        );

                        boton.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(
                                                        70,
                                                        75,
                                                        130
                                                ),
                                                1
                                        ),
                                        new EmptyBorder(
                                                9,
                                                14,
                                                9,
                                                14
                                        )
                                )
                        );
                    }
                }
        );

        return boton;
    }

    // =========================================================
    // INICIO
    // =========================================================

    private void mostrarInicio() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(FONDO);

        panel.add(
                crearEncabezado(
                        "TECHSTORE",
                        "Tecnología para todos"
                ),
                BorderLayout.NORTH
        );

        JPanel centro =
                new JPanel();

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS
                )
        );

        centro.setBackground(FONDO);

        JLabel bienvenida =
                new JLabel(
                        "¡Bienvenido a TechStore!"
                );

        bienvenida.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        bienvenida.setForeground(BLANCO);

        bienvenida.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        JLabel cantidad =
                new JLabel(
                        productos.size()
                                + " productos disponibles"
                );

        cantidad.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cantidad.setForeground(CYAN);

        cantidad.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        centro.add(
                Box.createVerticalStrut(40)
        );

        centro.add(bienvenida);

        centro.add(
                Box.createVerticalStrut(15)
        );

        centro.add(cantidad);

        centro.add(
                Box.createVerticalStrut(40)
        );

        JPanel informacion =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                20
                        )
                );

        informacion.setOpaque(false);

        informacion.setBorder(
                new EmptyBorder(
                        20,
                        70,
                        30,
                        70
                )
        );

        informacion.add(
                crearTarjetaResumen(
                        "PRODUCTOS",
                        String.valueOf(
                                productos.size()
                        ),
                        CYAN
                )
        );

        informacion.add(
                crearTarjetaResumen(
                        "STOCK DISPONIBLE",
                        String.valueOf(
                                calcularStock()
                        ),
                        VERDE
                )
        );

        informacion.add(
                crearTarjetaResumen(
                        "CATEGORÍAS",
                        String.valueOf(
                                contarCategorias()
                        ),
                        MORADO
                )
        );

        centro.add(informacion);

        panel.add(
                centro,
                BorderLayout.CENTER
        );

        cambiarContenido(panel);
    }

    // =========================================================
    // PRODUCTOS
    // =========================================================

    private void mostrarProductos() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(FONDO);

        panel.add(
                crearEncabezado(
                        "PRODUCTOS",
                        "Catálogo TechStore"
                ),
                BorderLayout.NORTH
        );

        JPanel tarjetas =
                new JPanel(
                        new GridLayout(
                                0,
                                4,
                                18,
                                18
                        )
                );

        tarjetas.setBackground(FONDO);

        tarjetas.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        25,
                        25
                )
        );

        for (Producto producto : productos) {

            tarjetas.add(
                    crearTarjetaProducto(
                            producto
                    )
            );
        }

        JScrollPane scroll =
                new JScrollPane(
                        tarjetas
                );

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        cambiarContenido(panel);
    }

    // =========================================================
    // OFERTAS
    // =========================================================

    private void mostrarOfertas() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(FONDO);

        panel.add(
                crearEncabezado(
                        "🔥 OFERTAS",
                        "Productos con pocas unidades"
                ),
                BorderLayout.NORTH
        );

        JPanel tarjetas =
                new JPanel(
                        new GridLayout(
                                0,
                                4,
                                18,
                                18
                        )
                );

        tarjetas.setBackground(FONDO);

        tarjetas.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        25,
                        25
                )
        );

        boolean hayOfertas = false;

        for (Producto producto : productos) {

            if (producto.getStock() <= 5) {

                tarjetas.add(
                        crearTarjetaProducto(
                                producto
                        )
                );

                hayOfertas = true;
            }
        }

        if (!hayOfertas) {

            JLabel mensaje =
                    new JLabel(
                            "No hay productos en oferta actualmente."
                    );

            mensaje.setForeground(GRIS);

            mensaje.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );

            tarjetas.add(mensaje);
        }

        JScrollPane scroll =
                new JScrollPane(
                        tarjetas
                );

        scroll.setBorder(null);

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        cambiarContenido(panel);
    }

    // =========================================================
    // TARJETA PRODUCTO
    // =========================================================

    private JPanel crearTarjetaProducto(
            Producto producto
    ) {

        JPanel tarjeta =
                new JPanel();

        tarjeta.setLayout(
                new BoxLayout(
                        tarjeta,
                        BoxLayout.Y_AXIS
                )
        );

        tarjeta.setBackground(PANEL);

        Color borde =
                producto.getStock() <= 5
                        ? NARANJA
                        : MORADO;

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                borde,
                                2
                        ),
                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel codigo =
                new JLabel(
                        producto.getCodigo()
                );

        codigo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        codigo.setForeground(CYAN);

        codigo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        tarjeta.add(codigo);

        tarjeta.add(
                Box.createVerticalStrut(8)
        );

        JLabel imagen =
                crearImagen(
                        producto.getCodigo()
                );

        imagen.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        tarjeta.add(imagen);

        JLabel nombre =
                new JLabel(
                        producto.getNombre()
                );

        nombre.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        nombre.setForeground(BLANCO);

        nombre.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        tarjeta.add(
                Box.createVerticalStrut(8)
        );

        tarjeta.add(nombre);

        JLabel marca =
                new JLabel(
                        "Marca: "
                                + producto.getMarca()
                );

        marca.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        marca.setForeground(GRIS);

        tarjeta.add(marca);

        JLabel precio =
                new JLabel(
                        moneda.format(
                                producto.getPrecio()
                        )
                );

        precio.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        precio.setForeground(CYAN);

        precio.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        tarjeta.add(
                Box.createVerticalStrut(8)
        );

        tarjeta.add(precio);

        JLabel stock =
                new JLabel(
                        "Stock: "
                                + producto.getStock()
                );

        stock.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        stock.setForeground(
                producto.getStock() <= 5
                        ? NARANJA
                        : VERDE
        );

        stock.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        tarjeta.add(stock);

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                5,
                                5
                        )
                );

        botones.setOpaque(false);

        JButton ver =
                crearBoton(
                        "VER",
                        MORADO
                );

        JButton agregar =
                crearBoton(
                        "AGREGAR",
                        AZUL
                );

        ver.addActionListener(
                e -> mostrarDetalle(producto)
        );

        agregar.addActionListener(
                e -> {

                    cantidadCarrito++;

                    lblCarrito.setText(
                            "🛒 CARRITO ("
                                    + cantidadCarrito
                                    + ")"
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto agregado al carrito."
                    );
                }
        );

        botones.add(ver);

        botones.add(agregar);

        tarjeta.add(
                Box.createVerticalGlue()
        );

        tarjeta.add(botones);

        return tarjeta;
    }

    // =========================================================
    // IMAGEN
    // =========================================================

    private JLabel crearImagen(
            String codigo
    ) {

        JLabel imagen =
                new JLabel(
                        "Sin imagen",
                        SwingConstants.CENTER
                );

        imagen.setPreferredSize(
                new Dimension(
                        240,
                        160
                )
        );

        imagen.setMinimumSize(
                new Dimension(
                        240,
                        160
                )
        );

        imagen.setMaximumSize(
                new Dimension(
                        240,
                        160
                )
        );

        imagen.setBackground(
                new Color(
                        10,
                        13,
                        40
                )
        );

        imagen.setOpaque(true);

        imagen.setForeground(GRIS);

        File archivo =
                new File(
                        "src/main/resources/images/"
                                + codigo.toLowerCase()
                                + ".jpg"
                );

        if (archivo.exists()) {

            ImageIcon icono =
                    new ImageIcon(
                            archivo.getAbsolutePath()
                    );

            Image original =
                    icono.getImage();

            Image escalada =
                    original.getScaledInstance(
                            220,
                            145,
                            Image.SCALE_SMOOTH
                    );

            imagen.setIcon(
                    new ImageIcon(
                            escalada
                    )
            );

            imagen.setText("");
        }

        return imagen;
    }

    // =========================================================
    // CLIENTE
    // =========================================================

    private void mostrarCliente() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(FONDO);

        panel.add(
                crearEncabezado(
                        "CLIENTE",
                        "Registro de clientes"
                ),
                BorderLayout.NORTH
        );

        JPanel formulario =
                new JPanel();

        formulario.setLayout(
                new BoxLayout(
                        formulario,
                        BoxLayout.Y_AXIS
                )
        );

        formulario.setBackground(PANEL);

        formulario.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        JTextField nombre =
                crearCampo();

        JTextField correo =
                crearCampo();

        JTextField telefono =
                crearCampo();

        formulario.add(
                crearEtiqueta(
                        "Nombre completo"
                )
        );

        formulario.add(nombre);

        formulario.add(
                Box.createVerticalStrut(15)
        );

        formulario.add(
                crearEtiqueta(
                        "Correo electrónico"
                )
        );

        formulario.add(correo);

        formulario.add(
                Box.createVerticalStrut(15)
        );

        formulario.add(
                crearEtiqueta(
                        "Teléfono"
                )
        );

        formulario.add(telefono);

        formulario.add(
                Box.createVerticalStrut(25)
        );

        JButton registrar =
                crearBoton(
                        "REGISTRAR CLIENTE",
                        AZUL
                );

        registrar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        registrar.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Para guardar clientes en MySQL "
                                    + "conectaremos esta pantalla "
                                    + "con ClienteController."
                    );
                }
        );

        formulario.add(registrar);

        panel.add(
                formulario,
                BorderLayout.CENTER
        );

        cambiarContenido(panel);
    }

    // =========================================================
    // ADMINISTRACION
    // =========================================================

    private void mostrarAdministracion() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(FONDO);

        panel.add(
                crearEncabezado(
                        "⚙ ADMINISTRACIÓN",
                        "Gestión de productos"
                ),
                BorderLayout.NORTH
        );

        String[] columnas = {
                "Código",
                "Producto",
                "Categoría",
                "Marca",
                "Precio",
                "Stock",
                "Estado"
        };

        modeloAdmin =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int fila,
                            int columna
                    ) {

                        return false;
                    }
                };

        tablaAdmin =
                new JTable(
                        modeloAdmin
                );

        configurarTabla(
                tablaAdmin
        );

        cargarTablaAdministracion();

        JScrollPane scroll =
                new JScrollPane(
                        tablaAdmin
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        MORADO,
                        1
                )
        );

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                10
                        )
                );

        botones.setBackground(FONDO);

        JButton editar =
                crearBoton(
                        "✏ EDITAR",
                        AZUL
                );

        JButton eliminar =
                crearBoton(
                        "🗑 ELIMINAR",
                        ROJO
                );

        JButton actualizar =
                crearBoton(
                        "↻ ACTUALIZAR",
                        MORADO
                );

        editar.addActionListener(
                e -> editarProducto()
        );

        eliminar.addActionListener(
                e -> eliminarProducto()
        );

        actualizar.addActionListener(
                e -> {

                    productos =
                            productoController
                                    .listarProductos();

                    cargarTablaAdministracion();
                }
        );

        botones.add(editar);

        botones.add(eliminar);

        botones.add(actualizar);

        panel.add(
                botones,
                BorderLayout.SOUTH
        );

        cambiarContenido(panel);
    }

    // =========================================================
    // CARGAR TABLA ADMIN
    // =========================================================

    private void cargarTablaAdministracion() {

        if (modeloAdmin == null) {
            return;
        }

        modeloAdmin.setRowCount(0);

        for (Producto producto : productos) {

            modeloAdmin.addRow(
                    new Object[]{
                            producto.getCodigo(),
                            producto.getNombre(),
                            producto.getCategoria(),
                            producto.getMarca(),
                            moneda.format(
                                    producto.getPrecio()
                            ),
                            producto.getStock(),
                            producto.isEstado()
                                    ? "Disponible"
                                    : "Inactivo"
                    }
            );
        }

        DefaultTableCellRenderer izquierda =
                new DefaultTableCellRenderer();

        izquierda.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        for (int i = 0; i < 4; i++) {

            tablaAdmin
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            izquierda
                    );
        }
    }

    // =========================================================
    // EDITAR PRODUCTO
    // =========================================================

    private void editarProducto() {

        int fila =
                tablaAdmin.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String codigo =
                modeloAdmin
                        .getValueAt(
                                fila,
                                0
                        )
                        .toString();

        Producto productoSeleccionado =
                buscarProducto(codigo);

        if (productoSeleccionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró el producto."
            );

            return;
        }

        JTextField nombre =
                crearCampo();

        JTextField marca =
                crearCampo();

        JTextField precio =
                crearCampo();

        JTextField stock =
                crearCampo();

        nombre.setText(
                productoSeleccionado.getNombre()
        );

        marca.setText(
                productoSeleccionado.getMarca()
        );

        precio.setText(
                String.valueOf(
                        productoSeleccionado.getPrecio()
                )
        );

        stock.setText(
                String.valueOf(
                        productoSeleccionado.getStock()
                )
        );

        JComboBox<String> categoria =
                new JComboBox<>(
                        new String[]{
                                "Computador",
                                "Celular",
                                "Tablet",
                                "Accesorios"
                        }
                );

        categoria.setSelectedItem(
                productoSeleccionado
                        .getCategoria()
        );

        JCheckBox estado =
                new JCheckBox(
                        "Producto disponible"
                );

        estado.setSelected(
                productoSeleccionado
                        .isEstado()
        );

        JPanel formulario =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                10,
                                10
                        )
                );

        formulario.add(
                new JLabel(
                        "Nombre:"
                )
        );

        formulario.add(nombre);

        formulario.add(
                new JLabel(
                        "Categoría:"
                )
        );

        formulario.add(categoria);

        formulario.add(
                new JLabel(
                        "Marca:"
                )
        );

        formulario.add(marca);

        formulario.add(
                new JLabel(
                        "Precio:"
                )
        );

        formulario.add(precio);

        formulario.add(
                new JLabel(
                        "Stock:"
                )
        );

        formulario.add(stock);

        formulario.add(
                new JLabel(
                        "Estado:"
                )
        );

        formulario.add(estado);

        int resultado =
                JOptionPane.showConfirmDialog(
                        this,
                        formulario,
                        "Editar producto - "
                                + codigo,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (
                resultado
                        != JOptionPane.OK_OPTION
        ) {

            return;
        }

        try {

            String nuevoNombre =
                    nombre.getText().trim();

            String nuevaMarca =
                    marca.getText().trim();

            String nuevaCategoria =
                    categoria
                            .getSelectedItem()
                            .toString();

            double nuevoPrecio =
                    Double.parseDouble(
                            precio
                                    .getText()
                                    .trim()
                    );

            int nuevoStock =
                    Integer.parseInt(
                            stock
                                    .getText()
                                    .trim()
                    );

            if (
                    nuevoNombre.isEmpty()
                            ||
                    nuevaMarca.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos."
                );

                return;
            }

            if (nuevoPrecio < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El precio no puede ser negativo."
                );

                return;
            }

            if (nuevoStock < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El stock no puede ser negativo."
                );

                return;
            }

            boolean actualizado =
                    productoController
                            .actualizarProducto(
                                    codigo,
                                    nuevoNombre,
                                    nuevaCategoria,
                                    nuevaMarca,
                                    nuevoPrecio,
                                    nuevoStock,
                                    estado.isSelected()
                            );

            if (actualizado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Producto actualizado correctamente.",
                        "TechStore",
                        JOptionPane.INFORMATION_MESSAGE
                );

                productos =
                        productoController
                                .listarProductos();

                cargarTablaAdministracion();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el producto.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Precio y stock deben ser valores numéricos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ELIMINAR PRODUCTO
    // =========================================================

    private void eliminarProducto() {

        int fila =
                tablaAdmin.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String codigo =
                modeloAdmin
                        .getValueAt(
                                fila,
                                0
                        )
                        .toString();

        String nombre =
                modeloAdmin
                        .getValueAt(
                                fila,
                                1
                        )
                        .toString();

        int confirmacion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar?\n\n"
                                + codigo
                                + " - "
                                + nombre,
                        "Eliminar producto",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (
                confirmacion
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }

        boolean eliminado =
                productoController
                        .eliminarProducto(
                                codigo
                        );

        if (eliminado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado correctamente.",
                    "TechStore",
                    JOptionPane.INFORMATION_MESSAGE
            );

            productos =
                    productoController
                            .listarProductos();

            cargarTablaAdministracion();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BUSCAR PRODUCTO
    // =========================================================

    private Producto buscarProducto(
            String codigo
    ) {

        for (Producto producto : productos) {

            if (
                    producto
                            .getCodigo()
                            .equalsIgnoreCase(
                                    codigo
                            )
            ) {

                return producto;
            }
        }

        return null;
    }

    // =========================================================
    // CARRITO
    // =========================================================

    private void mostrarCarrito() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(FONDO);

        panel.add(
                crearEncabezado(
                        "🛒 CARRITO",
                        "Productos seleccionados"
                ),
                BorderLayout.NORTH
        );

        JLabel mensaje =
                new JLabel(
                        "Productos en el carrito: "
                                + cantidadCarrito,
                        SwingConstants.CENTER
                );

        mensaje.setForeground(BLANCO);

        mensaje.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        panel.add(
                mensaje,
                BorderLayout.CENTER
        );

        cambiarContenido(panel);
    }

    // =========================================================
    // DETALLE
    // =========================================================

    private void mostrarDetalle(
            Producto producto
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Código: "
                        + producto.getCodigo()
                        + "\n\nProducto: "
                        + producto.getNombre()
                        + "\n\nCategoría: "
                        + producto.getCategoria()
                        + "\n\nMarca: "
                        + producto.getMarca()
                        + "\n\nPrecio: "
                        + moneda.format(
                        producto.getPrecio()
                )
                        + "\n\nStock: "
                        + producto.getStock(),
                "Detalle del producto",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // ENCABEZADO DEGRADADO
    // =========================================================

    private JPanel crearEncabezado(
            String titulo,
            String subtitulo
    ) {

        JPanel encabezado =
                new JPanel() {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        GradientPaint degradado =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                10,
                                                65,
                                                140
                                        ),
                                        getWidth(),
                                        0,
                                        new Color(
                                                120,
                                                20,
                                                145
                                        )
                                );

                        g2.setPaint(
                                degradado
                        );

                        g2.fillRect(
                                0,
                                0,
                                getWidth(),
                                getHeight()
                        );

                        g2.dispose();
                    }
                };

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        encabezado.setBorder(
                new EmptyBorder(
                        25,
                        20,
                        25,
                        20
                )
        );

        JLabel tituloLabel =
                new JLabel(
                        titulo
                );

        tituloLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        tituloLabel.setForeground(BLANCO);

        tituloLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        36
                )
        );

        JLabel subtituloLabel =
                new JLabel(
                        subtitulo
                );

        subtituloLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtituloLabel.setForeground(
                new Color(
                        220,
                        220,
                        255
                )
        );

        subtituloLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17
                )
        );

        encabezado.add(
                tituloLabel
        );

        encabezado.add(
                Box.createVerticalStrut(5)
        );

        encabezado.add(
                subtituloLabel
        );

        return encabezado;
    }

    // =========================================================
    // TABLA
    // =========================================================

    private void configurarTabla(
            JTable tabla
    ) {

        tabla.setBackground(PANEL);

        tabla.setForeground(BLANCO);

        tabla.setGridColor(
                new Color(
                        60,
                        65,
                        110
                )
        );

        tabla.setRowHeight(38);

        tabla.setSelectionBackground(
                new Color(
                        75,
                        35,
                        130
                )
        );

        tabla.setSelectionForeground(
                Color.WHITE
        );

        tabla.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        tabla.getTableHeader()
                .setBackground(
                        new Color(
                                28,
                                30,
                                75
                        )
                );

        tabla.getTableHeader()
                .setForeground(CYAN);

        tabla.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        tabla.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                42
                        )
                );
    }

    // =========================================================
    // TARJETA RESUMEN
    // =========================================================

    private JPanel crearTarjetaResumen(
            String titulo,
            String valor,
            Color color
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(PANEL);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                color,
                                2
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel t =
                new JLabel(
                        titulo
                );

        t.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        t.setForeground(GRIS);

        JLabel v =
                new JLabel(
                        valor
                );

        v.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        v.setForeground(color);

        v.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        panel.add(t);

        panel.add(
                Box.createVerticalStrut(8)
        );

        panel.add(v);

        return panel;
    }

    // =========================================================
    // BOTON
    // =========================================================

    private JButton crearBoton(
            String texto,
            Color color
    ) {

        JButton boton =
                new JButton(texto);

        boton.setBackground(color);

        boton.setForeground(Color.WHITE);

        boton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        boton.setFocusPainted(false);

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        boton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.WHITE,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                13,
                                8,
                                13
                        )
                )
        );

        return boton;
    }

    // =========================================================
    // CAMPO
    // =========================================================

    private JTextField crearCampo() {

        JTextField campo =
                new JTextField();

        campo.setForeground(BLANCO);

        campo.setBackground(
                new Color(
                        8,
                        10,
                        35
                )
        );

        campo.setCaretColor(CYAN);

        campo.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                75,
                                80,
                                145
                        )
                )
        );

        campo.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        return campo;
    }

    // =========================================================
    // ETIQUETA
    // =========================================================

    private JLabel crearEtiqueta(
            String texto
    ) {

        JLabel etiqueta =
                new JLabel(
                        texto
                );

        etiqueta.setForeground(BLANCO);

        etiqueta.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        return etiqueta;
    }

    // =========================================================
    // PIE
    // =========================================================

    private JPanel crearPie() {

        JPanel pie =
                new JPanel(
                        new BorderLayout()
                );

        pie.setBackground(
                new Color(
                        10,
                        12,
                        42
                )
        );

        pie.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        JLabel izquierda =
                new JLabel(
                        "⚡ Envío rápido | ✓ Productos originales"
                );

        izquierda.setForeground(GRIS);

        JLabel centro =
                new JLabel(
                        "© 2026 TechStore",
                        SwingConstants.CENTER
                );

        centro.setForeground(BLANCO);

        JLabel derecha =
                new JLabel(
                        "🔒 Pagos seguros"
                );

        derecha.setForeground(GRIS);

        pie.add(
                izquierda,
                BorderLayout.WEST
        );

        pie.add(
                centro,
                BorderLayout.CENTER
        );

        pie.add(
                derecha,
                BorderLayout.EAST
        );

        return pie;
    }

    // =========================================================
    // CAMBIAR CONTENIDO
    // =========================================================

    private void cambiarContenido(
            JPanel panel
    ) {

        contenido.removeAll();

        contenido.add(
                panel,
                BorderLayout.CENTER
        );

        contenido.revalidate();

        contenido.repaint();
    }

    // =========================================================
    // STOCK
    // =========================================================

    private int calcularStock() {

        int total = 0;

        for (Producto producto : productos) {

            total += producto.getStock();
        }

        return total;
    }

    // =========================================================
    // CATEGORIAS
    // =========================================================

    private int contarCategorias() {

        java.util.Set<String> categorias =
                new java.util.HashSet<>();

        for (Producto producto : productos) {

            categorias.add(
                    producto.getCategoria()
            );
        }

        return categorias.size();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    VentanaPrincipal ventana =
                            new VentanaPrincipal();

                    ventana.setVisible(true);
                }
        );
    }
}