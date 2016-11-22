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
    Label imagen;

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

        Image imagenComida= new Image(getClass().getResourceAsStream("flan-coco-sencillo-delicioso_1_1851347[1].jpg"));
        imagen.setGraphic(new ImageView(imagenComida));

       // imagen.setImage(new Image("recursos\\imagenes\\maxresdefault.jpg"));
    }
}
