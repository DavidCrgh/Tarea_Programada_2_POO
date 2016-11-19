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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Platillo;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bryan on 11/10/2016.
 */
public class ControladorPrincipalCliente implements Initializable {
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
    @FXML
    public Button AgregarPedido;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert AgregarPedido != null : "fx:id=\"AgregarPedido\" was not injected: check your FXML file 'PrincipalCliente.fxml'.";

        AgregarPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = new Stage();
                //Parent root = FXMLLoader.load(getClass().getResource("AnnadirPedido.fxml"));
                FXMLLoader loader = new FXMLLoader();
                Parent root = null;
                try {
                    root = loader.load(getClass().getResource("AnnadirPedido.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ControladorPrincipalAdministrador controladorAdministrador = (ControladorPrincipalAdministrador) loader.getController();

                primaryStage.setTitle("Log as admin");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            }
        });
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
        for(int i=0;i<platillos.size();i++){
            if(!(platillos.get(i).isDisponible())){
                platillos.remove(i);
                i--;
            }

        }

        tablaProductos.setItems(FXCollections.observableList(platillos));
    }
}
