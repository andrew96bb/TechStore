package com.techstore.util;

import com.techstore.model.Producto;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImagenProducto {

    private static final String CARPETA =
            "src/main/resources/images";

    private static final HttpClient CLIENTE =
            HttpClient.newBuilder()
                    .followRedirects(
                            HttpClient.Redirect.NORMAL
                    )
                    .build();

    // =========================================================
    // OBTENER IMAGEN
    // =========================================================

    public static ImageIcon obtenerImagen(
            Producto producto,
            int ancho,
            int alto
    ) {

        if (producto == null) {
            return null;
        }

        try {

            Files.createDirectories(
                    Paths.get(CARPETA)
            );

            String codigo =
                    producto.getCodigo();

            Path archivo =
                    Paths.get(
                            CARPETA,
                            codigo + ".webp"
                    );

            // -------------------------------------------------
            // SI YA EXISTE LOCALMENTE
            // -------------------------------------------------

            if (Files.exists(archivo)) {

                BufferedImage imagen =
                        ImageIO.read(
                                archivo.toFile()
                        );

                if (imagen != null) {

                    return crearIcono(
                            imagen,
                            ancho,
                            alto
                    );
                }
            }

            // -------------------------------------------------
            // BUSCAR EN INTERNET
            // -------------------------------------------------

            String url =
                    buscarImagen(producto);

            if (url == null) {

                System.out.println(
                        "No se encontró imagen para: "
                                + producto.getNombre()
                );

                return null;
            }

            System.out.println(
                    "Imagen encontrada: "
                            + producto.getNombre()
            );

            // -------------------------------------------------
            // DESCARGAR
            // -------------------------------------------------

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(url)
                            )
                            .header(
                                    "User-Agent",
                                    "TechStore/1.0"
                            )
                            .GET()
                            .build();

            HttpResponse<byte[]> respuesta =
                    CLIENTE.send(
                            request,
                            HttpResponse.BodyHandlers
                                    .ofByteArray()
                    );

            if (
                    respuesta.statusCode() < 200
                            ||
                    respuesta.statusCode() >= 300
            ) {

                System.out.println(
                        "Error HTTP: "
                                + respuesta.statusCode()
                );

                return null;
            }

            byte[] datos =
                    respuesta.body();

            // -------------------------------------------------
            // GUARDAR EN EL EQUIPO
            // -------------------------------------------------

            Files.write(
                    archivo,
                    datos
            );

            // -------------------------------------------------
            // LEER IMAGEN
            // -------------------------------------------------

            BufferedImage imagen =
                    ImageIO.read(
                            new ByteArrayInputStream(
                                    datos
                            )
                    );

            if (imagen == null) {

                System.out.println(
                        "No se pudo leer la imagen: "
                                + producto.getNombre()
                );

                return null;
            }

            return crearIcono(
                    imagen,
                    ancho,
                    alto
            );

        } catch (Exception e) {

            System.out.println(
                    "Error con imagen de "
                            + producto.getNombre()
            );

            System.out.println(
                    e.getMessage()
            );

            return null;
        }
    }

    // =========================================================
    // BUSCAR IMAGEN
    // =========================================================

    private static String buscarImagen(
            Producto producto
    ) throws Exception {

        String termino =
                producto.getNombre();

        String consulta =
                "https://dummyjson.com/products/search?q="
                        +
                URLEncoder.encode(
                        termino,
                        StandardCharsets.UTF_8
                );

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(consulta)
                        )
                        .header(
                                "User-Agent",
                                "TechStore/1.0"
                        )
                        .GET()
                        .build();

        HttpResponse<String> respuesta =
                CLIENTE.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString()
                );

        if (respuesta.statusCode() != 200) {
            return null;
        }

        String json =
                respuesta.body();

        // -----------------------------------------------------
        // BUSCAR THUMBNAIL
        // -----------------------------------------------------

        Pattern patronThumbnail =
                Pattern.compile(
                        "\"thumbnail\"\\s*:\\s*\"([^\"]+)\""
                );

        Matcher matcher =
                patronThumbnail.matcher(json);

        if (matcher.find()) {

            return limpiarURL(
                    matcher.group(1)
            );
        }

        // -----------------------------------------------------
        // BUSCAR IMAGES
        // -----------------------------------------------------

        Pattern patronImagen =
                Pattern.compile(
                        "\"images\"\\s*:\\s*\\[\\s*\"([^\"]+)\""
                );

        Matcher matcherImagen =
                patronImagen.matcher(json);

        if (matcherImagen.find()) {

            return limpiarURL(
                    matcherImagen.group(1)
            );
        }

        return null;
    }

    // =========================================================
    // CREAR ICONO
    // =========================================================

    private static ImageIcon crearIcono(
            BufferedImage imagen,
            int ancho,
            int alto
    ) {

        int originalAncho =
                imagen.getWidth();

        int originalAlto =
                imagen.getHeight();

        double escalaAncho =
                (double) ancho
                        /
                originalAncho;

        double escalaAlto =
                (double) alto
                        /
                originalAlto;

        double escala =
                Math.min(
                        escalaAncho,
                        escalaAlto
                );

        int nuevoAncho =
                (int)
                        (originalAncho * escala);

        int nuevoAlto =
                (int)
                        (originalAlto * escala);

        Image escalada =
                imagen.getScaledInstance(
                        nuevoAncho,
                        nuevoAlto,
                        Image.SCALE_SMOOTH
                );

        return new ImageIcon(
                escalada
        );
    }

    // =========================================================
    // LIMPIAR URL
    // =========================================================

    private static String limpiarURL(
            String url
    ) {

        return url
                .replace("\\/", "/")
                .replace("\\\"", "\"");
    }
}