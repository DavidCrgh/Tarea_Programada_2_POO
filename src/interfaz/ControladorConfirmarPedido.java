package interfaz;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LineaPedido;
import model.Platillo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bryan on 11/20/2016.
 */
public class ControladorConfirmarPedido implements Initializable{
    @FXML
    private TableColumn columnaCodigo;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private  TableColumn columnaPrecio;
    @FXML
    private TableColumn columnaCantidad;
    @FXML
    private TableView tabla;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert columnaCodigo != null : "fx:id=\"columnaCodigo\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert columnaPrecio != null : "fx:id=\"columnaPrecio\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert columnaCantidad != null : "fx:id=\"columnaCantidad\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert tabla != null : "fx:id=\"tabla\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";

        columnaCodigo.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("codigo")
        );
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("nombre")
        );
        columnaPrecio.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("precio")
        );
        columnaCantidad.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("cantidad")
        );
    }

    public void construirTabla(ArrayList<LineaPedido> platillos){
        for(int i=0;i<platillos.size();i++){
            if(!(platillos.get(i).getPlatillo().isDisponible())){
                platillos.remove(i);
                i--;
            }
        }
        ArrayList<Platillo> platillosAux=new ArrayList<>();
        for(int j=0; j<platillos.size();j++){
            Platillo auxPlatillo = platillos.get(j).getPlatillo();
            platillosAux.add(auxPlatillo);
        }
        tabla.setItems(FXCollections.observableList(platillosAux));
    }
}
