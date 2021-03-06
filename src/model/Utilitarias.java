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
 * Clase utilitaria se utiliza para escribir y leer de los archivos xml
 */
public class Utilitarias {
    /**
     * Parsea el xml del menu y lo conviente en un array de platillos
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
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

    /**
     * Guarda un nuevo usuario en el archivo xml
     * @param usuario nombre de usuario
     * @param contrasenna contraseña del usuario
     * @param numeroCelular numero celular del usuario
     * @param direccion direccion del usuario
     * @param quienRegistra dicta si el nuevo usuario es administrador o no
     * @throws Exception
     */
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

    /**
     * Agrega un nuevo platillo al xml del menu
     * @param platillo platillo por agregar
     */
    public static void agregarProducto(Platillo platillo){
        try {
            File archivoXML = new File("recursos\\Menu.xml");
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(archivoXML);

            NodeList listaMenu=doc.getElementsByTagName("menu");
            Element raiz= (Element)listaMenu.item(0);

            Element nuevoProducto=doc.createElement("producto");
            raiz.appendChild(nuevoProducto);

            Element codigoProducto= doc.createElement("codigo");
            codigoProducto.setTextContent(platillo.getCodigo());
            nuevoProducto.appendChild(codigoProducto);

            Element nombreProducto =doc.createElement("nombre");
            nombreProducto.setTextContent(platillo.getNombre());
            nuevoProducto.appendChild(nombreProducto);

            Element descripcionProducto = doc.createElement("descripcion");
            descripcionProducto.setTextContent(platillo.getDescripcion());
            nuevoProducto.appendChild(descripcionProducto);

            Element tamanoPorcionProducto = doc.createElement("tamanoPorcion");
            tamanoPorcionProducto.setTextContent(platillo.getTamanoPorcion()+"");
            nuevoProducto.appendChild(tamanoPorcionProducto);

            Element piezasPorcionProducto = doc.createElement("piezasPorPorcion");
            piezasPorcionProducto.setTextContent(platillo.getPiezasPorcion()+"");
            nuevoProducto.appendChild(piezasPorcionProducto);

            Element caloriasPorcionProducto = doc.createElement("caloriasPorPorcion");
            caloriasPorcionProducto.setTextContent(platillo.getCaloriasPorcion()+"");
            nuevoProducto.appendChild(caloriasPorcionProducto);

            Element precioProducto= doc.createElement("precio");
            precioProducto.setTextContent(platillo.getPrecio()+"");
            nuevoProducto.appendChild(precioProducto);

            Element imagenProducto = doc.createElement("imagen");
            imagenProducto.setTextContent(platillo.getImagen());
            nuevoProducto.appendChild(imagenProducto);

            Element productoDisponible = doc.createElement("disponible");
            productoDisponible.setTextContent(platillo.getDisponibleString());
            nuevoProducto.appendChild(productoDisponible);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File outputFile = new File("recursos\\Menu.xml");
            StreamResult result = new StreamResult(outputFile );
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    /**
     * Lee el archivo xml de los usuarios
     * @return usuarios que existen en el xml
     * @throws Exception
     */
    public static ArrayList<Usuario> cargarCuentas ()throws Exception {
        ArrayList<Usuario> cuentas = new ArrayList<>();
        File fXmlFile = new File("recursos\\Cuentas.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("person");

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

    /**
     *
     * @param quienRegistra boolean si es admi=true si es cliente=false
     * @return String, true=usuario es admi false=usuario no es admi
     */
    private String registroCuenta(boolean quienRegistra){
        if(quienRegistra)
            return "true";
        return "false";
    }

    /**
     * Reconstruye el xml del menu
     * @param platillos lista de platillos que van a estar en el xml
     */
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

    /**
     * Reconstruye el xml de las cuentas de usuario
     * @param usuarios cuentas de usuarios que van a estar en el xml
     */
    public static void reconstruirCuentasXML(ArrayList<Usuario> usuarios){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element raizXML = doc.createElement("clients");
            doc.appendChild(raizXML);

            for(Usuario usuario : usuarios){
                Element person = doc.createElement("person");
                raizXML.appendChild(person);

                Element username = doc.createElement("usuario");
                username.appendChild(doc.createTextNode(usuario.getUsuario()));
                person.appendChild(username);

                Element clave = doc.createElement("clave");
                clave.appendChild(doc.createTextNode(usuario.getClave()));
                person.appendChild(clave);

                Element numeroCelular = doc.createElement("numeroCelular");
                numeroCelular.appendChild(doc.createTextNode(usuario.getNumeroCelular()));
                person.appendChild(numeroCelular);

                Element direccion = doc.createElement("direccion");
                direccion.appendChild(doc.createTextNode(usuario.getDireccion()));
                person.appendChild(direccion);

                Element admin = doc.createElement("admin");
                if(usuario.isAdmin()){
                    admin.appendChild(doc.createTextNode("true"));
                } else{
                    admin.appendChild(doc.createTextNode("false"));
                }
                person.appendChild(admin);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("recursos\\Cuentas.xml"));

            transformer.transform(source, result);

            System.out.println("File saved!");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filtra los platillos del menu
     * @param platillos array de platillos para filtar
     * @param filtro filtro a utilizar
     * @return platillos que cumplen con el filtro
     */
    public static ArrayList<Platillo> filtrarPor(ArrayList<Platillo> platillos, String filtro){
        ArrayList<Platillo> filtrados = new ArrayList<>();
        for(Platillo platillo : platillos){
            if(platillo.getTipoCodigo().equals(filtro) || platillo.getDisponibleString().equals(filtro)){
                filtrados.add(platillo);
            }
        }
        return filtrados;
    }
}
