package com.techstore.view;

import com.techstore.controller.ProductoController;
import com.techstore.dao.ProductoDAO;
import com.techstore.controller.ClienteController;
import com.techstore.model.Producto;
import com.techstore.model.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaPrincipal extends JFrame {

    // =========================================================
    // COLORES
    // =========================================================

    private static final Color FONDO =
            new Color(7, 10, 30);

    private static final Color PANEL =
            new Color(18, 21, 52);

    private static final Color AZUL =
            new Color(0, 210, 255);

    private static final Color AZUL_BOTON =
            new Color(20, 120, 235);

    private static final Color MORADO =
            new Color(135, 55, 220);

    private static final Color ROSA =
            new Color(225, 65, 255);

    private static final Color VERDE =
            new Color(0, 220, 100);

    private static final Color NARANJA =
            new Color(255, 145, 20);

    private static final Color ROJO =
            new Color(245, 60, 80);

    private static final Color BLANCO =
            new Color(245, 247, 255);

    private static final Color GRIS =
            new Color(185, 190, 215);

    // =========================================================
    // CONTROLADOR
    // =========================================================

    private final ProductoController controller;
    private final ClienteController clienteController;

    // =========================================================
    // LISTA DE PRODUCTOS
    // =========================================================

    private List<Producto> productos =
            new ArrayList<>();

    // =========================================================
    // COMPONENTES
    // =========================================================

    private JPanel panelProductos;

    private JTextField txtBuscar;

    private JLabel lblMostrando;

    private JButton lblCarrito;

    private JCheckBox chkDisponibles;

    private JComboBox<String> comboOrdenar;

    private JRadioButton radioTodos;

    private JRadioButton radioComputadores;

    private JRadioButton radioCelulares;

    private JRadioButton radioTablets;

    private JRadioButton radioAccesorios;

    // =========================================================
    // CARRITO
    // =========================================================

    private final Map<String, Integer> carrito =
            new HashMap<>();

    // Indicadores de cantidad mostrados en las tarjetas.
    private final Map<String, JLabel> indicadoresCantidad =
            new HashMap<>();

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public VentanaPrincipal() {

        controller =
                new ProductoController();

        clienteController =
                new ClienteController();

        configurarVentana();

        crearInterfaz();

        cargarProductos();
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
                900
        );

        setMinimumSize(
                new Dimension(
                        1150,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }

    // =========================================================
    // CREAR INTERFAZ
    // =========================================================

    private void crearInterfaz() {

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setBackground(
                FONDO
        );

        principal.add(
                crearMenu(),
                BorderLayout.NORTH
        );

        principal.add(
                crearContenido(),
                BorderLayout.CENTER
        );

        principal.add(
                crearPie(),
                BorderLayout.SOUTH
        );

        setContentPane(
                principal
        );
    }

    // =========================================================
    // MENU SUPERIOR
    // =========================================================

    private JPanel crearMenu() {

        JPanel menu =
                new JPanel(
                        new BorderLayout()
                ) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int ancho =
                                getWidth();

                        int alto =
                                getHeight();

                        // Fondo premium oscuro con degradado
                        GradientPaint gradiente =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                6,
                                                10,
                                                35
                                        ),
                                        ancho,
                                        0,
                                        new Color(
                                                25,
                                                8,
                                                48
                                        )
                                );

                        g2.setPaint(
                                gradiente
                        );

                        g2.fillRect(
                                0,
                                0,
                                ancho,
                                alto
                        );

                        // Circuitos laterales - izquierda
                        g2.setStroke(
                                new BasicStroke(
                                        1.2f
                                )
                        );

                        g2.setColor(
                                new Color(
                                        0,
                                        210,
                                        255,
                                        100
                                )
                        );

                        int y =
                                alto / 2;

                        g2.drawLine(
                                0,
                                y - 12,
                                45,
                                y - 12
                        );

                        g2.drawLine(
                                15,
                                y + 12,
                                60,
                                y + 12
                        );

                        g2.drawLine(
                                45,
                                y - 12,
                                58,
                                y - 25
                        );

                        g2.fillOval(
                                42,
                                y - 16,
                                7,
                                7
                        );

                        g2.fillOval(
                                57,
                                y + 8,
                                7,
                                7
                        );

                        // Circuitos laterales - derecha
                        g2.setColor(
                                new Color(
                                        225,
                                        65,
                                        255,
                                        105
                                )
                        );

                        g2.drawLine(
                                ancho - 45,
                                y - 12,
                                ancho,
                                y - 12
                        );

                        g2.drawLine(
                                ancho - 60,
                                y + 12,
                                ancho - 15,
                                y + 12
                        );

                        g2.drawLine(
                                ancho - 45,
                                y - 12,
                                ancho - 58,
                                y - 25
                        );

                        g2.fillOval(
                                ancho - 49,
                                y - 16,
                                7,
                                7
                        );

                        g2.fillOval(
                                ancho - 64,
                                y + 8,
                                7,
                                7
                        );

                        // Línea inferior neón
                        GradientPaint linea =
                                new GradientPaint(
                                        0,
                                        alto - 2,
                                        AZUL,
                                        ancho,
                                        alto - 2,
                                        ROSA
                                );

                        g2.setPaint(
                                linea
                        );

                        g2.fillRect(
                                0,
                                alto - 2,
                                ancho,
                                2
                        );

                        g2.dispose();
                    }
                };

        menu.setOpaque(false);

        menu.setBorder(
                new EmptyBorder(
                        7,
                        20,
                        7,
                        20
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

        JButton btnInicio =
                crearBotonMenu(
                        "[HOME] INICIO"
                );

        JButton btnProductos =
                crearBotonMenu(
                        "[PRODUCTOS] PRODUCTOS"
                );

        JButton btnOfertas =
                crearBotonMenu(
                        "[OFERTAS] OFERTAS"
                );

        JButton btnCliente =
                crearBotonMenu(
                        "[CLIENTE] CLIENTE"
                );

        JButton btnAdministracion =
                crearBotonMenu(
                        "[ADMIN] ADMINISTRACIÓN"
                );

        izquierda.add(
                btnInicio
        );

        izquierda.add(
                btnProductos
        );

        izquierda.add(
                btnOfertas
        );

        izquierda.add(
                btnCliente
        );

        izquierda.add(
                btnAdministracion
        );

        // =====================================================
        // CARRITO
        // =====================================================

        lblCarrito =
                crearBotonMenu(
                        "[CARRITO] CARRITO (0)"
                );

        lblCarrito.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        13
                )
        );

        lblCarrito.addActionListener(
                e -> mostrarCarrito()
        );

        menu.add(
                izquierda,
                BorderLayout.WEST
        );

        menu.add(
                lblCarrito,
                BorderLayout.EAST
        );

        // =====================================================
        // ACCIONES DEL MENU
        // =====================================================

        btnInicio.addActionListener(
                e -> mostrarInicio()
        );

        btnProductos.addActionListener(
                e -> {
                    mostrarTodosLosProductos();
                    mostrarListaProductosDialogo(
                            false
                    );
                }
        );

        btnOfertas.addActionListener(
                e -> {
                    mostrarOfertas();
                    mostrarListaProductosDialogo(
                            true
                    );
                }
        );

        btnCliente.addActionListener(
                e ->
                        mostrarAcceso(
                                "CLIENTE",
                                this::mostrarCliente
                        )
        );

        btnAdministracion.addActionListener(
                e ->
                        mostrarAcceso(
                                "ADMINISTRACIÓN",
                                this::mostrarAdministracion
                        )
        );

        return menu;
    }

    // =========================================================
    // ACCESO CON CONTRASEÑA
    // =========================================================

    private void mostrarAcceso(
            String modulo,
            Runnable accion
    ) {

        JDialog dialogo =
                new JDialog(
                        this,
                        "Acceso - TechStore",
                        true
                );

        dialogo.setSize(
                400,
                255
        );

        dialogo.setResizable(
                false
        );

        dialogo.setLocationRelativeTo(
                this
        );

        JPanel fondo =
                new JPanel() {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w =
                                getWidth();

                        int h =
                                getHeight();

                        GradientPaint gradiente =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                7,
                                                15,
                                                42
                                        ),
                                        w,
                                        h,
                                        new Color(
                                                45,
                                                10,
                                                60
                                        )
                                );

                        g2.setPaint(
                                gradiente
                        );

                        g2.fillRoundRect(
                                0,
                                0,
                                w,
                                h,
                                24,
                                24
                        );

                        g2.setPaint(
                                new GradientPaint(
                                        0,
                                        0,
                                        AZUL,
                                        w,
                                        0,
                                        ROSA
                                )
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        1.5f
                                )
                        );

                        g2.drawRoundRect(
                                1,
                                1,
                                w - 3,
                                h - 3,
                                24,
                                24
                        );

                        g2.dispose();
                    }
                };

        fondo.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        fondo.setBorder(
                new EmptyBorder(
                        18,
                        24,
                        18,
                        24
                )
        );

        // =====================================================
        // TITULO
        // =====================================================

        JLabel titulo =
                new JLabel(
                        "🔐  ACCESO A " + modulo,
                        SwingConstants.CENTER
                );

        titulo.setForeground(
                AZUL
        );

        titulo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        21
                )
        );

        fondo.add(
                titulo,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTRO
        // =====================================================

        JPanel centro =
                new JPanel();

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS
                )
        );

        centro.setOpaque(
                false
        );

        JLabel etiqueta =
                new JLabel(
                        "Contraseña"
                );

        etiqueta.setForeground(
                BLANCO
        );

        etiqueta.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JPasswordField campoPassword =
                new JPasswordField();

        campoPassword.setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );

        campoPassword.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        36
                )
        );

        campoPassword.setBackground(
                new Color(
                        7,
                        10,
                        30
                )
        );

        campoPassword.setForeground(
                BLANCO
        );

        campoPassword.setCaretColor(
                AZUL
        );

        campoPassword.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                AZUL,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        etiqueta.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        campoPassword.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        centro.add(
                etiqueta
        );

        centro.add(
                Box.createVerticalStrut(
                        7
                )
        );

        centro.add(
                campoPassword
        );

        centro.add(
                Box.createVerticalStrut(
                        2
                )
        );

        centro.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );

        fondo.add(
                centro,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTONES
        // =====================================================

        JButton ingresar =
                crearBotonAccion(
                        "INGRESAR",
                        AZUL_BOTON
                );

        JButton cancelar =
                crearBotonAccion(
                        "CANCELAR",
                        MORADO
                );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                0
                        )
                );

        botones.setOpaque(
                false
        );

        botones.add(
                ingresar
        );

        botones.add(
                cancelar
        );

        fondo.add(
                botones,
                BorderLayout.SOUTH
        );

        Runnable validar =
                () -> {

                    String contraseña =
                            new String(
                                    campoPassword.getPassword()
                            );

                    if (
                            "aca".equals(
                                    contraseña
                            )
                    ) {

                        dialogo.dispose();

                        accion.run();

                    } else {

                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Contraseña incorrecta.",
                                "Acceso denegado",
                                JOptionPane.ERROR_MESSAGE
                        );

                        campoPassword.setText(
                                ""
                        );

                        campoPassword.requestFocusInWindow();
                    }
                };

        ingresar.addActionListener(
                e -> validar.run()
        );

        campoPassword.addActionListener(
                e -> validar.run()
        );

        cancelar.addActionListener(
                e -> dialogo.dispose()
        );

        dialogo.setContentPane(
                fondo
        );

        dialogo.getRootPane()
                .setDefaultButton(
                        ingresar
                );

        SwingUtilities.invokeLater(
                () ->
                        campoPassword
                                .requestFocusInWindow()
        );

        dialogo.setVisible(
                true
        );
    }

    // =========================================================
    // BOTON DEL MENU
    // =========================================================

    private JButton crearBotonMenu(
            String textoOriginal
    ) {

        String iconoMenu =
                "";

        String textoMenu =
                textoOriginal;

        if (textoMenu.startsWith("[HOME]")) {

            iconoMenu = "HOME";
            textoMenu =
                    textoMenu
                            .substring(6)
                            .trim();

        } else if (
                textoMenu.startsWith("[PRODUCTOS]")
        ) {

            iconoMenu = "PRODUCTOS";
            textoMenu =
                    textoMenu
                            .substring(11)
                            .trim();

        } else if (
                textoMenu.startsWith("[OFERTAS]")
        ) {

            iconoMenu = "OFERTAS";
            textoMenu =
                    textoMenu
                            .substring(9)
                            .trim();

        } else if (
                textoMenu.startsWith("[CLIENTE]")
        ) {

            iconoMenu = "CLIENTE";
            textoMenu =
                    textoMenu
                            .substring(9)
                            .trim();

        } else if (
                textoMenu.startsWith("[ADMIN]")
        ) {

            iconoMenu = "ADMIN";
            textoMenu =
                    textoMenu
                            .substring(7)
                            .trim();

        } else if (
                textoMenu.startsWith("[CARRITO]")
        ) {

            iconoMenu = "CARRITO";
            textoMenu =
                    textoMenu
                            .substring(9)
                            .trim();
        }

        final String iconoMenuFinal =
                iconoMenu;

        final String textoMenuFinal =
                textoMenu;

        JButton boton =
                new JButton(
                        textoMenuFinal
                ) {

                    private boolean hover =
                            false;

                    private float pulso =
                            0.0f;

                    private Timer animacion;

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w =
                                getWidth();

                        int h =
                                getHeight();

                        int arco =
                                h - 2;

                        /*
                         * =================================================
                         * EFECTO GLOW AL PASAR EL MOUSE
                         * =================================================
                         */

                        if (hover) {

                            int alphaGlow =
                                    24
                                            + (int)
                                            (
                                                    18
                                                            * (
                                                            0.5f
                                                                    + 0.5f
                                                                    * (float)
                                                                    Math.sin(
                                                                            pulso
                                                                    )
                                                    )
                                            );

                            g2.setColor(
                                    new Color(
                                            0,
                                            210,
                                            255,
                                            alphaGlow
                                    )
                            );

                            g2.fillRoundRect(
                                    1,
                                    1,
                                    w - 2,
                                    h - 2,
                                    arco,
                                    arco
                            );

                            g2.setColor(
                                    new Color(
                                            0,
                                            210,
                                            255,
                                            45
                                    )
                            );

                            g2.drawRoundRect(
                                    0,
                                    0,
                                    w - 1,
                                    h - 1,
                                    arco,
                                    arco
                            );
                        }

                        /*
                         * =================================================
                         * FONDO TIPO CAPSULA
                         * =================================================
                         */

                        GradientPaint fondo;

                        if (hover) {

                            fondo =
                                    new GradientPaint(
                                            0,
                                            0,
                                            new Color(
                                                    12,
                                                    45,
                                                    75
                                            ),
                                            w,
                                            h,
                                            new Color(
                                                    45,
                                                    12,
                                                    72
                                            )
                                    );

                        } else {

                            fondo =
                                    new GradientPaint(
                                            0,
                                            0,
                                            new Color(
                                                    11,
                                                    17,
                                                    43
                                            ),
                                            w,
                                            h,
                                            new Color(
                                                    24,
                                                    12,
                                                    49
                                            )
                                    );
                        }

                        g2.setPaint(
                                fondo
                        );

                        g2.fillRoundRect(
                                0,
                                0,
                                w - 1,
                                h - 1,
                                arco,
                                arco
                        );

                        /*
                         * =================================================
                         * BORDE NEON
                         * =================================================
                         */

                        GradientPaint borde;

                        if (hover) {

                            borde =
                                    new GradientPaint(
                                            0,
                                            0,
                                            AZUL,
                                            w,
                                            0,
                                            ROSA
                                    );

                        } else {

                            borde =
                                    new GradientPaint(
                                            0,
                                            0,
                                            new Color(
                                                    0,
                                                    155,
                                                    220
                                            ),
                                            w,
                                            0,
                                            new Color(
                                                    170,
                                                    55,
                                                    210
                                            )
                                    );
                        }

                        g2.setPaint(
                                borde
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        hover
                                                ? 2.0f
                                                : 1.0f
                                )
                        );

                        g2.drawRoundRect(
                                hover
                                        ? 1
                                        : 0,
                                hover
                                        ? 1
                                        : 0,
                                w - (
                                        hover
                                                ? 3
                                                : 1
                                ),
                                h - (
                                        hover
                                                ? 3
                                                : 1
                                ),
                                arco,
                                arco
                        );

                        /*
                         * Línea de luz interior cuando hay hover.
                         */

                        if (hover) {

                            g2.setColor(
                                    new Color(
                                            255,
                                            255,
                                            255,
                                            25
                                    )
                            );

                            g2.drawRoundRect(
                                    3,
                                    3,
                                    w - 7,
                                    h - 7,
                                    arco - 5,
                                    arco - 5
                            );
                        }

                        /*
                         * El fondo y el borde los dibujamos nosotros.
                         * El icono se dibuja como vector para que sí tenga
                         * color en Windows/Swing.
                         */

                        pintarIconoMenuColor(
                                g2,
                                iconoMenuFinal,
                                9,
                                h / 2 - 10,
                                hover
                        );

                        g2.setFont(
                                getFont()
                        );

                        g2.setColor(
                                hover
                                        ? AZUL
                                        : BLANCO
                        );

                        FontMetrics fm =
                                g2.getFontMetrics();

                        int textoX =
                                34;

                        int textoY =
                                (h
                                        - fm.getHeight())
                                        / 2
                                        + fm.getAscent();

                        String textoVisible =
                                "CARRITO".equals(
                                        iconoMenuFinal
                                )
                                        ? getText()
                                                .replace(
                                                        "[CARRITO]",
                                                        ""
                                                )
                                                .trim()
                                        : textoMenuFinal;

                        g2.drawString(
                                textoVisible,
                                textoX,
                                textoY
                        );

                        g2.dispose();
                    }

                    public void setHover(
                            boolean valor
                    ) {

                        hover =
                                valor;

                        setForeground(
                                hover
                                        ? AZUL
                                        : BLANCO
                        );

                        if (hover) {

                            if (
                                    animacion == null
                            ) {

                                animacion =
                                        new Timer(
                                                45,
                                                evento -> {

                                                    pulso +=
                                                            0.22f;

                                                    repaint();
                                                }
                                        );
                            }

                            if (
                                    !animacion.isRunning()
                            ) {

                                animacion.start();
                            }

                        } else {

                            pulso =
                                    0.0f;

                            if (
                                    animacion != null
                                            &&
                                    animacion.isRunning()
                            ) {

                                animacion.stop();
                            }

                            repaint();
                        }
                    }
                };

        boton.setForeground(
                BLANCO
        );

        boton.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        13
                )
        );

        /*
         * La cápsula se adapta al texto.
         */
        boton.setBorder(
                new EmptyBorder(
                        12,
                        30,
                        12,
                        30
                )
        );

        boton.setBorderPainted(
                false
        );

        boton.setFocusPainted(
                false
        );

        /*
         * Muy importante:
         * desactivamos el fondo estándar de Swing.
         */
        boton.setContentAreaFilled(
                false
        );

        boton.setOpaque(
                false
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        boton.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        ((JButton) e.getComponent())
                                .putClientProperty(
                                        "hover",
                                        Boolean.TRUE
                                );

                        /*
                         * Usamos el método setHover()
                         * de la subclase.
                         */
                        try {

                            e.getComponent()
                                    .getClass()
                                    .getMethod(
                                            "setHover",
                                            boolean.class
                                    )
                                    .invoke(
                                            e.getComponent(),
                                            true
                                    );

                        } catch (Exception ignored) {
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        ((JComponent) e.getComponent())
                                .putClientProperty(
                                        "hover",
                                        Boolean.FALSE
                                );

                        try {

                            e.getComponent()
                                    .getClass()
                                    .getMethod(
                                            "setHover",
                                            boolean.class
                                    )
                                    .invoke(
                                            e.getComponent(),
                                            false
                                    );

                        } catch (Exception ignored) {
                        }
                    }

                    @Override
                    public void mousePressed(
                            MouseEvent e
                    ) {

                        e.getComponent()
                                .setForeground(
                                        Color.WHITE
                                );
                    }

                    @Override
                    public void mouseReleased(
                            MouseEvent e
                    ) {

                        e.getComponent()
                                .setForeground(
                                        AZUL
                                );
                    }
                }
        );

        return boton;
    }


    // =========================================================
    // ICONOS COLOR - SOLO PARA EL MENU
    // =========================================================

    private void pintarIconoMenuColor(
            Graphics2D g2,
            String tipo,
            int x,
            int y,
            boolean hover
    ) {

        if ("HOME".equals(tipo)) {

            pintarIconoCasa(
                    g2,
                    x,
                    y,
                    new Color(
                            0,
                            210,
                            255
                    )
            );

        } else if ("PRODUCTOS".equals(tipo)) {

            pintarIconoLaptop(
                    g2,
                    x,
                    y,
                    new Color(
                            80,
                            165,
                            255
                    )
            );

        } else if ("OFERTAS".equals(tipo)) {

            pintarIconoOferta(
                    g2,
                    x,
                    y
            );

        } else if ("CLIENTE".equals(tipo)) {

            pintarIconoCliente(
                    g2,
                    x,
                    y
            );

        } else if ("ADMIN".equals(tipo)) {

            pintarIconoAdmin(
                    g2,
                    x,
                    y
            );

        } else if ("CARRITO".equals(tipo)) {

            pintarIconoCarrito(
                    g2,
                    x,
                    y
            );
        }

        if (hover) {

            g2.setColor(
                    new Color(
                            255,
                            255,
                            255,
                            28
                    )
            );

            g2.fillOval(
                    x - 2,
                    y - 2,
                    23,
                    23
            );
        }
    }

    private void pintarIconoCasa(
            Graphics2D g2,
            int x,
            int y,
            Color color
    ) {

        g2.setColor(color);

        Polygon techo =
                new Polygon();

        techo.addPoint(
                x + 1,
                y + 9
        );

        techo.addPoint(
                x + 10,
                y + 1
        );

        techo.addPoint(
                x + 19,
                y + 9
        );

        g2.fillPolygon(techo);

        g2.fillRoundRect(
                x + 4,
                y + 8,
                12,
                10,
                2,
                2
        );

        g2.setColor(
                new Color(
                        7,
                        10,
                        30
                )
        );

        g2.fillRect(
                x + 9,
                y + 12,
                3,
                6
        );
    }

    private void pintarIconoLaptop(
            Graphics2D g2,
            int x,
            int y,
            Color color
    ) {

        g2.setColor(color);

        g2.fillRoundRect(
                x + 2,
                y + 1,
                16,
                12,
                2,
                2
        );

        g2.setColor(
                new Color(
                        7,
                        10,
                        30
                )
        );

        g2.fillRect(
                x + 4,
                y + 3,
                12,
                8
        );

        g2.setColor(
                color
        );

        g2.fillRoundRect(
                x,
                y + 14,
                20,
                4,
                2,
                2
        );
    }

    private void pintarIconoOferta(
            Graphics2D g2,
            int x,
            int y
    ) {

        g2.setColor(
                new Color(
                        255,
                        75,
                        45
                )
        );

        Polygon llama =
                new Polygon();

        llama.addPoint(
                x + 10,
                y
        );

        llama.addPoint(
                x + 16,
                y + 8
        );

        llama.addPoint(
                x + 15,
                y + 16
        );

        llama.addPoint(
                x + 10,
                y + 20
        );

        llama.addPoint(
                x + 4,
                y + 16
        );

        llama.addPoint(
                x + 4,
                y + 9
        );

        llama.addPoint(
                x + 8,
                y + 12
        );

        g2.fillPolygon(llama);

        g2.setColor(
                new Color(
                        255,
                        215,
                        60
                )
        );

        Polygon centro =
                new Polygon();

        centro.addPoint(
                x + 10,
                y + 7
        );

        centro.addPoint(
                x + 13,
                y + 13
        );

        centro.addPoint(
                x + 10,
                y + 17
        );

        centro.addPoint(
                x + 7,
                y + 14
        );

        g2.fillPolygon(centro);
    }

    private void pintarIconoCliente(
            Graphics2D g2,
            int x,
            int y
    ) {

        g2.setColor(
                new Color(
                        75,
                        220,
                        255
                )
        );

        g2.fillOval(
                x + 6,
                y,
                8,
                8
        );

        g2.fillRoundRect(
                x + 3,
                y + 9,
                14,
                10,
                5,
                5
        );
    }

    private void pintarIconoAdmin(
            Graphics2D g2,
            int x,
            int y
    ) {

        Color color =
                new Color(
                        205,
                        100,
                        255
                );

        g2.setColor(color);

        g2.fillOval(
                x + 3,
                y + 3,
                14,
                14
        );

        for (
                int i = 0;
                i < 8;
                i++
        ) {

            double angulo =
                    Math.toRadians(
                            i * 45
                    );

            int dx =
                    (int)
                            (
                                    Math.cos(
                                            angulo
                                    ) * 8
                            );

            int dy =
                    (int)
                            (
                                    Math.sin(
                                            angulo
                                    ) * 8
                            );

            g2.fillRoundRect(
                    x + 9 + dx - 2,
                    y + 9 + dy - 2,
                    4,
                    4,
                    2,
                    2
            );
        }

        g2.setColor(
                new Color(
                        7,
                        10,
                        30
                )
        );

        g2.fillOval(
                x + 8,
                y + 8,
                4,
                4
        );
    }

    private void pintarIconoCarrito(
            Graphics2D g2,
            int x,
            int y
    ) {

        Color color =
                new Color(
                        0,
                        235,
                        150
                );

        g2.setColor(color);

        g2.setStroke(
                new BasicStroke(
                        2f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        g2.drawLine(
                x + 2,
                y + 3,
                x + 5,
                y + 3
        );

        g2.drawLine(
                x + 5,
                y + 3,
                x + 7,
                y + 14
        );

        g2.drawRoundRect(
                x + 7,
                y + 5,
                11,
                8,
                2,
                2
        );

        g2.fillOval(
                x + 8,
                y + 15,
                3,
                3
        );

        g2.fillOval(
                x + 15,
                y + 15,
                3,
                3
        );
    }

    // =========================================================
    // CONTENIDO
    // =========================================================

    private JPanel crearContenido() {

        JPanel contenido =
                new JPanel(
                        new BorderLayout()
                );

        contenido.setBackground(
                FONDO
        );

        contenido.add(
                crearEncabezado(),
                BorderLayout.NORTH
        );

        contenido.add(
                crearZonaTienda(),
                BorderLayout.CENTER
        );

        return contenido;
    }

    // =========================================================
    // ENCABEZADO
    // =========================================================

    private JPanel crearEncabezado() {

        JPanel encabezado =
                new JPanel() {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int ancho =
                                getWidth();

                        int alto =
                                getHeight();

                        // =================================================
                        // FONDO GRADIENTE TECHSTORE
                        // =================================================

                        GradientPaint gradiente =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                5,
                                                55,
                                                115
                                        ),
                                        ancho,
                                        0,
                                        new Color(
                                                125,
                                                20,
                                                140
                                        )
                                );

                        g2.setPaint(
                                gradiente
                        );

                        g2.fillRect(
                                0,
                                0,
                                ancho,
                                alto
                        );

                        // =================================================
                        // LINEAS TECNOLOGICAS - LADO IZQUIERDO
                        // =================================================

                        g2.setStroke(
                                new BasicStroke(
                                        1.5f
                                )
                        );

                        g2.setColor(
                                new Color(
                                        0,
                                        210,
                                        255,
                                        150
                                )
                        );

                        int yCentro =
                                alto / 2;

                        g2.drawLine(
                                25,
                                yCentro,
                                115,
                                yCentro
                        );

                        g2.drawLine(
                                70,
                                yCentro - 22,
                                145,
                                yCentro - 22
                        );

                        g2.drawLine(
                                70,
                                yCentro + 22,
                                145,
                                yCentro + 22
                        );

                        g2.drawLine(
                                115,
                                yCentro,
                                130,
                                yCentro - 22
                        );

                        g2.drawLine(
                                115,
                                yCentro,
                                130,
                                yCentro + 22
                        );

                        g2.fillOval(
                                20,
                                yCentro - 4,
                                8,
                                8
                        );

                        g2.fillOval(
                                140,
                                yCentro - 26,
                                8,
                                8
                        );

                        g2.fillOval(
                                140,
                                yCentro + 18,
                                8,
                                8
                        );

                        // =================================================
                        // LINEAS TECNOLOGICAS - LADO DERECHO
                        // =================================================

                        g2.setColor(
                                new Color(
                                        225,
                                        65,
                                        255,
                                        150
                                )
                        );

                        int xDerecha =
                                ancho - 25;

                        g2.drawLine(
                                xDerecha - 90,
                                yCentro,
                                xDerecha,
                                yCentro
                        );

                        g2.drawLine(
                                xDerecha - 120,
                                yCentro - 22,
                                xDerecha - 45,
                                yCentro - 22
                        );

                        g2.drawLine(
                                xDerecha - 120,
                                yCentro + 22,
                                xDerecha - 45,
                                yCentro + 22
                        );

                        g2.drawLine(
                                xDerecha - 90,
                                yCentro,
                                xDerecha - 105,
                                yCentro - 22
                        );

                        g2.drawLine(
                                xDerecha - 90,
                                yCentro,
                                xDerecha - 105,
                                yCentro + 22
                        );

                        g2.fillOval(
                                xDerecha - 4,
                                yCentro - 4,
                                8,
                                8
                        );

                        g2.fillOval(
                                xDerecha - 124,
                                yCentro - 26,
                                8,
                                8
                        );

                        g2.fillOval(
                                xDerecha - 124,
                                yCentro + 18,
                                8,
                                8
                        );

                        // =================================================
                        // LINEAS CENTRALES DE ACENTO
                        // =================================================

                        g2.setColor(
                                new Color(
                                        0,
                                        210,
                                        255,
                                        180
                                )
                        );

                        g2.drawLine(
                                ancho / 2 - 360,
                                yCentro + 32,
                                ancho / 2 - 250,
                                yCentro + 32
                        );

                        g2.drawLine(
                                ancho / 2 + 250,
                                yCentro + 32,
                                ancho / 2 + 360,
                                yCentro + 32
                        );

                        g2.fillOval(
                                ancho / 2 - 365,
                                yCentro + 28,
                                8,
                                8
                        );

                        g2.fillOval(
                                ancho / 2 + 357,
                                yCentro + 28,
                                8,
                                8
                        );

                        // =================================================
                        // BORDE INFERIOR SUTIL
                        // =================================================

                        g2.setColor(
                                new Color(
                                        0,
                                        210,
                                        255,
                                        90
                                )
                        );

                        g2.drawLine(
                                0,
                                alto - 1,
                                ancho,
                                alto - 1
                        );

                        g2.dispose();
                    }
                };

        // =========================================================
        // ALTURA MÁS PEQUEÑA DEL ENCABEZADO
        // =========================================================

        encabezado.setPreferredSize(
                new Dimension(
                        0,
                        125
                )
        );

        encabezado.setMinimumSize(
                new Dimension(
                        0,
                        110
                )
        );

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        // =========================================================
        // TITULO
        // =========================================================

        JLabel titulo =
                new JLabel(
                        "TECHSTORE"
                );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titulo.setForeground(
                AZUL
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        42
                )
        );

        // =========================================================
        // SUBTITULO
        // =========================================================

        JLabel subtitulo =
                new JLabel(
                        "Tecnología para todos"
                );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitulo.setForeground(
                BLANCO
        );

        subtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        19
                )
        );

        encabezado.add(
                Box.createVerticalStrut(
                        15
                )
        );

        encabezado.add(
                titulo
        );

        encabezado.add(
                Box.createVerticalStrut(
                        2
                )
        );

        encabezado.add(
                subtitulo
        );

        return encabezado;
    }

    // =========================================================
    // ZONA DE TIENDA
    // =========================================================

    private JPanel crearZonaTienda() {

        JPanel zona =
                new JPanel(
                        new BorderLayout()
                );

        zona.setBackground(
                FONDO
        );

        zona.setBorder(
                new EmptyBorder(
                        15,
                        30,
                        10,
                        30
                )
        );

        JLabel bienvenida =
                new JLabel(
                        "¡Bienvenido a TechStore!",
                        SwingConstants.CENTER
                );

        bienvenida.setForeground(
                BLANCO
        );

        bienvenida.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        zona.add(
                bienvenida,
                BorderLayout.NORTH
        );

        JPanel centro =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        centro.setBackground(
                FONDO
        );

        centro.add(
                crearPanelFiltros(),
                BorderLayout.NORTH
        );

        panelProductos =
                new JPanel(
                        new GridLayout(
                                0,
                                4,
                                12,
                                12
                        )
                );

        panelProductos.setBackground(
                FONDO
        );

        panelProductos.setBorder(
                new EmptyBorder(
                        5,
                        5,
                        20,
                        5
                )
        );

        JScrollPane scroll =
                new JScrollPane(
                        panelProductos
                );

        scroll.setBorder(
                null
        );

        scroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants
                        .HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants
                        .VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.getViewport()
                .setBackground(
                        FONDO
                );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(
                        20
                );

        centro.add(
                scroll,
                BorderLayout.CENTER
        );

        zona.add(
                centro,
                BorderLayout.CENTER
        );

        return zona;
    }

    // =========================================================
    // FILTROS
    // =========================================================

    private JPanel crearPanelFiltros() {

        JPanel filtros =
                new JPanel();

        filtros.setLayout(
                new GridBagLayout()
        );

        filtros.setBackground(
                PANEL
        );

        filtros.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        100,
                                        70,
                                        190
                                ),
                                1
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        // =====================================================
        // BUSQUEDA
        // =====================================================

        JPanel busqueda =
                new JPanel(
                        new GridBagLayout()
                );

        busqueda.setOpaque(
                false
        );

        JLabel buscarLabel =
                new JLabel(
                        "BUSCAR:"
                );

        buscarLabel.setForeground(
                BLANCO
        );

        buscarLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        txtBuscar =
                new JTextField(
                        25
                );

        txtBuscar.setPreferredSize(
                new Dimension(
                        220,
                        36
                )
        );

        txtBuscar.setMinimumSize(
                new Dimension(
                        120,
                        36
                )
        );

        txtBuscar.setBackground(
                new Color(
                        7,
                        10,
                        30
                )
        );

        txtBuscar.setForeground(
                BLANCO
        );

        txtBuscar.setCaretColor(
                AZUL
        );

        txtBuscar.setBorder(
                new LineBorder(
                        AZUL,
                        1
                )
        );

        JButton buscar =
                crearBotonAccion(
                        "BUSCAR",
                        AZUL_BOTON
                );

        GridBagConstraints gbcBusqueda =
                new GridBagConstraints();

        gbcBusqueda.gridy = 0;
        gbcBusqueda.insets =
                new Insets(
                        3,
                        4,
                        3,
                        4
                );

        gbcBusqueda.anchor =
                GridBagConstraints.CENTER;

        // Etiqueta BUSCAR
        gbcBusqueda.gridx = 0;
        gbcBusqueda.weightx = 0;
        gbcBusqueda.fill =
                GridBagConstraints.NONE;

        busqueda.add(
                buscarLabel,
                gbcBusqueda
        );

        // Campo de búsqueda adaptable
        gbcBusqueda.gridx = 1;
        gbcBusqueda.weightx = 1.0;
        gbcBusqueda.fill =
                GridBagConstraints.HORIZONTAL;

        busqueda.add(
                txtBuscar,
                gbcBusqueda
        );

        // Botón BUSCAR
        gbcBusqueda.gridx = 2;
        gbcBusqueda.weightx = 0;
        gbcBusqueda.fill =
                GridBagConstraints.NONE;

        busqueda.add(
                buscar,
                gbcBusqueda
        );

        // =====================================================
        // CATEGORIAS
        // =====================================================

        JPanel categorias =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                3
                        )
                );

        categorias.setOpaque(
                false
        );

        JLabel categoriaLabel =
                new JLabel(
                        "CATEGORÍA:"
                );

        categoriaLabel.setForeground(
                BLANCO
        );

        categoriaLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        radioTodos =
                crearRadio(
                        "Todos"
                );

        radioComputadores =
                crearRadio(
                        "Computadores"
                );

        radioCelulares =
                crearRadio(
                        "Celulares"
                );

        radioTablets =
                crearRadio(
                        "Tablets"
                );

        radioAccesorios =
                crearRadio(
                        "Accesorios"
                );

        ButtonGroup grupo =
                new ButtonGroup();

        grupo.add(
                radioTodos
        );

        grupo.add(
                radioComputadores
        );

        grupo.add(
                radioCelulares
        );

        grupo.add(
                radioTablets
        );

        grupo.add(
                radioAccesorios
        );

        radioTodos.setSelected(
                true
        );

        categorias.add(
                categoriaLabel
        );

        categorias.add(
                radioTodos
        );

        categorias.add(
                radioComputadores
        );

        categorias.add(
                radioCelulares
        );

        categorias.add(
                radioTablets
        );

        categorias.add(
                radioAccesorios
        );

        // =====================================================
        // OPCIONES
        // =====================================================

        JPanel opciones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                3
                        )
                );

        opciones.setOpaque(
                false
        );

        chkDisponibles =
                new JCheckBox(
                        "Solo disponibles"
                );

        chkDisponibles.setSelected(
                true
        );

        chkDisponibles.setOpaque(
                false
        );

        chkDisponibles.setForeground(
                BLANCO
        );

        chkDisponibles.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        JLabel ordenar =
                new JLabel(
                        "ORDENAR:"
                );

        ordenar.setForeground(
                BLANCO
        );

        ordenar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        comboOrdenar =
                new JComboBox<>(
                        new String[]{
                                "Código",
                                "Nombre",
                                "Precio menor",
                                "Precio mayor",
                                "Stock"
                        }
                );

        comboOrdenar.setPreferredSize(
                new Dimension(
                        165,
                        34
                )
        );

        opciones.add(
                chkDisponibles
        );

        opciones.add(
                ordenar
        );

        opciones.add(
                comboOrdenar
        );

        lblMostrando =
                new JLabel(
                        "Mostrando: 0 productos"
                );

        lblMostrando.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblMostrando.setForeground(
                ROSA
        );

        lblMostrando.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        // =====================================================
        // DISTRIBUCION HORIZONTAL DE CONTROLES
        // =====================================================

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;
        gbc.insets =
                new Insets(
                        4,
                        8,
                        4,
                        8
                );

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weighty = 0;

        // BUSCAR
        gbc.gridx = 0;
        gbc.weightx = 0.30;

        filtros.add(
                busqueda,
                gbc
        );

        // CATEGORIA
        gbc.gridx = 1;
        gbc.weightx = 0.45;

        filtros.add(
                categorias,
                gbc
        );

        // OPCIONES
        gbc.gridx = 2;
        gbc.weightx = 0.25;

        filtros.add(
                opciones,
                gbc
        );

        // MOSTRANDO
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;

        filtros.add(
                lblMostrando,
                gbc
        );

        // =====================================================
        // EVENTOS
        // =====================================================

        buscar.addActionListener(
                e -> filtrarProductos()
        );

        txtBuscar.addActionListener(
                e -> filtrarProductos()
        );

        radioTodos.addActionListener(
                e -> filtrarProductos()
        );

        radioComputadores.addActionListener(
                e -> filtrarProductos()
        );

        radioCelulares.addActionListener(
                e -> filtrarProductos()
        );

        radioTablets.addActionListener(
                e -> filtrarProductos()
        );

        radioAccesorios.addActionListener(
                e -> filtrarProductos()
        );

        chkDisponibles.addActionListener(
                e -> filtrarProductos()
        );

        comboOrdenar.addActionListener(
                e -> filtrarProductos()
        );

        return filtros;
    }

    // =========================================================
    // RADIO BUTTON
    // =========================================================

    private JRadioButton crearRadio(
            String texto
    ) {

        JRadioButton radio =
                new JRadioButton(
                        texto
                );

        radio.setOpaque(
                false
        );

        radio.setForeground(
                BLANCO
        );

        radio.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        return radio;
    }

    // =========================================================
    // CARGAR PRODUCTOS
    // =========================================================

    private void cargarProductos() {

        productos =
                controller.listarProductos();

        System.out.println(
                "Productos encontrados: "
                        + productos.size()
        );

        filtrarProductos();
    }

    // =========================================================
    // FILTRAR
    // =========================================================

    private void filtrarProductos() {

        if (
                panelProductos == null
        ) {

            return;
        }

        List<Producto> lista =
                new ArrayList<>(
                        productos
                );

        String texto =
                txtBuscar
                        .getText()
                        .trim()
                        .toLowerCase();

        // =====================================================
        // BUSQUEDA
        // =====================================================

        if (
                !texto.isEmpty()
        ) {

            lista.removeIf(
                    p ->
                            !p.getNombre()
                                    .toLowerCase()
                                    .contains(texto)

                                    &&

                            !p.getCodigo()
                                    .toLowerCase()
                                    .contains(texto)

                                    &&

                            !p.getMarca()
                                    .toLowerCase()
                                    .contains(texto)
            );
        }

        // =====================================================
        // CATEGORIA
        // =====================================================

        String categoria =
                obtenerCategoria();

        if (
                !categoria.equals(
                        "Todos"
                )
        ) {

            lista.removeIf(
                    p ->
                            !p.getCategoria()
                                    .equalsIgnoreCase(
                                            categoria
                                    )
            );
        }

        // =====================================================
        // DISPONIBILIDAD
        // =====================================================

        if (
                chkDisponibles.isSelected()
        ) {

            lista.removeIf(
                    p ->
                            p.getStock() <= 0
            );
        }

        // =====================================================
        // ORDENAMIENTO
        // =====================================================

        String orden =
                (String)
                        comboOrdenar
                                .getSelectedItem();

        if (
                "Nombre".equals(
                        orden
                )
        ) {

            lista.sort(
                    Comparator.comparing(
                            Producto::getNombre
                    )
            );

        } else if (
                "Precio menor".equals(
                        orden
                )
        ) {

            lista.sort(
                    Comparator.comparingDouble(
                            Producto::getPrecio
                    )
            );

        } else if (
                "Precio mayor".equals(
                        orden
                )
        ) {

            lista.sort(
                    Comparator.comparingDouble(
                            Producto::getPrecio
                    ).reversed()
            );

        } else if (
                "Stock".equals(
                        orden
                )
        ) {

            lista.sort(
                    Comparator.comparingInt(
                            Producto::getStock
                    ).reversed()
            );

        } else {

            lista.sort(
                    Comparator.comparing(
                            Producto::getCodigo
                    )
            );
        }

        mostrarProductos(
                lista
        );
    }

    // =========================================================
    // OBTENER CATEGORIA
    // =========================================================

    private String obtenerCategoria() {

        if (
                radioComputadores.isSelected()
        ) {

            return "Computador";
        }

        if (
                radioCelulares.isSelected()
        ) {

            return "Celular";
        }

        if (
                radioTablets.isSelected()
        ) {

            return "Tablet";
        }

        if (
                radioAccesorios.isSelected()
        ) {

            return "Accesorios";
        }

        return "Todos";
    }

    // =========================================================
    // MOSTRAR PRODUCTOS
    // =========================================================

    private void mostrarProductos(
            List<Producto> lista
    ) {

        panelProductos.removeAll();

        indicadoresCantidad.clear();

        lblMostrando.setText(
                "Mostrando: "
                        + lista.size()
                        + " productos"
        );

        ajustarAlturaPanelProductos(
                lista.size()
        );

        for (
                Producto producto :
                lista
        ) {

            panelProductos.add(
                    crearTarjeta(
                            producto
                    )
            );
        }

        panelProductos.revalidate();

        panelProductos.repaint();
    }

    // =========================================================
    // ALTURA DEL PANEL SEGUN CANTIDAD DE PRODUCTOS
    // =========================================================

    private void ajustarAlturaPanelProductos(
            int cantidadProductos
    ) {

        final int COLUMNAS =
                4;

        final int ALTO_TARJETA =
                350;

        final int SEPARACION =
                12;

        int filas =
                Math.max(
                        1,
                        (int) Math.ceil(
                                cantidadProductos
                                        / (double) COLUMNAS
                        )
                );

        int altura =
                filas * ALTO_TARJETA
                        + Math.max(
                                0,
                                filas - 1
                        ) * SEPARACION
                        + 10;

        panelProductos.setPreferredSize(
                new Dimension(
                        panelProductos.getWidth(),
                        altura
                )
        );

        panelProductos.setMinimumSize(
                new Dimension(
                        0,
                        altura
                )
        );

        panelProductos.revalidate();
        panelProductos.repaint();
    }

    // =========================================================
    // TARJETA
    // =========================================================

    private JPanel crearTarjeta(
            Producto producto
    ) {

        JPanel tarjeta =
                new JPanel() {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        int ancho =
                                getWidth();

                        int alto =
                                getHeight();

                        // -------------------------------------
                        // DEGRADADO COMPLETO
                        // -------------------------------------

                        GradientPaint gradiente =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                13,
                                                24,
                                                58
                                        ),

                                        0,
                                        alto,
                                        new Color(
                                                67,
                                                18,
                                                85
                                        )
                                );

                        g2.setPaint(
                                gradiente
                        );

                        g2.fillRoundRect(
                                0,
                                0,
                                ancho,
                                alto,
                                20,
                                20
                        );

                        // -------------------------------------
                        // BRILLO SUPERIOR
                        // -------------------------------------

                        GradientPaint brillo =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                35,
                                                70,
                                                130,
                                                80
                                        ),

                                        ancho,
                                        0,
                                        new Color(
                                                160,
                                                40,
                                                170,
                                                70
                                        )
                                );

                        g2.setPaint(
                                brillo
                        );

                        g2.fillRoundRect(
                                0,
                                0,
                                ancho,
                                95,
                                20,
                                20
                        );

                        g2.dispose();
                    }
                };

        tarjeta.setOpaque(
                false
        );

        tarjeta.setLayout(
                new BoxLayout(
                        tarjeta,
                        BoxLayout.Y_AXIS
                )
        );

        /*
         * El ancho de la tarjeta lo determina el GridLayout.
         * No fijamos un ancho para que las 4 columnas puedan
         * crecer o reducirse según el tamaño de la ventana.
         */
        tarjeta.setMinimumSize(
                new Dimension(
                        0,
                        350
                )
        );

        tarjeta.setPreferredSize(
                new Dimension(
                        0,
                        350
                )
        );

        tarjeta.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        350
                )
        );

        // =====================================================
        // BORDE SEGUN STOCK
        // =====================================================

        Color borde;

        if (
                producto.getStock() <= 0
        ) {

            borde =
                    ROJO;

        } else if (
                producto.getStock() <= 5
        ) {

            borde =
                    NARANJA;

        } else {

            borde =
                    VERDE;
        }

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                borde,
                                2,
                                true
                        ),
                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        // =====================================================
        // INDICADOR DE CANTIDAD EN CARRITO
        // =====================================================

        final String codigoProducto =
                producto.getCodigo();

        final int cantidadInicial =
                carrito.getOrDefault(
                        codigoProducto,
                        0
                );

        JLabel indicadorCantidad =
                new JLabel(
                        String.valueOf(
                                cantidadInicial
                        ),
                        SwingConstants.CENTER
                ) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int ancho =
                                getWidth();

                        int alto =
                                getHeight();

                        Color fondoIndicador =
                                new Color(
                                        225,
                                        65,
                                        255
                                );

                        if (
                                getText().equals("0")
                        ) {

                            fondoIndicador =
                                    new Color(
                                            75,
                                            80,
                                            120
                                    );
                        }

                        g2.setColor(
                                fondoIndicador
                        );

                        g2.fillOval(
                                1,
                                1,
                                ancho - 2,
                                alto - 2
                        );

                        g2.setColor(
                                new Color(
                                        0,
                                        210,
                                        255
                                )
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        1.5f
                                )
                        );

                        g2.drawOval(
                                1,
                                1,
                                ancho - 2,
                                alto - 2
                        );

                        g2.dispose();

                        super.paintComponent(
                                g
                        );
                    }
                };

        indicadorCantidad.setForeground(
                BLANCO
        );

        indicadorCantidad.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        indicadorCantidad.setPreferredSize(
                new Dimension(
                        28,
                        28
                )
        );

        indicadorCantidad.setMinimumSize(
                new Dimension(
                        28,
                        28
                )
        );

        indicadorCantidad.setMaximumSize(
                new Dimension(
                        28,
                        28
                )
        );

        indicadorCantidad.setOpaque(
                false
        );

        indicadorCantidad.setVisible(
                cantidadInicial > 0
        );

        indicadoresCantidad.put(
                codigoProducto,
                indicadorCantidad
        );

        JPanel cabeceraCantidad =
                new JPanel(
                        new BorderLayout()
                );

        cabeceraCantidad.setOpaque(
                false
        );

        cabeceraCantidad.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        28
                )
        );

        cabeceraCantidad.add(
                indicadorCantidad,
                BorderLayout.EAST
        );

        tarjeta.add(
                cabeceraCantidad
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        2
                )
        );

        // =====================================================
        // IMAGEN
        // =====================================================

        JLabel imagen =
                crearImagenLocal(
                        producto
                );

        // =====================================================
        // CATEGORIA
        // =====================================================

        JLabel categoria =
                new JLabel(
                        producto.getCategoria()
                                .toUpperCase()
                );

        categoria.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        categoria.setForeground(
                ROSA
        );

        categoria.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        // =====================================================
        // NOMBRE
        // =====================================================

        JLabel nombre =
                new JLabel(
                        producto.getNombre()
                );

        nombre.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        nombre.setForeground(
                BLANCO
        );

        nombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        // =====================================================
        // MARCA
        // =====================================================

        JLabel marca =
                new JLabel(
                        "Marca: "
                                + producto.getMarca()
                );

        marca.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        marca.setForeground(
                GRIS
        );

        marca.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        // =====================================================
        // PRECIO
        // =====================================================

        JLabel precio =
                new JLabel(
                        formatearPrecio(
                                producto.getPrecio()
                        )
                );

        precio.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        precio.setForeground(
                AZUL
        );

        precio.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        // =====================================================
        // STOCK
        // =====================================================

        JLabel stock =
                crearEtiquetaStock(
                        producto
                );

        // =====================================================
        // BOTONES
        // =====================================================

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                5,
                                3
                        )
                );

        botones.setOpaque(
                false
        );

        JButton ver =
                crearBotonAccion(
                        "VER",
                        MORADO
                );

        JButton agregar =
                crearBotonAccion(
                        "AGREGAR",
                        AZUL_BOTON
                );

        if (
                producto.getStock() <= 0
        ) {

            agregar.setEnabled(
                    false
            );
        }

        ver.addActionListener(
                e ->
                        mostrarDetalle(
                                producto
                        )
        );

        agregar.addActionListener(
                e ->
                        agregarCarrito(
                                producto
                        )
        );

        botones.add(
                ver
        );

        botones.add(
                agregar
        );

        // =====================================================
        // ARMAR TARJETA
        // =====================================================

        tarjeta.add(
                Box.createVerticalStrut(
                        5
                )
        );

        JPanel contenedorImagen =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                0,
                                0
                        )
                );

        contenedorImagen.setOpaque(false);
        contenedorImagen.add(imagen);

        tarjeta.add(
                contenedorImagen
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        7
                )
        );

        tarjeta.add(
                categoria
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        3
                )
        );

        tarjeta.add(
                nombre
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        3
                )
        );

        tarjeta.add(
                marca
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        4
                )
        );

        tarjeta.add(
                precio
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        2
                )
        );

        tarjeta.add(
                stock
        );

        tarjeta.add(
                Box.createVerticalStrut(
                        4
                )
        );

        tarjeta.add(
                botones
        );

        return tarjeta;
    }

    // =========================================================
    // IMAGEN
    // =========================================================

    private JLabel crearImagenLocal(
            Producto producto
    ) {

        JLabel imagen =
                new JLabel();

        // =====================================================
        // TAMAÑO DE IMAGEN
        // =====================================================

        int ANCHO =
                190;

        int ALTO =
                115;

        /*
         * La altura se mantiene controlada, pero el ancho
         * puede crecer junto con la tarjeta del producto.
         */
        imagen.setPreferredSize(
                new Dimension(
                        ANCHO,
                        ALTO
                )
        );

        imagen.setMinimumSize(
                new Dimension(
                        0,
                        ALTO
                )
        );

        imagen.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        ALTO
                )
        );

        imagen.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        imagen.setVerticalAlignment(
                SwingConstants.CENTER
        );

        /*
         * Centra el componente completo de la imagen
         * horizontalmente dentro de cada tarjeta.
         */
        imagen.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        imagen.setOpaque(
                true
        );

        imagen.setBackground(
                new Color(
                        8,
                        12,
                        35
                )
        );

        imagen.setBorder(
                new LineBorder(
                        new Color(
                                80,
                                85,
                                140
                        ),
                        1,
                        true
                )
        );

        // =====================================================
        // RUTA
        // =====================================================

        String ruta =
                "src/main/resources/images/"
                        + producto.getCodigo()
                        + ".jpg";

        File archivo =
                new File(
                        ruta
                );

        // =====================================================
        // IMAGEN NO ENCONTRADA
        // =====================================================

        if (
                !archivo.exists()
        ) {

            imagen.setText(
                    "Imagen no disponible"
            );

            imagen.setForeground(
                    GRIS
            );

            imagen.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            12
                    )
            );

            return imagen;
        }

        // =====================================================
        // CARGAR IMAGEN
        // =====================================================

        ImageIcon icono =
                new ImageIcon(
                        ruta
                );

        int anchoOriginal =
                icono.getIconWidth();

        int altoOriginal =
                icono.getIconHeight();

        if (
                anchoOriginal <= 0
                        ||
                altoOriginal <= 0
        ) {

            imagen.setText(
                    "Imagen no disponible"
            );

            imagen.setForeground(
                    GRIS
            );

            return imagen;
        }

        // =====================================================
        // ESCALAR SIN DEFORMAR
        // =====================================================

        double escalaX =
                (double) ANCHO
                        / anchoOriginal;

        double escalaY =
                (double) ALTO
                        / altoOriginal;

        double escala =
                Math.min(
                        escalaX,
                        escalaY
                );

        int nuevoAncho =
                (int)
                        (
                                anchoOriginal
                                        * escala
                        );

        /*
         * Escalado inicial.
         */
        ajustarIconoImagen(
                imagen,
                icono,
                ANCHO,
                ALTO
        );

        /*
         * Cuando la ventana cambia de tamaño, la imagen
         * vuelve a calcularse usando el espacio real disponible.
         */
        imagen.addComponentListener(
                new java.awt.event.ComponentAdapter() {

                    @Override
                    public void componentResized(
                            java.awt.event.ComponentEvent e
                    ) {

                        int anchoDisponible =
                                imagen.getWidth();

                        if (
                                anchoDisponible <= 0
                        ) {

                            anchoDisponible = ANCHO;
                        }

                        int altoDisponible =
                                imagen.getHeight();

                        if (
                                altoDisponible <= 0
                        ) {

                            altoDisponible = ALTO;
                        }

                        ajustarIconoImagen(
                                imagen,
                                icono,
                                anchoDisponible,
                                altoDisponible
                        );
                    }
                }
        );

        return imagen;
    }

    // =========================================================
    // AJUSTAR IMAGEN AL TAMAÑO DISPONIBLE
    // =========================================================

    private void ajustarIconoImagen(
            JLabel etiqueta,
            ImageIcon iconoOriginal,
            int anchoDisponible,
            int altoDisponible
    ) {

        int anchoOriginal =
                iconoOriginal.getIconWidth();

        int altoOriginal =
                iconoOriginal.getIconHeight();

        if (
                anchoOriginal <= 0
                        ||
                altoOriginal <= 0
        ) {

            return;
        }

        int margen =
                10;

        int anchoObjetivo =
                Math.max(
                        1,
                        anchoDisponible - margen
                );

        int altoObjetivo =
                Math.max(
                        1,
                        altoDisponible - margen
                );

        double escalaX =
                (double) anchoObjetivo
                        / anchoOriginal;

        double escalaY =
                (double) altoObjetivo
                        / altoOriginal;

        double escala =
                Math.min(
                        escalaX,
                        escalaY
                );

        int nuevoAncho =
                Math.max(
                        1,
                        (int)
                                (
                                        anchoOriginal
                                                * escala
                                )
                );

        int nuevoAlto =
                Math.max(
                        1,
                        (int)
                                (
                                        altoOriginal
                                                * escala
                                )
                );

        Image escalada =
                iconoOriginal.getImage()
                        .getScaledInstance(
                                nuevoAncho,
                                nuevoAlto,
                                Image.SCALE_SMOOTH
                        );

        etiqueta.setIcon(
                new ImageIcon(
                        escalada
                )
        );
    }

    // =========================================================
    // ETIQUETA STOCK
    // =========================================================

    private JLabel crearEtiquetaStock(
            Producto producto
    ) {

        String texto;

        Color color;

        if (
                producto.getStock() <= 0
        ) {

            texto =
                    "AGOTADO";

            color =
                    ROJO;

        } else if (
                producto.getStock() <= 5
        ) {

            texto =
                    "Pocas unidades: "
                            + producto.getStock();

            color =
                    NARANJA;

        } else {

            texto =
                    "Stock: "
                            + producto.getStock();

            color =
                    VERDE;
        }

        JLabel etiqueta =
                new JLabel(
                        texto
                );

        etiqueta.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        etiqueta.setForeground(
                color
        );

        etiqueta.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        return etiqueta;
    }

    // =========================================================
    // BOTON ACCION
    // =========================================================

    private JButton crearBotonAccion(
            String texto,
            Color color
    ) {

        JButton boton =
                new JButton(
                        texto
                );

        boton.setBackground(
                color
        );

        boton.setForeground(
                Color.WHITE
        );

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        boton.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        boton.setBorderPainted(
                false
        );

        boton.setFocusPainted(
                false
        );

        boton.setOpaque(
                true
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        boton.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (
                                boton.isEnabled()
                        ) {

                            boton.setBackground(
                                    color.brighter()
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (
                                boton.isEnabled()
                        ) {

                            boton.setBackground(
                                    color
                            );
                        }
                    }
                }
        );

        return boton;
    }

    // =========================================================
    // PRECIO
    // =========================================================

    private String formatearPrecio(
            double precio
    ) {

        return String.format(
                "$%,.0f",
                precio
        ).replace(
                ",",
                "."
        );
    }

    // =========================================================
    // INICIO
    // =========================================================

    private void mostrarInicio() {

        txtBuscar.setText(
                ""
        );

        radioTodos.setSelected(
                true
        );

        chkDisponibles.setSelected(
                true
        );

        comboOrdenar.setSelectedItem(
                "Código"
        );

        filtrarProductos();
    }

    // =========================================================
    // PRODUCTOS
    // =========================================================

    private void mostrarTodosLosProductos() {

        txtBuscar.setText(
                ""
        );

        radioTodos.setSelected(
                true
        );

        chkDisponibles.setSelected(
                false
        );

        comboOrdenar.setSelectedItem(
                "Código"
        );

        filtrarProductos();
    }

    // =========================================================
    // OFERTAS
    // =========================================================

    private void mostrarOfertas() {

        txtBuscar.setText(
                ""
        );

        radioTodos.setSelected(
                true
        );

        chkDisponibles.setSelected(
                false
        );

        List<Producto> ofertas =
                new ArrayList<>();

        for (
                Producto producto :
                productos
        ) {

            if (
                    producto.getStock() > 0
                            &&
                    producto.getStock() <= 5
            ) {

                ofertas.add(
                        producto
                );
            }
        }

        mostrarProductos(
                ofertas
        );

        lblMostrando.setText(
                "OFERTAS: "
                        + ofertas.size()
                        + " productos"
        );
    }

    // =========================================================
    // CUADRO DE LISTA DE PRODUCTOS / OFERTAS
    // =========================================================

    private void mostrarListaProductosDialogo(
            boolean soloOfertas
    ) {

        JDialog dialogo =
                new JDialog(
                        this,
                        soloOfertas
                                ? "Ofertas - TechStore"
                                : "Productos - TechStore",
                        true
                );

        dialogo.setSize(
                1250,
                700
        );

        dialogo.setLocationRelativeTo(
                this
        );

        JPanel principal =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        principal.setBackground(
                FONDO
        );

        principal.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel titulo =
                new JLabel(
                        soloOfertas
                                ? "OFERTAS DE TECHSTORE"
                                : "PRODUCTOS DE TECHSTORE",
                        SwingConstants.CENTER
                );

        titulo.setForeground(
                AZUL
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        principal.add(
                titulo,
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

        DefaultTableModel modelo =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return column == 0;
                    }

                    @Override
                    public Class<?> getColumnClass(
                            int column
                    ) {

                        switch (column) {

                            case 0:
                                return Boolean.class;

                            case 5:
                                return Integer.class;

                            default:
                                return String.class;
                        }
                    }
                };

        List<Producto> lista =
                new ArrayList<>(
                        productos
                );

        if (soloOfertas) {

            lista.removeIf(
                    producto ->
                            producto.getStock() <= 0
                                    ||
                            producto.getStock() > 5
            );
        }

        lista.sort(
                Comparator.comparing(
                        Producto::getCodigo
                )
        );

        for (
                Producto producto :
                lista
        ) {

            modelo.addRow(
                    new Object[]{
                            producto.getCodigo(),
                            producto.getNombre(),
                            producto.getCategoria(),
                            producto.getMarca(),
                            formatearPrecio(
                                    producto.getPrecio()
                            ),
                            producto.getStock(),
                            producto.getStock() > 0
                                    ? "Disponible"
                                    : "Agotado"
                    }
            );
        }

        JTable tabla =
                new JTable(
                        modelo
                );

        tabla.setRowHeight(
                34
        );

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        tabla.setBackground(
                new Color(
                        22,
                        26,
                        58
                )
        );

        tabla.setForeground(
                BLANCO
        );

        tabla.setGridColor(
                new Color(
                        65,
                        70,
                        110
                )
        );

        tabla.setSelectionBackground(
                MORADO
        );

        tabla.setSelectionForeground(
                Color.WHITE
        );

        tabla.setAutoCreateRowSorter(
                true
        );

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.getTableHeader()
                .setBackground(
                        new Color(
                                35,
                                40,
                                85
                        )
                );

        tabla.getTableHeader()
                .setForeground(
                        AZUL
                );

        tabla.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        tabla.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );

        DefaultTableCellRenderer centrado =
                new DefaultTableCellRenderer();

        centrado.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tabla.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        centrado
                );

        int[] anchos = {
                85,
                260,
                150,
                130,
                130,
                90,
                120
        };

        for (
                int i = 0;
                i < anchos.length;
                i++
        ) {

            tabla.getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            anchos[i]
                    );
        }

        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setBorder(
                new LineBorder(
                        new Color(
                                90,
                                70,
                                180
                        ),
                        1
                )
        );

        scroll.getViewport()
                .setBackground(
                        new Color(
                                22,
                                26,
                                58
                        )
                );

        principal.add(
                scroll,
                BorderLayout.CENTER
        );

        JLabel informacion =
                new JLabel(
                        (soloOfertas
                                ? "Ofertas encontradas: "
                                : "Productos encontrados: ")
                                + modelo.getRowCount()
                );

        informacion.setForeground(
                GRIS
        );

        informacion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JButton cerrar =
                crearBotonAccion(
                        "CERRAR",
                        MORADO
                );

        cerrar.addActionListener(
                e -> dialogo.dispose()
        );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botones.setOpaque(
                false
        );

        botones.add(
                cerrar
        );

        JPanel inferior =
                new JPanel(
                        new BorderLayout()
                );

        inferior.setOpaque(
                false
        );

        inferior.add(
                informacion,
                BorderLayout.WEST
        );

        inferior.add(
                botones,
                BorderLayout.EAST
        );

        principal.add(
                inferior,
                BorderLayout.SOUTH
        );

        dialogo.setContentPane(
                principal
        );

        dialogo.setVisible(
                true
        );
    }

    // =========================================================
    // AGREGAR AL CARRITO
    // =========================================================

    private void agregarCarrito(
            Producto producto
    ) {

        int cantidad =
                carrito.getOrDefault(
                        producto.getCodigo(),
                        0
                );

        if (
                cantidad >=
                        producto.getStock()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay más unidades disponibles.",
                    "Stock insuficiente",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        carrito.put(
                producto.getCodigo(),
                cantidad + 1
        );

        actualizarCarrito();


    }

    // =========================================================
    // ACTUALIZAR CARRITO
    // =========================================================

    private void actualizarCarrito() {

        int cantidad =
                0;

        for (
                Integer valor :
                carrito.values()
        ) {

            cantidad +=
                    valor;
        }

        lblCarrito.setText(
                "CARRITO ("
                        + cantidad
                        + ")"
        );

        actualizarIndicadoresCantidad();
    }

    // =========================================================
    // ACTUALIZAR INDICADORES DE CANTIDAD
    // =========================================================

    private void actualizarIndicadoresCantidad() {

        for (
                Map.Entry<String, JLabel> entrada :
                indicadoresCantidad.entrySet()
        ) {

            int cantidad =
                    carrito.getOrDefault(
                            entrada.getKey(),
                            0
                    );

            JLabel indicador =
                    entrada.getValue();

            indicador.setText(
                    String.valueOf(
                            cantidad
                    )
            );

            indicador.setVisible(
                    cantidad > 0
            );

            indicador.repaint();
        }
    }

    // =========================================================
    // CARRITO
    // =========================================================

    private void mostrarCarrito() {

        JDialog dialogo =
                new JDialog(
                        this,
                        "Carrito de compras - TechStore",
                        true
                );

        dialogo.setSize(900, 620);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(this);

        JPanel principal =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        principal.setBackground(FONDO);
        principal.setBorder(
                new EmptyBorder(22, 22, 22, 22)
        );

        JLabel titulo =
                new JLabel(
                        "🛒  MI CARRITO",
                        SwingConstants.CENTER
                );

        titulo.setForeground(AZUL);
        titulo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.BOLD,
                        28
                )
        );

        DefaultTableModel modelo =
                new DefaultTableModel(
                        new String[]{
                                "Seleccionar",
                                "Producto",
                                "Cantidad",
                                "Precio",
                                "Subtotal"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return column == 0;
                    }

                    @Override
                    public Class<?> getColumnClass(
                            int column
                    ) {

                        if (column == 0) {
                            return Boolean.class;
                        }

                        if (column == 2) {
                            return Integer.class;
                        }

                        return String.class;
                    }
                };

        for (
                Map.Entry<String, Integer> entrada :
                carrito.entrySet()
        ) {

            Producto producto =
                    buscarProducto(
                            entrada.getKey()
                    );

            if (producto == null) {
                continue;
            }

            int cantidad =
                    entrada.getValue();

            double subtotal =
                    producto.getPrecio()
                            * cantidad;

            modelo.addRow(
                    new Object[]{
                            Boolean.FALSE,
                            producto.getNombre(),
                            cantidad,
                            formatearPrecio(
                                    producto.getPrecio()
                            ),
                            formatearPrecio(
                                    subtotal
                            )
                    }
            );
        }

        JTable tabla =
                new JTable(modelo);

        tabla.setRowHeight(38);
        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tabla.setBackground(
                new Color(18, 21, 52)
        );

        tabla.setForeground(BLANCO);

        tabla.setGridColor(
                new Color(65, 70, 115)
        );

        tabla.setSelectionBackground(MORADO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);

        tabla.getTableHeader()
                .setBackground(
                        new Color(28, 32, 72)
                );

        tabla.getTableHeader()
                .setForeground(AZUL);

        tabla.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        DefaultTableCellRenderer centrado =
                new DefaultTableCellRenderer();

        centrado.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tabla.getColumnModel()
                .getColumn(2)
                .setCellRenderer(centrado);

        tabla.getColumnModel()
                .getColumn(3)
                .setCellRenderer(centrado);

        tabla.getColumnModel()
                .getColumn(4)
                .setCellRenderer(centrado);

        tabla.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        tabla.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(330);

        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(100);

        tabla.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        tabla.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(170);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBorder(
                new LineBorder(
                        new Color(95, 70, 190),
                        1,
                        true
                )
        );

        scroll.getViewport()
                .setBackground(
                        new Color(18, 21, 52)
                );

        JLabel totalLabel =
                new JLabel(
                        "TOTAL SELECCIONADO: $0"
                );

        totalLabel.setForeground(AZUL);
        totalLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        JLabel estado =
                new JLabel(
                        "0 seleccionado(s) de "
                                + modelo.getRowCount()
                );

        estado.setForeground(GRIS);
        estado.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JPanel info =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        info.setOpaque(false);
        info.add(totalLabel);
        info.add(estado);

        JPanel inferior =
                new JPanel(
                        new BorderLayout(
                                10,
                                4
                        )
                );

        inferior.setOpaque(false);
        inferior.add(
                info,
                BorderLayout.WEST
        );

        JButton vaciar =
                crearBotonAccion(
                        "VACIAR",
                        ROJO
                );

        JButton comprar =
                crearBotonAccion(
                        "COMPRAR",
                        VERDE
                );

        JButton cerrar =
                crearBotonAccion(
                        "CERRAR",
                        MORADO
                );

        comprar.setEnabled(false);

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botones.setOpaque(false);
        botones.add(vaciar);
        botones.add(comprar);
        botones.add(cerrar);

        inferior.add(
                botones,
                BorderLayout.EAST
        );

        Runnable actualizarSeleccion =
                () -> {

                    double totalSeleccionado = 0;
                    int seleccionados = 0;

                    for (
                            int fila = 0;
                            fila < modelo.getRowCount();
                            fila++
                    ) {

                        if (
                                !Boolean.TRUE.equals(
                                        modelo.getValueAt(
                                                fila,
                                                0
                                        )
                                )
                        ) {
                            continue;
                        }

                        seleccionados++;

                        String subtotalTexto =
                                modelo.getValueAt(
                                        fila,
                                        4
                                )
                                        .toString()
                                        .replace(
                                                "$",
                                                ""
                                        )
                                        .replace(
                                                ".",
                                                ""
                                        )
                                        .replace(
                                                ",",
                                                "."
                                        );

                        totalSeleccionado +=
                                Double.parseDouble(
                                        subtotalTexto
                                );
                    }

                    totalLabel.setText(
                            "TOTAL SELECCIONADO: "
                                    + formatearPrecio(
                                    totalSeleccionado
                            )
                    );

                    estado.setText(
                            seleccionados
                                    + " seleccionado(s) de "
                                    + modelo.getRowCount()
                    );

                    comprar.setEnabled(
                            seleccionados > 0
                    );
                };

        modelo.addTableModelListener(
                e -> actualizarSeleccion.run()
        );

        vaciar.addActionListener(
                e -> {

                    if (carrito.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                dialogo,
                                "El carrito ya está vacío.",
                                "TechStore",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        return;
                    }

                    carrito.clear();
                    actualizarCarrito();
                    dialogo.dispose();
                    mostrarCarrito();
                }
        );

        comprar.addActionListener(
                e -> {

                    List<String> seleccionados =
                            new ArrayList<>();

                    double totalSeleccionado = 0;

                    for (
                            int fila = 0;
                            fila < modelo.getRowCount();
                            fila++
                    ) {

                        if (
                                !Boolean.TRUE.equals(
                                        modelo.getValueAt(
                                                fila,
                                                0
                                        )
                                )
                        ) {
                            continue;
                        }

                        seleccionados.add(
                                modelo.getValueAt(
                                        fila,
                                        1
                                ).toString()
                        );

                        String subtotalTexto =
                                modelo.getValueAt(
                                        fila,
                                        4
                                )
                                        .toString()
                                        .replace(
                                                "$",
                                                ""
                                        )
                                        .replace(
                                                ".",
                                                ""
                                        )
                                        .replace(
                                                ",",
                                                "."
                                        );

                        totalSeleccionado +=
                                Double.parseDouble(
                                        subtotalTexto
                                );
                    }

                    if (
                            seleccionados.isEmpty()
                    ) {

                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Seleccione al menos un producto.",
                                "TechStore",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                    // =================================================
                    // VENTANA DE MEDIO DE PAGO
                    // =================================================

                    JDialog pagoDialog =
                            new JDialog(
                                    dialogo,
                                    "Método de pago - TechStore",
                                    true
                            );

                    pagoDialog.setSize(
                            470,
                            380
                    );

                    pagoDialog.setResizable(
                            false
                    );

                    pagoDialog.setLocationRelativeTo(
                            dialogo
                    );

                    JPanel panelPago =
                            new JPanel() {

                                @Override
                                protected void paintComponent(
                                        Graphics g
                                ) {

                                    super.paintComponent(g);

                                    Graphics2D g2 =
                                            (Graphics2D) g.create();

                                    g2.setRenderingHint(
                                            RenderingHints.KEY_ANTIALIASING,
                                            RenderingHints.VALUE_ANTIALIAS_ON
                                    );

                                    int w =
                                            getWidth();

                                    int h =
                                            getHeight();

                                    GradientPaint fondoPago =
                                            new GradientPaint(
                                                    0,
                                                    0,
                                                    new Color(
                                                            7,
                                                            10,
                                                            30
                                                    ),
                                                    w,
                                                    h,
                                                    new Color(
                                                            49,
                                                            10,
                                                            65
                                                    )
                                            );

                                    g2.setPaint(
                                            fondoPago
                                    );

                                    g2.fillRoundRect(
                                            0,
                                            0,
                                            w,
                                            h,
                                            24,
                                            24
                                    );

                                    g2.setPaint(
                                            new GradientPaint(
                                                    0,
                                                    0,
                                                    AZUL,
                                                    w,
                                                    0,
                                                    ROSA
                                            )
                                    );

                                    g2.setStroke(
                                            new BasicStroke(
                                                    1.5f
                                            )
                                    );

                                    g2.drawRoundRect(
                                            1,
                                            1,
                                            w - 3,
                                            h - 3,
                                            24,
                                            24
                                    );

                                    g2.fillRect(
                                            25,
                                            70,
                                            w - 50,
                                            1
                                    );

                                    g2.dispose();
                                }
                            };

                    panelPago.setLayout(
                            new BorderLayout(
                                    12,
                                    12
                            )
                    );

                    panelPago.setBorder(
                            new EmptyBorder(
                                    20,
                                    24,
                                    20,
                                    24
                            )
                    );

                    JLabel tituloPago =
                            new JLabel(
                                    "💳  SELECCIONA TU FORMA DE PAGO",
                                    SwingConstants.CENTER
                            );

                    tituloPago.setForeground(
                            AZUL
                    );

                    tituloPago.setFont(
                            new Font(
                                    "Segoe UI Emoji",
                                    Font.BOLD,
                                    19
                            )
                    );

                    JLabel totalPago =
                            new JLabel(
                                    "Total seleccionado: "
                                            + formatearPrecio(
                                            totalSeleccionado
                                    ),
                                    SwingConstants.CENTER
                            );

                    totalPago.setForeground(
                            ROSA
                    );

                    totalPago.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    14
                            )
                    );

                    JPanel opcionesPago =
                            new JPanel();

                    opcionesPago.setOpaque(
                            false
                    );

                    opcionesPago.setLayout(
                            new BoxLayout(
                                    opcionesPago,
                                    BoxLayout.Y_AXIS
                            )
                    );

                    JRadioButton credito =
                            new JRadioButton(
                                    "Tarjeta de crédito"
                            );

                    JRadioButton debito =
                            new JRadioButton(
                                    "Tarjeta de débito"
                            );

                    JRadioButton pse =
                            new JRadioButton(
                                    "PSE"
                            );

                    JRadioButton[] radios = {
                            credito,
                            debito,
                            pse
                    };

                    String[] iconos = {
                            "💳",
                            "💠",
                            "🏦"
                    };

                    Color[] colores = {
                            new Color(
                                    0,
                                    210,
                                    255
                            ),
                            new Color(
                                    130,
                                    90,
                                    255
                            ),
                            new Color(
                                    0,
                                    220,
                                    130
                            )
                    };

                    ButtonGroup grupoPago =
                            new ButtonGroup();

                    for (
                            int i = 0;
                            i < radios.length;
                            i++
                    ) {

                        JRadioButton radio =
                                radios[i];

                        radio.setOpaque(
                                false
                        );

                        radio.setForeground(
                                BLANCO
                        );

                        radio.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.BOLD,
                                        14
                                )
                        );

                        radio.setFocusPainted(
                                false
                        );

                        radio.setCursor(
                                new Cursor(
                                        Cursor.HAND_CURSOR
                                )
                        );

                        grupoPago.add(
                                radio
                        );

                        JPanel tarjetaPago =
                                new JPanel(
                                        new BorderLayout(
                                                10,
                                                0
                                        )
                                ) {

                                    @Override
                                    protected void paintComponent(
                                            Graphics g
                                    ) {

                                        super.paintComponent(g);

                                        Graphics2D g2 =
                                                (Graphics2D) g.create();

                                        g2.setRenderingHint(
                                                RenderingHints.KEY_ANTIALIASING,
                                                RenderingHints.VALUE_ANTIALIAS_ON
                                        );

                                        g2.setColor(
                                                new Color(
                                                        18,
                                                        24,
                                                        55,
                                                        235
                                                )
                                        );

                                        g2.fillRoundRect(
                                                0,
                                                0,
                                                getWidth(),
                                                getHeight(),
                                                16,
                                                16
                                        );

                                        g2.setColor(
                                                new Color(
                                                        75,
                                                        85,
                                                        150,
                                                        150
                                                )
                                        );

                                        g2.drawRoundRect(
                                                0,
                                                0,
                                                getWidth() - 1,
                                                getHeight() - 1,
                                                16,
                                                16
                                        );

                                        g2.dispose();
                                    }
                                };

                        tarjetaPago.setOpaque(
                                false
                        );

                        tarjetaPago.setBorder(
                                new EmptyBorder(
                                        7,
                                        10,
                                        7,
                                        10
                                )
                        );

                        JLabel iconoPago =
                                new JLabel(
                                        iconos[i]
                                );

                        iconoPago.setForeground(
                                colores[i]
                        );

                        iconoPago.setFont(
                                new Font(
                                        "Segoe UI Emoji",
                                        Font.PLAIN,
                                        25
                                )
                        );

                        tarjetaPago.add(
                                iconoPago,
                                BorderLayout.WEST
                        );

                        tarjetaPago.add(
                                radio,
                                BorderLayout.CENTER
                        );

                        final JRadioButton radioFinal =
                                radio;

                        tarjetaPago.addMouseListener(
                                new MouseAdapter() {

                                    @Override
                                    public void mouseClicked(
                                            MouseEvent e
                                    ) {

                                        radioFinal
                                                .setSelected(
                                                        true
                                                );
                                    }
                                }
                        );

                        opcionesPago.add(
                                tarjetaPago
                        );

                        opcionesPago.add(
                                Box.createVerticalStrut(
                                        8
                                )
                        );
                    }

                    credito.setSelected(
                            true
                    );

                    panelPago.add(
                            tituloPago,
                            BorderLayout.NORTH
                    );

                    JPanel centroPago =
                            new JPanel(
                                    new BorderLayout(
                                            0,
                                            8
                                    )
                            );

                    centroPago.setOpaque(
                            false
                    );

                    centroPago.add(
                            totalPago,
                            BorderLayout.NORTH
                    );

                    centroPago.add(
                            opcionesPago,
                            BorderLayout.CENTER
                    );

                    panelPago.add(
                            centroPago,
                            BorderLayout.CENTER
                    );

                    JButton continuarPago =
                            crearBotonAccion(
                                    "CONTINUAR",
                                    AZUL_BOTON
                            );

                    JButton cancelarPago =
                            crearBotonAccion(
                                    "CANCELAR",
                                    MORADO
                            );

                    JPanel botonesPago =
                            new JPanel(
                                    new FlowLayout(
                                            FlowLayout.CENTER,
                                            10,
                                            0
                                    )
                            );

                    botonesPago.setOpaque(
                            false
                    );

                    botonesPago.add(
                            continuarPago
                    );

                    botonesPago.add(
                            cancelarPago
                    );

                    final String[] metodoSeleccionado = {
                            null
                    };

                    continuarPago.addActionListener(
                            evento -> {

                                if (
                                        credito.isSelected()
                                ) {

                                    metodoSeleccionado[0] =
                                            "Tarjeta de crédito";

                                } else if (
                                        debito.isSelected()
                                ) {

                                    metodoSeleccionado[0] =
                                            "Tarjeta de débito";

                                } else {

                                    metodoSeleccionado[0] =
                                            "PSE";
                                }

                                pagoDialog.dispose();
                            }
                    );

                    cancelarPago.addActionListener(
                            evento -> {

                                metodoSeleccionado[0] =
                                        null;

                                pagoDialog.dispose();
                            }
                    );

                    panelPago.add(
                            botonesPago,
                            BorderLayout.SOUTH
                    );

                    pagoDialog.setContentPane(
                            panelPago
                    );

                    pagoDialog.getRootPane()
                            .setDefaultButton(
                                    continuarPago
                            );

                    pagoDialog.setVisible(
                            true
                    );

                    String metodo =
                            metodoSeleccionado[0];

                    if (
                            metodo == null
                    ) {

                        return;
                    }

                    StringBuilder resumenCompra =
                            new StringBuilder();

                    resumenCompra.append(
                            "Productos seleccionados:\n\n"
                    );

                    int numeroCompra =
                            1;

                    for (
                            String nombre :
                            seleccionados
                    ) {

                        resumenCompra.append(
                                numeroCompra
                        );

                        resumenCompra.append(
                                ". "
                        );

                        resumenCompra.append(
                                nombre
                        );

                        resumenCompra.append(
                                "\n"
                        );

                        numeroCompra++;
                    }

                    resumenCompra.append(
                            "\nMétodo de pago: "
                    );

                    resumenCompra.append(
                            metodo
                    );

                    resumenCompra.append(
                            "\nTotal: "
                    );

                    resumenCompra.append(
                            formatearPrecio(
                                    totalSeleccionado
                            )
                    );

                    JOptionPane.showMessageDialog(
                            dialogo,
                            resumenCompra.toString(),
                            "Confirmar compra - TechStore",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );

        cerrar.addActionListener(
                e -> dialogo.dispose()
        );

        principal.add(
                titulo,
                BorderLayout.NORTH
        );

        principal.add(
                scroll,
                BorderLayout.CENTER
        );

        principal.add(
                inferior,
                BorderLayout.SOUTH
        );

        dialogo.setContentPane(
                principal
        );

        actualizarSeleccion.run();
        dialogo.setVisible(true);
    }

    // =========================================================
    // BUSCAR PRODUCTO
    // =========================================================

    private Producto buscarProducto(
            String codigo
    ) {

        for (
                Producto producto :
                productos
        ) {

            if (
                    producto.getCodigo()
                            .equals(
                                    codigo
                            )
            ) {

                return producto;
            }
        }

        return null;
    }

    // =========================================================
    // DETALLE
    // =========================================================

    private void mostrarDetalle(
            Producto producto
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        JLabel nombre =
                new JLabel(
                        producto.getNombre()
                );

        nombre.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        nombre.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel precio =
                new JLabel(
                        formatearPrecio(
                                producto.getPrecio()
                        )
                );

        precio.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        precio.setForeground(
                AZUL_BOTON
        );

        precio.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                nombre
        );

        panel.add(
                Box.createVerticalStrut(
                        10
                )
        );

        panel.add(
                new JLabel(
                        "Código: "
                                + producto.getCodigo()
                )
        );

        panel.add(
                new JLabel(
                        "Categoría: "
                                + producto.getCategoria()
                )
        );

        panel.add(
                new JLabel(
                        "Marca: "
                                + producto.getMarca()
                )
        );

        panel.add(
                Box.createVerticalStrut(
                        8
                )
        );

        panel.add(
                precio
        );

        panel.add(
                new JLabel(
                        "Stock disponible: "
                                + producto.getStock()
                )
        );

        JOptionPane.showMessageDialog(
                this,
                panel,
                "Detalle del producto",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // CLIENTES
    // =========================================================

    private void mostrarCliente() {

        JDialog dialogo =
                new JDialog(
                        this,
                        "Clientes - TechStore",
                        true
                );

        dialogo.setSize(
                1250,
                700
        );

        dialogo.setLocationRelativeTo(this);

        JPanel principal =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        principal.setBackground(FONDO);
        principal.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        JLabel titulo =
                new JLabel(
                        "CLIENTES DE TECHSTORE",
                        SwingConstants.CENTER
                );

        titulo.setForeground(AZUL);
        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        principal.add(
                titulo,
                BorderLayout.NORTH
        );

        String[] columnas = {
                "ID",
                "Documento",
                "Nombre",
                "Correo",
                "Teléfono",
                "Sexo",
                "Fecha nacimiento",
                "Departamento",
                "Ciudad",
                "Dirección"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(
                        columnas,
                        0
                ) {
                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        cargarClientesEnTabla(modelo);

        JTable tabla =
                new JTable(modelo);

        tabla.setRowHeight(34);
        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );
        tabla.setBackground(
                new Color(22, 26, 58)
        );
        tabla.setForeground(BLANCO);
        tabla.setGridColor(
                new Color(65, 70, 110)
        );
        tabla.setSelectionBackground(MORADO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setAutoCreateRowSorter(true);
        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.getTableHeader().setBackground(
                new Color(35, 40, 85)
        );
        tabla.getTableHeader().setForeground(AZUL);
        tabla.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );
        tabla.getTableHeader().setPreferredSize(
                new Dimension(0, 40)
        );

        DefaultTableCellRenderer centrado =
                new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tabla.getColumnModel().getColumn(0)
                .setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(1)
                .setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(4)
                .setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(5)
                .setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(6)
                .setCellRenderer(centrado);

        int[] anchos = {
                55, 110, 190, 220, 120,
                100, 130, 170, 150, 200
        };

        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(anchos[i]);
        }

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBorder(
                new LineBorder(
                        new Color(90, 70, 180),
                        1
                )
        );

        scroll.getViewport().setBackground(
                new Color(22, 26, 58)
        );

        principal.add(
                scroll,
                BorderLayout.CENTER
        );

        JLabel informacion =
                new JLabel(
                        "Clientes registrados: "
                                + modelo.getRowCount()
                );

        informacion.setForeground(GRIS);
        informacion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JPanel inferior =
                new JPanel(
                        new BorderLayout()
                );
        inferior.setOpaque(false);
        inferior.add(
                informacion,
                BorderLayout.WEST
        );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );
        botones.setOpaque(false);

        JButton registrar =
                crearBotonAccion(
                        "REGISTRAR",
                        AZUL_BOTON
                );

        JButton editar =
                crearBotonAccion(
                        "EDITAR",
                        NARANJA
                );

        JButton eliminar =
                crearBotonAccion(
                        "ELIMINAR",
                        ROJO
                );

        JButton actualizar =
                crearBotonAccion(
                        "ACTUALIZAR",
                        AZUL_BOTON
                );

        JButton cerrar =
                crearBotonAccion(
                        "CERRAR",
                        MORADO
                );

        registrar.addActionListener(
                e -> mostrarFormularioNuevoCliente(
                        dialogo,
                        tabla,
                        modelo,
                        informacion
                )
        );

        editar.addActionListener(
                e -> {

                    int filaVista =
                            tabla.getSelectedRow();

                    if (filaVista < 0) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Seleccione un cliente de la tabla.",
                                "Editar cliente",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    int filaModelo =
                            tabla.convertRowIndexToModel(
                                    filaVista
                            );

                    int id =
                            Integer.parseInt(
                                    modelo.getValueAt(
                                            filaModelo,
                                            0
                                    ).toString()
                            );

                    Cliente cliente =
                            buscarCliente(id);

                    if (cliente == null) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "No se encontró el cliente seleccionado.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    mostrarFormularioEditarCliente(
                            dialogo,
                            cliente,
                            modelo,
                            informacion
                    );
                }
        );

        eliminar.addActionListener(
                e -> {

                    int filaVista =
                            tabla.getSelectedRow();

                    if (filaVista < 0) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Seleccione un cliente para eliminar.",
                                "Eliminar cliente",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    int filaModelo =
                            tabla.convertRowIndexToModel(
                                    filaVista
                            );

                    int id =
                            Integer.parseInt(
                                    modelo.getValueAt(
                                            filaModelo,
                                            0
                                    ).toString()
                            );

                    String nombre =
                            modelo.getValueAt(
                                    filaModelo,
                                    2
                            ).toString();

                    int respuesta =
                            JOptionPane.showConfirmDialog(
                                    dialogo,
                                    "¿Está seguro de eliminar este cliente?\n\n"
                                            + nombre,
                                    "Confirmar eliminación",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE
                            );

                    if (respuesta != JOptionPane.YES_OPTION) {
                        return;
                    }

                    boolean eliminado =
                            clienteController
                                    .eliminarCliente(id);

                    if (eliminado) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Cliente eliminado correctamente.",
                                "TechStore",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        refrescarTablaClientes(
                                modelo,
                                informacion
                        );

                    } else {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "No se pudo eliminar el cliente.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );

        actualizar.addActionListener(
                e -> refrescarTablaClientes(
                        modelo,
                        informacion
                )
        );

        cerrar.addActionListener(
                e -> dialogo.dispose()
        );

        botones.add(registrar);
        botones.add(editar);
        botones.add(eliminar);
        botones.add(actualizar);
        botones.add(cerrar);

        inferior.add(
                botones,
                BorderLayout.EAST
        );

        principal.add(
                inferior,
                BorderLayout.SOUTH
        );

        dialogo.setContentPane(principal);
        dialogo.setVisible(true);
    }

    // =========================================================
    // CARGAR CLIENTES EN TABLA
    // =========================================================

    private void cargarClientesEnTabla(
            DefaultTableModel modelo
    ) {

        modelo.setRowCount(0);

        List<Cliente> clientes =
                clienteController.listarClientes();

        for (Cliente cliente : clientes) {
            modelo.addRow(
                    new Object[]{
                            cliente.getId(),
                            cliente.getDocumento(),
                            cliente.getNombre(),
                            cliente.getCorreo(),
                            cliente.getTelefono(),
                            cliente.getSexo(),
                            cliente.getFechaNacimiento(),
                            cliente.getDepartamento(),
                            cliente.getCiudad(),
                            cliente.getDireccion()
                    }
            );
        }
    }

    // =========================================================
    // BUSCAR CLIENTE
    // =========================================================

    private Cliente buscarCliente(int id) {

        List<Cliente> clientes =
                clienteController.listarClientes();

        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    // =========================================================
    // REFRESCAR TABLA DE CLIENTES
    // =========================================================

    private void refrescarTablaClientes(
            DefaultTableModel modelo,
            JLabel informacion
    ) {

        cargarClientesEnTabla(modelo);

        informacion.setText(
                "Clientes registrados: "
                        + modelo.getRowCount()
        );
    }

    // =========================================================
    // NUEVO CLIENTE
    // =========================================================

    private void mostrarFormularioNuevoCliente(
            JDialog padre,
            JTable tabla,
            DefaultTableModel modelo,
            JLabel informacion
    ) {

        mostrarFormularioCliente(
                padre,
                null,
                modelo,
                informacion,
                false
        );
    }

    // =========================================================
    // EDITAR CLIENTE
    // =========================================================

    private void mostrarFormularioEditarCliente(
            JDialog padre,
            Cliente cliente,
            DefaultTableModel modelo,
            JLabel informacion
    ) {

        mostrarFormularioCliente(
                padre,
                cliente,
                modelo,
                informacion,
                true
        );
    }

    // =========================================================
    // FORMULARIO REGISTRAR / EDITAR CLIENTE
    // =========================================================

    private void mostrarFormularioCliente(
            JDialog padre,
            Cliente cliente,
            DefaultTableModel modelo,
            JLabel informacion,
            boolean editar
    ) {

        JDialog dialogo =
                new JDialog(
                        padre,
                        editar
                                ? "Editar cliente"
                                : "Registrar cliente",
                        true
                );

        dialogo.setSize(
                650,
                720
        );

        dialogo.setLocationRelativeTo(padre);

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setBackground(FONDO);
        principal.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        JLabel titulo =
                new JLabel(
                        editar
                                ? "EDITAR CLIENTE"
                                : "REGISTRAR CLIENTE",
                        SwingConstants.CENTER
                );

        titulo.setForeground(AZUL);
        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        principal.add(
                titulo,
                BorderLayout.NORTH
        );

        JPanel formulario =
                new JPanel(
                        new GridBagLayout()
                );

        formulario.setBackground(FONDO);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(7, 7, 7, 7);
        gbc.fill =
                GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JTextField txtDocumento =
                crearCampoFormulario();

        JTextField txtNombre =
                crearCampoFormulario();

        JTextField txtCorreo =
                crearCampoFormulario();

        JTextField txtTelefono =
                crearCampoFormulario();

        JTextField txtFechaNacimiento =
                crearCampoFormulario();

        JTextField txtDireccion =
                crearCampoFormulario();

        JComboBox<String> comboDepartamento =
                new JComboBox<>(
                        obtenerDepartamentos()
                );

        JComboBox<String> comboCiudad =
                new JComboBox<>();

        configurarComboFormulario(
                comboDepartamento
        );

        configurarComboFormulario(
                comboCiudad
        );

        ButtonGroup grupoSexo =
                new ButtonGroup();

        JRadioButton radioMasculino =
                crearRadioSexo("Masculino");

        JRadioButton radioFemenino =
                crearRadioSexo("Femenino");

        JRadioButton radioOtro =
                crearRadioSexo("Otro");

        JRadioButton radioPrefieroNo =
                crearRadioSexo("Prefiero no decir");

        grupoSexo.add(radioMasculino);
        grupoSexo.add(radioFemenino);
        grupoSexo.add(radioOtro);
        grupoSexo.add(radioPrefieroNo);

        if (editar && cliente != null) {

            txtDocumento.setText(
                    cliente.getDocumento()
            );

            txtNombre.setText(
                    cliente.getNombre()
            );

            txtCorreo.setText(
                    cliente.getCorreo()
            );

            txtTelefono.setText(
                    cliente.getTelefono()
            );

            txtFechaNacimiento.setText(
                    cliente.getFechaNacimiento()
            );

            txtDireccion.setText(
                    cliente.getDireccion()
            );

            comboDepartamento.setSelectedItem(
                    cliente.getDepartamento()
            );

            seleccionarSexo(
                    cliente.getSexo(),
                    radioMasculino,
                    radioFemenino,
                    radioOtro,
                    radioPrefieroNo
            );
        }

        cargarCiudades(
                comboCiudad,
                String.valueOf(
                        comboDepartamento
                                .getSelectedItem()
                )
        );

        if (editar && cliente != null) {
            comboCiudad.setSelectedItem(
                    cliente.getCiudad()
            );
        }

        comboDepartamento.addActionListener(
                e -> cargarCiudades(
                        comboCiudad,
                        String.valueOf(
                                comboDepartamento
                                        .getSelectedItem()
                        )
                )
        );

        int fila = 0;

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Documento *",
                txtDocumento
        );

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Nombre completo *",
                txtNombre
        );

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Correo electrónico *",
                txtCorreo
        );

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Teléfono *",
                txtTelefono
        );

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Fecha de nacimiento",
                txtFechaNacimiento
        );

        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0.30;

        formulario.add(
                crearEtiquetaFormulario(
                        "Sexo *"
                ),
                gbc
        );

        JPanel panelSexo =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                0
                        )
                );

        panelSexo.setOpaque(false);
        panelSexo.add(radioMasculino);
        panelSexo.add(radioFemenino);
        panelSexo.add(radioOtro);
        panelSexo.add(radioPrefieroNo);

        gbc.gridx = 1;
        gbc.weightx = 0.70;

        formulario.add(
                panelSexo,
                gbc
        );

        fila++;

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Departamento *",
                comboDepartamento
        );

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Ciudad *",
                comboCiudad
        );

        agregarCampoFormulario(
                formulario,
                gbc,
                fila++,
                "Dirección",
                txtDireccion
        );

        JScrollPane scrollFormulario =
                new JScrollPane(
                        formulario
                );

        scrollFormulario.setBorder(null);
        scrollFormulario.getViewport()
                .setBackground(FONDO);

        principal.add(
                scrollFormulario,
                BorderLayout.CENTER
        );

        JButton guardar =
                crearBotonAccion(
                        editar
                                ? "GUARDAR CAMBIOS"
                                : "GUARDAR CLIENTE",
                        AZUL_BOTON
                );

        JButton cancelar =
                crearBotonAccion(
                        "CANCELAR",
                        MORADO
                );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        botones.setOpaque(false);
        botones.add(guardar);
        botones.add(cancelar);

        principal.add(
                botones,
                BorderLayout.SOUTH
        );

        guardar.addActionListener(
                e -> {

                    String documento =
                            txtDocumento.getText().trim();

                    String nombre =
                            txtNombre.getText().trim();

                    String correo =
                            txtCorreo.getText().trim();

                    String telefono =
                            txtTelefono.getText().trim();

                    String fechaNacimiento =
                            txtFechaNacimiento.getText().trim();

                    String direccion =
                            txtDireccion.getText().trim();

                    String departamento =
                            String.valueOf(
                                    comboDepartamento
                                            .getSelectedItem()
                            );

                    String ciudad =
                            String.valueOf(
                                    comboCiudad
                                            .getSelectedItem()
                            );

                    String sexo =
                            obtenerSexo(
                                    radioMasculino,
                                    radioFemenino,
                                    radioOtro,
                                    radioPrefieroNo
                            );

                    if (
                            documento.isEmpty()
                                    || nombre.isEmpty()
                                    || correo.isEmpty()
                                    || telefono.isEmpty()
                    ) {

                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Complete los campos obligatorios (*).",
                                "Datos incompletos",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    if (sexo == null) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Seleccione una opción de sexo.",
                                "Datos incompletos",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    if (
                            "Seleccione...".equals(
                                    departamento
                            )
                                    || "Seleccione...".equals(
                                    ciudad
                            )
                    ) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Seleccione departamento y ciudad.",
                                "Datos incompletos",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    if (!correo.contains("@")) {
                        JOptionPane.showMessageDialog(
                                dialogo,
                                "Ingrese un correo electrónico válido.",
                                "Correo inválido",
                                JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    boolean resultado;

                    if (editar && cliente != null) {

                        resultado =
                                clienteController
                                        .actualizarCliente(
                                                cliente.getId(),
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

                    } else {

                        resultado =
                                clienteController
                                        .registrarCliente(
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
                    }

                    if (resultado) {

                        JOptionPane.showMessageDialog(
                                dialogo,
                                editar
                                        ? "Cliente actualizado correctamente."
                                        : "Cliente registrado correctamente.",
                                "TechStore",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        refrescarTablaClientes(
                                modelo,
                                informacion
                        );

                        dialogo.dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                dialogo,
                                editar
                                        ? "No se pudo actualizar el cliente."
                                        : "No se pudo registrar el cliente.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );

        cancelar.addActionListener(
                e -> dialogo.dispose()
        );

        dialogo.setContentPane(principal);
        dialogo.setVisible(true);
    }

    // =========================================================
    // AGREGAR CAMPO DEL FORMULARIO
    // =========================================================

    private void agregarCampoFormulario(
            JPanel panel,
            GridBagConstraints gbc,
            int fila,
            String etiqueta,
            JComponent componente
    ) {

        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0.30;

        panel.add(
                crearEtiquetaFormulario(etiqueta),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.70;

        panel.add(
                componente,
                gbc
        );
    }

    // =========================================================
    // RADIO DE SEXO
    // =========================================================

    private JRadioButton crearRadioSexo(
            String texto
    ) {

        JRadioButton radio =
                new JRadioButton(texto);

        radio.setForeground(BLANCO);
        radio.setBackground(FONDO);
        radio.setFocusPainted(false);
        radio.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        return radio;
    }

    private String obtenerSexo(
            JRadioButton masculino,
            JRadioButton femenino,
            JRadioButton otro,
            JRadioButton prefieroNo
    ) {

        if (masculino.isSelected()) {
            return "Masculino";
        }

        if (femenino.isSelected()) {
            return "Femenino";
        }

        if (otro.isSelected()) {
            return "Otro";
        }

        if (prefieroNo.isSelected()) {
            return "Prefiero no decir";
        }

        return null;
    }

    private void seleccionarSexo(
            String sexo,
            JRadioButton masculino,
            JRadioButton femenino,
            JRadioButton otro,
            JRadioButton prefieroNo
    ) {

        if ("Masculino".equalsIgnoreCase(sexo)) {
            masculino.setSelected(true);
        } else if ("Femenino".equalsIgnoreCase(sexo)) {
            femenino.setSelected(true);
        } else if ("Otro".equalsIgnoreCase(sexo)) {
            otro.setSelected(true);
        } else if ("Prefiero no decir".equalsIgnoreCase(sexo)) {
            prefieroNo.setSelected(true);
        }
    }

    // =========================================================
    // DEPARTAMENTOS DE COLOMBIA
    // =========================================================

    private String[] obtenerDepartamentos() {

        return new String[]{
                "Seleccione...",
                "Amazonas",
                "Antioquia",
                "Arauca",
                "Atlántico",
                "Bolívar",
                "Boyacá",
                "Caldas",
                "Caquetá",
                "Casanare",
                "Cauca",
                "Cesar",
                "Chocó",
                "Córdoba",
                "Cundinamarca",
                "Guainía",
                "Guaviare",
                "Huila",
                "La Guajira",
                "Magdalena",
                "Meta",
                "Nariño",
                "Norte de Santander",
                "Putumayo",
                "Quindío",
                "Risaralda",
                "San Andrés y Providencia",
                "Santander",
                "Sucre",
                "Tolima",
                "Valle del Cauca",
                "Vaupés",
                "Vichada",
                "Bogotá D.C."
        };
    }

    // =========================================================
    // CIUDADES / CAPITALES POR DEPARTAMENTO
    // =========================================================

    private void cargarCiudades(
            JComboBox<String> comboCiudad,
            String departamento
    ) {

        comboCiudad.removeAllItems();
        comboCiudad.addItem("Seleccione...");

        String[] ciudades;

        switch (departamento) {

            case "Amazonas" ->
                    ciudades = new String[]{"Leticia"};

            case "Antioquia" ->
                    ciudades = new String[]{"Medellín"};

            case "Arauca" ->
                    ciudades = new String[]{"Arauca"};

            case "Atlántico" ->
                    ciudades = new String[]{"Barranquilla"};

            case "Bolívar" ->
                    ciudades = new String[]{"Cartagena de Indias"};

            case "Boyacá" ->
                    ciudades = new String[]{"Tunja"};

            case "Caldas" ->
                    ciudades = new String[]{"Manizales"};

            case "Caquetá" ->
                    ciudades = new String[]{"Florencia"};

            case "Casanare" ->
                    ciudades = new String[]{"Yopal"};

            case "Cauca" ->
                    ciudades = new String[]{"Popayán"};

            case "Cesar" ->
                    ciudades = new String[]{"Valledupar"};

            case "Chocó" ->
                    ciudades = new String[]{"Quibdó"};

            case "Córdoba" ->
                    ciudades = new String[]{"Montería"};

            case "Cundinamarca" ->
                    ciudades = new String[]{"Bogotá D.C."};

            case "Guainía" ->
                    ciudades = new String[]{"Inírida"};

            case "Guaviare" ->
                    ciudades = new String[]{"San José del Guaviare"};

            case "Huila" ->
                    ciudades = new String[]{"Neiva"};

            case "La Guajira" ->
                    ciudades = new String[]{"Riohacha"};

            case "Magdalena" ->
                    ciudades = new String[]{"Santa Marta"};

            case "Meta" ->
                    ciudades = new String[]{"Villavicencio"};

            case "Nariño" ->
                    ciudades = new String[]{"Pasto"};

            case "Norte de Santander" ->
                    ciudades = new String[]{"Cúcuta"};

            case "Putumayo" ->
                    ciudades = new String[]{"Mocoa"};

            case "Quindío" ->
                    ciudades = new String[]{"Armenia"};

            case "Risaralda" ->
                    ciudades = new String[]{"Pereira"};

            case "San Andrés y Providencia" ->
                    ciudades = new String[]{"San Andrés"};

            case "Santander" ->
                    ciudades = new String[]{"Bucaramanga"};

            case "Sucre" ->
                    ciudades = new String[]{"Sincelejo"};

            case "Tolima" ->
                    ciudades = new String[]{"Ibagué"};

            case "Valle del Cauca" ->
                    ciudades = new String[]{"Cali"};

            case "Vaupés" ->
                    ciudades = new String[]{"Mitú"};

            case "Vichada" ->
                    ciudades = new String[]{"Puerto Carreño"};

            case "Bogotá D.C." ->
                    ciudades = new String[]{"Bogotá D.C."};

            default ->
                    ciudades = new String[0];
        }

        for (String ciudad : ciudades) {
            comboCiudad.addItem(ciudad);
        }
    }

    // =========================================================
    // ESTILO DE COMBOS DEL FORMULARIO
    // =========================================================

    private void configurarComboFormulario(
            JComboBox<String> combo
    ) {

        combo.setPreferredSize(
                new Dimension(
                        300,
                        38
                )
        );

        /*
         * Fondo claro con texto oscuro para que
         * Departamento y Ciudad se lean claramente.
         */
        combo.setBackground(
                Color.WHITE
        );

        combo.setForeground(
                new Color(
                        25,
                        25,
                        35
                )
        );

        combo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        /*
         * Renderer para que tanto el elemento seleccionado
         * como las opciones desplegadas tengan texto visible.
         */
        combo.setRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component getListCellRendererComponent(
                            JList<?> lista,
                            Object valor,
                            int indice,
                            boolean seleccionado,
                            boolean enfocado
                    ) {

                        JLabel etiqueta =
                                (JLabel) super.getListCellRendererComponent(
                                        lista,
                                        valor,
                                        indice,
                                        seleccionado,
                                        enfocado
                                );

                        etiqueta.setOpaque(
                                true
                        );

                        if (seleccionado) {

                            etiqueta.setBackground(
                                    new Color(
                                            220,
                                            230,
                                            255
                                    )
                            );

                            etiqueta.setForeground(
                                    new Color(
                                            10,
                                            60,
                                            120
                                    )
                            );

                        } else {

                            etiqueta.setBackground(
                                    Color.WHITE
                            );

                            etiqueta.setForeground(
                                    new Color(
                                            25,
                                            25,
                                            35
                                    )
                            );
                        }

                        etiqueta.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.PLAIN,
                                        13
                                )
                        );

                        return etiqueta;
                    }
                }
        );

        combo.setBorder(
                new LineBorder(
                        new Color(
                                75,
                                85,
                                160
                        ),
                        1
                )
        );
    }

    // =========================================================
    // PANEL FORMULARIO CLIENTE
    // =========================================================

    private JPanel crearPanelFormularioCliente() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(FONDO);

        panel.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        return panel;
    }

    // =========================================================
    // ETIQUETA FORMULARIO
    // =========================================================

    private JLabel crearEtiquetaFormulario(
            String texto
    ) {

        JLabel label =
                new JLabel(texto);

        label.setForeground(BLANCO);
        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        label.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        label.setVerticalAlignment(
                SwingConstants.CENTER
        );

        return label;
    }

    // =========================================================
    // CAMPO FORMULARIO
    // =========================================================

    private JTextField crearCampoFormulario() {

        JTextField campo =
                new JTextField();

        campo.setPreferredSize(
                new Dimension(
                        300,
                        38
                )
        );

        campo.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        38
                )
        );

        campo.setBackground(
                new Color(
                        8,
                        11,
                        35
                )
        );

        campo.setForeground(BLANCO);
        campo.setCaretColor(AZUL);

        campo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        campo.setBorder(
                new LineBorder(
                        new Color(
                                75,
                                85,
                                160
                        ),
                        1
                )
        );

        return campo;
    }

    // =========================================================
    // ADMINISTRACION
    // =========================================================

    private void mostrarAdministracion() {

        JDialog dialogo =
                new JDialog(
                        this,
                        "Administración - TechStore",
                        true
                );

        dialogo.setSize(
                1100,
                650
        );

        dialogo.setLocationRelativeTo(
                this
        );

        JPanel principal =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        principal.setBackground(
                FONDO
        );

        principal.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel titulo =
                new JLabel(
                        "ADMINISTRACIÓN DE TECHSTORE",
                        SwingConstants.CENTER
                );

        titulo.setForeground(
                AZUL
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        principal.add(
                titulo,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLA
        // =====================================================

        String[] columnas = {

                "Código",
                "Producto",
                "Categoría",
                "Marca",
                "Precio",
                "Stock",
                "Estado"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        for (
                Producto producto :
                productos
        ) {

            modelo.addRow(
                    new Object[]{
                            producto.getCodigo(),
                            producto.getNombre(),
                            producto.getCategoria(),
                            producto.getMarca(),
                            formatearPrecio(
                                    producto.getPrecio()
                            ),
                            producto.getStock(),
                            producto.isEstado()
                                    ?
                                    "Disponible"
                                    :
                                    "No disponible"
                    }
            );
        }

        JTable tabla =
                new JTable(
                        modelo
                );

        tabla.setRowHeight(
                32
        );

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tabla.setBackground(
                new Color(
                        22,
                        26,
                        58
                )
        );

        tabla.setForeground(
                BLANCO
        );

        tabla.setGridColor(
                new Color(
                        65,
                        70,
                        110
                )
        );

        tabla.setSelectionBackground(
                MORADO
        );

        tabla.setSelectionForeground(
                Color.WHITE
        );

        tabla.setAutoCreateRowSorter(
                true
        );

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.getTableHeader()
                .setBackground(
                        new Color(
                                35,
                                40,
                                85
                        )
                );

        tabla.getTableHeader()
                .setForeground(
                        AZUL
                );

        tabla.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        tabla.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );

        // =====================================================
        // CENTRAR COLUMNAS
        // =====================================================

        DefaultTableCellRenderer centrado =
                new DefaultTableCellRenderer();

        centrado.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tabla.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centrado
                );

        tabla.getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        centrado
                );

        // =====================================================
        // ANCHOS
        // =====================================================

        tabla.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        80
                );

        tabla.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        230
                );

        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(
                        130
                );

        tabla.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(
                        120
                );

        tabla.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(
                        120
                );

        tabla.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(
                        80
                );

        tabla.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(
                        120
                );

        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setBorder(
                new LineBorder(
                        new Color(
                                90,
                                70,
                                180
                        ),
                        1
                )
        );

        scroll.getViewport()
                .setBackground(
                        new Color(
                                22,
                                26,
                                58
                        )
                );

        principal.add(
                scroll,
                BorderLayout.CENTER
        );

        // =====================================================
        // PARTE INFERIOR
        // =====================================================

        JLabel informacion =
                new JLabel(
                        "Productos registrados: "
                                + productos.size()
                );

        informacion.setForeground(
                GRIS
        );

        informacion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JPanel inferior =
                new JPanel(
                        new BorderLayout()
                );

        inferior.setOpaque(
                false
        );

        inferior.add(
                informacion,
                BorderLayout.WEST
        );

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botones.setOpaque(
                false
        );

        JButton editar =
                crearBotonAccion(
                        "EDITAR",
                        NARANJA
                );

        JButton eliminar =
                crearBotonAccion(
                        "ELIMINAR",
                        ROJO
                );

        JButton actualizar =
                crearBotonAccion(
                        "ACTUALIZAR",
                        AZUL_BOTON
                );

        JButton cerrar =
                crearBotonAccion(
                        "CERRAR",
                        MORADO
                );

        // =====================================================
        // EDITAR PRODUCTO
        // =====================================================

        editar.addActionListener(
                e -> editarProductoDesdeTabla(
                        dialogo,
                        tabla,
                        modelo
                )
        );

        // =====================================================
        // ELIMINAR PRODUCTO
        // =====================================================

        eliminar.addActionListener(
                e -> eliminarProductoDesdeTabla(
                        dialogo,
                        tabla,
                        modelo
                )
        );

        actualizar.addActionListener(
                e -> {

                    dialogo.dispose();

                    cargarProductos();

                    SwingUtilities.invokeLater(
                            this::mostrarAdministracion
                    );
                }
        );

        cerrar.addActionListener(
                e ->
                        dialogo.dispose()
        );

        botones.add(
                editar
        );

        botones.add(
                eliminar
        );

        botones.add(
                actualizar
        );

        botones.add(
                cerrar
        );

        inferior.add(
                botones,
                BorderLayout.EAST
        );

        principal.add(
                inferior,
                BorderLayout.SOUTH
        );

        dialogo.setContentPane(
                principal
        );

        dialogo.setVisible(
                true
        );
    }

    // =========================================================
    // EDITAR PRODUCTO DESDE ADMINISTRACIÓN
    // =========================================================

    private void editarProductoDesdeTabla(
            JDialog padre,
            JTable tabla,
            DefaultTableModel modelo
    ) {

        int filaVista = tabla.getSelectedRow();

        if (filaVista < 0) {
            JOptionPane.showMessageDialog(
                    padre,
                    "Seleccione un producto de la tabla.",
                    "Editar producto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int fila = tabla.convertRowIndexToModel(filaVista);
        String codigo = String.valueOf(modelo.getValueAt(fila, 0));
        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            JOptionPane.showMessageDialog(
                    padre,
                    "No se encontró el producto seleccionado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JTextField nombre = crearCampoFormulario();
        JTextField marca = crearCampoFormulario();
        JTextField precio = crearCampoFormulario();
        JTextField stock = crearCampoFormulario();

        nombre.setText(producto.getNombre());
        marca.setText(producto.getMarca());
        precio.setText(String.valueOf(producto.getPrecio()));
        stock.setText(String.valueOf(producto.getStock()));

        JComboBox<String> categoria =
                new JComboBox<>(
                        new String[]{
                                "Computador",
                                "Celular",
                                "Tablet",
                                "Accesorios"
                        }
                );
        categoria.setSelectedItem(producto.getCategoria());

        JCheckBox estado =
                new JCheckBox("Producto disponible");
        estado.setSelected(producto.isEstado());
        estado.setOpaque(false);
        estado.setForeground(BLANCO);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel formulario =
                new JPanel(
                        new GridLayout(6, 2, 10, 10)
                );
        formulario.setBackground(PANEL);

        JLabel l1 = new JLabel("Nombre:");
        JLabel l2 = new JLabel("Categoría:");
        JLabel l3 = new JLabel("Marca:");
        JLabel l4 = new JLabel("Precio:");
        JLabel l5 = new JLabel("Stock:");
        JLabel l6 = new JLabel("Estado:");

        for (JLabel label : new JLabel[]{l1,l2,l3,l4,l5,l6}) {
            label.setForeground(BLANCO);
            label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        }

        formulario.add(l1);
        formulario.add(nombre);
        formulario.add(l2);
        formulario.add(categoria);
        formulario.add(l3);
        formulario.add(marca);
        formulario.add(l4);
        formulario.add(precio);
        formulario.add(l5);
        formulario.add(stock);
        formulario.add(l6);
        formulario.add(estado);

        int resultado =
                JOptionPane.showConfirmDialog(
                        padre,
                        formulario,
                        "Editar producto - " + codigo,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }

        String nuevoNombre = nombre.getText().trim();
        String nuevaMarca = marca.getText().trim();
        String nuevaCategoria = String.valueOf(categoria.getSelectedItem());

        if (nuevoNombre.isEmpty() || nuevaMarca.isEmpty()) {
            JOptionPane.showMessageDialog(
                    padre,
                    "Nombre y marca son obligatorios.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            double nuevoPrecio = Double.parseDouble(precio.getText().trim());
            int nuevoStock = Integer.parseInt(stock.getText().trim());

            if (nuevoPrecio < 0 || nuevoStock < 0) {
                JOptionPane.showMessageDialog(
                        padre,
                        "Precio y stock no pueden ser negativos.",
                        "Datos inválidos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            producto.setNombre(nuevoNombre);
            producto.setCategoria(nuevaCategoria);
            producto.setMarca(nuevaMarca);
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
            producto.setEstado(estado.isSelected());

            ProductoDAO dao = new ProductoDAO();
            boolean actualizado = dao.actualizar(producto);

            if (actualizado) {
                JOptionPane.showMessageDialog(
                        padre,
                        "Producto actualizado correctamente.",
                        "TechStore",
                        JOptionPane.INFORMATION_MESSAGE
                );

                cargarProductos();
                padre.dispose();
                SwingUtilities.invokeLater(this::mostrarAdministracion);

            } else {
                JOptionPane.showMessageDialog(
                        padre,
                        "No se pudo actualizar el producto.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    padre,
                    "Precio y stock deben ser valores numéricos.",
                    "Datos inválidos",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =========================================================
    // ELIMINAR PRODUCTO DESDE ADMINISTRACIÓN
    // =========================================================

    private void eliminarProductoDesdeTabla(
            JDialog padre,
            JTable tabla,
            DefaultTableModel modelo
    ) {

        int filaVista = tabla.getSelectedRow();

        if (filaVista < 0) {
            JOptionPane.showMessageDialog(
                    padre,
                    "Seleccione un producto para eliminar.",
                    "Eliminar producto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int fila = tabla.convertRowIndexToModel(filaVista);
        String codigo = String.valueOf(modelo.getValueAt(fila, 0));
        String nombre = String.valueOf(modelo.getValueAt(fila, 1));

        int confirmacion =
                JOptionPane.showConfirmDialog(
                        padre,
                        "¿Está seguro de eliminar este producto?\n\n"
                                + codigo + " - " + nombre,
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        ProductoDAO dao = new ProductoDAO();
        boolean eliminado = dao.eliminar(codigo);

        if (eliminado) {
            JOptionPane.showMessageDialog(
                    padre,
                    "Producto eliminado correctamente.",
                    "TechStore",
                    JOptionPane.INFORMATION_MESSAGE
            );

            cargarProductos();
            padre.dispose();
            SwingUtilities.invokeLater(this::mostrarAdministracion);

        } else {
            JOptionPane.showMessageDialog(
                    padre,
                    "No se pudo eliminar el producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // PIE DE PAGINA
    // =========================================================

    private JPanel crearPie() {

        JPanel pie =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        pie.setBackground(
                new Color(
                        12,
                        16,
                        48
                )
        );

        JLabel texto =
                new JLabel(
                        "© 2026 TechStore - Tienda Tecnológica"
                );

        texto.setForeground(
                BLANCO
        );

        texto.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        pie.add(
                texto
        );

        return pie;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager
                                        .getSystemLookAndFeelClassName()
                        );

                    } catch (
                            Exception e
                    ) {

                        System.out.println(
                                "No se pudo cargar el estilo."
                        );
                    }

                    VentanaPrincipal ventana =
                            new VentanaPrincipal();

                    ventana.setVisible(
                            true
                    );
                }
        );
    }
}
