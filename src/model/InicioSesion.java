package model;

import interfaz.Cliente;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 */
public class InicioSesion {
    public ArrayList<Cliente> cuentas;

    public InicioSesion(){
        cuentas = new ArrayList<>();
        try {

            File fXmlFile = new File("C:\\Users\\David\\IDE_Projects\\IdeaProjects\\Tarea_Programada_2_POO\\src\\model\\Cuentas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("person");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

            //    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                  //  System.out.println("First Name : " + eElement.getElementsByTagName("firstName").item(0).getTextContent());
                   // System.out.println("Last Name : " + eElement.getElementsByTagName("lastName").item(0).getTextContent());
                    //System.out.println("Salary : " + eElement.getElementsByTagName("clave").item(0).getTextContent());
                    String usuario=eElement.getElementsByTagName("usuario").item(0).getTextContent();
                    String clave= eElement.getElementsByTagName("clave").item(0).getTextContent();
                    String numeroCelular= eElement.getElementsByTagName("numeroCelular").item(0).getTextContent();
                    String direccion= eElement.getElementsByTagName("direccion").item(0).getTextContent();
                    String esAdmin= eElement.getElementsByTagName("admin").item(0).getTextContent();

                    Cliente actual = new Cliente(usuario,clave,numeroCelular,direccion,esAdmin);
                    cuentas.add(actual);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarCuenta(String usuario, String contrasenna){
        int iteraciones = cuentas.size()-1;
        while(iteraciones>=0) {
            Cliente actual = cuentas.get(iteraciones);
            if (actual.getClave().equals(contrasenna) && actual.getUsuario().equals(usuario))
                return actual;
            iteraciones--;
        }
        return null;
    }
    public void abrirConexion() {
        try {
            Socket cliente = new Socket("localhost", 8080);
        }
        catch (IOException e) {System.out.println(e);}
    }
}
