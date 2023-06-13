package GUI;

import Interfaz.Durable;
import Interfaz.Filtrable;
import TipoPublicacion.*;
import TipoPublicacion.Audio;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Perfil.Reportes;
public class Publicaciones extends JDialog {
    private JPanel contentPane;
    private JButton next;
    private JButton prev;
    private Reportes reporte = new Reportes();
    /*private Reproduccion repro = new Reproduccion(List<Publicacion>);*/
    private JLabel cantPub;
    private JTextPane textPane1;
    private JButton filtro;
    private JTextPane textdurable;
    private JButton PAUSARButton;
    private JButton AVANZARButton;
    private JButton reportesButton;

    private JButton eliminarButton;
    private JButton agregarButton;

    private JButton reproduccionButton;


    int filtroaplicado = 0;
    private static int indice = 0;
    private Thread Thread;
    private boolean pausado;//para verificar si esta en pausa la repro
    private List<Publicacion> listaPublicacion;

    public void setPausado(boolean pausa){
        pausado = pausa;
    }

    public Publicaciones(List<Publicacion> ListaPublicacion) {

        Thread= null; //Inicializo hilo
        this.listaPublicacion = ListaPublicacion;
        setTitle("Publicaciones");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(prev);
        setSize(1080, 720);

        Font font = textPane1.getFont();
        Font newFont = font.deriveFont(font.getSize() + 10f); // Aumentar el tamaño en 2 puntos
        textPane1.setFont(newFont);
        if(listaPublicacion.isEmpty()){
            String texto = "Sin publicaciones";
            textPane1.setText(texto);
        }else{
            mostrarPublicacion(indice);
        }

        PAUSARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OnPausar(listaPublicacion.get(indice));
            }
        });
        AVANZARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OnAvanzar(listaPublicacion.get(indice));
            }
        });
        filtro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Onfiltro();
            }
        });
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPrev();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNext();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEliminar();
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAgregar();
            }
        });
        reportesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onReportes();
            }
        });
        reproduccionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRepro();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Lógica para cerrar el programa
                System.exit(0); // Cierra la aplicación con el código de salida 0 (éxito)
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onX();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

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
    private void onAgregar() {
        // Mostrar el cuadro de diálogo de opción
        String[] opciones = {"Texto", "Imagen", "Video", "Audio"};
        int seleccion = JOptionPane.showOptionDialog(this, "Seleccione el tipo de publicación a agregar:", "Agregar Publicación", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        listaPublicacion = new ArrayList<>();
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
                // No se seleccionó una opción válida
                break;
        }

    }

    private void agregarPublicacionTexto() {
        // Solicitar el nombre de la nueva publicación de texto
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        // Solicitar la descripción de la nueva publicación de texto
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        String hashtagsInput = JOptionPane.showInputDialog(this, "Ingrese los hashtags de la nueva publicación (separados por comas):", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        // Separar los hashtags ingresados por comas y agregarlos a una lista
        ArrayList<String> hashtags = new ArrayList<>();
        String[] hashtagsArray = hashtagsInput.split("\\s*,\\s*");
        for (String hashtag : hashtagsArray) {
            hashtags.add(hashtag.trim());
        }

        // datos del texto
        String fuenteInput = JOptionPane.showInputDialog(this, "Ingrese la fuente de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        int tamañoImput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el tamaño de texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));

        int cantCararcInput = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cantidad de caracteres del texto:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE));
        // Crear una nueva instancia de Publicacion de texto
        ArrayList<String> comentariosInput = new ArrayList<>();
        Publicacion nuevaPublicacion = new Texto(nombre, descripcion,0, fuenteInput,cantCararcInput,tamañoImput,hashtags,comentariosInput);

        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz

        mostrarPublicacion(indice);
    }

    private void agregarPublicacionImagen() {
        // Solicitar el nombre de la nueva publicación de imagen
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        // Solicitar la descripción de la nueva publicación de imagen
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de imagen:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

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
        Publicacion nuevaPublicacion = new Imagen(nombre, descripcion,0,resolucionInput,altoInput,anchoInput,hashtags,comentariosInput );

        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz
        mostrarPublicacion(indice);
    }

    private void agregarPublicacionVideo() {
        // Solicitar el nombre de la nueva publicación de video
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        // Solicitar la descripción de la nueva publicación de video
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de video:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

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
        Publicacion nuevaPublicacion = new Video(nombre, descripcion,0,resolucionImput,duracionInput,cantCuadrosInput,hashtags,comentariosInput );

        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz
        mostrarPublicacion(indice);
    }

    private void agregarPublicacionAudio() {
        // Solicitar el nombre de la nueva publicación de audio
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva publicación de audio:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

        // Solicitar la descripción de la nueva publicación de audio
        String descripcion = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nueva publicación de audio:", "Agregar Publicación", JOptionPane.PLAIN_MESSAGE);

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
        Publicacion nuevaPublicacion = new Audio(nombre, descripcion,0,duracionImput,velocidad_bitsImput,hashtags,comentariosInput );
        // Agregar la nueva publicación a la lista
        listaPublicacion.add(nuevaPublicacion);

        // Mostrar la nueva publicación en la interfaz
        indice = listaPublicacion.size() - 1;
        mostrarPublicacion(indice);
    }
    public void onReportes(){
        reporte.prueba(listaPublicacion);
    }
    public void onRepro(){
        Repro dialog = new Repro(listaPublicacion);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    private void onX() {
        dispose();
    }

    private void OnPausar(Publicacion publicacion){
        ((Durable)publicacion).detener(this);
    }
    private void OnAvanzar(Publicacion publicacion){
        ((Durable)publicacion).avanzar(0,this);//HAY QUE LLAMAR A AVANZAR DE AUDIO/VIDEO Y QUE SE HAGA FALSA O VERDADERA AHI
    }
    private void Onfiltro(){
        Publicacion publicacion = listaPublicacion.get(indice);
        String tipo = publicacion.getClass().getSimpleName();

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
        int duracion=0;
        filtro.setVisible(false);
        String contador = (indice+1) + "/" + listaPublicacion.toArray().length;
        Publicacion publicacion = listaPublicacion.get(i);
        String tipo = publicacion.getClass().getSimpleName();
        String texto = "Tipo: " + tipo + "\n";
        texto += "Nombre: " + publicacion.getNombre() + "\n";
        texto += "Descripción: " + publicacion.getDescripcionPost() + "\n";
        texto += "Me gusta: " + publicacion.getCantMG() + "\n";

        // Mostrar los datos específicos según el tipo de publicación
        if (publicacion instanceof Texto) {
            Texto textoPublicacion = (Texto) publicacion;
            texto += "Fuente: " + textoPublicacion.getFuente() + "\n";
            texto += "Cantidad de caracteres: " + textoPublicacion.getCantCaracteres() + "\n";
            texto += "Tamaño de fuente: " + textoPublicacion.getTamañoFuente() + "\n";
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
            textdurable.setVisible(false);
            AVANZARButton.setVisible(false);
            PAUSARButton.setVisible(false);
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