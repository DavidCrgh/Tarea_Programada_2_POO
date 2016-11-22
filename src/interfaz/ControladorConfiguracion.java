package interfaz;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sockets.client.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Creado por David Valverde Garro - 2016034774
 * Fecha: 21-Nov-16 Tiempo: 10:08 PM
 */
public class ControladorConfiguracion implements Initializable{
    @FXML
    private TextField cuadroCostoExpress;
    @FXML
    private TextField cuadroCostoEmpaque;
    @FXML
    private Button botonAceptar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonHacerAdmin;
    @FXML
    private ListView listaUsuarios;

    public Usuario usuario;
    public ArrayList<Usuario> usuarios;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        botonHacerAdmin.setOnAction(event -> {
            String usuarioSelecionado = (String) listaUsuarios.getSelectionModel().getSelectedItem();
            if(usuarioSelecionado != null){
                for(Usuario usuario : usuarios){
                    if(usuario.getUsuario().equals(usuarioSelecionado)){
                        usuario.setAdmin(true);
                        break;
                    }
                }
            }
            usuario.abrirConexion();
            usuario.obtenerFlujos();
            try {
                usuario.getSalidaDatos().writeInt(6);
                usuario.getSalidaObjetos().writeObject(usuarios);
            } catch (IOException e) {
                e.printStackTrace();
            }
            construirLista();
        });
    }

    public void pedirListaUsuarios(){
        usuario.abrirConexion();
        usuario.obtenerFlujos();
        try {
            usuario.getSalidaDatos().writeInt(5);
            usuarios = (ArrayList<Usuario>) usuario.getEntradaObjetos().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        construirLista();
    }

    public void construirLista(){
        ArrayList<String> nombres = new ArrayList<>();
        for(Usuario usuario : usuarios){
            if(!usuario.isAdmin()){
                nombres.add(usuario.getUsuario());
            }
        }
        listaUsuarios.setItems(FXCollections.observableList(nombres));
    }
}
