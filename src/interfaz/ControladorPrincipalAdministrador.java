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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.LinearGradient;
import javafx.stage.Stage;
import model.*;
import model.Platillo;
import model.Utilitarias;
import sockets.client.Usuario;

import java.io.IOException;
import java.lang.reflect.Array;
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
    @FXML
    private Button botonVer;
    @FXML
    private Button botonCompletar;
    @FXML
    public TableView<Cliente> tablePedidos;
    @FXML
    private TableColumn columnaCliente;
    @FXML
    private TableColumn columnaFecha;
    @FXML
    private TableColumn columnaPrecioPedido;
    @FXML
    private Button botonActualizar;
    @FXML
    private ToggleButton botonFiltar;
    @FXML
    private RadioButton botonFiltrarTipo;
    @FXML
    private RadioButton botonFiltrarDisponibilidad;
    @FXML
    private ChoiceBox cajaTipos;
    @FXML
    private ChoiceBox cajaDisponibilidad;
    @FXML
    private MenuItem botonConfiguracion;
    @FXML
    private MenuItem consultaNoPedidos;
    @FXML
    private MenuItem consultaTop10;
    @FXML
    private MenuItem consultaRelacionesPorcentuales;

    private ToggleGroup grupoBotonesFiltro;

    public ControladorConsultaNoPedidos controladorConsultaNoPedidos;

    public ControladorTop10 controladorTop10;

    public Usuario usuario;

    public ArrayList<Platillo> platillos;

    public ListaPedidos lineasTemporal;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        grupoBotonesFiltro = new ToggleGroup();
        botonFiltrarTipo.setToggleGroup(grupoBotonesFiltro);
        botonFiltrarDisponibilidad.setToggleGroup(grupoBotonesFiltro);
        botonFiltrarTipo.setSelected(true);

        botonVer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pedido pedidoSeleccionado = lineasTemporal.getPedidos().get(tablePedidos.getSelectionModel().getFocusedIndex());

                try {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VerPedidoAdmi.fxml"));
                    Parent root = loader.load();
                    ControladorVerPedidoAdmi controladorVerPedidoAdmi = loader.getController();
                    primaryStage.setTitle("Pedido");
                    primaryStage.setScene(new Scene(root, 595,465));
                    primaryStage.show();
                    controladorVerPedidoAdmi.construirTabla(pedidoSeleccionado.getLineasPedido());
                    Platform.runLater(()->{

                        controladorVerPedidoAdmi.cargarInformacion(pedidoSeleccionado);

                        if(pedidoSeleccionado.tipo==tipoPedido.ENSITIO)
                            controladorVerPedidoAdmi.labelTipoPedido.setText("En Sitio");
                        else if(pedidoSeleccionado.tipo==tipoPedido.RECOGER)
                            controladorVerPedidoAdmi.labelTipoPedido.setText("Recoger");
                        else
                            controladorVerPedidoAdmi.labelTipoPedido.setText("Express");
                    });
                }
                catch (IOException e){System.out.println(e);}
            }
        });

        botonFiltar.setOnAction(event -> {
            if(botonFiltar.isSelected()){
                if(botonFiltrarTipo.isSelected()){
                    aplicarFiltro(cajaTipos.getSelectionModel().getSelectedItem().toString());
                } else{
                    aplicarFiltro(cajaDisponibilidad.getSelectionModel().getSelectedItem().toString());
                }
            } else{
                construirTabla(platillos);
            }
        });

        cajaTipos.setItems(FXCollections.observableArrayList("ENT","PRN","PTR","BEB"));
        cajaTipos.getSelectionModel().selectFirst();
        cajaDisponibilidad.setItems(FXCollections.observableArrayList("Disponible","No disponible"));
        cajaDisponibilidad.getSelectionModel().selectFirst();

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
        tablePedidos.setEditable(true);
        columnaCliente.setCellValueFactory(
                new PropertyValueFactory<Cliente,String>("nombre")
        );
        columnaFecha.setCellValueFactory(
                new PropertyValueFactory<Cliente,String>("fechaPedido")
        );
        columnaPrecioPedido.setCellValueFactory(
                new PropertyValueFactory<Cliente,String>("precioPedido")
        );

        botonActualizar.setOnAction(event->{
            try {
                usuario.getSalidaDatos().writeInt(7);
               // ListaPedidos listaPedidosClientes = (ListaPedidos)usuario.getEntradaObjetos().readObject();
              //  construirTablaPedidos(listaPedidosClientes);
            }catch(Exception e ){
                e.printStackTrace();
            }


        });

        botonCompletar.setOnAction(event ->{

            Cliente clientePedido = tablePedidos.getSelectionModel().getSelectedItem();
            System.out.println("Precio del pedido del cliente" +clientePedido.precioPedido);
            for(int i=0;i<clientePedido.pedidoEnviado.getLineasPedido().size();i++){
                System.out.println(clientePedido.pedidoEnviado.getLineasPedido().get(i).getCantidadPiezas());
                System.out.println(clientePedido.pedidoEnviado.getLineasPedido().get(i).getPlatillo().getNombre());
                System.out.println(clientePedido.pedidoEnviado.tipo);
            }
            try {
                usuario.getSalidaDatos().writeInt(8);
                usuario.getSalidaObjetos().reset();
                usuario.getSalidaObjetos().writeUnshared(clientePedido);
            }catch(Exception e){
                e.printStackTrace();
            }

        });

        botonAgregar.setOnAction(event->{
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
            if (platilloBuscado != null) {
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
            }
        });

        botonEliminar.setOnAction(event -> {
            Platillo platilloEliminado = (Platillo) tablaProductos.getSelectionModel().getSelectedItem();
            if(platilloEliminado != null){
                for (Platillo platillo:platillos) {
                    if(platillo.getCodigo().equals(platilloEliminado.getCodigo())){
                        platilloEliminado = platillo;
                        break;
                    }
                }
                platillos.remove(platilloEliminado);
                construirTabla(platillos);
                usuario.abrirConexion();
                usuario.obtenerFlujos();
                try {
                    usuario.getSalidaDatos().writeInt(3);
                    usuario.getSalidaObjetos().writeObject(platillos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        botonConfiguracion.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("Configuracion.fxml").openStream());
                ControladorConfiguracion controlador = (ControladorConfiguracion) loader.getController();
                controlador.usuario = usuario;
                controlador.pedirListaUsuarios();
                stage.setTitle("Configuración de Aplicación");
                stage.setScene(new Scene(root, 475,346));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        consultaTop10.setOnAction(event->{

            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("Top10.fxml").openStream());
                ControladorTop10 controlador = loader.getController();
                controladorTop10=controlador;

                usuario.getSalidaDatos().writeInt(13);

                stage.setTitle("Top 10 de platillos");
                stage.setScene(new Scene(root,600,400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        consultaNoPedidos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Parent root = loader.load(getClass().getResource("consultaNoPedidos.fxml").openStream());
                    ControladorConsultaNoPedidos controlador = (ControladorConsultaNoPedidos) loader.getController();
                    controladorConsultaNoPedidos=controlador;

                    try{
                        usuario.getSalidaDatos().writeInt(10);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    controlador.construirTabla(platillos);
                    stage.setTitle("Nunca Pedidos");
                    stage.setScene(new Scene(root, 520,320));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void construirTablaPedidos(ListaPedidos pedidos){
        lineasTemporal=pedidos;
        ArrayList<Cliente> clientes = new ArrayList<>();
        int precioTotal=0;
        for(Pedido pedidoTemporal:pedidos.getPedidos()){
            precioTotal=0;
            Cliente clienteTemporal = pedidoTemporal.getCliente();
            for(LineaPedido lineaPedido: pedidoTemporal.getLineasPedido()){
                precioTotal+=lineaPedido.getCantidad();
            }
            System.out.println(pedidoTemporal.getNumeroPedido());
            clienteTemporal.precioPedido=precioTotal+"";
            clienteTemporal.fechaPedido= clienteTemporal.getFecha(pedidoTemporal.getFecha());
            clienteTemporal.pedidoEnviado=pedidoTemporal;
            clientes.add(pedidoTemporal.getCliente());
        }
        tablePedidos.setItems(FXCollections.observableList(clientes));
    }

    public void aplicarFiltro(String filtro){
        if(filtro.equals("Disponible")){
            construirTabla(Utilitarias.filtrarPor(platillos,"Si"));
        } else if(filtro.equals("No disponible")){
            construirTabla(Utilitarias.filtrarPor(platillos,"No"));
        } else {
            construirTabla(Utilitarias.filtrarPor(platillos,filtro));
        }
    }

}
