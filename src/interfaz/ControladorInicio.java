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
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorInicio implements Initializable{
    Utilitarias objetoUtilitario = new Utilitarias();
    private Socket cliente;
    private Socket administrador;
    private DataOutputStream salidaDatoCliente;
    private DataInputStream entradaDatoCliente;
    private ObjectInputStream entradaObjetoCliente;
    private ObjectOutputStream salidaObjetoCliente;

    private DataOutputStream salidaDatoAdmin;
    private DataInputStream entradaDatoAdmin;
    private ObjectInputStream entradaObjetoAdmin;
    private ObjectOutputStream salidaObjetoAdmin;

    @FXML
    public Button botonInicio;
    @FXML
    private  Button botonCrear;
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
                            // Parent root = FXMLLoader.load(getClass().getResource("PrincipalAdministrador.fxml"));
                            FXMLLoader loader = new FXMLLoader();
                            Parent root = loader.load(getClass().getResource("PrincipalAdministrador.fxml").openStream());
                            ControladorPrincipalAdministrador controladorAdministrador = (ControladorPrincipalAdministrador) loader.getController();

                            primaryStage.setTitle("Log as admin");
                            primaryStage.setScene(new Scene(root, 600, 400));
                            primaryStage.show();
                            administrador= inicioSesion.abrirConexion();
                            salidaDatoAdmin = new DataOutputStream(administrador.getOutputStream());
                            salidaDatoAdmin.flush();
                            entradaDatoAdmin = new DataInputStream(administrador.getInputStream());

                            salidaObjetoAdmin = new ObjectOutputStream(administrador.getOutputStream());
                            salidaObjetoAdmin.flush();
                            entradaObjetoAdmin= new ObjectInputStream(administrador.getInputStream());

                            ThreadCliente administrador = new ThreadCliente(controladorAdministrador,entradaDatoAdmin,entradaObjetoAdmin);
                            administrador.start();

                            salidaDatoAdmin.writeInt(1);
                        }
                        catch (IOException e){
                            System.out.println(e);
                        }
                    }
                    else{
                        try{
                            Stage primaryStage = new Stage();
                           // Parent root = FXMLLoader.load(getClass().getResource("PrincipalCliente.fxml"));
                            FXMLLoader loader = new FXMLLoader();
                            Parent root = loader.load(getClass().getResource("PrincipalCliente.fxml").openStream());
                            ControladorPrincipalCliente controladorCliente = (ControladorPrincipalCliente) loader.getController();

                            primaryStage.setTitle("Log as Client");
                            primaryStage.setScene(new Scene(root, 600, 400));
                            primaryStage.show();
                            cliente= inicioSesion.abrirConexion();
                            salidaDatoCliente = new DataOutputStream(cliente.getOutputStream());
                            salidaDatoCliente.flush();
                            entradaDatoCliente = new DataInputStream(cliente.getInputStream());

                            salidaObjetoCliente = new ObjectOutputStream(cliente.getOutputStream());
                            salidaObjetoCliente.flush();
                            entradaObjetoCliente= new ObjectInputStream(cliente.getInputStream());



                            ThreadCliente cliente = new ThreadCliente(controladorCliente,entradaDatoCliente,entradaObjetoCliente);
                            cliente.start();
                            salidaDatoCliente.writeInt(1);
                        }
                        catch (IOException e){
                            System.out.println(e);
                        }
                    }
                }
            }
        });

        botonCrear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){

                String usuario= nombreUsuario.getText();
                String contrasenna= contrasennaUsuario.getText();
                String direccion= direccionUsuario.getText();
                String numero = numeroUsuario.getText();
                try {
                    objetoUtilitario.guardarUsuarioXML(usuario, contrasenna, numero, direccion, false);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });

    }


    public void actualizarVentana(){
    for(int i=0;i<9999999;i++)
        textUsuario.setText(""+i);


    }


}
