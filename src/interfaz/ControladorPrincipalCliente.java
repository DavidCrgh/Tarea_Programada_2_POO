package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

import interfaz.Person;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Bryan on 11/10/2016.
 */
public class ControladorPrincipalCliente implements Initializable {
    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn columnaCodigoCliente;
    @FXML
    private TableColumn columnaNombreCliente;
    @FXML
    private TableColumn columnaCaloriasCliente;
    @FXML
    private TableColumn columnaPrecioCliente;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

    }
}
