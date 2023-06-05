package GUI;

import Interfaz.Filtrable;
import TipoPublicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Publicaciones extends JDialog {
    private JPanel contentPane;
    private JButton next;
    private JButton prev;

    private JLabel cantPub;
    private JTextPane textPane1;
    private JButton filtro;
    private JTextPane textdurable;
    private JPanel durable;
    private JButton PAUSARButton;
    private JButton AVANZARButton;

    int filtroaplicado = 0;
    private static int indice = 0;
    private List<Publicacion> listaPublicacion;

    public Publicaciones(List<Publicacion> ListaPublicacion) {

        this.listaPublicacion = ListaPublicacion;
        setTitle("Publicaciones");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(prev);
        setSize(1080, 720);

        Font font = textPane1.getFont();
        Font newFont = font.deriveFont(font.getSize() + 10f); // Aumentar el tamaño en 2 puntos
        textPane1.setFont(newFont);

        mostrarPublicacion(indice);
        PAUSARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OnPausar();
            }
        });
        AVANZARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OnAvanzar();
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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onX();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onX() {
        dispose();
    }

    private void OnPausar(){

    }
    private void OnAvanzar(){

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
        int duracion;
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
            ;

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
            texto += "Hashtags:\n";
            for (String hashtag : hashtags) {
                texto += "#" + hashtag ;
            }
        }else{texto += "Sin hashtags";}

        List<String> comentarios = publicacion.getComentarios();
        if (!comentarios.isEmpty()) {
            texto += "\nComentarios:\n";
            for (String comentario : comentarios) {
                texto += comentario + "\n";
            }
        }else{texto += "Sin comentario";}


        cantPub.setText(contador);
        textPane1.setText(texto);

        if(publicacion instanceof Video || publicacion instanceof Audio){
        textdurable.setVisible(true);
        AVANZARButton.setVisible(true);
        PAUSARButton.setVisible(true);
        if(publicacion instanceof Video){
            duracion = ((Video) publicacion).getDuracion();
        }else{
            duracion = ((Audio) publicacion).getDuracion();
        }
            mostrarDuracionPublicacion(duracion);
        }else{
            textdurable.setVisible(false);
            AVANZARButton.setVisible(false);
            PAUSARButton.setVisible(false);
        }
    }
    private void mostrarDuracionPublicacion(int duracion) {

        for (int j = 0; j <= duracion; j++) {

            String duraciontexto = j + " --------------------- " + duracion;
            textdurable.setText(duraciontexto);
        }
    }

}