package interfaz;

import com.sun.org.glassfish.gmbal.DescriptorFields;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Platillo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Francisco Contreras on 18/11/2016.
 */
public class ControladorAgregarProducto implements Initializable {

    @FXML
    private TextField textoCodigo;
    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoDescripcion;
    @FXML
    private TextField textoTamanoPorcion;
    @FXML
    private TextField textoCaloriasPorcion;
    @FXML
    private TextField textoPiezasPorcion;
    @FXML
    private TextField textoCaloriasPieza;
    @FXML
    private TextField textoPrecio;
    @FXML
    private Button botonImagen;
    @FXML
    private RadioButton botonSi;
    @FXML
    private RadioButton botonNo;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {



    }

}
