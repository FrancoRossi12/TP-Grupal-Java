package GUI;

import Interfaz.Filtrable;
import TipoPublicacion.Imagen;
import TipoPublicacion.Publicacion;
import TipoPublicacion.Video;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Repro.
 */
public class Repro extends JDialog {
    private JPanel contentPane;
    private JButton buttonSimulacion;
    private JList<String> listaPublicaciones;
    private JComboBox<String> filtroComboBox;
    private final List<Publicacion> publicaciones;
    private final List<Publicacion> publicacionesSeleccionadas= new ArrayList<>();

    /**
     * Instantiates a new Repro.
     *
     * @param lista the lista
     */
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
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        publicaciones = lista;
        String[] nombresPublicaciones = obtenerNombresPublicaciones(publicaciones);
        listaPublicaciones.setListData(nombresPublicaciones);
        String[] filtros = {"DEFAULT", "B_N", "CLARENDON", "SEPIA"}; // Opciones de filtro disponibles
        filtroComboBox.setModel(new DefaultComboBoxModel<>(filtros));
        filtroComboBox.addActionListener(e -> {
            String filtroSeleccionado = (String) filtroComboBox.getSelectedItem();
            filtrarPublicaciones(filtroSeleccionado);
        });

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

        if (filtro.equals("DEFAULT")) {
            // Agregar todas las publicaciones por defecto
            publicacionesFiltradas.addAll(publicaciones);
        } else {
            for (Publicacion publicacion : publicaciones) {
                if (publicacion instanceof Video) {
                    // Agregar los videos que coincidan con el filtro
                    Video video = (Video) publicacion;
                    Filtrable.tipoFiltro filtroAplicado = video.getFiltro();
                    if (filtroAplicado != null && filtroAplicado.name().equalsIgnoreCase(filtro)) {
                        publicacionesFiltradas.add(publicacion);
                    }
                } else if (publicacion instanceof Imagen) {
                    // Agregar las imagenes que coincidan con el filtro
                    Imagen imagen = (Imagen) publicacion;
                    Filtrable.tipoFiltro filtroAplicado = imagen.getFiltro();
                    if (filtroAplicado != null && filtroAplicado.name().equalsIgnoreCase(filtro)) {
                        publicacionesFiltradas.add(publicacion);
                    }
                }
            }
        }

        String[] nombresPublicacionesFiltradas = obtenerNombresPublicaciones(publicacionesFiltradas);
        listaPublicaciones.setListData(nombresPublicacionesFiltradas);
    }


    private void reproducirPublicaciones() {
        int duracionTotal = 0;
        if (publicacionesSeleccionadas.isEmpty()) {
            System.out.println("No hay publicaciones seleccionadas para reproducir.");
            return;
        }
        for (Publicacion publicacion : publicacionesSeleccionadas) {
            final Publicacion pub = publicacion;
            System.out.println("Reproduciendo: " + pub.getNombre());
            int duracionPublicacion;
            if (pub instanceof Video) {
                Video video = (Video) pub;
                duracionPublicacion = video.getDuracion();
                System.out.println("Duración: " + duracionPublicacion + " segundos");
            } else if (pub instanceof Imagen) {
                duracionPublicacion = 1; // Duración simulada de 1 segundo para las imagenes
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


}

