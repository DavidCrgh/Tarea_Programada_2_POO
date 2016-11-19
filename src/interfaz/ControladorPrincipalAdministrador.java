package interfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    private TableColumn columnaDisponible;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonModificar;


    public ArrayList<Platillo> platillos;

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
        columnaDisponible.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("disponibleString")
        );


        botonAgregar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){

                Platillo platillo = (Platillo) tablaProductos.getSelectionModel().getSelectedItem();
                try {
                    Stage primaryStage = new Stage();
                   // FXMLLoader loader = new FXMLLoader();
                    Parent root = FXMLLoader.load(getClass().getResource("AgregarProducto.fxml"));
                   // ControladorPrincipalAdministrador controladorAdministrador = (ControladorPrincipalAdministrador) loader.getController();
                    primaryStage.setTitle("Agregar Producto");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();
                }catch(Exception e){
                    e.printStackTrace();

                }

            }
        });






    }

    public void construirTabla(ArrayList<Platillo> platillos){
        tablaProductos.setItems(FXCollections.observableList(platillos));
    }




}
