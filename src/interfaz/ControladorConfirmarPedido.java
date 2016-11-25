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
import model.LineaPedido;
import model.Pedido;
import model.Platillo;
import model.tipoPedido;
import sockets.client.Usuario;
import model.Cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Bryan on 11/20/2016.
 */
public class ControladorConfirmarPedido implements Initializable{
    @FXML
    private CheckBox recoger;
    @FXML
    private CheckBox express;
    @FXML
    private CheckBox enSitio;
    @FXML
    private Button Modificar;
    @FXML
    private Button refrescar;
    @FXML
    private Button Eliminar;
    @FXML
    private Button enviarPedido;
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

    public ControladorPrincipalCliente controladorCliente;

    public Usuario clienteEnvia;

    public Pedido pedidoSolicitud;

    public ArrayList<LineaPedido>pedidoFinal;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert columnaCodigo != null : "fx:id=\"columnaCodigo\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert columnaPrecio != null : "fx:id=\"columnaPrecio\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert columnaCantidad != null : "fx:id=\"columnaCantidad\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert enviarPedido != null : "fx:id=\"enviarPedido\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert tabla != null : "fx:id=\"tabla\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert Eliminar != null : "fx:id=\"Eliminar\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert refrescar != null : "fx:id=\"refrescar\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert Modificar != null : "fx:id=\"Modificar\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert recoger != null : "fx:id=\"recoger\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert express != null : "fx:id=\"express\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";
        assert enSitio != null : "fx:id=\"enSitio\" was not injected: check your FXML file 'ConfirmarPedido.fxml'.";

        enviarPedido.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pedidoSolicitud=null;
                ArrayList<Platillo> aux = new ArrayList<>();
                for (LineaPedido lineaPedido : pedidoFinal) {
                    aux.add(lineaPedido.getPlatillo());
                }
                try {
                    clienteEnvia.getSalidaDatos().writeInt(8);
                    clienteEnvia.getSalidaObjetos().reset();
                    clienteEnvia.getSalidaObjetos().writeUnshared(aux);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(enSitio.isSelected()){
                    Cliente clienteTemporal= new Cliente(clienteEnvia.getUsuario(),clienteEnvia.getNumeroCelular(),clienteEnvia.getDireccion());
                    pedidoSolicitud = new Pedido(clienteTemporal, pedidoFinal);
                }
                else{
                    if(recoger.isSelected()){
                        Cliente clienteTemporal= new Cliente(clienteEnvia.getUsuario(),clienteEnvia.getNumeroCelular(),clienteEnvia.getDireccion());
                        pedidoSolicitud = new Pedido(clienteTemporal, pedidoFinal);
                        pedidoSolicitud.setTipo(tipoPedido.RECOGER);
                    }
                    else{
                        Cliente clienteTemporal= new Cliente(clienteEnvia.getUsuario(),clienteEnvia.getNumeroCelular(),clienteEnvia.getDireccion());
                        pedidoSolicitud = new Pedido(clienteTemporal, pedidoFinal);
                        pedidoSolicitud.setTipo(tipoPedido.EXPRESS);
                    }
                }
                try{
                    clienteEnvia.getSalidaDatos().writeInt(4);
                    clienteEnvia.getSalidaObjetos().reset();
                    clienteEnvia.getSalidaObjetos().writeUnshared(pedidoSolicitud);
                    //clienteEnvia.getSalidaDatos().flush();
                    //clienteEnvia.getSalidaObjetos().flush();
                    pedidoFinal.clear();
                    controladorCliente.pedidoActual.clear();
                    Stage stage = (Stage)enviarPedido.getScene().getWindow();
                    stage.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        Eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platillo platilloActual= (Platillo) tabla.getSelectionModel().getSelectedItem();
                for (LineaPedido lineaPedido : pedidoFinal) {
                    if(lineaPedido.getPlatillo().getCodigo().equals(platilloActual.getCodigo())){
                        pedidoFinal.remove(lineaPedido);
                        break;
                    }
                }
                construirTabla(pedidoFinal);
            }
        });

        Modificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platillo platilloActual = (Platillo) tabla.getSelectionModel().getSelectedItem();
                System.out.println(platilloActual.getNombre());
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnnadirPedido.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    ControladorAnnadirPedido c = loader.getController();
                    c.annadirPedidoSetLabel(platilloActual.getNombre());
                    c.platillo=platilloActual;
                    for (LineaPedido lineaPedido : pedidoFinal) {
                        if(lineaPedido.getPlatillo().getCodigo().equals(platilloActual.getCodigo())){
                            pedidoFinal.remove(lineaPedido);
                            break;
                        }
                    }
                    c.arrayProductos=pedidoFinal;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.setTitle("Confirmar Producto");
                primaryStage.setScene(new Scene(root, 400, 150));
                primaryStage.show();
            }
        });

        refrescar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                construirTabla(pedidoFinal);
            }
        });
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
