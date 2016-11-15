package model;

import interfaz.Person;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 */
public class InicioSesion {
    private ArrayList<Person> cuentas;

    public InicioSesion(){
        try {

            File fXmlFile = new File("C:\\Users\\Bryan\\Desktop\\Tarea_Programada_2_POO\\src\\model\\Cuentas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("person");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstName").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastName").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("clave").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    private static void printNote(NodeList nodeList) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());
                if (tempNode.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());
                    }
                }
                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());
                }
                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
            }
        }
    }
    */
    public Person buscarCuenta(String usuario, String contrasenna){
        int iteraciones = cuentas.size();
        while(iteraciones>=0) {
            Person actual = cuentas.get(iteraciones);
            if (actual.getClave().equals(contrasenna) && actual.getUsuario().equals(usuario))
                return actual;
            iteraciones--;
        }
        return null;
    }
}
