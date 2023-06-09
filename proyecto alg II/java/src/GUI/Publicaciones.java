package GUI;

import Interfaz.Durable;
import Interfaz.Filtrable;
import Perfil.Reportes;
import TipoPublicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Publicaciones.
 */
public class Publicaciones extends JDialog {
    private JPanel contentPane;
    private JButton next;
    private JButton prev;
    private Reportes reporte = new Reportes();
    private JLabel cantPub;
    private JTextPane textPane1;
    private JButton filtro;
    private JTextPane textdurable;
    private JButton PAUSARButton;
    private JButton AVANZARButton;
    private JButton reportesButton;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JButton reproducirAPartirDeButton;

    private JButton reproduccionButton;
    private JButton estadisticasButton;

    /**
     * The Filtroaplicado.
     */
    int filtroaplicado = 0;
    private int indice = 0;
    private Thread Thread;
    private boolean pausado;//para verificar si esta en pausa la repro
    private final List<Publicacion> listaPublicacion;

    /**
     * Set pausado.
     *
     * @param pausa the pausa
     */
    public void setPausado(boolean pausa){
        pausado = pausa;
    }

    /**
     * Instantiates a new Publicaciones.
     *
     * @param ListaPublicacion the lista publicacion
     */
    public Publicaciones(List<Publicacion> ListaPublicacion) {

        Thread= null; //Inicializo hilo
        this.listaPublicacion = ListaPublicacion;
        setTitle("Publicaciones");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(prev);
        setSize(1080, 720);
        Font font = textPane1.getFont();
        Font newFont = font.deriveFont(font.getSize() + 10f); // Aumentar el tamanio en 10 puntos
        textPane1.setFont(newFont);
        mostrarPublicacion(indice);

        reproducirAPartirDeButton.addActionListener(e -> onreproducirAPartirDeButton());
        PAUSARButton.addActionListener(e -> OnPausar(listaPublicacion.get(indice)));
        AVANZARButton.addActionListener(e -> {
            if(listaPublicacion.get(indice) instanceof Audio)
                OnAvanzar(((Audio) listaPublicacion.get(indice)),0,((Audio) listaPublicacion.get(indice)).getDuracion());
            else
                OnAvanzar(((Video) listaPublicacion.get(indice)),0,((Video) listaPublicacion.get(indice)).getDuracion());
        });
        filtro.addActionListener(e -> Onfiltro());
        prev.addActionListener(e -> onPrev());
        next.addActionListener(e -> onNext());
        eliminarButton.addActionListener(e -> onEliminar());
        agregarButton.addActionListener(e -> onAgregar());
        reportesButton.addActionListener(e -> onReportes());

        reproduccionButton.addActionListener(e -> onRepro());
        estadisticasButton.addActionListener(e -> onEstadisticas());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> onX(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    /**
     * Instantiates a new Publicaciones.
     *
     * @param ListaPublicaciones         the lista publicaciones
     * @param ListaPublicacionescompleta the lista publicacionescompleta
     */
    public Publicaciones(List<Publicacion> ListaPublicaciones,List<Publicacion> ListaPublicacionescompleta) {

        Thread= null; //Inicializo hilo
        this.listaPublicacion = ListaPublicaciones;
        setTitle("Publicaciones");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(prev);
        setSize(1080, 720);
        reportesButton.setVisible(false);
        Font font = textPane1.getFont();
        Font newFont = font.deriveFont(font.getSize() + 10f); // Aumentar el tamanio en 2 puntos
        textPane1.setFont(newFont);
        mostrarPublicacion(indice);

        reproducirAPartirDeButton.addActionListener(e -> onreproducirAPartirDeButton());
        PAUSARButton.addActionListener(e -> OnPausar(listaPublicacion.get(indice)));
        AVANZARButton.addActionListener(e -> {
            if(listaPublicacion.get(indice) instanceof Audio)
                OnAvanzar(((Audio) listaPublicacion.get(indice)),0,((Audio) listaPublicacion.get(indice)).getDuracion());
            else
                OnAvanzar(((Video) listaPublicacion.get(indice)),0,((Video) listaPublicacion.get(indice)).getDuracion());
        });
        filtro.addActionListener(e -> Onfiltro());
        prev.addActionListener(e -> onPrev());
        next.addActionListener(e -> onNext());
        eliminarButton.addActionListener(e -> onEliminar());
        agregarButton.addActionListener(e -> onAgregar(listaPublicacion,ListaPublicacionescompleta));
        reproduccionButton.addActionListener(e -> onRepro());
        estadisticasButton.addActionListener(e -> onEstadisticas());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(e -> onX(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }
    private void onEliminar(){

        if (!listaPublicacion.isEmpty()) {
            listaPublicacion.remove(indice);
            if (indice >= listaPublicacion.size()) {
                indice = listaPublicacion.size() - 1;
            }

            mostrarPublicacion(indice);
        }else{
            String texto = "Sin publicaciones";
            textPane1.setText(texto);
        }
    }

    /**
     * Onreproducir a partir de button.
     */
    void onreproducirAPartirDeButton(){
        Thread.interrupt();
        int desde = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese desde el segundo a reproducir:", "Reproduccion", JOptionPane.PLAIN_MESSAGE));
        int hasta = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese hasta el segundo a reproducir:", "Reproduccion", JOptionPane.PLAIN_MESSAGE));
        if(listaPublicacion.get(indice) instanceof Audio)
            mostrarDuracionPublicacion(((Audio) listaPublicacion.get(indice)).getDuracion(),desde,hasta);
        else
            mostrarDuracionPublicacion(((Video) listaPublicacion.get(indice)).getDuracion(),desde,hasta);

    }
    private void onAgregar(List<Publicacion> ListaPublicacion, List<Publicacion> listaPublicacion) {
        // Obtener los nombres de las publicaciones como opciones
        String[] opciones = new String[listaPublicacion.size()];
        for (int i = 0; i < listaPublicacion.size(); i++) {
            opciones[i] = listaPublicacion.get(i).getNombre();
        }
        // Mostrar cuadro de dialogo con el selector de publicaciones
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona una publicación para agregar:",
                "Agregar Publicación",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                null
        );
        // Verificar si se seleccionó una publicación y realizar la acción correspondiente
        if (seleccion != null) {
            // Obtener la publicación seleccionada
            Publicacion publicacionSeleccionada = null;
            for (Publicacion publicacion : listaPublicacion) {
                if (publicacion.getNombre().equals(seleccion)) {
                    publicacionSeleccionada = publicacion;
                    break;
                }
            }
            if (publicacionSeleccionada != null) {
                ListaPublicacion.add(publicacionSeleccionada);
            }
        }
        mostrarPublicacion(indice);
    }
    private void onAgregar() {
        // Mostrar el cuadro de dialogo de opción
        String[] opciones = {"Texto", "Imagen", "Video", "Audio"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione el tipo de publicación a agregar:", "Agregar Publicación", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        // Verificar la opción seleccionada
        switch (seleccion) {
            case 0:
                // Agregar una publicación de texto
                agregarPublicacionTexto();
                mostrarPublicacion(listaPublicacion.size()-1);
                break;
            case 1:
                // Agregar una publicación de imagen
                agregarPublicacionImagen();
                mostrarPublicacion(listaPublicacion.size()-1);
                break;
            case 2:
                // Agregar una publicación de video
                agregarPublicacionVideo();
                mostrarPublicacion(listaPublicacion.size()-1);
                break;
            case 3:
                // Agregar una publicación de audio
                agregarPublicacionAudio();
                mostrarPublicacion(listaPublicacion.size()-1);
                break;
            default:
                // No se seleccionó una opción valida
                break;
        }

    }

    private void agregarPublicacionTexto() {
        // Solicitar el nombre de la nueva publicación de texto
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String fechaSubida = String.valueOf(LocalDate.now());
        String hashtagsInput = JOptionPane.showInputDialog(this, "Ingrese los hashtags de la nueva publicación (separados por comas):", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        // Separar los hashtags ingresados por comas y agregarlos a una lista
        ArrayList<String> hashtags = new ArrayList<>();
        String[] hashtagsArray = hashtagsInput.split("\\s*,\\s*");
        for (String hashtag : hashtagsArray) {
            hashtags.add(hashtag.trim());
        }
        String fuenteInput = JOptionPane.showInputDialog(this, "Ingrese la fuente de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        int tamanioImput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el tamanio de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        int cantCararcInput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cantidad de caracteres del texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        // Crear una nueva instancia de Publicacion de texto
        ArrayList<String> comentariosInput = new ArrayList<>();
        Publicacion nuevaPublicacion = new Texto(nombre, descripcion, fechaSubida,0, fuenteInput,cantCararcInput,tamanioImput,hashtags,comentariosInput);

        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz

        mostrarPublicacion(indice);
    }

    private void agregarPublicacionImagen() {

        // Solicitar el nombre de la nueva publicación de imagen
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String fechaSubida = String.valueOf(LocalDate.now());
        String resolucionInput = JOptionPane.showInputDialog(this, "Ingrese la Resolucion de la imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        int altoInput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la altura de la imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        int anchoInput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ancho de la imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        String hashtagsInput = JOptionPane.showInputDialog(this, "Ingrese los hashtags de la nueva publicación (separados por comas):", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        // Separar los hashtags ingresados por comas y agregarlos a una lista
        ArrayList<String> hashtags = new ArrayList<>();
        String[] hashtagsArray = hashtagsInput.split("\\s*,\\s*");
        for (String hashtag : hashtagsArray) {
            hashtags.add(hashtag.trim());
        }

        ArrayList<String> comentariosInput = new ArrayList<>();
        // Crear una nueva instancia de Publicacion de imagen
        Publicacion nuevaPublicacion = new Imagen(nombre, descripcion, fechaSubida,0,resolucionInput,altoInput,anchoInput,hashtags,comentariosInput );

        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz
        mostrarPublicacion(indice);
    }

    private void agregarPublicacionVideo() {
        // Solicitar el nombre de la nueva publicación de video
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String fechaSubida = String.valueOf(LocalDate.now());
        String resolucionImput = JOptionPane.showInputDialog(this, "Ingrese la resolucion del video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        int cantCuadrosInput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cantidad de cuadros del video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        int duracionInput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la duracion del video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        String hashtagsInput = JOptionPane.showInputDialog(this, "Ingrese los hashtags de la nueva publicación (separados por comas):", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        // Separar los hashtags ingresados por comas y agregarlos a una lista
        ArrayList<String> hashtags = new ArrayList<>();
        String[] hashtagsArray = hashtagsInput.split("\\s*,\\s*");
        for (String hashtag : hashtagsArray) {
            hashtags.add(hashtag.trim());
        }
        ArrayList<String> comentariosInput = new ArrayList<>();
        // Crear una nueva instancia de Publicacion de imagen
        Publicacion nuevaPublicacion = new Video(nombre, descripcion, fechaSubida,0,resolucionImput,duracionInput,cantCuadrosInput,hashtags,comentariosInput );

        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz
        mostrarPublicacion(indice);
    }

    private void agregarPublicacionAudio() {
        // Solicitar el nombre de la nueva publicación de audio
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de audio:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de audio:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        String fechaSubida = String.valueOf(LocalDate.now());
        int duracionImput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la duracion del audio:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        int velocidad_bitsImput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la velocidad de bits del audio: ", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        String hashtagsInput = JOptionPane.showInputDialog(this, "Ingrese los hashtags de la nueva publicación (separados por comas):", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);
        // Separar los hashtags ingresados por comas y agregarlos a una lista
        ArrayList<String> hashtags = new ArrayList<>();
        String[] hashtagsArray = hashtagsInput.split("\\s*,\\s*");
        for (String hashtag : hashtagsArray) {
            hashtags.add(hashtag.trim());
        }
        ArrayList<String> comentariosInput = new ArrayList<>();
        // Crear una nueva instancia de Publicacion de imagen
        Publicacion nuevaPublicacion = new Audio(nombre, descripcion, fechaSubida,0,duracionImput,velocidad_bitsImput,hashtags,comentariosInput );
        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz
        indice = listaPublicacion.size() - 1;
        mostrarPublicacion(indice);
    }

    /**
     * On reportes.
     */
    public void onReportes(){
        reporte.Reporte1(listaPublicacion);
    }

    /**
     * On repro.
     */
    public void onRepro(){
        Repro dialog = new Repro(listaPublicacion);
        dialog.pack();
        dialog.setVisible(true);

    }

    public void onEstadisticas(){
        Estadisticas dialog = new Estadisticas(listaPublicacion);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onX() {
        dispose();
    }

    private void OnPausar(Publicacion publicacion){
        ((Durable)publicacion).detener(this);
    }
    private void OnAvanzar(Publicacion publicacion, int desde, int hasta){
        OnPausar(publicacion);
        ((Durable)publicacion).avanzar(0,this);
    }
    private void Onfiltro(){
        Publicacion publicacion = listaPublicacion.get(indice);

        if(publicacion instanceof Imagen || publicacion instanceof Video){
            filtropublicaciones dialog = new filtropublicaciones();
            dialog.pack();
            dialog.setVisible(true);
            filtroaplicado = dialog.getFiltroaplicado();
            ((Filtrable) publicacion).aplicarFiltro(filtroaplicado);
        }

        mostrarPublicacion(indice);
    }
    private void onPrev() {
        if (indice > 0) {
            indice--;
        } else {
            indice = listaPublicacion.size() - 1; // Mostrar el último elemento si se alcanza el principio de la lista
        }
        mostrarPublicacion(indice);
    }

    private void onNext() {
        if (indice < listaPublicacion.size() - 1) {
            indice++;
        } else {
            indice = 0; // Mostrar el primer elemento si se alcanza el final de la lista
        }
        mostrarPublicacion(indice);
    }

    private void mostrarPublicacion(int i) {
        if(listaPublicacion.isEmpty()){
            String texto = "Sin publicaciones";
            textPane1.setText(texto);
        }else{
            int duracion=0;
            filtro.setVisible(false);
            String contador = (indice+1) + "/" + listaPublicacion.toArray().length;
            Publicacion publicacion = listaPublicacion.get(i);
            String tipo = publicacion.getClass().getSimpleName();
            String texto = "Tipo: " + tipo + "\n";
            texto += "Nombre: " + publicacion.getNombre() + "\n";
            texto += "Descripción: " + publicacion.getDescripcionPost() + "\n";
            texto += "Fecha de Subida: " + publicacion.getFechaSubida() + "\n";
            texto += "Me gusta: " + publicacion.getCantMG() + "\n";

            // Mostrar los datos específicos según el tipo de publicación
            if (publicacion instanceof Texto) {
                Texto textoPublicacion = (Texto) publicacion;
                texto += "Fuente: " + textoPublicacion.getFuente() + "\n";
                texto += "Cantidad de caracteres: " + textoPublicacion.getCantCaracteres() + "\n";
                texto += "Tamanio de fuente: " + textoPublicacion.getTamanioFuente() + "\n";
            } else if (publicacion instanceof Imagen) {
                Imagen imagenPublicacion = (Imagen) publicacion;
                texto += "Resolucion: " + imagenPublicacion.getResolucion() + "\n";
                texto += "Alto: " + imagenPublicacion.getAlto() + "\n";
                texto += "Ancho: " + imagenPublicacion.getAncho() + "\n";
                texto += "Filtro:" + ((Imagen) publicacion).getFiltro() + "\n";
            } else if (publicacion instanceof Audio) {
                Audio audioPublicacion = (Audio) publicacion;
                texto += "Velocidad Bits: " + audioPublicacion.getVelocidad_bits() + "\n";
                texto += "Duracion: " + audioPublicacion.getDuracion() + "\n";

            } else if (publicacion instanceof Video) {
                Video videoPublicacion = (Video) publicacion;
                texto += "Resolucion: " + videoPublicacion.getResolucion() + "\n";
                texto += "Cantidad de cuadros: " + videoPublicacion.getCantcuadros() + "\n";
                texto += "Duracion: " + videoPublicacion.getDuracion() + "\n";
                texto += "Filtro:" + ((Video) publicacion).getFiltro() + "\n";

            }

            if(publicacion instanceof Video || publicacion instanceof Imagen){
                filtro.setVisible(true);
            }
            List<String> hashtags = publicacion.getHashtags();
            if (!hashtags.isEmpty()) {
                texto += "\nHashtags:\n";
                for (String hashtag : hashtags) {
                    texto += "#" + hashtag ;
                }
            }else{texto += "\nSin hashtags";}

            List<String> comentarios = publicacion.getComentarios();
            if (!comentarios.isEmpty()) {
                texto += "\nComentarios:\n";
                for (String comentario : comentarios) {
                    texto += comentario + "\n";
                }
            }else{texto += "\nSin comentario";}

            cantPub.setText(contador);
            textPane1.setText(texto);

            if(publicacion instanceof Video || publicacion instanceof Audio){
                reproducirAPartirDeButton.setVisible(true);
                textdurable.setVisible(true);
                AVANZARButton.setVisible(true);
                PAUSARButton.setVisible(true);
                if (Thread != null) {
                    Thread.interrupt();
                }
                if(publicacion instanceof Video){
                    duracion = ((Video) publicacion).getDuracion();
                }else{
                    duracion = ((Audio) publicacion).getDuracion();
                }
                mostrarDuracionPublicacion(duracion);
            }else{
                duracion=0;
                reproducirAPartirDeButton.setVisible(false);
                textdurable.setVisible(false);
                AVANZARButton.setVisible(false);
                PAUSARButton.setVisible(false);
            }
        }

    }
    private void mostrarDuracionPublicacion2(int duracion, int desde, int hasta) {
        if(desde >= 0 && desde < hasta && hasta <= duracion) {
            Thread = new Thread(() -> {
                int j = desde;
                while (j <= hasta && !Thread.interrupted()) {
                    if (!pausado) {
                        String duraciontexto = j + " --------------------- " + duracion;
                        SwingUtilities.invokeLater(() -> textdurable.setText(duraciontexto));
                        j++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            Thread.start();
        }
        else {
            System.err.println("parametros erroneos");
            onreproducirAPartirDeButton();
        }
        }
    private void mostrarDuracionPublicacion(int duracion, int desde, int hasta) {

        if(desde >= 0 && desde < hasta && hasta <= duracion) {
            Thread = new Thread(() -> {
                int j = desde;
                while (j <= hasta && !Thread.interrupted()) {
                    if (!pausado) {
                        String duraciontexto = j + " --------------------- " + duracion;
                        SwingUtilities.invokeLater(() -> textdurable.setText(duraciontexto));
                        j++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            Thread.start();
        }
        else {
            System.err.println("parametros erroneos");
            onreproducirAPartirDeButton();
        }
    }
    private void mostrarDuracionPublicacion(int duracion) {
        Thread = new Thread(() -> {
            int j = 0;
            while (j <= duracion && !Thread.interrupted()) {
                if (!pausado) {
                    String duraciontexto = j + " --------------------- " + duracion;
                    SwingUtilities.invokeLater(() -> textdurable.setText(duraciontexto));
                    j++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        Thread.start();
    }
    //Creo un hilo para cada publciacion y muestra la duracion correspondiente a la publicacion (video o audio) actual
}