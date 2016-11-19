package interfaz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Platillo;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 19-Nov-16.
 */
public class ControladorVerDetalles implements Initializable{
    @FXML
    Label codigo;
    @FXML
    Label nombre;
    @FXML
    Label descripcion;
    @FXML
    Label tamanoPorcion;
    @FXML
    Label piezasPorcion;
    @FXML
    Label caloriasPorcion;
    @FXML
    Label precio;
    @FXML
    Label disponible;
    @FXML
    ImageView imagen;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

    }

    public void precargarDatos(Platillo platillo){
        codigo.setText(platillo.getCodigo());
        nombre.setText(platillo.getNombre());
        descripcion.setText(platillo.getDescripcion());
        tamanoPorcion.setText(""+platillo.getTamanoPorcion());
        piezasPorcion.setText(""+platillo.getPiezasPorcion());
        caloriasPorcion.setText(""+platillo.getCaloriasPorcion());
        precio.setText(""+platillo.getPrecio());
        disponible.setText(platillo.getDisponibleString());
        //imagen.setImage(new Image("recursos\\imagenes\\maxresdefault.jpg"));
    }
}
