package interfaz;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import sockets.client.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bryan on 11/20/2016.
 */
public class ControladorVerPedidoAdmi implements Initializable{
    @FXML
    private Button entregar;
    @FXML
    private TableColumn columnaCodigo;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaPrecio;
    @FXML
    private TableColumn columnaCantidad;
    @FXML
    private TableView tabla;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert columnaCodigo != null : "fx:id=\"columnaCodigo\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert columnaPrecio != null : "fx:id=\"columnaPrecio\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert columnaCantidad != null : "fx:id=\"columnaCantidad\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert entregar != null : "fx:id=\"entregar\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";
        assert tabla != null : "fx:id=\"tabla\" was not injected: check your FXML file 'VerPedidoAdmi.fxml'.";

        entregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });/*
        refrescar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                construirTabla(pedidoFinal);
            }
        });*/
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
            auxPlatillo.cantidad= Integer.toString(platillos.get(j).getCantidadPiezas());
            platillosAux.add(auxPlatillo);
        }
        tabla.setItems(FXCollections.observableList(platillosAux));
    }
}
