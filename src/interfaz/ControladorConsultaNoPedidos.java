package interfaz;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.LineaPedido;
import model.Platillo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bryan on 11/20/2016.
 */
public class ControladorConsultaNoPedidos implements Initializable{
    @FXML
    private Button cerrar;
    @FXML
    private TableColumn columnaCodigo;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableView tabla;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert columnaCodigo != null : "fx:id=\"columnaCodigo\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert cerrar != null : "fx:id=\"entregar\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert tabla != null : "fx:id=\"tabla\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";

        cerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cerrar.getScene().getWindow();
                stage.close();
            }
        });

        columnaCodigo.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("codigo")
        );
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("nombre")
        );
    }

    public void construirTabla(ArrayList<Platillo> platillos){
        for(int i=0;i<platillos.size();i++){
            if(platillos.get(i).yaPedido){
                platillos.remove(i);
                i--;
            }
        }
        tabla.setItems(FXCollections.observableList(platillos));
    }
}
