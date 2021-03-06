package interfaz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Platillo;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Francisco Contreras on 18/11/2016.
 */
public class ControladorAgregarProducto implements Initializable {

    @FXML
    public Label tituloVentana;
    @FXML
    private TextField textoCodigo;
    @FXML
    private TextField textoNombre;
    @FXML
    private TextArea textoDescripcion;
    @FXML
    private TextField textoTamanoPorcion;
    @FXML
    private TextField textoCaloriasPorcion;
    @FXML
    private TextField textoPiezasPorcion;
    @FXML
    private TextField textoPrecio;
    @FXML
    private Button botonImagen;
    @FXML
    private RadioButton botonSi;
    @FXML
    private RadioButton botonNo;
    @FXML
    private Button botonAceptar;
    private File imagen;
    boolean disponible;


    public ControladorPrincipalAdministrador controladorAdministrador;
    public Platillo platillo;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        botonImagen.setOnAction(event -> {
            Stage stage = (Stage) botonImagen.getScene().getWindow();

            FileChooser fileChooser = new FileChooser();
            configurarFileChooser(fileChooser);

            imagen = fileChooser.showOpenDialog(stage);

        });


        botonAceptar.setOnAction(event -> {
            final File imagenPlatillo = imagen;
            if (platillo != null) {
                platillo.setCodigo(textoCodigo.getText());
                platillo.setNombre(textoNombre.getText());
                platillo.setDescripcion(textoDescripcion.getText());
                platillo.setTamanoPorcion(Integer.parseInt(textoTamanoPorcion.getText()));
                platillo.setCaloriasPorcion(Integer.parseInt(textoCaloriasPorcion.getText()));
                platillo.setPiezasPorcion(Integer.parseInt(textoPiezasPorcion.getText()));
                platillo.setPrecio(Integer.parseInt(textoPrecio.getText()));
                String imagenModificada;
                if(imagenPlatillo==null){
                    imagenModificada ="No existe";
                    platillo.setImagen(imagenModificada);
                }
                else {
                    imagenModificada = imagenPlatillo.getName();
                    platillo.setImagen(imagenModificada);
                }

                if (botonSi.isSelected()) {
                    platillo.setDisponible(true);
                    platillo.setDisponibleString("Si");
                } else {
                    platillo.setDisponible(false);
                    platillo.setDisponibleString("No");
                }
                controladorAdministrador.enviarPlatoModificado();
                Stage stage = (Stage)botonAceptar.getScene().getWindow();
                stage.close();

            } else {
                String codigo = textoCodigo.getText();
                String nombre = textoNombre.getText();
                String descripcion = textoDescripcion.getText();
                int tamanoPorcion = Integer.parseInt(textoTamanoPorcion.getText());
                int caloriasPorcion = Integer.parseInt(textoCaloriasPorcion.getText());
                int piezasPorcion = Integer.parseInt(textoPiezasPorcion.getText());
                int precio = Integer.parseInt(textoPrecio.getText());
                String imagen;
                if(imagenPlatillo==null){
                    imagen ="No existe";
                }
                else {
                     imagen = imagenPlatillo.getName();
                }
                System.out.println(imagen);

                boolean disponible = botonSi.isSelected();

                Platillo platilloNuevo = new Platillo(codigo, nombre, descripcion, tamanoPorcion, caloriasPorcion, piezasPorcion, precio, imagen, disponible);
                try {
                    controladorAdministrador.platillos.add(platilloNuevo);
                    controladorAdministrador.construirTabla(controladorAdministrador.platillos);
                    controladorAdministrador.usuario.getSalidaDatos().writeInt(2);
                    controladorAdministrador.usuario.getSalidaObjetos().writeObject(platilloNuevo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void precargarDatos(Platillo platillo){
        textoCodigo.setText(platillo.getCodigo());
        textoNombre.setText(platillo.getNombre());
        textoDescripcion.setText(""+platillo.getDescripcion());
        textoTamanoPorcion.setText(""+platillo.getTamanoPorcion());
        textoCaloriasPorcion.setText(""+platillo.getCaloriasPorcion());
        textoPiezasPorcion.setText(""+platillo.getPiezasPorcion());
        //textoCaloriasPieza.setText(""+platillo.get);
        textoPrecio.setText(""+platillo.getPrecio());
        if(platillo.isDisponible()){
            botonSi.setSelected(true);
            botonNo.setSelected(false);
        }else{
            botonSi.setSelected(false);
            botonNo.setSelected(true);
        }
    }
    private void configurarFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes(*.PNG,*.JPG)","*.png","*.jpg")
        );
    }
}
