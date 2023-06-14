/*package GUI;

import TipoPublicacion.Publicacion;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Estadisticas extends JDialog {
    private JPanel contentPane;
    private JTextPane estadisticasPane;

    public Estadisticas(List<Publicacion> lista) {
        setTitle("Estadsiticas de las Publicaciones de Perfil");
        setSize(1080, 720);
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        mostrarEstadsiticas(lista);
    }

    private void mostrarEstadsiticas(List<Publicacion> lista){
        String estadis ="Estadisticas";


        estadisticasPane.setText(estadis);
    }

}*/
package GUI;

import TipoPublicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Estadisticas extends JDialog {
    private JPanel contentPane;
    private JTextPane statisticsPane;
    private JProgressBar textProgressBar;
    private JProgressBar imageProgressBar;
    private JProgressBar videoProgressBar;
    private JProgressBar audioProgressBar;

    public Estadisticas(List<Publicacion> listaPublicacion) {
        setTitle("Estadísticas");
        setContentPane(contentPane);
        setModal(true);
        setSize(1080, 720);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose();
            }
        });

        mostrarEstadisticas(listaPublicacion);
    }

    private void mostrarEstadisticas(List<Publicacion> listaPublicacion) {
        StringBuilder sb = new StringBuilder();
        sb.append("Estadísticas de las publicaciones:\n\n");
        sb.append("Total de publicaciones: ").append(listaPublicacion.size()).append("\n");

        int textCount = contarPublicacionesTexto(listaPublicacion);
        textProgressBar.setMaximum(listaPublicacion.size());
        textProgressBar.setValue(textCount);
        textProgressBar.setString("Publicaciones tipo Texto: "+ textCount + "/" + listaPublicacion.size());
        textProgressBar.setStringPainted(true);
        textProgressBar.setForeground(Color.BLUE);

        int imageCount = contarPublicacionesImagen(listaPublicacion);
        imageProgressBar.setMaximum(listaPublicacion.size());
        imageProgressBar.setValue(imageCount);
        imageProgressBar.setString("Publicaciones tipo Imagen: "+ imageCount + "/" + listaPublicacion.size());
        imageProgressBar.setStringPainted(true);
        imageProgressBar.setForeground(Color.RED);

        int videoCount = contarPublicacionesVideo(listaPublicacion);
        videoProgressBar.setMaximum(listaPublicacion.size());
        videoProgressBar.setValue(videoCount);
        videoProgressBar.setString("Publicaciones tipo Video: "+ videoCount + "/" + listaPublicacion.size());
        videoProgressBar.setStringPainted(true);
        videoProgressBar.setForeground(Color.GREEN);

        int audioCount = contarPublicacionesAudio(listaPublicacion);
        audioProgressBar.setMaximum(listaPublicacion.size());
        audioProgressBar.setValue(audioCount);
        audioProgressBar.setString("Publicaciones tipo Audio: "+audioCount + "/" + listaPublicacion.size());
        audioProgressBar.setStringPainted(true);
        audioProgressBar.setForeground(Color.ORANGE);

        statisticsPane.setText(sb.toString());
    }

    private int contarPublicacionesTexto(List<Publicacion> listaPublicacion) {
        int contador = 0;
        for (Publicacion publicacion : listaPublicacion) {
            if (publicacion instanceof Texto) {
                contador++;
            }
        }
        return contador;
    }

    private int contarPublicacionesImagen(List<Publicacion> listaPublicacion) {
        int contador = 0;
        for (Publicacion publicacion : listaPublicacion) {
            if (publicacion instanceof Imagen) {
                contador++;
            }
        }
        return contador;
    }

    private int contarPublicacionesVideo(List<Publicacion> listaPublicacion) {
        int contador = 0;
        for (Publicacion publicacion : listaPublicacion) {
            if (publicacion instanceof Video) {
                contador++;
            }
        }
        return contador;
    }

    private int contarPublicacionesAudio(List<Publicacion> listaPublicacion) {
        int contador = 0;
        for (Publicacion publicacion : listaPublicacion) {
            if (publicacion instanceof Audio) {
                contador++;
            }
        }
        return contador;
    }
}
