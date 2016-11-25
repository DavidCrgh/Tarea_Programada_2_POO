package interfaz;

import model.Utilitarias;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InicioSesion;
import sockets.client.ThreadCliente;
import sockets.client.Usuario;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class ControladorInicio implements Initializable{
    private Utilitarias objetoUtilitario = new Utilitarias();

    @FXML
    public Button botonInicio;
    @FXML
    private Button botonCrear;
    @FXML
    private TextField textUsuario;
    @FXML
    private PasswordField textPassword;
    @FXML
    private TextField nombreUsuario;
    @FXML
    private PasswordField contrasennaUsuario;
    @FXML
    private TextField numeroUsuario;
    @FXML
    private TextField direccionUsuario;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert botonInicio != null : "fx:id=\"botonInicio\" was not injected: check your FXML file 'Inicio.fxml'.";
        assert botonCrear != null : "fx:id=\"botonCrear\" was not injected: check your FXML file 'Inicio.fxml'.";

        botonInicio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InicioSesion inicioSesion = new InicioSesion();
                String usuario = textUsuario.getText();
                String contrasenna = textPassword.getText();
                Usuario buscado = inicioSesion.buscarCuenta(usuario, contrasenna);
                if(buscado!=null) {
                    if (buscado.isAdmin()) {
                        try{
                            Stage primaryStage = new Stage();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaz/PrincipalAdministrador.fxml"));
                            Parent root = loader.load();
                            ControladorPrincipalAdministrador controladorAdministrador = (ControladorPrincipalAdministrador) loader.getController();
                            controladorAdministrador.usuario = buscado;
                            primaryStage.setTitle("Log as admin");
                            primaryStage.setScene(new Scene(root, 600, 400));
                            primaryStage.show();

                            buscado.abrirConexion();
                            buscado.obtenerFlujos();

                            controladorAdministrador.usuario=buscado;

                            ThreadCliente administrador = new ThreadCliente(controladorAdministrador,buscado);
                            administrador.start();
                            buscado.getSalidaDatos().writeInt(1);
                            Stage stage = (Stage)botonInicio.getScene().getWindow();
                            stage.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        try{
                            Stage primaryStage = new Stage();
                            FXMLLoader loader = new FXMLLoader();
                            Parent root = loader.load(getClass().getResource("PrincipalCliente.fxml").openStream());
                            ControladorPrincipalCliente controladorCliente = (ControladorPrincipalCliente) loader.getController();
                            controladorCliente.usuario = buscado;
                            primaryStage.setTitle("Log as Client");
                            primaryStage.setScene(new Scene(root, 600, 400));
                            primaryStage.show();

                            buscado.abrirConexion();
                            buscado.obtenerFlujos();
                            ThreadCliente cliente = new ThreadCliente(controladorCliente,buscado);
                            cliente.start();

                            buscado.getSalidaDatos().writeInt(1);
                            Stage stage = (Stage)botonInicio.getScene().getWindow();
                            stage.close();
                        }
                        catch (IOException e){
                            System.out.println(e);
                        }
                    }
                }
            }
        });

        botonCrear.setOnAction(event -> {
            String usuario= nombreUsuario.getText();
            String contrasenna= contrasennaUsuario.getText();
            String direccion= direccionUsuario.getText();
            String numero = numeroUsuario.getText();
            try {
                objetoUtilitario.guardarUsuarioXML(usuario, contrasenna, numero, direccion, false);
            }catch(Exception e){
                e.printStackTrace();
            }
        });
    }
}
