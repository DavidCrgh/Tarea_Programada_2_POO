package interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Utilitarias;

public class MainCliente extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));
        /*FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("Inicio.fxml").openStream());
        ControladorInicio controladorInicio = (ControladorInicio) loader.getController();*/
        primaryStage.setTitle("Inicio Sesión");
        primaryStage.setScene(new Scene(root, 505, 260));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


