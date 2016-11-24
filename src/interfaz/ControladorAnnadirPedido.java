package interfaz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LineaPedido;
import model.Platillo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

/**
 * Created by Bryan on 11/18/2016.
 */
public class ControladorAnnadirPedido implements Initializable {

    public Platillo platillo;
    public ArrayList<LineaPedido> arrayProductos=new ArrayList<>();
    @FXML
    private Button confirmarBoton;
    @FXML
    private TextField cantidadField;
    @FXML
    private Text Nombre;


    public void annadirPedidoSetLabel(String text){
        Nombre.setText(text);
    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert confirmarBoton != null : "fx:id=\"confirmarBoton\" was not injected: check your FXML file 'AnnadirPedido.fxml'.";
        assert Nombre != null : "fx:id=\"Nombre\" was not injected: check your FXML file 'AnnadirPedido.fxml'.";

        confirmarBoton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int cantidad= Integer.parseInt(cantidadField.getText());
                LineaPedido linea=new LineaPedido((cantidad*platillo.getPrecio()), cantidad, platillo);
                arrayProductos.add(linea);
                Stage stage = (Stage) confirmarBoton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
