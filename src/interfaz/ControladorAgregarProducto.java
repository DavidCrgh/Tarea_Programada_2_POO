package interfaz;

import com.sun.org.glassfish.gmbal.DescriptorFields;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Platillo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    boolean disponible;

    public ControladorPrincipalAdministrador controladorAdministrador;
    public Platillo platillo;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        botonSi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disponible=true;
            }
        });
        botonNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disponible =false;
            }
        });

        botonAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(platillo!=null){
                    platillo.setCodigo(textoCodigo.getText());
                    platillo.setNombre(textoNombre.getText());
                    platillo.setDescripcion(textoDescripcion.getText());
                    platillo.setTamanoPorcion(Integer.parseInt(textoTamanoPorcion.getText()));
                    platillo.setCaloriasPorcion(Integer.parseInt(textoCaloriasPorcion.getText()));
                    platillo.setPiezasPorcion(Integer.parseInt(textoPiezasPorcion.getText()));
                    platillo.setPrecio(Integer.parseInt(textoPrecio.getText()));
                    controladorAdministrador.enviarPlatoModificado();


                }
                else{
                    String codigo= textoCodigo.getText();
                    String nombre = textoNombre.getText();
                    String descripcion=textoDescripcion.getText();
                    int tamanoPorcion = Integer.parseInt(textoTamanoPorcion.getText());
                    int caloriasPorcion= Integer.parseInt(textoCaloriasPorcion.getText());
                    int piezasPorcion = Integer.parseInt(textoPiezasPorcion.getText());
                    int precio = Integer.parseInt(textoPrecio.getText());
                    String imagen ="pathTemporal";

                    Platillo platilloNuevo = new Platillo(codigo,nombre,descripcion,tamanoPorcion,caloriasPorcion,piezasPorcion,precio,imagen,disponible);
                    try {
                        controladorAdministrador.usuario.getSalidaDatos().writeInt(2);
                        controladorAdministrador.usuario.getSalidaObjetos().writeObject(platilloNuevo);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

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
}
