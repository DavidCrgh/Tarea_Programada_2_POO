package model;

import sockets.client.Usuario;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/15/2016.
 * Esta clase se utiliza para comprobar que el usuario y la contraseña digitados sean los mismos que los datos en el xml de las cuentas
 */
public class InicioSesion {
    public ArrayList<Usuario> cuentas;

    Utilitarias objetoUtilitario = new Utilitarias();

    public InicioSesion(){
        cuentas = new ArrayList<>();

        try {
            cuentas =objetoUtilitario.cargarCuentas();
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarCuenta(String usuario, String contrasenna){
        int iteraciones = cuentas.size()-1;
        while(iteraciones>=0) {
            Usuario actual = cuentas.get(iteraciones);
            if (actual.getClave().equals(contrasenna) && actual.getUsuario().equals(usuario))
                return actual;
            iteraciones--;
        }
        return null;
    }
}
