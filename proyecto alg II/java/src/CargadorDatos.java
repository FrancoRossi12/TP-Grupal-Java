import TipoPublicacion.Publicacion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class CargadorDatos {
    public static List<Publicacion> cargarPublicacionesDesdeArchivo() throws ParserConfigurationException, IOException, SAXException {
        return cargarPublicacionesDesdeArchivo(null);
    }

    public static List<Publicacion> cargarPublicacionesDesdeArchivo(String nombreArchivo) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("publicaciones.xml");
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("publicacion");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String nombre = element.getAttribute("nombre");
                String fecha = element.getElementsByTagName("fecha").item(0).getTextContent();
                int likes = Integer.parseInt(element.getElementsByTagName("likes").item(0).getTextContent());

                // Procesar el resto de los datos de la publicación
            }
        }


        return null;
    }
}