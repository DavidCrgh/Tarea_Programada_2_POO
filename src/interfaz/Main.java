package interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<Person> cuentas;

    Main(){
        cuentas=new ArrayList<Person>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        primaryStage.setTitle("Inicio Sesión");
        primaryStage.setScene(new Scene(root, 505, 260));
        primaryStage.show();

    }

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("Cuentas.xml");
            NodeList list = doc.getElementsByTagName("person");
            for(int i=0; i<list.getLength(); i++){
                NodeList datos = (NodeList) list.item(i).getChildNodes();
                for(int j=0; j<datos.getLength(); j++){
                    Element aux = (Element)datos.item(j);
                    Person auxPerson = new Person();

                }
            }
            launch(args);
        }
        catch (SAXException e1) {
            e1.printStackTrace();
        }
        catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
