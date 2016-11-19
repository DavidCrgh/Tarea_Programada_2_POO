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
import sockets.client.Usuario;

import java.io.IOException;
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
    @FXML
    private Button botonVerDetalles;

    public Usuario usuario;


    public ArrayList<Platillo> platillos;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        tablaProductos.setEditable(true);
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


        botonAgregar.setOnAction(event->{
                Platillo platillo = (Platillo) tablaProductos.getSelectionModel().getSelectedItem();
                try {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Parent root = loader.load(getClass().getResource("AgregarProducto.fxml").openStream());
                    ControladorAgregarProducto controladorProducto= (ControladorAgregarProducto) loader.getController();
                    primaryStage.setTitle("Agregar Producto");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();
                    controladorProducto.controladorAdministrador=this;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        );
        botonModificar.setOnAction(event -> {
            Platillo platilloBuscado = (Platillo) tablaProductos.getSelectionModel().getSelectedItem();
            for (Platillo platillo: platillos) {
                if(platilloBuscado.getCodigo().equals(platillo.getCodigo())){
                    platilloBuscado = platillo;
                    break;
                }
            }
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("AgregarProducto.fxml").openStream());
                ControladorAgregarProducto controlador = (ControladorAgregarProducto) loader.getController();
                stage.setTitle("Modificar Producto");
                controlador.tituloVentana.setText("Modificar producto");
                controlador.platillo = platilloBuscado;
                controlador.precargarDatos(platilloBuscado);
                controlador.controladorAdministrador = this;
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        botonVerDetalles.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("VerDetalles.fxml").openStream());
                ControladorVerDetalles controlador = (ControladorVerDetalles) loader.getController();
                Platillo platillo = (Platillo) tablaProductos.getSelectionModel().getSelectedItem();
                controlador.precargarDatos(platillo);
                stage.setTitle("Detalles de Producto");
                stage.setScene(new Scene(root, 600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void enviarPlatoModificado(){
        tablaProductos.setItems(FXCollections.observableList(platillos));
        usuario.abrirConexion();
        usuario.obtenerFlujos();
        try {
            usuario.getSalidaDatos().writeInt(3);
            usuario.getSalidaObjetos().writeObject(platillos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void construirTabla(ArrayList<Platillo> platillos){
        tablaProductos.setItems(FXCollections.observableList(platillos));
    }
}
