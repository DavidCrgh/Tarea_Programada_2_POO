package interfaz;

import javafx.application.Platform;
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
import model.LineaPedido;
import model.Pedido;
import model.Platillo;
import sockets.client.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bryan on 11/10/2016.
 */
public class ControladorPrincipalCliente implements Initializable {
    public ArrayList<LineaPedido> pedidoActual;
    @FXML
    private TableView<Platillo> tablaProductos;
    @FXML
    private TableColumn columnaCodigo;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaCalorias;
    @FXML
    private TableColumn columnaPrecio;
    @FXML
    private Button verPedido;
    @FXML
    public Button AgregarPedido;
    @FXML
    private Button botonVerDetalles;

    public int costoEmpaque;
    public int costoExpress;

    public Usuario usuario;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert AgregarPedido != null : "fx:id=\"AgregarPedido\" was not injected: check your FXML file 'PrincipalCliente.fxml'.";
        assert verPedido !=null : "fx:id=\"verPedido\" was not injected: check your FXML file 'PrincipalCliente.fxml'.";
        pedidoActual= new ArrayList<LineaPedido>();
        AgregarPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Platillo platilloActual = tablaProductos.getSelectionModel().getSelectedItem();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnnadirPedido.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    ControladorAnnadirPedido c = loader.getController();
                    c.annadirPedidoSetLabel(platilloActual.getNombre());
                    c.platillo=platilloActual;
                    c.arrayProductos=pedidoActual;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.setTitle("Confirmar Producto");
                primaryStage.setScene(new Scene(root, 400, 150));
                primaryStage.show();
            }
        });
        verPedido.setOnAction(event-> {
                try {
                    solicitarCostosConfiguraciones();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmarPedido.fxml"));
                    Parent root = loader.load();
                    ControladorConfirmarPedido controladorConfirmarPedido = loader.getController();
                    primaryStage.setTitle("Confirmar Pedido");
                    primaryStage.setScene(new Scene(root, 520, 320));
                    primaryStage.show();
                    controladorConfirmarPedido.costoEmpaque= this.costoEmpaque;
                    controladorConfirmarPedido.costoExpress=this.costoExpress;
                    controladorConfirmarPedido.actualizarToolTips();
                    controladorConfirmarPedido.construirTabla(pedidoActual);
                    controladorConfirmarPedido.pedidoFinal=pedidoActual;
                    controladorConfirmarPedido.clienteEnvia=usuario;
                    controladorConfirmarPedido.controladorCliente=this;

                    Platform.runLater(()->{
                        int totalPrecio=0;
                        int calorias=0;
                        for(int i=0;i<controladorConfirmarPedido.pedidoFinal.size();i++){
                            LineaPedido lineaTemporal = controladorConfirmarPedido.pedidoFinal.get(i);

                            totalPrecio+=controladorConfirmarPedido.pedidoFinal.get(i).getCantidad();
                            calorias+= lineaTemporal.calcularTotalCalorias();
                        }
                        controladorConfirmarPedido.labelPrecioTotal.setText(totalPrecio+"");
                        controladorConfirmarPedido.labelTotalCalorias.setText(calorias+"");


                    });
                }
                catch (IOException e){System.out.println(e);}

        });

        botonVerDetalles.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("VerDetalles.fxml").openStream());
                ControladorVerDetalles controlador = (ControladorVerDetalles) loader.getController();
                Platillo platillo = tablaProductos.getSelectionModel().getSelectedItem();
                controlador.precargarDatos(platillo);
                stage.setTitle("Detalles de Producto");
                stage.setScene(new Scene(root, 600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
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

    public void solicitarCostosConfiguraciones(){
        try {
            usuario.getSalidaDatos().writeInt(12);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
