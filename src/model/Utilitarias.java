package model;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Francisco Contreras on 16/11/2016.
 */
public class Utilitarias {


    public Utilitarias(){

    }


    public void escribirXML(String usuario,String contrasenna,String numeroCelular,String direccion,boolean quienRegistra)throws Exception{

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

    public ArrayList<Usuario> cargarCuentas (ArrayList<Usuario> cuentas)throws Exception {

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

}
