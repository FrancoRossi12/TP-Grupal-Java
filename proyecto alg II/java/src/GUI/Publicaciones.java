package GUI;

import TipoPublicacion.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Publicaciones extends JDialog {
    private JPanel contentPane;
    private JButton next;
    private JButton prev;
    private JLabel publicacionLabel;
    private int indice = 0;
    private List<Publicacion> listaPublicacion;

    public Publicaciones(List<Publicacion> listaPublicacion) {
        setTitle("Publicaciones");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(prev);

        this.listaPublicacion = listaPublicacion;

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
                onNext();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onX();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        mostrarPublicacion();
    }

    public Publicaciones() {

    }

    private void onX() {
        dispose();
    }

    private void onPrev() {
        if (indice > 0) {
            indice--;
            mostrarPublicacion();
        }
    }

    private void onNext() {
        if (indice < listaPublicacion.size() - 1) {
            indice++;
            mostrarPublicacion();
        }
    }

    private static List<Publicacion> cargarPublicacionesDesdeXML() {
        List<Publicacion> listaPublicacion = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("proyecto alg II/java/src/Swing/Publicaciones.xml");

            NodeList publicacionNodes = ((Document) document).getElementsByTagName("publicacion");
            for (int i = 0; i < ((NodeList) publicacionNodes).getLength(); i++) {
                Node publicacionNode = publicacionNodes.item(i);
                if (publicacionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element publicacionElement = (Element) publicacionNode;
                    String tipo = publicacionElement.getAttribute("tipo");

                    if (tipo.equals("Texto")) {
                        String nombre = publicacionElement.getAttribute("nombre");
                        String descripcion = publicacionElement.getAttribute("descripcion");
                        int cantMG = Integer.parseInt(publicacionElement.getAttribute("cantMG"));
                        String fuente = publicacionElement.getAttribute("fuente");
                        int cantCaracteres = Integer.parseInt(publicacionElement.getAttribute("cantCaracteres"));
                        int tamañoFuente = Integer.parseInt(publicacionElement.getAttribute("tamañoFuente"));

                        listaPublicacion.add(new Texto(nombre, descripcion, cantMG, fuente, cantCaracteres, tamañoFuente));
                    } else if (tipo.equals("Imagen")) {
                        String nombre = publicacionElement.getAttribute("nombre");
                        String descripcion = publicacionElement.getAttribute("descripcion");
                        int cantMG = Integer.parseInt(publicacionElement.getAttribute("cantMG"));
                        int resolucion =Integer.parseInt(publicacionElement.getAttribute("resolucion"));
                        int alto = Integer.parseInt(publicacionElement.getAttribute("alto"));
                        int ancho = Integer.parseInt(publicacionElement.getAttribute("ancho"));

                        listaPublicacion.add(new Imagen(nombre, descripcion, cantMG,resolucion, alto, ancho));
                    } else if (tipo.equals("Audio")) {
                        String nombre = publicacionElement.getAttribute("nombre");
                        String descripcion = publicacionElement.getAttribute("descripcion");
                        int cantMG = Integer.parseInt(publicacionElement.getAttribute("cantMG"));
                        int velocidad_bits = Integer.parseInt(publicacionElement.getAttribute("velocidad_bits"));
                        int duracion = Integer.parseInt(publicacionElement.getAttribute("duracion"));

                        listaPublicacion.add(new Audio(nombre, descripcion, cantMG,duracion, velocidad_bits));
                    } else if (tipo.equals("Video")) {
                        String nombre = publicacionElement.getAttribute("nombre");
                        String descripcion = publicacionElement.getAttribute("descripcion");
                        int cantMG = Integer.parseInt(publicacionElement.getAttribute("cantMG"));
                        int resolucion = Integer.parseInt(publicacionElement.getAttribute("resolucion"));
                        int duracion = Integer.parseInt(publicacionElement.getAttribute("duracion"));
                        int cantcuadros = Integer.parseInt(publicacionElement.getAttribute("cantcuadros"));

                        listaPublicacion.add(new Video(nombre, descripcion, cantMG,resolucion, duracion, cantcuadros));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaPublicacion;
    }

    private void mostrarPublicacion() {
        Publicacion publicacion = listaPublicacion.get(indice);
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
        } else if (publicacion instanceof Audio) {
            Audio audioPublicacion = (Audio) publicacion;
            texto += "Velocidad Bits: " + audioPublicacion.getVelocidad_bits() + "\n";
            texto += "Duracion: " + audioPublicacion.getDuracion() + "\n";

        } else if (publicacion instanceof Video) {
            Video videoPublicacion = (Video) publicacion;
            texto += "Resolucion: " + videoPublicacion.getResolucion() + "\n";
            texto += "Cantidad de cuadros: " + videoPublicacion.getCantcuadros() + "\n";
            texto += "Duracion: " + videoPublicacion.getDuracion() + "\n";
        }

        publicacionLabel.setText(texto);
    }
}