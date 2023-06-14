package GUI;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Perfil.Reportes;
import Perfil.Album;
import TipoPublicacion.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static GUI.Perfil.getTextContent;
import static GUI.Perfil.parseOptionalInt;

/**
 * The type Albumvisual.
 */
public class Albumvisual extends JDialog {
    private JPanel contentPane;
    private JButton agregar;
    private JButton eliminar;
    private JComboBox<Object> comboBoxAlbumes;
    private JPanel box;
    private JButton albumButton;
    private JButton subalbumButton;
    private JButton reportesButton;

    private Reportes reporte = new Reportes();
    private List<Album> listaAlbumes;

    private List<Publicacion> ListaPublicacion;
    private List<Publicacion> listaPublicacioncompleta;
    private List<Publicacion> sublistaPublicacion;
    private Document documentoXML;

    /**
     * Instantiates a new Albumvisual.
     *
     * @param ListaAlbumes     the lista albumes
     * @param listaPublicacion the lista publicacion
     */
    public Albumvisual(List<Album> ListaAlbumes,List<Publicacion> listaPublicacion) {
        this.listaAlbumes=ListaAlbumes;
        setTitle("Album");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(agregar);
        cargarDatosDesdeXML();
        listaPublicacioncompleta = listaPublicacion;
        mostrarAlbumes();

        albumButton.addActionListener(e -> onalbumButton());
        subalbumButton.addActionListener(e -> onsubalbumButton());
        agregar.addActionListener(e -> onAgregar());
        reportesButton.addActionListener(e -> onReportes());

        eliminar.addActionListener(e -> onEliminar());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onX();
            }
        });

        contentPane.registerKeyboardAction(e -> onX(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        try {
            // Cargar el archivo XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documentoXML = dBuilder.parse("proyecto alg II/java/src/Swing/Album.xml");
            documentoXML.getDocumentElement().normalize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * On reportes.
     */
    public void onReportes(){
        reporte.Reporte2(listaAlbumes);
    }

    private void onX(){
        dispose();
    }
    private void onalbumButton() {
        String nombreAlbum = (String) comboBoxAlbumes.getSelectedItem();
        Optional<Album> optionalAlbum = listaAlbumes.stream()
                .filter(album -> album.getNombreAlbum().equals(nombreAlbum))
                .findFirst();

        optionalAlbum.ifPresent(album -> {
            ListaPublicacion = album.getListaPublicacion();
            Publicaciones dialog = new Publicaciones(ListaPublicacion,listaPublicacioncompleta);
            dialog.pack();
            dialog.setVisible(true);
        });
    }

    private void onsubalbumButton() {
        String nombreAlbum = (String) comboBoxAlbumes.getSelectedItem();
        Optional<Album> optionalAlbum = listaAlbumes.stream()
                .filter(album -> album.getNombreAlbum().equals(nombreAlbum))
                .findFirst();

        optionalAlbum.ifPresent(album -> {
            sublistaPublicacion = album.getSubpublicaciones();
            Publicaciones dialog = new Publicaciones(sublistaPublicacion,listaPublicacioncompleta);
            dialog.pack();
            dialog.setVisible(true);
        });
    }

    private void onAgregar() {
        String nombreAlbum = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo album:", "Agregar Album", JOptionPane.PLAIN_MESSAGE);
        if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
            Album nuevoAlbum = new Album(nombreAlbum);
            listaAlbumes.add(nuevoAlbum);
            agregarAlbumXML(nombreAlbum);
            mostrarAlbumes();
        }
    }

    private void onEliminar() {
        String nombreAlbum = JOptionPane.showInputDialog(this, "Ingrese el nombre del album a eliminar:", "Eliminar Album", JOptionPane.PLAIN_MESSAGE);
        if (nombreAlbum != null && !nombreAlbum.isEmpty()) {
            Album albumEliminado = null;
            for (Album album : listaAlbumes) {
                if (album.getNombreAlbum().equals(nombreAlbum)) {
                    albumEliminado = album;
                    break;
                }
            }
            if (albumEliminado != null) {
                listaAlbumes.remove(albumEliminado);
                JOptionPane.showMessageDialog(this, "Album eliminado correctamente.", "Eliminar Album", JOptionPane.INFORMATION_MESSAGE);
                eliminarAlbumXML(nombreAlbum);
                mostrarAlbumes();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el album especificado.", "Eliminar Album", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void eliminarAlbumXML(String nombreAlbum) {
        try {
            NodeList albumNodes = documentoXML.getElementsByTagName("album");
            for (int i = 0; i < albumNodes.getLength(); i++) {
                Node albumNode = albumNodes.item(i);
                if (albumNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element albumElement = (Element) albumNode;
                    Node nombreNode = albumElement.getElementsByTagName("nombre").item(0);
                    String nombre = nombreNode.getTextContent();
                    if (nombre.equals(nombreAlbum)) {
                        albumNode.getParentNode().removeChild(albumNode);
                        guardarCambiosXML();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void agregarAlbumXML(String nombreAlbum) {
        try {
            Element albumElement = documentoXML.createElement("album");
            Element nombreElement = documentoXML.createElement("nombre");
            nombreElement.appendChild(documentoXML.createTextNode(nombreAlbum));
            albumElement.appendChild(nombreElement);

            Node albumsNode = documentoXML.getElementsByTagName("albums").item(0);
            albumsNode.appendChild(albumElement);

            guardarCambiosXML();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void guardarCambiosXML() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(documentoXML);
            StreamResult result = new StreamResult("proyecto alg II/java/src/Swing/Album.xml");
            transformer.transform(source, result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void cargarDatosDesdeXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("proyecto alg II/java/src/Swing/Album.xml");
            NodeList albumNodes = document.getElementsByTagName("album");
            listaAlbumes = new ArrayList<>(); // Crear la lista de albumes fuera del bucle

            for (int i = 0; i < albumNodes.getLength(); i++) {
                Node albumNode = albumNodes.item(i);
                if (albumNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element albumElement = (Element) albumNode;

                    // Obtener el nombre del album
                    String nombreAlbum = getTextValue(albumElement, "nombre");
                    Album album = new Album(nombreAlbum);

                    // Cargar la lista principal
                    NodeList listaNodes = albumElement.getElementsByTagName("lista");
                    if (listaNodes.getLength() > 0) {
                        Element listaElement = (Element) listaNodes.item(0);
                        NodeList publicacionNodes = listaElement.getElementsByTagName("publicacion");
                        for (int j = 0; j < publicacionNodes.getLength(); j++) {
                            Element publicacionElement = (Element) publicacionNodes.item(j);
                            Publicacion publicacion = crearPublicacionDesdeElement(publicacionElement);
                            album.agregarPublicacion(publicacion);
                        }
                    }

                    // Cargar la sublista
                    Element sublistaElement = (Element) albumElement.getElementsByTagName("sublista").item(0);
                    if (sublistaElement != null) {
                        NodeList subpublicacionNodes = sublistaElement.getElementsByTagName("publicacion");
                        for (int j = 0; j < subpublicacionNodes.getLength(); j++) {
                            Element subpublicacionElement = (Element) subpublicacionNodes.item(j);
                            Publicacion subpublicacion = crearPublicacionDesdeElement(subpublicacionElement);
                            album.agregarSubpublicacion(subpublicacion);
                        }
                    }

                    listaAlbumes.add(album); // Agregar el album a la lista

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private Publicacion crearPublicacionDesdeElement(Element publicacionElement) {
        Publicacion pub = null;
        if (publicacionElement.getNodeType() == Node.ELEMENT_NODE) {

            String tipo = publicacionElement.getAttribute("tipo");
            String nombre = publicacionElement.getElementsByTagName("nombre").item(0).getTextContent();
            String descripcion = publicacionElement.getElementsByTagName("descripcionPost").item(0).getTextContent();
            String fechaSubida = publicacionElement.getElementsByTagName("fechaSubida").item(0).getTextContent();
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
                int tamanioFuente = parseOptionalInt(getTextContent(publicacionElement, "tamanioFuente"));

                pub = new Texto(nombre, descripcion, fechaSubida, cantMG, fuente, cantCaracteres, tamanioFuente, hashtags, comentarios);
            } else if (tipo.equals("imagen")) {
                String resolucion = publicacionElement.getElementsByTagName("resolucion").item(0).getTextContent();
                int alto = parseOptionalInt(getTextContent(publicacionElement, "alto"));
                int ancho = parseOptionalInt(getTextContent(publicacionElement, "ancho"));

                pub = new Imagen(nombre, descripcion, fechaSubida, cantMG, resolucion, alto, ancho, hashtags, comentarios);
            } else if (tipo.equals("audio")) {
                int velocidad_bits = parseOptionalInt(getTextContent(publicacionElement, "velocidad_bits"));
                int duracion = parseOptionalInt(getTextContent(publicacionElement, "duracion"));

                pub = new Audio(nombre, descripcion, fechaSubida, cantMG, duracion, velocidad_bits, hashtags, comentarios);
            } else if (tipo.equals("video")) {
                String resolucion = publicacionElement.getElementsByTagName("resolucion").item(0).getTextContent();
                int duracion = parseOptionalInt(getTextContent(publicacionElement, "duracion"));
                int cantcuadros = parseOptionalInt(getTextContent(publicacionElement, "cantCuadros"));

                pub = new Video(nombre, descripcion, fechaSubida, cantMG, resolucion, duracion, cantcuadros, hashtags, comentarios);
            }

        }

        return pub;

    }

    private String getTextValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element tagElement = (Element) nodeList.item(0);
            if (tagElement != null && tagElement.getFirstChild() != null) {
                return tagElement.getFirstChild().getNodeValue();
            }
        }
        return "";
    }
    private void mostrarAlbumes(){

        comboBoxAlbumes.removeAllItems();
        for (Album album : listaAlbumes) {
            comboBoxAlbumes.addItem(album.getNombreAlbum());
        }
        box.setLayout(new FlowLayout());
        box.add(comboBoxAlbumes);
        box.revalidate();
        box.repaint();
    }

}