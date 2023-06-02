package GUI;
import Perfil.PerfilInstagram;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import TipoPublicacion.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Perfil extends JDialog{
    private JPanel contentPane;
    private JButton publicacionesButton;
    private JButton albunesButton;
    private JLabel descripcion;
    private JLabel seguidos;
    private JLabel seguidores;
    private JLabel Albunes;
    private JLabel Publicaciones;
    private JLabel usuario;

    private String nombreUsuario;
    private String descripcionPerfil;
    private int cantidadSeguidores;
    private int cantidadSeguidos;
    private int cantidadAlbunes;
    private int cantidadPublicaciones;
    private PerfilInstagram perfilInstagram;
    private List<Publicacion> listaPublicacion;
    public Perfil() {
        setTitle("Perfil");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(publicacionesButton);

        // Establecer tamaño mínimo
        publicacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPublicaciones();
            }
        });

        albunesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAlbunes();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onX();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onX();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        Dimension minimumSize;
        minimumSize = new Dimension(1080, 1920);
        contentPane.setMinimumSize(minimumSize);

        // Cargar datos del perfil
        listaPublicacion = cargarPublicacionesDesdeXML("Publicaciones");
        cargarDatosPerfil();
    }

    private void cargarDatosPerfil() {
        try {
            // Ruta al archivo XML
            String rutaArchivo = "proyecto alg II/java/src/Swing/Perfil.xml";

            // Crear el analizador de documentos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = dBuilder.parse(rutaArchivo);

            // Obtener el elemento raíz del perfil
            Element perfil = doc.getDocumentElement();

            // Obtener los datos del perfil
            nombreUsuario = perfil.getElementsByTagName("nombreUsuario").item(0).getTextContent();
            descripcionPerfil = perfil.getElementsByTagName("descripcion").item(0).getTextContent();
            cantidadSeguidores = Integer.parseInt(perfil.getElementsByTagName("seguidores").item(0).getTextContent());
            cantidadSeguidos = Integer.parseInt(perfil.getElementsByTagName("seguidos").item(0).getTextContent());
            cantidadAlbunes = Integer.parseInt(perfil.getElementsByTagName("cantAlbums").item(0).getTextContent());
            cantidadPublicaciones = Integer.parseInt(perfil.getElementsByTagName("cantPosts").item(0).getTextContent());

            perfilInstagram = new PerfilInstagram(nombreUsuario,descripcionPerfil,cantidadSeguidores,cantidadSeguidos,cantidadPublicaciones,cantidadAlbunes,listaPublicacion);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Manejo de excepciones
        }
        muestradatosPerfil();
    }

    void muestradatosPerfil(){

        usuario.setText(perfilInstagram.getNombreUsuario());
        descripcion.setText(perfilInstagram.getDescripcion());
        seguidores.setText(String.valueOf(perfilInstagram.getSeguidores()));
        seguidos.setText(String.valueOf(perfilInstagram.getSeguidos()));
        Albunes.setText(String.valueOf(perfilInstagram.getCantAlbums()));
        Publicaciones.setText(String.valueOf(perfilInstagram.getCantPosts()));

    }

    private void onPublicaciones() {

        // add your code here
        Publicaciones dialog = new Publicaciones(perfilInstagram.getListaPublicacion());
        dialog.pack();
        dialog.setVisible(true);
    }
    private void onX(){
        dispose();

    }

    private void onAlbunes() {
        // add your code here if necessary

        Album dialog = new Album();
        dialog.pack();
        dialog.setVisible(true);

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

    private static List<Publicacion> cargarPublicacionesDesdeXML(String NombreArch) {
        List<Publicacion> listaPublicacion = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("proyecto alg II/java/src/Swing/" + NombreArch + ".xml");

            NodeList publicacionNodes = ((Document) document).getElementsByTagName("publicacion");
            for (int i = 0; i < ((NodeList) publicacionNodes).getLength(); i++) {
                Node publicacionNode = publicacionNodes.item(i);
                if (publicacionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element publicacionElement = (Element) publicacionNode;
                    String tipo = publicacionElement.getAttribute("tipo");
                    String nombre = publicacionElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String descripcion = publicacionElement.getElementsByTagName("descripcionPost").item(0).getTextContent();
                    int cantMG = parseOptionalInt(getTextContent(publicacionElement, "cantMG"));

                    ArrayList<String> hashtags = new ArrayList<>();
                    NodeList hashtagNodes = publicacionElement.getElementsByTagName("hashtag");
                    for (int j = 0; j < hashtagNodes.getLength(); j++) {
                        Node hashtagNode = hashtagNodes.item(j);
                        if (hashtagNode.getNodeType() == Node.ELEMENT_NODE) {
                            hashtags.add(hashtagNode.getTextContent());
                        }
                    }

                    ArrayList<String> comentarios = new ArrayList<>();
                    NodeList comentarioNodes = publicacionElement.getElementsByTagName("comentario");
                    for (int j = 0; j < comentarioNodes.getLength(); j++) {
                        Node comentarioNode = comentarioNodes.item(j);
                        if (comentarioNode.getNodeType() == Node.ELEMENT_NODE) {
                            comentarios.add(comentarioNode.getTextContent());
                        }
                    }

                    if (tipo.equals("texto")) {
                        String fuente = publicacionElement.getElementsByTagName("fuente").item(0).getTextContent();
                        int cantCaracteres = parseOptionalInt(getTextContent(publicacionElement, "cantCaracteres"));
                        int tamañoFuente = parseOptionalInt(getTextContent(publicacionElement, "tamañoFuente"));

                        listaPublicacion.add(new Texto(nombre, descripcion, cantMG, fuente, cantCaracteres, tamañoFuente, hashtags, comentarios));
                    } else if (tipo.equals("imagen")) {
                        String resolucion = publicacionElement.getElementsByTagName("resolucion").item(0).getTextContent();
                        int alto = parseOptionalInt(getTextContent(publicacionElement, "alto"));
                        int ancho = parseOptionalInt(getTextContent(publicacionElement, "ancho"));

                        listaPublicacion.add(new Imagen(nombre, descripcion, cantMG, resolucion, alto, ancho, hashtags, comentarios));
                    } else if (tipo.equals("audio")) {
                        int velocidad_bits = parseOptionalInt(getTextContent(publicacionElement, "velocidad_bits"));
                        int duracion = parseOptionalInt(getTextContent(publicacionElement, "duracion"));

                        listaPublicacion.add(new Audio(nombre, descripcion, cantMG, duracion, velocidad_bits, hashtags, comentarios));
                    } else if (tipo.equals("video")) {
                        String resolucion = publicacionElement.getElementsByTagName("resolucion").item(0).getTextContent();
                        int duracion = parseOptionalInt(getTextContent(publicacionElement, "duracion"));
                        int cantcuadros = parseOptionalInt(getTextContent(publicacionElement, "cantCuadros"));

                        listaPublicacion.add(new Video(nombre, descripcion, cantMG, resolucion, duracion, cantcuadros, hashtags, comentarios));
                    }
                }
            }
            listaPublicacion.sort(Comparator.comparing(Publicacion::getNombre));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaPublicacion;
    }
}
