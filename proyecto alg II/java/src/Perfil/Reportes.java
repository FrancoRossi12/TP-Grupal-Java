package Perfil;

import TipoPublicacion.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Reportes {
    public void prueba(List<Publicacion> lista) {
        String rutaReportesPublicaciones="proyecto alg II/java/src/Reportes/ReportePublicaciones.txt";
        List<Audio> audios = new ArrayList<>();
        List<Imagen> imagenes = new ArrayList<>();
        List<Video> videos = new ArrayList<>();
        List<Texto> textos = new ArrayList<>();

        for (Publicacion publicacion : lista) {
            if (publicacion instanceof Audio) {
                audios.add((Audio) publicacion);
            } else if (publicacion instanceof Imagen) {
                imagenes.add((Imagen) publicacion);
            } else if (publicacion instanceof Video) {
                videos.add((Video) publicacion);
            } else if (publicacion instanceof Texto) {
                textos.add((Texto) publicacion);
            }

            audios.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());
            imagenes.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());
            videos.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());
            textos.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReportesPublicaciones))) {
                writer.write("Listado completo de publicaciones agrupadas por tipo:\n");

                // Escribe los audios
                writer.write("Audios:\n");
                writer.write("Cantidad de publicaciones: " + audios.size() + "\n");
                if (!audios.isEmpty()) {
                    int totalLikes = 0;
                    for (Audio audio : audios) {
                        totalLikes += audio.getCantMG();
                        writer.write("Nombre Publicacion: " + audio.getNombre() + "   Me gusta: " + audio.getCantMG() + "\n");
                    }
                    double likesPromedio = (double) totalLikes / audios.size();
                    writer.write("Me gusta promedio: " + likesPromedio + "\n");
                }

                // Resultados para las fotos
                writer.write("Fotos:\n");
                writer.write("Cantidad de publicaciones: " + imagenes.size() + "\n");
                if (!imagenes.isEmpty()) {
                    int totalLikes = 0;
                    for (Imagen imagen : imagenes) {
                        totalLikes += imagen.getCantMG();
                        writer.write("Nombre Publicacion: " + imagen.getNombre() + "   Me gusta: " + imagen.getCantMG() + "\n");
                        // Escribir información adicional de la foto si es necesario
                    }
                    double likesPromedio = (double) totalLikes / imagenes.size();
                    writer.write("Me gusta promedio: " + likesPromedio + "\n");
                }

                // Resultados para los videos
                writer.write("Videos:\n");
                writer.write("Cantidad de publicaciones: " + videos.size() + "\n");
                if (!videos.isEmpty()) {
                    int totalLikes = 0;
                    for (Video video : videos) {
                        totalLikes += video.getCantMG();
                        writer.write("Nombre Publicacion: " + video.getNombre() + "   Me gusta: " + video.getCantMG() + "\n");
                        // Escribir información adicional del video si es necesario
                    }
                    double likesPromedio = (double) totalLikes / videos.size();
                    writer.write("Me gusta promedio: " + likesPromedio + "\n");
                }

                // Resultados para los textos
                writer.write("Textos:\n");
                writer.write("Cantidad de publicaciones: " + textos.size() + "\n");
                if (!textos.isEmpty()) {
                    int totalLikes = 0;
                    for (Texto texto : textos) {
                        totalLikes += texto.getCantMG();
                        writer.write("Nombre Publicacion: " + texto.getNombre() + "   Me gusta: " + texto.getCantMG() + "\n");
                        // Escribir información adicional de la foto si es necesario
                    }
                    double likesPromedio = (double) totalLikes / textos.size();
                    writer.write("Me gusta promedio: " + likesPromedio + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Listado completo de publicaciones agrupadas por tipo:");

            // Mostrar resultados para los audios
            System.out.println("Audios:");
            System.out.println("Cantidad de publicaciones: " + audios.size());
            if (!audios.isEmpty()) {
                int totalLikes = 0;
                for (Audio audio : audios) {
                    totalLikes += audio.getCantMG();
                    System.out.println("Nombre Publicacion: " + audio.getNombre() + "   Me gusta: " + audio.getCantMG());
                    // Mostrar información adicional del audio si es necesario
                }
                double likesPromedio = (double) totalLikes / audios.size();
                System.out.println("Me gusta promedio: " + likesPromedio);
            }

            // Mostrar resultados para las fotos
            System.out.println("Fotos:");
            System.out.println("Cantidad de publicaciones: " + imagenes.size());
            if (!imagenes.isEmpty()) {
                int totalLikes = 0;
                for (Imagen imagen : imagenes) {
                    totalLikes += imagen.getCantMG();
                    System.out.println("Nombre Publicacion: " + imagen.getNombre() + "   Me gusta: " + imagen.getCantMG());
                    // Mostrar información adicional de la foto si es necesario
                }
                double likesPromedio = (double) totalLikes / imagenes.size();
                System.out.println("Me gusta promedio: " + likesPromedio);
            }

            // Mostrar resultados para los videos
            System.out.println("Videos:");
            System.out.println("Cantidad de publicaciones: " + videos.size());
            if (!videos.isEmpty()) {
                int totalLikes = 0;
                for (Video video : videos) {
                    totalLikes += video.getCantMG();
                    System.out.println("Nombre Publicacion: " + video.getNombre() + "   Me gusta: " + video.getCantMG());
                    // Mostrar información adicional del video si es necesario
                }
                double likesPromedio = (double) totalLikes / videos.size();
                System.out.println("Me gusta promedio: " + likesPromedio);
            }

            // Resultados para los textos
            System.out.println("Textos:");
            System.out.println("Cantidad de publicaciones: " + textos.size());
            if (!textos.isEmpty()) {
                int totalLikes = 0;
                for (Texto texto : textos) {
                    totalLikes += texto.getCantMG();
                    System.out.println("Nombre Publicacion: " + texto.getNombre() + "   Me gusta: " + texto.getCantMG());
                    // Escribir información adicional de la foto si es necesario
                }
                double likesPromedio = (double) totalLikes / textos.size();
                System.out.println("Me gusta promedio: " + likesPromedio);
            }
        }
    }
}
