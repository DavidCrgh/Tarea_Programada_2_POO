package model;


import sockets.client.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Francisco Contreras on 16/11/2016.
 */
public class Utilitarias {
    public static ArrayList<Platillo> cargarMenu() throws ParserConfigurationException, IOException, SAXException{
        ArrayList<Platillo> platillos = new ArrayList<>();
        File fXmlFile = new File("recursos\\Menu.xml" );
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("producto");

        for(int i = 0; i < nList.getLength(); i++){
            Node nNode = nList.item(i);
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element)nNode;
                String codigo = eElement.getElementsByTagName("codigo").item(0).getTextContent();
                String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                String descripcion = eElement.getElementsByTagName("descripcion").item(0).getTextContent();
                int tamanoPorcion = Integer.parseInt(eElement.getElementsByTagName("tamanoPorcion").item(0).getTextContent());
                int piezasPorPorcion = Integer.parseInt(eElement.getElementsByTagName("piezasPorPorcion").item(0).getTextContent());
                int caloriasPorPorcion = Integer.parseInt(eElement.getElementsByTagName("caloriasPorPorcion").item(0).getTextContent());
                int precio = Integer.parseInt(eElement.getElementsByTagName("precio").item(0).getTextContent());
                String imagen = eElement.getElementsByTagName("imagen").item(0).getTextContent();
                String tagDisponible = eElement.getElementsByTagName("disponible").item(0).getTextContent();
                boolean disponible;
                if(tagDisponible.equals("Si")){
                    disponible = true;
                } else{
                    disponible = false;
                }

                Platillo platilloActual = new Platillo(codigo,nombre,descripcion,tamanoPorcion,caloriasPorPorcion,piezasPorPorcion,precio,imagen,disponible);
                platillos.add(platilloActual);
            }
        }

        return platillos;
    }

    public void guardarUsuarioXML(String usuario, String contrasenna, String numeroCelular, String direccion, boolean quienRegistra)throws Exception{

        File archivoXML = new File("recursos\\Cuentas.xml");
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(archivoXML);

        NodeList listaClientes=doc.getElementsByTagName("clients");
        Element raiz= (Element)listaClientes.item(0);

        Element nuevoCliente=doc.createElement("person");
        raiz.appendChild(nuevoCliente);

        Element usuarioCliente= doc.createElement("usuario");
        usuarioCliente.setTextContent(usuario);
        nuevoCliente.appendChild(usuarioCliente);

        Element contrasennaCliente =doc.createElement("clave");
        contrasennaCliente.setTextContent(contrasenna);
        nuevoCliente.appendChild(contrasennaCliente);

        Element numeroCliente = doc.createElement("numeroCelular");
        numeroCliente.setTextContent(numeroCelular);
        nuevoCliente.appendChild(numeroCliente);

        Element direccionCliente = doc.createElement("direccion");
        direccionCliente.setTextContent(direccion);
        nuevoCliente.appendChild(direccionCliente);

        Element adminCliente = doc.createElement("admin");
        adminCliente.setTextContent(registroCuenta(quienRegistra));
        nuevoCliente.appendChild(adminCliente);


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File outputFile = new File("recursos\\Cuentas.xml");
        StreamResult result = new StreamResult(outputFile );
        transformer.transform(source, result);


    }

    public ArrayList<Usuario> cargarCuentas ()throws Exception {
        ArrayList<Usuario> cuentas = new ArrayList<>();
        File fXmlFile = new File("recursos\\Cuentas.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("person");

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                String usuario = eElement.getElementsByTagName("usuario").item(0).getTextContent();
                String clave = eElement.getElementsByTagName("clave").item(0).getTextContent();
                String numeroCelular = eElement.getElementsByTagName("numeroCelular").item(0).getTextContent();
                String direccion = eElement.getElementsByTagName("direccion").item(0).getTextContent();
                String esAdmin = eElement.getElementsByTagName("admin").item(0).getTextContent();

                Usuario actual = new Usuario(usuario, clave, numeroCelular, direccion, esAdmin);
                cuentas.add(actual);
            }

        }
        return cuentas;
    }

    private String registroCuenta(boolean quienRegistra){
        if(quienRegistra)
            return "true";
        return "false";
    }

    public static void reconstruirMenuXML(ArrayList<Platillo> platillos){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element raizXML = doc.createElement("menu");
            doc.appendChild(raizXML);

            for (Platillo platillo: platillos) {
                Element producto = doc.createElement("producto");
                raizXML.appendChild(producto);

                Element codigo = doc.createElement("codigo");
                codigo.appendChild(doc.createTextNode(platillo.getCodigo()));
                producto.appendChild(codigo);

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(platillo.getNombre()));
                producto.appendChild(nombre);

                Element descripcion = doc.createElement("descripcion");
                descripcion.appendChild(doc.createTextNode(platillo.getDescripcion()));
                producto.appendChild(descripcion);

                Element tamanoPorcion = doc.createElement("tamanoPorcion");
                tamanoPorcion.appendChild(doc.createTextNode(""+platillo.getTamanoPorcion()));
                producto.appendChild(tamanoPorcion);

                Element piezasPorPorcion = doc.createElement("piezasPorPorcion");
                piezasPorPorcion.appendChild(doc.createTextNode(""+platillo.getPiezasPorcion()));
                producto.appendChild(piezasPorPorcion);

                Element caloriasPorPorcion = doc.createElement("caloriasPorPorcion");
                caloriasPorPorcion.appendChild(doc.createTextNode(""+platillo.getCaloriasPorcion()));
                producto.appendChild(caloriasPorPorcion);

                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode(""+platillo.getPrecio()));
                producto.appendChild(precio);

                Element imagen = doc.createElement("imagen");
                imagen.appendChild(doc.createTextNode(platillo.getImagen()));
                producto.appendChild(imagen);

                Element disponible = doc.createElement("disponible");
                disponible.appendChild(doc.createTextNode(platillo.getDisponibleString()));
                producto.appendChild(disponible);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("recursos\\Menu.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
