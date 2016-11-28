package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ListaPedidos;

import javax.annotation.Resources;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Francisco Contreras on 27/11/2016.
 */
public class ControladorRelacionesPorcentuales implements Initializable {

    @FXML
    private ListView tablaRelaciones;
    @FXML
    public Label labelTotalPedidos;


    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        tablaRelaciones.setEditable(true);
    }

    public void construirTabla(ArrayList<String> lista){
        ObservableList<String> items = FXCollections.observableArrayList(lista);
        tablaRelaciones.setItems(items);
    }

}
