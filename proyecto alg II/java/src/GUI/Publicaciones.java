package GUI;

import TipoPublicacion.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    private static int indice = 0;
    private List<Publicacion> listaPublicacion;

    public Publicaciones(List<Publicacion> listaPublicacion) {


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

        mostrarPublicacion(indice);
    }

    public Publicaciones() {
        setTitle("Publicaciones");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(prev);
        setSize(1080, 720);
        listaPublicacion=cargarPublicacionesDesdeXML();
        mostrarPublicacion(indice);
    }

    private void onX() {
        dispose();
    }

    private void onPrev() {
        if (indice > 0) {
            indice--;
        } else {
            indice = listaPublicacion.size() - 1; // Mostrar el último elemento si se alcanza el principio de la lista
        }
        System.out.println("LLegue");
        mostrarPublicacion(indice);
    }

    private void onNext() {
        if (indice < listaPublicacion.size() - 1) {
            indice++;
        } else {
            indice = 0; // Mostrar el primer elemento si se alcanza el final de la lista
        }
        System.out.println("LLegue");
        mostrarPublicacion(indice);
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
                    String nombre = publicacionElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String descripcion = publicacionElement.getElementsByTagName("descripcionPost").item(0).getTextContent();
                    int cantMG = parseOptionalInt(getTextContent(publicacionElement, "cantMG"));
                    if (tipo.equals("texto")) {
                        String fuente = publicacionElement.getElementsByTagName("fuente").item(0).getTextContent();
                        int cantCaracteres = parseOptionalInt(getTextContent(publicacionElement,"cantCaracteres"));
                        int tamañoFuente = parseOptionalInt(getTextContent(publicacionElement,"tamañoFuente"));

                        listaPublicacion.add(new Texto(nombre, descripcion, cantMG, fuente, cantCaracteres, tamañoFuente));
                    } else if (tipo.equals("imagen")) {
                        String resolucion = publicacionElement.getElementsByTagName("resolucion").item(0).getTextContent();
                        int alto = parseOptionalInt(getTextContent(publicacionElement,"alto"));
                        int ancho = parseOptionalInt(getTextContent(publicacionElement,"ancho"));

                        listaPublicacion.add(new Imagen(nombre, descripcion, cantMG, resolucion, alto, ancho));
                    } else if (tipo.equals("audio")) {
                        int velocidad_bits = parseOptionalInt(getTextContent(publicacionElement,"velocidad_bits"));
                        int duracion = parseOptionalInt(getTextContent(publicacionElement,"duracion"));

                        listaPublicacion.add(new Audio(nombre, descripcion, cantMG,duracion, velocidad_bits));
                    } else if (tipo.equals("video")) {
                        String resolucion = publicacionElement.getElementsByTagName("resolucion").item(0).getTextContent();
                        int duracion = parseOptionalInt(getTextContent(publicacionElement,"duracion"));
                        int cantcuadros = parseOptionalInt(getTextContent(publicacionElement,"cantCuadros"));

                        listaPublicacion.add(new Video(nombre, descripcion, cantMG, resolucion, duracion, cantcuadros));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaPublicacion;
    }
    public static int parseOptionalInt(String value) {
        if (value != null && !value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // El valor no es un número válido, se puede manejar el error aquí
                e.printStackTrace();
            }
        }
        return 0; // Valor predeterminado si el valor es vacío o no válido
    }

    public static String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                return node.getTextContent();
            }
        }
        return ""; // Valor predeterminado si el contenido no está presente
    }
    private void mostrarPublicacion(int i) {
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
        System.out.print(texto);
        publicacionLabel.setText(texto);
    }
}