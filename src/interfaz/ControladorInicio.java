package interfaz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorInicio implements Initializable{

    @FXML
    private Button botonInicio;
    @FXML
    private  Button botonCrear;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert botonInicio != null : "fx:id=\"botonInicio\" was not injected: check your FXML file 'Inicio.fxml'.";
        assert botonCrear != null : "fx:id=\"botonCrear\" was not injected: check your FXML file 'Inicio.fxml'.";

        botonInicio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("PrincipalAdministrador.fxml"));
                    primaryStage.setTitle("Log as admin");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();
                } catch (IOException e){
                    System.out.println(e);
                }
            }
        });

        botonCrear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("PrincipalCliente.fxml"));
                    primaryStage.setTitle("Log as Client");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();
                } catch (IOException e){
                    System.out.println(e);
                }
            }
        });

    }

}
