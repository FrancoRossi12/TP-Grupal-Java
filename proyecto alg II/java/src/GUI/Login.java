package GUI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Login.
 */
public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField password;
    private JTextField Usuario;

    /**
     * Instantiates a new Login.
     */
    public Login() {
        setTitle("Login");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void onOK() {
        // Obtener los valores ingresados por el usuario
        String usuarioIngresado = Usuario.getText();
        String contrasenaIngresada = new String(password.getPassword());

        // Cargar y analizar el archivo XML
        try {
            // Ruta al archivo XML
            String rutaArchivo = "proyecto alg II/java/src/Swing/Usuarios.xml";

            // Crear el analizador de documentos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(rutaArchivo);

            // Obtener la lista de nodos de usuario
            NodeList nodeList = doc.getElementsByTagName("usuario");

            // Iterar sobre los nodos de usuario
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element elementoUsuario = (Element) nodeList.item(i);
                // Obtener el nombre de usuario y contrasenia del nodo actual
                String usuario = elementoUsuario.getElementsByTagName("nombre").item(0).getTextContent();
                String contrasena = elementoUsuario.getElementsByTagName("contrasena").item(0).getTextContent();
                // Comparar los datos ingresados con los datos del archivo XML
                if (usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena)) {
                    dispose();

                    Perfil dialog = new Perfil();
                    dialog.pack();
                    dialog.setVisible(true);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() { // cerrar programa
        dispose();
    }

}
