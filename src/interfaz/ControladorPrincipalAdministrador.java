package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Platillo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 10-Nov-16.
 */
public class ControladorPrincipalAdministrador implements Initializable {
    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn columnaCodigo;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaCalorias;
    @FXML
    private TableColumn columnaPrecio;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        columnaCodigo.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("codigo")
        );
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("nombre")
        );
        columnaCalorias.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("caloriasPorcion")
        );
        columnaPrecio.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("precio")
        );
    }

    public void construirTabla(ArrayList<Platillo> platillos){
        tablaProductos.setItems(FXCollections.observableList(platillos));
    }
}
