import javax.swing.*;
import java.awt.event.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField password;
    private JTextField Usuario;

    public Login() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // Obtener los valores ingresados por el usuario
        String usuarioIngresado = Usuario.getText();
        String contrasenaIngresada = new String(password.getPassword());

        // Cargar y analizar el archivo XML
        try {
            // Ruta al archivo XML (reemplaza con tu propia ruta)
            String rutaArchivo = "C:\\Users\\Bruno\\Documents\\GitHub\\TP-Grupal-Java\\proyecto alg II\\java\\src\\Swing\\Usuarios.xml";

            // Crear el analizador de documentos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = dBuilder.parse(rutaArchivo);

            // Obtener la lista de nodos de usuario
            NodeList nodeList = doc.getElementsByTagName("usuario");

            // Iterar sobre los nodos de usuario
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element elementoUsuario = (Element) nodeList.item(i);

                // Obtener el nombre de usuario y contraseña del nodo actual
                String usuario = elementoUsuario.getElementsByTagName("nombre").item(0).getTextContent();
                String contrasena = elementoUsuario.getElementsByTagName("contrasena").item(0).getTextContent();

                // Comparar los datos ingresados con los datos del archivo XML
                if (usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena)) {
                    // Autenticación exitosa
                    dispose();
                    // Agrega aquí el código para continuar con la lógica de tu aplicación
                    return;
                }
            }

            // Si se llega a este punto, la autenticación ha fallado
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error de autenticación", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            // Manejo de excepciones
        }
    }

    private void onCancel() { // cerrar programa
        // add your code here if necessary
        dispose();
    }


}
