package GUI;

import TipoPublicacion.Imagen;
import TipoPublicacion.Publicacion;
import TipoPublicacion.Video;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Repro extends JDialog {
    private JPanel contentPane;
    private JButton buttonSimulacion;
    private JList<String> listaPublicaciones;
    private JComboBox<String> filtroComboBox;
    private List<Publicacion> publicaciones;
    private List<Publicacion> publicacionesSeleccionadas= new ArrayList<>();

    public Repro(List<Publicacion> lista) {
        setTitle("Reproduccion de Publicaciones");
        setSize(1080, 720);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSimulacion);

        buttonSimulacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                publicacionesSeleccionadas.clear();
                List<String> selectedPublicaciones = listaPublicaciones.getSelectedValuesList();
                for (String nombre : selectedPublicaciones) {
                    for (Publicacion publicacion : publicaciones) {
                        if (publicacion.getNombre().equals(nombre)) {
                            if (publicacion instanceof Video || publicacion instanceof Imagen) {
                                publicacionesSeleccionadas.add(publicacion);
                            }
                            break;
                        }
                    }
                }
                reproducirPublicaciones();
            }
        });


        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        publicaciones = lista;
        //publicacionesSeleccionadas = new ArrayList<>();

        String[] nombresPublicaciones = obtenerNombresPublicaciones(publicaciones);
        listaPublicaciones.setListData(nombresPublicaciones);

        String[] filtros = {"DEFAULT", "B_N", "CLARENDON", "SEPIA"}; // Opciones de filtro disponibles
        filtroComboBox.setModel(new DefaultComboBoxModel<>(filtros));
        filtroComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filtroSeleccionado = (String) filtroComboBox.getSelectedItem();
                filtrarPublicaciones(filtroSeleccionado);
            }
        });

        pack();
        setVisible(true);
    }

    private String[] obtenerNombresPublicaciones(List<Publicacion> publicaciones) {
        String[] nombres = new String[publicaciones.size()];
        for (int i = 0; i < publicaciones.size(); i++) {
            nombres[i] = publicaciones.get(i).getNombre();
        }
        return nombres;
    }

    private void filtrarPublicaciones(String filtro) {
        List<Publicacion> publicacionesFiltradas = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            if (publicacion instanceof Video || publicacion instanceof Imagen) {
                if (filtro.equals("DEFAULT")) {
                    publicacionesFiltradas.add(publicacion);
                } else if (filtro.equals("B_N")) {
                    if (publicacion instanceof Imagen) {
                        Imagen imagen = (Imagen) publicacion;
                        imagen.aplicarFiltro(1);
                        publicacionesFiltradas.add(imagen);
                    }
                } else if (filtro.equals("CLARENDON")) {
                    if (publicacion instanceof Imagen) {
                        Imagen imagen = (Imagen) publicacion;
                        imagen.aplicarFiltro(2);
                        publicacionesFiltradas.add(imagen);
                    }
                } else if (filtro.equals("SEPIA")) {
                    if (publicacion instanceof Imagen) {
                        Imagen imagen = (Imagen) publicacion;
                        imagen.aplicarFiltro(3);
                        publicacionesFiltradas.add(imagen);
                    }
                }
            }
        }
        String[] nombresPublicacionesFiltradas = obtenerNombresPublicaciones(publicacionesFiltradas);
        listaPublicaciones.setListData(nombresPublicacionesFiltradas);
    }

    private void reproducirPublicaciones() {
        int duracionTotal = 0;
        System.out.println("Hola");
        if (publicacionesSeleccionadas.isEmpty()) {
            System.out.println("No hay publicaciones seleccionadas para reproducir.");
            return;
        }
        for (Publicacion publicacion : publicacionesSeleccionadas) {
            final Publicacion pub = publicacion; // Variable final capturando el valor de publicacion
            System.out.println("Reproduciendo: " + pub.getNombre());
            int duracionPublicacion;
            if (pub instanceof Video) {
                Video video = (Video) pub;
                duracionPublicacion = video.getDuracion();
                System.out.println("Duración: " + duracionPublicacion + " segundos");
            } else if (pub instanceof Imagen) {
                duracionPublicacion = 1; // Duración simulada de 1 segundo para las imágenes
                System.out.println("Duración: 1 segundo");
            } else {
                duracionPublicacion = 0;
            }
            duracionTotal += duracionPublicacion;

            // Simulación de reproducción utilizando un hilo
            Thread thread = new Thread(() -> {
                System.out.println("Inicio: " + pub.getNombre());
                LocalTime startTime = LocalTime.now();
                for (int j = 0; j <= duracionPublicacion-1; j++) {
                    if (Thread.interrupted()) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                        LocalTime currentTime = LocalTime.now();
                        Duration duration = Duration.between(startTime, currentTime);
                        long secondsElapsed = duration.getSeconds();
                        System.out.println("Publicacion actual: "+ pub.getNombre() +"  Segundo actual: " + secondsElapsed);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println("Fin: " + pub.getNombre());
            });
            thread.start();
        }
        System.out.println("Tiempo total de reproducción: " + duracionTotal + " segundos");
    }

    private void onOK() {
        dispose();
    }
}

