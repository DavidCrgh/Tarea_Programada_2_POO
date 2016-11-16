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
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorInicio implements Initializable{
    Utilitarias objetoUtilitario = new Utilitarias();

    @FXML
    private Button botonInicio;
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
                            Parent root = FXMLLoader.load(getClass().getResource("PrincipalAdministrador.fxml"));
                            primaryStage.setTitle("Log as admin");
                            primaryStage.setScene(new Scene(root, 600, 400));
                            primaryStage.show();
                            buscado.abrirConexion();

                        }
                        catch (IOException e){
                            System.out.println(e);
                        }
                    }
                    else{
                        try{
                            Stage primaryStage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource("PrincipalCliente.fxml"));
                            primaryStage.setTitle("Log as Client");
                            primaryStage.setScene(new Scene(root, 600, 400));
                            primaryStage.show();
                            buscado.abrirConexion();
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
                    objetoUtilitario.escribirXML(usuario, contrasenna, numero, direccion, false);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });

    }

}
