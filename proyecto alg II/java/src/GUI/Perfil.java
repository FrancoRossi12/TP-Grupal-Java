package GUI;

import javax.swing.*;
import java.awt.event.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Perfil extends JDialog {
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

    public Perfil() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(publicacionesButton);

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

        // call onCancel() when cross is clicked
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

        // Cargar datos del perfil
        cargarDatosPerfil();
    }

    private void cargarDatosPerfil() {
        try {
            // Ruta al archivo XML (reemplaza con tu propia ruta)
            String rutaArchivo = "C:\\Users\\m\\Documents\\GitHub\\TP-Grupal-Java\\proyecto alg II\\java\\src\\Swing\\Perfil.xml";

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

            // Actualizar los componentes de la interfaz con los datos cargados
            usuario.setText(nombreUsuario);
            descripcion.setText(descripcionPerfil);
            seguidores.setText(String.valueOf(cantidadSeguidores));
            seguidos.setText(String.valueOf(cantidadSeguidos));
            Albunes.setText(String.valueOf(cantidadAlbunes));
            Publicaciones.setText(String.valueOf(cantidadPublicaciones));
        } catch (Exception ex) {
            ex.printStackTrace();
            // Manejo de excepciones
        }
    }

    private void onPublicaciones() {
        // add your code here
        Publicaciones dialog = new Publicaciones();
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
}

