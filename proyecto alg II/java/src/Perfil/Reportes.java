package Perfil;

import TipoPublicacion.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Reportes {
    public void Reporte1(List<Publicacion> lista) {
        String rutaReportePublicaciones="proyecto alg II/java/src/Reportes/ReportePublicaciones.txt";
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
        }
        audios.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());
        imagenes.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());
        videos.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());
        textos.sort(Comparator.comparingInt(Publicacion::getCantMG).reversed());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReportePublicaciones))) {
            writer.write("Listado completo de publicaciones agrupadas por tipo:");
            writer.newLine();
            // Escribe los audios
            writer.write("Audios:");
            writer.newLine();
            writer.write("Cantidad de publicaciones: " + audios.size());
            writer.newLine();
            if (!audios.isEmpty()) {
                int totalLikes = 0;
                for (Audio audio : audios) {
                    totalLikes += audio.getCantMG();
                    writer.write("Nombre Publicacion: " + audio.getNombre() + "   Me gusta: " + audio.getCantMG());
                    writer.newLine();
                }
                double likesPromedio = (double) totalLikes / audios.size();
                writer.write("Me gusta promedio: " + likesPromedio);
                writer.newLine();
            }

            // Escribe las imagenes
            writer.write("Imagenes:");
            writer.newLine();
            writer.write("Cantidad de publicaciones: " + imagenes.size());
            writer.newLine();
            if (!imagenes.isEmpty()) {
                int totalLikes = 0;
                for (Imagen imagen : imagenes) {
                    totalLikes += imagen.getCantMG();
                    writer.write("Nombre Publicacion: " + imagen.getNombre() + "   Me gusta: " + imagen.getCantMG());
                    writer.newLine();

                }
                double likesPromedio = (double) totalLikes / imagenes.size();
                writer.write("Me gusta promedio: " + likesPromedio);
                writer.newLine();
            }


            writer.write("Videos:");
            writer.newLine();
            writer.write("Cantidad de publicaciones: " + videos.size());
            writer.newLine();
            if (!videos.isEmpty()) {
                int totalLikes = 0;
                for (Video video : videos) {
                    totalLikes += video.getCantMG();
                    writer.write("Nombre Publicacion: " + video.getNombre() + "   Me gusta: " + video.getCantMG());
                    writer.newLine();

                }
                double likesPromedio = (double) totalLikes / videos.size();
                writer.write("Me gusta promedio: " + likesPromedio);
                writer.newLine();
            }

            // Resultados para los textos
            writer.write("Textos");
            writer.newLine();
            writer.write("Cantidad de publicaciones: " + textos.size());
            writer.newLine();
            if (!textos.isEmpty()) {
                int totalLikes = 0;
                for (Texto texto : textos) {
                    totalLikes += texto.getCantMG();
                    writer.write("Nombre Publicacion: " + texto.getNombre() + "   Me gusta: " + texto.getCantMG());
                    writer.newLine();
                }
                double likesPromedio = (double) totalLikes / textos.size();
                writer.write("Me gusta promedio: " + likesPromedio);
                writer.newLine();
            }
            writer.flush();
            writer.close();
            System.out.println("El reporte de Publicaciones se generó correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Mostrar resultados para los audios
        System.out.println("Audios:");
        System.out.println("Cantidad de publicaciones: " + audios.size());
        if (!audios.isEmpty()) {
            int totalLikes = 0;
            for (Audio audio : audios) {
                totalLikes += audio.getCantMG();
                System.out.println("Nombre Publicacion: " + audio.getNombre() + "   Me gusta: " + audio.getCantMG());

            }
            double likesPromedio = (double) totalLikes / audios.size();
            System.out.println("Me gusta promedio: " + likesPromedio);
        }

        // Mostrar resultados para las imagenes
        System.out.println("Imagenes:");
        System.out.println("Cantidad de publicaciones: " + imagenes.size());
        if (!imagenes.isEmpty()) {
            int totalLikes = 0;
            for (Imagen imagen : imagenes) {
                totalLikes += imagen.getCantMG();
                System.out.println("Nombre Publicacion: " + imagen.getNombre() + "   Me gusta: " + imagen.getCantMG());

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

            }
            double likesPromedio = (double) totalLikes / textos.size();
            System.out.println("Me gusta promedio: " + likesPromedio);
        }

        System.out.println("Listado completo de publicaciones agrupadas por tipo:");
        System.out.println("-------------------------------------------------------------");
    }

    public void Reporte2(List<Album> lista2){
        String rutaReporteAlbumes="proyecto alg II/java/src/Reportes/ReporteAlbumes.txt";

        // Leo fechas desde la terminal
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

        // Filtro los álbumes dentro del rango de fechas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaReporteAlbumes))) {
            for (Album album : lista2) {

                int cantidadPublicaciones = 0;
                int cantidadComentarios = 0;

                for (Publicacion publicacion : album.getPublicaciones()) {
                    LocalDate fechaPublicacion = LocalDate.parse(publicacion.getFechaSubida());
                    if (fechaPublicacion.isAfter(fechaInicio) && fechaPublicacion.isBefore(fechaFin)) {
                        cantidadPublicaciones++;
                        List<String> listaComentarios = publicacion.getComentarios();
                        for(String comentario : listaComentarios){
                            cantidadComentarios++;
                        }
                    }
                }

                writer.write("Álbum: " + album.getNombreAlbum());
                System.out.println("Álbum: " + album.getNombreAlbum());
                writer.newLine();
                writer.write("Cantidad de publicaciones: " + cantidadPublicaciones);
                System.out.println("Cantidad de publicaciones: " + cantidadPublicaciones);
                writer.newLine();
                writer.write("Cantidad de comentarios: " + cantidadComentarios);
                System.out.println("Cantidad de comentarios: " + cantidadComentarios);
                writer.newLine();
                writer.newLine();
            }
            writer.flush();
            writer.close();
            System.out.println("El reporte de Albumes se generó correctamente.");
            System.out.println("-------------------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

