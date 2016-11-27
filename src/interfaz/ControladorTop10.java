package interfaz;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Platillo;

import java.net.URL;
import java.util.*;

/**
 * Created by Francisco Contreras on 27/11/2016.
 */

public class ControladorTop10 implements Initializable {

    @FXML
    public TableView tablaTop10;
    @FXML
    public TableColumn columnaCodigo;
    @FXML
    public TableColumn columnaNombre;
    @FXML
    public TableColumn columnaDescripcion;


    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        columnaCodigo.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("codigo")
        );
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("nombre")
        );
        columnaDescripcion.setCellValueFactory(
                new PropertyValueFactory<Platillo,String>("descripcion")
        );

    }


    public void construirTabla(ArrayList<Platillo> platillos){
        if(platillos.size()<=10) {
            tablaTop10.setItems(FXCollections.observableList(platillos));
        }
        else{

            Platillo [] arregloPlatillo = new Platillo[platillos.size()];
            arregloPlatillo = platillos.toArray(arregloPlatillo);
            Platillo tmp=null;
            int j;
            for (int i=0; i<platillos.size(); i++){
                j=i;

                while(j>0 && Integer.parseInt(arregloPlatillo[j].cantidad)> Integer.parseInt(arregloPlatillo[j-1].cantidad)){
                    tmp=arregloPlatillo[j];
                    arregloPlatillo[j]=arregloPlatillo[j-1];
                    arregloPlatillo[j-1]=tmp;
                    j--;

                }

            }

            ArrayList<Platillo> arregloTemporal= new ArrayList<>();
            for(int r=0;r<arregloPlatillo.length-1;r++){
                    arregloTemporal.add(arregloPlatillo[r]);
            }
            tablaTop10.setItems(FXCollections.observableList(arregloTemporal));
        }

    }



}
